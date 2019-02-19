package com.philippe75.libraryBatch.bootstrap;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.philippe75.libraryBatch.stub.generated.borrowingServ.Book;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingDto;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingService;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingServiceImplService;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.LibraryServiceException_Exception;
import com.philippe75.libraryBatch.tools.headerWriter.CustomHeaderWriter;
import com.philippe75.libraryBatch.tools.processor.BorrowingDtoProcessor;
import com.philippe75.libraryBatch.tools.tasklet.LateBorrowingEmailSender;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public LateBorrowingEmailSender lateBorrowingEmailSender;

	/**
	 * 
	 * Batch reader. 
	 * 
	 * @return reader {@link ItemReader}
	 */
	@Bean
	public ItemReader<BorrowingDto> itemReader() {

		List<BorrowingDto> lb = null;
		BorrowingService borrowingService = new BorrowingServiceImplService().getBorrowingServiceImplPort();
		
		try {
			lb = (List<BorrowingDto>) borrowingService.getAllLateBorrowings().getItem();
		} catch (LibraryServiceException_Exception ex) {
			ex.printStackTrace();
		}
		
		ListItemReader<BorrowingDto> reader = new ListItemReader<BorrowingDto>(lb);
		
		return reader;
	}
	
	/**
	 * Batch processor.
	 * 
	 * @return processor {@link ItemProcessor}
	 */
    @Bean
    public ItemProcessor<BorrowingDto, BorrowingDto> itemProcessor() {
        return new BorrowingDtoProcessor();
    }
	
	/**
	 * Batch writer.
	 * 
	 * @return processor {@link ItemProcessor}
	 */
    @Bean
    public ItemWriter<BorrowingDto> itemWriter (){
    	
    	FlatFileItemWriter<BorrowingDto> writer = new FlatFileItemWriter<>();
    	writer.setResource(new ClassPathResource("borrowings.csv"));
    	// all job repetitions to the same file
    	writer.setAppendAllowed(true);
    	
    	CustomHeaderWriter chw = new CustomHeaderWriter();
    	chw.setHeader("Borrowing Id,Supposed End Date,Name,Surname, book Name, Book id");
    	writer.setHeaderCallback(chw);
    	//replace the .csv file if exists
    	writer.setShouldDeleteIfExists(true);
    	DelimitedLineAggregator<BorrowingDto> dla = new DelimitedLineAggregator<>();
    	dla.setDelimiter(",");
    	
    	BeanWrapperFieldExtractor<BorrowingDto> bwfe = new BeanWrapperFieldExtractor<>();
    	bwfe.setNames(new String [] {"id", "supposedEndDate","userAccount.firstName","userAccount.sureName","book.name","book.id"});
    	
    	dla.setFieldExtractor(bwfe);
    	writer.setLineAggregator(dla);
    	
    	return writer;
    }
    
    
    @Bean
    public Step stepStoreLateBorrowingsToCSV(@Qualifier("itemReader") ItemReader<BorrowingDto> reader, @Qualifier("itemProcessor") ItemProcessor<BorrowingDto, BorrowingDto> processor, @Qualifier("itemWriter") ItemWriter<BorrowingDto> writer) {
    	return stepBuilderFactory.get("stepStoreLateBorrowingToCSV").<BorrowingDto, BorrowingDto>chunk(10).reader(reader).processor(processor).writer(writer).allowStartIfComplete(true).build(); 
    }
    
    @Bean
    public Step stepSendEmailToUser() {
    	return stepBuilderFactory 
    			.get("stepSendEmailToUser")
    			.tasklet(lateBorrowingEmailSender)
    			.build();
    }
    
    @Bean(name="batchJob1")
    public Job job(@Qualifier("stepStoreLateBorrowingsToCSV") Step step1, @Qualifier("stepSendEmailToUser") Step step2) {		// @Qualifier inject Step
    	return jobBuilderFactory.get("SendEmailToLateBorrowings").incrementer(new RunIdIncrementer()).start(step1).next(step2).build();
    }
	
	
}







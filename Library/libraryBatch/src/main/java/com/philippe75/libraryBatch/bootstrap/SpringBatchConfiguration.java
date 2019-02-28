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

import com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingDto;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingService;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.LibraryServiceException_Exception;
import com.philippe75.libraryBatch.tools.headerWriter.CustomHeaderWriter;
import com.philippe75.libraryBatch.tools.processor.BorrowingDtoProcessor;
import com.philippe75.libraryBatch.tools.tasklet.LateBorrowingEmailSender;
import com.philippe75.libraryBatch.tools.tasklet.LateBorrowingProcessor;
import com.philippe75.libraryBatch.tools.tasklet.LateBorrowingReader;

/**
 * <b>Spring Batch configuration Class</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Configuration
@EnableBatchProcessing
public class SpringBatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public BorrowingService borrowingService;
	
	@Autowired
	public LateBorrowingReader lateBorrowingReader;
	
	@Autowired
	public LateBorrowingProcessor lateBorrowingProcessor;
	
	@Autowired
	public LateBorrowingEmailSender lateBorrowingEmailSender;
	
	
	// ------- Sending emails to late borrowing via tasklet way (+ code externalized in class ) ---------------

	
	
	// ------------------ Creation of CSV file via Chunk way ( + code internalized ) ------------------

	/**
	 * 
	 * Batch reader. 
	 * 
	 * @return reader {@link ItemReader}
	 */
	@Bean
	public ItemReader<BorrowingDto> itemReader() {

		List<BorrowingDto> lb = null;
		
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
    
    //========== STEPS ==========
    
    // ------------------------------- Steps Task way ----------------------------------------
    
	@Bean
	public Step readLateBorrowing() {
		return stepBuilderFactory.get("stepReadLateBorrowings")
				.tasklet(lateBorrowingReader)
				.allowStartIfComplete(true)
				.build();
	}
	@Bean
	public Step processLateBorrowing() {
		return stepBuilderFactory.get("stepProcessLateBorrowings")
				.tasklet(lateBorrowingProcessor)
				.allowStartIfComplete(true)
				.build();
	}
	
    @Bean
    public Step SendEmailToUser() {
    	return stepBuilderFactory 
    			.get("stepSendEmailToUser")
    			.tasklet(lateBorrowingEmailSender)
    			.allowStartIfComplete(true)
    			.build();
    }
    
    // ------------------------------- Step chunk way ----------------------------------------
    
   @Bean
    public Step StoreLateBorrowingsToCSV(@Qualifier("itemReader") ItemReader<BorrowingDto> reader, @Qualifier("itemProcessor") ItemProcessor<BorrowingDto, BorrowingDto> processor, @Qualifier("itemWriter") ItemWriter<BorrowingDto> writer) {
    	return stepBuilderFactory.get("stepStoreLateBorrowingToCSV").<BorrowingDto, BorrowingDto>chunk(10).reader(reader).processor(processor).writer(writer).allowStartIfComplete(true).build(); 
    }
    
    //========== JOB ==========
    
    @Bean(name="batchJob1")
    public Job job(@Qualifier("StoreLateBorrowingsToCSV") Step step1, @Qualifier("readLateBorrowing") Step step2, @Qualifier("processLateBorrowing") Step step3, @Qualifier("SendEmailToUser") Step step4) {		// @Qualifier inject Step
    	return jobBuilderFactory.get("LateBorrowingsJob")
    			.incrementer(new RunIdIncrementer())
    			.start(step1)
    			.next(step2)
    			.next(step3)
    			.next(step4)
    			.build();
    }
	
}







package com.philippe75.libraryBatch.tools.processor;

import org.springframework.batch.item.ItemProcessor;

import com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingDto;

/**
 * <b>Batch processor</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public class BorrowingDtoProcessor implements ItemProcessor<BorrowingDto, BorrowingDto> {

	/**
	 * Allows data treatement. 
	 */
	@Override
	public BorrowingDto process(BorrowingDto borrowingDto) throws Exception {
		return borrowingDto;
	}
}

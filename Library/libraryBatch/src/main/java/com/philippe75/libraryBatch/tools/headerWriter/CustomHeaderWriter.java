package com.philippe75.libraryBatch.tools.headerWriter;

import java.io.IOException;
import java.io.Writer;

import org.springframework.batch.item.file.FlatFileHeaderCallback;

public class CustomHeaderWriter implements FlatFileHeaderCallback{

	private String header;
	
	//G&S
	public void setHeader(String header) {
		this.header = header;
	}

	@Override
	public void writeHeader(Writer writer) throws IOException {
		writer.write(header);
	}

	
	
}

package org.tonylin.util;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class CloseStreamUtils {
	/**
	 * Close the writer stream.
	 * 
	 * @param writer
	 */
	static public void closeWriter(Writer writer){
		if( writer == null )
			return;
		try {
			writer.close();
		} catch (IOException e) {
			//Ignore exception
		}
	}
	
	/**
	 * Close the reader stream.
	 * 
	 * @param reader
	 */
	static public void closeReader(Reader reader){
		if( reader == null )
			return;
		try {
			reader.close();
		} catch (IOException e) {
			//Ignore exception
		}
	}
}

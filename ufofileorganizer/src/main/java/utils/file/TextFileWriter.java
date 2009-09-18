/**
 * 
 */
package utils.file;

import java.util.ArrayList;

/**
 * @author ufo
 *
 */
public interface TextFileWriter {
	boolean writeLine(String s, boolean append);
	
	boolean writeLines(ArrayList<String> text, boolean append);
}

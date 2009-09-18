//	FileWriteUtils.java
package utils.file;

/** Questa classe offre metodi per la scritture di stringhe su un file di testo
 * 
 * @author Francesco Cinà
 * @version 0.5
*/

import java.io.*;
import java.util.*;

public class FileTxtWriter implements TextFileWriter{

	private File fileName = null;
	
	public FileTxtWriter(File fileName){
		this.fileName = fileName;
	}
	
	/**
	 * Questo metodo scrive un vettore di stringhe in un file 
	 * @param text un vettore di stringhe che verranno scritte una per riga nel file destinazione
	 * @param append se è true le stringhe verranno appese in coda al contenuto del file, altrimenti il file verrà sovrascritto
	 */
	public boolean writeLines(ArrayList<String> text, boolean append){

		try
		{
			FileWriter fw = new FileWriter (fileName, append);
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter outFile = new PrintWriter (bw);
			
			for (int i = 0; i < text.size(); i++ )
			{
				outFile.println(text.get(i));
			}
			
			outFile.close();
			bw.close();
			fw.close();
		}
		catch (FileNotFoundException exception){
			return false;
		}
		catch (IOException exception){
			return false;
		}	
		catch (NullPointerException exception){
			return false;
		}
		return true;
	}
	
	/**
	 * Questo metodo scrive una stringa in un file
	 * @param riga la riga che verrà scritta nel file
	 * @param append se è true la stringa verrà appesa in coda al contenuto del file, altrimenti il file verrà sovrascritto
	 */
	public boolean writeLine(String riga, boolean append){
		ArrayList<String> arr = new ArrayList<String>();
		arr.add(riga);
		return writeLines(arr, append);
		
	}		

}



//	FileReadUtils.java
package utils.file;

/** Questo file offre metodi per la lettura di stringhe da un file di testo
 * 
 * @author Francesco Cinà
 * @version 1.0
*/

import java.io.*;
import java.util.*;

public class FileTxtReader implements TextFileReader {

	private File fileName = null;
	
	public FileTxtReader(File fileName){
		this.fileName = fileName;
	}
	
	/**
	 * Questo metodo statico legge riga per riga l'intero file e restituisce un vettore di stringhe che contiene tutto quanto
	 * letto nel file di input.
	 * 
	 * @param fileName
	 * @return ritorna un vettore di stringhe, ogni stringa riporta una riga letta nel file
	 */
	public ArrayList<String> getAll()
	{		
		/*if ( (fileName==null) || (!fileName.exists()) ) {
				return null;
			}
			*/	
		ArrayList<String> vettString = new ArrayList<String>();
		try
		{
			FileReader fr = new FileReader(fileName);
			BufferedReader inFile = new BufferedReader(fr);

			String str = inFile.readLine(); 
			
			while(str != null) 
			{
				vettString.add(str);
				str = inFile.readLine();
			}

			inFile.close();
			fr.close();
		}
		catch (FileNotFoundException exception){}
		catch (IOException exception){}

		return vettString;

	}
}



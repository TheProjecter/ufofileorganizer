package utils.file;

import java.io.File;
import java.io.IOException;

public abstract class FileUtils {
	/**
	 * Java non possiede un metodo per identificare un symlink.
	 * Questa classe restituisce true se una qualsiasi delle directory lungo il path del
	 * file passato è un symlink.
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static boolean isLinkPath( final File file ) throws IOException {
		if (file == null || !file.exists()) {
			return false;
		}

		return !file.getCanonicalFile().equals(file.getAbsoluteFile());
	}
	
	/**
	 * Java non possiede un metodo per identificare un symlink.
	 * Questa classe restituisce true se il file passato è un symlink.
	 * (Questa funzione è più lenta della precedente)
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static boolean isLink( final File file ) throws IOException {
		if (file == null || !file.exists()) {
			return false;
		}
		
		//Controllo se il file ha parent
		if (file.getParentFile() != null){
			File canonicalDir = file.getParentFile().getCanonicalFile();
			File fileInCanonicalDir = new File(canonicalDir, file.getName());
			return !fileInCanonicalDir.getCanonicalFile().equals(fileInCanonicalDir.getAbsoluteFile());
		}
		else {
			return isLinkPath(file);
		}
	}
}

package utils.file;

/**
 * Questa classe offre dei metodi per scandagliare una directory ed ottenere l'elenco dei file
 * e delle directory in essa presente. Può essere utilizzato in maniera ricorsiva
 * 
 * 
 * @author Francesco Cinà
 * @version 0.3
 */

import java.io.*;
import java.util.*;


public class DirectoryScanner implements DirectoryReader 
{
	private File currentFile = null;
	private File lastFile = null;
	private boolean lastRecursive = false;
	private ArrayList<File> fileList = new ArrayList<File>();
	private ArrayList<File> dirList = new ArrayList<File>();
	private ArrayList<File> fileAndDirList = new ArrayList<File>();

	DirectoryScanner(File file){
		this.currentFile = file;
	}
	
	public void setFile(File file){
		this.currentFile = file;
	}
	
	public File getFile(){
		return currentFile;
	}
	
	/**
	 * Questo metodo restituisce un vettore di File contenente tutti i file e le directory
	 * presenti sotto il suo path.
	 * Se l'oggetto passato non è una directory, restituisce null
	 * @param recursiveSearch se è true la ricerca viene effettuata ricursivamente in tutte le sottodirectory
	 * @param forceScan Se forceScan è true la scansione viene fisicamente effettuata ignorando eventuali dati in cache
	 * viene effettuata in ogni caso.
	 * @return
	 */
	public ArrayList<File> getFilesAndDirectories(boolean recursiveSearch, boolean forceScan)
	{
		scanAndUpdateAll(recursiveSearch, forceScan);
		return fileAndDirList;
	}
	
	
	/**
	 * Questo metodo restituisce un vettore contenente tutti e soli i file 
	 * presenti sotto il suo path (non vengono riportate le directory.)
	 * Se l'oggetto passato non è una directory, restituisce null
	 * @param recursiveSearch se è true la ricerca viene effettuata ricursivamente in tutte le sottodirectory
	 * @param forceScan Se forceScan è true la scansione viene fisicamente effettuata ignorando eventuali dati in cache
	 * @return
	 */
	public ArrayList<File> getFiles(boolean recursiveSearch, boolean forceScan)
	{
		scanAndUpdateAll(recursiveSearch, forceScan);
		return fileList;
	}
	
	
	/**
	 * Questo metodo restituisce un vettore contenente tutte e sole le directory 
	 * presenti sotto il suo path (non vengono riportati i file.)
	 * Se l'oggetto passato non è una directory, restituisce null
	 * @param recursiveSearch se è true la ricerca viene effettuata ricursivamente in tutte le sottodirectory
	 * @param forceScan Se forceScan è true la scansione viene fisicamente effettuata ignorando eventuali dati in cache
	 * @return
	 */
	public ArrayList<File> getDirectories(boolean recursiveSearch, boolean forceScan)
	{
		scanAndUpdateAll(recursiveSearch, forceScan);
		return dirList;
	}
	
	/**
	 * Questa classe è quella che di occupa di gestire la scansione vera e propria,
	 * una volta scansionato un path, vengono creati tre vettori contenenti solo i file, solo le directory,
	 * e sia i file che le directory, questi vettori vengono utilizzati dagli altri metodi.
	 * Se il path passato è uguale all'ultimo utilizzato e forceScan=false, la scansione non viene rieffettuata ma
	 * vengono utilizzati i dati già posseduti.
	 * 
	 */
	private void scanAndUpdateAll(boolean recursiveSearch, boolean forceScan)
	{		
		if ( (currentFile != null) && (lastFile != null) )
		{
			if ( (currentFile.equals(lastFile)) && (!forceScan) && (lastRecursive==recursiveSearch))
			{
				//System.out.println("test di uguaglianza superato, riscansione non effettuata.");
				return;				
			}
		}
		//System.out.println("test di uguaglianza fallito");
				
		if ( (currentFile!=null) && currentFile.exists() && currentFile.isDirectory() )
		{
			lastFile = currentFile;
			lastRecursive = recursiveSearch;
			fileAndDirList = new ArrayList<File>();
			fileList = new ArrayList<File>();
			dirList = new ArrayList<File>();
			
			goScanner(currentFile, recursiveSearch);
		}
		else
		{
			lastFile = null;
			fileAndDirList = new ArrayList<File>();
			fileList = new ArrayList<File>();
			dirList = new ArrayList<File>();
		}
		
		//decommentare qui per ottenere un output di test su file di testo
		//testOutputSuFile(new File("txtFile.txt"), new File("txtDirectory.txt"), new File("txtFileAndDirectory.txt") );
	}
	
	
	/**
	 * Motore di scansione della classe
	 * @param file
	 * @param recursiveSearch
	 */
	private void goScanner(File file, boolean recursiveSearch)
	{		
		
		ArrayList<File> localDirList = new ArrayList<File>();

		for(int i = 0; i < file.list().length; i++)
		{
			
			fileAndDirList.add(file.listFiles()[i]);
			
			if(file.listFiles()[i].isDirectory())
			{
				if (recursiveSearch) {
					localDirList.add(file.listFiles()[i]);
				}
				dirList.add(file.listFiles()[i]);
			}
			else
			{
				fileList.add(file.listFiles()[i]);
			}
		}
		
		if ( (localDirList.size()>0) && recursiveSearch)
		{
			for (int i=0; i<localDirList.size();i++)
			{
				goScanner((File)localDirList.get(i), true);
			}
		}
	}
	

	/**
	 * Questo metodo può essere utilizzato per testare se la scansione avviene correttamente.
	 * Manda in output su dei file di testo il risultato di una scansione.
	 *
	private static void testOutputSuFile(File txtFile, File txtDirectory, File txtFileAndDirectory)
	{
		ArrayList<String> listatoDirectory = new ArrayList<String>();
		ArrayList<String> listatoFile = new ArrayList<String>();
		ArrayList<String> listatoFileAndDirectory = new ArrayList<String>();

		if (dirList != null)
		{
			for (int i=0; i<dirList.size();i++)
			{
				listatoDirectory.add(dirList.get(i).getParent() + "\t" + dirList.get(i).getName() + "\t" + dirList.get(i).lastModified());
			}
			FileTxtWriteUtils.scrivi(txtDirectory, listatoDirectory, false);
		}
		else
		{
			FileTxtWriteUtils.scrivi(txtDirectory, "", false);
		}
		
		if (fileList != null)
		{
			for (int i=0; i<fileList.size();i++)
			{
				listatoFile.add(fileList.get(i).getParent() + "\t" + fileList.get(i).getName() + "\t" + fileList.get(i).length() + "\t" + fileList.get(i).lastModified());
			}
			FileTxtWriteUtils.scrivi(txtFile, listatoFile, false);
		}
		else
		{
			FileTxtWriteUtils.scrivi(txtFile, "", false);
		}
		
		if (fileAndDirList != null)
		{
			for (int i=0; i<fileAndDirList.size();i++)
			{
				listatoFileAndDirectory.add(fileAndDirList.get(i).getParent() + "\t" + fileAndDirList.get(i).getName() + "\t" + fileAndDirList.get(i).length() + "\t" + fileAndDirList.get(i).lastModified());
			}
			FileTxtWriteUtils.scrivi(txtFileAndDirectory, listatoFileAndDirectory, false);
		}
		else
		{
			FileTxtWriteUtils.scrivi(txtFileAndDirectory, "", false);
		}
	}
	*/
	

	
}

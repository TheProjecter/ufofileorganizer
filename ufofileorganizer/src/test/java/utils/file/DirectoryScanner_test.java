package utils.file;

import junit.framework.TestCase;
import java.io.File;

public class DirectoryScanner_test extends TestCase {

	DirectoryReader dirCurrent = null;
	DirectoryReader dirUpper = null;
	DirectoryReader dirInexistent = null;
	
	protected void setUp() throws Exception {
		super.setUp();
		dirCurrent = new DirectoryScanner(new File(System.getProperty("user.dir")));
		dirUpper = new DirectoryScanner(new File(System.getProperty("user.dir")).getParentFile() );
		dirInexistent = new DirectoryScanner( new File("inexistentFile"  + System.currentTimeMillis()) );
	}
	
	public void test_getFilesAndDirectories_currentDir1(){
				
		int fileAndDirNumberRecursive = dirCurrent.getFilesAndDirectories(true, true).size();
		int dirNumberRecursive = dirCurrent.getDirectories(true, true).size();
		int fileNumberRecursive = dirCurrent.getFiles(true, true).size();
		
		int fileAndDirNumber = dirCurrent.getFilesAndDirectories(false, true).size();
		int dirNumber = dirCurrent.getDirectories(false, true).size();
		int fileNumber = dirCurrent.getFiles(false, true).size();
		
		assertEquals(fileAndDirNumberRecursive, fileNumberRecursive + dirNumberRecursive);
		assertEquals(fileAndDirNumber, fileNumber + dirNumber);
	}
	
	public void test_getFilesAndDirectories_currentDir2(){
		
		int fileAndDirNumberRecursive = dirCurrent.getFilesAndDirectories(true, false).size();
		int dirNumberRecursive = dirCurrent.getDirectories(true, true).size();
		int fileNumberRecursive = dirCurrent.getFiles(true, false).size();
		
		int fileAndDirNumber = dirCurrent.getFilesAndDirectories(false, true).size();
		int dirNumber = dirCurrent.getDirectories(false, false).size();
		int fileNumber = dirCurrent.getFiles(false, true).size();
		
		assertEquals(fileAndDirNumberRecursive, fileNumberRecursive + dirNumberRecursive);
		assertEquals(fileAndDirNumber, fileNumber + dirNumber);
	}

	public void test_getFilesAndDirectories_inexistentDir1(){
		
		int fileAndDirNumberRecursive = dirInexistent.getFilesAndDirectories(true, true).size();
		int dirNumberRecursive = dirInexistent.getDirectories(true, true).size();
		int fileNumberRecursive = dirInexistent.getFiles(true, true).size();
		
		int fileAndDirNumber = dirInexistent.getFilesAndDirectories(false, true).size();
		int dirNumber = dirInexistent.getDirectories(false, true).size();
		int fileNumber = dirInexistent.getFiles(false, true).size();
		
		assertEquals(fileAndDirNumberRecursive, fileNumberRecursive + dirNumberRecursive);
		assertEquals(fileAndDirNumber, fileNumber + dirNumber);
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}

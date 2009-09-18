package utils.file;

import java.io.File;
import java.util.ArrayList;

import junit.framework.TestCase;

public class FileTxtReaderWriter_test extends TestCase {

	TextFileReader tfr = null;
	TextFileWriter tfw = null;
	File tempFile = null;
	File dontExist_tfr = null;
	File dontExist_tfw = null;
	
	protected void setUp() throws Exception {
		super.setUp();
		String fileName = "tempFile" + System.currentTimeMillis();
		tempFile = new File(fileName);
		tempFile.createNewFile();
		tfr = new FileTxtReader(tempFile);
		tfw = new FileTxtWriter(tempFile);
		
		dontExist_tfr = new File("dontExist_tfr" + System.currentTimeMillis());
		dontExist_tfw = new File("dontExist_tfw" + System.currentTimeMillis());
	}
	
	public void test_getAll1(){
		
		int writeLine = (int) (100 * Math.random());
		
		for (int i = 0; i < writeLine; i++){
			tfw.writeLine(new Integer(i).toString(),true);
		}
	
		assertEquals(tfr.getAll().size(),writeLine);
	}
	
	
	public void test_getAll2(){
		TextFileReader tfr_local = new FileTxtReader(dontExist_tfr);
		TextFileWriter tfw_local = new FileTxtWriter(dontExist_tfw);
		assertNotNull("",tfr_local.getAll());
		assertTrue(tfw_local.writeLine("adasd", true));
		assertTrue(dontExist_tfw.delete());
		assertTrue(tfw_local.writeLine("adasd", false));
		assertTrue(dontExist_tfw.delete());
		assertFalse(tfw_local.writeLines(null, true));
		assertTrue(dontExist_tfw.delete());
		assertFalse(tfw_local.writeLines(null, false));
		assertTrue(dontExist_tfw.delete());
		assertTrue(tfw_local.writeLines(new ArrayList<String>(), true));
		assertTrue(dontExist_tfw.delete());
		assertTrue(tfw_local.writeLines(new ArrayList<String>(), false));
		assertTrue(dontExist_tfw.delete());
	}

	
	protected void tearDown() throws Exception {
		super.tearDown();
		tempFile.delete();
		dontExist_tfr.delete();
		dontExist_tfw.delete();
	}

}

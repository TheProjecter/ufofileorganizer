package ufofilearchiver.core.commands;

import java.util.Date;

import orm.mapping.File;
import ufofilearchiver.core.BaseTest;
import ufofilearchiver.core.mock.RealFile_File_Mock;
import ufofilearchiver.core.services.EqualsClause;
import ufofilearchiver.core.services.WhereClausesList;

public class CreateDeleteFile_Test extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void test_CreateDeleteFile() throws Exception {
		
		Date timestamp = new Date();
		String aName = "File" + timestamp;
		java.io.File mockFile = new RealFile_File_Mock(aName, "c:\\winzozz");
		
		CreateFile fileCommand = new CreateFile(mockFile, _Directory1 , "creato da test");
		assertTrue(fileCommand.execute());
		File createdFile = (File) fileCommand.result();
		
		assertNotNull(createdFile);
		
		assertEquals(_Directory1.getLocation() ,createdFile.getLocation());
		
		
		WhereClausesList whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "id", createdFile.getId() ));		
		File estractedFile = (File) (_DBService.select(File.class, whereClausesArray).get(0));
		
		assertEquals(createdFile,estractedFile);
		
		DeleteFile deleteFileCommand = new DeleteFile(createdFile);
		assertTrue(deleteFileCommand.execute());
		//File createdFile = (File) deleteFileCommand.result();
		
		/*
		DeleteObject deleteObject = new DeleteObject(createdFile);
		deleteObject.execute();
		*/
		
		assertEquals(0, _DBService.select(File.class, whereClausesArray).size());
	}

}

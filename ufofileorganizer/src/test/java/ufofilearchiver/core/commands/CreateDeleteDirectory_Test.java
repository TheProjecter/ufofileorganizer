package ufofilearchiver.core.commands;

import java.math.BigDecimal;

import orm.mapping.Directory;
import ufofilearchiver.core.BaseTest;
import ufofilearchiver.core.mock.RealFile_Directory_Mock;
import ufofilearchiver.core.services.EqualsClause;
import ufofilearchiver.core.services.WhereClausesList;

public class CreateDeleteDirectory_Test extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void test_CreateDeleteDirectory() throws Exception {
		
		String aName = "Directory" + _Timestamp;
		java.io.File mockFile = new RealFile_Directory_Mock(aName, "c:\\winzozz");
		
		CreateDirectory directoryCommand = new CreateDirectory(mockFile, _Group3 , null, new BigDecimal(1000), "creato da test");
		directoryCommand.execute();
		Directory createdDirectory = (Directory) directoryCommand.result();
		
		assertNotNull(createdDirectory);
		
		WhereClausesList whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "name", aName ));		
		Directory estractedDirectory = (Directory) (_DBService.select(Directory.class, whereClausesArray).get(0));
		
		assertEquals(createdDirectory,estractedDirectory);
		
		assertEquals("c:/winzozz/",createdDirectory.getLocation());
		
		/*
		DeleteObject deleteObject = new DeleteObject(createdDirectory);
		deleteObject.execute();
		*/
		DeleteDirectory deleteDirectory = new DeleteDirectory(createdDirectory);
		assertTrue(deleteDirectory.execute());
		
		whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "name", aName ));
		assertEquals(0, _DBService.select(Directory.class, whereClausesArray).size());
	}

}

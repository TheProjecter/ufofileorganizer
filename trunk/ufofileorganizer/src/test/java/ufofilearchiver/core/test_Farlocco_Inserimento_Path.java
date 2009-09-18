package ufofilearchiver.core;

import java.io.File;

import orm.mapping.Container;
import orm.mapping.Group;
import ufofilearchiver.core.commands.ICommand;
import ufofilearchiver.core.commands.facade.DeleteGroupStructure;
import ufofilearchiver.core.commands.facade.InsertPath;
import ufofilearchiver.core.services.EqualsClause;
import ufofilearchiver.core.services.WhereClausesList;


public class test_Farlocco_Inserimento_Path extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void test_TestFAttoProprioMaProprioMale(){
		File aDirectoryPath = new java.io.File("/home/ufo/Documenti/informatica");
		String aGroupName = "questo NON deve essere presete perchè cancellato! " + _Timestamp;
		String aGroupDescription = "";
		boolean ignoreSymlink = true;
		
		WhereClausesList whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "id", new Integer(0) ));		
		Container aContainer = (Container) (_DBService.select(Container.class, whereClausesArray).get(0));
				
		ICommand insertPathCommand = new InsertPath(aDirectoryPath, aGroupName, aGroupDescription, ignoreSymlink , aContainer);
		insertPathCommand.execute();
		
		
		whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "name", aGroupName ));		
		Group aGroup = _DBService.select(Group.class, whereClausesArray).get(0);
	
		
		DeleteGroupStructure deleteGroupStructure = new DeleteGroupStructure(aGroup);
		assertTrue(deleteGroupStructure.execute());
		assertEquals(0, _DBService.select(Group.class, whereClausesArray).size());
	}

}

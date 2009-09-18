package ufofilearchiver.core.commands;

import java.math.BigDecimal;
import java.util.Date;

import orm.mapping.Group;
import ufofilearchiver.core.BaseTest;
import ufofilearchiver.core.services.EqualsClause;
import ufofilearchiver.core.services.WhereClausesList;

public class CreateDeleteGroup_Test extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void test_CreateDeleteGroup() throws Exception {
		
		Date timestamp = new Date();
		String aName = "Group" + timestamp;
				
		CreateGroup fileCommand = new CreateGroup(aName, _Container1 , "c:" , "creato da test", new BigDecimal(1111));
		assertTrue(fileCommand.execute());
		Group createdGroup = (Group) fileCommand.result();
		
		assertNotNull(createdGroup);
		
		assertEquals( aName ,createdGroup.getName());
		
		
		WhereClausesList whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "name", aName ));		
		Group estractedGroup = (Group) (_DBService.select(Group.class, whereClausesArray).get(0));
		
		assertEquals(createdGroup,estractedGroup);
		
		/*
		DeleteObject deleteObject = new DeleteObject(createdGroup);
		deleteObject.execute();
		*/
		DeleteGroup deleteGroupCommand = new DeleteGroup(createdGroup);
		assertTrue(deleteGroupCommand.execute());
		
		assertEquals(0, _DBService.select(Group.class, whereClausesArray).size());
	}

}


package ufofileorganizer.core.commands;


import orm.mapping.Group;

import ufofileorganizer.core.BaseTest;
import ufofileorganizer.core.commands.ICommand;
import ufofileorganizer.core.commands.facade.MoveGroup;
import ufofileorganizer.core.services.EqualsClause;
import ufofileorganizer.core.services.WhereClausesList;

public class MoveGroup_Test extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	public void test_MoveGroup1() throws Exception {
		assertEquals(_Group1.getContainer(), _Container1);
		
		ICommand moveGroupCommand = new MoveGroup(_Group1,_Container2);
		assertTrue(moveGroupCommand.execute());
		
		assertEquals(_Group1.getContainer(), _Container2);
		
		WhereClausesList whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "container", _Container2 ));		
		Group estractedGroup = (Group) (_DBService.select(Group.class, whereClausesArray).get(0));
		
		assertEquals(estractedGroup.getContainer(), _Container2);
	}
}

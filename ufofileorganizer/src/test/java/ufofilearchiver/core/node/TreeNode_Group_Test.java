package ufofilearchiver.core.node;

import orm.mapping.*;
import ufofilearchiver.core.BaseTest;
import ufofilearchiver.core.services.EqualsClause;
import ufofilearchiver.core.services.WhereClausesList;


public class TreeNode_Group_Test extends BaseTest {

	public void test_TestCriteria(){
	
		WhereClausesList whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "id", _Group1.getId() ));		
		assertNotNull((_DBService.select(Group.class, whereClausesArray).get(0)));
	}
	
	public void test_Children() throws Exception {

		WhereClausesList whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "id", _Group1.getId() ));
		
		Group groupTest = (Group) (_DBService.select(Group.class, whereClausesArray).get(0));
		//System.out.println("group children: " + groupTest.getDirectories().size());
		
		assertNotNull(groupTest);
		TreeNodeVector treeNodeGroup = TreeNodeFactory.getTreeNodeVector( groupTest);
		
		assertEquals(1,treeNodeGroup.getChildrenOfSameType().size());
		assertEquals(_Directory1.getId().intValue() ,treeNodeGroup.getID());
		assertEquals(2,treeNodeGroup.getAllChildren().size());
	}
	
	
	public void test_Children2() throws Exception {

		WhereClausesList whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "id", _Group2.getId() ));
		
		Group groupTest = (Group) (_DBService.select(Group.class, whereClausesArray).get(0));
		assertNotNull(groupTest);
		TreeNodeVector treeNodeGroup = TreeNodeFactory.getTreeNodeVector( groupTest);
		assertEquals(0,treeNodeGroup.getChildrenOfSameType().size());
		assertEquals(_Directory21.getId().intValue() ,treeNodeGroup.getID());
		assertEquals(1,treeNodeGroup.getAllChildren().size());
	}
	
	public void test_Children3() throws Exception {

		WhereClausesList whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "id", _Group2.getId() ));
		
		Group groupTest = (Group) (_DBService.select(Group.class, whereClausesArray).get(0));
		assertNotNull(groupTest);
		TreeNodeVector treeNodeGroup = TreeNodeFactory.getTreeNodeVector( groupTest);
		assertEquals(0,treeNodeGroup.getChildrenOfSameType().size());
		assertEquals(_Directory21.getId().intValue() ,treeNodeGroup.getID());
		assertEquals(1,treeNodeGroup.getAllChildren().size());
	}
	
}

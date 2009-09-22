package ufofileorganizer.core.node;


import orm.mapping.*;
import ufofileorganizer.core.BaseTest;
import ufofileorganizer.core.node.TreeNodeFactory;
import ufofileorganizer.core.node.TreeNodeVector;
import ufofileorganizer.core.services.EqualsClause;
import ufofileorganizer.core.services.WhereClausesList;


public class TreeNode_Directory_Test extends BaseTest {

	
	public void test_TestCriteria() throws Exception {
				
		WhereClausesList whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "id", _Directory1.getId() ));
		
		Directory directoryTest = (Directory) (_DBService.select(Directory.class, whereClausesArray).get(0));
		
		assertNotNull(directoryTest);
	}
	

	public void test_Children() throws Exception {
		WhereClausesList whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "id", _Directory1.getId() ));
		
		Directory directoryTest = (Directory) (_DBService.select(Directory.class, whereClausesArray).get(0));
		//System.out.println("_Directory1 id: " + (new BigDecimal(_Directory1.getId().longValue()) ) );
		//System.out.println("_Directory2 id: " + (new BigDecimal(_Directory2.getParentdir().longValue()) ) );

		
		TreeNodeVector treeNodeDirectory = TreeNodeFactory.getTreeNodeVector(directoryTest);
		
		assertEquals(1,treeNodeDirectory.getChildrenOfSameType().size());
		
		assertEquals(2,treeNodeDirectory.getAllChildren().size());
	}

}

package ufofilearchiver.core.node;

import orm.mapping.*;
import junit.framework.TestCase;

public class TreeNodeFactory_Test extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void test_01(){
		TreeNodeVector treeNodeTest = TreeNodeFactory.getTreeNodeVector( null);
		
		assertNotNull(treeNodeTest);
		assertTrue( treeNodeTest.getClass() == NullTreeNodeVector.class );
		
		treeNodeTest = TreeNodeFactory.getTreeNodeVector( new Container());
		assertNotNull(treeNodeTest);
		assertTrue( treeNodeTest.getClass() == TreeNode_Container.class );
		
		treeNodeTest = TreeNodeFactory.getTreeNodeVector( new Group());
		assertNotNull(treeNodeTest);
		assertTrue( treeNodeTest.getClass() == TreeNode_Group.class );
		
		treeNodeTest = TreeNodeFactory.getTreeNodeVector( new Directory());
		assertNotNull(treeNodeTest);
		assertTrue( treeNodeTest.getClass() == TreeNode_Directory.class );
		
		treeNodeTest = TreeNodeFactory.getTreeNodeVector( new File());
		assertNotNull(treeNodeTest);
		assertTrue( treeNodeTest.getClass() == TreeNode_File.class );
		
	}

}

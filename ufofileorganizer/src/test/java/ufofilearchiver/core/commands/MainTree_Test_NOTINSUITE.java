package ufofilearchiver.core.commands;

import ufofilearchiver.core.BaseTest;
import ufofilearchiver.core.commands.facade.MainTree;
import ufofilearchiver.core.node.TreeNodeVector;

public class MainTree_Test_NOTINSUITE extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void test_MainTree() throws Exception {
		ICommand mainTreeBuilder = new MainTree();
		assertTrue(mainTreeBuilder.execute());
		
		TreeNodeVector resultTreeNode = (TreeNodeVector) mainTreeBuilder.result();
		
		assertTrue(resultTreeNode.size()>0);
		
	}

}

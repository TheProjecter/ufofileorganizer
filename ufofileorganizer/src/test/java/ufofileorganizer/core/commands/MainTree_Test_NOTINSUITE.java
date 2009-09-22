package ufofileorganizer.core.commands;

import ufofileorganizer.core.BaseTest;
import ufofileorganizer.core.commands.ICommand;
import ufofileorganizer.core.commands.facade.MainTree;
import ufofileorganizer.core.node.TreeNodeVector;

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

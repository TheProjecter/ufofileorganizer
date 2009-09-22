package ufofileorganizer.core.commands;

import ufofileorganizer.core.BaseTest;
import ufofileorganizer.core.commands.ICommand;
import ufofileorganizer.core.commands.facade.Find;
import ufofileorganizer.core.node.TreeNodeVector;

public class Find_Test extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void test_find1() throws Exception {
		ICommand finder = new Find("%" + _Timestamp + "%");
		assertTrue(finder.execute());
		TreeNodeVector treeNodeResult = (TreeNodeVector) finder.result();
		
		assertEquals(3, treeNodeResult.size());
	}

}

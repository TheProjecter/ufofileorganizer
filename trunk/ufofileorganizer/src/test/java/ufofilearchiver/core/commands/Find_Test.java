package ufofilearchiver.core.commands;

import ufofilearchiver.core.BaseTest;
import ufofilearchiver.core.commands.facade.Find;
import ufofilearchiver.core.node.TreeNodeVector;

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

package ufofileorganizer.core;

import ufofileorganizer.core.commands.*;
import ufofileorganizer.core.node.*;
import ufofileorganizer.core.services.DBService_Test;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for ufofilearchiver.core");
		//$JUnit-BEGIN$
		suite.addTestSuite(BaseTest.class);
		suite.addTestSuite(TreeNode_Directory_Test.class);
		suite.addTestSuite(TreeNode_Group_Test.class);
		suite.addTestSuite(TreeNodeFactory_Test.class);
		suite.addTestSuite(DBService_Test.class);
		suite.addTestSuite(TreeNodeAllChildrenCommand_Test.class);
		suite.addTestSuite(CreateDeleteContainer_Test.class);
		suite.addTestSuite(CreateDeleteDirectory_Test.class);
		suite.addTestSuite(CreateDeleteFile_Test.class);
		suite.addTestSuite(CreateDeleteGroup_Test.class);
		suite.addTestSuite(MoveGroup_Test.class);
		suite.addTestSuite(Find_Test.class);
		//$JUnit-END$

		return suite;
	}

}

package ufofileorganizer.core.commands;

import orm.mapping.Container;
import ufofileorganizer.core.ApplicationBuilder;
import ufofileorganizer.core.Constants;
import ufofileorganizer.core.commands.facade.TreeNodeAllChildren;
import ufofileorganizer.core.node.TreeNodeFactory;
import ufofileorganizer.core.node.TreeNodeVector;
import ufofileorganizer.core.services.EqualsClause;
import ufofileorganizer.core.services.IDBService;
import ufofileorganizer.core.services.WhereClausesList;
import junit.framework.TestCase;

public class TreeNodeAllChildrenCommand_Test extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testChildren(){
		WhereClausesList whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "id", new Integer(0) ));

		Container root = (Container) ( (IDBService) ApplicationBuilder.getApplicationBuilder().getServiceProvider().getService(Constants.DATABASE) ).select(orm.mapping.Container.class, whereClausesArray).get(0);
		
		TreeNodeVector rootNode = TreeNodeFactory.getTreeNodeVector(root);
		TreeNodeAllChildren aTreeNodeAllChildrenCommand = new TreeNodeAllChildren(rootNode);
		aTreeNodeAllChildrenCommand.execute();
		assertEquals(rootNode.getAllChildren().size(), ( (TreeNodeVector) aTreeNodeAllChildrenCommand.result() ).size());
	}

}

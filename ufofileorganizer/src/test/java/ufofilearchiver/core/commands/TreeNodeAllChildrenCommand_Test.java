package ufofilearchiver.core.commands;

import orm.mapping.Container;
import ufofilearchiver.core.ApplicationBuilder;
import ufofilearchiver.core.Constants;
import ufofilearchiver.core.commands.facade.TreeNodeAllChildren;
import ufofilearchiver.core.node.TreeNodeFactory;
import ufofilearchiver.core.node.TreeNodeVector;
import ufofilearchiver.core.services.EqualsClause;
import ufofilearchiver.core.services.IDBService;
import ufofilearchiver.core.services.WhereClausesList;
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

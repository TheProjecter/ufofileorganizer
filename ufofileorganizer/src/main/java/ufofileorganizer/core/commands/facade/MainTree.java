package ufofileorganizer.core.commands.facade;


import java.util.Collections;
import java.util.List;

import orm.mapping.Container;
import ufofileorganizer.core.commands.ACommand;
import ufofileorganizer.core.node.*;
import ufofileorganizer.core.services.EqualsClause;
import ufofileorganizer.core.services.WhereClausesList;


public class MainTree extends ACommand {

	
	private TreeNodeVector _TreeNode = new NullTreeNodeVector();
	
	public boolean execute() {
		_TreeNode = new NullTreeNodeVector();

		WhereClausesList whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "id", new Integer(0) ));
		Container root = (Container) _DBService.select(orm.mapping.Container.class, whereClausesArray).get(0);
		
		_TreeNode = TreeNodeFactory.getTreeNodeVector(root);
		insertContainers( (TreeNode_Container) _TreeNode);
		
		return true;
	}

	public Object result() {
		return _TreeNode;
	}

	
	private void insertContainers(TreeNode_Container containers){
				
		List<TreeNodeVector> containerChildren = containers.getChildrenOfSameType();
		Collections.sort( containerChildren, new TreeNodeComparator());
		
		List<TreeNodeVector> groupChildren = containers.getChildrenOfSubType();
		Collections.sort( groupChildren, new TreeNodeComparator());
		
		containers.addAll(containerChildren);
		containers.addAll(groupChildren);
		
		
		for (int i = 0; i < containerChildren.size(); i++) {
			insertContainers( (TreeNode_Container) containerChildren.get(i));
		}
		for (int i = 0; i < groupChildren.size(); i++) {
			insertDirectoryChildrenRecursively( groupChildren.get(i));
		}
		
	}
	
	
	private void insertDirectoryChildrenRecursively( TreeNodeVector treeNodeDirectory) {
		TreeNodeVector treeNodeVectorChildren =  treeNodeDirectory.getChildrenOfSameType();
		if (treeNodeVectorChildren != null
				&& treeNodeVectorChildren.size() > 0) {
			treeNodeDirectory.addAll(treeNodeVectorChildren);
			Collections.sort( treeNodeDirectory, new TreeNodeComparator());

			for (int i = 0; i < treeNodeDirectory.size(); i++) {
				TreeNodeVector treeNodeNew = (TreeNodeVector) treeNodeDirectory
						.get(i);

				insertDirectoryChildrenRecursively(treeNodeNew);
			}
		}
	}

}

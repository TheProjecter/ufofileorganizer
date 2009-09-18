package ufofilearchiver.core.commands.facade;

import ufofilearchiver.core.commands.ICommand;
import ufofilearchiver.core.node.NullTreeNodeVector;
import ufofilearchiver.core.node.TreeNodeVector;

public class TreeNodeAllChildren implements ICommand {

	private TreeNodeVector _TreeNode;
	private TreeNodeVector _TreeNodeResult = new NullTreeNodeVector();

	public TreeNodeAllChildren(TreeNodeVector aTreeNodeVector){
		_TreeNode = aTreeNodeVector;
	}
	
	public boolean execute() {
		_TreeNodeResult =_TreeNode.getAllChildren();
		return true;

	}

	public Object result() {
		return _TreeNodeResult;
	}

}

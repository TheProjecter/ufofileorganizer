package ufofilearchiver.core.node;

import orm.mapping.*;

public abstract class TreeNodeFactory {
	
	public static TreeNodeVector getTreeNodeVector( Object object){
		if (object == null) {
			return new NullTreeNodeVector();
		}
		if (object.getClass() == Container.class){
			return new TreeNode_Container( (Container) object);
		}
		if (object.getClass() == Group.class){
			return new TreeNode_Group( (Group) object);
		}
		if (object.getClass() == Directory.class){
			return new TreeNode_Directory( (Directory) object);
		}
		if (object.getClass() == File.class){
			return new TreeNode_File( (File) object);
		}		
		return new NullTreeNodeVector();
	}
	
}

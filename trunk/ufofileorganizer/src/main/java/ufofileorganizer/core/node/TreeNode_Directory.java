package ufofileorganizer.core.node;

import java.math.BigDecimal;
import java.util.Iterator;

import orm.mapping.Directory;


public class TreeNode_Directory extends TreeNodeVector {


	
	private static final long serialVersionUID = 1L;
	private Directory _directory = null;
	
	public Directory get_directory() {
		return _directory;
	}

	TreeNode_Directory(Directory directory) {
		this._directory = directory;
	}

	public TreeNodeVector getAllChildren() {
		TreeNodeVector childrenList = getChildrenOfSameType();
		childrenList.addAll(getChildrenOfSubType());
		return childrenList;
	}

	public TreeNodeVector getChildrenOfSameType() {
		TreeNodeVector childrenList = TreeNodeFactory.getTreeNodeVector( _directory);
		
		Iterator<Directory> it = _directory.getDirectories().iterator();
		
		while( it.hasNext() ){
			Directory directory_child = (Directory) it.next();
			childrenList.add( TreeNodeFactory.getTreeNodeVector( directory_child) );
		}
		
		return childrenList;
	}

	
	public TreeNodeVector getChildrenOfSubType() {
		TreeNodeVector childrenList = TreeNodeFactory.getTreeNodeVector( _directory);		
		
		Iterator<orm.mapping.File> it = _directory.getFiles().iterator();
		
		while( it.hasNext() ){
			orm.mapping.File directory_child = it.next();
			childrenList.add( TreeNodeFactory.getTreeNodeVector( directory_child) );
		}

		return childrenList;
	}
	
	
	public String getDescription() {
		return _directory.getDescription();
	}

	public int getID() {
		return _directory.getId();
	}

	public String getLocation() {
		return _directory.getLocation();
	}

	public String getName() {
		return _directory.getName();
	}

	public Long getSize() {
		return new Long( _directory.getSize().longValue() ) ;
	}

	public String getType() {
		return _directory.getType().getName();
	}

	public Directory getDirectory() {
		return _directory;
	}
	
	public BigDecimal getModified() {		
		return _directory.getModified();
	}

	public String getParentGroupName() {
		return "Disk: " + _directory.getGroup().getName();
	}
	
	@Override
	public String getParentContainerName() {
		return _directory.getGroup().getContainer().getName();
	}
}

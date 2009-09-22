package ufofileorganizer.core.node;

import java.math.BigDecimal;
import java.util.Iterator;

import orm.mapping.*;

public class TreeNode_Group extends TreeNodeVector {

	private static final long serialVersionUID = 1L;
	private Group _group = null;
	
	private Directory _directory = new NullDirectory();
	
	TreeNode_Group( Group group) {
		super();
		this._group = group;
		
		Iterator<Directory> it = _group.getDirectories().iterator();
		while (it.hasNext()) {
			Directory tempDirectory = it.next();
			if ( tempDirectory.getLocation().equals(_group.getLocation())  ){
				_directory = tempDirectory;
				break;
			}
		}
		

	}
	
	public TreeNodeVector getAllChildren() {
		TreeNodeVector childrenList = getChildrenOfSameType();
		childrenList.addAll(getChildrenOfSubType());
		
		return childrenList;
		
	}

	public TreeNodeVector getChildrenOfSameType() {
		TreeNodeVector childrenList = TreeNodeFactory.getTreeNodeVector( _group);
		childrenList.addAll( TreeNodeFactory.getTreeNodeVector( _directory).getChildrenOfSameType() );

		return childrenList;
	}
	
	public TreeNodeVector getChildrenOfSubType() {
		TreeNodeVector childrenList = TreeNodeFactory.getTreeNodeVector( _group);
	
		Iterator<orm.mapping.File> it = _directory.getFiles().iterator();
		
		while( it.hasNext() ){
			orm.mapping.File directory_child = it.next();
			childrenList.add( TreeNodeFactory.getTreeNodeVector( directory_child) );
		}
		
		return childrenList;
		
	}

	public String getDescription() {
		return _group.getDescription();
	}

	public int getID() {
		return _directory.getId();
	}

	public String getLocation() {
		return _group.getLocation();
	}

	public String getName() {
		return _group.getName();
	}

	public Long getSize() {
		return new Long( _group.getSize().longValue() );
	}

	public String getType() {
		return _group.getType().getName();
	}

	public BigDecimal getModified() {		
		return _directory.getModified();
	}

	public String getParentGroupName() {
		return "Disk: " + _group.getName();
	}
	
	@Override
	public String getParentContainerName() {
		return _group.getContainer().getName();
	}
	
	public Group get_group() {
		return _group;
	}
	
}

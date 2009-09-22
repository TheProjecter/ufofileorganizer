package ufofileorganizer.core.node;

import java.math.BigDecimal;

import orm.mapping.File;

public class TreeNode_File extends TreeNodeVector {

	private static final long serialVersionUID = 1L;
	private File _file;
	
	public File getFile() {
		return _file;
	}

	TreeNode_File( File file) {
		this._file = file;
	}

	public TreeNodeVector getAllChildren() {
		return new NullTreeNodeVector();
	}

	public TreeNodeVector getChildrenOfSameType() {
		return new NullTreeNodeVector();
	}
	
	public TreeNodeVector getChildrenOfSubType() {
		return new NullTreeNodeVector();
	}

	public String getDescription() {
		return _file.getDescription();
	}

	public int getID() {
		return 0;
	}

	public String getLocation() {
		return _file.getDirectory().getLocation();
	}

	public String getName() {
		return _file.getName();
	}

	public Long getSize() {
		return new Long( _file.getSize().longValue() );
	}

	public String getType() {
		return _file.getType().getName();
	}
	
	public BigDecimal getModified() {		
		return _file.getModified();
	}

	public String getParentGroupName() {
		return "Disk: " + _file.getGroup().getName();
	}
	
	@Override
	public String getParentContainerName() {
		return _file.getGroup().getContainer().getName();
	}
	
}

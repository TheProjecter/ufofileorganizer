package ufofilearchiver.core.node;

import java.math.BigDecimal;

public class NullTreeNodeVector extends TreeNodeVector {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		return "";
	}

	public int getID() {
		return 0;
	}

	public String getLocation() {
		return "";
	}

	public BigDecimal getModified() {
		return new BigDecimal(0);
	}

	public String getName() {
		return "";
	}

	public String getParentGroupName() {
		return "";
	}

	public Long getSize() {
		return new Long(0);
	}

	public String getType() {
		return "";
	}

	@Override
	public String getParentContainerName() {
		return "";
	}

}

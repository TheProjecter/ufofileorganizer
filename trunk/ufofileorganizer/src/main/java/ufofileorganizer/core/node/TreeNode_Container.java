package ufofileorganizer.core.node;

import java.math.BigDecimal;
import java.util.Iterator;

import orm.mapping.*;


public class TreeNode_Container extends TreeNodeVector {

	private static final long serialVersionUID = 1L;
	private Container _container = null;
	

	TreeNode_Container(Container container) {
		this._container = container;
	}
	
	public TreeNodeVector getAllChildren() {
		TreeNodeVector childrenList = getChildrenOfSameType();
		childrenList.addAll(getChildrenOfSubType());
		return childrenList;
	}

	public TreeNodeVector getChildrenOfSameType() {
		TreeNodeVector childrenList = TreeNodeFactory.getTreeNodeVector( _container);
		
		Iterator<Container> it = _container.getContainers().iterator();
		
		while( it.hasNext() ){
			Container container_child = it.next();
			childrenList.add( TreeNodeFactory.getTreeNodeVector( container_child) );
			
		}
		return childrenList;
	}
	
	
	public TreeNodeVector getChildrenOfSubType() {
		TreeNodeVector childrenList = TreeNodeFactory.getTreeNodeVector( _container);
		
		Iterator<Group> it = _container.getGroups().iterator();
		
		while( it.hasNext() ){
			Group group_child = it.next();
			childrenList.add( TreeNodeFactory.getTreeNodeVector( group_child) );
			
		}
		return childrenList;
	}
	

	public int getID() {
		return _container.getId();
	}

	public String getLocation() {
		return "";
	}

	public String getName() {
		return _container.getName();
	}

	public String getDescription() {
		return _container.getDescription();
	}
	
	public Long getSize() {
		return new Long(0);
	}

	public String getType() {
		return CONTAINER;
	}
	
	public BigDecimal getModified() {		
		return new BigDecimal(0);
	}

	public String getParentGroupName() {
		return "Container: " + _container.getName();
	}
	
	public Container get_container() {
		return _container;
	}

	@Override
	public String getParentContainerName() {
		return "";
	}
}

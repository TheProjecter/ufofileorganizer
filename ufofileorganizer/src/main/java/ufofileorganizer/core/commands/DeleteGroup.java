package ufofileorganizer.core.commands;

import orm.mapping.Container;
import orm.mapping.Group;

public class DeleteGroup extends ACommand{

	private Group _group;
	private boolean _deleted = false;
	
	public DeleteGroup(Group aGroup) {
		_group = aGroup;
	}

	@Override
	public boolean execute() {
		
		Container parentContainer = _group.getContainer();
		
		_deleted = _DBService.delete(_group);
		
		if (_deleted) {
			if ((parentContainer != null) && (parentContainer.getGroups() != null)){ parentContainer.getGroups().remove(_group); }
		}
		
		return _deleted;
	}

	@Override
	public Object result() {
		return new Boolean(_deleted);
	}
}

package ufofileorganizer.core.commands.facade;

import orm.mapping.Container;
import orm.mapping.Group;
import ufofileorganizer.core.commands.ACommand;

public class MoveGroup extends ACommand {

	
	private Group _Group;
	private Container _Container;

	public MoveGroup(Group aGroup, Container aContainer) {
		_Group = aGroup;
		_Container = aContainer;
	}
	
	public boolean execute() {
		Container oldContainer = _Group.getContainer();
		oldContainer.getGroups().remove(_Group);
		
		_Group.setContainer(_Container);
		_Container.getGroups().add(_Group);
		
		boolean ok = _DBService.update(_Group);
		if (ok) {
			updateSystem();
		}
		return ok;
	}

	public Object result() {
		return _Group;
	}

}

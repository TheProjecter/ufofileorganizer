package ufofileorganizer.core.commands.facade;

import orm.mapping.Group;
import ufofileorganizer.core.commands.ACommand;
import ufofileorganizer.core.commands.DeleteGroup;

public class DeleteGroupStructure extends ACommand{

	private Group _group;
	private boolean _deleted = false;
	private boolean _update = true;
	
	public DeleteGroupStructure(Group aGroup){
		this(aGroup, true);
	}
	
	public DeleteGroupStructure(Group aGroup, boolean update){
		_group = aGroup;
		_update = update;
	}

	@Override
	public synchronized boolean execute() {
		String hql = "delete from File dir where IDGROUP = " + _group.getId();
		_DBService.executeQuery(hql);
		hql = "delete from Directory dir where IDGROUP = " + _group.getId();
		_DBService.executeQuery(hql);
		
		_deleted = new DeleteGroup(_group).execute();
		if (_deleted && _update) {
			updateSystem();
		}
		return _deleted;
	}

	@Override
	public Object result() {
		return new Boolean(_deleted);
	}
}

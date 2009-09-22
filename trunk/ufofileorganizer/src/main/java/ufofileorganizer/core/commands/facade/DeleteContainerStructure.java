package ufofileorganizer.core.commands.facade;

import orm.mapping.Container;
import orm.mapping.Group;
import ufofileorganizer.core.commands.ACommand;
import ufofileorganizer.core.commands.DeleteContainer;

public class DeleteContainerStructure extends ACommand{

	private Container _container;
	private boolean _deleted = false;


	public DeleteContainerStructure(Container aContainer){
		_container = aContainer;
	}

	@Override
	public  boolean execute() {
		
		Object[] groups = _container.getGroups().toArray();
		
		for(int i = 0 ; i < groups.length ; i++){
				new DeleteGroupStructure( (Group)groups[i],false).execute();
				new Save().execute();
		}
		
		
		_deleted = new DeleteContainer(_container).execute();
		
		if (_deleted) {
			updateSystem();
		}
		
		return _deleted;
	}

	@Override
	public Object result() {
		return new Boolean(_deleted);
	}
	
	
	
}

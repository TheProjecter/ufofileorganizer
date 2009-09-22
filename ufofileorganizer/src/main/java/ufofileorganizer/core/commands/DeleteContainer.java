package ufofileorganizer.core.commands;

import orm.mapping.Container;

public class DeleteContainer extends ACommand{

	private Container _container;
	private boolean _deleted = false;
	
	public DeleteContainer(Container aContainer) {
		_container = aContainer;
	}

	@Override
	public boolean execute() {
		
		Container parentContainer = _container.getContainer();
		
		_deleted = _DBService.delete(_container);
		
		if (_deleted) {
			if ((parentContainer != null) && (parentContainer.getContainers() != null)){ parentContainer.getContainers().remove(_container); }
		}
		
		return _deleted;
	}

	@Override
	public Object result() {
		return new Boolean(_deleted);
	}
}

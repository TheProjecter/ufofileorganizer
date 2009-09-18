package ufofilearchiver.core.commands.facade;

import ufofilearchiver.core.commands.ACommand;

public class Save extends ACommand {
	
	private boolean _saved;

	public boolean execute() {
		_saved = _DBService.commit();
		return _saved;
	}

	public Object result() {
		return _saved;
	}
	
}

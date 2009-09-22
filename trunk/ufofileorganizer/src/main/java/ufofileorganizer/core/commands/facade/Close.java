package ufofileorganizer.core.commands.facade;

import ufofileorganizer.core.commands.ACommand;

public class Close extends ACommand {
	
	private boolean _closed;

	public boolean execute() {
		_closed = _DBService.close();
		return _closed;
	}

	public Object result() {
		return _closed;
	}
	
}

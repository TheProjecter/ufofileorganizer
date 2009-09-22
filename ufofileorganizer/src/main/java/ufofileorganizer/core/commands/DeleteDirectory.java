package ufofileorganizer.core.commands;

import orm.mapping.Directory;
import orm.mapping.Group;

public class DeleteDirectory extends ACommand{

	private Directory _directory;
	private boolean _deleted = false;
	
	public DeleteDirectory(Directory aDirectory) {
		_directory = aDirectory;
	}

	@Override
	public boolean execute() {
		
		Directory parentDir = _directory.getDirectory();
		Group parentGroup = _directory.getGroup();
		
		_deleted = _DBService.delete(_directory);
		
		if (_deleted) {
			if ((parentDir != null) && (parentDir.getDirectories() != null)){ parentDir.getDirectories().remove(_directory); }
			if (parentGroup.getDirectories() != null){ parentGroup.getDirectories().remove(_directory); }
		}
		
		return _deleted;
	}

	@Override
	public Object result() {
		return new Boolean(_deleted);
	}

}

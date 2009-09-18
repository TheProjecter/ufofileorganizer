package ufofilearchiver.core.commands;

import orm.mapping.Directory;
import orm.mapping.File;
import orm.mapping.Group;

public class DeleteFile extends ACommand{

	private File _file;
	private boolean _deleted = false;
	
	public DeleteFile(File aFile) {
		_file = aFile;
	}

	@Override
	public boolean execute() {
		
		Directory parentDir = _file.getDirectory();
		Group parentGroup = _file.getGroup();
		
		_deleted = _DBService.delete(_file);
		
		if (_deleted) {
			if ((parentDir != null) && (parentDir.getFiles() != null)){ parentDir.getFiles().remove(_file); }
			if (parentGroup.getDirectories() != null) { parentGroup.getFiles().remove(_file); }
		}
		
		return _deleted;
	}

	@Override
	public Object result() {
		return new Boolean(_deleted);
	}

}

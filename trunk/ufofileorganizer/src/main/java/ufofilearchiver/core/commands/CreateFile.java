package ufofilearchiver.core.commands;

import java.math.BigDecimal;

import orm.mapping.Directory;
import orm.mapping.File;
import orm.mapping.Type;

public class CreateFile extends ACommand {

	private java.io.File _RealFile;
	private Directory _Directory;
	private String _Description;
	private File _File;

	public CreateFile(java.io.File aFile, Directory aDirectory, String aDescription) {
		_RealFile = aFile;
		_Directory = aDirectory;
		_Description = aDescription;
	}

	public boolean execute() {
		if ( !_RealFile.isFile() ){
			return false;
		}
		_File = new File();
		_File.setName(_RealFile.getName());
		_File.setHidden(new Boolean(_RealFile.isHidden()));
		_File.setLocation(_Directory.getLocation());
		_File.setModified(new BigDecimal(_RealFile.lastModified() ));
		_File.setSize( new BigDecimal( _RealFile.length()) );
		_File.setGroup(_Directory.getGroup());
		_File.setType(new Type("File"));
		_File.setDescription(_Description);
		_File.setDirectory(_Directory);
		_Directory.getFiles().add(_File);
		
		
		return _DBService.insert(_File);
		
	}

	public Object result() {
		return _File;
	}

}

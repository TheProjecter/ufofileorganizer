package ufofileorganizer.core.commands;

import java.math.BigDecimal;

import orm.mapping.Directory;
import orm.mapping.Group;
import orm.mapping.Type;
import utils.string.*;

public class CreateDirectory extends ACommand {
	
	java.io.File _RealFile;
	orm.mapping.Directory _Directory;
	orm.mapping.Group _Group;
	private String _Description;
	private BigDecimal _Size;
	
	private SQLStringOperator _PathStringOperator = new SqlString_AppendiPathSeparatorAllaFine(new SqlString_CambiaPathSeparator());
	private orm.mapping.Directory _ParentDir;
	
	public CreateDirectory(java.io.File aRealFile, Group aGroup, Directory parentDir, BigDecimal size, String aDescription){
		_Group = aGroup;
		_RealFile = aRealFile;
		_Size = size;
		_Description = aDescription;
		_ParentDir = parentDir;
	}
	
	public boolean execute() {
		if ( !_RealFile.isDirectory() ){
			return false;
		}
		_Directory = new Directory();
		_Directory.setName(_RealFile.getName());
		_Directory.setModified(new BigDecimal(_RealFile.lastModified() ));
		_Directory.setHidden(new Boolean(_RealFile.isHidden()));
		_Directory.setType(new Type("Directory"));
		_Directory.setLocation( _PathStringOperator.operate( _RealFile.getPath() ) );
		_Directory.setSize( _Size );
		_Directory.setGroup(_Group);
		_Group.getDirectories().add(_Directory);
		
		_Directory.setDescription(_Description);
		
		if (_ParentDir != null) {
			_Directory.setDirectory(_ParentDir);
			_ParentDir.getDirectories().add(_Directory);
		}	
		
		
		return _DBService.insert(_Directory);
	}

	public Object result() {
		return _Directory;
	}

}

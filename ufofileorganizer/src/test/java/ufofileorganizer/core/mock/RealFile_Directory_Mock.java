package ufofileorganizer.core.mock;

import java.io.File;
import java.util.Date;


public class RealFile_Directory_Mock extends File{

	private static final long serialVersionUID = 1L;
	private String _Name;
	private String _Path;
	
	public RealFile_Directory_Mock(String aName, String aParent) {
		super(aName);
		_Name = aName;
		_Path = aParent;
	}

	
	public boolean isDirectory() {
		return true;
	}
	
	public String getName(){
		return _Name;
	}
	
	public long lastModified(){
		return (new Date() ).getTime();
	}
	
	public String getPath(){
		return _Path;
	}

}

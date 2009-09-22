package ufofileorganizer.core.mock;

import java.io.File;
import java.util.Date;

public class RealFile_File_Mock extends File{

	private static final long serialVersionUID = 1L;
	private String _Name;
	private String _Parent;
	
	public RealFile_File_Mock(String aName, String aParent) {
		super(aName);
		_Name = aName;
		_Parent = aParent;
	}

	public boolean isFile(){
		return true;
	}
	
	public boolean isDirectory() {
		return false;
	}
	
	public String getName(){
		return _Name;
	}
	
	public long lastModified(){
		return (new Date() ).getTime();
	}
	
	public String getParent(){
		return _Parent;
	}
}

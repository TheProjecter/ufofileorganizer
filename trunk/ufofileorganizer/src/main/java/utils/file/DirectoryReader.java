package utils.file;

import java.io.File;
import java.util.ArrayList;

public interface DirectoryReader {
	void setFile(File file);
	File getFile();
	public ArrayList<File> getFilesAndDirectories(boolean recursiveSearch, boolean forceScan);
	public ArrayList<File> getFiles(boolean recursiveSearch, boolean forceScan);
	public ArrayList<File> getDirectories(boolean recursiveSearch, boolean forceScan);
}

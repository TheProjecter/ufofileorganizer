package ufofileorganizer.core.commands.facade;

import java.io.IOException;
import java.math.BigDecimal;

import orm.mapping.*;
import ufofileorganizer.core.ApplicationBuilder;
import ufofileorganizer.core.Constants;
import ufofileorganizer.core.commands.ACommand;
import ufofileorganizer.core.commands.CreateDirectory;
import ufofileorganizer.core.commands.CreateFile;
import ufofileorganizer.core.commands.CreateGroup;
import ufofileorganizer.core.services.EqualsClause;
import ufofileorganizer.core.services.IDBService;
import ufofileorganizer.core.services.WhereClausesList;
import utils.file.FileUtils;

public class InsertPath extends ACommand {

	private Group _CreatedGroup;
	private Container _Container;
	private String _GroupDescription;
	private String _GroupName;
	private java.io.File _DirectoryPath;
	private boolean _IgnoreSymlink;

	public InsertPath(java.io.File aDirectoryPath, String aGroupName, String aGroupDescription, boolean ignoreSymlink){
		this (aDirectoryPath, aGroupName, aGroupDescription, ignoreSymlink, getRoot());
	}
	
	public InsertPath(java.io.File aDirectoryPath, String aGroupName, String aGroupDescription, boolean ignoreSymlink , Container aContainer){
		_DirectoryPath = aDirectoryPath;
		_GroupName = aGroupName;
		_GroupDescription = aGroupDescription;
		_Container = aContainer;
		_IgnoreSymlink = ignoreSymlink;
	}
	
	public boolean execute() {
		CreateGroup createGroup = new CreateGroup( _GroupName, _Container, _DirectoryPath.getPath(), _GroupDescription, new BigDecimal(0) );
		createGroup.execute();
		_CreatedGroup = (Group) createGroup.result();
		
		insertDataLauncher(_DirectoryPath, null );
		
		WhereClausesList whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause("group", _CreatedGroup));
		whereClausesArray.add(new EqualsClause( "location", _CreatedGroup.getLocation()));

		Directory directory = (Directory) _DBService.select(orm.mapping.Directory.class, whereClausesArray).get(0);
		_CreatedGroup.setSize(directory.getSize());
		
		updateSystem();
		
		return true;
	}

	public Object result() {
		return _CreatedGroup;
	}
	
	
	private BigDecimal insertDataLauncher(java.io.File realFile, Directory parentDir) {
	
		if (realFile == null || !realFile.exists() || !realFile.isDirectory()) {
			return new BigDecimal(0);
		}
	
		BigDecimal dirSize = new BigDecimal(0);
		
		Directory directory = insertDir(realFile, parentDir, dirSize);
		
		try {
			if (!(FileUtils.isLink(realFile) && _IgnoreSymlink)) {

				for (int i = 0; i < realFile.list().length; i++) {
					if (realFile.listFiles()[i].isDirectory()) {
						dirSize = dirSize.add( insertDataLauncher(realFile.listFiles()[i], directory ) );
					} else {
						dirSize = dirSize.add( insertFile(realFile.listFiles()[i], directory) );

					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		directory.setSize(dirSize);
		_DBService.update(directory);
		
		return dirSize;
	}

	private BigDecimal insertFile(java.io.File file, Directory aDirectory) {
		
		CreateFile fileCreator = new CreateFile(file, aDirectory, "");
		fileCreator.execute();
		File createdFile = (File) fileCreator.result();
		return createdFile.getSize();
	}

	private Directory insertDir(java.io.File file, Directory parentDir, BigDecimal dirSize) {
		
		CreateDirectory directoryCreator = new CreateDirectory(file, _CreatedGroup, parentDir , dirSize, "");
		directoryCreator.execute();
		Directory createdDirectory = (Directory) directoryCreator.result();
		
		return (Directory) createdDirectory;
	}
	
	
	private static Container getRoot(){
		WhereClausesList whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "id", new Integer(0) ));
		IDBService db = ((IDBService) ApplicationBuilder.getApplicationBuilder().getServiceProvider().getService(Constants.DATABASE) );
		return db.select(orm.mapping.Container.class, whereClausesArray).get(0);
		
	}
	
}

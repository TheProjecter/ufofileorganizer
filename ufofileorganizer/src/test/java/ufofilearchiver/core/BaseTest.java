package ufofilearchiver.core;

import java.util.Date;

import orm.mapping.*;
import ufofilearchiver.core.services.EqualsClause;
import ufofilearchiver.core.services.IDBService;
import ufofilearchiver.core.services.WhereClausesList;

import junit.framework.TestCase;

public class BaseTest extends TestCase {

	protected IDBService _DBService;
	protected Container _Container1, _Container2;
	protected Group _Group1, _Group2, _Group3;
	protected Directory _Directory1;
	protected Directory _Directory2;
	protected Directory _Directory3;
	protected File _File1;
	protected Container _Container3;
	protected Directory _Directory21;
	protected File _File21;
	protected long _Timestamp;
	
	
	protected void setUp() throws Exception {
		super.setUp();
		
		Date aDate = new Date();
		_Timestamp = aDate.getTime() + ( (int) ( 10000.0 * (new Double(Math.random()).doubleValue() ) ) ) ;
  		_DBService = (IDBService) ApplicationBuilder.getApplicationBuilder().getServiceProvider().getService(Constants.DATABASE);
  		
  		WhereClausesList whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "id", new Integer(0) ));
		Container root = (Container) _DBService.select(orm.mapping.Container.class, whereClausesArray).get(0);
		
  		_Container1 = new Container();
  		_Container1.setContainer(root);
  		root.getContainers().add(_Container1);
  		_Container1.setDescription("primo container");
  		_Container1.setName("Container1" + _Timestamp);
  		_Container1.setId(100);
  		assertTrue(_DBService.insert(_Container1));
  		
  		_Container2 = new Container();
  		_Container2.setContainer(root);
  		root.getContainers().add(_Container2);
  		_Container2.setDescription("secondo container");
  		_Container2.setName("Container2" + _Timestamp);
  		assertTrue(_DBService.insert(_Container2));
  		
  		_Container3 = new Container();
  		_Container3.setContainer(root);
  		root.getContainers().add(_Container3);
  		_Container3.setDescription("secondo container");
  		_Container3.setName("Container3" + _Timestamp);
  		assertTrue(_DBService.insert(_Container3));
  		
  		_Group1 = new Group();
  		_Group1.setContainer(_Container1);
  		_Container1.getGroups().add(_Group1);
  		_Group1.setDescription("gruppo 1 - container 1");
  		_Group1.setLocation("c:/");
  		_Group1.setName("gruppo 1" + _Timestamp);
  		assertTrue(_DBService.insert(_Group1));
  		assertTrue(_Group1.getDirectories().size() == 0);

  		_Directory1 = new Directory();
  		_Directory1.setDescription("Directory 1 - Gruppo 1");
  		_Directory1.setGroup(_Group1);
  		_Group1.getDirectories().add(_Directory1);
  		_Directory1.setName("Directory 1");
  		_Directory1.setLocation("c:/");
  		assertTrue(_DBService.insert(_Directory1));
  		//_Group1.
  		//assertTrue(_Group1.getDirectories().size() == 1);
  		
  		_Directory2 = new Directory();
  		_Directory2.setDescription("Directory 2 - Gruppo 1");
  		_Directory2.setGroup(_Group1);
  		_Group1.getDirectories().add(_Directory2);
  		_Directory2.setDirectory( _Directory1 );
  		_Directory1.getDirectories().add(_Directory2);
  		_Directory2.setName("subdir");
  		_Directory2.setLocation("c:/subdir/");
  		assertTrue(_DBService.insert(_Directory2));
  		
  		_Directory3 = new Directory();
  		_Directory3.setDescription("Directory 3 - Gruppo 1");
  		_Directory3.setGroup(_Group1);
  		_Directory3.setDirectory( _Directory2 );
  		_Directory2.getDirectories().add(_Directory3);
  		_Directory3.setName("subdir");
  		_Directory3.setLocation("c:/subdir/subdir/");
  		assertTrue(_DBService.insert(_Directory3));
  		
  		_File1 = new File();
  		_File1.setDirectory(_Directory1);
  		_Directory1.getFiles().add(_File1);
  		_File1.setGroup(_Group1);
  		_File1.setName("File1");
  		_File1.setLocation("c:/");
  		assertTrue(_DBService.insert(_File1));
  		
  		_Group2 = new Group();
  		_Group2.setContainer(_Container1);
  		_Container1.getGroups().add(_Group2);
  		_Group2.setDescription("gruppo 2 - container 1");
  		_Group2.setLocation("d:/");
  		_Group2.setName("gruppo 2" + _Timestamp);
  		assertTrue(_DBService.insert(_Group2));
  		
  		_Directory21 = new Directory();
  		_Directory21.setDescription("Directory 1 - Gruppo 2");
  		_Directory21.setGroup(_Group2);
  		_Group2.getDirectories().add(_Directory21);
  		//_Directory21.setDirectory( ));
  		_Directory21.setName("");
  		_Directory21.setLocation("d:/");
  		assertTrue(_DBService.insert(_Directory21));
  		
  		_File21 = new File();
  		_File21.setDirectory(_Directory21);
  		_Directory21.getFiles().add(_File21);
  		_File21.setGroup(_Group2);
  		_File21.setName("File21");
  		_File21.setLocation("d:/");
  		assertTrue(_DBService.insert(_File21));
  		
  		_Group3 = new Group();
  		_Group3.setContainer(_Container2);
  		_Container2.getGroups().add(_Group3);
  		_Group3.setDescription("gruppo 3 - container 2");
  		_Group3.setLocation("/");
  		_Group3.setName("gruppo 3" + _Timestamp);
  		assertTrue(_DBService.insert(_Group3));
  		
  		//assertTrue(_DBService.commit());
	}

	protected void tearDown() throws Exception {
		super.tearDown();

		_DBService.delete(_File1);
		_DBService.delete(_File21);
		
		_DBService.delete(_Directory1);
		_DBService.delete(_Directory2);
		_DBService.delete(_Directory3);
		_DBService.delete(_Directory21);
		
		_DBService.delete(_Group1);
		_DBService.delete(_Group2);
		_DBService.delete(_Group3);
		
		_DBService.delete(_Container1);
		_DBService.delete(_Container2);
		_DBService.delete(_Container3);

		//_DBService.rollback();
		//_DBService.close();
		_DBService.commit();
	}

	
	public void testInserimento(){
		assertTrue(true);
	}
}

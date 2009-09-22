package ufofileorganizer.core.services;

import java.util.Date;
import java.util.List;

import orm.mapping.Container;
import ufofileorganizer.core.ApplicationBuilder;
import ufofileorganizer.core.Constants;
import ufofileorganizer.core.services.EqualsClause;
import ufofileorganizer.core.services.IDBService;
import ufofileorganizer.core.services.WhereClausesList;
import junit.framework.TestCase;

public class DBService_Test extends TestCase {

	private IDBService _DBService;

	protected void setUp() throws Exception {
		super.setUp();
  		_DBService = ( (IDBService) ApplicationBuilder.getApplicationBuilder().getServiceProvider().getService(Constants.DATABASE));

	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void test_DBTest1(){
		assertNotNull(_DBService);
		
		WhereClausesList whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "id", new Integer(0) ));

		Container root = (Container) _DBService.select(orm.mapping.Container.class, whereClausesArray).get(0);
		
		String aName = "Container1 da servizio" + new Date().getTime();
  		Container aContainer1 = new Container();
  		aContainer1.setContainer(root);
  		aContainer1.setDescription("primo container con interfaccia");
  		aContainer1.setName(aName);
  		
  		assertTrue( _DBService.insert(aContainer1) );
  		
		whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "id", aContainer1.getId() ));

		Container containerResult = (Container) _DBService.select(orm.mapping.Container.class, whereClausesArray).get(0);
		
		
		assertNotNull( containerResult );
		assertEquals(aName, containerResult.getName());  		
  		
  		assertTrue( _DBService.delete(aContainer1) );
  		assertTrue( _DBService.commit() );
	}
	
	public void test_DBTest2(){
		assertNotNull(_DBService);
		
		WhereClausesList whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "id", new Integer(0) ));

		List<Container> resultSelect = _DBService.select(orm.mapping.Container.class, whereClausesArray);
		
		
		assertNotNull(resultSelect);
		assertEquals(1, resultSelect.size());
		
		assertEquals("root", ((Container) resultSelect.get(0)).getName());
	}
	

}

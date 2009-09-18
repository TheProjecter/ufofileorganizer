package ufofilearchiver.core.commands;

import java.util.Date;

import orm.mapping.Container;
import ufofilearchiver.core.BaseTest;
import ufofilearchiver.core.commands.facade.CreateContainer;
import ufofilearchiver.core.services.EqualsClause;
import ufofilearchiver.core.services.WhereClausesList;


public class CreateDeleteContainer_Test extends BaseTest {
	
	public void test_create1() throws Exception {
		
		Date timestamp = new Date();
		String aName = "Container" + timestamp;
		
		CreateContainer containerCommand = new CreateContainer(aName , "creato da test");
		containerCommand.execute();
		
		Container createdContainer = (Container) containerCommand.result();
		
		assertNotNull(createdContainer);
		
		WhereClausesList whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "name", aName ));		
		Container estractedContainer = (Container) (_DBService.select(Container.class, whereClausesArray).get(0));
		
		assertEquals(createdContainer,estractedContainer);
		
		
		DeleteContainer deleteContainerCommand = new DeleteContainer(createdContainer);
		deleteContainerCommand.execute();
		
		
		
		whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "name", aName ));
		assertEquals(0, _DBService.select(Container.class, whereClausesArray).size());
	}
	
}

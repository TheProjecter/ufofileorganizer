package ufofileorganizer.core.commands.facade;

import orm.mapping.Container;
import ufofileorganizer.core.ApplicationBuilder;
import ufofileorganizer.core.Constants;
import ufofileorganizer.core.commands.ACommand;
import ufofileorganizer.core.services.EqualsClause;
import ufofileorganizer.core.services.IDBService;
import ufofileorganizer.core.services.WhereClausesList;

public class CreateContainer extends ACommand {

	private Container _Container;
	private Container _Parent;
	private String _Description;
	private String _Name;

	public CreateContainer( String aName, String aDescription , Container aParent ){
		_Name = aName;
		_Description = aDescription;
		_Parent = aParent;
	}
	
	public CreateContainer(String aName, String aDescription){
		this( aName , aDescription , root());

	}
	
	public boolean execute() {
		_Container = new Container();		
		_Container.setName(_Name);
		_Container.setDescription(_Description);
		_Container.setContainer(_Parent);
		_Parent.getContainers().add(_Container);
		
		boolean ok = _DBService.insert(_Container);
		if (ok) {
			updateSystem();
		}
		return ok;
	}

	public Object result() {
		return _Container;
	}
	
	private static Container root(){
		WhereClausesList whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new EqualsClause( "id", new Integer(0) ));
		Container root = (Container) ( (IDBService) ApplicationBuilder.getApplicationBuilder().getServiceProvider().getService(Constants.DATABASE) ).select(orm.mapping.Container.class, whereClausesArray).get(0);
		return root;
	}
}

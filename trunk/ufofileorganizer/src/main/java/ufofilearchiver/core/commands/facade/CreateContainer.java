package ufofilearchiver.core.commands.facade;

import orm.mapping.Container;
import ufofilearchiver.core.ApplicationBuilder;
import ufofilearchiver.core.Constants;
import ufofilearchiver.core.commands.ACommand;
import ufofilearchiver.core.services.EqualsClause;
import ufofilearchiver.core.services.IDBService;
import ufofilearchiver.core.services.WhereClausesList;

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

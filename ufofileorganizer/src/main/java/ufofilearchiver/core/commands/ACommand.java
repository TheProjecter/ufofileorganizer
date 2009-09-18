package ufofilearchiver.core.commands;

import ufofilearchiver.core.ApplicationBuilder;
import ufofilearchiver.core.Constants;
import ufofilearchiver.core.services.IDBService;

public abstract class ACommand implements ICommand {

	
	protected IDBService _DBService;

	protected ACommand(){
		_DBService = ((IDBService) ApplicationBuilder.getApplicationBuilder().getServiceProvider().getService(Constants.DATABASE) );
	}
	
	protected void updateSystem(){
		ApplicationBuilder.getApplicationBuilder().inform();
	}
}

package ufofileorganizer.core.commands;

import ufofileorganizer.core.ApplicationBuilder;
import ufofileorganizer.core.Constants;
import ufofileorganizer.core.services.IDBService;

public abstract class ACommand implements ICommand {

	
	protected IDBService _DBService;

	protected ACommand(){
		_DBService = ((IDBService) ApplicationBuilder.getApplicationBuilder().getServiceProvider().getService(Constants.DATABASE) );
	}
	
	protected void updateSystem(){
		ApplicationBuilder.getApplicationBuilder().inform();
	}
}

package ufofilearchiver.core;

import java.util.*;

import ufofilearchiver.core.services.DBServiceHibernateBuilder;
import ufofilearchiver.core.services.IServiceBuilder;
import ufofilearchiver.core.services.ServiceProvider;

public class ApplicationBuilder implements ScanObservable {

	private static ApplicationBuilder applicationBuilder;
	private Vector<ScanObserver> _Observers = new Vector<ScanObserver>();
	private ServiceProvider _ServiceBuilder;

	public static ApplicationBuilder getApplicationBuilder(){
		if (applicationBuilder == null) {
			applicationBuilder = new ApplicationBuilder();
		}
		return applicationBuilder;
	}
	
	/**
	 * Apre la connessione al db con i dati
	 * 
	 * @param path il percorso (la directory) in cui si trova il db con i dati
	 * @return
	 */
	private ApplicationBuilder() {
		_ServiceBuilder = new ServiceProvider();
		String curDir = System.getProperty("user.dir");
		//System.out.println(curDir);
		String aConnectionString = "jdbc:h2:" + curDir + "/data/test";
		System.out.println(aConnectionString);
		IServiceBuilder aDbService = new DBServiceHibernateBuilder(Constants.DATABASE, aConnectionString);		
		_ServiceBuilder.register(aDbService);
	}
	
	public ServiceProvider getServiceProvider(){
		return _ServiceBuilder;
	}
	
	public void attach(ScanObserver so) {
		_Observers.add(so);
	}

	public void detach(ScanObserver so) {
		for (int i = 0; i < _Observers.size(); i++) {
			if (_Observers.get(i).equals(so)) {
				_Observers.removeElementAt(i);
				i--;
			}
		}
	}

	public void inform() {
		for (int i = 0; i < _Observers.size(); i++) {
			( (ScanObserver) _Observers.get(i) ).update();
		}
	}
	
}

package ufofileorganizer.core.services;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public abstract class ADBServiceHibernate implements IDBService {
	private Session _session;
	protected String _name;
	private String _connectionString;
	
	protected ADBServiceHibernate(String aName, String aConnectionString){
		_name = aName;
		_connectionString = aConnectionString;
	}
	
	public final String getName() {
		return _name;
	}
	
	protected final Session session(){
		if (_session == null || !_session.isOpen() ) {
			
			Configuration config = new Configuration();
			config.setProperty("hibernate.connection.url", _connectionString);
			SessionFactory sessionFactory = config.configure().buildSessionFactory();
			_session = sessionFactory.openSession();
			//_session = sessionFactory.getCurrentSession();
		}
		//_session.clear();
		return _session;
		
	}
}

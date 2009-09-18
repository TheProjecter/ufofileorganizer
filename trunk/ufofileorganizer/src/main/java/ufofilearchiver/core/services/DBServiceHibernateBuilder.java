package ufofilearchiver.core.services;


public class DBServiceHibernateBuilder implements IServiceBuilder{

	private String _name;
	private String _connectionString;

	public DBServiceHibernateBuilder(String aName, String aConnectionString) {
		_name = aName;
		_connectionString = aConnectionString;
	}
	
	public IService build() {
		return new DBServiceHibernate(_name , _connectionString);
	}

	public String getName() {
		return _name;
	}
	
}

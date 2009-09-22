package ufofileorganizer.core.services;

import java.util.List;


public interface IDBService extends IService{
	
	<T> List<T> select(Class<T> selectClass, WhereClausesList whereClauses);
	
	boolean delete(Object aObject);
	
	boolean insert(Object aObject);
	
	boolean update(Object aObject);
	
	int executeQuery(String aHqlQuery);
	
	boolean commit();
	
	boolean rollback();
	
	boolean close();
	
	

}

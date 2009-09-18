package ufofilearchiver.core.services;

import java.util.ArrayList;
import java.util.List;

public class WhereClausesList {
	
	List<IWhereClause> _whereClauses = new ArrayList<IWhereClause>();
	
	public void add(IWhereClause whereClause){
		_whereClauses.add(whereClause);
	}
	
	public List<IWhereClause> whereClauses(){
		return _whereClauses;
	}

	public int size() {
		return _whereClauses.size();
	}

	public IWhereClause get(int i) {
		return _whereClauses.get(i);
	}
}

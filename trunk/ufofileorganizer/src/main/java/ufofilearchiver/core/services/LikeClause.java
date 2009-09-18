package ufofilearchiver.core.services;

public class LikeClause implements IWhereClause {

	private String _Property;
	private Object _Value;

	public LikeClause( String aProperty, Object aValue ){
		_Property = aProperty;
		_Value = aValue;
	}
	
	public String property() {
		return _Property;
	}

	public Object value() {
		return _Value;
	}

}

package ufofilearchiver.core.services;

public class EqualsClause implements IWhereClause {

	private String _Property;
	private Object _Value;

	public EqualsClause( String aProperty, Object aValue ){
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

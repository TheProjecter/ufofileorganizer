package ufofilearchiver.core.services;

public class LikeIgnoreCaseClause implements IWhereClause {

	private String _Property;
	private Object _Value;

	public LikeIgnoreCaseClause( String aProperty, Object aValue ){
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

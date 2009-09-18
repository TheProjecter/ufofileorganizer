package ufofilearchiver.core.commands;

import java.math.BigDecimal;

import orm.mapping.Container;
import orm.mapping.Group;
import orm.mapping.Type;
import utils.string.SQLStringOperator;
import utils.string.SqlString_AppendiPathSeparatorAllaFine;
import utils.string.SqlString_CambiaPathSeparator;

public class CreateGroup extends ACommand {

	
	private SQLStringOperator _PathStringOperator = new SqlString_AppendiPathSeparatorAllaFine(new SqlString_CambiaPathSeparator());
	private BigDecimal _Size;
	private String _Description;
	private Container _Container;
	private String _Name;
	private String _Location;
	private Group _Group;

	public CreateGroup(String aName, Container aContainer, String aLocation, String aDescription, BigDecimal aSize) {
		_Name = aName;
		_Container = aContainer;
		_Description = aDescription;
		_Location = aLocation;
		_Size = aSize;
	}

	public boolean execute() {
		_Group = new Group();
		_Group.setName(_Name);
		_Group.setLocation( _PathStringOperator.operate( _Location ) );
		_Group.setSize( _Size );
		_Group.setType(new Type("Group"));
		_Group.setDescription(_Description);
		_Group.setContainer(_Container);
		_Container.getGroups().add(_Group);
		
		return _DBService.insert(_Group);
	}

	public Object result() {
		return _Group;
	}

}

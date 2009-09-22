package ufofileorganizer.core.commands.facade;

import java.util.Iterator;
import java.util.List;

import orm.mapping.*;
import ufofileorganizer.core.commands.ACommand;
import ufofileorganizer.core.node.NullTreeNodeVector;
import ufofileorganizer.core.node.TreeNodeFactory;
import ufofileorganizer.core.node.TreeNodeVector;
import ufofileorganizer.core.services.LikeIgnoreCaseClause;
import ufofileorganizer.core.services.WhereClausesList;

public class Find extends ACommand {

	
	private TreeNodeVector _TreeNodeVector = new NullTreeNodeVector();
	private String _textToSearch;

	public Find(String aTextToSearch) {
		_textToSearch = aTextToSearch;
	}

	@SuppressWarnings("unchecked")
	public boolean execute() {
		
		WhereClausesList whereClausesArray = new WhereClausesList();
		whereClausesArray.add(new LikeIgnoreCaseClause( "name", _textToSearch ));		
		
		List results = _DBService.select(Group.class, whereClausesArray);
		results.addAll(_DBService.select(Directory.class, whereClausesArray));
		results.addAll(_DBService.select(File.class, whereClausesArray));
		
		Iterator resultIterator = results.iterator();
		while(resultIterator.hasNext()) {
		    _TreeNodeVector.add( TreeNodeFactory.getTreeNodeVector(resultIterator.next()) );
		}
		
		return true;
	}

	public Object result() {
		return _TreeNodeVector;
	}

}

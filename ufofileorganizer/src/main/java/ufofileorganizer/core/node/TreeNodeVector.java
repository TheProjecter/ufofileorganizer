package ufofileorganizer.core.node;

import java.math.BigDecimal;
import java.util.Vector;

import ufofileorganizer.core.*;
import ufofileorganizer.core.services.IDBService;


/**
 * Questa classe estende Vector per creare un albero che sarà utilizzato per
 * rappresentare la struttura dei gruppi e delle directory memorizzate
 * 
 * @author francesco
 * 
 * @param <E>
 */

public abstract class TreeNodeVector extends Vector<TreeNodeVector> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String CONTAINER = "Container";
	public static final String GROUP = "Group";
	public static final String DIRECTORY = "Directory";
	public static final String FILE = "File";
	
	protected IDBService _DBService = null;
	
	public TreeNodeVector(){
		_DBService = (IDBService) ApplicationBuilder.getApplicationBuilder().getServiceProvider().getService(Constants.DATABASE);
	}

	/**
	 * 
	 * @return il nome del nodo
	 */
	public String toString() {
		return getName();
	}

	/**
	 * 
	 * @return il nome del nodo
	 */
	public abstract String getName();

	/**
	 * 
	 * @return l'id univoco dell'oggetto sul db
	 */
	public abstract int getID();

	/**
	 * 
	 * @return il path dell'oggetto
	 */
	public abstract String getLocation();


	/**
	 * @return il tipo dell'oggetto, se container, gruppo, directory o file
	 */
	public abstract String getType();
	
	public abstract String getDescription();
	
	public abstract String getParentGroupName();
	
	public abstract String getParentContainerName();

	public abstract Long getSize();
	
	public abstract BigDecimal getModified();
	
	public abstract TreeNodeVector getAllChildren();
	
	public abstract TreeNodeVector getChildrenOfSameType();
	public abstract TreeNodeVector getChildrenOfSubType();
	

}

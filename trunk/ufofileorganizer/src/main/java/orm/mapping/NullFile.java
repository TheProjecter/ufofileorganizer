package orm.mapping;

import java.math.BigDecimal;

public class NullFile extends File{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer getID(){
		return new Integer(0);
	}
	
	public BigDecimal getSize(){
		return new BigDecimal(0);
	}
	
	public BigDecimal getModified() {		
		return new BigDecimal(0);
	}
}

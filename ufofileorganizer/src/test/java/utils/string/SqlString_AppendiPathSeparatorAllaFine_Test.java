package utils.string;

import junit.framework.TestCase;

public class SqlString_AppendiPathSeparatorAllaFine_Test extends TestCase {

	private String stConSegnoDiPathAllaFine = null;
	private String stSoloSegnoDiPath = null;
	private String stSenzaSegnoDiPathAllaFine = null;
	private String stLunghezzaZero = null;
	
			
	SQLStringOperator stringOperator = null;
	
	
	protected void setUp() throws Exception {
		super.setUp();
		
		stConSegnoDiPathAllaFine = new String("Stringa con path alla fine /");
		stSoloSegnoDiPath = "/";
		stSenzaSegnoDiPathAllaFine = new String("Stringa senza path alla fine");
		stLunghezzaZero = "";
		
		stringOperator = new SqlString_AppendiPathSeparatorAllaFine();
	}
	
	public void test_validate_1(){		
		assertEquals(stConSegnoDiPathAllaFine, stringOperator.operate(stConSegnoDiPathAllaFine));
		
		assertEquals(stSoloSegnoDiPath, stringOperator.operate(stSoloSegnoDiPath));
	}
	
	public void test_validate_2(){
		assertEquals(stSenzaSegnoDiPathAllaFine + "/", stringOperator.operate(stSenzaSegnoDiPathAllaFine));
	}
	
	public void test_validate_3(){
		assertNull(stringOperator.operate(null));
	}
	
	public void test_validate_4(){
		assertEquals("/", stringOperator.operate(stLunghezzaZero));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}



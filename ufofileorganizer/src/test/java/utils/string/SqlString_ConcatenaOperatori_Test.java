package utils.string;

import junit.framework.TestCase;

public class SqlString_ConcatenaOperatori_Test extends TestCase {

	private String stringaProvaUnix1 = null;
	private String stringaProvaWindows1 = null;	
	private String stringaProvaUnix2 = null;
	private String stringaProvaWindows2 = null;	
	SQLStringOperator stringOperator = null;
	
	
	protected void setUp() throws Exception {
		super.setUp();
		
		stringaProvaUnix1 = new String("/usr/home/ufo/cina'");
		stringaProvaUnix2 = new String("/usr/home/ufo/cina'/");
		
		stringaProvaWindows1 = new String("c:\\windows\\System32");
		stringaProvaWindows2 = new String("c:\\windows\\System32\\");
				
		stringOperator = new SqlString_AppendiPathSeparatorAllaFine(new SqlString_CorreggiApice(new SqlString_CambiaPathSeparator()));
	}
	
	public void test_validate_1(){		
		assertEquals("/usr/home/ufo/cina''/", stringOperator.operate(stringaProvaUnix1));
		assertEquals("/usr/home/ufo/cina''/", stringOperator.operate(stringaProvaUnix2));
	}
	
	public void test_validate_2(){
		assertEquals("c:/windows/System32/", stringOperator.operate(stringaProvaWindows1));
		assertEquals("c:/windows/System32/", stringOperator.operate(stringaProvaWindows2));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}




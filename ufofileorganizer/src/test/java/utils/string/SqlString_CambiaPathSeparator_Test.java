package utils.string;

import junit.framework.TestCase;

public class SqlString_CambiaPathSeparator_Test extends TestCase {

	String stValida1 = null;
	String stConSegnoDiPathErrato = null;
	
	CharSequence target = new StringBuilder("\\");
	CharSequence replacement = new StringBuilder("/");
			
			
	SqlString_CambiaPathSeparator stringOperator = null;
	
	
	protected void setUp() throws Exception {
		super.setUp();
		
		stValida1 = new String("stringa da inserire in una insert");
		stConSegnoDiPathErrato = new String("Stringa con path da cambiare qui \\ che dovrà essere /");
		
		stringOperator = new SqlString_CambiaPathSeparator();
	}
	
	public void test_validate_1(){		
		assertEquals(stValida1, stringOperator.operate(stValida1));
	}
	
	public void test_validate_2(){
		assertEquals(stConSegnoDiPathErrato.replace(target , replacement), stringOperator.operate(stConSegnoDiPathErrato));
		
		//questo è l'equivalente delle tre stringhe di sopra, solo più esplicito
		assertEquals(new String("Stringa con path da cambiare qui / che dovrà essere /"), stringOperator.operate(stConSegnoDiPathErrato));
	}
	
	public void test_validate_3(){
		assertNull(stringOperator.operate(null));
	}
	
	public void test_validate_4(){
		assertEquals("",stringOperator.operate(""));
		
		assertEquals("c:/windows/System32/", stringOperator.operate(new String("c:\\windows\\System32\\")));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}


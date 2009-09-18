package utils.string;

import junit.framework.TestCase;

public class SqlString_CorreggiApice_Test extends TestCase {

	String stValida1 = null;
	String stConApice1 = null;
	SqlString_CorreggiApice stringOperator = null;
	
	
	protected void setUp() throws Exception {
		super.setUp();
		
		stValida1 = new String("stringa da inserire in una insert");
		stConApice1 = new String("Stringa con apice qui ' che dovrà essere doppio");
		
		stringOperator = new SqlString_CorreggiApice();
	}
	
	public void test_validate_1(){		
		
		assertEquals(stValida1, stringOperator.operate(stValida1));
		
	}
	
	public void test_validate_2(){
		CharSequence target = new StringBuilder("'");
		CharSequence replacement = new StringBuilder("''");
		assertEquals(stConApice1.replace(target , replacement), stringOperator.operate(stConApice1));
		
		//questo è l'equivalente delle tre stringhe di sopra, solo più esplicito
		assertEquals(new String("Stringa con apice qui '' che dovrà essere doppio"), stringOperator.operate(stConApice1));
	}
	
	public void test_validate_3(){
		assertNull(stringOperator.operate(null));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}

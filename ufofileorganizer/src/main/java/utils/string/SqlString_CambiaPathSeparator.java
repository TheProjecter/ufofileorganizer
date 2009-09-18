package utils.string;

	/**
	 * Questa classe offre metodi per ripulire una stringa da caratteri che potrebbero creare 
	 * problemi in fase di inserimento su db tramite clausola sql
	 * @author Francesco Cinà
	 * @version 0.1
	 */
	public class SqlString_CambiaPathSeparator implements SQLStringOperator{
		
		SQLStringOperator child = null;
		
		public SqlString_CambiaPathSeparator(){
			child = new SqlString_DoNothing();
		}
		
		public SqlString_CambiaPathSeparator(SQLStringOperator child){
			this.child = child;
		}
		
		/**
		 * presa una stringa restituisce la stessa con i segni \ sostituiti da / Se
		 * la stringa non finisce con un carattere di / o \ questo viene aggiunto Se
		 * la stringa è null viene restituito ""
		 * 
		 * @param stringa
		 * @return
		 */
		public String operate(String stringaDaValidare)
		{
			
			// Il codice ASCII relativo alla virgoletta ' è il 39
			// Il codice ASCII relativo alla doppia virgoletta " è il 34
			// Il codice ASCII relativo al "line feed" (cioè "enter") è il 10 
			// Il codice ASCII relativo al \ (cioè "back slash") è il 92 
			// Il codice ASCII relativo al / (cioè "slash") è il 47 
	
			String stringaPulita = null;
			
			try {
				CharSequence target = new StringBuilder("\\");
				CharSequence replacement = new StringBuilder("/");
				stringaPulita = child.operate(stringaDaValidare).replace(target , replacement);
			}
			catch (Exception e){
				// do nothing
			}
			
			return stringaPulita;
		}
	}


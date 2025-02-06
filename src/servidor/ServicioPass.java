package servidor;

import java.util.Random;

public class ServicioPass {

	//Atributos
	private RequisitosPass requisitosPass;
	
	//Constructor
	public ServicioPass(RequisitosPass requisitosPass) {
		this.requisitosPass = requisitosPass; 
	}
	
	//Métodos
	public String generaPass() {
		/*
		 * Creamos objeto Stringbuilder para crear la contraseña, ya que es de tamaño y contenido modificable. 
		 * Posee el método append() muy util para la tarea.(Cuenca, n.d.)
		 * 
		 * Cuenca, J. L. (n.d.). StringBuffer, StringBuilder Java. Ejemplo. Diferencias entre clases. Criterios para elegir. Métodos.
		 * (CU00914C). aprenderaprogramar.com. 
		 * https://www.aprenderaprogramar.com/index.php?option=com_content&view=article&id=961:stringbuffer-stringbuilder-java-ejemplo-diferencias-entre-clases-criterios-para-elegir-metodos-cu00914c&catid=58&Itemid=180
		 */
		
		StringBuilder pass  = new StringBuilder();
		Random random = new Random();
		String letrasMayus = "ABCDEFGHIJKKLMNÑOPQRRSTUVWXYZ";
		String digitos  = "0123456789"; 
		String charSpecial = "!@#$%^&*()_-+=.:?";
		
		//Cogemos un número de letras mayusculas dadas por el cliente
		for(int i = 0; i<requisitosPass.getNumMayusculas(); i++) {
			int posicion = random.nextInt(letrasMayus.length());
			
			pass.append(letrasMayus.charAt(posicion)); //La añadimos a la cadena  pass usando el método de stringBuilder
		}
		
		//Aprovechamos la variable letraMayus pero le pasamos el número de minusculas dadas.
		for(int i = 0; i<requisitosPass.getNumMinusculas(); i++) {
			int posicion = random.nextInt(letrasMayus.length());
			//https://stackoverflow.com/questions/5447580/tolowercasechar-method
			pass.append(Character.toLowerCase(letrasMayus.charAt(posicion)));//La añadimos a la cadena  pass en minuscula
		}
		
		//Cogemos un número de dígitos dados por el cliente
		for(int i = 0; i<requisitosPass.getNumDigitos(); i++) {
			int posicion = random.nextInt(digitos.length());
			
			pass.append(digitos.charAt(posicion));
		}
		
		//Cogemos un número de caracteres especiales dados por el cliente
		for(int i = 0; i<requisitosPass.getNumCaractEspeciales(); i++) {
			int posicion = random.nextInt(charSpecial.length());
			
			pass.append(charSpecial.charAt(posicion));
		}
		
		/*
		 * Alteramos el orden de la contraseña  aprovechando el método setCharAt de StringBuilder,
		 * este permite cambiar un carácter a una posición de la cadena dada. (Cuenca, n.d.)
		 */
		for(int i = 0; i<pass.length(); i++) {
			int posicion = random.nextInt(pass.length());
			
			char caracter = pass.charAt(posicion);
			
			pass.setCharAt(posicion, pass.charAt(i));
			pass.setCharAt(i, caracter);
		}
		
		//retornamos pass convertido a string
		return pass.toString();
	}
	
	public int longitudPass() {
		//llamamos al método toString() de requisitosPass para que devuelva la longitud de la contraseña. 
		return Integer.parseInt(requisitosPass.toString());
	}
}

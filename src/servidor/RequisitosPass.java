package servidor;

public class RequisitosPass {

	//Atributos
	private int numCaractEspeciales;
	private int numDigitos;
	private int numMayusculas;
	private int numMinusculas;
	
	//Constructor
	public RequisitosPass(int numCaractEspeciales, int numDigitos, int numMayusculas, int numMinusculas) {
		this.numCaractEspeciales = numCaractEspeciales;
		this.numDigitos = numDigitos;
		this.numMayusculas = numMayusculas;
		this.numMinusculas = numMinusculas;
	}
	
	//Métodos getter y setter
	public int getNumCaractEspeciales() {
		return numCaractEspeciales;
	}

	public void setNumCaractEspeciales(int numCaractEspeciales) {
		this.numCaractEspeciales = numCaractEspeciales;
	}

	public int getNumDigitos() {
		return numDigitos;
	}

	public void setNumDigitos(int numDigitos) {
		this.numDigitos = numDigitos;
	}

	public int getNumMayusculas() {
		return numMayusculas;
	}

	public void setNumMayusculas(int numMayusculas) {
		this.numMayusculas = numMayusculas;
	}

	public int getNumMinusculas() {
		return numMinusculas;
	}

	public void setNumMinusculas(int numMinusculas) {
		this.numMinusculas = numMinusculas;
	}

	//Método toString con la suma de números
	@Override
	public String toString() {
		return Integer.toString(numCaractEspeciales + numDigitos + numMayusculas + numMinusculas);
	}
	
	
}

package cliente;

import java.io.IOException;

public class MainCliente {

	public static void main(String[] args) throws IOException {
		// Creamos objeto de la clase cliente
		Cliente cliente = new Cliente();
		System.out.println("Cliente conectado");
		
		//Iniciamos la conexión
		cliente.interactua();

	}

}

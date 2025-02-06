package servidor;

import java.io.IOException;

public class MainServidor {

	public static void main(String[] args) throws IOException {
		//Declaramos objetos
		Servidor servidor = new Servidor();
		System.out.println("Servidor arrancando");
		
		//Lo  iniciamos
		servidor.start();
		
		System.out.println("Esperando Cliente...");
		
		//Declaramos para finalizarlo pero se deja comentado ya que debe quedarse abierto
		//servidor.finalizarServer();

	}

}

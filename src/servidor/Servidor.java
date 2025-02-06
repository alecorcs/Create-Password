package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	//Datos de conexion
	private final int PUERTO  = 4321;
	private ServerSocket serverSocket;
	private Socket socket;
	private ServicioPass servicioPass;
	private RequisitosPass requisitosPass;
	
	//Constructor
	public Servidor() throws IOException {
		//Se define la conexión.
		serverSocket = new ServerSocket(PUERTO);
		//Se inicia cliente.
		socket = new Socket();
	}

	//Inicia conexión
	public void start() throws IOException {
		
		//Se reciben datos del cliente
		while(true) {
			//Se guarda la peticion del cliente
			socket = serverSocket.accept();
			System.out.println("Cliente conectado desde " + socket.getInetAddress()+ ":" + socket.getPort());
			
			//Al recibir la peticion, empieza la conexión
			//Entrada mensaje
			DataOutputStream mensajeCliente = new DataOutputStream(socket.getOutputStream());
			//Salida mensaje
			DataInputStream entradaCliente = new DataInputStream(socket.getInputStream());
			
			//Envia saludo cliente
			mensajeCliente.writeUTF("Hola, soy el servidor. ¿Cómo te llamas?");
			//Recibe el nombre del cliente
			String nombre = entradaCliente.readUTF();
			System.out.println("Nombre del cliente: " + nombre);
			//Envia bienvenida
			mensajeCliente.writeUTF("Te doy la bienvenida, " + nombre);
			
			//Obtención de requisitos
			mensajeCliente.writeUTF("Voy a solicitarte distintos requisitos para la contraseña que voy a generar");
			
			//Envia petición minusculas
			mensajeCliente.writeUTF("Cuántas minúsculas debe tener la contraseña?");			
			//Llama a la funcion que recibe número cliente
			int minusculas= numeroCliente(entradaCliente, mensajeCliente);
			
			//Envia petición mayusculas
			mensajeCliente.writeUTF("Cuántas mayúsculas debe tener la contraseña?");			
			int mayusculas= numeroCliente(entradaCliente, mensajeCliente);
			
			//Envia petición dígitos
			mensajeCliente.writeUTF("Cuántos dígitos debe tener la contraseña?");			
			int digitos= numeroCliente(entradaCliente, mensajeCliente);
			
			//Envia petición caracteres especiales
			mensajeCliente.writeUTF("Cuántos caracteres especiales debe tener la contraseña?");			
			int especiales= numeroCliente(entradaCliente, mensajeCliente);
			
			System.out.println("Los requsitos del cliente son los siguientes:");
			
			System.out.println(String.format("PasswordReqs{ minusculas = %d mayusculas = %d, digitos = %d y caracteres especiales = %d",
					minusculas, mayusculas, digitos, especiales));
			
			//Se asignan los requisitos a la clase RequisitosPass para controlarlos.
			requisitosPass = new RequisitosPass(especiales, digitos, mayusculas, minusculas);
			 
			/*
			 * Pasamos el objeto requisitosPass por parámeto de la clase SeervicioPass
			 * para tener acceso a ellos.
			 */
			servicioPass = new ServicioPass(requisitosPass);
			 
			//Llamamos al método que devuelve la longitud de la contraseña
			int longitud = servicioPass.longitudPass();
			 
			//Informamos al cliente la longitud de la contraseña
			mensajeCliente.writeUTF(String.format("La longitud de la contraseña que se va a generar es de %d", longitud));
			System.out.println("Se ha enviado la longitud de la contrtaseña al cliente");
			
			//Pregunta si quiere recibirla
			mensajeCliente.writeUTF("¿Quieres generar una contraseña ahora? [si/no]");
			
			//Recibe la respuesta
			String respuesta  = entradaCliente.readUTF();
			String contraseña;
			
			//Si la respuesta es "si" 
			if(respuesta.equals("si")) {
				//Genera la contraseña
		        contraseña = servicioPass.generaPass();
		        //La envía al cliente
		        mensajeCliente.writeUTF("La contraseña generada es: " + contraseña);
		        System.out.println("Se ha enviado la contraseña al cliente");
		    } else {
		    	//Manda una despedida al cliente.
		        mensajeCliente.writeUTF("Gracias por su visita");
		    }		
		   
		}
	}
	
	//Recibe números cliente
	public int numeroCliente(DataInputStream entradaCliente, DataOutputStream mensajeCliente) throws IOException {
		int numero = 0; // Inicializa la variable para almacenar el número
	    // Convierte la entrada del cliente a un número entero
	    numero = Integer.parseInt(entradaCliente.readUTF());
	    return numero; // Devuelve el número
	}
	
	//Fun finalizar conexión
	public void finalizarServer() throws IOException {
		serverSocket.close();
	}
}

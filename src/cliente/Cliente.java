package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Cliente {
	
	//Datos de conexión
	private final String  HOST = "localhost";
	private final int PUERTO = 4321;	
	//Crea un socket
	private Socket socket;
	Scanner sc = new Scanner(System.in);
	
	//Constructor
	public Cliente() throws IOException {
		//lo conecta al puerto especificado en el host
		socket = new Socket("localhost", 4321);
	}
	
	//Funcion intercambio de mensajes
	public void interactua() throws IOException {
		
		//Se inicia la entrada de datos
		DataInputStream entradaServidor = new DataInputStream(socket.getInputStream());		
		//Salida de datos
		DataOutputStream salidaServidor = new DataOutputStream(socket.getOutputStream());
		
		//Recibe saludo servidor
		System.out.println(entradaServidor.readUTF());
		//Mensaje que queremos enviar
		String nombre = sc.nextLine();
		//envia nombre
		salidaServidor.writeUTF(nombre);
		
		//Recibe bienvenida
		System.out.println(entradaServidor.readUTF());
		//Incio Petición
		System.out.println(entradaServidor.readUTF());
		
		//Se le da el número de minúsculas
		int numMinus = darNumeroCliente(entradaServidor, salidaServidor);	
				
		//Número de mayúsculas
		int numMayus = darNumeroCliente(entradaServidor, salidaServidor);	
			
		//Número de dígitos
		int numDig = darNumeroCliente(entradaServidor, salidaServidor);	
		 
		//Número de caracteres especiales
		int carEsp = darNumeroCliente(entradaServidor, salidaServidor);	
		    
		
		//Recibe longitud de contraseña
		System.out.println(entradaServidor.readUTF());
		
		//Recibe pregunta de si quiere la contraseña
		System.out.println(entradaServidor.readUTF());
		
		//Envia respuesta de si quieres o no la contraseña
		String respuesta = sc.nextLine();
		salidaServidor.writeUTF(respuesta);
		
		//Recibe contraseña o despedida
		System.out.println(entradaServidor.readUTF());
		
		
		//Cerramos la salida de datos
		salidaServidor.close();
		//Cerramos la recepcion de datos
		entradaServidor.close();
		//Cerramos Socket
		socket.close();
		
	}
	
	//Recibe números cliente
		public int darNumeroCliente(DataInputStream entradaServidor, DataOutputStream salidaServidor) throws IOException {
			int num = 0; // Variable para almacenar el número
		    boolean valido = false; // Verificar si el número es válido
		 // Recibe petición de número
            System.out.println(entradaServidor.readUTF());
		    do {
	            // Cliente escribe número
	            String numero = sc.nextLine();
	            
	            try {
	                // Intenta convertir la entrada en un número entero
	                num = Integer.parseInt(numero);
	                
	                // Verifica si el número es positivo
	                if (num >= 0) {	                   
	                 // El número es válido
	                    valido = true;
	                    //Envía número al servidor
	                    salidaServidor.writeUTF(Integer.toString(num));
	                } else {
	                	//Si el número es negativo escribe el error.
	                	 System.out.println("Por favor, ingresa un número entero positivo.");	                    
	                }
	            } catch (NumberFormatException e) {
	            	/*
	            	 * NumberFormatException selanza en la conversión de una cadena no numérica a número.
	            	 * Vypirailenko, A. (2024, February 2). NumberFormatException en Java. CodeGym. 
	            	 * https://codegym.cc/es/groups/posts/es.588.numberformatexception-en-java
	            	 */
	                // si no es un número lanza el error
	                System.out.println("Por favor, ingresa un valor numérico válido.");	                
	            }
	            
	        } while (!valido);
		    
		    return num;
		}
}

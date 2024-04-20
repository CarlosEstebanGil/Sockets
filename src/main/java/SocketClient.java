import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.attribute.AclEntry;

import org.apache.logging.log4j.Logger;

/**
 * ejecutar 1ero el server pero no con run project sino con run file y sobre el .java del server, recien luego 
 * venir a este cliente java y ejecutarlo tmb con run file ( asi se ejecutan (se lanzan) como progs <>s independientes )  
 */
 
public class SocketClient {

	
	public static void main(String[] args) {

		final String IP_HOST = "127.0.0.1"; // host es el servidor el anfitrion el q recibe y obvio va a estar en esta pc ip 
	
		final int PUERTO = 5000; // obvio en el mismo puerto q elegí xq quise xa el server osea en el q escucha el server

		DataInputStream in;
		DataOutputStream out;

		// SABER: EN ESTE CLI HAGO 1 SOLA PETICION Y MUERE XQ NO HICE UN BUCLE NI UN FOR XA MAS PETICIONES DE EJEMPLO
		//			( pero mepa q al server hay q matarlo explicitamente xq tiene un while true ??????? ) TODO VERIFICAR ESO!
		//				--> SIIIIIIIIII si reejecuto el cli hay rta! osea q el server esta vivo!! pero no se como identificarlo 
		//				en el taskmanager process explorer asi que X AHORA cierro el ide xa que muera xD  	
		//			--> EFECTIVAMENTE EL CERRAR EL IDE MATó TODOS SUS PROCESOS Y CON ELLO A LA APP SERVER!!
		//				me di cuenta xq cerré el IDE y lo abrí y corrí de 1 1ero el socket cli app y arrojó exception "No server"
		try {
			Socket socket_client = new Socket(IP_HOST,PUERTO);
			
			in = new DataInputStream(socket_client.getInputStream());
			out = new DataOutputStream(socket_client.getOutputStream());
			
			out.writeUTF("hola desde el cliente"); // SABER: Aca el cli a dif del serv manda de una el msge
			
			
			String mensajeRta = in.readUTF(); // 	 SABER: recordemos q idem al server el in.readUTF FRENA ACA H'Q HAYA EFEC-
											  //				tivamente datos en la in corresp !!! es clave saber q frena!
			
			//aca ya recibió rta ent:
			System.out.println("rta from serve --> " + mensajeRta);
			
			socket_client.close(); 			//		SABER: siempre se cierra al socketclient tanto desde el server como 
											//				acá en el cli q se cierra a si mismo digamos 
		} catch (IOException e) {
			e.printStackTrace();
			
			//Logger
		}
		
		
		
	}
	
	
	
	
}

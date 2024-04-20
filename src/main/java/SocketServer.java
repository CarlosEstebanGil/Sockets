import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Basicamente espera en un puerto con accept ( pero a su vez en un while tru infitivo xa aceptar varias 
 * peticiones(c/pet en una conn)
 * 
 *  la idea es que corra como un demonio servidor escuchando siempre en ese puerto y que tome la ref de c/cli xa leer su 
 *  solicitud y enviarle un rta a ese cli socket ( en este caso es solo recibir un str y devolver otro )
 */
public class SocketServer {

	public static void main(String[] args) {
		
		ServerSocket socket_server =null;
		Socket socket_client = null;
		
		final int PUERTO = 5000;
		
		DataInputStream in;
		DataOutputStream out;
		
		try {
			
			socket_server = new ServerSocket(PUERTO);
			System.out.println("Servidor iniciado");
		
			while(true) {
				socket_client= socket_server.accept(); // SABER --> QUEDA FRENADO ACA A LA ESPERA!! LAS LINEAS SIG NO SE EJECUTAN
				
				System.out.println(" cliente conectado ");
				
				in = new DataInputStream(socket_client.getInputStream());
				out = new DataOutputStream(socket_client.getOutputStream());
				
				String mensaje = in.readUTF();		// SABER -> ACA TMB SE QDA FRENADO HASTA LEER EFECTIVAMENTE DATOS Q HAYAN DE IN
				
				System.out.println("recibido: " + mensaje);
				
				out.writeUTF("rta desde el servidor al msge "+ mensaje  );
				
				socket_client.close();
				System.out.println(" cliente ya atendido y desconectado por el servidor ");
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

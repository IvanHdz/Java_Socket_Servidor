
/**
 *
 * @author Jesus Ivan
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {

    private final int puerto;
    private ServerSocket servidor; //Servidor
    private Socket conexion; //Cliente
    String[] cadena;

    /* Crea nuevas instancias de ServidorTcp*/
    public ServidorTCP(int puerto) {
        this.puerto = puerto;
        cadena = new String[]{"El conocimiento es libre.",
            "Somos Anónimos",
            "Somos Legión",
            "No perdonamos",
            "No olvidamos",
            "Viruz Blog"};
    }

    public void iniciarConexion() {
        try {
            //La oreja del servidor estara pegada al (puerto).
            servidor = new ServerSocket(puerto);
            System.out.println("Espera un Cliente");
            /*.accept() es para inicializar el Server al puerto.
           Espera aque un cliente Llegue (Mientras imprimira Espera un Cliente)*/
            conexion = servidor.accept();
            System.out.println("¡¡¡Un Cliente llego!!!");
        } catch (IOException e) {
            System.out.println("Erros de Conexion");
            System.exit(0);
        }
    }

    public void comunicarConCliente() {
        //Como se Ocupan Flujos se Necesita try{} catch(){}.
        try {
            //Flujo de entrada desde el cliente.
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(conexion.getInputStream()));
            //Flujo de salida hacia el cliente.
            BufferedWriter salida = new BufferedWriter(
                    new OutputStreamWriter(
                            conexion.getOutputStream()));

            //Lee una peticion del cliente.
            String lectura = entrada.readLine();
            System.out.println("El cliente es: " + lectura);

            //Enviar informacion al cliente.
            for (String cadena1 : cadena) {
                //"\n" salto de Linea.
                salida.write(cadena1 + "\n");
                //.flush Se libera el flujo(No espera que se llene la Antememoria).
                salida.flush();
            }
            //Agregamos otro String al Socket.
            salida.write("Conexion finalizada por el servidor\n");
            salida.flush();
            salida.close();
            entrada.close();
            conexion.close();

        } catch (IOException e) {
            System.out.println("Error de conexion");
        }
    }
}

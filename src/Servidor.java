
/**
 *
 * @author Jesus Ivan
 */
public class Servidor {

    public static void main(String[] args) {
        //Escucha en el puerto 3000.
        ServidorTCP t = new ServidorTCP(3000);
        //iniciamos la conexion
        t.iniciarConexion();
        // Y nos comunicamos con el cliente.
        t.comunicarConCliente();
    }
}

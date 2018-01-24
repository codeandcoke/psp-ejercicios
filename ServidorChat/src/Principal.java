import java.io.IOException;

/**
 * Created by dam on 24/01/18.
 */
public class Principal {

    public static void main(String args[]) {

        Servidor servidor = new Servidor(5555);
        try {
            servidor.conectar();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        while (servidor.estaConectado()) {
            try {
                servidor.aceptarConexion();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}

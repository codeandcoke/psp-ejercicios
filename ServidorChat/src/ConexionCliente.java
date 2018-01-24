import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ConexionCliente extends Thread {

    private Socket socket;
    private PrintWriter salida;
    private BufferedReader entrada;
    private Servidor servidor;

    public ConexionCliente(Servidor servidor, Socket socket) throws IOException {
        this.servidor = servidor;
        this.socket = socket;
        salida = new PrintWriter(socket.getOutputStream(), true);
        entrada = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
    }

    @Override
    public void run() {

        salida.println("Bienvenido al Chat");
        salida.println("Puedes enviar mensajes de texto");

        String mensaje = null;
        try {
            while ((mensaje = entrada.readLine()) != null) {
                servidor.enviarATodos(mensaje);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void enviar(String mensaje) {
        salida.println(mensaje);
    }
}

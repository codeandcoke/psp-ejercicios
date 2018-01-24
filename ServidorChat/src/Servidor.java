import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

    private ArrayList<ConexionCliente> clientes;
    private ServerSocket socketServidor;
    private int puerto;

    public Servidor(int puerto) {
        this.puerto = puerto;
        clientes = new ArrayList<>();
    }

    public void conectar() throws IOException {
        socketServidor = new ServerSocket(puerto);
    }

    public void desconectar() throws IOException {
        socketServidor.close();
    }

    public void enviarATodos(String mensaje) {

        for (ConexionCliente cliente : clientes) {
            cliente.enviar(mensaje);
        }
    }

    public void aceptarConexion() throws IOException {

        Socket socketCliente = socketServidor.accept();
        ConexionCliente conexionCliente =
                new ConexionCliente(this, socketCliente);
        clientes.add(conexionCliente);
        conexionCliente.start();
    }

    public boolean estaConectado() {
        return !socketServidor.isClosed();
    }
}

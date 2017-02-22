package com.sfaci.examenpsp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by dam on 22/02/17.
 */
public class ConexionCliente extends Thread {

    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;
    private Servidor servidor;

    public ConexionCliente(Socket socket, Servidor servidor) throws IOException {

        this.servidor = servidor;
        this.socket = socket;
        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        salida = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {

        for (String producto : servidor.getProductos()) {
            salida.println(producto);
        }

        try {
            while (true) {
                String orden = entrada.readLine();
                String partes[] = orden.split("#");
                switch(partes[0]) {
                    case "anadir":
                        servidor.anadirProducto(partes[1]);
                        break;
                    case "eliminar":
                        servidor.eliminarProducto(partes[1],
                                socket.getInetAddress().getHostAddress());
                        break;
                    case "cantidad":
                        salida.println(servidor.getProductos().size());
                        break;
                    case "salir":
                        socket.close();
                        return;
                    default:
                        break;
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}

package com.sfaci.servidormensajeria;

import java.io.IOException;
import java.net.Socket;

/**
 * Clase principal
 */
public class Main {

    public static void main(String args[]) {

        Servidor servidor = new Servidor(5555);
        try {
            servidor.iniciar();
            while (servidor.estaConectado()) {
                Socket socket = servidor.aceptarConexion();
                Cliente cliente = new Cliente(socket);
                servidor.anadirCliente(cliente);
                ConexionCliente conexionCliente = new ConexionCliente(cliente);
                conexionCliente.start();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();

        }

    }
}

package com.sfaci.servidormensajeria;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * El servidor
 */
public class Servidor {

    private ServerSocket socket;
    private int puerto;
    private ArrayList<Cliente> clientes;

    public Servidor(int puerto) {
        this.puerto = puerto;
    }

    public void iniciar() throws IOException {
        clientes = new ArrayList<>();
        socket = new ServerSocket(puerto);
    }

    public void desconectar() throws IOException {
        socket.close();
    }

    public Socket aceptarConexion() throws IOException {
        return socket.accept();
    }

    public boolean estaConectado() {
        if (socket != null)
            return !socket.isClosed();

        return false;
    }

    public void anadirCliente(Cliente cliente) {
        clientes.add(cliente);
    }
}

package com.sfaci.examenpsp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by dam on 22/02/17.
 */
public class Servidor {

    private int puerto;
    private ServerSocket socket;
    private boolean conectado;
    private ArrayList<String> mensajes;

    public Servidor(int puerto) {
        this.puerto = puerto;
        conectado = false;

        mensajes = new ArrayList<>();
    }

    public void iniciar() throws IOException {
        socket = new ServerSocket(puerto);
        conectado = true;
    }

    public boolean estaConectado() {
        return conectado;
    }

    public void desconectar() throws IOException {
        socket.close();
        conectado = false;
    }

    public Socket aceptarConexion() throws IOException {
        return socket.accept();
    }

    public void dejarMensaje(String mensaje) {
        mensajes.add(mensaje);
    }

    public ArrayList<String> getMensajes() {
        return mensajes;
    }
}

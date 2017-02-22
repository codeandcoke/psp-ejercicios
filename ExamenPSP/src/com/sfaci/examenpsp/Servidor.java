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
    private ArrayList<String> productos;
    private ArrayList<String> clientesQueEliminan;

    public Servidor(int puerto) {
        this.puerto = puerto;
        conectado = false;

        productos = new ArrayList<>();
        productos.add("Colacao");
        productos.add("Conguitos");
        productos.add("M&Ms");

        clientesQueEliminan = new ArrayList<>();
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

    public void eliminarProducto(String producto, String ip) {
        productos.remove(producto);
        clientesQueEliminan.add(ip);
    }

    public void anadirProducto(String producto) {
        productos.add(producto);
    }

    public ArrayList<String> getProductos() {
        return productos;
    }
}

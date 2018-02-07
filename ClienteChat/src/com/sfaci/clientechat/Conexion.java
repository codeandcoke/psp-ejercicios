package com.sfaci.clientechat;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by dam on 7/02/18.
 */
public class Conexion extends SwingWorker<Void, Void> {

    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;
    private String host;
    private int puerto;

    public Conexion(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    @Override
    protected Void doInBackground() throws Exception {

        conectar();

        // Iniciar bucle para recibir mensajes
        String mensaje = null;
        while ((mensaje = entrada.readLine()) != null) {
            firePropertyChange("mensaje", null, mensaje);
        }

        return null;
    }

    private void conectar() throws IOException {
        // Conectar
        socket = new Socket(host, puerto);
        // Inicializar entrada y salida
        entrada = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        salida = new PrintWriter(socket.getOutputStream(), true);
    }

    public void enviar(String mensaje) {
        salida.println(mensaje);
    }

    public void desconectar() {

    }
}

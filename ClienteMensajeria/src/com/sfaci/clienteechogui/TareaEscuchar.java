package com.sfaci.clienteechogui;

import javax.swing.*;
import java.io.BufferedReader;
import java.net.Socket;

/**
 * Created by dam on 25/01/17.
 */
public class TareaEscuchar extends SwingWorker<Void, Void> {

    private Socket socket;
    private BufferedReader entrada;

    public TareaEscuchar(Socket socket, BufferedReader entrada) {
        this.socket = socket;
        this.entrada = entrada;
    }

    @Override
    protected Void doInBackground() throws Exception {

        while (socket.isConnected()) {
            String mensaje = entrada.readLine();
            if (mensaje != null)
                firePropertyChange("mensaje", null, mensaje);
        }

        return null;
    }
}

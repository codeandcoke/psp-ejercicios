package com.sfaci.clientemensajeriagui;

import javax.swing.*;
import java.io.BufferedReader;
import java.net.Socket;
import java.util.Arrays;

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
            if (mensaje != null) {
                if (mensaje.startsWith("/")) {
                    String[] partes = mensaje.split("#");
                    switch (partes[0]) {
                        case "/nicks":
                            firePropertyChange("nicks", null, partes);
                            break;
                        default:
                            break;
                    }
                }
                else {
                    firePropertyChange("mensaje", null, mensaje);
                }
            }
        }

        return null;
    }
}

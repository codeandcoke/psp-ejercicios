package com.sfaci.servidormensajeria;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * En esta clase se guarda la información de cada uno de los clientes
 * conectados al servidor
 *
 * @author Santiago Faci
 * @version 1.0
 * @since 8 Febrero 2017
 */
public class Cliente {

    private Socket socket;
    PrintWriter salida;
    BufferedReader entrada;

    public Cliente(Socket socket) throws IOException {
        this.socket = socket;

        iniciar();
    }

    /**
     * Inicia
     * @throws IOException
     */
    private void iniciar() throws IOException {

        entrada = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        salida = new PrintWriter(socket.getOutputStream(), true);
    }

    public boolean estaConectado() {
        return socket.isConnected();
    }
}

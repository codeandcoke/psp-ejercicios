package com.sfaci.servidorecho;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by dam on 11/01/17.
 */
public class Main {

    public static void main(String args[]) {

        final int PUERTO = 5555;
        boolean conectado = true;

        try {
            ServerSocket socketServidor = new ServerSocket(PUERTO);

            while (conectado) {
                Socket socketCliente = socketServidor.accept();
                ConexionCliente conexionCliente =
                        new ConexionCliente(socketCliente);
                conexionCliente.start();
            }

            socketServidor.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

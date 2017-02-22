package com.sfaci.examenpsp;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by dam on 22/02/17.
 */
public class Main {

    public static void main(String args[]) {

        try {
            Servidor servidor = new Servidor(5555);
            servidor.iniciar();
            while (servidor.estaConectado()) {
                Socket socketCliente = servidor.aceptarConexion();
                ConexionCliente conexionCliente =
                        new ConexionCliente(socketCliente, servidor);
                conexionCliente.start();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}

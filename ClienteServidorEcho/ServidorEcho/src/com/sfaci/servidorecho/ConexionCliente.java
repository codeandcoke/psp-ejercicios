package com.sfaci.servidorecho;

import java.io.*;
import java.net.Socket;

/**
 * Created by dam on 18/01/17.
 */
public class ConexionCliente extends Thread {

    private Socket socketCliente;
    private PrintWriter salida;
    private BufferedReader entrada;

    public ConexionCliente(Socket socketCliente) throws IOException {
        this.socketCliente = socketCliente;

        salida = new PrintWriter(socketCliente.getOutputStream(), true);
        entrada = new BufferedReader(new InputStreamReader(
                socketCliente.getInputStream()));
    }

    @Override
    public void run() {

        salida.println("Bienvenido a mi servidor de echo");
        salida.println("Para salir enviame un '.'");

        try {
            String mensaje = null;
            while ((mensaje = entrada.readLine()) != null) {

                if (mensaje.equals(".")) {
                    salida.println("Bye");
                    socketCliente.close();
                    return;
                }

                salida.println(mensaje);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

package com.sfaci.clienteecho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by dam on 18/01/17.
 */
public class Main {

    public static void main(String args[]) {

        final String host = "localhost";
        final int puerto = 5555;

        try {
            Socket socket = new Socket(host, puerto);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader teclado = new BufferedReader(new InputStreamReader(
                    System.in));

            System.out.println(entrada.readLine());
            System.out.println(entrada.readLine());

            String mensaje = null;
            while ((mensaje = teclado.readLine()) != null) {

                salida.println(mensaje);
                if (mensaje.equals(".")) {
                    System.out.println(entrada.readLine());
                    socket.close();
                    return;
                }

                System.out.println(entrada.readLine());
            }

        } catch (UnknownHostException uhe) {
            uhe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}

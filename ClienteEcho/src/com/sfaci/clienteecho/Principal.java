package com.sfaci.clienteecho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by dam on 23/01/18.
 */
public class Principal {

    public static void main(String args[]) {

        Socket socket;
        PrintWriter salida;
        BufferedReader entrada;
        BufferedReader teclado;
        String host = "localhost";
        int puerto = 7777;

        try {
            socket = new Socket(host, puerto);
            salida = new PrintWriter(socket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            teclado = new BufferedReader(new InputStreamReader(System.in));

            System.out.println(entrada.readLine());
            System.out.println(entrada.readLine());

            String texto = null;
            while ((texto = teclado.readLine()) != null) {
                // Envio al servidor el texto introducido por teclado
                salida.println(texto);
                // Leo por pantalla la respuesta del servidor
                System.out.println(entrada.readLine());
            }

        } catch (UnknownHostException uhe) {
            uhe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

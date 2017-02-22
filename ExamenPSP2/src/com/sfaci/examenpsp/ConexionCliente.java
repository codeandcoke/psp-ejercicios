package com.sfaci.examenpsp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by dam on 22/02/17.
 */
public class ConexionCliente extends Thread {

    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;
    private Servidor servidor;

    public ConexionCliente(Socket socket, Servidor servidor) throws IOException {

        this.servidor = servidor;
        this.socket = socket;
        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        salida = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {

        int contador = 0;

        try {
            while (true) {
                String orden = entrada.readLine();
                String partes[] = orden.split("#");
                switch(partes[0]) {
                    case "escribir":
                        if (contador == 2) {
                            salida.println("limite superado");
                            continue;
                        }
                        servidor.dejarMensaje(partes[1] + "#" + partes[2]);
                        contador++;
                        break;
                    case "leer":
                        String usuario = partes[1];
                        for (String mensaje : servidor.getMensajes()) {
                            String trozos[] = mensaje.split("#");
                            if (trozos[0].equals(usuario)) {
                                salida.println(trozos[1]);
                            }
                        }
                        break;
                    case "pirarme":
                        socket.close();
                        return;
                    default:
                        break;
                }

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}

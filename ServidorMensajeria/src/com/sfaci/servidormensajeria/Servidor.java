package com.sfaci.servidormensajeria;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Servidor de chat con soporte para múltiples clientes
 *
 * @author Santiago Faci
 * @version 1.0
 * @since 8 Febrero 2017
 */
public class Servidor {

    private ServerSocket socket;
    private int puerto;
    private ArrayList<Cliente> clientes;

    public Servidor(int puerto) {
        this.puerto = puerto;
    }

    /**
     * Inicia el servidor en el puerto correspondiente
     * @throws IOException En caso de que no se puede iniciar
     */
    public void iniciar() throws IOException {
        clientes = new ArrayList<>();
        socket = new ServerSocket(puerto);
    }

    /**
     * Desconecta el servidor
     * @throws IOException
     */
    public void desconectar() throws IOException {
        socket.close();
    }

    /**
     * Acepta una conexión de un cliente
     * @return El socket del cliente que ha conectado
     * @throws IOException
     */
    public Socket aceptarConexion() throws IOException {
        return socket.accept();
    }

    /**
     * Comprueba si el servidor esta conectado
     * @return verdadero si esta conectado, falso en cualquier otro caso
     */
    public boolean estaConectado() {
        if (socket != null)
            return !socket.isClosed();

        return false;
    }

    /**
     * Añade un cliente a la lista de clientes conectados al servidor
     * @param cliente El cliente que acaba de conectar
     */
    public void anadirCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    /**
     * Envia un mensaje a un Cliente
     * @param mensaje El mensaje a enviar
     * @param cliente El cliente al que se envia
     */
    public void enviar(String mensaje, Cliente cliente) {

        cliente.salida.println(mensaje);
    }

    /**
     * Recibe un mensaje de un cliente
     * @param cliente El cliente del que se quiere recibir el mensaje
     * @return El mensaje del cliente
     * @throws IOException
     */
    public String recibir(Cliente cliente) throws IOException {
        return cliente.entrada.readLine();
    }

    /**
     * Envia un mensaje a todos los clientes conectados
     * @param mensaje El mensaje
     */
    public void enviarATodos(String mensaje, String nickRemitente) {

        for (Cliente cliente : clientes) {
            if (!cliente.getNick().equals(nickRemitente))
                enviar(mensaje, cliente);
        }
    }

    public void enviarNicksATodos() {

        /*
        Forma una cadena de texto con todos los nicks de los
        clientes conectados separados por el carácter #
         */
        StringBuilder sb = new StringBuilder();
        sb.append("/nicks");
        for (Cliente cliente : clientes) {
            sb.append("#");
            sb.append(cliente.getNick());
        }

        /*
        Envia la lista de nicks a todos los clientes conectados
         */
        for (Cliente cliente : clientes) {
            enviar(sb.toString(), cliente);
        }
    }
}

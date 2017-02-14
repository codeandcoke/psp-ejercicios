package com.sfaci.servidormensajeria;

import java.io.IOException;

/**
 * Clase que atiende las conexiones con cada cliente
 */
public class ConexionCliente extends Thread {

    private Cliente cliente;
    private Servidor servidor;

    public ConexionCliente(Servidor servidor, Cliente cliente) {
        this.servidor = servidor;
        this.cliente = cliente;
    }

    @Override
    public void run() {

        servidor.enviar("Hola, bienvenido al mejor chat del mundo", cliente);

        try {
            String mensaje = null;
            while (cliente.estaConectado()) {
                mensaje = servidor.recibir(cliente);
                servidor.enviarATodos(mensaje);
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

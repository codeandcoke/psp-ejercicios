package com.sfaci.clientechat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.Socket;

/**
 * Created by dam on 6/02/18.
 */
public class ClienteChatController implements ActionListener,
        KeyListener {

    ClienteChat view;
    Conexion conexion;

    public ClienteChatController(ClienteChat view) {
        this.view = view;
        conexion = new Conexion("localhost", 5555);

        view.btConectar.addActionListener(this);
        view.tfMensaje.addKeyListener(this);
    }

    private void conectar() {

        view.lbEstado.setText("Conectando . . .");
        conexion.execute();
        conexion.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent event) {
                if (event.getPropertyName().equals("mensaje")) {
                    String mensaje = (String) event.getNewValue();
                    view.taChat.append(mensaje + "\n");
                }
            }
        });
        view.btConectar.setEnabled(false);
        view.lbEstado.setText("Conectado");
    }

    private void desconectar() {

    }

    private void salir() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();
        switch(comando) {
            case "Conectar":
                conectar();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String mensaje = view.tfMensaje.getText();
            conexion.enviar(mensaje);
            view.tfMensaje.setText("");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

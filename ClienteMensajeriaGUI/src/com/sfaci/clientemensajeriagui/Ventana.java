package com.sfaci.clientemensajeriagui;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.sfaci.clientemensajeriagui.util.Constantes.PUERTO;

/**
 * Created by dam on 23/01/17.
 */
public class Ventana implements KeyListener {
    private JTextField tfTexto;
    private JPanel panel1;
    private JTextArea taMensajes;
    private JList lNicks;
    private DefaultListModel modelNicks;

    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;

    public Ventana() {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        tfTexto.addKeyListener(this);
        tfTexto.requestFocus();

        modelNicks = new DefaultListModel();
        lNicks.setModel(modelNicks);

        conectar();
    }

    private void conectar() {

        JDatos jDatos = new JDatos();
        if (jDatos.mostrarDialogo() == JDatos.Accion.CANCELAR)
            System.exit(0);

        String servidor = jDatos.getServidor();
        String nick = jDatos.getNick();

        try {
            socket = new Socket(servidor, PUERTO);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new PrintWriter(socket.getOutputStream(), true);

            TareaEscuchar tarea = new TareaEscuchar(socket, entrada);
            tarea.addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if (evt.getPropertyName().equals("mensaje")) {
                        String mensaje = (String) evt.getNewValue();
                        taMensajes.append(mensaje + "\n");
                    }
                    else if (evt.getPropertyName().equals("nicks")) {
                        String[] nicks = (String[]) evt.getNewValue();
                        modelNicks.removeAllElements();
                        for (int i = 1; i < nicks.length; i++)
                            modelNicks.addElement(nicks[i]);
                    }
                }
            });
            tarea.execute();

            salida.println(nick);

        } catch (UnknownHostException uhe) {
            uhe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    private void enviarMensaje() {

        String mensaje = tfTexto.getText();
        salida.println(mensaje);
        taMensajes.append("> " + mensaje + "\n");
        tfTexto.setText("");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            enviarMensaje();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

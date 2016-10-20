package com.sfaci.ejemploswingworker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by profesor on 05/10/2016.
 *
 */
public class Ventana implements ActionListener {
    private JPanel panel1;
    private JTextField tfTiempo;
    private JProgressBar pbProgreso;
    private JButton btIniciar;
    private JButton btParar;

    private Temporizador temporizador;

    public Ventana() {

        btIniciar.addActionListener(this);
        btParar.addActionListener(this);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String comando = actionEvent.getActionCommand();

        switch (comando) {
            case "Iniciar":
                int tiempo = Integer.parseInt(tfTiempo.getText());

                temporizador = new Temporizador(tiempo);
                temporizador.addPropertyChangeListener(
                        new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {

                        switch (propertyChangeEvent.getPropertyName()) {
                            case "progress":
                                int progreso = (Integer) propertyChangeEvent.getNewValue();
                                pbProgreso.setValue(progreso);
                                break;
                            case "fin":
                                pbProgreso.setValue(0);
                                break;
                            default:
                                break;
                        }
                    }
                });
                temporizador.execute();
                btIniciar.setActionCommand("Pausar");
                break;
            case "Parar":
                // TODO Parar el temporizador
                temporizador.terminar();
                btIniciar.setActionCommand("Iniciar");
                break;
            case "Continuar":
                temporizador.continuar();
                btIniciar.setActionCommand("Pausar");
                break;
            case "Pausar":
                temporizador.pausar();
                btIniciar.setActionCommand("Continuar");
                break;
            default:
                break;
        }
    }
}

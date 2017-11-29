package com.sfaci.jdespertador;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

public class PanelAlarma extends Observable implements ActionListener {
    public JPanel panel;
    private JProgressBar pbProgreso;
    private JLabel lbAlarma;
    private JButton btCancelar;

    private Alarma alarma;
    private Observer observador;

    public PanelAlarma(int hora, int minuto, String mensaje) {

        btCancelar.addActionListener(this);

        alarma = new Alarma(hora, minuto, mensaje);
        alarma.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("alarma")) {
                    JOptionPane.showMessageDialog(null,
                            alarma.getMensaje(), "Alarma",
                            JOptionPane.INFORMATION_MESSAGE);
                    pbProgreso.setIndeterminate(false);
                    pbProgreso.setValue(100);
                }
                else if (evt.getPropertyName().equals("alarma_cancelada")) {
                    notifyObservers();
                    pbProgreso.setIndeterminate(false);
                    pbProgreso.setBorder(new LineBorder(Color.RED));
                    pbProgreso.setValue(0);
                }
            }
        });
        alarma.execute();
        pbProgreso.setIndeterminate(true);
    }

    @Override
    public void addObserver(Observer observador) {
        this.observador = observador;
    }

    @Override
    public void notifyObservers() {
        if (observador != null)
            observador.update(this, "alarma_cancelada");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        alarma.cancel(false);
    }
}

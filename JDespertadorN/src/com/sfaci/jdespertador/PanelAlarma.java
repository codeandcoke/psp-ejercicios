package com.sfaci.jdespertador;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PanelAlarma {
    public JPanel panel;
    private JProgressBar pbProgreso;
    private JLabel lbAlarma;
    private JButton btCancelar;

    private Alarma alarma;

    public PanelAlarma(int hora, int minuto, String mensaje) {

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
            }
        });
        alarma.execute();
        pbProgreso.setIndeterminate(true);
    }
}

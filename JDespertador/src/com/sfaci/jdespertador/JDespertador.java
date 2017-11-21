package com.sfaci.jdespertador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by dam on 25/10/17.
 */
public class JDespertador implements ActionListener {
    private JPanel panel;
    private JTextField tfMensaje;
    private JTextField tfHora;
    private JTextField tfMinuto;
    private JButton btProgramar;
    private JButton btCancelar;
    private JLabel lbEstado;

    public JDespertador() {
        JFrame frame = new JFrame("JDespertador");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        btProgramar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int hora = Integer.parseInt(tfHora.getText());
        int minuto = Integer.parseInt(tfMinuto.getText());
        String mensaje = tfMensaje.getText();

        Alarma alarma = new Alarma(hora, minuto, mensaje);
        alarma.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("alarma")) {
                    JOptionPane.showMessageDialog(null,
                            evt.getNewValue().toString(), "Alarma",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        alarma.execute();
    }
}

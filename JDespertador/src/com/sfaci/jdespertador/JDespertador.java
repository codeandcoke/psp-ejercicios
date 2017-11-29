package com.sfaci.jdespertador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by dam on 25/10/17.
 */
public class JDespertador implements ActionListener, Observer {
    private JPanel panel;
    private JTextField tfHora;
    private JTextField tfMinuto;
    private JTextField tfMensaje;
    private JButton btProgramar;
    private JPanel panelAlarmas;
    private JLabel lbEstado;

    public JDespertador() {

        inicializar();

        JFrame frame = new JFrame("JDespertador");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void inicializar() {

        panelAlarmas.setLayout(new BoxLayout(panelAlarmas, BoxLayout.Y_AXIS));

        btProgramar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        PanelAlarma panelAlarma = new PanelAlarma(
                Integer.parseInt(tfHora.getText()),
                Integer.parseInt(tfMinuto.getText()),
                tfMensaje.getText());
        panelAlarma.addObserver(this);

        panelAlarmas.add(panelAlarma.panel);
        panelAlarmas.revalidate();
    }

    @Override
    public void update(Observable observable, Object parametro) {
        String mensaje = (String) parametro;

        switch (mensaje) {
            case "alarma_cancelada":
                JOptionPane.showMessageDialog(null,
                        "Se ha cancelado la alarma", "Alarma Cancelada",
                        JOptionPane.WARNING_MESSAGE);
                break;
            default:
                break;
        }
    }
}

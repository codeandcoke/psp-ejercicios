package com.sfaci.clienteechogui;

import javax.swing.*;
import java.awt.event.*;

public class JDatos extends JDialog {
    private JPanel contentPane;
    private JButton btOk;
    private JButton btCancelar;
    private JTextField tfServidor;
    private JTextField tfPuerto;

    private String servidor;
    private int puerto;
    private Accion accion;

    public enum Accion {
        OK, CANCELAR;
    }

    public JDatos() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btOk);
        pack();
        setLocationRelativeTo(null);

        btOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        btCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public Accion mostrarDialogo() {

        setVisible(true);
        return accion;
    }

    public String getServidor() {
        return servidor;
    }

    public int getPuerto() {
        return puerto;
    }

    private void onOK() {

        servidor = tfServidor.getText();
        puerto = Integer.parseInt(tfPuerto.getText());
        accion = Accion.OK;
        dispose();
    }

    private void onCancel() {
        accion = Accion.CANCELAR;
        dispose();
    }
}

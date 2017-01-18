package com.sfaci.uidinamica.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by dam on 24/10/16.
 */
public class Ventana implements ActionListener, KeyListener {

    private JPanel panel1;
    private JButton btAnadir;
    private JTabbedPane panelAlarmas;
    private JLabel lbEstado;
    private JTextField tfNombreAlarma;

    private static final Logger logger = LogManager.getLogger(Ventana.class);

    public Ventana() {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        btAnadir.addActionListener(this);
        tfNombreAlarma.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PanelAlarma panelAlarma = new PanelAlarma();
        panelAlarmas.addTab(tfNombreAlarma.getText(), panelAlarma.panel);
        tfNombreAlarma.setText("");
        tfNombreAlarma.requestFocus();
        panelAlarmas.revalidate();

        logger.trace("Nueva Pestaña");

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            btAnadir.doClick();
            logger.trace("Pulsada tecla ENTER");
        }
    }
}

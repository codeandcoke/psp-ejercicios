package com.sfaci.clientechat;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dam on 6/02/18.
 */
public class ClienteChat {
    public JPanel panel1;
    public JTextField tfMensaje;
    public JLabel lbEstado;
    public JButton btConectar;
    public JButton btDesconectar;
    public JButton btSalir;
    public JTextArea taChat;

    public ClienteChat() {
        JFrame frame = new JFrame("ClienteChat");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(new Dimension(800, 600));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

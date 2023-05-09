package GUI;

import java.awt.*;
import javax.swing.*;

public class UrnaLayout extends JFrame {
    private JPanel panel;
    private JLabel lbl;
    private JTextField tf;
    private JButton btn;

    public UrnaLayout(){
        // Cria um painel
        panel = new JPanel();
        panel.setBackground(Color.black);

        //Cria um componente label
        lbl = new JLabel();
        lbl.setText("DIGITE A SUA MATRÍCULA");
        lbl.setForeground(Color.orange);
        lbl.setFont(new Font("Verdana", Font.BOLD, 20));

        // Cria um campo de texto(input) com 12 caracteres
        tf = new JTextField(12);
        tf.setBackground(Color.black);
        tf.setForeground(Color.CYAN);
        tf.setFont(new Font("Lucida Fax", Font.PLAIN, 20));

        // Cria botão
        btn = new JButton();
        btn.setBackground(Color.orange);
        btn.setForeground(Color.black);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setText("ENVIAR");
        btn.setFont(new Font("Lucida Fax", Font.PLAIN, 12));


        //Frame
        this.setSize(330,200);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Urna Eletronica");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ///Adicionar conteiner ao frame
        this.setContentPane(panel);

        //Adicionar elementos ao painel
        panel.add(lbl);
        panel.add(tf);
        panel.add(btn);

        //Tornar visivel
        panel.setVisible(true);
        this.setVisible(true);

    }//Fim do construtor

}

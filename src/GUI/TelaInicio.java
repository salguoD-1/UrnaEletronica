package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicio extends JFrame implements ActionListener {
    private JPanel inicio;
    private JLabel bemVindoL;
    private JLabel instrucoes;
    private JButton iniciarVotacaoB;

    public TelaInicio() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(330, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Tela Inicial");

        inicio = new JPanel();
        inicio.setBackground(Color.BLACK);
        add(inicio);

        bemVindoL = new JLabel("BEM-VINDO! ");
        bemVindoL.setForeground(Color.ORANGE);
        bemVindoL.setFont(new Font("Lucida Fax", Font.BOLD, 30));
        inicio.add(bemVindoL);

        instrucoes = new JLabel("> PARA IR À ÁREA DE VOTAÇÃO APERTE O BOTÃO ABAIXO");
        instrucoes.setForeground(Color.CYAN);
        instrucoes.setFont(new Font("Lucida Fax", Font.BOLD, 9));
        inicio.add(instrucoes);

        iniciarVotacaoB = new JButton("INICIAR VOTAÇÃO");
        iniciarVotacaoB.setBackground(Color.ORANGE);
        iniciarVotacaoB.setForeground(Color.BLACK);
        iniciarVotacaoB.setFocusPainted(false);
        iniciarVotacaoB.addActionListener(this);
        inicio.add(iniciarVotacaoB);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == iniciarVotacaoB) {
            UrnaLayout urna = new UrnaLayout();
        }
    }
}

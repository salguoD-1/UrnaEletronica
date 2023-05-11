package GUI;

import arquivo.Arquivo;
import criptografia.HashGenerator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.swing.*;

public class UrnaLayout extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel lbl;
    private JTextField tf;
    private JButton btn;
    private Arquivo arquivo;
    private static final String VOTOS_FILENAME = "votos.txt";

    public UrnaLayout() {
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
        btn.addActionListener(this);

        //Frame
        this.setSize(330, 200);
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

    // Analisa se o eleitor existe no arquivo eleitores.txt e se está validado com a criptografia
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btn) {
            arquivo = new Arquivo();
            if (arquivo.validarMatricula(tf.getText())) {
                JOptionPane.showMessageDialog(null, "Boa votação!");
                telaVotar();
            } else {
                JOptionPane.showMessageDialog(null, "Usuário inválido!");
            }
        }
    }// fim da ação do botão

    public void telaVotar() {
        Font buttonsFonte = new Font("Verdana", Font.PLAIN, 20);

        // Cria a tela com os radio buttons e o botão votar
        JFrame telaVotacao = new JFrame("Votação");
        telaVotacao.setSize(650, 300);
        telaVotacao.setResizable(false);
        telaVotacao.setLocationRelativeTo(null);

        //Panel
        JPanel panelVotacao = new JPanel();
        panelVotacao.setBackground(Color.black);

        //Label
        JLabel perguntaLabel = new JLabel();
        perguntaLabel.setText("QUAL É O SEU SISTEMA OPERACIONAL PREFERIDO?");
        perguntaLabel.setForeground(Color.orange);
        perguntaLabel.setFont(new Font("Lucida Fax", Font.BOLD, 22));

        //Botões
        ButtonGroup grupoBotoes = new ButtonGroup();
        JRadioButton linuxButton = new JRadioButton("Linux");
        JRadioButton windowsButton = new JRadioButton("Windows");
        JRadioButton macButton = new JRadioButton("Mac");
        JRadioButton nuloButton = new JRadioButton("Branco/Nulo");
        JButton votarButton = new JButton("VOTAR");

        grupoBotoes.add(linuxButton);
        grupoBotoes.add(windowsButton);
        grupoBotoes.add(macButton);
        grupoBotoes.add(nuloButton);

        //Estilo dos botões
        linuxButton.setForeground(Color.cyan);
        linuxButton.setBackground(Color.black);
        linuxButton.setFont(buttonsFonte);
        //Windows
        windowsButton.setBackground(Color.black);
        windowsButton.setForeground(Color.green);
        windowsButton.setFont(buttonsFonte);
        //Mac
        macButton.setBackground(Color.black);
        macButton.setForeground(Color.red);
        macButton.setFont(buttonsFonte);
        //Nulo
        nuloButton.setBackground(Color.black);
        nuloButton.setForeground(Color.white);
        nuloButton.setFont(buttonsFonte);
        //Votar
        votarButton.setBackground(Color.orange);
        votarButton.setForeground(Color.black);
        votarButton.setFont(new Font("Lucida Fax", Font.PLAIN, 12));
        votarButton.setFocusPainted(false);


        // Add elementos
        panelVotacao.add(perguntaLabel);
        panelVotacao.add(linuxButton);
        panelVotacao.add(windowsButton);
        panelVotacao.add(macButton);
        panelVotacao.add(nuloButton);
        panelVotacao.add(votarButton);

        //Adicionar panel ao frame
        telaVotacao.setContentPane(panelVotacao);

        //Tornar Visivel
        panelVotacao.setVisible(true);
        telaVotacao.setVisible(true);

        panel.removeAll();
        panel.revalidate();
        panel.repaint();

        telaVotacao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Computa o voto seguido da hash no arquivo votos.txt
        votarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String escolha = "";
                if (linuxButton.isSelected()) {
                    escolha = "Linux";
                } else if (windowsButton.isSelected()) {
                    escolha = "Windows";
                } else if (macButton.isSelected()) {
                    escolha = "Mac";
                } else if (nuloButton.isSelected()) {
                    escolha = "Branco/Nulo";
                }

                if (!escolha.isEmpty()) {
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(VOTOS_FILENAME, true));
                        writer.write(escolha + " " + HashGenerator.generateHash(tf.getText()));
                        writer.newLine();
                        writer.close();
                        JOptionPane.showMessageDialog(null, "Voto registrado com sucesso!");
                        telaVotacao.dispose();
                        telaEncerramento();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao gravar o voto.");
                        ex.printStackTrace();
                    } catch (NoSuchAlgorithmException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Escolha uma opção antes de votar.");
                }
            }
        });
    }//Fim telaVotar()

    public void telaEncerramento() {
        //Criar novo frame
        JFrame encerramentoFrame = new JFrame();
        encerramentoFrame.setResizable(false);
        encerramentoFrame.setSize(330, 200);
        encerramentoFrame.setLocationRelativeTo(null);

        //Criar novo panel
        JPanel encerramentoPanel = new JPanel();
        encerramentoPanel.setBackground(Color.black);

        //Criar label
        JLabel encerramentoTexto = new JLabel();
        encerramentoTexto.setText("OBRIGADO POR VOTAR!");
        encerramentoTexto.setFont(new Font("Lucida Fax", Font.BOLD, 22));
        encerramentoTexto.setForeground(Color.orange);

        //Criar botão
        JButton voltarBotao = new JButton();
        voltarBotao.setText("VOLTAR");
        voltarBotao.setFocusPainted(false);
        voltarBotao.setBackground(Color.ORANGE);
        voltarBotao.setForeground(Color.BLACK);
        voltarBotao.setBorderPainted(false);

        //Adicionar elementos ao painel
        encerramentoPanel.add(encerramentoTexto);
        encerramentoPanel.add(voltarBotao);

        //Adicionar panel ao frame
        encerramentoFrame.setContentPane(encerramentoPanel);

        //Tornar visivel
        encerramentoFrame.setVisible(true);
        encerramentoPanel.setVisible(true);

        voltarBotao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                encerramentoFrame.dispose(); // fecha a telaEncerramento
                dispose(); // fecha a tela principal
                exibirTelaMatricula();
            }
        });
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        encerramentoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }//fim telaEncerramento

    // Cria a tela padrão do construtor
    public void exibirTelaMatricula() {
        new UrnaLayout();
    }//fim exibirTelaMatricula()
}

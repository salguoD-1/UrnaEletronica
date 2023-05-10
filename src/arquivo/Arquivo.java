package arquivo;

import criptografia.HashGenerator;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Arquivo extends HashGenerator {
    // Constante que armazena o nome do arquivo
    private static final String NOME_ARQUIVO = "eleitores.txt";
    private static final String VOTOS_FILENAME = "votos.txt";

    // Valida uma matrícula
    public static boolean validarMatricula(String matricula) {
        // Verifica se o arquivo existe antes de executar a busca
        File arquivo = new File(NOME_ARQUIVO);
        if (!arquivo.exists()) {
            JOptionPane.showMessageDialog(null, "Arquivos não encontrados.");
            return false;
        } if (!new File(VOTOS_FILENAME).exists()) {
            JOptionPane.showMessageDialog(null, "Arquivos não encontrados.");
            return false;
        }

        boolean encontrouMatricula = false;
        // Executa a leitura do arquivo
        try {
            BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO));
            String linha;
            while ((linha = br.readLine()) != null) {
                String hashLinha = linha;
                if (hashLinha.equals(HashGenerator.generateHash(matricula))) {
                    encontrouMatricula = true;
                    break;
                }
            }
            br.close();
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return encontrouMatricula;
    }
}
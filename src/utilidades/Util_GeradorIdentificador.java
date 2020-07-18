package utilidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Util_GeradorIdentificador {

    private int id = 0;
    String nomeDoArquivo = "IdGerado.txt";

    public Util_GeradorIdentificador() throws FileNotFoundException, IOException {
        verificarArquivo();
        FileReader fr = new FileReader(nomeDoArquivo);
        BufferedReader br = new BufferedReader(fr);
        String linha = br.readLine();
        id = Integer.parseInt(linha);
        br.close();
    }

    public int getID() {
        return ++id;
    }

    public void finalize() throws IOException {
        verificarArquivo();
        FileWriter fw = new FileWriter(nomeDoArquivo, false);
        BufferedWriter bw = new BufferedWriter(fw);
        String saida = id + "";
        bw.write(saida);
        bw.close();
    }

    public void verificarArquivo() {

        File arquivo = new File(nomeDoArquivo);
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

    }
}

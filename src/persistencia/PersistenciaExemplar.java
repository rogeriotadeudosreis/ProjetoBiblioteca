/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import classes.Exemplar;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import utilidades.Util_GeradorIdentificador;
import interfaces.InterfaceExemplar;

/**
 *
 * @author roger
 */
public class PersistenciaExemplar implements InterfaceExemplar {

    private String nomeDoArquivo = null;

    public PersistenciaExemplar() throws Exception {

    }

    public PersistenciaExemplar(String nomeDoArquivo) throws Exception {
        this.nomeDoArquivo = nomeDoArquivo;
        verificaArquivo();
    }

    @Override
    public void incluir(Exemplar objeto) throws Exception {
        verificaArquivo();
        try {
            Util_GeradorIdentificador idExemplar = new Util_GeradorIdentificador();
            objeto.setId(idExemplar.getID());

            FileWriter fw = new FileWriter(nomeDoArquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(objeto.toString() + "\n");
            bw.close();
            idExemplar.finalize();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao incluir esse exemplar\n" + erro);
        }
    }

    @Override
    public ArrayList<Exemplar> recuperar() throws Exception {
        verificaArquivo();
        try {
            ArrayList<Exemplar> listaExemplar = new ArrayList<Exemplar>();
            FileReader fr = new FileReader(nomeDoArquivo);
            BufferedReader br = new BufferedReader(fr);
            String linha = "";
            while ((linha = br.readLine()) != null) {
                Exemplar objetoExemplar = new Exemplar(linha);
                listaExemplar.add(objetoExemplar);
            }
            br.close();
           // Collections.sort(listaExemplar);
            return listaExemplar;

        } catch (Exception erro) {
            throw new Exception("Talvez o cadastro de exemplares esteja vazio\n" + erro);

        }
    }

    @Override
    public void alterar(Exemplar exemplarAlterado) throws Exception {
        verificaArquivo();
        try {
            ArrayList<Exemplar> listaExemplar = recuperar();
            FileWriter fw = new FileWriter(nomeDoArquivo);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int pos = 0; pos < listaExemplar.size(); pos++) {
                Exemplar aux = listaExemplar.get(pos);
                if (exemplarAlterado.getId() == aux.getId()) {
                    bw.write(exemplarAlterado.toString() + "\n");
                } else {
                    bw.write(aux.toString() + "\n");
                }
            }
            bw.close();

        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro na alteração deste exemplar\n" + erro);
        }

    }

    public void verificaArquivo() throws IOException {
        File arquivo = new File(nomeDoArquivo);
        if (!arquivo.exists()) {
            arquivo.createNewFile();
        }
    }

}

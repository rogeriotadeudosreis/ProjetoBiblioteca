package persistencia;

import classes.Autor;
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
import interfaces.InterfaceAutor;

public class PersistenciaAutor implements InterfaceAutor {

    private String nomeDoArquivo;

    public PersistenciaAutor() throws Exception {

    }

    public PersistenciaAutor(String nomeDoArquivo) throws Exception {
        this.nomeDoArquivo = nomeDoArquivo;
        verificaArquivo();
    }

    @Override
    public void incluirAutor(Autor objeto) throws Exception {
       verificaArquivo();
        try {
            Util_GeradorIdentificador idAutor = new Util_GeradorIdentificador();
            objeto.setId(idAutor.getID());

            FileWriter fw = new FileWriter(nomeDoArquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(objeto.toString() + "\n");
            bw.close();
            idAutor.finalize();

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "Ocorreu um erro na gravação deste arquivo!");
        }
    }

    @Override
    public ArrayList<Autor> recuperar() throws Exception {
      verificaArquivo();
        try {
            ArrayList<Autor> listaDeAutores = new ArrayList<>();
            FileReader fr = new FileReader(nomeDoArquivo);
            BufferedReader br = new BufferedReader(fr);
            String linha = "";
            while ((linha = br.readLine()) != null) {
                Autor objetoAutor = new Autor(linha);
                listaDeAutores.add(objetoAutor);
            }
            br.close();
            //Collections.sort(listaDeAutores);
            return listaDeAutores;

        } catch (Exception erro) {
            throw new Exception("Talvez o cadastro de autor esteja vazio\n" + erro);

        }
    }

    public Autor buscarPeloId(int id) throws Exception {
       verificaArquivo();
        try {

            FileReader fr = new FileReader(nomeDoArquivo);
            BufferedReader br = new BufferedReader(fr);
            String linha = "";
            while ((linha = br.readLine()) != null) {
                Autor objetoAutor = new Autor(linha);
                if (objetoAutor.getId() == id) {
                    br.close();
                    return objetoAutor;
                }
            }
            br.close();

            return null;
        } catch (Exception erro) {
            throw new Exception("Id do Autor não encontrado!" + erro.getMessage());
        }
    }

    @Override
    public void alterarAutor(Autor autorAlterado) throws Exception {
       verificaArquivo();
        try {
            ArrayList<Autor> listaDeAutores = recuperar();

            FileWriter fw = new FileWriter(nomeDoArquivo);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int pos = 0; pos < listaDeAutores.size(); pos++) {
                Autor aux = listaDeAutores.get(pos);

                if (aux.getId() == autorAlterado.getId()) {
                    bw.write(autorAlterado.toString() + "\n");
                } else {
                    bw.write(aux.toString() + "\n");
                }
            }
            bw.close();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }
    
    public void verificaArquivo() throws IOException {
        File arquivo = new File(nomeDoArquivo);
        if (!arquivo.exists()) {
            arquivo.createNewFile();
        }
    }

}

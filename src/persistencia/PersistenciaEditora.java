package persistencia;

import classes.Editora;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import utilidades.Util_ComunicadorTCP;
import utilidades.Util_GeradorIdentificador;
import interfaces.InterfaceEditora;

public class PersistenciaEditora implements InterfaceEditora {

    private String nomeDoArquivo;

    public PersistenciaEditora() throws Exception {

    }

    public PersistenciaEditora(String nomeDoArquivo) throws Exception {
        this.nomeDoArquivo = nomeDoArquivo;
        verificaArquivo();
    }

    @Override
    public void incluirEditora(Editora objeto) throws Exception {
       verificaArquivo();
        try {
            Util_GeradorIdentificador idEditora = new Util_GeradorIdentificador();
            objeto.setId(idEditora.getID());

            FileWriter fw = new FileWriter(nomeDoArquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(objeto.toString() + "\n");
            bw.close();
            idEditora.finalize();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao gravar");
        }
    }

    @Override
    public ArrayList<Editora> recuperar() throws Exception {
      verificaArquivo();
        try {
            ArrayList<Editora> listaEditora = new ArrayList<>();
            FileReader fr = new FileReader(nomeDoArquivo);
            BufferedReader br = new BufferedReader(fr);
            String linha = "";
            while ((linha = br.readLine()) != null) {
                Editora objetoEditora = new Editora(linha);
                listaEditora.add(objetoEditora);
            }
            br.close();
            //Collections.sort(listaEditora);
            return listaEditora;

        } catch (Exception erro) {
            throw new Exception("Talvez o cadastro de editora esteja vazio\n" + erro);

        }

    }

    @Override
    public void alterarEditora(Editora objetoNovo) throws Exception {
       verificaArquivo();
        try {
            ArrayList<Editora> listaEditora = recuperar();

            FileWriter fw = new FileWriter(nomeDoArquivo);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int pos = 0; pos < listaEditora.size(); pos++) {
                Editora aux = listaEditora.get(pos);

                if (aux.getId() == objetoNovo.getId()) {
                    bw.write(objetoNovo.toString() + "\n");
                } else {
                    bw.write(aux.toString() + "\n");
                }
            }
            bw.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro na alteração!" + erro.getMessage());
        }
    }

    public Editora buscarId(int id) throws Exception {
verificaArquivo();
        try {
            FileReader fr = new FileReader(nomeDoArquivo);
            BufferedReader br = new BufferedReader(fr);
            String linha = "";
            while ((linha = br.readLine()) != null) {
                Editora objetoEditora = new Editora(linha);
                if (objetoEditora.getId() == id) {
                    br.close();
                    return objetoEditora;
                }
            }
            br.close();
            return null;

        } catch (Exception ex) {
            throw new Exception("Id da Editora não encontrado");
        }
    }
    
    public void verificaArquivo() throws IOException{
        File arquivo = new File(nomeDoArquivo);
        if (!arquivo.exists()) {
            arquivo.createNewFile();
        }
    }

}

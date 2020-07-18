/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import classes.Sugestao;
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
import interfaces.InterfaceSugestao;

/**
 *
 * @author roger
 */
public class PersistenciaSugestao implements InterfaceSugestao{
    
    private String nomeDoArquivo = null;
    
    public PersistenciaSugestao()throws Exception{
        
    }
    
    public PersistenciaSugestao (String nomeDoArquivo)throws Exception{
        this.nomeDoArquivo = nomeDoArquivo;
        verificaArquivo();
    }

    @Override
    public void incluir(Sugestao objeto) throws Exception {
       verificaArquivo();
        try {
            Util_GeradorIdentificador idSugestao = new Util_GeradorIdentificador();
            objeto.setId(idSugestao.getID());
            
            FileWriter fw = new FileWriter(nomeDoArquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(objeto.toString() + "\n");
            bw.close();
            idSugestao.finalize();
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro na inclusão deste objeto!\n");
        
        }
    }

    @Override
    public ArrayList<Sugestao> recuperar() throws Exception {
      verificaArquivo();
        try {
            ArrayList<Sugestao> listaDeSugestoes = new ArrayList<Sugestao>();
            FileReader fr = new FileReader(nomeDoArquivo);
            BufferedReader br = new BufferedReader(fr);
            String linha = "";
            while((linha = br.readLine()) != null){
                Sugestao objetoSugestao = new Sugestao(linha);
                listaDeSugestoes.add(objetoSugestao);
            }
            br.close();
            //Collections.sort(listaDeSugestoes);
            return listaDeSugestoes;
        } catch (Exception erro) {
            throw new Exception( "Ocorreu um erro ao recuperar dados das sugestões" + erro);
        }
    }

    @Override
    public void alterar(Sugestao objetoAlterado) throws Exception {
       verificaArquivo();
        try {
            ArrayList<Sugestao> listaDeSugestoes = recuperar();
            FileWriter fw = new FileWriter(nomeDoArquivo);
            BufferedWriter bw = new BufferedWriter(fw);
            
            for (int pos = 0; pos < listaDeSugestoes.size(); pos++) {
                Sugestao aux = listaDeSugestoes.get(pos);
                if (objetoAlterado.getId() == aux.getId()) {
                    bw.write(objetoAlterado.toString() + "\n");
                }else {
                    bw.write(aux.toString() + "\n");
                }
            }
            bw.close();
            
        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro na alteração desta sugestão!\n");
        }
    }
    
    public void verificaArquivo() throws IOException{
        File arquivo = new File(nomeDoArquivo);
        if (!arquivo.exists()) {
            arquivo.createNewFile();
        }
    }
    
}

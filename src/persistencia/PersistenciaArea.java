/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import classes.AreaCdd;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import utilidades.Util_GeradorIdentificador;
import interfaces.InterfaceAreaDoLivro;

/**
 *
 * @author roger
 */
public class PersistenciaArea implements InterfaceAreaDoLivro {

    private String arquivoDestino;
    private String arquivoOrigem;

    public PersistenciaArea() throws Exception {

    }

    public PersistenciaArea(String arquivoDestino) throws Exception {
        this.arquivoDestino = arquivoDestino;
        verificaArquivoDestino();

    }

    @Override
    public void importarTabelaCdd(String arquivoOrigem) throws Exception {
        this.arquivoOrigem = arquivoOrigem;
        verificaArquivoOrigem();
        try {
            Util_GeradorIdentificador idArea = new Util_GeradorIdentificador();

            FileReader arquivo = new FileReader(this.arquivoOrigem);
            BufferedReader br = new BufferedReader(arquivo);

            FileWriter fw = new FileWriter(arquivoDestino, true);
            BufferedWriter bw = new BufferedWriter(fw);

            String linha = "";
            AreaCdd objetoCDD = new AreaCdd();
            while ((linha = br.readLine()) != null) {
                String[] cdd = linha.split(";");
                if (cdd.length != 2) {
                    throw new Exception("Faltam dados do arquivo de origem CDD\n");
                }
                objetoCDD.setId(idArea.getID());
                objetoCDD.setCdd(cdd[0]);
                objetoCDD.setDescricaoDaArea(cdd[1]);
                bw.write(objetoCDD.toString() + "\n");
            }

            bw.close();
            br.close();
            idArea.finalize();

        } catch (Exception erro) {

            throw new Exception("Ocorreu um erro ao importar o arquivo CDD\n");
        }
    }

    @Override
    public void incluirAreaCdd(AreaCdd objeto) throws Exception {
        try {
            verificaArquivoDestino();
            Util_GeradorIdentificador idCdd = new Util_GeradorIdentificador();
            objeto.setId(idCdd.getID());

            FileWriter fw = new FileWriter(arquivoDestino, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append(objeto.toString() + "\n");
            bw.close();
            idCdd.finalize();

        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao incluir este registro!\n");
        }
    }

    @Override

    public ArrayList<AreaCdd> recuperar() throws Exception {
        try {
            verificaArquivoDestino();
            ArrayList<AreaCdd> listaArea = new ArrayList<AreaCdd>();
            FileReader fr = new FileReader(arquivoDestino);
            BufferedReader br = new BufferedReader(fr);
            String linha = "";
            AreaCdd objetoArea = null;
            while ((linha = br.readLine()) != null) {
                objetoArea = new AreaCdd(linha);
                listaArea.add(objetoArea);
            }
            br.close();
            
            return listaArea;
        } catch (Exception erro) {
            throw new Exception("Talvez o cadastro de área esteja vazio\n");
        }
    }

    @Override
    public AreaCdd buscarId(int id) throws Exception {
        try {
            FileReader fr = new FileReader(arquivoDestino);
            BufferedReader br = new BufferedReader(fr);
            String linha = "";
            while ((linha = br.readLine()) != null) {
                AreaCdd objetoArea = new AreaCdd(linha);
                if (objetoArea.getId() == id) {
                    br.close();
                    return objetoArea;
                }
            }
            br.close();
            return null;
        } catch (Exception erro) {
            throw new Exception("Id da Área não encontrado!");

        }
    }

    public void verificaArquivoOrigem() throws IOException {
        File arqOrigem = new File(arquivoOrigem);
        if (!arqOrigem.exists()) {
            arqOrigem.createNewFile();
        }
    }

    public void verificaArquivoDestino() throws IOException {
        File arqDestino = new File(arquivoDestino);
        if (!arqDestino.exists()) {
            arqDestino.createNewFile();
        }
    }

    @Override
    public void alterar(AreaCdd area) throws Exception {
        try {
            verificaArquivoDestino();
            ArrayList<AreaCdd> listaArea = recuperar();
            FileWriter fw = new FileWriter(arquivoDestino);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int pos = 0; pos < listaArea.size(); pos++) {
                AreaCdd aux = listaArea.get(pos);
                if (area.getId() == aux.getId()) {
                    bw.write(area.toString() + "\n");
                } else {
                    bw.write(aux.toString() + "\n");
                }
            }
            bw.close();
        } catch (Exception erro) {
            throw new Exception("Erro ao alterar este registro!\n");
        }
    }

}

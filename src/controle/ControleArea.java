/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import classes.AreaCdd;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import persistencia.PersistenciaArea;
import interfaces.InterfaceAreaDoLivro;

/**
 *
 * @author roger
 */
public class ControleArea implements InterfaceAreaDoLivro {

    private InterfaceAreaDoLivro objeto = null;

    public ControleArea() {
    }

    public ControleArea(String arquivoDestino) throws Exception {
        this.objeto = new PersistenciaArea(arquivoDestino);
    }

    @Override
    public void importarTabelaCdd(String arquivoOrigem) throws Exception {
        validaImportacao(arquivoOrigem);
        this.objeto.importarTabelaCdd(arquivoOrigem);
    }

    @Override
    public void incluirAreaCdd(AreaCdd area) throws Exception {
        validaDadosArea(area);
        this.objeto.incluirAreaCdd(area);
    }

    @Override
    public ArrayList<AreaCdd> recuperar() throws Exception {
        return this.objeto.recuperar();
    }

    @Override
    public AreaCdd buscarId(int id) throws Exception {
        return this.objeto.buscarId(id);
    }

    @Override
    public void alterar(AreaCdd area) throws Exception {
        validaDadosArea(area);
        this.objeto.alterar(area);
    }

    public void validaDadosArea(AreaCdd area) throws Exception {
        ArrayList<AreaCdd> lista = recuperar();
        for (int pos = 0; pos < lista.size(); pos++) {
            AreaCdd aux = lista.get(pos);
            if (area.getId() != aux.getId() && area.getDescricaoDaArea().equalsIgnoreCase(aux.getDescricaoDaArea().trim())) {
                throw new Exception("A descrição --> " + " [ " + aux.getDescricaoDaArea() + " ] " + " já existe no cadastro de área.\n");
            }
            if (area.getId() != aux.getId() && area.getCdd().equalsIgnoreCase(aux.getCdd().trim())) {
                throw new Exception("O código CDD --> " + " [ " + aux.getCdd() + " ] " + " já existe no cadastro de área.\n");
            }

        }
    }

    public void validaImportacao(String arquivoOrigem) throws Exception {
        ArrayList<AreaCdd> lista = recuperar();

        FileReader fr = new FileReader(arquivoOrigem);
        BufferedReader br = new BufferedReader(fr);
        String linha = "";
        AreaCdd objetoCDD = new AreaCdd();
        while ((linha = br.readLine()) != null) {
            String[] cdd = linha.split(";");
            if (cdd.length != 2) {
                throw new Exception("Faltam dados do arquivo de origem CDD\n");
            }
            objetoCDD.setCdd(cdd[0]);
            objetoCDD.setDescricaoDaArea(cdd[1]);

        }
        for (int pos = 0; pos < lista.size(); pos++) {
            AreaCdd aux = lista.get(pos);
            if (!objetoCDD.getCdd().equalsIgnoreCase(aux.getCdd())) {
                throw new Exception("CDD já existe no cadastro!\n");
            }

        }

    }
    
    public ArrayList pesquisar (String dadoDigitado)throws Exception{
        String textoDigitado = dadoDigitado;    
        ArrayList<AreaCdd> resultadoDaPesquisa = new ArrayList<>();
            boolean cont = true;
            for (AreaCdd areaCdd :recuperar()) {
                if ((areaCdd.getDescricaoDaArea().toLowerCase().trim().contains(textoDigitado))
                        || areaCdd.getCdd().trim().contains(textoDigitado)) {
                    resultadoDaPesquisa.add(areaCdd);
                    cont = false;
                } 
            }
            if (cont) {
                throw new Exception("Registro não encontrado!\n");
            }
            return resultadoDaPesquisa;
       
    }

}

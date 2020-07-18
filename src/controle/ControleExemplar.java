/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import classes.Exemplar;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import persistencia.PersistenciaExemplar;
import interfaces.InterfaceExemplar;

/**
 *
 * @author roger
 */
public class ControleExemplar implements InterfaceExemplar {

    private InterfaceExemplar objeto = null;

    public ControleExemplar() {

    }

    public ControleExemplar(String nomeDoArquivo) throws Exception {
        this.objeto = new PersistenciaExemplar(nomeDoArquivo);
    }

    @Override
    public void incluir(Exemplar objeto) throws Exception {

        validaEntradaExemplar(objeto);

        this.objeto.incluir(objeto);
    }

    @Override
    public ArrayList<Exemplar> recuperar() throws Exception {
        try {
            return this.objeto.recuperar();
        } catch (Exception erro) {
            throw new Exception("Ocorreu um erro ao recuperar\n" + erro);
        }
    }

    @Override
    public void alterar(Exemplar exemplarAlterado) throws Exception {
        validaEntradaExemplar(exemplarAlterado);
        this.objeto.alterar(exemplarAlterado);
    }

    public void validaEntradaExemplar(Exemplar objeto) throws Exception {
        Date data = new Date();
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");

        if (objeto.getDataAquisicao().after(data)) {
            throw new Exception("A data de aquisição do exemplar é inválida.\n");
        }
        if (objeto.getPrecoExemplar() < 0) {
            throw new Exception("O valor informado do exemplar não pode ser menor que 0.\n");
        }

    }

    public ArrayList pesquisarExemplar(String dados) throws Exception {
        ArrayList<Exemplar> resultadoDaPesquisa = new ArrayList<>();
        boolean vdd = true;
        for (Exemplar exemplar : recuperar()) {
            if (exemplar.getLivro().getTituloLivro().toLowerCase().trim().contains(dados)) {
                resultadoDaPesquisa.add(exemplar);
                vdd = false;
            }
        }
        if (vdd) {
            throw new Exception("Registro não encontrado!\n");
        }
        return resultadoDaPesquisa;
    }

}

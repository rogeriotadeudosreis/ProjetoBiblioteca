/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import classes.Livro;
import classes.Sugestao;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import persistencia.PersistenciaSugestao;
import interfaces.InterfaceSugestao;

/**
 *
 * @author roger
 */
public class ControleSugestao implements InterfaceSugestao {

    private InterfaceSugestao objeto = null;

    public ControleSugestao() throws Exception {

    }

    public ControleSugestao(String nomeDoArquivo) throws Exception {
        this.objeto = new PersistenciaSugestao(nomeDoArquivo);
    }

    @Override
    public void incluir(Sugestao objeto) throws Exception {
        validaDadosSugestao(objeto);
        this.objeto.incluir(objeto);
    }

    @Override
    public ArrayList<Sugestao> recuperar() throws Exception {
        return this.objeto.recuperar();
    }

    @Override
    public void alterar(Sugestao objeto) throws Exception {
        validaDadosSugestao(objeto);
        this.objeto.alterar(objeto);
    }

    public void validaDadosSugestao(Sugestao objeto) throws Exception {
        Date data = new Date();
        String sugestaoDoUsuario = objeto.getSugestao().trim().toLowerCase();

        if (objeto.getDataDaSugestao().after(data)) {
            throw new Exception("A data da sugestão é inválida!\n");
        }

        ControleLivro controleLivro = new ControleLivro("livro.txt");
        ArrayList<Livro> lista = controleLivro.recuperar();
        for (int pos = 0; pos < lista.size(); pos++) {
            Livro aux = lista.get(pos);
            if (sugestaoDoUsuario.equalsIgnoreCase(aux.getTituloLivro())) {
                throw new Exception("Este livro já existe no acervo da biblioteca!\n");
            }
        }

        ArrayList<Sugestao> listaSugestoes = this.objeto.recuperar();
        for (int pos = 0; pos < listaSugestoes.size(); pos++) {
            Sugestao aux = listaSugestoes.get(pos);
            if (sugestaoDoUsuario.equalsIgnoreCase(aux.getSugestao())) {
                if (objeto.getUsuario().getId() != aux.getUsuario().getId()) {
                    JOptionPane.showMessageDialog(null, aux.getUsuario().getNome() + " também indicou este livro!\n");
                }
            }

            if ((sugestaoDoUsuario.equalsIgnoreCase(aux.getSugestao()) && (objeto.getUsuario().getId() == aux.getUsuario().getId()))) {
                throw new Exception(aux.getUsuario().getNome() + " já sugeriu este livro!\n");
            }
        }

    }
    
    public ArrayList pesquisar(String dados)throws Exception{
        ArrayList<Sugestao> resultadoDaPesquisa = new ArrayList<>();
        boolean vdd = true;
        for (Sugestao sugestao : recuperar()) {
            if (sugestao.getSugestao().toLowerCase().trim().contains(dados)){
                resultadoDaPesquisa.add(sugestao);
                vdd = false;
            }
            else if (sugestao.getUsuario().getNome().toLowerCase().trim().contains(dados)) {
                 resultadoDaPesquisa.add(sugestao);
                vdd = false;
            }
            else if (sugestao.getUsuario().getMatriculaCpf().trim().contains(dados)) {
                resultadoDaPesquisa.add(sugestao);
                vdd = false;
            }
        }
            if (vdd) {
                throw new Exception("Registro não encontrado!\n");
            }
        return resultadoDaPesquisa;
    }

}

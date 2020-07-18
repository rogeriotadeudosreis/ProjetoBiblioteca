/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import classes.Livro;
import java.util.ArrayList;
import persistencia.PersistenciaLivro;
import interfaces.InterfaceLivro;

/**
 *
 * @author roger
 */
public class ControleLivro implements InterfaceLivro {

    private InterfaceLivro objeto = null;

    public ControleLivro() {

    }

    public ControleLivro(String nomeDoArquivo) throws Exception {
        this.objeto = new PersistenciaLivro(nomeDoArquivo);
    }

    @Override
    public void incluir(Livro objeto) throws Exception {
        validaDadosDoLivro(objeto);
        this.objeto.incluir(objeto);
    }

    @Override
    public ArrayList<Livro> recuperar() throws Exception {
        return this.objeto.recuperar();
    }

    @Override
    public void alterarDadosDoLivro(Livro livroAlterado) throws Exception {
        validaDadosDoLivro(livroAlterado);
        this.objeto.alterarDadosDoLivro(livroAlterado);
    }

    @Override
    public void emprestarOuDevolver() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void validaDadosDoLivro(Livro objeto) throws Exception {
        String titulo = objeto.getTituloLivro().trim().toLowerCase();
        String subTitulo = objeto.getSubTituloLivro().trim().toLowerCase();
        
        if (titulo.equals("")) {
            throw new Exception("Informe o título do livro!\n");
        }
        if (subTitulo.equals("")) {
            throw new Exception("Informe o sub-título do livro!\n");
        }
        if (objeto.getAnoDePublicacao() <= 1455 || objeto.getAnoDePublicacao() > 2020) {
            throw new Exception("Ano de publicação inválido!\nDigite apenas 4 dígitos\n");
        }

        ArrayList<Livro> lista = recuperar();
        for (int pos = 0; pos < lista.size(); pos++) {
            Livro aux = lista.get(pos);
            
            if ((objeto.getId() != aux.getId()) && (objeto.getIsbn().equalsIgnoreCase(aux.getIsbn()))) {
                throw new Exception("O isbn informato já existe para o livro --> " + aux.getTituloLivro() + "\n");
            }
           
        }
    }

    @Override
    public Livro buscarPeloId(int id) throws Exception {
        return this.objeto.buscarPeloId(id);
    }
    
    public ArrayList pesquisar (String dados)throws Exception{
        ArrayList<Livro> resultadoDaPesquisa = new ArrayList<>();
        boolean vdd = true;
        for (Livro livro : recuperar()) {
            if (livro.getIsbn().trim().contains(dados)) {
                resultadoDaPesquisa.add(livro);
                vdd = false;
            }
            else if (livro.getTituloLivro().toLowerCase().trim().contains(dados)) {
                resultadoDaPesquisa.add(livro);
                vdd = false;
            }else if (livro.getAutor().getNomeAutor().toLowerCase().trim().contains(dados)) {
                resultadoDaPesquisa.add(livro);
                vdd = false;
            }
        }
        if (vdd) {
            throw new Exception("Registro não encontrado!\n");
        }
        return resultadoDaPesquisa;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.Livro;
import java.util.ArrayList;

/**
 *
 * @author roger
 */
public interface InterfaceLivro {

    void incluir(Livro objeto) throws Exception;

    ArrayList<Livro> recuperar() throws Exception;

    void alterarDadosDoLivro(Livro livroAlterado) throws Exception;

    public Livro buscarPeloId(int id) throws Exception;

    void emprestarOuDevolver() throws Exception;
}

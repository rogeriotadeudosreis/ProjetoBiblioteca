/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import classes.Autor;
import java.util.ArrayList;

/**
 *
 * @author roger
 */
public interface InterfaceAutor {
    
    void incluirAutor(Autor objeto)throws Exception;
    ArrayList<Autor> recuperar() throws Exception;
    public void alterarAutor(Autor autorAlterado)throws Exception;
    public Autor buscarPeloId(int id) throws Exception ;
    
}

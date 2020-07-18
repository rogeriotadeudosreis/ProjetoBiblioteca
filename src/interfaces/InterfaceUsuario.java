/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.Usuario;
import java.util.ArrayList;

/**
 *
 * @author roger
 */
public interface InterfaceUsuario {
    
    void incluir(Usuario objeto) throws Exception;
    void alterar (Usuario objeto)throws Exception;
    ArrayList<Usuario> recuperar()throws Exception;
    public Usuario buscarPeloId(int id) throws Exception;
    
}

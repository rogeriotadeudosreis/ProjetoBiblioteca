/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.Sugestao;
import java.util.ArrayList;

/**
 *
 * @author roger
 */
public interface InterfaceSugestao {
    
    public void incluir (Sugestao objeto)throws Exception;
    public ArrayList<Sugestao> recuperar()throws Exception;
    public void alterar (Sugestao objeto)throws Exception;
    
}

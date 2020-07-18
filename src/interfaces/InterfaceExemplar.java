/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.Exemplar;
import java.util.ArrayList;

/**
 *
 * @author roger
 */
public interface InterfaceExemplar {
    
    void incluir(Exemplar objeto) throws Exception;
    void alterar (Exemplar exemplarAlterado)throws Exception;
    ArrayList<Exemplar> recuperar()throws Exception;
    
}

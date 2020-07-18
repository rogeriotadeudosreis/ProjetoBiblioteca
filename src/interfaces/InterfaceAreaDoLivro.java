/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import classes.AreaCdd;
import java.util.ArrayList;
/**
 *
 * @author roger
 */
public interface InterfaceAreaDoLivro {
    
    public void importarTabelaCdd(String nomeDoArquivo) throws Exception;
    public void incluirAreaCdd (AreaCdd area)throws Exception;
    ArrayList<AreaCdd> recuperar() throws Exception;
    public void alterar(AreaCdd area)throws Exception;
    public AreaCdd buscarId(int id) throws Exception;
}

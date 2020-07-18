/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import classes.Autor;
import classes.Editora;
import java.util.ArrayList;
import persistencia.PersistenciaAutor;
import interfaces.InterfaceAutor;

/**
 *
 * @author roger
 */
public class ControleAutor implements InterfaceAutor{
    
    private InterfaceAutor objeto = null;    

    public ControleAutor (String nomeDoArquivo) throws Exception{
     this.objeto = new PersistenciaAutor(nomeDoArquivo);
    }

    @Override
    public void incluirAutor(Autor objeto) throws Exception {
        validaDadosAutor(objeto);
        this.objeto.incluirAutor(objeto);
        
    }

    @Override
    public ArrayList<Autor> recuperar() throws Exception {
    return this.objeto.recuperar();
    }
    
    @Override
    public void alterarAutor(Autor autorAlterado) throws Exception {
        validaDadosAutor(autorAlterado);
        this.objeto.alterarAutor(autorAlterado);
    }

    @Override
    public Autor buscarPeloId(int id) throws Exception {
        return this.objeto.buscarPeloId(id);
    }
    
    public void validaDadosAutor(Autor objeto)throws Exception{
        String nome = objeto.getNomeAutor().trim().toLowerCase();
        String invalidos = "1234567890'\"!@#$%¨&*()-_+={[}]/?><;:";
        for (int i = 0; i < invalidos.length(); i++) {
            if (nome.contains("" + invalidos.charAt(i))) {
                throw new Exception("Nome de autor inválido!");
            }
        }
        if (nome.equals("")) {
            throw new Exception("Informe o nome do autor");
        }
        
        ArrayList<Autor> lista = recuperar();
        for (int pos = 0; pos < lista.size(); pos++) {
            Autor aux = lista.get(pos);
            if (objeto.getId() == aux.getId() && objeto.getNomeAutor().equalsIgnoreCase(aux.getNomeAutor())) {
                throw new Exception("Não houve alteração neste registro!\n");
            }
        }
   }
    
    public ArrayList pesquisarAutor(String dados)throws Exception{
        String textoDigitado = dados;
        ArrayList<Autor> resultadoDaPesquisa = new ArrayList<>();
        boolean vdd= true;
        for (Autor autor : recuperar()) {
            if (autor.getNomeAutor().toLowerCase().trim().contains(textoDigitado)) {
             resultadoDaPesquisa.add(autor);
             vdd = false;
            }
            
        }
        if (vdd) {
            throw new Exception("Registro não encontrado!\n");
        }
        return resultadoDaPesquisa;
    }
}

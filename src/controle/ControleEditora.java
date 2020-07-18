package controle;

import classes.Editora;
import java.util.ArrayList;
import persistencia.PersistenciaEditora;
import interfaces.InterfaceEditora;

public class ControleEditora implements InterfaceEditora {

    private InterfaceEditora objeto = null;

    public ControleEditora() throws Exception {
    }

    public ControleEditora(String nomeDoArquivo) throws Exception {
        this.objeto = new PersistenciaEditora(nomeDoArquivo);
    }

    @Override
    public void incluirEditora(Editora objeto) throws Exception {
        ValidaDadosDescricao(objeto);
        this.objeto.incluirEditora(objeto);
    }

    @Override
    public ArrayList<Editora> recuperar() throws Exception {
        return this.objeto.recuperar();

    }

    @Override
    public void alterarEditora(Editora objetoNovo) throws Exception {
        ValidaDadosDescricao(objetoNovo);
        this.objeto.alterarEditora(objetoNovo);
    }

    public void ValidaDadosDescricao(Editora objeto) throws Exception {
        String descricao = objeto.getDescricaoEditora().trim().toLowerCase();
        String invalidos = "'\"!@#$%¨*()_+={[}]/?><;:";
        for (int i = 0; i < invalidos.length(); i++) {
            if (descricao.contains("" + invalidos.charAt(i))) {
                throw new Exception("Descrição da Editora inválida!");
            }
        }
        if (descricao.equals("")) {
            throw new Exception("Informe a descrição da editora do livro.");
        }
        ArrayList<Editora> listaEditora = recuperar();
        for (int pos = 0; pos < listaEditora.size(); pos++) {
            Editora aux = listaEditora.get(pos);
            if ((objeto.getId() != aux.getId()) && (objeto.getDescricaoEditora().equalsIgnoreCase(aux.getDescricaoEditora()))) {
                
                    throw new Exception("A editora -->  " + objeto.getDescricaoEditora() + "\nJá existe no cadastro!\n");
              
            }
            else if ((objeto.getId() == aux.getId()) && (objeto.getDescricaoEditora().equalsIgnoreCase(aux.getDescricaoEditora()))) {
                
                    throw new Exception("A editora -->  " + objeto.getDescricaoEditora() + "\nNão foi alterada!\n");
              
            }
        }
    }

    @Override
    public Editora buscarId(int id) throws Exception {
        return this.objeto.buscarId(id);
    }
    
    public ArrayList pesquisarEditora(String dados)throws Exception{
        ArrayList<Editora> resultadoDaPesquisa = new ArrayList<>();
        boolean vdd = true;
        for (Editora editora : recuperar()) {
            if (dados.equals(dados.toUpperCase())) {
                throw new Exception("Digite apenas minúsculas!\n");
            }
            if (editora.getDescricaoEditora().toLowerCase().trim().contains(dados)) {
            resultadoDaPesquisa.add(editora);
            vdd = false;
            }
        }
        if (vdd) {
            throw new Exception("Registro não encontrado!\n");
        }
        return resultadoDaPesquisa;
    }

}

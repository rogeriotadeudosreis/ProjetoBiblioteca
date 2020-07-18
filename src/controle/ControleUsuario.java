/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import classes.Livro;
import classes.Usuario;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import persistencia.PersistenciaUsuario;
import interfaces.InterfaceUsuario;

/**
 *
 * @author roger
 */
public class ControleUsuario implements InterfaceUsuario {

    private InterfaceUsuario objeto = null;

    public ControleUsuario() throws Exception {

    }

    public ControleUsuario(String nomeDoArquivo) throws Exception {
        this.objeto = new PersistenciaUsuario(nomeDoArquivo);
    }

    @Override
    public void incluir(Usuario objeto) throws Exception {
        validaDadosUsuario(objeto);
        this.objeto.incluir(objeto);
    }

    @Override
    public ArrayList<Usuario> recuperar() throws Exception {
        return this.objeto.recuperar();
    }

    @Override
    public void alterar(Usuario usuarioAlterado) throws Exception {
        validaDadosUsuario(usuarioAlterado);
        this.objeto.alterar(usuarioAlterado);
    }

    public void validaDadosUsuario(Usuario objeto) throws Exception {
        String nome = objeto.getNome().trim().toLowerCase();
        String invalidos = "1234567890'\"!@#$%¨&*()-_+={[}]/?><;:";
        for (int i = 0; i < invalidos.length(); i++) {
            if (nome.contains("" + invalidos.charAt(i))) {
                throw new Exception("Nome de usuário inválido!");
            }
        }

        if (nome.equals("")) {
            throw new Exception("Informe o nome do usuário\n");
        }
        
        if (validaCPF(objeto.getMatriculaCpf()) != true) {
            throw new Exception("Matrícula de CPF inválida!\n");
        }

        else if (objeto.getAssinaturaEletronica().length() != 6) {
            throw new Exception("Senha inválida! - Senha deve conter 6 dígitos numéricos\n");
        }

        else if ((objeto.getTipoDeUsuario().equals(ENUMERADORES.EnumTipoDeUsuario.ADVOGADO))
                && (objeto.getOab() == 0)) {
            throw new Exception("Para o tipo de usuário selecionado, a matrícula de OAB não pode ser 0\n");
        }

        ArrayList<Usuario> lista = recuperar();
        for (int pos = 0; pos < lista.size(); pos++) {

            Usuario aux = lista.get(pos);
           
            if ((objeto.getId() != aux.getId()) && (objeto.getMatriculaCpf().equalsIgnoreCase(aux.getMatriculaCpf()))) {
                throw new Exception("A matrícula de Cpf informada já existe no cadastro do usuário\n" + aux.getId() );
            }
            if ((objeto.getId() == aux.getId()) && (!objeto.getAssinaturaEletronica().equalsIgnoreCase(aux.getAssinaturaEletronica()))) {
                throw new Exception("Assinatura inválida para realizar esta operação!\n");
            }
                        
        }

    }

    public boolean validaCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000")
                || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {

            // Calculando o primeiro digito
            sm = 0;
            peso = 10;

            for (i = 0; i < 9; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;

            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }

            //Calculando o segundo digito
            sm = 0;
            peso = 11;

            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;

            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            //Verificando se os dígitos calculados são iguais os fornecidos pelo usuário
            if ((dig10 == CPF.charAt(9)) && dig11 == CPF.charAt(10)) {
                return (true);
            } else {
                return (false);
            }
        } catch (Exception erro) {
            return (false);
        }

    }

    @Override
    public Usuario buscarPeloId(int id) throws Exception {
        return this.objeto.buscarPeloId(id);
    }
    
    public ArrayList pesquisar (String dados)throws Exception{
        ArrayList<Usuario> resultadoDaPesquisa = new ArrayList<>();
        boolean vdd = true;
        for (Usuario usuario : recuperar()) {
            if ((usuario.getMatriculaCpf().trim().contains(dados))
                    || (usuario.getNome().toLowerCase().trim().contains(dados))) {
                resultadoDaPesquisa.add(usuario);
                vdd = false;
            }
        }
        if (vdd) {
            throw new Exception("Registro não encontado!\n");
        }
        return resultadoDaPesquisa;
    }

}

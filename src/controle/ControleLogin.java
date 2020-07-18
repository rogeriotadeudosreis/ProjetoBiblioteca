/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import ENUMERADORES.EnumNomesDeArquivos;
import ENUMERADORES.EnumTipoDeStatusUsuario;
import ENUMERADORES.EnumTipoDeUsuario;
import classes.Login;
import classes.Usuario;
import java.util.ArrayList;
import interfaces.InterfaceLogin;
import javax.swing.JOptionPane;

/**
 *
 * @author roger
 */
public class ControleLogin implements interfaces.InterfaceLogin{

    ControleUsuario controle = null;

    private InterfaceLogin objeto = null;

    public ControleLogin() throws Exception {

    }   

    public boolean validaLogin(Login objeto) throws Exception {
        controle = new ControleUsuario(EnumNomesDeArquivos.USUARIOS + ".txt");

        ArrayList<Usuario> listaDeUsuarios = controle.recuperar();
        for (int pos = 0; pos < listaDeUsuarios.size(); pos++) {
            Usuario aux = listaDeUsuarios.get(pos);
            if (objeto.getEmail().equalsIgnoreCase(aux.getEmail())
                    && (objeto.getSenha().equalsIgnoreCase(aux.getAssinaturaEletronica()))) {
                if (aux.getTipoDeStatus().equals(EnumTipoDeStatusUsuario.ATIVO)
                        &&(aux.getTipoDeUsuario().equals(EnumTipoDeUsuario.ADMINISTRADOR))) {
                    JOptionPane.showMessageDialog(null, "Bem Vindo ao Sistema --> " + aux.getNome());
                    return true;
                } else {
                    throw new Exception("O usuário não está ativo no sistema ou não é um administrador!\nProcure um administrador.\n");
                }
            }
        }
        return false;
    }
}

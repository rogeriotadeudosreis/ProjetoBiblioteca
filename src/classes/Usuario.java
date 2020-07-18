/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import ENUMERADORES.EnumTipoDeStatusUsuario;
import ENUMERADORES.EnumTipoDeUsuario;

/**
 *
 * @author roger
 */
public class Usuario implements Comparable<Usuario>{

    private int id = 0;
    private String matriculaCpf = "";
    private String nome = "";
    private String email = "";
    private String telefone = "";
    private EnumTipoDeUsuario tipoDeUsuario = null;
    private EnumTipoDeStatusUsuario tipoDeStatus = null;
    private int oab;
    private String assinaturaEletronica = null;

    public Usuario() throws Exception {
    }

    public Usuario(String matriculaCpf,String nome,String email,String telefone,
            EnumTipoDeUsuario tipoDeUsuario, EnumTipoDeStatusUsuario tipoDeStatus,int oab, String assinaturaEletronica) throws Exception {
       
        this.matriculaCpf = matriculaCpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.tipoDeUsuario = tipoDeUsuario;
        this.tipoDeStatus = tipoDeStatus;
        this.oab = oab;
        this.assinaturaEletronica = assinaturaEletronica;
    }

    public Usuario(String dadosDoUsuario) throws Exception {
        String vetorUsuario[] = dadosDoUsuario.split(";");
        if (vetorUsuario.length < 9) {
            throw new Exception("Faltam dados do usuário\n");
        }
        id = Integer.parseInt(vetorUsuario[0]);
        matriculaCpf = vetorUsuario[1];
        nome = vetorUsuario[2];
        email = vetorUsuario[3];
        telefone = vetorUsuario[4];
        tipoDeUsuario = EnumTipoDeUsuario.values()[Integer.parseInt(vetorUsuario[5])];
        tipoDeStatus = EnumTipoDeStatusUsuario.values()[Integer.parseInt(vetorUsuario[6])];
        oab = Integer.parseInt(vetorUsuario[7]);
        assinaturaEletronica = vetorUsuario[8];

    }
    
     public void setUsuarioSplit (String combBox)throws Exception{        
        String[]vetorString = combBox.split("-");
        if (vetorString.length < 2) {
            throw new Exception();
        }        
        id = Integer.parseInt(vetorString[0]);
        nome = vetorString[1];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatriculaCpf() {
        return matriculaCpf;
    }

    public void setMatriculaCpf(String matriculaCpf) {
        this.matriculaCpf = matriculaCpf;
    }

    public int getOab() {
        return oab;
    }

    public void setOab(int oab) {
        this.oab = oab;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public EnumTipoDeUsuario getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(EnumTipoDeUsuario tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }

    public EnumTipoDeStatusUsuario getTipoDeStatus() {
        return tipoDeStatus;
    }

    public void setTipoDeStatus(EnumTipoDeStatusUsuario tipoDeStatus) {
        this.tipoDeStatus = tipoDeStatus;
    }

    public String getAssinaturaEletronica() {
        return assinaturaEletronica;
    }

    public void setAssinaturaEletronica(String assinaturaEletronica) {
        this.assinaturaEletronica = assinaturaEletronica;
    }

    @Override
    public String toString() {
        String saida = id + ";";
        saida += matriculaCpf + ";";
        saida += nome + ";";
        saida += email + ";";
        saida += telefone + ";";
        saida += tipoDeUsuario.ordinal() + ";";
        saida += tipoDeStatus.ordinal() + ";";
        saida += oab + ";";
        saida += assinaturaEletronica + ";";

        return saida;
    }

    @Override
    public int compareTo(Usuario objeto) {
        return nome.compareToIgnoreCase(objeto.getNome());
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import ENUMERADORES.EnumStatusSugestao;
import controle.ControleUsuario;
import java.text.SimpleDateFormat;
import java.util.Date;
import interfaces.InterfaceUsuario;

/**
 *
 * @author roger
 */
public class Sugestao implements Comparable<Sugestao>{

    private int id;
    private Date dataDaSugestao;
    private Usuario usuario;
    private EnumStatusSugestao statusDaSugestao;
    private String sugestao;

    public Sugestao() throws Exception {

    }

    public Sugestao(int id, Date dataDaSugestao, Usuario usuario, EnumStatusSugestao statusDaSugestao, String sugestao) {
        this.id = id;
        this.dataDaSugestao = dataDaSugestao;
        this.usuario = usuario;
        this.statusDaSugestao = statusDaSugestao;
        this.sugestao = sugestao;
    }
    
    public Sugestao (String dadosDaSugestao)throws Exception{
        String vetorSugestoes [] = dadosDaSugestao.split(";");
        if (vetorSugestoes.length < 5) {
            throw new Exception("Faltam dados do arquivo de sugestões\n");
        }
        id = Integer.parseInt(vetorSugestoes[0]);
        dataDaSugestao = new SimpleDateFormat("dd/MM/yyyy").parse(vetorSugestoes[1]);
        InterfaceUsuario controle = new ControleUsuario("Usuarios.txt");
        usuario = controle.buscarPeloId(Integer.parseInt(vetorSugestoes[2]));
        statusDaSugestao = EnumStatusSugestao.values()[Integer.parseInt(vetorSugestoes[3])];
        sugestao = vetorSugestoes[4];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataDaSugestao() {
        return dataDaSugestao;
    }

    public void setDataDaSugestao(Date dataDaSugestao) {
        this.dataDaSugestao = dataDaSugestao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EnumStatusSugestao getStatusDaSugestao() {
        return statusDaSugestao;
    }

    public void setStatusDaSugestao(EnumStatusSugestao statusDaSugestao) {
        this.statusDaSugestao = statusDaSugestao;
    }

    public String getSugestao() {
        return sugestao;
    }

    public void setSugestao(String sugestao) {
        this.sugestao = sugestao;
    }
    
    @Override
    public String toString(){
        return id + ";" +
         new SimpleDateFormat("dd/MM/yyyy").format(dataDaSugestao) + ";" +
      usuario.getId() + ";" +
       statusDaSugestao.ordinal() + ";" +
       sugestao + ";" ;
      
    }

    @Override
    public int compareTo(Sugestao objeto) {
       return usuario.getNome().compareToIgnoreCase(objeto.usuario.getNome().toString());
    }
    
   
    
}

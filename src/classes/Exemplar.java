/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import ENUMERADORES.EnumDisponibilidadeExemplar;
import ENUMERADORES.EnumSituacaoExemplar;
import controle.ControleLivro;
import java.text.SimpleDateFormat;
import java.util.Date;
import interfaces.InterfaceLivro;

/**
 *
 * @author roger
 */
public class Exemplar implements Comparable<Exemplar>{
    
    
    public int id = 0;
    public Livro livro = null;
    public Date dataAquisicao = null;
    public float precoExemplar = 0f;
    public EnumSituacaoExemplar situacao = null;
    public EnumDisponibilidadeExemplar disponibilidade = null;
    public String motivoInativacao = "Estado físico do livro";
    
    public Exemplar()throws Exception{
        
    }

    public Exemplar(Livro livro, Date dataAquisicao, float precoExemplar, 
            EnumSituacaoExemplar situacao, EnumDisponibilidadeExemplar disponibilidade, String motivoInativacao)throws Exception {
       
        this.livro = livro;
        this.dataAquisicao = dataAquisicao;
        this.precoExemplar = precoExemplar;
        this.situacao = situacao;
        this.disponibilidade = disponibilidade;
        this.motivoInativacao = motivoInativacao;
    }
    
    public Exemplar(String dados)throws Exception{
        
        String vetorExemplar[] = dados.split(";");
        if (vetorExemplar.length < 7) {
            throw new Exception("Faltam dados do arquivo Exemplar.txt\n");
        }
        id = Integer.parseInt(vetorExemplar[0]);
        InterfaceLivro controle = new ControleLivro("livro.txt");
        livro = controle.buscarPeloId(Integer.parseInt(vetorExemplar[1]));
        dataAquisicao = new SimpleDateFormat("dd/MM/yyyy").parse(vetorExemplar[2]);
        precoExemplar = Float.parseFloat(vetorExemplar[3]);
        situacao = EnumSituacaoExemplar.values()[Integer.parseInt(vetorExemplar[4])];
        disponibilidade = EnumDisponibilidadeExemplar.values()[Integer.parseInt(vetorExemplar[5])];
        motivoInativacao = vetorExemplar[6];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Date getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(Date dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public float getPrecoExemplar() {
        return precoExemplar;
    }

    public void setPrecoExemplar(float precoExemplar) {
        this.precoExemplar = precoExemplar;
    }

    public EnumSituacaoExemplar getSituacao() {
        return situacao;
    }

    public void setSituacao(EnumSituacaoExemplar situacao) {
        this.situacao = situacao;
    }

    public EnumDisponibilidadeExemplar getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(EnumDisponibilidadeExemplar disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getMotivoInativacao() {
        return motivoInativacao;
    }

    public void setMotivoInativacao(String motivoInativacao) {
        this.motivoInativacao = motivoInativacao;
    }
    public String toString(){
        String saida = id + ";";
        saida+= livro.getId() + ";";
        saida += new SimpleDateFormat("dd/MM/yyyy").format(dataAquisicao) + ";";
        saida += precoExemplar + ";";
        saida += situacao.ordinal() + ";";
        saida += disponibilidade.ordinal() + ";";
        saida += motivoInativacao + ";";
        return saida;
    }

    @Override
    public int compareTo(Exemplar objeto) {
        return livro.getTituloLivro().compareToIgnoreCase(objeto.livro.getTituloLivro());
    }
}

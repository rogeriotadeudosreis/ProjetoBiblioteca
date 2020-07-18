/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import controle.ControleArea;
import controle.ControleAutor;
import controle.ControleEditora;
import interfaces.*;

/**
 *
 * @author roger
 */
public class Livro implements Comparable<Livro> {

    private int id;
    private String isbn;
    private String tituloLivro;
    private String subTituloLivro;
    private Autor autor;
    private Editora editora;
    private AreaCdd area;
    private String edicao;
    private int anoDePublicacao;

    public Livro() throws Exception {

    }

    public Livro(String isbn, String tituloLivro, String subTituloLivro, Autor autor,
            Editora editora, AreaCdd area, String edicao, int anoDePublicacao) throws Exception {
        this.isbn = isbn;
        this.tituloLivro = tituloLivro;
        this.subTituloLivro = subTituloLivro;
        this.autor = autor;
        this.editora = editora;
        this.area = area;
        this.edicao = edicao;
        this.anoDePublicacao = anoDePublicacao;
    }

    public Livro(String strDadosLivro) throws Exception {

        String vetorLivro[] = strDadosLivro.split(";");
        if (vetorLivro.length < 9) {
            throw new Exception("Faltam dados do livro\n");
        }
        id = Integer.parseInt(vetorLivro[0]);
        isbn = vetorLivro[1];
        tituloLivro = vetorLivro[2];
        subTituloLivro = vetorLivro[3];
        InterfaceAutor controleAutor = new ControleAutor("Autores.txt");
        autor = controleAutor.buscarPeloId(Integer.parseInt(vetorLivro[4]));
        InterfaceEditora controleEditora = new ControleEditora("Editoras.txt");
        editora = controleEditora.buscarId(Integer.parseInt(vetorLivro[5]));
        InterfaceAreaDoLivro controleArea = new ControleArea("areaCdd.txt");
        area = controleArea.buscarId(Integer.parseInt(vetorLivro[6]));
        edicao = vetorLivro[7];
        anoDePublicacao = Integer.parseInt(vetorLivro[8]);

    }
    
     public void setLivroSplit (String combBox)throws Exception{        
        String[]vetorString = combBox.split("-");
        if (vetorString.length < 2) {
            throw new Exception();
        }        
        id = Integer.parseInt(vetorString[0]);
        tituloLivro = vetorString[1];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public void setTituloLivro(String tituloLivro) {
        this.tituloLivro = tituloLivro;
    }

    public String getSubTituloLivro() {
        return subTituloLivro;
    }

    public void setSubTituloLivro(String subTituloLivro) {
        this.subTituloLivro = subTituloLivro;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public AreaCdd getArea() {
        return area;
    }

    public void setArea(AreaCdd area) {
        this.area = area;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public int getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public void setAnoDePublicacao(int anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }

    public String toString() {
        String saida = id + ";";
        saida += isbn + ";";
        saida += tituloLivro + ";";
        saida += subTituloLivro + ";";
        saida += autor.getId() + ";";
        saida += editora.getId() + ";";
        saida += area.getId() + ";";
        saida += edicao + ";";
        saida += anoDePublicacao + ";";

        return saida;
    }

    @Override
    public int compareTo(Livro objeto) {
        return tituloLivro.compareToIgnoreCase(objeto.getTituloLivro());
    }

}

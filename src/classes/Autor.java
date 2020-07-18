package classes;

public class Autor implements Comparable<Autor> {

    private int id = 0;
    private String nomeAutor = "";

    public Autor() throws Exception {
        this.id = 0;        
        this.nomeAutor = "";
    }  

    public Autor(int id, String nome) throws Exception {
        this.id = id;        
        this.nomeAutor = nome;
    }  
    
    public Autor(String strDadosAutor) throws Exception {
        String vetorString[] = strDadosAutor.split(";");
        if (vetorString.length < 2) {
            throw new Exception("Dados incompletos\n");
        }
        id = Integer.parseInt(vetorString[0]);        
        nomeAutor = vetorString[1];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setAutorSplit (String combBox)throws Exception{        
        String[]vetorString = combBox.split("-");
        if (vetorString.length < 2) {
            throw new Exception();
        }        
        id = Integer.parseInt(vetorString[0]);
        nomeAutor = vetorString[1];
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    @Override
    public int compareTo(Autor objeto) {
        return nomeAutor.compareToIgnoreCase(objeto.getNomeAutor());
    }

    @Override
    public String toString() {
        return this.id + ";" + this.getNomeAutor() + ";";       
    }

}

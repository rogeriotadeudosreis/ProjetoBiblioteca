package classes;

public class Editora implements Comparable<Editora> {

    private int id = 0;
    private String descricaoEditora = "";

    public Editora() throws Exception {
        this.id = 0;
        this.descricaoEditora = "";
    }

     public Editora(int id, String descricao) throws Exception {
        this.id = id;
        this.descricaoEditora = descricao;
    }
     
    public Editora(String strDadosEditora) throws Exception {
        String vetorString[] = strDadosEditora.split(";");
        if (vetorString.length < 2) {
            throw new Exception("Dados Incompletos\n");
        }
        id = Integer.parseInt(vetorString[0]);
        descricaoEditora = vetorString[1];
    }

    public void setEditoraSplit(String combBox) throws Exception {
        String[] vetorString = combBox.split("-");
        id = Integer.parseInt(vetorString[0]);
        descricaoEditora = vetorString[1];
        if (vetorString.length < 2) {
            throw new Exception();
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricaoEditora() {
        return descricaoEditora;
    }

    public void setDescricaoEditora(String nome) {
        this.descricaoEditora = nome;
    }

    @Override
    public String toString() {
        return this.id + ";" + this.descricaoEditora + ";";
    }

    @Override
    public int compareTo(Editora objeto) {
        return descricaoEditora.compareToIgnoreCase(objeto.getDescricaoEditora());
    }

}

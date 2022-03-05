package mypackage;

public class Artista{
    private String nome, filme;
    private int idade, ano;
    public enum sexo{FEMININO, MASCULINO};
    private sexo genero;

    public Artista(String nome, int idade, String filme, int ano, sexo genero){
        this.ano = ano;
        this.nome = nome;
        this.filme = filme;
        this.idade = idade;
        this.genero = genero;
    }

    public String getNome(){
        return this.nome;
    }

    public String getFilme(){
        return this.filme;
    }

    public int getIdade(){
        return this.idade;
    }

    public int getAno(){
        return this.ano;
    }

    public sexo getGenero(){
        return this.genero;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setFilme(String filme){
        this.filme = filme;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }

    public void setAno(int ano){
        this.ano = ano;
    }

    public void setGenero(sexo genero){
        this.genero = genero;
    }

    public String toString(){
        return this.nome + " (" + this.idade + ")\t" + this.filme + " (" + this.ano + ")\n";
    }

    public void exibirInformacoes(){
        System.out.print(this.toString());
    }

    public String oscar(){
        return "\t" + this.ano + ", " + this.idade + ", " + this.filme + "\n";
    }

    public void exibirOscars(){
        System.out.print(this.oscar());
    }

}

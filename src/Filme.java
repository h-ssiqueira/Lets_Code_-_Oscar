public class Filme{
    private Integer ano;
    private String nome;

    public Filme(String nome, Integer ano){
        this.ano = ano;
        this.nome = nome;
    }

    public Integer getAno(){
        return this.ano;
    }

    public String getNome(){
        return this.nome;
    }

    public String toString(){
        return this.nome + " (" + this.ano + ")";
    }
}

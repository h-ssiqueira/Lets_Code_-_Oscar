public class Artista{
    private String nome;
    private Integer idade;

    public Artista(String nome, Integer idade){
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome(){
        return this.nome;
    }

    public Integer getIdade(){
        return this.idade;
    }

    public String toString(){
        return this.nome + " aos " + this.idade + " anos";
    }

}

public class Premio{
    private Artista artista;
    private Filme filme;

    public Premio(String nome, Integer idade, String filme, Integer ano){
        this.artista = new Artista(nome,idade);
        this.filme = new Filme(filme, ano);
    }

    public static Premio of(String informacoesLinha){
        String[] informacoes = informacoesLinha.split("; ");
        return new Premio(informacoes[3], Integer.parseInt(informacoes[2]), informacoes[4], Integer.parseInt(informacoes[1]));
    }

    public Artista getArtista(){
        return this.artista;
    }

    public Filme getFilme(){
        return this.filme;
    }

    public String oscar(){
        return this.filme.toString() + " aos " + this.artista.getIdade() + " anos.";
    }

    public String toString(){
        return this.artista.toString() + " no filme " + this.filme.toString() + ".";
    }
}

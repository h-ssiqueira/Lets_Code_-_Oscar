import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Base{
    private final List<Premio> listaDePremios;

    public Base(String nomeArquivo){
        this.listaDePremios = lerArquivoCSV(nomeArquivo);
    }

    private List<Premio> lerArquivoCSV(String nomeArquivo){
        try(Stream<String> linhasArquivo = Files.lines(Paths.get(nomeArquivo))){
            return linhasArquivo
                .skip(1)
                .map(Premio::of)
                .collect(Collectors.toList());
        }
        catch(IOException e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Premio> getListaDePremios(){
        return this.listaDePremios;
    }
}

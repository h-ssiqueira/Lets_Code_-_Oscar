import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import mypackage.Artista.sexo;
import mypackage.*;

public class Aplicacao{
    public static void main(String[] args){
        String atores = "oscar_age_male.csv", atrizes = "oscar_age_female.csv";
        List<Artista> artistas = new ArrayList<Artista>();
        String[] results = null;
        sexo genero = sexo.MASCULINO;

        // Coleta das informações de cada ator
        try(Scanner pointer = new Scanner(new File(atores))){
            pointer.nextLine(); // Cabeçalho
            while(pointer.hasNext()){
                results = pointer.nextLine().split("; ");
                artistas.add(new Artista(results[3],Integer.parseInt(results[2]),results[4],Integer.parseInt(results[1]),genero));
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        genero = sexo.FEMININO;
        // Coleta das informações de cada atriz
        try(Scanner pointer = new Scanner(new File(atrizes))){
            pointer.nextLine(); // Cabeçalho
            while(pointer.hasNext()){
                results = pointer.nextLine().split("; ");
                artistas.add(new Artista(results[3],Integer.parseInt(results[2]),results[4],Integer.parseInt(results[1]),genero));
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        /*
        artistas.stream()
            .filter(Artista -> Artista.getGenero() == sexo.FEMININO)
            .forEach(Artista::exibirInformacoes);
        */
        // TODO: partes 1-4
        try(Scanner input = new Scanner(System.in)){
            System.out.print("\nInsira o nome de um ator ou atriz para mais informações.\n-> ");
            final String procurar = input.nextLine();
            artistas.stream()
                .filter(Artista -> Artista.getNome().compareTo(procurar) == 0)
                .forEach(Artista::exibirOscars); // TODO: contador parte 5
        }
    }
}
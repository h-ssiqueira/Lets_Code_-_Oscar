import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Aplicacao{
    private static Base atrizes, atores;
    private static Map<String, Long> estrutura;
    public static void main(String[] args){
        atrizes = new Base("oscar_age_female.csv");
        atores = new Base("oscar_age_male.csv");
        final String procurar;

        System.out.println("┌─────────────────────────────────────────────────────────────────────┐\n│   ________  ________  ________  ________  ________  ________        │\n│  |\\   __  \\|\\   ____\\|\\   ____\\|\\   __  \\|\\   __  \\|\\   ____\\       │\n│  \\ \\  \\|\\  \\ \\  \\___|\\ \\  \\___|\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\___|_      │\n│   \\ \\  \\\\\\  \\ \\_____  \\ \\  \\    \\ \\   __  \\ \\   _  _\\ \\_____  \\     │\n│    \\ \\  \\\\\\  \\|____|\\  \\ \\  \\____\\ \\  \\ \\  \\ \\  \\\\  \\\\|____|\\  \\    │\n│     \\ \\_______\\____\\_\\  \\ \\_______\\ \\__\\ \\__\\ \\__\\\\ _\\ ____\\_\\  \\   │\n│      \\|_______|\\_________\\|_______|\\|__|\\|__|\\|__|\\|__|\\_________\\  │\n│               \\|_________|                            \\|_________|  │\n│                                                                     │\n└─────────────────────────────────────────────────────────────────────┘");
        printLinha();
        atorMaisJovem();
        printLinha();
        atrizMaisPremiada();
        printLinha();
        atrizEntre20E30AnosMaisPremiada();
        printLinha();
        artistasComMaisDeUmOscar();
        printLinha();
        try(Scanner input = new Scanner(System.in)){
            System.out.print("Insira o nome de um ator ou atriz para mais informações:\n-> ");
            procurar = input.nextLine();
        }
        printLinha();
        procurarArtista(procurar);
        printLinha();
    }

    private static void atorMaisJovem(){
        atores.getListaDePremios()
        .stream()
        .min((p1,p2) -> p1.getArtista().getIdade() - p2.getArtista().getIdade())
        .ifPresent(obj -> System.out.println("Ator mais jovem a ganhar um oscar foi:\n-> " + obj.toString()));
    }

    private static void atrizMaisPremiada(){
        /*List<Artista> geral = atrizes.getListaDePremios().stream()
            .map(p -> p.getArtista())
            .collect(Collectors.toList());
        Map<String, Long> categorias = geral.stream()
            .map(Artista::getNome)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        categorias.entrySet().stream()
            .max(Comparator.comparingLong(Entry::getValue))
            .ifPresent(c -> System.out.println(c.getKey() + " com " + c.getValue() + " oscars."));*/

        atrizes.getListaDePremios().stream()
            .map(p -> p.getArtista())
            .map(Artista::getNome)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .max(Comparator.comparingLong(Entry::getValue))
            .ifPresent(c -> System.out.println("Atriz mais premiada:\n-> " + c.getKey() + " com " + c.getValue() + " oscars."));
    }

    private static void atrizEntre20E30AnosMaisPremiada(){
        System.out.println("Atriz entre 20 e 30 anos mais premiada:");
        Map<String, Long> artistas = atrizes.getListaDePremios().stream()
            .map(p -> p.getArtista())
            .filter(a -> a.getIdade() >= 20 && a.getIdade() <= 30)
            .map(Artista::getNome)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if(!artistas.isEmpty()){
            Long max = artistas.entrySet().stream()
                .max((e1, e2) -> Math.toIntExact(e1.getValue() - e2.getValue()))
                .get()
                .getValue();
            artistas.entrySet().stream()
                .filter(e -> e.getValue() == max)
                .forEach(e -> System.out.println("-> " + e.getKey() + " com " + e.getValue() + " oscars."));
        }
    }

    private static void artistasComMaisDeUmOscar(){
        estrutura = atrizes.getListaDePremios().stream()
            .map(p -> p.getArtista())
            .map(Artista::getNome)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        estrutura.putAll(atores.getListaDePremios().stream()
            .map(p -> p.getArtista())
            .map(Artista::getNome)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
        System.out.println("Artistas que ganharam mais de um oscar:");
        estrutura.entrySet().stream()
            .filter(a -> a.getValue() > 1)
            .forEach(a -> System.out.println("-> " + a.getKey() + " com " + a.getValue() + " oscars."));
    }

    private static void procurarArtista(String nome){
        Long totalOscars = estrutura.entrySet().stream()
            .filter(a -> a.getKey().equals(nome))
            .map(Map.Entry::getValue)
            .findFirst()
            .orElse(null);
        if(totalOscars != null && totalOscars > 0){
            System.out.println(nome + " ganhou " + totalOscars + " oscar(s) no(s) seguinte(s) filme(s):");
            atores.getListaDePremios().stream()
                .filter(a -> a.getArtista().getNome().equals(nome))
                .forEach(obj -> System.out.println("-> " + obj.oscar()));
            atrizes.getListaDePremios().stream()
                .filter(a -> a.getArtista().getNome().equals(nome))
                .forEach(obj -> System.out.println("-> " + obj.oscar()));
        }
        else
            System.out.println(nome + " não ganhou oscars até o momento.");
    }

    private static void printLinha(){
        System.out.println("\n\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\|///////////////////////////////////\n");
    }
}
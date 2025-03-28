package br.com.vkl;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Input {
    String comando;
    Scanner scanner = new Scanner(System.in);
    Set<SeresZumanos> lista = new LinkedHashSet<>();


    public static void main(String[] args) {
        Input input = new Input();
        input.menu();
    }

    private void menu() {
        do {
            System.out.println("Selecione uma opcao.");
            System.out.println("1.Adicionar Informação");
            System.out.println("2.Ordenar e listar");
            System.out.println("3.Listar genero especifico");
            System.out.println("Sair");
            this.comando = scanner.nextLine();
            switch (comando) {
                case "1":
                    adicionarInfo();
                    break;
                case "2":
                    listar();
                    break;
                case "3":
                    streamGeneroEspecifico();
                    break;
                case "Sair":
                    System.out.println("Saindo... Até mais!");
                    return;
                default:
                    if (!comando.equalsIgnoreCase("Sair")) {
                        System.out.println("Erro.");
                    }
                    break;

            }

        }while (!comando.equalsIgnoreCase("Sair")) ;
    }

    private void adicionarInfo(){
        System.out.println("**** Escreva as informacoes separando por elas por '-' e pessoas por ',' ****");
        String input = scanner.nextLine();
        String[] rawInfo = input.split(",");
        for (String i : rawInfo){
            if(i.contains("-")){
                SeresZumanos zumanos = new SeresZumanos();
                String[] localRaw = i.split("-");
                String nome = localRaw[0];
                String genero = localRaw[1];
                zumanos.setInfo(nome, genero);
                lista.add(zumanos);
            }
        }
    }

    private void listar() {
        if (!lista.isEmpty()){
            Map<String, List<SeresZumanos>> mapaGeneros = new HashMap<>();
            for (SeresZumanos zumano : lista){
                String genero = zumano.getGenero();
                mapaGeneros.putIfAbsent(genero, new ArrayList<>());
                mapaGeneros.get(genero).add(zumano);
            }
            for (Map.Entry<String, List<SeresZumanos>> entry : mapaGeneros.entrySet()) {
                System.out.println("**** Gênero: " + entry.getKey() + " ****");
                for (SeresZumanos sz : entry.getValue()) {
                    System.out.println(sz);
                }
                System.out.println();
            }
        }else {
            System.out.println("**** Sem Dados ****");
        }
        System.out.println("**** Pressione Enter para continuar ****");
        scanner.nextLine();
    }

    private void streamGeneroEspecifico() {
        System.out.println("**** Digite um genero especifico para ser listado. ****");
        String generoEspecifico = scanner.nextLine().toLowerCase();
        Set<SeresZumanos> setGenEspecifico = lista.stream().filter(sz -> sz.getGenero().equalsIgnoreCase(generoEspecifico)).collect(Collectors.toSet());
        if (!setGenEspecifico.isEmpty()){
            System.out.println("**** Genero: " + generoEspecifico + " ****");
            setGenEspecifico.forEach(System.out::println);
        }else {
            System.out.println("**** Sem Dados ****");
        }
        System.out.println("**** Pressione Enter para continuar ****");
        scanner.nextLine();
    }

}


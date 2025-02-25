package br.com.vkl;
import java.util.*;
import java.util.Arrays;
public class Input {
    String comando;
    Scanner scanner = new Scanner(System.in);
    Set<String> lista = new LinkedHashSet<>();


    public static void main(String[] args) {
        Input input = new Input();
        input.menu();
    }

    private void menu() {
        do {
            System.out.println("Selecione uma opcao.");
            System.out.println("1.Adicionar Nomes");
            System.out.println("2.Ordenar e listar");
            System.out.println("Sair");
            this.comando = scanner.nextLine();
            switch (comando) {
                case "1":
                    adicionarNomes();
                    break;

                case "2":
                    listar();
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

    private void adicionarNomes(){
        System.out.println("**** Escreva os nomes separando por ',' ****");
        String input = scanner.nextLine();
        String[] tempArray = input.split(",");
        lista.addAll(Arrays.asList(tempArray));
    }

    private void listar() {
        if (!lista.isEmpty()){
            System.out.println("**** Nomes por ordem alfabetica ****");
            LinkedList<String> sortedList = new LinkedList<>(lista);
            sortedList.sort((Comparator.reverseOrder()));
            for (String i : sortedList) {
                System.out.println(i);
            }
        }else {
            System.out.println("**** Sem Dados ****");
        }
        System.out.println("**** Pressione Enter para continuar ****");
        scanner.nextLine();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Input input)) return false;
        return Objects.equals(lista, input.lista);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(lista);
    }
}


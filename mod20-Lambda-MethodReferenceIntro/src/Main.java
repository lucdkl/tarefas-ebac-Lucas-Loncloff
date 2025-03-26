import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Predicate<Integer> isPar = n -> n % 2 == 0;
        boolean par1 = isPar.test(10);
        boolean par2 = isPar.test(5);
        Consumer<Object> imprimirObjeto = Main::imprimir; //referencia o proprio metodo imprimir
        imprimirObjeto.accept(par1);
        imprimir(par2);

        List<String> nomes = new ArrayList<>(List.of("Ana", "João"));
        nomes.forEach(nome -> System.out.println(nome));
    }

    private static <T> void imprimir(T obj){
        System.out.println(obj);;
    }

}
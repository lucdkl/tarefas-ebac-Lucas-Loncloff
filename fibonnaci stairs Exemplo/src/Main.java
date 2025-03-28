import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Integer> n = new ArrayList<>();
        n.add(1);
        n.add(2);

        int numeroDegrausAdicionais = 10;
        for (int i = 0; i < numeroDegrausAdicionais; i++){
            int n1 = n.get(n.size()-1);
            int n2 = n.get(n.size()-2);
            int n3 = n1 + n2;
            n.add(n3);
        }
        System.out.println(n);
    }
}
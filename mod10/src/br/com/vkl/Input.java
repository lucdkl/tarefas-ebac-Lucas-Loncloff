package br.com.vkl;
import java.util.*;

public class Input {

    String comando;
    Scanner scanner = new Scanner(System.in);
    NotaAluno notaAluno = new NotaAluno();
    DbAluno dbAluno = new DbAluno();
    List<DbAluno> lista = new LinkedList<>();

    public static void main(String[] args) {
        Input input = new Input();
        input.menu();
    }


    private void menu() {
        do {
            dbAluno = new DbAluno();
            System.out.println("Selecione uma opcao.");
            System.out.println("1.Nota");
            System.out.println("2.Listas");
            System.out.println("Sair");
            this.comando = scanner.next();
            switch (comando) {
                case "1":
                    notas();
                    break;
                case "2":
                    if (!lista.isEmpty()){
                        for (DbAluno i : lista) {
                            System.out.println(i);
                        }
                    }else {
                        System.out.println("Sem dados.");
                    }
                    break;
                default:
                    if (!comando.equalsIgnoreCase("Sair")){
                        System.out.println("Erro.");
                    }
                    break;
            }
        }while (!comando.equalsIgnoreCase("Sair"));
    }

    private void notas() {


        System.out.print("Digite sua nota de Geografia: ");
        notaAluno.setNotaGeografia(Math.max(Math.min(scanner.nextInt(), 10),0));
        System.out.print("Digite sua nota de Matematica: ");
        notaAluno.setNotaMatematica(Math.max(Math.min(scanner.nextInt(), 10),0));
        System.out.print("Digite sua nota de Portugues: ");
        notaAluno.setNotaPortugues(Math.max(Math.min(scanner.nextInt(), 10),0));
        System.out.print("Digite sua nota de Fisica: ");
        notaAluno.setNotaFisica(Math.max(Math.min(scanner.nextInt(), 10),0));
        double media = notaAluno.calcularMedia();
        System.out.println("Sua media é de: " + media + ". ");

        if (media >= 7) {
            System.out.println("Aprovado!");
        } else if (media >= 5) {
            System.out.println("Recuperacao!");
        } else {
            System.out.println("Reprovado!");
        }

        dbAluno.setDbInfo(notaAluno.getNotaGeografia(), notaAluno.getNotaMatematica(), notaAluno.getNotaPortugues(), notaAluno.getNotaFisica(), media);
        lista.add(dbAluno);

    }


}

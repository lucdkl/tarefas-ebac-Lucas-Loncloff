package br.com.vkl;
import java.util.Scanner;

public class Input {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        NotaAluno notaAluno = new NotaAluno();
        System.out.print("Digite sua nota de Geografia: ");
        notaAluno.setNotaGeografia(scanner.nextInt());
        System.out.print("Digite sua nota de Matematica: ");
        notaAluno.setNotaMatematica(scanner.nextInt());
        System.out.print("Digite sua nota de Portugues: ");
        notaAluno.setNotaPortugues(scanner.nextInt());
        System.out.print("Digite sua nota de Fisica: ");
        notaAluno.setNotaFisica(scanner.nextInt());
        scanner.close();
        double media = notaAluno.calcularMedia();
        System.out.println("Sua media é de: " + media + ". ");
        if (media >= 7){
            System.out.println("Aprovado!");
        }else if(media >= 5){
            System.out.println("Recuperacao!");
        }else{
            System.out.println("Reprovado!");
        }
    }
}

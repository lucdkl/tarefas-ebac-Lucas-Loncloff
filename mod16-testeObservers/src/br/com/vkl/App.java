package br.com.vkl;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ObserverManager obManager = new ObserverManager();
        obManager.add(new ObValor());
        obManager.add(new ObPar());
        Scanner scan = new Scanner(System.in);
        for(int i = 0; i < 5; i++){
            System.out.println("Digite um numero:");
            Integer valor = (Integer) scan.nextInt();
            obManager.checkAll(valor);
            scan.nextLine();
        }
        System.out.println("EU NAO AGUENTO MAIS JAVA");
        for (int i = 0; i < 100; i++){
            System.out.println("AAAAAA");
        }
    }
}

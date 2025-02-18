package br.com.vkl;

public class sistemaEscola {
    public static void main (String args[]){

        Notas notas = new Notas();
        notas.setNotaGeografia(1);
        notas.setNotaMatematica(8);
        notas.setNotaPortugues(6);
        notas.setNotaFisica(5);
        double media = notas.calcularMedia();
        System.out.println("A media das notas é: " + media);

    }
}

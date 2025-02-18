package br.com.vkl;

public class Notas {

    private int notaGeografia;

    private int notaMatematica;

    private int notaPortugues;

    private int notaFisica;

    public int getNotaGeografia() {
        return notaGeografia;
    }

    public void setNotaGeografia(int notaGeografia) {
        this.notaGeografia = notaGeografia;
    }

    public int getNotaMatematica() {
        return notaMatematica;
    }

    public void setNotaMatematica(int notaMatematica) {
        this.notaMatematica = notaMatematica;
    }

    public int getNotaPortugues() {
        return notaPortugues;
    }

    public void setNotaPortugues(int notaPortugues) {
        this.notaPortugues = notaPortugues;
    }

    public int getNotaFisica() {
        return notaFisica;
    }

    public void setNotaFisica(int notaFisica) {
        this.notaFisica = notaFisica;
    }
    public double calcularMedia () {
        double media = (notaMatematica + notaGeografia + notaPortugues + notaFisica) / 4;
        return media;
    }
}


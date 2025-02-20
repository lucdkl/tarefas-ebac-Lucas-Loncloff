package br.com.vkl;

public class DbAluno {

    int nota1;
    int nota2;
    int nota3;
    int nota4;
    double media;

    public void setDbInfo(int nota1, int nota2, int nota3, int nota4, double media) {
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.nota4 = nota4;
        this.media = media;
    }

    @Override
    public String toString() {
        return "DbAluno{" +
                "Media=" + media +
                ", Nota Geografia =" + nota1 +
                ", Nota Matematica =" + nota2 +
                ", Nota Portugues =" + nota3 +
                ", Nota Fisica =" + nota4 +
                '}';
    }

}

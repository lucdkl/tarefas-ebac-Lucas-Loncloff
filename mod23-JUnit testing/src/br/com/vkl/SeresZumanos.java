package br.com.vkl;

import java.util.Objects;

public class SeresZumanos {
    private String nome;
    private String genero;


    public SeresZumanos(){}

    public SeresZumanos(String nome, String genero){
        this.nome = nome;
        this.genero = genero;
    }

    public void setInfo (String nome, String genero){
        this.nome = nome;
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Gênero: " + genero;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SeresZumanos that = (SeresZumanos) o;
        return Objects.equals(nome, that.nome) && Objects.equals(genero, that.genero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, genero);
    }

}

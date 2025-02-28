package br.com.vkl;

import java.util.Objects;

public abstract class Pessoa {

    private Short idade;

    private String nome;

    private String tipo;

    public abstract Long getDoc();

    public void setIdade(Short idade) {
        this.idade = idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Short getIdade() {
        return idade;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "idade=" + idade +
                ", nome='" + nome + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(idade, pessoa.idade)
                && Objects.equals(nome, pessoa.nome)
                && Objects.equals(tipo, pessoa.tipo)
                && Objects.equals(this.getDoc(), pessoa.getDoc());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idade, nome, tipo, getDoc());
    }
}

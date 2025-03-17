package br.com.vkl.domain;

public abstract class Car {

    String modelo;
    String tipo;
    String marca;
    String motor;
    String cor;
    boolean arCondicionado;
    boolean direcaoHidraulica;


    public Car(String modelo,String tipo, String marca, String motor, String cor, boolean arCondicionado, boolean direcaoHidraulica){
        this.modelo = modelo;
        this.tipo = tipo;
        this.marca = marca;
        this.motor = motor;
        this.cor = cor;
        this.arCondicionado = arCondicionado;
        this.direcaoHidraulica = direcaoHidraulica;
    }

    @Override
    public String toString() {
        return "Car{" +
                "modelo='" + modelo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", marca='" + marca + '\'' +
                ", motor='" + motor + '\'' +
                ", cor='" + cor + '\'' +
                ", arCondicionado=" + arCondicionado +
                ", direcaoHidraulica=" + direcaoHidraulica +
                '}';
    }
}

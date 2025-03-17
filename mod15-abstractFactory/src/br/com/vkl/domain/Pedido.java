package br.com.vkl.domain;

public class Pedido {
    String montadora;
    int modelo;

    public Pedido(String montadoraIndex, int pedido){
        this.montadora = montadoraIndex;
        this.modelo = pedido;
    }

    public int getModelo(){
        return modelo;
    }

    public String getMontadora(){
        return montadora;
    }
}

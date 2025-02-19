package br.com.vkl;

public class Mundo {





    //Gerar um novo avião no mundo.
    public static void main(String[] args){
        Aviao aviao = new Aviao();
        aviao.setModelo("Northrop F-5");
        aviao.setAltitude(3000);
        aviao.setVelocidade(1100);
        aviao.informacoesDeVoo();
    }
}

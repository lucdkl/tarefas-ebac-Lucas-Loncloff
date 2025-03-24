package br.com.vkl;

public class ObPar implements Observer{

    @Override
    public void update(Integer valor) {
        System.out.println("Valor é par?:" + (valor % 2 == 0));
    }
}

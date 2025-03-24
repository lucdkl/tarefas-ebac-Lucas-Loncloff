package br.com.vkl;

public class ObValor implements Observer{

    @Override
    public void update(Integer valor) {
        System.out.println("Valor é positivo?:" + (valor >= 0));
    }
}

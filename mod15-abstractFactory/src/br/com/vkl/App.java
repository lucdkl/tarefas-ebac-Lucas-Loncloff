package br.com.vkl;

import br.com.vkl.domain.Car;
import br.com.vkl.domain.Factory;
import br.com.vkl.domain.Pedido;
import br.com.vkl.factories.FiatFactory;
import br.com.vkl.factories.RenaultFactory;

public class App {
    public static void main(String[] args) {
        Car car1 = criarPedido("renault", 1);
        System.out.println(car1);
        Car car2 = criarPedido("fiat", 1);
        System.out.println(car2);
        Car car3 = criarPedido("Renault", 0);
        System.out.println(car3);
        Car car4 = criarPedido("mitsubishi", 0);
        System.out.println(car4);
    }

    public static Car criarPedido(String montadora, int modelo){
        Pedido pedido = new Pedido(montadora, modelo);
        Factory factory = getFactory(pedido);
        if (factory != null){
            Car car = factory.create(pedido.getModelo());
            return car;
        }else {
            System.out.println("Fabrica Invalida.");
            return null;
        }

    }

    private static Factory getFactory(Pedido pedido) {
        switch (pedido.getMontadora().toLowerCase()){
            case "renault" -> {
                return new RenaultFactory();
            }
            case "fiat" ->{
                return new FiatFactory();
            }
            default -> {
                return null;
            }
        }
    }


}

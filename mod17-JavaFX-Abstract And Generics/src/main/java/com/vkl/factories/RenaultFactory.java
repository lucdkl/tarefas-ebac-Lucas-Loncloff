package com.vkl.factories;


import com.vkl.cars.Twingo;
import com.vkl.domain.Car;
import com.vkl.domain.Factory;

public class RenaultFactory extends Factory {

    @Override
    public Car createCar(int index) {
        switch (index){
            case 1 -> {
                return new Twingo("Twingo", "Padrão", "Renault", "1.6", "Azul", false, true);
            }

            case 2 -> {
                return new Twingo("Twingo", "Economico", "Renault", "1.0", "Cinza", false, false);
            }

            case 3 -> {
                return new Twingo("Twingo", "Esportivo", "Renault", "2.0", "Vermelho", true, true);
            }

            default -> {
                System.out.println("Modelo Incorreto.");
                return null;
            }
        }
    }
}

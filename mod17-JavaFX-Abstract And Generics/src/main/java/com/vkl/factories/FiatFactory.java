package com.vkl.factories;


import com.vkl.cars.Uno;
import com.vkl.domain.Car;
import com.vkl.domain.Factory;

public class FiatFactory extends Factory{
    @Override
    public Car createCar(int index) {
        switch (index){
            case 1 -> {
                return new Uno("Uno", "Padrão", "Fiat", "1.2", "Preto", false, false);
            }

            case 2 -> {
                return new Uno("Uno", "Economico", "Fiat", "1.0", "Branco", false, false);
            }

            case 3 -> {
                return new Uno("Uno", "Esportivo", "Fiat", "1.6 Turbo", "Vermelho", false, true);
            }

            default -> {
                System.out.println("Modelo Incorreto.");
                return null;
            }
        }
    }
}

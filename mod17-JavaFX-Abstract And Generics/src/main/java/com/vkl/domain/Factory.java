package com.vkl.domain;

import com.vkl.factories.FiatFactory;
import com.vkl.factories.RenaultFactory;

public abstract class Factory {

    public Car create (int index){
        Car car = createCar(index);
        return car;
    }

    public abstract Car createCar(int index);

    public static Factory getFactory(int montadoraIndex) {
        switch (montadoraIndex){
            case 1 -> {
                return new RenaultFactory();
            }
            case 2 ->{
                return new FiatFactory();
            }
            default -> {
                return null;
            }
        }
    }
}

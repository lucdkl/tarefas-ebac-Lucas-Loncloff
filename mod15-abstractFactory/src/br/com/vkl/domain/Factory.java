package br.com.vkl.domain;

public abstract class Factory {

    public Car create (int index){
        Car car = createCar(index);
        return car;
    }

    public abstract Car createCar(int index);

}

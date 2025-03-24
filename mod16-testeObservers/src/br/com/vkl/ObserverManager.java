package br.com.vkl;

import java.util.ArrayList;
import java.util.List;

public class ObserverManager implements Subject {

    public List<Observer> observers = new ArrayList<>();

    public Integer valor;

    @Override
    public void add(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void checkAll(Integer valor) {
        this.valor = valor;
        observers.forEach(observer -> observer.update(valor));
    }
}

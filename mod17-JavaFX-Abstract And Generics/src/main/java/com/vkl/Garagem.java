package com.vkl;

import com.vkl.domain.Car;

import java.util.ArrayList;
import java.util.List;

public class Garagem {

   private List<Object> carros = new ArrayList<>();

    public void adicionarCarros(Car carro) {
        carros.add(carro);
    }

    public void venderCarro(int index) {
        carros.remove(index);
    }
}

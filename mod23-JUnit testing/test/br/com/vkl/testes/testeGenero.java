package br.com.vkl.testes;

import br.com.vkl.Input;
import br.com.vkl.SeresZumanos;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class testeGenero {

    @Test
    public void testeFeminino(){
        Input testeInput = new Input();
        SeresZumanos teste1 = new SeresZumanos("Teste1", "feminino");
        SeresZumanos teste2 = new SeresZumanos("Teste2", "masculino");
        testeInput.addLista(teste1);
        testeInput.addLista(teste2);
        Set<SeresZumanos> testeGeneroSet = testeInput.getGeneroTesting("FEMININO");
        Assert.assertNotEquals(0,testeGeneroSet.size());
    }

}
package br.com.vkl.dao;

import br.com.vkl.domain.Carro;
import br.com.vkl.domain.Marca;

import java.util.List;

public interface IMarcaDao {

    public Marca cadastrar(Marca marca);

    public Marca consultar (String codigo);

    public List<Marca> consultarTodos();

    public Marca atualizar (Marca marca);

    public void excluir (Marca marca);
}

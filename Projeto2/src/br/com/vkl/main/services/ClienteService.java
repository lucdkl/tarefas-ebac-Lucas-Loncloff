package br.com.vkl.main.services;

import br.com.vkl.main.dao.IClienteDAO;
import br.com.vkl.main.domain.Cliente;
import br.com.vkl.main.domain.Persistente;
import reflections.anotacao.cadastro.exception.TipoChaveNaoEncontradaException;

public class ClienteService implements IClienteService {

    private IClienteDAO clienteDAO;



    public ClienteService(IClienteDAO clienteDAO){
        this.clienteDAO = clienteDAO;
    }

    @Override
    public Boolean salvar(Persistente entity) throws TipoChaveNaoEncontradaException {
        return true;
    }

    @Override
    public Persistente buscarPorCpf(Long valor) {
        return clienteDAO.consultar(valor);
    }

    @Override
    public void excluir(Long valor) {

    }

    @Override
    public void alterar(Persistente entity) throws TipoChaveNaoEncontradaException {

    }
}

package br.com.rpires.service;

import br.com.rpires.dao.IContratoDao;

/**
 * @author rodrigo.pires
 */
public class ContratoService implements IContratoService {

    private IContratoDao contratoDao;

    public ContratoService(IContratoDao dao) {
        this.contratoDao = dao;
    }

    @Override
    public String salvar() {
        contratoDao.salvar();
        return "Salvado com sucesso";
    }

    @Override
    public String buscar() {
        contratoDao.buscar();
        return "Buscado com sucesso";
    }

    @Override
    public String excluir() {
        contratoDao.excluir();
        return "Excluido com sucesso";
    }

    @Override
    public String atualizar() {
        contratoDao.atualizar();
        return "Atualizado com sucesso";
    }
}

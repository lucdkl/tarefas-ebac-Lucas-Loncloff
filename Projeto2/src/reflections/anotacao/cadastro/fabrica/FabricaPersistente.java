package reflections.anotacao.cadastro.fabrica;


import reflections.anotacao.cadastro.exception.DadosInvalidosException;

/**
 * @author rodrigo.pires
 *
 * Fábrica que sabe criar objetos da aplicação
 */
public interface FabricaPersistente {

    /**
     * Método que sabe criar objetos da aplicação
     *
     * @param dados São os dados a serem cadastrados no banco de dados
     * @return Retorna o objeto <b>Persistente</b> que é pai de todas as entidades da aplicação
     * @throws DadosInvalidosException quando os dados estão inválidos
     */
    br.com.vkl.main.domain.Persistente criarObjeto(String[] dados) throws DadosInvalidosException, reflections.anotacao.cadastro.exception.DadosInvalidosException;

}

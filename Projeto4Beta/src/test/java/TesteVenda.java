import br.com.vkl.dao.*;
import br.com.vkl.domain.Produto;
import br.com.vkl.domain.Venda;
import br.com.vkl.exceptions.DAOException;
import br.com.vkl.exceptions.MaisDeUmRegistroException;
import br.com.vkl.exceptions.TableException;
import br.com.vkl.exceptions.TipoChaveNaoEncontradaException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class TesteVenda {

    private IClienteDAO clienteDAO;

    private IProdutoDAO produtoDAO;

    private IVendaDAO vendaDAO;

    private String codigo;

    private Long valor;

    public TesteVenda(){
        this.clienteDAO = new ClienteDAO();
        this.produtoDAO = new ProdutoDAO();
        this.vendaDAO = new VendaDAO();
        codigo = String.valueOf(ThreadLocalRandom.current().nextLong(1000000000L, 10000000000L));;
        valor = ThreadLocalRandom.current().nextLong(10L, 100L);
    }

    public Produto criarProdutoHolder(){



    }


    @Test
    public void testeGeral() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {



    }
}
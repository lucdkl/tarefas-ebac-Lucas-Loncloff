import br.com.vkl.dao.ProdutoDAO;
import br.com.vkl.dao.IProdutoDAO;
import br.com.vkl.domain.Produto;
import br.com.vkl.exceptions.DAOException;
import br.com.vkl.exceptions.MaisDeUmRegistroException;
import br.com.vkl.exceptions.TableException;
import br.com.vkl.exceptions.TipoChaveNaoEncontradaException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class TesteProduto {

    private IProdutoDAO produtoDAO;

    private String codigo;

    private Long valor;

    public TesteProduto(){
        this.produtoDAO = new ProdutoDAO();
        codigo = String.valueOf(ThreadLocalRandom.current().nextLong(1000000000L, 10000000000L));;
        valor = ThreadLocalRandom.current().nextLong(10L, 100L);
    }

    private Produto criarProduto() {
        Produto produto = new Produto();
        produto.setNome("teste");
        produto.setCodigo(codigo);
        produto.setValor(BigDecimal.valueOf(valor));
        produto.setDescricao("produtoTeste");
        return produto;
    }

    @Test
    public void testeGeral() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {

        Produto produto = criarProduto();

        Assert.assertNotNull(produtoDAO.cadastrar(produto));

        produto.setNome("teste da silva");

        Assert.assertNotNull(produtoDAO.alterar(produto));

        Assert.assertEquals(produtoDAO.consultar(produto.getId()).getId(), produto.getId());

        Collection<Produto> produtoList = produtoDAO.buscarTodos();

        Boolean existe = produtoList.stream().anyMatch(c -> c.getId().equals(produto.getId()));

        Assert.assertTrue(existe);

        produtoDAO.excluir(produto);

        Assert.assertNull(produtoDAO.consultar(produto.getId()));

    }
}
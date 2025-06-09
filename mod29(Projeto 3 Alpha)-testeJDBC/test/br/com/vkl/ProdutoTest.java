package br.com.vkl;

import br.com.vkl.dao.IProdutoDAO;
import br.com.vkl.dao.ProdutoDAO;
import br.com.vkl.domain.Cliente;
import br.com.vkl.domain.Produto;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProdutoTest {

    @Test
    public void testProduto() throws Exception{

        ProdutoDAO dao = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("01");
        produto.setNome("Bananay");

        Integer qtd = dao.cadastrar(produto);
        assertTrue(qtd == 1);

        Produto produtoBD = dao.consultar(produto.getCodigo());
        assertNotNull(produtoBD);
        assertNotNull(produtoBD.getId());
        assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        assertEquals(produto.getNome(), produtoBD.getNome());

        produtoBD.setNome("Bananeiro");
        Integer qtdUp = dao.atualizar(produtoBD);
        assertTrue(qtdUp == 1);

        List<Produto> lista = dao.consultarTodos();
        assertNotNull(lista);

        Integer qtdDel = dao.excluir(produtoBD);
        assertNotNull(qtdDel == 1);
    }
}

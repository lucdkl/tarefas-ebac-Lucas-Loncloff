/**
 * 
 */
package br.com.rpires;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;

import br.com.rpires.dao.EstoqueDao;
import br.com.rpires.domain.Estoque;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import br.com.rpires.dao.IProdutoDAO;
import br.com.rpires.dao.ProdutoDAO;
import br.com.rpires.domain.Produto;
import br.com.rpires.exceptions.DAOException;
import br.com.rpires.exceptions.MaisDeUmRegistroException;
import br.com.rpires.exceptions.TableException;
import br.com.rpires.exceptions.TipoChaveNaoEncontradaException;

import static org.junit.Assert.*;

/**
 * @author rodrigo.pires
 *
 */
public class ProdutoDAOTest {
	
	private IProdutoDAO produtoDao;

	private EstoqueDao estoqueDao = new EstoqueDao();

	public ProdutoDAOTest() {
		produtoDao = new ProdutoDAO();
	}

	@After
	public void end() throws DAOException {
		Collection<Produto> list2 = produtoDao.buscarTodos();
		list2.forEach(prod -> {
			try {
				produtoDao.excluir(prod.getCodigo());
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@Test
	public void testeEstoqueDeleteAll() throws DAOException {
		Collection<Estoque> list = estoqueDao.buscarTodos();
		list.forEach(estoq -> {
			try {
				estoqueDao.excluir(estoq.getId());
			} catch (DAOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}


	private Produto criarProdutoComEstoque(String codigo) throws TipoChaveNaoEncontradaException, DAOException {
		Produto produto = new Produto();
		produto.setCodigo(codigo);
		produto.setDescricao("Produto 1");
		produto.setNome("Produto 1");
		produto.setMarca("Marca 1");
		produto.setValor(BigDecimal.TEN);
		produtoDao.cadastrar(produto);
		produtoDao.setEstoque(produto, 10);
		return produto;
	}

	private Produto criarProdutoSemEstoque(String codigo) throws TipoChaveNaoEncontradaException, DAOException {
		Produto produto = new Produto();
		produto.setCodigo(codigo);
		produto.setDescricao("Produto 1");
		produto.setNome("Produto 1");
		produto.setMarca("Marca 1");
		produto.setValor(BigDecimal.TEN);
		produtoDao.cadastrar(produto);
		return produto;
	}

	
	private void excluir(String valor) throws DAOException {
		this.produtoDao.excluir(valor);
	}
	
	@Test
	public void pesquisar() throws MaisDeUmRegistroException, TableException, DAOException, TipoChaveNaoEncontradaException {
		Produto produto = criarProdutoComEstoque("A1");
		Assert.assertNotNull(produto);
		Produto produtoDB = this.produtoDao.consultar(produto.getCodigo());
		Assert.assertNotNull(produtoDB);
		excluir(produtoDB.getCodigo());
	}
	
	@Test
	public void salvar() throws TipoChaveNaoEncontradaException, DAOException {
		Produto produto = criarProdutoComEstoque("A2");
		Assert.assertNotNull(produto);
		excluir(produto.getCodigo());
	}
	
	@Test
	public void excluir() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {
		Produto produto = criarProdutoComEstoque("A3");
		Assert.assertNotNull(produto);
		excluir(produto.getCodigo());
		Produto produtoBD = this.produtoDao.consultar(produto.getCodigo());
		assertNull(produtoBD);
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
		Produto produto = criarProdutoComEstoque("A4");
		produto.setNome("Rodrigo Pires");
		produtoDao.alterar(produto);
		Produto produtoBD = this.produtoDao.consultar(produto.getCodigo());
		assertNotNull(produtoBD);
		Assert.assertEquals("Rodrigo Pires", produtoBD.getNome());
		
		excluir(produto.getCodigo());
		Produto produtoBD1 = this.produtoDao.consultar(produto.getCodigo());
		assertNull(produtoBD1);
	}
	
	@Test
	public void buscarTodos() throws DAOException, TipoChaveNaoEncontradaException {
		criarProdutoComEstoque("A5");
		criarProdutoComEstoque("A6");
		Collection<Produto> list = produtoDao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
		
		for (Produto prod : list) {
			excluir(prod.getCodigo());
		}
		
		list = produtoDao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 0);
		
	}

	@Test
	public void procurarEsetNoEstoque() throws DAOException, TipoChaveNaoEncontradaException {
		Produto produto = criarProdutoSemEstoque("A7");
		Integer qtd = 10;
		produtoDao.setEstoque(produto, qtd);
		assertEquals(produtoDao.consultarEstoque(produto), qtd);
	}
}

import br.com.vkl.dao.*;
import br.com.vkl.domain.Acessorio;
import br.com.vkl.domain.Carro;
import br.com.vkl.domain.Marca;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CarroTeste {


    private ICarroDao carroDao;
    private IMarcaDao marcaDao;
    private IAcessorioDao acessorioDao;

    public CarroTeste() {
        carroDao = new CarroDao();
        marcaDao = new MarcaDao();
        acessorioDao = new AcessorioDao();
    }

    public Carro criarCarro(String codigo){
        Carro carro = new Carro();
        carro.setCodigo(codigo);
        carro.setNome("Twingo");
        carro.setMarca(pegarMarcaTesteBanco());
        carro.
        return carro;
    }

    private Marca pegarMarcaTesteBanco() {
        if (marcaDao.consultar("TESTHOLD") == null){
            marcaDao.cadastrar(criarMarcaTeste());
        }
        return marcaDao.consultar("TESTHOLD");
    }

    public Marca criarMarcaTeste(){
        Marca marca = new Marca();
        marca.setCodigo("TESTHOLD");
        marca.setNome("testeMarca");
        return marca;
    }

    private Marca pegarAcessTesteBanco() {
        if (marcaDao.consultar("TESTHOLD") == null){
            marcaDao.cadastrar(criarMarcaTeste());
        }
        return marcaDao.consultar("TESTHOLD");
    }

    public Acessorio criarAcessorioTeste(){
        Acessorio acessorio = new Acessorio();
        acessorio.setCodigo("TESTHOLD");
        acessorio.setNome("testeMarca");
        return acessorio;
    }

    @Test
    public void testeGeral() {

        Carro carro = criarCarro("A1");

        Assert.assertNotNull(carroDao.cadastrar(carro));

        carro.setNome("TWINGO 2");

        Assert.assertNotNull(carroDao.atualizar(carro));

        Assert.assertEquals(carroDao.consultar("A1").getCodigo(), carro.getCodigo());

        List<Carro> listCarro = carroDao.consultarTodos();

        Boolean existe = listCarro.stream().anyMatch(c -> c.getCodigo().equals("A1"));

        Assert.assertTrue(existe);

        carroDao.excluir(carro);

        Assert.assertNull(carroDao.consultar("A1"));

    }

}

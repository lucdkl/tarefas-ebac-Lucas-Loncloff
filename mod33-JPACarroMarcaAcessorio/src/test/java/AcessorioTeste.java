import br.com.vkl.dao.*;
import br.com.vkl.domain.Acessorio;
import br.com.vkl.domain.Carro;
import br.com.vkl.domain.Marca;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AcessorioTeste {


    private IAcessorioDao acessoriosDao;

    private ICarroDao carroDao;

    private IMarcaDao marcaDao;


    public AcessorioTeste() {
        acessoriosDao = new AcessorioDao();
        carroDao = new CarroDao();
        marcaDao = new MarcaDao();
    }

    public Acessorio criarAcessorios(String codigo){
        Acessorio acessorio = new Acessorio();
        acessorio.setCodigo(codigo);
        acessorio.setNome("Ar Condicionado");
        return acessorio;
    }

    private Carro pegarCarroTesteBanco() {
        if (carroDao.consultar("TESTHOLD") == null){
            carroDao.cadastrar(criarCarroTeste());
        }
        return carroDao.consultar("TESTHOLD");
    }

    public Carro criarCarroTeste(){
        Carro carro = new Carro();
        carro.setCodigo("TESTHOLD");
        carro.setNome("testeCarro");
        carro.setMarca(pegarMarcaTesteBanco());
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

    @Test
    public void testeGeral() {

        Acessorio acessorio = criarAcessorios("A1");

        Assert.assertNotNull(acessoriosDao.cadastrar(acessorio));

        acessorio.setNome("Ar Condicionado MEGA POWER");

        Assert.assertNotNull(acessoriosDao.atualizar(acessorio));

        Assert.assertEquals(acessoriosDao.consultar("A1").getCodigo(), acessorio.getCodigo());

        List<Acessorio> listAcessorios = acessoriosDao.consultarTodos();

        Boolean existe = listAcessorios.stream().anyMatch(c -> c.getCodigo().equals("A1"));

        Assert.assertTrue(existe);

        acessoriosDao.excluir(acessorio);

        Assert.assertNull(acessoriosDao.consultar("A1"));

    }

}

import br.com.vkl.dao.*;
import br.com.vkl.domain.Carro;
import br.com.vkl.domain.Marca;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MarcaTeste {


    private IMarcaDao marcaDao;

    private ICarroDao carroDao;

    public MarcaTeste() {
        marcaDao = new MarcaDao();
        carroDao = new CarroDao();
    }

    public Marca criarMarca(String codigo){
        Marca marca = new Marca();
        marca.setCodigo(codigo);
        marca.setNome("Renault");
        return marca;
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
        return carro;
    }

    @Test
    public void testeGeral() {

        Marca marca = criarMarca("A1");

        Assert.assertNotNull(marcaDao.cadastrar(marca));

        marca.setNome("Renault ULTRA");

        Assert.assertNotNull(marcaDao.atualizar(marca));

        Assert.assertEquals(marcaDao.consultar("A1").getCodigo(), marca.getCodigo());

        List<Marca> listMarca = marcaDao.consultarTodos();

        Boolean existe = listMarca.stream().anyMatch(c -> c.getCodigo().equals("A1"));

        Assert.assertTrue(existe);

        marcaDao.excluir(marca);

        Assert.assertNull(marcaDao.consultar("A1"));

    }

}

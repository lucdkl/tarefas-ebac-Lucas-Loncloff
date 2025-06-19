package br.com.vkl;

import br.com.vkl.dao.CursoDao;
import br.com.vkl.dao.ICursoDao;
import br.com.vkl.domain.Curso;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CursoTest {

    private ICursoDao cursoDao;

    public CursoTest() {
        cursoDao = new CursoDao();
    }

    public Curso criarCurso(String codigo){
        Curso curso = new Curso();
        curso.setCodigo(codigo);
        curso.setNome("TOP 10 CURSOS");
        curso.setDescricao("OS MELHORES CURSOS HELL YEAH");
        return curso;
    }
    @Test
    public void testeGeral() {

        Curso curso = criarCurso("A1");

        Assert.assertNotNull(cursoDao.cadastrar(curso));

        curso.setNome("DEFINITIVAMENTE OS MELHORES CURSOS");

        Assert.assertNotNull(cursoDao.atualizar(curso));

        Assert.assertEquals(cursoDao.consultar("A1").getCodigo(), curso.getCodigo());

        List<Curso> listCurso = cursoDao.consultarTodos();

        Boolean existe = listCurso.stream().anyMatch(c -> c.getCodigo().equals("A1"));

        Assert.assertTrue(existe);

        cursoDao.excluir(curso);

        Assert.assertNull(cursoDao.consultar("A1"));
        
    }
}

package br.com.vkl.dao;

import br.com.vkl.domain.Acessorio;

import javax.persistence.*;
import java.util.List;

public class AcessorioDao implements IAcessorioDao {

    private EntityManagerFactory entityManagerFactory;

    public AcessorioDao() {
        this.entityManagerFactory =
                Persistence.createEntityManagerFactory("JPAMODULO33");
    }

    @Override
    public Acessorio cadastrar(Acessorio acessorio) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(acessorio);
            entityManager.getTransaction().commit();
            return acessorio;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Acessorio consultar(String codigo) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<Acessorio> query = entityManager.createQuery(
                    "SELECT a FROM Acessorio a WHERE a.codigo = :codigo", Acessorio.class);
            query.setParameter("codigo", codigo);

            return query.getSingleResult();

        } catch (NoResultException e){
            return null;
        } finally {
            entityManager.close();
        }

    }

    @Override
    public List<Acessorio> consultarTodos() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<Acessorio> query = entityManager.createQuery("SELECT a FROM Acessorio a", Acessorio.class);
            return query.getResultList();
        }finally {
            entityManager.close();
        }

    }

    @Override
    public Acessorio atualizar(Acessorio acessorio) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Acessorio acessorioAtualizado = entityManager.merge(acessorio);
            entityManager.getTransaction().commit();
            return acessorioAtualizado;
        }finally {
            entityManager.close();
        }

    }

    @Override
    public void excluir(Acessorio acessorio) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            acessorio = entityManager.merge(acessorio);
            entityManager.remove(acessorio);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }


    public void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }


}

package br.com.vkl.dao;

import br.com.vkl.domain.Marca;

import javax.persistence.*;
import java.util.List;

public class MarcaDao implements IMarcaDao{


    private EntityManagerFactory entityManagerFactory;

    public MarcaDao() {
        this.entityManagerFactory =
                Persistence.createEntityManagerFactory("JPAMODULO33");
    }

    @Override
    public Marca cadastrar(Marca marca) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(marca);
            entityManager.getTransaction().commit();
            return marca;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Marca consultar(String codigo) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<Marca> query = entityManager.createQuery(
                    "SELECT c FROM Marca c WHERE c.codigo = :codigo", Marca.class);
            query.setParameter("codigo", codigo);

            return query.getSingleResult();

        } catch (NoResultException e){
            return null;
        } finally {
            entityManager.close();
        }

    }

    @Override
    public List<Marca> consultarTodos() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<Marca> query = entityManager.createQuery("SELECT c FROM Marca c", Marca.class);
            return query.getResultList();
        }finally {
            entityManager.close();
        }

    }

    @Override
    public Marca atualizar(Marca marca) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Marca marcaAtualizado = entityManager.merge(marca);
            entityManager.getTransaction().commit();
            return marcaAtualizado;
        }finally {
            entityManager.close();
        }

    }

    @Override
    public void excluir(Marca marca) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            marca = entityManager.merge(marca);
            entityManager.remove(marca);
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

package br.com.vkl.dao;

import br.com.vkl.domain.Carro;

import javax.persistence.*;
import java.util.List;

public class CarroDao implements ICarroDao{

    private EntityManagerFactory entityManagerFactory;

    public CarroDao() {
        this.entityManagerFactory =
                Persistence.createEntityManagerFactory("JPAMODULO33");
    }

    @Override
    public Carro cadastrar(Carro carro) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(carro);
            entityManager.getTransaction().commit();
            return carro;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Carro consultar(String codigo) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<Carro> query = entityManager.createQuery(
                    "SELECT c FROM Carro c WHERE c.codigo = :codigo", Carro.class);
            query.setParameter("codigo", codigo);

            return query.getSingleResult();

        } catch (NoResultException e){
            return null;
        } finally {
            entityManager.close();
        }

    }

    @Override
    public List<Carro> consultarTodos() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<Carro> query = entityManager.createQuery("SELECT c FROM Carro c", Carro.class);
            return query.getResultList();
        }finally {
            entityManager.close();
        }

    }

    @Override
    public Carro atualizar(Carro carro) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Carro carroAtualizado = entityManager.merge(carro);
            entityManager.getTransaction().commit();
            return carroAtualizado;
        }finally {
            entityManager.close();
        }

    }

    @Override
    public void excluir(Carro carro) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            carro = entityManager.merge(carro);
            entityManager.remove(carro);
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

package br.com.vkl.dao.generic;

import br.com.vkl.dao.Persistente;
import br.com.vkl.exceptions.DAOException;
import br.com.vkl.exceptions.MaisDeUmRegistroException;
import br.com.vkl.exceptions.TableException;
import br.com.vkl.exceptions.TipoChaveNaoEncontradaException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public abstract class GenericDAO<T extends Persistente, E extends Serializable> implements IGenericDAO<T,E>{

    private EntityManagerFactory entityManagerFactory;

    private final Class<T> classPersistente;

    public GenericDAO(Class<T> classPersistente) {
        this.entityManagerFactory = Persistence.createEntityManagerFactory
                ("Projeto4Jpa");
        this.classPersistente = classPersistente;
    }

    private void setClassName() {
    }

    @Override
    public T cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return entity;
        } finally {
            entityManager.close();
        }

    }



    @Override
    public T consultar(E valor) throws MaisDeUmRegistroException, TableException, DAOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            T entity = entityManager.find(this.classPersistente, valor);
            entityManager.getTransaction().commit();
            return entity;

        } catch (NoResultException e){
            return null;
        } finally {
            entityManager.close();
        }

    }

//    @Override
//    public T consultar(Long valor) throws MaisDeUmRegistroException, TableException, DAOException {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        try {
//            String jpql = "SELECT e FROM " + classPersistente.getSimpleName() + " e WHERE e.id = :valor";
//            TypedQuery<T> query = entityManager.createQuery(jpql, classPersistente);
//            query.setParameter("valor", valor);
//            return query.getSingleResult();
//
//        } catch (NoResultException e){
//            return null;
//        } finally {
//            entityManager.close();
//        }
//
//    }

    @Override
    public Collection buscarTodos() throws DAOException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            String jpql = "SELECT e FROM " + classPersistente.getSimpleName() + " e";
            TypedQuery<T> query = entityManager.createQuery(jpql, classPersistente);
            return query.getResultList();
        }finally {
            entityManager.close();
        }

    }

    @Override
    public T alterar(T entity) throws TipoChaveNaoEncontradaException, DAOException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            T tAtualizado = entityManager.merge(entity);
            entityManager.getTransaction().commit();
            return tAtualizado;
        }finally {
            entityManager.close();
        }
    }

    @Override
    public void excluir(T entity) throws DAOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entity = entityManager.merge(entity);
            entityManager.remove(entity);
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

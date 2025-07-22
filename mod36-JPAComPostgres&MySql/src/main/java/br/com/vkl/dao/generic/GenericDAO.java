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

    private static final String PERSISTENCE_UNIT_NAME = "Projeto4JPA";

    protected EntityManagerFactory entityManagerFactory;

    protected EntityManager entityManager;

    private final Class<T> classPersistente;

    private String persistenceUnitName;

    public GenericDAO(Class<T> classPersistente, String persistenceUnitName) {
        this.classPersistente = classPersistente;
        this.persistenceUnitName = persistenceUnitName;
    }

    private void setClassName() {
    }

    @Override
    public T cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException {

        openConnection();

        try {
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return entity;
        } finally {
            close();
        }

    }



    @Override
    public T consultar(E valor) throws MaisDeUmRegistroException, TableException, DAOException {
        openConnection();

        try {

            T entity = entityManager.find(this.classPersistente, valor);
            entityManager.getTransaction().commit();
            return entity;

        } catch (NoResultException e){
            return null;
        } finally {
            close();
        }

    }


    @Override
    public Collection buscarTodos() throws DAOException {

        openConnection();

        try {
            String jpql = "SELECT e FROM " + classPersistente.getSimpleName() + " e";
            TypedQuery<T> query = entityManager.createQuery(jpql, classPersistente);
            return query.getResultList();
        }finally {
            close();
        }

    }

    @Override
    public T alterar(T entity) throws TipoChaveNaoEncontradaException, DAOException {

        try {
            openConnection();
            T tAtualizado = entityManager.merge(entity);
            entityManager.getTransaction().commit();
            return tAtualizado;
        }finally {
            close();
        }
    }

    @Override
    public void excluir(T entity) throws DAOException {

        try {
            openConnection();
            entity = entityManager.merge(entity);
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        } finally {
            close();
        }
    }

    protected void openConnection() {
        entityManagerFactory =
                Persistence.createEntityManagerFactory(getPersistenceUnit());
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    private String getPersistenceUnit() {
        if (persistenceUnitName == null || "".equals(persistenceUnitName)){
            return PERSISTENCE_UNIT_NAME;
        }else{
            return persistenceUnitName;
        }
    }

    public void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

}

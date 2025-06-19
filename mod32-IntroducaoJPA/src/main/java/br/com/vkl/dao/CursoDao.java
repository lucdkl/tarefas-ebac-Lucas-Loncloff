package br.com.vkl.dao;

import br.com.vkl.domain.Curso;

import javax.persistence.*;
import java.util.List;

public class CursoDao implements ICursoDao{

    private EntityManagerFactory entityManagerFactory;

    public CursoDao() {
        this.entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
    }

    @Override
    public Curso cadastrar(Curso curso) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(curso);
            entityManager.getTransaction().commit();
            return curso;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Curso consultar(String codigo) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<Curso> query = entityManager.createQuery(
                    "SELECT c FROM Curso c WHERE c.codigo = :codigo", Curso.class);
            query.setParameter("codigo", codigo);

            return query.getSingleResult();

        } catch (NoResultException e){
            return null;
        } finally {
            entityManager.close();
        }

    }

    @Override
    public List<Curso> consultarTodos() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<Curso> query = entityManager.createQuery("SELECT c FROM Curso c", Curso.class);
            return query.getResultList();
        }finally {
            entityManager.close();
        }

    }

    @Override
    public Curso atualizar(Curso curso) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Curso cursoAtualizado = entityManager.merge(curso);
            entityManager.getTransaction().commit();
            return cursoAtualizado;
        }finally {
            entityManager.close();
        }

    }

    @Override
    public void excluir(Curso curso) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            curso = entityManager.merge(curso);
            entityManager.remove(curso);
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




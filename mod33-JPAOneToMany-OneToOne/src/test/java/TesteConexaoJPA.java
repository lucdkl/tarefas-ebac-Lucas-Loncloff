import javax.persistence.*;

public class TesteConexaoJPA {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;

        try {
            // Usando o mesmo nome do persistence-unit
            emf = Persistence.createEntityManagerFactory("JPAMODULO33");

            EntityManager em = emf.createEntityManager();

            // Teste de conexão
            System.out.println("✅ Conexão estabelecida com sucesso!");

            // Teste de consulta SQL nativa
            Query query = em.createNativeQuery("SELECT 1");
            Object result = query.getSingleResult();
            System.out.println("Resultado do teste: " + result);

            em.close();

        } catch (PersistenceException e) {
            System.err.println("❌ Falha na conexão: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }
}
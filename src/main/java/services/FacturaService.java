package services;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Cliente;
import model.Factura;

public class FacturaService {
	private EntityManagerFactory emf;

    public FacturaService() {
        emf = Persistence.createEntityManagerFactory("Prueba63EduardoArceM"); 
    }

    public List<Factura> obtenerDeudasPorDni(String dni) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            
            Cliente cliente = em.createQuery("SELECT c FROM Cliente c WHERE c.dni = :dni", Cliente.class)
                    .setParameter("dni", dni)
                    .getSingleResult();

            
            List<Factura> deudas = em.createQuery("SELECT f FROM Factura f WHERE f.cliente = :cliente AND f.total > 0", Factura.class)
                    .setParameter("cliente", cliente)
                    .getResultList();

            em.getTransaction().commit();
            return deudas;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}


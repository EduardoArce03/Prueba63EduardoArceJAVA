package dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import model.Cliente;
import model.Factura;
@Stateless
public class FacturaDAO {
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Factura factura) {
		em.persist(factura);
	}
	
	public void update(Factura factura) {
		em.merge(factura);
	}
	
	public void remove(int codigo) {
		Factura factura = em.find(Factura.class, codigo);
		em.refresh(factura);
	}
	
	public Factura read(int codigo) {
		Factura factura = em.find(Factura.class, codigo);
		return factura;
	}
	
	public List<Factura> getAll(){
		String jpql = "SELECT c FROM Factura c";
		Query q = em.createQuery(jpql, Factura.class);
		return q.getResultList();
	}
	
	public List<Factura> obtenerDeudasPorCliente(Cliente cliente) {
        String jpql = "SELECT f FROM Factura f WHERE f.cliente = :cliente AND f.total > 0";
        Query q = em.createQuery(jpql, Factura.class);
        q.setParameter("cliente", cliente);
        return q.getResultList();
    }
	
}

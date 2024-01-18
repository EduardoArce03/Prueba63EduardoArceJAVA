package business;

import java.util.Date;
import java.util.List;

import dao.ClienteDAO;
import dao.FacturaDAO;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import model.Cliente;
import model.DetalleFactura;
import model.Factura;
@Singleton
@Startup
public class GestionDatos {
	@Inject
	private ClienteDAO clienteDAO;
	@Inject
	private FacturaDAO facturaDAO;
	@PostConstruct
	public void init() {
		System.out.println("Iniciando.........");
		Cliente cliente = new Cliente();
		cliente.setCodigo(1);
		cliente.setDni("0150517241");
		cliente.setDireccion("Arenal");
		cliente.setNombre("Edu");
		
		clienteDAO.insert(cliente);
		
		cliente = new Cliente();
		cliente.setCodigo(2);
		cliente.setDni("0150517242");
		cliente.setDireccion("Chaulla");
		cliente.setNombre("Erick");
		
		clienteDAO.insert(cliente);
		
		Factura factura = new Factura();
		factura.setCliente(cliente);
		factura.setNumero("001-001-00000001");
		factura.setFechaEmision(new Date());
		factura.setTotal(1000.52);
		
		DetalleFactura det = new DetalleFactura();
		det.setNombre("TV");
		det.setCantidad(2);
		det.setPrecio(150.5);
		
		factura.addDetalle(det);
		
		det = new DetalleFactura();
		det.setNombre("Cocina");
		det.setCantidad(1);
		det.setPrecio(150.5);
		
		factura.addDetalle(det);
		
		facturaDAO.insert(factura);
		
		System.out.println("\n------------- Facturas2");
		List<Factura> list2 = facturaDAO.getAll();
		for(Factura fac : list2) {
			System.out.println(fac);
		}
		
		
	}
	
	
}

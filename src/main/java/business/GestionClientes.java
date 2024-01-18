package business;

import java.util.List;

import dao.ClienteDAO;
import dao.FacturaDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import model.Cliente;
import model.Factura;

@Stateless
public class GestionClientes {
	@Inject
	private ClienteDAO clienteDAO;
	private FacturaDAO facturaDAO;
	public void guardarClientes(Cliente cliente) {
		Cliente cli = clienteDAO.read(cliente.getCodigo());
		if(cli != null) {
			clienteDAO.update(cliente);
		}else {
			clienteDAO.insert(cliente);
		}
	}
	
	public void actualizarCliente(Cliente cliente) throws Exception {
		Cliente cli = clienteDAO.read(cliente.getCodigo());
		if(cli != null) {
			clienteDAO.update(cliente);
		}else {
			throw new Exception("El cliente no existe");
		}
	}
	
	public Cliente getClienteporCedula(String cedula) throws Exception {
		if(cedula.length()!=10) 
			throw new Exception("Cedula incorrecta");
		
		return clienteDAO.getClienteporCedula(cedula);
	}
	
	public void borrarCliente(int codigo) {
		clienteDAO.remove(codigo);
	}
	
	public List<Cliente> getClientes(){
		return clienteDAO.getAll();
	}
	
	public List<Factura> obtenerDeudasPorDni(String dni) {
        try {
            Cliente cliente = getClienteporCedula(dni);
            if (cliente != null) {
                return facturaDAO.obtenerDeudasPorCliente(cliente);
            }
            return null; 
        } catch (Exception e) {
            e.printStackTrace(); 
            return null;
        }
    }
}

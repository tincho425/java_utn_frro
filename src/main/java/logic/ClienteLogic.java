package logic;

import entities.Cliente;
import data.DataCliente;
import java.util.LinkedList;

public class ClienteLogic {
	private DataCliente dc;
	
	public ClienteLogic() {
		dc = new DataCliente();
	}
	
	public Cliente getByDni(Cliente c) {
		/* Para hacer m�s seguro el manejo de passwords este ser�a un lugar
		 * adecuado para generar un hash de la password utilizando un cifrado
		 * asim�trico como sha256 y utilizar el hash en lugar de las password en plano
		 */
		return dc.getByDni(c);
	}
	
	public LinkedList<Cliente> getAll(){
		return dc.getAll();
	}
	
	public Boolean insert(Cliente c) {
		return dc.insert(c);
	}
	
}

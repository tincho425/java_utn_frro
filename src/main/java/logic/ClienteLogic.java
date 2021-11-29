package logic;

import entities.Cliente;
import data.DataCliente;
import java.util.LinkedList;

public class ClienteLogic {
	private DataCliente dc;
	
	public ClienteLogic() {
		dc = new DataCliente();
	}
	
	public Cliente validate(Cliente c) {
		/* Para hacer m�s seguro el manejo de passwords este ser�a un lugar
		 * adecuado para generar un hash de la password utilizando un cifrado
		 * asim�trico como sha256 y utilizar el hash en lugar de las password en plano
		 */
		return dc.getByUser(c);
	}
	
	public LinkedList<Cliente> getAll(){
		return dc.getAll();
	}
	
	/*public Cliente getByDocumento(Cliente cli) {
		return dc.getByDocumento(cli);
	}*/
}

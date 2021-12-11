package logic;

import entities.Llamada;
import data.DataLlamada;
import java.util.LinkedList;

public class LlamadaLogic {
	private DataLlamada dl;
	
	public LlamadaLogic() {
		dl = new DataLlamada();
	}
	
	public LinkedList<Llamada> getAll(){
		return dl.getAll();
	}
	
	public Boolean insert(Llamada l, String[] servicios) {
		return dl.insert(l, servicios);
	}
	
}

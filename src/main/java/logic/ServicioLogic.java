package logic;

import entities.Servicio;
import data.DataServicio;

import java.util.LinkedList;

public class ServicioLogic {
	private DataServicio ds;
	
	public ServicioLogic() {
		ds = new DataServicio();
	}
	
	public LinkedList<Servicio> getAll(){
		return ds.getAll();
	}
	
}

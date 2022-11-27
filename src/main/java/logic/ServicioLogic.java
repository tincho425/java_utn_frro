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
	
	public Servicio getById(Servicio s){
		return ds.getById(s);
	}
	
	public Boolean insert(Servicio s){
		return ds.insert(s);
	}
	
	public Boolean update(Servicio s){
		return ds.update(s);
	}
	
	public Boolean delete(Servicio s) {
		return ds.delete(s);
	}
	
}

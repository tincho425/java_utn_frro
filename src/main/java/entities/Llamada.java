package entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;

import logic.ClienteLogic;
import logic.Login;
import servlet.Signin;

public class Llamada {
	
	private int id;
	private Timestamp timestamp_created;
	private Timestamp timestamp_ended;
	private String remitente, nota;
	private Integer id_cliente;
	private Integer id_usuario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Timestamp getTimestamp_created() {
		return timestamp_created;
	}

	public void setTimestamp_created(Timestamp timestamp_created) {
		this.timestamp_created = timestamp_created;
	}

	public Timestamp getTimestamp_ended() {
		return timestamp_ended;
	}

	public void setTimestamp_ended(Timestamp timestamp_ended) {
		this.timestamp_ended = timestamp_ended;
	}
	
	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	public Integer getId_usuario() {
		return id_usuario;
	}
	
	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getRemitente() {
		String remitenteString = ""; 
		if(this.remitente != null && this.remitente != "") {
			remitenteString = this.remitente;
		} else if ( this.id_cliente != null ){

			ClienteLogic ctrl = new ClienteLogic();
			Cliente cl = new Cliente(this.id_cliente);
			cl = ctrl.getByDni(cl);
			remitenteString = cl.getNombre() + " " + cl.getApellido();
			
		}
		return remitenteString;
	}

	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	
	public String getDestinatario() {
		if(this.remitente == null) {
			ClienteLogic ctrl = new ClienteLogic();
			Cliente cl = new Cliente(this.id_cliente);
			cl = ctrl.getByDni(cl);
			return cl.getNombre() + " " + cl.getApellido();
		}
		Login lctrl = new Login();
		System.out.println(this.id_usuario);
		Usuario u = new Usuario(this.id_usuario);
		u = lctrl.getById(u);
		System.out.println(u);
		return u.getNombre()+" "+u.getApellido();
	}

	@Override
	public String toString() {
		return "\nLlamada [id=" + id + ", id_usuario="+ id_usuario +", nota=" + nota +", timestamp_created=" + timestamp_created + ", timestamp_ended=" + timestamp_ended + ", remitente=" + remitente + "]";
	}

}
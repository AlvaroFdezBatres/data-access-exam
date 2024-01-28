package viajes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
@XmlType(name="reserva")
public class Reserva {
	private String nombrecliente;
	private int codreserva;
	private int plazasreservadas;
	
	
	@XmlTransient
	public int getCodreserva() {
		return codreserva;
	}
	public void setCodreserva(int codreserva) {
		this.codreserva = codreserva;
	}
	@XmlElement(name="nombrecliente")
	public String getNomrecliente() {
		return nombrecliente;
	}
	public void setNomrecliente(String nombrecliente) {
		this.nombrecliente = nombrecliente;
	}
	@XmlElement(name="plazasreservadas")
	public int getPlazasreservadas() {
		return plazasreservadas;
	}
	public void setPlazasreservadas(int plazasreservadas) {
		this.plazasreservadas = plazasreservadas;
	}
	public Reserva(String nombrecliente, int codreserva, int plazasreservadas) {
		super();
		this.nombrecliente = nombrecliente;
		this.codreserva = codreserva;
		this.plazasreservadas = plazasreservadas;
	}
	
	public Reserva() {}
}

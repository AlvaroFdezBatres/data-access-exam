package viajes;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
@XmlType(name="viaje")
public class Viaje {
	private int codviaje;
	private String nombre;
	private int pvp;
	private int numplazas;
	private int numreservas;
	private int totalimporte;
	private ArrayList<Reserva> listaReservas;
	
	@XmlElement(name="codviaje")
	public int getCodviaje() {
		return codviaje;
	}
	public void setCodviaje(int codviaje) {
		this.codviaje = codviaje;
	}
	@XmlElement(name="nombre")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@XmlElement(name="pvp")
	public int getPvp() {
		return pvp;
	}
	public void setPvp(int pvp) {
		this.pvp = pvp;
	}
	@XmlElement(name="numplazas")
	public int getNumplazas() {
		return numplazas;
	}
	public void setNumplazas(int numplazas) {
		this.numplazas = numplazas;
	}
	@XmlElement(name="numreservas")
	public int getNumreservas() {
		return numreservas;
	}
	public void setNumreservas(int numreservas) {
		this.numreservas = numreservas;
	}
	@XmlElement(name="totalimporte")
	public int getTotalimporte() {
		return totalimporte;
	}
	public void setTotalimporte(int totalimporte) {
		this.totalimporte = totalimporte;
	}
	@XmlElementWrapper(name="ListaReservas")
	@XmlElement(name="reserva")
	public ArrayList<Reserva> getListaReservas() {
		return listaReservas;
	}
	public void setListaReservas(ArrayList<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}
	public Viaje(int codviaje, String nombre, int pvp, int numplazas, int numreservas, int totalimporte,
			ArrayList<Reserva> listaReservas) {
		
		this.codviaje = codviaje;
		this.nombre = nombre;
		this.pvp = pvp;
		this.numplazas = numplazas;
		this.numreservas = numreservas;
		this.totalimporte = totalimporte;
		this.listaReservas = listaReservas;
	}
	
	public Viaje() {}
	
}

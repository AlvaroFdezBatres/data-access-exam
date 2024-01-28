package viajes;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="ListaViajes")
public class ListaViajes {
	
	private ArrayList<Viaje> viaje;
	
	@XmlElement(name="viaje")
	public ArrayList<Viaje> getViaje() {
		return viaje;
	}

	public void setViaje(ArrayList<Viaje> viaje) {
		this.viaje = viaje;
	}
	public ListaViajes(ArrayList<Viaje> viaje) {
		
		this.viaje = viaje;
	}
	
	public ListaViajes() {}
	
}

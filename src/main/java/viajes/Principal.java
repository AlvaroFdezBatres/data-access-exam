package viajes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;



public class Principal {
	final static long LONGVIAJES = 76;
	final static long LONGRESERVAS = 48;
	public static void main(String[] args) throws JAXBException {
		File f1 = new File("./Viajes.dat");
		File f2 = new File("./Reservas.dat");

		try {
			RandomAccessFile viajes = new RandomAccessFile(f1,"rw");
			RandomAccessFile reservas = new RandomAccessFile(f2,"rw");
			Ejercicio1(reservas,viajes);
			Ejercicio2(reservas, viajes);
			try {
				reservas.close();
				viajes.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void Ejercicio2(RandomAccessFile reservas, RandomAccessFile viajes) throws JAXBException {
		ArrayList<Reserva> listaReservas = new ArrayList<>();
		long posiR = 0;
		try {
			
			for(;;) {
				reservas.seek(posiR);
				String nombre = "";
				for(int i=0; i<20; i++) {
					nombre = nombre + reservas.readChar();
				}
				int codV = reservas.readInt();
				int plazasReser = reservas.readInt();
				
				Reserva r = new Reserva(nombre.trim(),codV,plazasReser);
				listaReservas.add(r);
				posiR += LONGRESERVAS;
				
				if(posiR >= reservas.length())
					break;
			}
	
			ArrayList<Viaje> viaje = new ArrayList<>();
			long posicion = 0;
			for(;;) {
				viajes.seek(posicion);
				int codigo = viajes.readInt();
				if(codigo!=0) {
					String destino = "";
					for(int i=0; i<30; i++) {
						destino = destino + viajes.readChar();
					}
					int pvp = viajes.readInt();
					int plazas = viajes.readInt();
					int numReservas = viajes.readInt();
					int importe = pvp * numReservas;

					Viaje v = new Viaje(codigo,destino.trim(), pvp, plazas, numReservas, importe, null);
					viaje.add(v);
				}
				posicion += LONGVIAJES;
				if(posicion >= viajes.length())
					break;
			}//fin for
			
			ArrayList<Viaje> viajesFinales = new ArrayList<>();
			for(Viaje v: viaje) {
			
					Viaje vi = new Viaje();
					vi.setCodviaje(v.getCodviaje());
					vi.setNombre(v.getNombre());
					vi.setPvp(v.getPvp());
					vi.setNumplazas(v.getNumplazas());
					vi.setNumreservas(v.getNumreservas());
					vi.setTotalimporte(v.getTotalimporte());
					ArrayList<Reserva> reservasFinales= new ArrayList<>();
					for(Reserva r: listaReservas) {
						if(v.getCodviaje()==r.getCodreserva()) {
							Reserva res = new Reserva();
							res.setNomrecliente(r.getNomrecliente());
							res.setPlazasreservadas(r.getPlazasreservadas());
							reservasFinales.add(res);
						}
					}//fin for reserva
					vi.setListaReservas(reservasFinales);
					viajesFinales.add(vi);
			}//fin for viaje
			
			ListaViajes l = new ListaViajes(viajesFinales);
			
			// Creamos el contexto indicando la clase raíz
			JAXBContext context = JAXBContext.newInstance(ListaViajes.class);
			// Creamos el Marshaller, convierte el java bean en una cadena XML
			Marshaller m = context.createMarshaller();
			// Formateamos el xml para que quede bien
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// Lo visualizamos con system out
			m.marshal(l, System.out);
			// Escribimos en el archivo
			m.marshal(l, new File("viajes.xml"));
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void Ejercicio1( RandomAccessFile reservas, RandomAccessFile viajes) {
		long posiR = 0;
//		ArrayList<Reserva> listaReservas = new ArrayList<>();
			try {
				//Actualizo
				for(;;) {
					reservas.seek(posiR);
					String nombre = "";
					for(int i=0; i<20; i++) {
						nombre = nombre + reservas.readChar();
					}
					int codV = reservas.readInt();
					int plazasReser = reservas.readInt();
					
//					Reserva r = new Reserva(nombre,plazasReser);
//					listaReservas.add(r);
					
					long posiV = (codV -1) * LONGVIAJES;
					viajes.seek(posiV);
					int codViaje2 = viajes.readInt();
					if(codV == codViaje2) {
						String destino = "";
						for(int i=0; i<30; i++) {
							destino = destino + viajes.readChar();
						}
						int pvp = viajes.readInt();
						int plazas = viajes.readInt();
						int numReservas = viajes.readInt();
						viajes.seek(viajes.getFilePointer()-4);
						viajes.writeInt(plazasReser+numReservas);

					}//fin if
					
					posiR += LONGRESERVAS;
					
					if(posiR >= reservas.length())
						break;
				}//fin for(;;)
				
				//Leo el fichero actualizado
				long posicion = 0;
				String destinoMasReservado = "";
				System.out.printf("%6s %40s %10s %10s %10s %10s %30s%n","CodViaje","Nombre","PVP","PLAZAS","RESERVAS","IMPORTE","SITUACIÓN");
				System.out.printf("%6s %40s %10s %10s %10s %10s %30s%n","----------","----------------------------------","----------","----------","----------","----------","-------------------------------");

				for(;;) {
					viajes.seek(posicion);
					int codigo = viajes.readInt();
					if(codigo!=0) {
						String destino = "";
						for(int i=0; i<30; i++) {
							destino = destino + viajes.readChar();
						}
						int pvp = viajes.readInt();
						int plazas = viajes.readInt();
						int numReservas = viajes.readInt();
						
						int maxReservas = 0;
						
						
						if(numReservas > maxReservas) {
							maxReservas = numReservas;
							destinoMasReservado = destino;
						}
						String situacion ="";
						if(plazas < numReservas) {
							situacion = "Reservas excedidas en "+(plazas - numReservas);
						}else {
							situacion = "Correcto";
						}
						int importe = pvp * numReservas;
						System.out.printf("%6s %40s %10s %10s %10s %10s %30s%n",codigo,destino,pvp,plazas,numReservas,importe,situacion);
					}
					posicion += LONGVIAJES;
					if(posicion >= viajes.length())
						break;
				}//fin for
				System.out.printf("%6s %40s %10s %10s %10s %10s %30s%n","----------","----------------------------------","----------","----------","----------","----------","-------------------------------");
				System.out.println("Viaje con más reservas: "+destinoMasReservado);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}

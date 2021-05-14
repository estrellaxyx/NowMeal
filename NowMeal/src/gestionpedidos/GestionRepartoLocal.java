package gestionpedidos;

import gestionpedidos.pedido.Pedido;
import gestionpedidos.transportes.Furgoneta;
import gestionpedidos.transportes.Moto;
import gestionpedidos.transportes.Transporte;
import list.ArrayList;
import queues.NaiveQueue;

import anotacion.Programacion2;

@Programacion2(nombreAutor1 = "Yuxiao", apellidoAutor1 = "Xiong", emailUPMAutor1 = "y.xiong@alumnos.upm.es", nombreAutor2 = "Irene", apellidoAutor2 = "Tard�n Piquer", emailUPMAutor2 = "irene.tpiquer@alumnos.upm.es")

public class GestionRepartoLocal {
	// C�DIGO DE APOYO
	private ArrayList<Moto> motosDisponibles;
	private ArrayList<Furgoneta> furgonetasDisponibles;

	private NaiveQueue<Pedido> pedidosEsperandoMoto;
	private NaiveQueue<Pedido> pedidosEsperandoFurgoneta;

	// C�DIGO DE APOYO
	private static ArrayList<String> getCodList(ArrayList<?> disponibles) {
		ArrayList<String> salida = new ArrayList<>();
		for (int i = 0; i < disponibles.size(); i++)
			salida.add(salida.size(), ((Transporte) disponibles.get(i)).getCodigo());
		return salida;
	}

	// C�DIGO DE APOYO
	private static ArrayList<String[]> getClienteRestauranteList(NaiveQueue<Pedido> pendientes) {
		ArrayList<String[]> salida = new ArrayList<>();
		NaiveQueue<Pedido> aux = new NaiveQueue<>();
		while (!pendientes.isEmpty()) {
			Pedido pedido = pendientes.poll();

			salida.add(salida.size(),
					new String[] { pedido.getCliente().getCodigo(), pedido.getRestaurante().getCodigo() });
			aux.add(pedido);
		}
		while (!aux.isEmpty())
			pendientes.add(aux.poll());

		return salida;
	}

	// C�DIGO DE APOYO
	private static String myArrayListToString(ArrayList<?> list) {
		String salida = "";
		for (int i = 0; i < list.size(); i++) {
			salida += " ";
			if (list.get(i) instanceof String[]) {
				String[] item = (String[]) list.get(i);
				for (int j = 0; j < item.length; j++) {
					salida += item[j];
				}
			} else if (list.get(i) instanceof String) {
				salida += (String) list.get(i);
			}
		}

		return salida;
	}

	// C�DIGO DE APOYO
	public String getDisponibles() {
		return "Motos Disponibles:" + myArrayListToString(getCodList(motosDisponibles)) + System.lineSeparator()
				+ "Furgonetas Disponibles:" + myArrayListToString(getCodList(furgonetasDisponibles))
				+ System.lineSeparator();

	}

	// C�DIGO DE APOYO
	public String getEsperando() {
		return "Pedidos esperando moto:" + myArrayListToString(getClienteRestauranteList(pedidosEsperandoMoto))
				+ System.lineSeparator() + "Pedidos esperando furgoneta:"
				+ myArrayListToString(getClienteRestauranteList(pedidosEsperandoFurgoneta)) + System.lineSeparator();
	}

	// C�DIGO DE APOYO
	public ArrayList<String> getCodMotosDisponibles() {
		return getCodList(motosDisponibles);
	}

	// C�DIGO DE APOYO
	public ArrayList<String> getCodFurgoDisponibles() {
		return getCodList(furgonetasDisponibles);

	}

	// C�DIGO DE APOYO
	public ArrayList<String[]> getCodEsperandoMoto() {
		return getClienteRestauranteList(pedidosEsperandoMoto);
	}

	public ArrayList<String[]> getCodEsperandoFurgo() {
		return getClienteRestauranteList(pedidosEsperandoFurgoneta);
	}

	private static final double PESOMAXMOTO = 20;

	// C�DIGO DE APOYO
	public GestionRepartoLocal() {

		this.motosDisponibles = new ArrayList<>();
		this.furgonetasDisponibles = new ArrayList<>();

		this.pedidosEsperandoFurgoneta = new NaiveQueue<>();
		this.pedidosEsperandoMoto = new NaiveQueue<>();
	}

	/**
	 * <B>PRE:</B> el transporte no ha sido asignado a ninguna zona.<BR/>
	 * <B>POST:</B> a�ade el transporte dado a la lista de los disponibles
	 * dependiendo del tipo de veh�culo que es.
	 * 
	 * @param transporte
	 */
	public void add(Transporte transporte) {
		if (transporte instanceof Moto) {
			motosDisponibles.add(motosDisponibles.size(), (Moto) transporte);
		} else {

			furgonetasDisponibles.add(furgonetasDisponibles.size(), (Furgoneta) transporte);
		}
	}

	/**
	 * M�todo auxiliar de asignarPedido que calcula el menor coste entre todos los
	 * transportes de un ArrayList (de una clase que sea hija de la clase
	 * Transporte)
	 * 
	 * @param disponible
	 * @param pedido
	 * @return
	 */
	private void menorCoste(ArrayList<? extends Transporte> disponible, NaiveQueue<Pedido> esperando, Pedido pedido) {
		if (disponible.size() != 0) {
			int pos = 0;
			double coste = pedido.coste(disponible.get(pos));
			for (int i = 1; i < disponible.size(); i++) {
				if (coste > pedido.coste(disponible.get(i))) {
					coste = pedido.coste(disponible.get(i));
					pos = i;
				}
			}
			Transporte transpDisp = disponible.get(pos);
			pedido.setTransporte(transpDisp);
			disponible.removeElementAt(pos);
		} else {
			esperando.add(pedido);
		}
	}

	/**
	 * <B>PRE:</B> el pedido no tiene asignado ning�n transporte.<BR/>
	 * <B>POST:</B> asigna el pedido a una moto o una furgoneta dependiendo del peso
	 * del pedido de forma que minimice el coste.
	 * 
	 * @param pedido
	 */
	public void asignarPedido(Pedido pedido) {
		if (pedido.getPeso() <= PESOMAXMOTO) {
			menorCoste(motosDisponibles, pedidosEsperandoMoto, pedido);
		} else {
			menorCoste(furgonetasDisponibles, pedidosEsperandoFurgoneta, pedido);
		}
	}

	/**
	 * <B>PRE:</B> el pedido tiene asignado un transporte.<BR/>
	 * <B>POST:</B> notifica la entrga del pedido. Vuelve a poner el transporte
	 * disponible si no hay ning�n pedido en espera de ese tipo de transporte.
	 * 
	 * @param pedido
	 */
	public void notificarEntregaPedido(Pedido pedido) {
		if (pedido.getTransporte() != null) {
			Transporte transp = pedido.getTransporte();
			if (transp instanceof Moto) {
				if (!pedidosEsperandoMoto.isEmpty()) {
					pedidosEsperandoMoto.poll().setTransporte(transp);
				} else {
					motosDisponibles.add(motosDisponibles.size(), (Moto) transp);
				}
			} else {
				if (!pedidosEsperandoFurgoneta.isEmpty()) {
					pedidosEsperandoFurgoneta.poll().setTransporte(transp);
				} else {
					furgonetasDisponibles.add(furgonetasDisponibles.size(), (Furgoneta) transp);
				}
			}
		}
	}
}
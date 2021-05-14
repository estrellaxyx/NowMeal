package gestionpedidos;

import gestionpedidos.mapa.Mapa;

import gestionpedidos.mapa.PosicionXY;
import gestionpedidos.pedido.Pedido;
import gestionpedidos.transportes.Transporte;

import anotacion.Programacion2;

@Programacion2(nombreAutor1 = "Yuxiao", apellidoAutor1 = "Xiong", emailUPMAutor1 = "y.xiong@alumnos.upm.es", nombreAutor2 = "Irene", apellidoAutor2 = "Tard�n Piquer", emailUPMAutor2 = "irene.tpiquer@alumnos.upm.es")

public class GestionReparto {
	// C�DIGO DE APOYO
	private GestionRepartoLocal[] gestoresLocales;
	private Mapa mapa;

	/**
	 * Constructor de la clase reparto.
	 * 
	 * @param mapa
	 */
	public GestionReparto(Mapa mapa) {
		this.mapa = mapa;
		gestoresLocales = new GestionRepartoLocal[4];
		for (int i = 0; i < gestoresLocales.length; i++) {
			gestoresLocales[i] = new GestionRepartoLocal();
		}
	}

	// C�DIGO DE APOYO
	public Mapa getMapa() {
		return mapa;
	}

	// C�DIGO DE APOYO
	public String getEstadoGestorLocal(int i) {
		return this.gestoresLocales[i].getDisponibles() + this.gestoresLocales[i].getEsperando();
	}

	// C�DIGO DE APOYO
	public String getEstadoGestorLocalNum(int i) {
		return this.gestoresLocales[i].getCodMotosDisponibles().size() + ";"
				+ this.gestoresLocales[i].getCodFurgoDisponibles().size() + ";"
				+ this.gestoresLocales[i].getCodEsperandoMoto().size() + ";"
				+ this.gestoresLocales[i].getCodEsperandoFurgo().size();
	}

	/**
	 * M�todo auxiliar para calcular en qu� localidad est� el objeto recibido
	 * 
	 * @param pos
	 * @return
	 */
	private int seleccionarLocalidad(PosicionXY pos) {
		int x = pos.getX();
		int y = pos.getY();
		int ans;
		int middleX = mapa.getMaxCoordX() / 2;
		int middleY = mapa.getMaxCoordY() / 2;
		if (x <= middleX && y <= middleY) {
			ans = 0;
		} else if (x >= middleX + 1 && y >= middleY + 1) {
			ans = 3;
		} else if (x <= middleX) {
			ans = 1;
		} else {
			ans = 2;
		}
		return ans;
	}

	/**
	 * <B>PRE:</B> el transporte no ha sido asignado a ninguna zona.<BR/>
	 * <B>POST:</B> a�ade el transporte dado a la lista de los disponibles del
	 * gestor de su zona dependiendo de la localidad en la que se encuentre.
	 * 
	 * @param transporte
	 */
	public void addTransporteLocalidad(Transporte transporte) {
		String codigo = transporte.getCodigo();
		int localidad = seleccionarLocalidad(mapa.getPosicion(codigo));
		for (int i = 0; i < gestoresLocales.length; i++) {
			gestoresLocales[localidad].add(transporte);
		}
	}

	/**
	 * <B>PRE:</B> el pedido no tiene asignado ning�n transporte.<BR/>
	 * <B>POST:</B> asigna el pedido a un gestor u otro dependiendo de la localidad
	 * en la que se encuentre el cliente.
	 * 
	 * @param pedido
	 */
	public void asignarPedido(Pedido pedido) {
		int localidad = seleccionarLocalidad(mapa.getPosicion(pedido.getCliente().getCodigo()));
		gestoresLocales[localidad].asignarPedido(pedido);

	}

	/**
	 * <B>PRE:</B> el pedido tiene asignado un transporte.<BR/>
	 * <B>POST:</B> notifica la entrega del pedido al gestor correspondiente (el de
	 * la localidad del cliente).
	 * 
	 * @param pedido
	 */
	public void notificarEntregaPedido(Pedido pedido) {
		//if (pedido.getTransporte() != null) {
			int localidad = seleccionarLocalidad(mapa.getPosicion(pedido.getCliente().getCodigo()));
			gestoresLocales[localidad].notificarEntregaPedido(pedido);
		
	}

}

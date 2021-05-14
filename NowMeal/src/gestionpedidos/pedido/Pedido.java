package gestionpedidos.pedido;

import anotacion.Programacion2;
import gestionpedidos.transportes.Transporte;

@Programacion2(nombreAutor1 = "Irene", apellidoAutor1 = "Tardón Piquer", emailUPMAutor1 = "@alumnos.upm.es", nombreAutor2 = "Yuxiao", apellidoAutor2 = "Xiong", emailUPMAutor2 = "y.xiong@alumnos.upm.es")

public class Pedido {
	// CÓDIGO DE APOYO
	private Cliente cliente;
	private PlatoComida[] comidas;
	private Restaurante restaurante;
	private double importe;
	private Transporte transporte;
	private double peso;

	public Pedido(Cliente cliente, PlatoComida[] comidas, Restaurante restaurante) {
		this.cliente = cliente;
		this.comidas = comidas;
		this.restaurante = restaurante;
		for (int i = 0; i < comidas.length; i++) {
			this.importe += comidas[i].getPrecio();
		}
		for (int i = 0; i < comidas.length; i++) {
			this.peso += comidas[i].getPeso();
		}
	}

	/**
	 * Getter para peso
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * Devuelve el coste del pedido
	 */
	public double coste(Transporte transporte) {
		return importe + transporte.coste(restaurante.getCodigo())
				+ transporte.coste(restaurante.getCodigo(), cliente.getCodigo());
	}

	// CÓDIGO DE APOYO
	public double getImporte() {
		return importe;
	}

	// CÓDIGO DE APOYO
	public Transporte getTransporte() {
		return transporte;
	}

	// CÓDIGO DE APOYO
	public void setTransporte(Transporte transporte) {
		this.transporte = transporte;
	}

	// CÓDIGO DE APOYO
	public Cliente getCliente() {
		return cliente;
	}

	// CÓDIGO DE APOYO
	public Restaurante getRestaurante() {
		return restaurante;
	}
}
package gestionpedidos.transportes;

import anotacion.Programacion2;
import gestionpedidos.mapa.Mapa;

@Programacion2(nombreAutor1 = "Irene", apellidoAutor1 = "Tardón Piquer", emailUPMAutor1 = "@alumnos.upm.es", nombreAutor2 = "Yuxiao", apellidoAutor2 = "Xiong", emailUPMAutor2 = "y.xiong@alumnos.upm.es")

public abstract class Transporte {
	private String codigo;
	private Mapa mapa;

	public Transporte(String codigo, Mapa mapa) {
		this.codigo = new String(codigo);
		this.mapa = mapa;
	}

	public String getCodigo() {
		return codigo;
	}

	/**
	 * Método recursivo sobre el método abstracto coste(String codPosOrigen, String
	 * codPosDestino) que tendrán implementado las clases hijas
	 */
	public double coste(String codPosDestino) {
		return coste(codigo, codPosDestino);
	}

	/**
	 * método abstracto, se calcula el coste del origen al destino lo implementamos
	 * en las clases de hija.
	 */
	public abstract double coste(String codPosOrigen, String codPosDestino);

	protected Mapa getMapa() {
		return mapa;
	}
}

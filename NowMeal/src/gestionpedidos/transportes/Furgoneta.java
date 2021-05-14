package gestionpedidos.transportes;

import anotacion.Programacion2;
import gestionpedidos.mapa.Mapa;

@Programacion2(nombreAutor1 = "Irene", apellidoAutor1 = "Tardón Piquer", emailUPMAutor1 = "@alumnos.upm.es", nombreAutor2 = "Yuxiao", apellidoAutor2 = "Xiong", emailUPMAutor2 = "y.xiong@alumnos.upm.es")

public abstract class Furgoneta extends Transporte {

	private double tara;

	public Furgoneta(String codigo, Mapa mapa, double tara) {
		super(codigo, mapa);
		this.tara = tara;
	}

	/**
	 * Getters y setters para tara
	 */
	public double getTara() {
		return tara;
	}

	public void setTara(double tara) {
		this.tara = tara;
	}
}

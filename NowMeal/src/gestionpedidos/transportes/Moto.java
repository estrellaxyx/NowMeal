package gestionpedidos.transportes;

import anotacion.Programacion2;
import gestionpedidos.mapa.Mapa;

@Programacion2(nombreAutor1 = "Irene", apellidoAutor1 = "Tardón Piquer", emailUPMAutor1 = "@alumnos.upm.es", nombreAutor2 = "Yuxiao", apellidoAutor2 = "Xiong", emailUPMAutor2 = "y.xiong@alumnos.upm.es")

public class Moto extends Transporte {

	private double eurosPKm = 2;

	public Moto(String codigo, Mapa mapa) {
		super(codigo, mapa);
	}

	/**
	 * Setters y getters para eurosPKm
	 */
	public double getEurosPKm() {
		return eurosPKm;
	}

	public void setEurosPKm(double eurosPKm) {
		this.eurosPKm = eurosPKm;
	}

	/**
	 * Sobreescribe coste(String codOrigen, String codDestino) con su fórmula propia
	 * de calcularlo
	 */
	@Override
	public double coste(String codOrigen, String codDestino) {
		return super.getMapa().distancia(codOrigen, codDestino) * eurosPKm;
	}
}

package gestionpedidos.transportes;

import anotacion.Programacion2;
import gestionpedidos.mapa.Mapa;

@Programacion2(nombreAutor1 = "Irene", apellidoAutor1 = "Tardón Piquer", emailUPMAutor1 = "@alumnos.upm.es", nombreAutor2 = "Yuxiao", apellidoAutor2 = "Xiong", emailUPMAutor2 = "y.xiong@alumnos.upm.es")

public class FurgonetaSubcontratada extends Furgoneta {

	private double eurosPKm = 1;

	public FurgonetaSubcontratada(String codigo, Mapa mapa, double tara) {
		super(codigo, mapa, tara);
	}

	/**
	 * Getters y setters para eurosPKm
	 */
	public double getEurosPKm() {
		return eurosPKm;
	}

	public void setEurosPKm(double eurosPKm) {
		this.eurosPKm = eurosPKm;
	}

	/**
	 * Sobreescribe coste(String codOrigen, String codDestino) y lo calcula de
	 * formas distintas dependiendo de su tara
	 */
	@Override
	public double coste(String codPosOrigen, String codPosDestino) {
		double ans = super.getMapa().distancia(codPosOrigen, codPosDestino) * eurosPKm;
		if (super.getTara() > 1000) {
			ans = ans * 1.1;
		}
		return ans;
	}

}

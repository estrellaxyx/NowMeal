package gestionpedidos.transportes;

import anotacion.Programacion2;
import gestionpedidos.mapa.Mapa;

@Programacion2(nombreAutor1 = "Irene", apellidoAutor1 = "Tardón Piquer", emailUPMAutor1 = "@alumnos.upm.es", nombreAutor2 = "Yuxiao", apellidoAutor2 = "Xiong", emailUPMAutor2 = "y.xiong@alumnos.upm.es")

public class FurgonetaPropia extends Furgoneta {
	private double velocidadMedia = 30;
	private final static double EUROS_P_HORA = 40;

	public FurgonetaPropia(String codigo, Mapa mapa, double tara) {
		super(codigo, mapa, tara);
	}

	/**
	 * Getters y setters para velocidadMedia
	 */
	public double getVelocidadMedia() {
		return velocidadMedia;
	}

	public void setVelocidadMedia(double velocidadMedia) {
		this.velocidadMedia = velocidadMedia;
	}

	/**
	 * Sobreescribe coste(String codOrigen, String codDestino) y lo calcula de
	 * formas distintas dependiendo de su tara
	 */
	@Override
	public double coste(String codPosOrigen, String codPosDestino) {
		double ans = getMapa().distancia(codPosOrigen, codPosDestino) * (EUROS_P_HORA / velocidadMedia);
		if (super.getTara() >= 500) {
			ans = ans * 1.1;
		}
		return ans;
	}

}

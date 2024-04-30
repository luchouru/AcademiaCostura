package sistema.logica.VO;

import java.io.Serializable;

public class VOMontoInscripcion implements Serializable {

	private int ci;
	private int anio;

	public VOMontoInscripcion(int ci, int anio) {
		this.ci = ci;
		this.anio = anio;
	}

	public int getCi() {
		return ci;
	}

	public int getAnio() {
		return anio;
	}

}

package sistema.logica.VO;

import java.io.Serializable;

import sistema.logica.constantes.Modalidad;

public class VOEscolaridad implements Serializable {
	private int cedula;
	private Modalidad tipoListado;

	public VOEscolaridad(int cedula, Modalidad tipoListado) {
		this.cedula = cedula;
		this.tipoListado = tipoListado;
	}

	public int getCedula() {
		return cedula;
	}

	public Modalidad getTipoListado() {
		return tipoListado;
	}

}

package sistema.logica.VO;

import java.io.Serializable;

public class VOInscripcionCompleta implements Serializable {
	private int anio;
	private int cedula;
	private String codigoAsignatura;
	private float monto;

	public VOInscripcionCompleta(int anio, int cedula, String codigoAsignatura, float monto) {
		this.anio = anio;
		this.cedula = cedula;
		this.codigoAsignatura = codigoAsignatura;
		this.monto = monto;
	}

	public int getAnio() {
		return anio;
	}

	public int getCedula() {
		return cedula;
	}

	public String getCodigoAsignatura() {
		return codigoAsignatura;
	}

	public float getMonto() {
		return monto;
	}

}

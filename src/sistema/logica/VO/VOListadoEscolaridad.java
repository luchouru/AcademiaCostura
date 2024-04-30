package sistema.logica.VO;

import java.io.Serializable;

public class VOListadoEscolaridad implements Serializable {
	private int numero;
	private String asignatura;
	private int anio;
	private int calificacion;

	public VOListadoEscolaridad(int numero, String asignatura, int anio, int calificacion) {
		this.numero = numero;
		this.asignatura = asignatura;
		this.anio = anio;
		this.calificacion = calificacion;
	}

	public int getNumero() {
		return numero;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public int getAnio() {
		return anio;
	}

	public int getCalificacion() {
		return calificacion;
	}

}

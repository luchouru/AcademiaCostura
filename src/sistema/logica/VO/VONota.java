package sistema.logica.VO;

import java.io.Serializable;

public class VONota implements Serializable {
	
	private int cedula;
	private int numeroInscripcion;
	private int calificacionFinal;

	public VONota(int cedula, int numeroInscripcion, int calificacionFinal) {
		this.cedula = cedula;
		this.numeroInscripcion = numeroInscripcion;
		this.calificacionFinal = calificacionFinal;
	}

	public int getCedula() {
		return cedula;
	}

	public int getNumeroInscripcion() {
		return numeroInscripcion;
	}

	public int getCalificacionFinal() {
		return calificacionFinal;
	}

}

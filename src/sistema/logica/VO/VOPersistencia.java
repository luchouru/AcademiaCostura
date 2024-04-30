package sistema.logica.VO;

import java.io.Serializable;

import sistema.logica.alumnos.Alumnos;
import sistema.logica.asignaturas.Asignaturas;

public class VOPersistencia implements Serializable {
	private Alumnos alumnos;
	private Asignaturas asignaturas;

	public VOPersistencia(Alumnos alumnos, Asignaturas asignaturas) {
		this.alumnos = alumnos;
		this.asignaturas = asignaturas;
	}

	public Alumnos getAlumnos() {
		return alumnos;
	}

	public Asignaturas getAsignaturas() {
		return asignaturas;
	}

}

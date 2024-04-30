package sistema.logica.constantes;

public enum CodExcepcionesAlumno {

	YA_EXISTE("Alumno ya existe."), NO_EXISTE("El alumno no se encuentra registrado."),
	SIN_ALUMNOS("No hay alumnos ingresados al sistema."),
	SIN_EGRESADOS("No hay alumnos egresados."),
	SIN_INSCRIPCIONES("El alumno no tiene inscripciones."),
	NO_EXISTE_INSCRIPCION("El alumno no tiene una inscripcion con el numero brindado."),
	RESULTADO_ASIGNADO("La inscripcion del alumno ya cuenta con una calificacion final.");

	private String msg;

	CodExcepcionesAlumno(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
}

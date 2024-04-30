package sistema.logica.constantes;

public enum CodExcepcionesAsignatura {
	
	YA_EXISTE("La asignatura ya existe"), 
	NO_EXISTE("La asignatura no se encuentra registrada"),
	LLENO("Ya hay 10 asignaturas ingresadas"),
	SIN_ASIGNATURAS("No hay asignaturas ingresadas en el sistema"), 
	ANIO_INVALIDO("El anio lectivo debe ser igual o mayor a la ultima inscripcion"),
	ESTA_APROBADA("La asignatura se encuentra aprobada"),
	NO_ESTA_CERRADA("La última inscripción a la asignatura no tiene el curso cerrado"),
	CURSANDO_ANIO_LECTIVO("Ya existe una inscripción en el anio lectivo");
	
	private String msg;
	
	CodExcepcionesAsignatura(String msg){
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}

}

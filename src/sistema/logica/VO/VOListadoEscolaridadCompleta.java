package sistema.logica.VO;

public class VOListadoEscolaridadCompleta extends VOListadoEscolaridad {

	private float montoBase;

	public VOListadoEscolaridadCompleta(int numero, String asignatura, int anio, int calificacion, float montoBase) {
		super(numero, asignatura, anio, calificacion);
		this.montoBase = montoBase;
	}

	public float getMontoBase() {
		return montoBase;
	}

}

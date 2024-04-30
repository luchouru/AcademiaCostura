package sistema.logica.VO;

public class VOListadoEgresadoCompleta extends VOListadoEgresado {
	private float promedioTotal;
	private float promedioAprobaciones;

	public VOListadoEgresadoCompleta(int cedula, String nombre, String apellido, float promedioTotal,
			float promedioAprobaciones) {
		super(cedula, nombre, apellido);
		this.promedioTotal = promedioTotal;
		this.promedioAprobaciones = promedioAprobaciones;
	}

	public float getPromedioTotal() {
		return promedioTotal;
	}

	public float getPromedioAprobaciones() {
		return promedioAprobaciones;
	}

}

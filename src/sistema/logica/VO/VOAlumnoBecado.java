package sistema.logica.VO;

public class VOAlumnoBecado extends VOAlumno {

	private int descuento;
	private String razon;

	public VOAlumnoBecado(int cedula, String nombre, int telefono, String domicilio, String apellido, int descuento,
			String razon) {
		super(cedula, nombre, telefono, domicilio, apellido);
		this.descuento = descuento;
		this.razon = razon;
	}

	public VOAlumnoBecado(int cedula, String nombre, int telefono, String domicilio, String apellido, int descuento,
			String razon, int cantMateriasAprobadas) {
		super(cedula, nombre, telefono, domicilio, apellido, cantMateriasAprobadas);
		this.descuento = descuento;
		this.razon = razon;
	}

	public int getDescuento() {
		return descuento;
	}

	public String getRazon() {
		return razon;
	}

}

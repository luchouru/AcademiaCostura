package sistema.logica.alumnos;

import sistema.logica.VO.VOAlumnoBecado;

public class Becado extends Alumno {

	private int descuento;
	private String razon;

	public Becado(int cedula, String nombre, String apellido, String domicilio, int telefono, int descuento,
			String razon) {
		super(cedula, nombre, apellido, domicilio, telefono);
		this.descuento = descuento;
		this.razon = razon;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}
	

	public VOAlumnoBecado toVO() {
		return new VOAlumnoBecado(this.getCedula(), this.getNombre(), this.getTelefono(), this.getDomicilio(),
				this.getApellido(), this.getDescuento(), this.getRazon(), this.getCantMateriasAprobadas());
	}

}

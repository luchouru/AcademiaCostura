package sistema.logica.VO;

import java.io.Serializable;

public class VOAlumno implements Serializable {

	private int cedula;
	private String nombre;
	private int telefono;
	private String domicilio;
	private String apellido;
	private int cantMateriasAprobadas;

	public VOAlumno(int cedula, String nombre, int telefono, String domicilio, String apellido) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.apellido = apellido;
	}

	public VOAlumno(int cedula, String nombre, int telefono, String domicilio, String apellido,
			int cantMateriasAprobadas) {
		this(cedula, nombre, telefono, domicilio, apellido);
		this.cantMateriasAprobadas = cantMateriasAprobadas;
	}

	public int getCedula() {
		return cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public int getTelefono() {
		return telefono;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public String getApellido() {
		return apellido;
	}

	public int getCantMateriasAprobadas() {
		return cantMateriasAprobadas;
	}

}

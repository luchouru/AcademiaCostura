package sistema.logica.VO;

import java.io.Serializable;

public class VOListadoEgresado implements Serializable {
	private int cedula;
	private String nombre;
	private String apellido;

	public VOListadoEgresado(int cedula, String nombre, String apellido) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public int getCedula() {
		return cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

}

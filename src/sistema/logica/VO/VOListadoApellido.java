package sistema.logica.VO;

import java.io.Serializable;

public class VOListadoApellido extends VOListadoEgresado implements Serializable {
	private String tipo;

	public VOListadoApellido(int cedula, String nombre, String apellido, String tipo) {
		super(cedula, nombre, apellido);
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

}

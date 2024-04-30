package sistema.logica.excepciones;

import java.io.Serializable;

public class SistemaException extends Exception implements Serializable {

	private String mensaje;

	public SistemaException(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMessage() {
		return mensaje;
	}
}

package sistema.conexion;

import java.rmi.Naming;
import java.rmi.Remote;

import sistema.logica.excepciones.SistemaException;

public class Connector {

	private static String ruta = "";

	public static Remote createConnection() throws SistemaException {

		try {
			if (ruta.isEmpty()) {
				throw new SistemaException("Error no hay respuesta del servidor.");
			}
			return Naming.lookup(ruta);
		} catch (Exception e) {
			throw new SistemaException("Hubo un error en la comunicacion con el servidor.");
		}

	}

	public static void setConnectorRuta(String route) {
		if (ruta.isEmpty()) {
			ruta = route;
		}
	}

}

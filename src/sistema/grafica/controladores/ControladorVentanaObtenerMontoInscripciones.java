package sistema.grafica.controladores;

import java.rmi.RemoteException;
import sistema.logica.VO.VOMontoInscripcion;
import sistema.logica.excepciones.SistemaException;

public class ControladorVentanaObtenerMontoInscripciones extends ControladorSistema {

	public float obtenerMontoInscripciones(int cedula, int anio)  
			throws RemoteException, SistemaException{
			VOMontoInscripcion nuevoVOMontoInscripcion = new VOMontoInscripcion(cedula, anio);
			return this.getConexion().obtenerMontoInscripciones(nuevoVOMontoInscripcion);
	}
}
package sistema.grafica.controladores;

import java.rmi.RemoteException;
import java.util.ArrayList;

import sistema.logica.VO.VOAsignatura;
import sistema.logica.VO.VOInscripcionCompleta;
import sistema.logica.excepciones.SistemaException;

public class ControladorVentanaInscripcion extends ControladorSistema {

	public void ingresarInscripcion(int anio, int cedula, String codigoAsignatura, float monto) 
			throws RemoteException, SistemaException{
		VOInscripcionCompleta nuevaVOInscripcion = new VOInscripcionCompleta(anio, cedula, codigoAsignatura, monto);
		this.getConexion().inscripcionAsignatura(nuevaVOInscripcion);

	}
	
	public ArrayList<VOAsignatura> obtenerAsignaturas() throws RemoteException, SistemaException {
		return this.getConexion().listadoDeAsignaturas();
	}

}

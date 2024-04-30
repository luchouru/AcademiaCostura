package sistema.grafica.controladores;

import java.rmi.RemoteException;
import java.util.ArrayList;

import sistema.logica.VO.VOAsignatura;
import sistema.logica.excepciones.SistemaException;

public class ControladorVentanaListadoAsignaturas extends ControladorSistema {

	public ArrayList<VOAsignatura> listarAsignaturas() throws RemoteException, SistemaException {
		return this.getConexion().listadoDeAsignaturas();
	}
}

package sistema.grafica.controladores;

import java.rmi.RemoteException;
import java.util.ArrayList;

import sistema.logica.VO.VOListadoApellido;
import sistema.logica.excepciones.SistemaException;

public class ControladorVentanaListadoPorApellido extends ControladorSistema {

	public ArrayList<VOListadoApellido> listarPorApellido(String apellido) throws RemoteException, SistemaException {
		return this.getConexion().listarAlumnosApellido(apellido);
	}
}

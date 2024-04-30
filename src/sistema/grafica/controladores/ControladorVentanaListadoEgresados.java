package sistema.grafica.controladores;

import java.rmi.RemoteException;
import java.util.ArrayList;

import sistema.logica.VO.VOListadoEgresado;
import sistema.logica.constantes.Modalidad;
import sistema.logica.excepciones.SistemaException;

public class ControladorVentanaListadoEgresados extends ControladorSistema {

	public ArrayList<VOListadoEgresado> listarEgresados(Modalidad tipoListado)
			throws RemoteException, SistemaException {
		return this.getConexion().listarEgresados(tipoListado);
	}

}

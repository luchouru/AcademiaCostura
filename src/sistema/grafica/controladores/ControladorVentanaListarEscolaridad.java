package sistema.grafica.controladores;

import java.rmi.RemoteException;
import java.util.ArrayList;

import sistema.logica.VO.VOEscolaridad;
import sistema.logica.VO.VOListadoEscolaridad;
import sistema.logica.constantes.Modalidad;
import sistema.logica.excepciones.SistemaException;

public class ControladorVentanaListarEscolaridad extends ControladorSistema {
	public ArrayList<VOListadoEscolaridad> listarEscolaridad(int cedula, Modalidad tipoListado)
			throws RemoteException, SistemaException {
		VOEscolaridad nuevoVOEscolaridad = new VOEscolaridad(cedula, tipoListado);
		return this.getConexion().listarEscolaridad(nuevoVOEscolaridad);
	}

}
package sistema.grafica.controladores;

import java.rmi.RemoteException;

import sistema.logica.VO.VOAlumno;
import sistema.logica.excepciones.SistemaException;

public class ControladorVentanaListarDetalladoCedula extends ControladorSistema {

	public VOAlumno listarDetalladoCedula(int cedula) 
			throws RemoteException, SistemaException{
			return this.getConexion().listarDetalladoCedula(cedula);
	}
}
package sistema.grafica.controladores;

import java.rmi.RemoteException;

import sistema.logica.VO.VOAsignatura;
import sistema.logica.excepciones.SistemaException;

public class ControladorVentanaAltaAsignatura extends ControladorSistema {

	public void ingresarAsignatura(String nombre, String codigo, String descripcion) throws  RemoteException, SistemaException{
		VOAsignatura voAsignatura = new VOAsignatura(codigo, nombre, descripcion);
		this.getConexion().ingresarAsignatura(voAsignatura);
	}
}

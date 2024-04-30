package sistema.grafica.controladores;

import java.rmi.RemoteException;

import sistema.logica.excepciones.SistemaException;

public class ControladorRespaldo extends ControladorSistema {

	public void respaldar() throws RemoteException, SistemaException {
		this.getConexion().respaldo();
	}
}

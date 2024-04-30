package sistema.grafica.controladores;

import java.rmi.RemoteException;

import sistema.logica.VO.VONota;
import sistema.logica.excepciones.SistemaException;

public class ControladorVentanaRegistrarCalificacion extends ControladorSistema {

	public void ingresarCalificacion(int cedula, int numeroInscripcion, int calificacionFinal) 
			throws RemoteException, SistemaException{
		VONota nuevaVONota = new VONota(cedula, numeroInscripcion, calificacionFinal);
		this.getConexion().registroNota(nuevaVONota);
		
	}
}

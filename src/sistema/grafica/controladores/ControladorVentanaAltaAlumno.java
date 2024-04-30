package sistema.grafica.controladores;

import java.rmi.RemoteException;

import sistema.logica.VO.VOAlumnoBecado;
import sistema.logica.VO.VOAlumno;
import sistema.logica.excepciones.SistemaException;

public class ControladorVentanaAltaAlumno extends ControladorSistema {

	public void ingresarAlumno(int cedula, String nombre, String apellido, String direccion, int telefono) throws RemoteException, SistemaException{
		VOAlumno nuevoVOAlumno = new VOAlumno(cedula, nombre, telefono, direccion, apellido);
		this.getConexion().inscripcionAlumno(nuevoVOAlumno);
	}

	public void ingresarAlumnoBecado(int cedula, String nombre, String apellido, String direccion, int telefono,
			int descuento, String razon) throws RemoteException, SistemaException{
		VOAlumnoBecado nuevoVOBecado = new VOAlumnoBecado(cedula, nombre, telefono, direccion, apellido, descuento, razon);
		this.getConexion().inscripcionAlumno(nuevoVOBecado);
	}
	
}

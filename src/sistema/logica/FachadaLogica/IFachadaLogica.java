package sistema.logica.FachadaLogica;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import sistema.logica.VO.*;
import sistema.logica.constantes.Modalidad;
import sistema.logica.excepciones.SistemaException;

public interface IFachadaLogica extends Remote {
	public void ingresarAsignatura(VOAsignatura asignatura) throws RemoteException, SistemaException;

	public ArrayList<VOAsignatura> listadoDeAsignaturas() throws RemoteException, SistemaException;

	public void inscripcionAlumno(VOAlumno alumno) throws RemoteException, SistemaException;

	public ArrayList<VOListadoApellido> listarAlumnosApellido(String apellido) throws RemoteException, SistemaException;

	public VOAlumno listarDetalladoCedula(int ci) throws RemoteException, SistemaException;

	public void inscripcionAsignatura(VOInscripcionCompleta inscripcionAlumno) throws RemoteException, SistemaException;

	public void registroNota(VONota objNota) throws RemoteException, SistemaException;

	public float obtenerMontoInscripciones(VOMontoInscripcion objMontoInscripcion)
			throws RemoteException, SistemaException;

	public ArrayList<VOListadoEscolaridad> listarEscolaridad(VOEscolaridad objEscolaridad)
			throws RemoteException, SistemaException;

	public ArrayList<VOListadoEgresado> listarEgresados(Modalidad tipoListado) throws RemoteException, SistemaException;

	public void respaldo() throws RemoteException, SistemaException;
}
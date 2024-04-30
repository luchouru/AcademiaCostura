package sistema.logica.FachadaLogica;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

import java.util.ArrayList;

import sistema.logica.VO.*;
import sistema.logica.alumnos.*;
import sistema.logica.asignaturas.*;
import sistema.logica.inscripciones.*;
import sistema.logica.monitor.Monitor;
import sistema.logica.constantes.CodExcepcionesAlumno;
import sistema.logica.constantes.CodExcepcionesAsignatura;
import sistema.logica.constantes.Modalidad;
import sistema.logica.excepciones.SistemaException;
import sistema.persistencia.FachadaPersistencia;

public class FachadaLogica extends UnicastRemoteObject implements IFachadaLogica {
	private Alumnos alumnos;
	private Asignaturas asignaturas;
	private static FachadaLogica instancia;
	private static Monitor monitor = new Monitor();

	private FachadaLogica() throws RemoteException {
		try {
			this.restauracion();
		} catch (SistemaException e) {
			e.printStackTrace();
		}
	}

	public static FachadaLogica getInstancia() throws RemoteException {
		if (instancia == null) {
			instancia = new FachadaLogica();
		}
		return instancia;
	}

	// Requerimiento 1
	public void ingresarAsignatura(VOAsignatura asignatura) throws RemoteException, SistemaException {
		monitor.comienzoEscritura();
		if (asignaturas.estaLleno()) {
			monitor.terminoEscritura();
			throw new SistemaException(CodExcepcionesAsignatura.LLENO.getMsg());
		} else {
			String cod = asignatura.getCodigo();
			if (asignaturas.existeAsignatura(cod)) {
				monitor.terminoEscritura();
				throw new SistemaException(CodExcepcionesAsignatura.YA_EXISTE.getMsg());
			} else {
				String codigo = asignatura.getCodigo();
				String nombre = asignatura.getNombre();
				String descContenido = asignatura.getDescripcion();
				Asignatura nuevaAsignatura;
				nuevaAsignatura = new Asignatura(codigo, nombre, descContenido);
				asignaturas.insertar(nuevaAsignatura);
				monitor.terminoEscritura();
			}
		}
	}

	// Requerimiento 2
	public ArrayList<VOAsignatura> listadoDeAsignaturas() throws RemoteException, SistemaException {
		monitor.comienzoLectura();
		if (asignaturas.estaVacio()) {
			monitor.terminoLectura();
			throw new SistemaException(CodExcepcionesAsignatura.SIN_ASIGNATURAS.getMsg());
		} else {
			monitor.terminoLectura();
			return asignaturas.listarAsignaturas();
		}
	}

	// Requerimiento 3
	public void inscripcionAlumno(VOAlumno alumno) throws RemoteException, SistemaException {
		int ci = alumno.getCedula();
		monitor.comienzoEscritura();
		if (alumnos.existeAlumno(ci)) {
			monitor.terminoEscritura();
			throw new SistemaException(CodExcepcionesAlumno.YA_EXISTE.getMsg());
		} else {
			String nombre = alumno.getNombre();
			String apellido = alumno.getApellido();
			String domicilio = alumno.getDomicilio();
			int tel = alumno.getTelefono();
			Alumno nuevoAlumno;
			if (alumno instanceof VOAlumnoBecado) {
				nuevoAlumno = new Becado(ci, nombre, apellido, domicilio, tel, ((VOAlumnoBecado) alumno).getDescuento(),
						((VOAlumnoBecado) alumno).getRazon());

			} else {
				nuevoAlumno = new Alumno(ci, nombre, apellido, domicilio, tel);
			}
			alumnos.insertAlumno(nuevoAlumno);
			monitor.terminoEscritura();
		}
	}

	// Requerimiento 4
	public ArrayList<VOListadoApellido> listarAlumnosApellido(String apellido)
			throws RemoteException, SistemaException {
		monitor.comienzoLectura();
		if (alumnos.estaVacio()) {
			monitor.terminoLectura();
			throw new SistemaException(CodExcepcionesAlumno.SIN_ALUMNOS.getMsg());
		} else {
			monitor.terminoLectura();
			return alumnos.listarAlumnosApellido(apellido);
		}
	}

	// Requerimiento 5
	public VOAlumno listarDetalladoCedula(int ci) throws SistemaException {
		monitor.comienzoLectura();
		if (alumnos.estaVacio()) {
			monitor.terminoLectura();
			throw new SistemaException(CodExcepcionesAlumno.SIN_ALUMNOS.getMsg());
		} else {
			if (!alumnos.existeAlumno(ci)) {
				monitor.terminoLectura();
				throw new SistemaException(CodExcepcionesAlumno.NO_EXISTE.getMsg());
			} else {
				monitor.terminoLectura();
				return alumnos.listarDetalladoCedula(ci);
			}
		}
	}

	// Requerimiento 6
	public void inscripcionAsignatura(VOInscripcionCompleta inscripcionAlumno)
			throws RemoteException, SistemaException {
		int cedula = inscripcionAlumno.getCedula();
		String codigo = inscripcionAlumno.getCodigoAsignatura();
		int anio = inscripcionAlumno.getAnio();
		float monto = inscripcionAlumno.getMonto();
		monitor.comienzoEscritura();
		if (!alumnos.existeAlumno(cedula)) {
			monitor.terminoEscritura();
			throw new SistemaException(CodExcepcionesAlumno.NO_EXISTE.getMsg());
		} else {
			if (!asignaturas.existeAsignatura(codigo)) {
				monitor.terminoEscritura();
				throw new SistemaException(CodExcepcionesAsignatura.NO_EXISTE.getMsg());
			} else {
				Alumno alumno = alumnos.findAlumno(cedula);
				if (!alumno.tieneInscripciones()) {
					Asignatura primera = asignaturas.findAsignatura(codigo);
					alumnos.agregarInscripcion(alumno, anio, monto, primera);
					monitor.terminoEscritura();
				} else {
					if (!alumno.anioInscripcionValido(anio)) {
						monitor.terminoEscritura();
						throw new SistemaException(CodExcepcionesAsignatura.ANIO_INVALIDO.getMsg());
					} else {
						Inscripcion previa = alumno.inscripcionPrevia(codigo);
						if (previa == null) {
							Asignatura asignatura = asignaturas.findAsignatura(codigo);
							alumnos.agregarInscripcion(alumno, anio, monto, asignatura);
							monitor.terminoEscritura();
						} else {
							if (previa.estaAprobada()) {
								monitor.terminoEscritura();
								throw new SistemaException(CodExcepcionesAsignatura.ESTA_APROBADA.getMsg());
							} else if (!previa.tieneCalificacionFinal()) {
								monitor.terminoEscritura();
								throw new SistemaException(CodExcepcionesAsignatura.NO_ESTA_CERRADA.getMsg());
							} else if (previa.getAnio() == anio) {
								monitor.terminoEscritura();
								throw new SistemaException(CodExcepcionesAsignatura.CURSANDO_ANIO_LECTIVO.getMsg());
							} else {
								Asignatura asignatura = asignaturas.findAsignatura(codigo);
								alumnos.agregarInscripcion(alumno, anio, monto, asignatura);
								monitor.terminoEscritura();
							}
						}
					}
				}
			}
		}
	}

	// Requerimiento 7
	public void registroNota(VONota objNota) throws RemoteException, SistemaException {
		monitor.comienzoEscritura();
		if (!alumnos.existeAlumno(objNota.getCedula())) {
			throw new SistemaException(CodExcepcionesAlumno.NO_EXISTE.getMsg());
		} else {
			int cedula = objNota.getCedula();
			int calificacionFinal = objNota.getCalificacionFinal();
			int numeroInscripcion = objNota.getNumeroInscripcion();
			Alumno alumno = alumnos.findAlumno(cedula);

			if (!alumno.existeInscripcion(numeroInscripcion)) {
				monitor.terminoEscritura();
				throw new SistemaException(CodExcepcionesAlumno.NO_EXISTE_INSCRIPCION.getMsg());
			} else {
				Inscripcion insc = alumno.obtenerInscripcion(numeroInscripcion);
				if (insc.tieneCalificacionFinal()) {
					monitor.terminoEscritura();
					throw new SistemaException(CodExcepcionesAlumno.RESULTADO_ASIGNADO.getMsg());
				} else {
					alumnos.registrarNota(alumno, numeroInscripcion, calificacionFinal);
					monitor.terminoEscritura();
				}
			}
		}
	}

	// Requerimiento 8
	public float obtenerMontoInscripciones(VOMontoInscripcion objMontoInscripcion)
			throws RemoteException, SistemaException {
		monitor.comienzoLectura();
		if (!alumnos.existeAlumno(objMontoInscripcion.getCi())) {
			monitor.terminoLectura();
			throw new SistemaException(CodExcepcionesAlumno.NO_EXISTE.getMsg());
		} else {
			monitor.terminoLectura();
			return alumnos.obtenerMontoInscripcionesEnAnio(objMontoInscripcion.getCi(), objMontoInscripcion.getAnio());
		}
	}

	// Requerimiento 9
	public ArrayList<VOListadoEscolaridad> listarEscolaridad(VOEscolaridad objEscolaridad)
			throws RemoteException, SistemaException {
		int cedula = objEscolaridad.getCedula();
		monitor.comienzoLectura();
		if (alumnos.estaVacio()) {
			monitor.terminoLectura();
			throw new SistemaException(CodExcepcionesAlumno.SIN_ALUMNOS.getMsg());
		} else {
			if (!alumnos.existeAlumno(cedula)) {
				monitor.terminoLectura();
				throw new SistemaException(CodExcepcionesAlumno.NO_EXISTE.getMsg());
			} else {
				if (!alumnos.tieneInscripciones(cedula)) {
					monitor.terminoLectura();
					throw new SistemaException(CodExcepcionesAlumno.SIN_INSCRIPCIONES.getMsg());
				} else {
					Modalidad tipoListado = objEscolaridad.getTipoListado();
					ArrayList<VOListadoEscolaridad> escolaridad = alumnos.listarEscolaridad(cedula, tipoListado);
					monitor.terminoLectura();
					return escolaridad;
				}
			}
		}
	}

	// Requerimiento 10
	public ArrayList<VOListadoEgresado> listarEgresados(Modalidad tipoListado)
			throws RemoteException, SistemaException {
		monitor.comienzoLectura();
		if (alumnos.estaVacio()) {
			monitor.terminoLectura();
			throw new SistemaException(CodExcepcionesAlumno.SIN_ALUMNOS.getMsg());
		} else {
			ArrayList<VOListadoEgresado> listadoDeEgresados = alumnos.listarEgresados(tipoListado);
			if (listadoDeEgresados == null) {
				monitor.terminoLectura();
				throw new SistemaException(CodExcepcionesAlumno.SIN_EGRESADOS.getMsg());
			} else {
				monitor.terminoLectura();
				return listadoDeEgresados;
			}
		}
	}

	// Requerimiento 11
	public void respaldo() throws RemoteException, SistemaException {
		VOPersistencia datos = new VOPersistencia(alumnos, asignaturas);
		FachadaPersistencia.getInstancia().respaldar(datos);
	}

	// Requerimiento 12
	private void restauracion() throws SistemaException {
		VOPersistencia datos;
		try {
			datos = FachadaPersistencia.getInstancia().recuperar();
			this.alumnos = datos.getAlumnos();
			this.asignaturas = datos.getAsignaturas();
		} catch (SistemaException e) {
			this.alumnos = new Alumnos();
			this.asignaturas = new Asignaturas();
			e.printStackTrace();
		}
	}

}

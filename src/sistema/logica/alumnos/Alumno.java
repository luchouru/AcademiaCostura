package sistema.logica.alumnos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import sistema.logica.VO.VOAlumno;
import sistema.logica.VO.VOListadoEscolaridad;
import sistema.logica.asignaturas.Asignatura;
import sistema.logica.constantes.Modalidad;
import sistema.logica.inscripciones.Inscripcion;
import sistema.logica.inscripciones.Inscripciones;

public class Alumno implements Serializable {
	private int cedula;
	private String nombre;
	private String apellido;
	private String domicilio;
	private int telefono;
	private int cantMateriasAprobadas;
	private Inscripciones inscripciones;

	public Alumno(int cedula, String nombre, String apellido, String domicilio, int telefono) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.cantMateriasAprobadas = 0;
		this.inscripciones = new Inscripciones();
	}

	public int getCedula() {
		return cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public int getCantMateriasAprobadas() {
		return cantMateriasAprobadas;
	}

	public void setCantMateriasAprobadas(int cantMateriasAprobadas) {
		this.cantMateriasAprobadas = cantMateriasAprobadas;
	}

	public LinkedList<Inscripcion> getInscripciones() {
		return inscripciones.getInscripciones();
	}

	public void setInscripciones(Inscripciones inscripciones) {
		this.inscripciones = inscripciones;
	}

	public void agregarInscripcion(int anio, float monto, Asignatura asignatura) {
		Inscripcion nueva = new Inscripcion(this.numeroInscripcion(), anio, monto, asignatura);
		this.inscripciones.insertar(nueva);
	}

	public boolean existeInscripcionPrevia(String codigo) {
		return this.inscripciones.existeInscripcionPrevia(codigo);
	}

	public Inscripcion inscripcionPrevia(String codigo) {
		return this.inscripciones.inscripcionPrevia(codigo);
	}

	public VOAlumno toVO() {
		return new VOAlumno(this.getCedula(), this.getNombre(), this.getTelefono(), this.getDomicilio(),
				this.getApellido(), this.getCantMateriasAprobadas());
	}

	private int numeroInscripcion() {
		int numero = 0;
		if (!this.tieneInscripciones())
			numero = 1;
		else
			numero = this.ultimaInscripcion().getNumero() + 1;
		return numero;
	}

	public Inscripcion ultimaInscripcion() {
		return this.inscripciones.ultimaInscripcion();
	}

	public boolean tieneInscripciones() {
		return this.inscripciones.tieneInscripciones();
	}

	public boolean anioInscripcionValido(int anio) {
		return this.inscripciones.ultimaInscripcion().esAnioMayorOIgual(anio);
	}

	public ArrayList<VOListadoEscolaridad> listarEscolaridad(Modalidad tipoListado) {
		return this.inscripciones.listarEscolaridad(tipoListado);
	}

	public float promedioTotal() {
		return this.inscripciones.promedioTotal();
	}

	public float promedioAprobacion() {
		return this.inscripciones.promedioAprobaciones();
	}
	
	public void registrarNota(int numero, int calificacionFinal) {
		this.inscripciones.registrarNota(numero, calificacionFinal);
		if(calificacionFinal >= 6) {
			int nuevaCalificacion = this.getCantMateriasAprobadas() + 1;
			this.setCantMateriasAprobadas(nuevaCalificacion);
		}
	}
	
	public Inscripcion obtenerInscripcion(int numero) {
		return this.inscripciones.obtenerInscripcion(numero);
	}
	
	public boolean existeInscripcion(int numero) {
		return this.inscripciones.existeInscripcion(numero);
	}
	
}

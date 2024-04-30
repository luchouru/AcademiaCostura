package sistema.logica.alumnos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import sistema.logica.VO.VOListadoEgresado;
import sistema.logica.VO.VOListadoEgresadoCompleta;
import sistema.logica.VO.VOListadoEscolaridad;
import sistema.logica.asignaturas.Asignatura;
import sistema.logica.asignaturas.Asignaturas;
import sistema.logica.VO.VOAlumno;
import sistema.logica.VO.VOListadoApellido;
import sistema.logica.constantes.Modalidad;
import sistema.logica.inscripciones.Inscripcion;

public class Alumnos implements Serializable {
	private TreeMap<Integer, Alumno> alumnos;

	public Alumnos() {
		this.alumnos = new TreeMap<Integer, Alumno>();
	}

	public boolean existeAlumno(int ci) {
		return this.alumnos.containsKey(ci);
	}

	public void insertAlumno(Alumno alumn) {
		this.alumnos.put(alumn.getCedula(), alumn);
	}

	public Alumno findAlumno(int ci) {
		return this.alumnos.get(ci);
	}

	public ArrayList<VOListadoApellido> listarAlumnosApellido(String apellidoBuscado) {
		ArrayList<VOListadoApellido> listadoAlumnosApellido = new ArrayList<VOListadoApellido>();
		Iterator<Map.Entry<Integer, Alumno>> iterador = alumnos.entrySet().iterator();
		while (iterador.hasNext()) {
			Map.Entry<Integer, Alumno> alumno = iterador.next();
			Alumno al = alumno.getValue();
			if (al.getApellido().toLowerCase().startsWith(apellidoBuscado.toLowerCase())) {
				int cedula = al.getCedula();
				String nombre = al.getNombre();
				String apellido = al.getApellido();
				String tipo = "Comun";
				if (al instanceof Becado) {

					tipo = "Becado";
				}

				VOListadoApellido nuevo = new VOListadoApellido(cedula, nombre, apellido, tipo);
				listadoAlumnosApellido.add(nuevo);
			}
		}
		return listadoAlumnosApellido;
	}

	public VOAlumno listarDetalladoCedula(int ci) {
		return this.findAlumno(ci).toVO();
	}

	public boolean estaVacio() {
		return alumnos.isEmpty();
	}

	public ArrayList<VOListadoEgresado> listarEgresados(Modalidad tipoListado) {
		Modalidad completo = Modalidad.COMPLETO;
		ArrayList<VOListadoEgresado> listadoEgresado = new ArrayList<VOListadoEgresado>();
		if (tipoListado.equals(completo)) {
			Iterator<Map.Entry<Integer, Alumno>> iterador = alumnos.entrySet().iterator();
			while (iterador.hasNext()) {
				Map.Entry<Integer, Alumno> alumno = iterador.next();
				if (alumno.getValue().getCantMateriasAprobadas() == Asignaturas.CANT_ASIGNATURAS) {
					float promedioTotal = alumno.getValue().promedioTotal();
					float promedioAprobacion = alumno.getValue().promedioAprobacion();
					int cedula = alumno.getValue().getCedula();
					String nombre = alumno.getValue().getNombre();
					String apellido = alumno.getValue().getApellido();
					VOListadoEgresadoCompleta nuevo = new VOListadoEgresadoCompleta(cedula, nombre, apellido,
							promedioTotal, promedioAprobacion);
					listadoEgresado.add(nuevo);
				}
			}
		} else {
			Iterator<Map.Entry<Integer, Alumno>> iterador = alumnos.entrySet().iterator();
			while (iterador.hasNext()) {
				Map.Entry<Integer, Alumno> alumno = iterador.next();
				if (alumno.getValue().getCantMateriasAprobadas() == 10) {
					int cedula = alumno.getValue().getCedula();
					String nombre = alumno.getValue().getNombre();
					String apellido = alumno.getValue().getApellido();
					VOListadoEgresado nuevo = new VOListadoEgresado(cedula, nombre, apellido);
					listadoEgresado.add(nuevo);
				}
			}
		}
		return listadoEgresado;
	}

	public float obtenerMontoInscripcionesEnAnio(int ci, int anio) {
		float montoRecaudado = 0;

		if (this.existeAlumno(ci)) {
			Alumno alumno = this.findAlumno(ci);

			for (Inscripcion inscripcion : alumno.getInscripciones()) {
				float montoAnual = 0;

				if (inscripcion.getAnio() == anio) {
					montoAnual = inscripcion.getMontoBase() * 9;

					if (alumno instanceof Becado) {
						float descuentoMonto = (montoAnual * ((Becado) alumno).getDescuento()) / 100;
						montoAnual -= descuentoMonto;
					}
				}

				montoRecaudado += montoAnual;
			}
		}

		return montoRecaudado;
	}

	public void agregarInscripcion(Alumno alumno, int anio, float monto, Asignatura primera) {
		alumno.agregarInscripcion(anio, monto, primera);
	}

	public boolean tieneInscripciones(int cedula) {
		Alumno alumno = this.findAlumno(cedula);
		return alumno.tieneInscripciones();
	}

	public ArrayList<VOListadoEscolaridad> listarEscolaridad(int cedula, Modalidad tipoListado) {
		Alumno alumno = this.findAlumno(cedula);
		return alumno.listarEscolaridad(tipoListado);
	}

	public void registrarNota(Alumno alumno, int numeroInscripcion, int calificacionFinal) {
		alumno.registrarNota(numeroInscripcion, calificacionFinal);
	}

}

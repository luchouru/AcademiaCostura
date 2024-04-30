package sistema.logica.inscripciones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import sistema.logica.VO.VOListadoEscolaridad;
import sistema.logica.VO.VOListadoEscolaridadCompleta;
import sistema.logica.asignaturas.Asignatura;
import sistema.logica.constantes.Modalidad;

public class Inscripciones implements Serializable {
	private LinkedList<Inscripcion> inscripciones;

	public Inscripciones() {
		this.inscripciones = new LinkedList<Inscripcion>();
	}

	public void insertar(Inscripcion nueva) {
		this.inscripciones.add(nueva);
	}

	public Inscripcion obtenerInscripcion(int insc) {
		Inscripcion inscripcion = null;
		if (existeInscripcion(insc)) {
			boolean existe = false;
			Iterator<Inscripcion> iterador = this.inscripciones.iterator();
			while (iterador.hasNext() && !existe) {
				Inscripcion inscripcionTemp = iterador.next();
				if (inscripcionTemp.getNumero() == insc) {
					inscripcion = inscripcionTemp;
					existe = true;
				}

			}
		}

		return inscripcion;
	}

	public boolean tieneInscripciones() {
		return (!this.inscripciones.isEmpty());
	}

	public Inscripcion ultimaInscripcion() {
		return this.inscripciones.getLast();
	}

	public boolean existeInscripcionPrevia(String codigo) {
		boolean existe = false;
		Iterator<Inscripcion> iterador = this.inscripciones.descendingIterator();
		while (iterador.hasNext() && !existe) {
			Asignatura asignatura = iterador.next().getAsignatura();
			if (asignatura.getCodigo().equals(codigo))
				existe = true;
		}
		return existe;
	}

	public Inscripcion inscripcionPrevia(String codigo) {
		Inscripcion inscripcion = null;

		Iterator<Inscripcion> iterador = this.inscripciones.descendingIterator();
		while (iterador.hasNext() && inscripcion == null) {
			Inscripcion inscripcionTemp = iterador.next();
			Asignatura asignatura = inscripcionTemp.getAsignatura();
			if (asignatura.getCodigo().equals(codigo))
				inscripcion = inscripcionTemp;
		}
		return inscripcion;
	}

	public float promedioTotal() {
		float sumaNotas = 0;
		for (Inscripcion inscripcion : this.inscripciones) {
			if (inscripcion.getCalificacion() >= 6)
				sumaNotas += inscripcion.getCalificacion();
		}
		return sumaNotas / this.inscripciones.size();
	}

	public float promedioAprobaciones() {
		float sumaNotas = 0;
		for (Inscripcion inscripcion : this.inscripciones) {
			if (inscripcion.getCalificacion() >= 6)
				sumaNotas += inscripcion.getCalificacion();
		}
		return sumaNotas / 10;
	}

	public ArrayList<VOListadoEscolaridad> listarEscolaridad(Modalidad tipoListado) {
		Modalidad completo = Modalidad.COMPLETO;
		ArrayList<VOListadoEscolaridad> escolaridad = new ArrayList<>();
		if (tipoListado.equals(completo)) {
			for (Inscripcion inscripcion : this.inscripciones) {
				int numero = inscripcion.getNumero();
				String asignatura = inscripcion.getAsignatura().getNombre();
				int anio = inscripcion.getAnio();
				float montoBase = inscripcion.getMontoBase();
				int calificacion = inscripcion.getCalificacion();
				VOListadoEscolaridadCompleta nueva = new VOListadoEscolaridadCompleta(numero, asignatura, anio,
						calificacion, montoBase);
				escolaridad.add(nueva);
			}
		} else {
			for (Inscripcion inscripcion : this.inscripciones) {
				if (inscripcion.getCalificacion() > 0) {
					int numero = inscripcion.getNumero();
					String asignatura = inscripcion.getAsignatura().getNombre();
					int anio = inscripcion.getAnio();
					int calificacion = inscripcion.getCalificacion();
					VOListadoEscolaridad nueva = new VOListadoEscolaridad(numero, asignatura, anio, calificacion);
					escolaridad.add(nueva);
				}
			}
		}
		return escolaridad;
	}

	public LinkedList<Inscripcion> getInscripciones() {
		return inscripciones;
	}

	public void registrarNota(int numero, int calificacionFinal) {
		this.obtenerInscripcion(numero).setCalificacion(calificacionFinal);
	}


	public boolean existeInscripcion(int numero) {
		boolean existe = false;

		if (this.tieneInscripciones()) {
			int ultimaInscripcion = this.ultimaInscripcion().getNumero();
			existe = numero > 0 && ultimaInscripcion >= numero;
		}

		return existe;

	}

}

package sistema.logica.inscripciones;

import java.io.Serializable;

import sistema.logica.asignaturas.Asignatura;

public class Inscripcion implements Serializable {
	private int numero;
	private int anio;
	private float montoBase;
	private int calificacion;
	private Asignatura asignatura;

	public Inscripcion(int numero, int anio, float montoBase, Asignatura asignatura) {
		this.numero = numero;
		this.anio = anio;
		this.montoBase = montoBase;
		this.asignatura = asignatura;
		this.calificacion = 0;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public float getMontoBase() {
		return montoBase;
	}

	public void setMontoBase(float montoBase) {
		this.montoBase = montoBase;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public boolean esAnioMayorOIgual(int anio) {
		return (anio >= this.anio);
	}

	public boolean estaAprobada() {
		return (this.calificacion >= 6);
	}

	public boolean tieneCalificacionFinal() {
		return (this.calificacion != 0);
	}

}

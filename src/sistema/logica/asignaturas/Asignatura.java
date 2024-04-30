package sistema.logica.asignaturas;

import java.io.Serializable;

import sistema.logica.VO.VOAsignatura;

public class Asignatura implements Serializable{
	private String codigo;
	private String nombre;
	private String descripcion;

	public Asignatura(String codigo, String nombre, String descripcion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public VOAsignatura toVO() {
		return new VOAsignatura(this.codigo, this.nombre, this.descripcion);
	}

}

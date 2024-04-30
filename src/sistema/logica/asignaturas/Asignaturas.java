package sistema.logica.asignaturas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import sistema.logica.VO.VOAsignatura;

public class Asignaturas implements Serializable {
	public static final int CANT_ASIGNATURAS = 10;
	private ArrayList<Asignatura> asignaturas;
	private int tope;

	public Asignaturas() {
		tope = CANT_ASIGNATURAS;
		asignaturas = new ArrayList<>();
	}

	public boolean existeAsignatura(String codigo) {
		Iterator<Asignatura> iterator = asignaturas.iterator();
		boolean encontre = false;

		while (iterator.hasNext() && !encontre) {
			Asignatura asignatura = iterator.next();
			encontre = asignatura.getCodigo().equals(codigo);
		}

		return encontre;
	}

	public boolean estaLleno() {
		return (asignaturas.size() == tope);
	}

	public void insertar(Asignatura nueva) {
		asignaturas.add(nueva);
	}

	public boolean estaVacio() {
		return (asignaturas.isEmpty());
	}

	public Asignatura findAsignatura(String codigo) {
		Asignatura encontrada = null;
		Iterator<Asignatura> iterador = asignaturas.iterator();
		while (iterador.hasNext()) {
			Asignatura temp = iterador.next();
			if (temp.getCodigo().equals(codigo))
				encontrada = temp;
		}
		return encontrada;
	}

	public ArrayList<VOAsignatura> listarAsignaturas() {
		ArrayList<VOAsignatura> listaAsignaturas = new ArrayList<VOAsignatura>();
		for (Asignatura actual : asignaturas) {
			listaAsignaturas.add(actual.toVO());
		}
		return listaAsignaturas;
	}
}

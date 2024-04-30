package sistema;

import java.rmi.RemoteException;
import java.util.ArrayList;

import sistema.cliente.MainCliente;
import sistema.logica.FachadaLogica.FachadaLogica;
import sistema.logica.VO.*;
import sistema.logica.constantes.Modalidad;
import sistema.logica.excepciones.SistemaException;
import sistema.servidor.MainServidor;

public class Principal {

	public static void main(String[] args) throws RemoteException {

		try {
			MainServidor.main(args);
			MainCliente.main(args);
		} catch (SistemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// FachadaLogica fachada = FachadaLogica.getInstancia();
//		
		// probarIngresoAsignaturas(fachada); //Req 01: ingresarAsignatura
		// probarIngresoAlumno(fachada); //Req 03: inscripcionAlumno
		// probarInscripcionAsignatura(fachada); //Req 06: inscripcionAsignatura
//		probarListarAsignaturas(fachada);			//Req 02: listadoDeAsignaturas
//		probarListarAlumnosApellido(fachada);		//Req 04: listarAlumnosApellido
//		probarListarDetalladoCedula(fachada);						//Req 05: listarDetalladoCedula
//		probarRegistroNota(fachada);				//Req 07: registroNota
//		probarObtenerMontoInscripciones(fachada);	//Req 08: obtenerMontoInscripciones
//		probarListarEscolaridad(fachada);						//Req 09: listarEscolaridad
//		probarListarEgresados(fachada);				//Req 10: listarEgresados
//		
//		try {
//			fachada.respaldo();
//		} catch (SistemaException e) {
//			e.printStackTrace();
//		}
	}

	private static void probarObtenerMontoInscripciones(FachadaLogica fachada) throws RemoteException {
		try {
			VOMontoInscripcion montoInsc = new VOMontoInscripcion(23456789, 2023);
			System.out.println(fachada.obtenerMontoInscripciones(montoInsc));
		} catch (SistemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void probarIngresoAlumno(FachadaLogica fachada) throws RemoteException {
		VOAlumno alumno1 = new VOAlumno(123456789, "Juan", 5551234, "Calle Roja 123", "Perez");
		VOAlumno alumno2 = new VOAlumno(234567890, "Maria", 5552345, "Calle Verde 456", "Gomez");
		VOAlumno alumno3 = new VOAlumno(345678901, "Pedro", 5553456, "Calle Azul 789", "Rodriguez");
		VOAlumno alumno4 = new VOAlumno(456789012, "Ana", 5554567, "Calle Naranja 321", "Sanchez");
		VOAlumno alumno5 = new VOAlumno(567890123, "Carlos", 5555678, "Calle Morada 654", "Fernandez");
		VOAlumno alumno6 = new VOAlumno(678901234, "Luisa", 5556789, "Calle Lila 987", "Lopez");
		VOAlumno alumno7 = new VOAlumno(789012345, "Pablo", 5557890, "Calle Amarilla 210", "Garcia");
		VOAlumno alumno8 = new VOAlumno(890123456, "Sofia", 5558901, "Calle Rosa 543", "Martinez");
		VOAlumno alumno9 = new VOAlumno(901234567, "Carmen", 5559012, "Calle Celeste 876", "Gonzalez");
		VOAlumno alumno10 = new VOAlumno(1012345678, "Luis", 55510123, "Calle Violeta 123", "Ramirez");

		VOAlumnoBecado alumnoBecado1 = new VOAlumnoBecado(111111111, "Andrea", 55511121, "Avenida Gris 456",
				"Hernandez", 20, "Becado por baja ingresos");
		VOAlumnoBecado alumnoBecado2 = new VOAlumnoBecado(222222222, "Roberto", 555151617, "Paseo Dorado 789", "Torres",
				15, "Becado por excelencia académica");
		VOAlumnoBecado alumnoBecado3 = new VOAlumnoBecado(333333333, "Alicia", 555202122, "Calle Oro 1098", "Vargas",
				10, "Becado por necesidades especiales");
		VOAlumnoBecado alumnoBecado4 = new VOAlumnoBecado(444444444, "Juan Carlos", 55534353, "Boulevard Plata 321",
				"Perez", 5, "Becado por deportes");
		VOAlumnoBecado alumnoBecado5 = new VOAlumnoBecado(555555555, "Mariana", 55545464, "Avenida Bronce 654",
				"Ramirez", 25, "Becado por investigación");
		VOAlumnoBecado alumnoBecado6 = new VOAlumnoBecado(666666666, "Luis Alberto", 55556585, "Calle Plata 987",
				"Gonzalez", 30, "Becado por liderazgo");
		VOAlumnoBecado alumnoBecado7 = new VOAlumnoBecado(777777777, "Ana Maria", 56768697, "Paseo Oro 210", "Martinez",
				22, "Becado por voluntariado");
		VOAlumnoBecado alumnoBecado8 = new VOAlumnoBecado(888888888, "Carlos Alberto", 55879808, "Calle Bronce 543",
				"Garcia", 18, "Becado por tutoría");
		VOAlumnoBecado alumnoBecado9 = new VOAlumnoBecado(999999999, "Sofia Maria", 555809101, "Boulevard Oro 876",
				"Hernandez", 12, "Becado por cultura");
		VOAlumnoBecado alumnoBecado10 = new VOAlumnoBecado(100000000, "Luis Enrique", 91314151, "Avenida Bronce 123",
				"Torres", 15, "Becado por música");

		try {
			fachada.inscripcionAlumno(alumno1);
			fachada.inscripcionAlumno(alumno2);
			fachada.inscripcionAlumno(alumno3);
			fachada.inscripcionAlumno(alumno4);
			fachada.inscripcionAlumno(alumno5);
			fachada.inscripcionAlumno(alumno6);
			fachada.inscripcionAlumno(alumno7);
			fachada.inscripcionAlumno(alumno8);
			fachada.inscripcionAlumno(alumno9);
			fachada.inscripcionAlumno(alumno10);
			fachada.inscripcionAlumno(alumnoBecado1);
			fachada.inscripcionAlumno(alumnoBecado2);
			fachada.inscripcionAlumno(alumnoBecado3);
			fachada.inscripcionAlumno(alumnoBecado4);
			fachada.inscripcionAlumno(alumnoBecado5);
			fachada.inscripcionAlumno(alumnoBecado6);
			fachada.inscripcionAlumno(alumnoBecado7);
			fachada.inscripcionAlumno(alumnoBecado8);
			fachada.inscripcionAlumno(alumnoBecado9);
			fachada.inscripcionAlumno(alumnoBecado10);
		} catch (SistemaException e) {
			System.out.println(e.getMessage());
		}

		try {
			VOAlumno alumnoRes1 = fachada.listarDetalladoCedula(alumno1.getCedula());
			System.out.println(alumnoRes1.getCedula());
			System.out.println(alumnoRes1.getNombre());
			System.out.println(alumnoRes1.getApellido());
			System.out.println(alumnoRes1.getTelefono());
			System.out.println(alumnoRes1.getCantMateriasAprobadas());

			VOAlumnoBecado alumnoRes2 = (VOAlumnoBecado) fachada.listarDetalladoCedula(alumnoBecado3.getCedula());
			System.out.println(alumnoRes2.getCedula());
			System.out.println(alumnoRes2.getNombre());
			System.out.println(alumnoRes2.getApellido());
			System.out.println(alumnoRes2.getTelefono());
			System.out.println(alumnoRes2.getCantMateriasAprobadas());
			System.out.println(alumnoRes2.getDescuento());
			System.out.println(alumnoRes2.getRazon());
		} catch (SistemaException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void probarIngresoAsignaturas(FachadaLogica fachada) throws RemoteException {
		VOAsignatura asignatura2 = new VOAsignatura("AS101", "Zurcido",
				"Técnicas de zurcido aplicadas a la confección.");
		VOAsignatura asignatura3 = new VOAsignatura("AS102", "Bordado",
				"Técnicas de bordado para la decoración de textiles.");
		VOAsignatura asignatura4 = new VOAsignatura("AS103", "Corte",
				"Técnicas de corte para la confección de prendas.");
		VOAsignatura asignatura5 = new VOAsignatura("AS104", "Tejidos",
				"Estudios sobre los tejidos y su aplicación en la moda.");
		VOAsignatura asignatura6 = new VOAsignatura("AS105", "Costura",
				"Técnicas de costura para la confección de prendas.");
		VOAsignatura asignatura7 = new VOAsignatura("AS106", "Teñido", "Procesos y técnicas de teñido para textiles.");
		VOAsignatura asignatura8 = new VOAsignatura("AS107", "Moldes",
				"Creación y aplicación de moldes para la confección.");
		VOAsignatura asignatura9 = new VOAsignatura("AS108", "Botones",
				"Diseño y fabricación de botones para la moda.");
		VOAsignatura asignatura10 = new VOAsignatura("AS109", "Cierres",
				"Técnicas y diseños de cierres para la confección.");
		// VOAsignatura asignatura11 = new VOAsignatura("AS110", "Taller de tendencias
		// en moda",
		// "Análisis y estudio de las últimas tendencias en la moda.");

		try {
			fachada.ingresarAsignatura(asignatura2);
			fachada.ingresarAsignatura(asignatura3);
			fachada.ingresarAsignatura(asignatura4);
			fachada.ingresarAsignatura(asignatura5);
			fachada.ingresarAsignatura(asignatura6);
			fachada.ingresarAsignatura(asignatura7);
			fachada.ingresarAsignatura(asignatura8);
			fachada.ingresarAsignatura(asignatura9);
			fachada.ingresarAsignatura(asignatura10);
			// fachada.ingresarAsignatura(asignatura11);
		} catch (SistemaException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void probarListarAsignaturas(FachadaLogica fachada) {
		ArrayList<VOAsignatura> listaAsignatura;
		try {
			listaAsignatura = fachada.listadoDeAsignaturas();
			for (VOAsignatura vo : listaAsignatura) {
				System.out.println(vo.getCodigo());
				System.out.println(vo.getNombre());
				System.out.println(vo.getDescripcion());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void probarListarDetalladoCedula(FachadaLogica fachada) {
		VOInscripcionCompleta vo = new VOInscripcionCompleta(2022, 23456789, "AS101", 500);
		try {
			fachada.inscripcionAsignatura(vo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void probarListarEscolaridad(FachadaLogica fachada) {
		Modalidad COMPLETO = null;
		VOEscolaridad vo = new VOEscolaridad(23456789, COMPLETO);
		ArrayList<VOListadoEscolaridad> listaEscolaridad;
		try {
			listaEscolaridad = fachada.listarEscolaridad(vo);
			for (VOListadoEscolaridad voLE : listaEscolaridad) {
				System.out.println(voLE.getNumero());
				System.out.println(voLE.getAnio());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void probarListarAlumnosApellido(FachadaLogica fachada) {

	}

	private static void probarInscripcionAsignatura(FachadaLogica fachada) {

	}

	private static void probarRegistroNota(FachadaLogica fachada) {
		/*
		 * VONota asignatura1 = new VONota("AS101", "Lengua Castellana",
		 * "Estudios de la lengua y literatura castellana."); VONota asignatura2 = new
		 * VONota("AS102", "Ingles", "Desarrollo de habilidades en ingles."); VONota
		 * asignatura3 = new VONota("AS103", "Matematicas", "Calculo y algebra."); try {
		 * fachada.ingresarAsignatura(asignatura1);
		 * fachada.ingresarAsignatura(asignatura2);
		 * fachada.ingresarAsignatura(asignatura3); } catch (SistemaException e) {
		 * System.out.println(e.getMessage()); }
		 */
	}

	private static void probarListarEgresados(FachadaLogica fachada) {

	}
}

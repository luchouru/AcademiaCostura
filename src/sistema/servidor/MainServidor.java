package sistema.servidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import sistema.conexion.ArchivoConfiguracion;
import sistema.conexion.Connector;
import sistema.logica.FachadaLogica.FachadaLogica;
import sistema.logica.VO.VOAlumnoBecado;
import sistema.logica.VO.VOAlumno;
import sistema.logica.VO.VOAsignatura;
import sistema.logica.VO.VOInscripcionCompleta;
import sistema.logica.VO.VONota;
import sistema.logica.excepciones.SistemaException;

public class MainServidor {
	public static void main(String[] args) throws SistemaException {
		try {
			String ip = ArchivoConfiguracion.getInstancia().getIpServidor();
			int port = ArchivoConfiguracion.getInstancia().getPuertoServidor();

			LocateRegistry.createRegistry(port);

			String ruta = "//" + ip + ":" + port + "/sistema";

			Connector.setConnectorRuta(ruta);

			FachadaLogica fachada = FachadaLogica.getInstancia();

			probarIngresoAsignaturas();
			probarIngresoAlumno();
			probarMultiplesInscripciones();
			probarCasiEgresado();
			probarEgresadoSinReprobacion();
			probarEgresadoConReprobacion();
			probarEscolaridadComun();
			probarEscolaridadBecado();

			System.out.println("Antes de publicar");
			Naming.rebind(ruta, fachada);
			System.out.println("Luego de publicar ");
		} catch (RemoteException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static void probarIngresoAsignaturas() throws RemoteException {
		FachadaLogica fachada = FachadaLogica.getInstancia();

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
		VOAsignatura asignatura11 = new VOAsignatura("AS110", "Taller de tendencias en moda",
				"Análisis y estudio de las últimas tendencias en la moda.");

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
			fachada.ingresarAsignatura(asignatura11);
		} catch (SistemaException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void probarIngresoAlumno() throws RemoteException {
		FachadaLogica fachada = FachadaLogica.getInstancia();
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
	}

	private static void probarMultiplesInscripciones() throws RemoteException {
		FachadaLogica fachada = FachadaLogica.getInstancia();

		// 222222222

		/*
		 * Asignaturas AS101 AS102 AS103 AS104 AS105 AS106 AS107 AS108 AS109 AS110
		 */

		VOInscripcionCompleta inscripcion1 = new VOInscripcionCompleta(2024, 222222222, "AS101", 1000);
		VOInscripcionCompleta inscripcion2 = new VOInscripcionCompleta(2024, 222222222, "AS102", 1100);
		VOInscripcionCompleta inscripcion3 = new VOInscripcionCompleta(2024, 222222222, "AS103", 1200);
		VOInscripcionCompleta inscripcion4 = new VOInscripcionCompleta(2024, 222222222, "AS104", 1300);
		VOInscripcionCompleta inscripcion5 = new VOInscripcionCompleta(2024, 222222222, "AS105", 1400);
		VOInscripcionCompleta inscripcion6 = new VOInscripcionCompleta(2024, 222222222, "AS106", 1500);
		VOInscripcionCompleta inscripcion7 = new VOInscripcionCompleta(2024, 222222222, "AS107", 1600);
		VOInscripcionCompleta inscripcion8 = new VOInscripcionCompleta(2024, 222222222, "AS108", 1700);
		VOInscripcionCompleta inscripcion9 = new VOInscripcionCompleta(2024, 222222222, "AS109", 1800);
		VOInscripcionCompleta inscripcion10 = new VOInscripcionCompleta(2024, 222222222, "AS110", 1900);

		VONota nota1 = new VONota(222222222, 1, 10); // AS101 aprobada
		VONota nota4 = new VONota(222222222, 4, 10); // AS104 aprobada
		VONota nota7 = new VONota(222222222, 7, 10); // AS107 aprobada
		VONota nota2 = new VONota(222222222, 2, 5);
		VONota nota3 = new VONota(222222222, 3, 5);
		VONota nota5 = new VONota(222222222, 5, 3);
		VONota nota6 = new VONota(222222222, 6, 5);
		VONota nota8 = new VONota(222222222, 8, 5);
		VONota nota9 = new VONota(222222222, 9, 5);
		VONota nota10 = new VONota(222222222, 10, 5);

		VOInscripcionCompleta inscripcion11 = new VOInscripcionCompleta(2025, 222222222, "AS102", 1500);
		VOInscripcionCompleta inscripcion12 = new VOInscripcionCompleta(2025, 222222222, "AS103", 1500);
		VONota nota11 = new VONota(222222222, 11, 10); // AS102 aprobada
		VONota nota12 = new VONota(222222222, 12, 9); // AS103 aprobada

		VOInscripcionCompleta inscripcion13 = new VOInscripcionCompleta(2025, 222222222, "AS105", 1600);
		VOInscripcionCompleta inscripcion14 = new VOInscripcionCompleta(2025, 222222222, "AS106", 1650);
		VOInscripcionCompleta inscripcion15 = new VOInscripcionCompleta(2025, 222222222, "AS108", 1700);
		VONota nota13 = new VONota(222222222, 13, 10); // AS105 aprobada
		VONota nota14 = new VONota(222222222, 14, 9); // AS106 aprobada
		VONota nota15 = new VONota(222222222, 15, 5); // AS108 no aprobada

		VOInscripcionCompleta inscripcion16 = new VOInscripcionCompleta(2026, 222222222, "AS108", 2000);
		VOInscripcionCompleta inscripcion17 = new VOInscripcionCompleta(2026, 222222222, "AS109", 2000);
		VOInscripcionCompleta inscripcion18 = new VOInscripcionCompleta(2026, 222222222, "AS110", 2000);
		VONota nota16 = new VONota(222222222, 16, 12); // AS108 aprobada
		VONota nota17 = new VONota(222222222, 17, 12); // AS109 aprobada

		// falta solo aprobar la as110
		// VONota nota18 = new VONota(222222222, 18, 12); // AS109 aprobada
		// falta solo aprobar la as110

		try {
			fachada.inscripcionAsignatura(inscripcion1);
			fachada.inscripcionAsignatura(inscripcion2);
			fachada.inscripcionAsignatura(inscripcion3);
			fachada.inscripcionAsignatura(inscripcion4);
			fachada.inscripcionAsignatura(inscripcion5);
			fachada.inscripcionAsignatura(inscripcion6);
			fachada.inscripcionAsignatura(inscripcion7);
			fachada.inscripcionAsignatura(inscripcion8);
			fachada.inscripcionAsignatura(inscripcion9);
			fachada.inscripcionAsignatura(inscripcion10);

			fachada.registroNota(nota1);
			fachada.registroNota(nota2);
			fachada.registroNota(nota3);
			fachada.registroNota(nota4);
			fachada.registroNota(nota5);
			fachada.registroNota(nota6);
			fachada.registroNota(nota7);
			fachada.registroNota(nota8);
			fachada.registroNota(nota9);
			fachada.registroNota(nota10);

			fachada.inscripcionAsignatura(inscripcion11);
			fachada.inscripcionAsignatura(inscripcion12);
			fachada.inscripcionAsignatura(inscripcion13);
			fachada.inscripcionAsignatura(inscripcion14);
			fachada.inscripcionAsignatura(inscripcion15);

			fachada.registroNota(nota11);
			fachada.registroNota(nota12);
			fachada.registroNota(nota13);
			fachada.registroNota(nota14);
			fachada.registroNota(nota15);

			fachada.inscripcionAsignatura(inscripcion16);
			fachada.inscripcionAsignatura(inscripcion17);
			fachada.inscripcionAsignatura(inscripcion18);

			fachada.registroNota(nota16);
			fachada.registroNota(nota17);
			// fachada.registroNota(nota18);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SistemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void probarCasiEgresado() throws RemoteException {
		FachadaLogica fachada = FachadaLogica.getInstancia();
		// 234567890

		VOInscripcionCompleta inscripcion1 = new VOInscripcionCompleta(2024, 234567890, "AS101", 1000);
		VOInscripcionCompleta inscripcion2 = new VOInscripcionCompleta(2024, 234567890, "AS102", 1100);
		VOInscripcionCompleta inscripcion3 = new VOInscripcionCompleta(2024, 234567890, "AS103", 1200);
		VOInscripcionCompleta inscripcion4 = new VOInscripcionCompleta(2024, 234567890, "AS104", 1300);
		VOInscripcionCompleta inscripcion5 = new VOInscripcionCompleta(2024, 234567890, "AS105", 1400);
		VOInscripcionCompleta inscripcion6 = new VOInscripcionCompleta(2024, 234567890, "AS106", 1500);
		VOInscripcionCompleta inscripcion7 = new VOInscripcionCompleta(2024, 234567890, "AS107", 1600);
		VOInscripcionCompleta inscripcion8 = new VOInscripcionCompleta(2024, 234567890, "AS108", 1700);
		VOInscripcionCompleta inscripcion9 = new VOInscripcionCompleta(2024, 234567890, "AS109", 1800);
		VOInscripcionCompleta inscripcion10 = new VOInscripcionCompleta(2024, 234567890, "AS110", 1900);

		VONota nota1 = new VONota(234567890, 1, 10);
		VONota nota2 = new VONota(234567890, 2, 10);
		VONota nota3 = new VONota(234567890, 3, 10);
		VONota nota4 = new VONota(234567890, 4, 10);
		VONota nota5 = new VONota(234567890, 5, 10);
		VONota nota6 = new VONota(234567890, 6, 10);
		VONota nota7 = new VONota(234567890, 7, 10);
		VONota nota8 = new VONota(234567890, 8, 10);
		VONota nota9 = new VONota(234567890, 9, 10);

		try {
			fachada.inscripcionAsignatura(inscripcion1);
			fachada.inscripcionAsignatura(inscripcion2);
			fachada.inscripcionAsignatura(inscripcion3);
			fachada.inscripcionAsignatura(inscripcion4);
			fachada.inscripcionAsignatura(inscripcion5);
			fachada.inscripcionAsignatura(inscripcion6);
			fachada.inscripcionAsignatura(inscripcion7);
			fachada.inscripcionAsignatura(inscripcion8);
			fachada.inscripcionAsignatura(inscripcion9);
			fachada.inscripcionAsignatura(inscripcion10);

			fachada.registroNota(nota1);
			fachada.registroNota(nota2);
			fachada.registroNota(nota3);
			fachada.registroNota(nota4);
			fachada.registroNota(nota5);
			fachada.registroNota(nota6);
			fachada.registroNota(nota7);
			fachada.registroNota(nota8);
			fachada.registroNota(nota9);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SistemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void probarEgresadoSinReprobacion() throws RemoteException {
		FachadaLogica fachada = FachadaLogica.getInstancia();
		VOInscripcionCompleta inscripcion1 = new VOInscripcionCompleta(2024, 123456789, "AS101", 1000);
		VOInscripcionCompleta inscripcion2 = new VOInscripcionCompleta(2024, 123456789, "AS102", 1000);
		VOInscripcionCompleta inscripcion3 = new VOInscripcionCompleta(2024, 123456789, "AS103", 1000);
		VOInscripcionCompleta inscripcion4 = new VOInscripcionCompleta(2025, 123456789, "AS104", 1000);
		VOInscripcionCompleta inscripcion5 = new VOInscripcionCompleta(2025, 123456789, "AS105", 1000);
		VOInscripcionCompleta inscripcion6 = new VOInscripcionCompleta(2025, 123456789, "AS106", 1000);
		VOInscripcionCompleta inscripcion7 = new VOInscripcionCompleta(2025, 123456789, "AS107", 1000);
		VOInscripcionCompleta inscripcion8 = new VOInscripcionCompleta(2025, 123456789, "AS108", 1000);
		VOInscripcionCompleta inscripcion9 = new VOInscripcionCompleta(2025, 123456789, "AS109", 1000);
		VOInscripcionCompleta inscripcion10 = new VOInscripcionCompleta(2025, 123456789, "AS110", 1000);

		VONota nota1 = new VONota(123456789, 1, 10);
		VONota nota2 = new VONota(123456789, 2, 9);
		VONota nota3 = new VONota(123456789, 3, 6);
		VONota nota4 = new VONota(123456789, 4, 7);
		VONota nota5 = new VONota(123456789, 5, 10);
		VONota nota6 = new VONota(123456789, 6, 8);
		VONota nota7 = new VONota(123456789, 7, 6);
		VONota nota8 = new VONota(123456789, 8, 6);
		VONota nota9 = new VONota(123456789, 9, 7);
		VONota nota10 = new VONota(123456789, 10, 7);

		try {
			fachada.inscripcionAsignatura(inscripcion1);
			fachada.inscripcionAsignatura(inscripcion2);
			fachada.inscripcionAsignatura(inscripcion3);
			fachada.inscripcionAsignatura(inscripcion4);
			fachada.inscripcionAsignatura(inscripcion5);
			fachada.inscripcionAsignatura(inscripcion6);
			fachada.inscripcionAsignatura(inscripcion7);
			fachada.inscripcionAsignatura(inscripcion8);
			fachada.inscripcionAsignatura(inscripcion9);
			fachada.inscripcionAsignatura(inscripcion10);

			fachada.registroNota(nota1);
			fachada.registroNota(nota2);
			fachada.registroNota(nota3);
			fachada.registroNota(nota4);
			fachada.registroNota(nota5);
			fachada.registroNota(nota6);
			fachada.registroNota(nota7);
			fachada.registroNota(nota8);
			fachada.registroNota(nota9);
			fachada.registroNota(nota10);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SistemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void probarEgresadoConReprobacion() throws RemoteException {
		FachadaLogica fachada = FachadaLogica.getInstancia();
		VOInscripcionCompleta inscripcion1 = new VOInscripcionCompleta(2024, 111111111, "AS101", 1000);
		VOInscripcionCompleta inscripcion2 = new VOInscripcionCompleta(2024, 111111111, "AS102", 1000);
		VOInscripcionCompleta inscripcion3 = new VOInscripcionCompleta(2024, 111111111, "AS103", 1000);
		VOInscripcionCompleta inscripcion4 = new VOInscripcionCompleta(2025, 111111111, "AS101", 1000);
		VOInscripcionCompleta inscripcion5 = new VOInscripcionCompleta(2025, 111111111, "AS106", 1000);
		VOInscripcionCompleta inscripcion6 = new VOInscripcionCompleta(2025, 111111111, "AS107", 1000);
		VOInscripcionCompleta inscripcion7 = new VOInscripcionCompleta(2025, 111111111, "AS108", 1000);
		VOInscripcionCompleta inscripcion8 = new VOInscripcionCompleta(2026, 111111111, "AS102", 1000);
		VOInscripcionCompleta inscripcion9 = new VOInscripcionCompleta(2026, 111111111, "AS106", 1000);
		VOInscripcionCompleta inscripcion10 = new VOInscripcionCompleta(2026, 111111111, "AS107", 1000);
		VOInscripcionCompleta inscripcion11 = new VOInscripcionCompleta(2026, 111111111, "AS101", 1000);
		VOInscripcionCompleta inscripcion12 = new VOInscripcionCompleta(2027, 111111111, "AS104", 1000);
		VOInscripcionCompleta inscripcion13 = new VOInscripcionCompleta(2027, 111111111, "AS105", 1000);
		VOInscripcionCompleta inscripcion14 = new VOInscripcionCompleta(2027, 111111111, "AS108", 1000);
		VOInscripcionCompleta inscripcion15 = new VOInscripcionCompleta(2027, 111111111, "AS109", 1000);
		VOInscripcionCompleta inscripcion16 = new VOInscripcionCompleta(2028, 111111111, "AS109", 1000);
		VOInscripcionCompleta inscripcion17 = new VOInscripcionCompleta(2028, 111111111, "AS110", 1000);

		VONota nota1 = new VONota(111111111, 1, 4);
		VONota nota2 = new VONota(111111111, 2, 5);
		VONota nota3 = new VONota(111111111, 3, 8);
		VONota nota4 = new VONota(111111111, 4, 5);
		VONota nota5 = new VONota(111111111, 5, 3);
		VONota nota6 = new VONota(111111111, 6, 5);
		VONota nota7 = new VONota(111111111, 7, 4);
		VONota nota8 = new VONota(111111111, 8, 6);
		VONota nota9 = new VONota(111111111, 9, 7);
		VONota nota10 = new VONota(111111111, 10, 6);
		VONota nota11 = new VONota(111111111, 11, 9);
		VONota nota12 = new VONota(111111111, 12, 8);
		VONota nota13 = new VONota(111111111, 13, 9);
		VONota nota14 = new VONota(111111111, 14, 8);
		VONota nota15 = new VONota(111111111, 15, 5);
		VONota nota16 = new VONota(111111111, 16, 10);
		VONota nota17 = new VONota(111111111, 17, 10);

		try {
			fachada.inscripcionAsignatura(inscripcion1);
			fachada.inscripcionAsignatura(inscripcion2);
			fachada.inscripcionAsignatura(inscripcion3);
			fachada.registroNota(nota1);
			fachada.registroNota(nota2);
			fachada.registroNota(nota3);

			fachada.inscripcionAsignatura(inscripcion4);
			fachada.inscripcionAsignatura(inscripcion5);
			fachada.inscripcionAsignatura(inscripcion6);
			fachada.inscripcionAsignatura(inscripcion7);
			fachada.registroNota(nota4);
			fachada.registroNota(nota5);
			fachada.registroNota(nota6);
			fachada.registroNota(nota7);

			fachada.inscripcionAsignatura(inscripcion8);
			fachada.inscripcionAsignatura(inscripcion9);
			fachada.inscripcionAsignatura(inscripcion10);
			fachada.inscripcionAsignatura(inscripcion11);
			fachada.registroNota(nota8);
			fachada.registroNota(nota9);
			fachada.registroNota(nota10);
			fachada.registroNota(nota11);

			fachada.inscripcionAsignatura(inscripcion12);
			fachada.inscripcionAsignatura(inscripcion13);
			fachada.inscripcionAsignatura(inscripcion14);
			fachada.inscripcionAsignatura(inscripcion15);
			fachada.registroNota(nota12);
			fachada.registroNota(nota13);
			fachada.registroNota(nota14);
			fachada.registroNota(nota15);

			fachada.inscripcionAsignatura(inscripcion16);
			fachada.inscripcionAsignatura(inscripcion17);
			fachada.registroNota(nota16);
			fachada.registroNota(nota17);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SistemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void probarEscolaridadComun() throws RemoteException {
		FachadaLogica fachada = FachadaLogica.getInstancia();

		int cedulaPrueba = 345678901;
		/*
		 * Asignaturas AS101 AS102 AS103 AS104 AS105 AS106 AS107 AS108 AS109 AS110
		 */

		VOInscripcionCompleta inscripcion1 = new VOInscripcionCompleta(2024, cedulaPrueba, "AS101", 1000);
		VOInscripcionCompleta inscripcion2 = new VOInscripcionCompleta(2024, cedulaPrueba, "AS102", 1100);//SinNota
		VOInscripcionCompleta inscripcion3 = new VOInscripcionCompleta(2024, cedulaPrueba, "AS103", 1200);//SinNota
		VOInscripcionCompleta inscripcion4 = new VOInscripcionCompleta(2024, cedulaPrueba, "AS104", 1300);
		VOInscripcionCompleta inscripcion5 = new VOInscripcionCompleta(2024, cedulaPrueba, "AS105", 1400);//SinNota
		VOInscripcionCompleta inscripcion6 = new VOInscripcionCompleta(2024, cedulaPrueba, "AS106", 1500);
		VOInscripcionCompleta inscripcion7 = new VOInscripcionCompleta(2024, cedulaPrueba, "AS107", 1600);
		VOInscripcionCompleta inscripcion8 = new VOInscripcionCompleta(2024, cedulaPrueba, "AS108", 1700);
		VOInscripcionCompleta inscripcion9 = new VOInscripcionCompleta(2024, cedulaPrueba, "AS109", 1800);
		VOInscripcionCompleta inscripcion10 = new VOInscripcionCompleta(2024, cedulaPrueba, "AS110", 1900);

		VONota nota1 = new VONota(cedulaPrueba, 1, 10); // AS101 aprobada
		VONota nota4 = new VONota(cedulaPrueba, 4, 10); // AS104 aprobada
		VONota nota7 = new VONota(cedulaPrueba, 7, 10); // AS107 aprobada
		VONota nota6 = new VONota(cedulaPrueba, 6, 5);  // AS106
		VONota nota8 = new VONota(cedulaPrueba, 8, 5);  // AS108
		VONota nota9 = new VONota(cedulaPrueba, 9, 5);  // AS109
		VONota nota10 = new VONota(cedulaPrueba, 10, 5);// AS110

		VOInscripcionCompleta inscripcion11 = new VOInscripcionCompleta(2025, cedulaPrueba, "AS106", 1650);
		VOInscripcionCompleta inscripcion12 = new VOInscripcionCompleta(2025, cedulaPrueba, "AS108", 1700);
		
		VONota nota11 = new VONota(cedulaPrueba, 11, 9); // AS106 aprobada
		VONota nota12 = new VONota(cedulaPrueba, 12, 5); // AS108 no aprobada

		VOInscripcionCompleta inscripcion13 = new VOInscripcionCompleta(2026, cedulaPrueba, "AS108", 2000);
		VOInscripcionCompleta inscripcion14 = new VOInscripcionCompleta(2026, cedulaPrueba, "AS109", 2000);
		VOInscripcionCompleta inscripcion15 = new VOInscripcionCompleta(2026, cedulaPrueba, "AS110", 2000);
		VONota nota13 = new VONota(cedulaPrueba, 13, 12); // AS108 aprobada
		VONota nota14 = new VONota(cedulaPrueba, 14, 12); // AS109 aprobada

		try {
			fachada.inscripcionAsignatura(inscripcion1);
			fachada.inscripcionAsignatura(inscripcion2);
			fachada.inscripcionAsignatura(inscripcion3);
			fachada.inscripcionAsignatura(inscripcion4);
			fachada.inscripcionAsignatura(inscripcion5);
			fachada.inscripcionAsignatura(inscripcion6);
			fachada.inscripcionAsignatura(inscripcion7);
			fachada.inscripcionAsignatura(inscripcion8);
			fachada.inscripcionAsignatura(inscripcion9);
			fachada.inscripcionAsignatura(inscripcion10);

			fachada.registroNota(nota1);
			fachada.registroNota(nota4);
			fachada.registroNota(nota6);
			fachada.registroNota(nota7);
			fachada.registroNota(nota8);
			fachada.registroNota(nota9);
			fachada.registroNota(nota10);

			fachada.inscripcionAsignatura(inscripcion11);
			fachada.registroNota(nota11);
			fachada.inscripcionAsignatura(inscripcion12);
			fachada.registroNota(nota12);
			fachada.inscripcionAsignatura(inscripcion13);
			fachada.registroNota(nota13);
			fachada.inscripcionAsignatura(inscripcion14);
			fachada.registroNota(nota14);
			fachada.inscripcionAsignatura(inscripcion15);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SistemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void probarEscolaridadBecado() throws RemoteException {
		FachadaLogica fachada = FachadaLogica.getInstancia();

		int cedulaPrueba = 333333333;
		/*
		 * Asignaturas AS101 AS102 AS103 AS104 AS105 AS106 AS107 AS108 AS109 AS110
		 */

		VOInscripcionCompleta inscripcion1 = new VOInscripcionCompleta(2024, cedulaPrueba, "AS101", 1000);
		VOInscripcionCompleta inscripcion2 = new VOInscripcionCompleta(2024, cedulaPrueba, "AS102", 1100);//SinNota
		VOInscripcionCompleta inscripcion3 = new VOInscripcionCompleta(2024, cedulaPrueba, "AS103", 1200);//SinNota
		VOInscripcionCompleta inscripcion4 = new VOInscripcionCompleta(2024, cedulaPrueba, "AS105", 1400);//SinNota
		VOInscripcionCompleta inscripcion5 = new VOInscripcionCompleta(2024, cedulaPrueba, "AS106", 1500);
		VOInscripcionCompleta inscripcion6 = new VOInscripcionCompleta(2024, cedulaPrueba, "AS107", 1600);
		VOInscripcionCompleta inscripcion7 = new VOInscripcionCompleta(2024, cedulaPrueba, "AS108", 1700);
		VOInscripcionCompleta inscripcion8 = new VOInscripcionCompleta(2024, cedulaPrueba, "AS110", 1900);

		VONota nota1 = new VONota(cedulaPrueba, 1, 9); // AS101 aprobada
		//VONota nota2
		//VONota nota3
		//VONota nota4
		VONota nota5 = new VONota(cedulaPrueba, 5, 11); // AS104 aprobada
		VONota nota6 = new VONota(cedulaPrueba, 6, 12); // AS107 aprobada
		VONota nota7 = new VONota(cedulaPrueba, 7, 1);// AS110
		VONota nota8 = new VONota(cedulaPrueba, 8, 3);  // AS106

		VOInscripcionCompleta inscripcion9 = new VOInscripcionCompleta(2025, cedulaPrueba, "AS108", 1750);
		VOInscripcionCompleta inscripcion10 = new VOInscripcionCompleta(2025, cedulaPrueba, "AS110", 1950);
		
		VONota nota11 = new VONota(cedulaPrueba, 9, 12); // AS106 aprobada
		VONota nota12 = new VONota(cedulaPrueba, 10, 2); // AS108 no aprobada

		try {
			fachada.inscripcionAsignatura(inscripcion1);
			fachada.inscripcionAsignatura(inscripcion2);
			fachada.inscripcionAsignatura(inscripcion3);
			fachada.inscripcionAsignatura(inscripcion4);
			fachada.inscripcionAsignatura(inscripcion5);
			fachada.inscripcionAsignatura(inscripcion6);
			fachada.inscripcionAsignatura(inscripcion7);
			fachada.inscripcionAsignatura(inscripcion8);

			fachada.registroNota(nota1);
			fachada.registroNota(nota6);
			fachada.registroNota(nota7);
			fachada.registroNota(nota8);


			fachada.inscripcionAsignatura(inscripcion9);
			fachada.inscripcionAsignatura(inscripcion10);

			fachada.registroNota(nota11);
			fachada.registroNota(nota12);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SistemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

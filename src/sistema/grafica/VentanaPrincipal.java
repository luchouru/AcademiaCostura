package sistema.grafica;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import sistema.grafica.controladores.ControladorRespaldo;
import sistema.logica.excepciones.SistemaException;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanelAltaAsignaturas altaAsignaturasPanel;
	private JPanelListarDetalladoCedula panelListarDetalladoCedula;
	private JPanelObtenerMontoInscripciones panelObtenerMontoInscripciones;
	private JPanelListarEscolaridad panelListarEscolaridad;
	private JPanelAltaAlumno panelAltaAlumno;
	private JPanelInscripcionAsignatura panelInscripcionAsignatura;
	private JPanelRegistrarCalificacion panelRegistrarCalificacion;
	private JPanelListarEgresados panelListarEgresados;
	private JPanelListarPorApellido panelListarPorApellido;
	private JPanelListarAsignaturas panelListarAsignaturas;
	private ControladorRespaldo controlador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {

		ImageIcon icon = new ImageIcon("img/icono.png");
		setIconImage(icon.getImage());

		setContentPane(new JLabel(new ImageIcon("img/fondo.png")));
		pack();

		setTitle("Academia fashionista do norte - Programa gestion");
		controlador = new ControladorRespaldo();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 520);
		setBackground(new Color(196, 161, 131));

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnAltas = new JMenu("Altas");
		menuBar.add(mnAltas);

		JMenuItem mntmNewMenuItem = new JMenuItem("Ingresar una asignatura");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaAsignaturasPanel = new JPanelAltaAsignaturas();
				setContentPane(altaAsignaturasPanel);
				invalidate();
				validate();
			}
		});
		mnAltas.add(mntmNewMenuItem);

		JMenuItem JMenuItemIngresarAlumno = new JMenuItem("Ingresar un alumno");
		JMenuItemIngresarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAltaAlumno = new JPanelAltaAlumno();
				setContentPane(panelAltaAlumno);
				invalidate();
				validate();
			}
		});
		mnAltas.add(JMenuItemIngresarAlumno);

		JMenu mnReportesGenerales = new JMenu("Reportes generales");
		menuBar.add(mnReportesGenerales);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Listar todos los egresados");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelListarEgresados = new JPanelListarEgresados();
				setContentPane(panelListarEgresados);
				invalidate();
				validate();
			}
		});
		mnReportesGenerales.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Listar alumnos por apellido");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelListarPorApellido = new JPanelListarPorApellido();
				setContentPane(panelListarPorApellido);
				invalidate();
				validate();
			}
		});
		mnReportesGenerales.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Listar todas las asignaturas");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelListarAsignaturas = new JPanelListarAsignaturas();
				setContentPane(panelListarAsignaturas);
				invalidate();
				validate();
			}
		});
		mnReportesGenerales.add(mntmNewMenuItem_4);

		JMenu mnConsultas = new JMenu("Consultas");
		menuBar.add(mnConsultas);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Listar alumno detallado mediante cédula");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelListarDetalladoCedula = new JPanelListarDetalladoCedula();
				setContentPane(panelListarDetalladoCedula);
				invalidate();
				validate();
			}
		});
		mnConsultas.add(mntmNewMenuItem_7);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Obtener el monto de todas las inscripciones de un alumno");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelObtenerMontoInscripciones = new JPanelObtenerMontoInscripciones();
				setContentPane(panelObtenerMontoInscripciones);
				invalidate();
				validate();
			}
		});
		mnConsultas.add(mntmNewMenuItem_5);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Listar la escolaridad de un alumno");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelListarEscolaridad = new JPanelListarEscolaridad();
				setContentPane(panelListarEscolaridad);
				invalidate();
				validate();
			}
		});
		mnConsultas.add(mntmNewMenuItem_6);

		JMenu mnGestionAlumnos = new JMenu("Gestión de alumnos");
		menuBar.add(mnGestionAlumnos);

		JMenuItem menuInscripcionAsignatura = new JMenuItem("Ingresar una inscripción a una asignatura");
		menuInscripcionAsignatura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelInscripcionAsignatura = new JPanelInscripcionAsignatura();
				setContentPane(panelInscripcionAsignatura);
				invalidate();
				validate();
			}
		});
		mnGestionAlumnos.add(menuInscripcionAsignatura);

		JMenuItem menuItemRegistrarCalificacion = new JMenuItem("Registrar una calificación final");
		menuItemRegistrarCalificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRegistrarCalificacion = new JPanelRegistrarCalificacion();
				setContentPane(panelRegistrarCalificacion);
				invalidate();
				validate();
			}
		});
		mnGestionAlumnos.add(menuItemRegistrarCalificacion);

		JMenu mnDatos = new JMenu("Datos");
		menuBar.add(mnDatos);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Respaldar");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					controlador.respaldar();
					JOptionPane.showMessageDialog(null, "Respaldado con exito.", "Respaldo",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (RemoteException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Atencion!", JOptionPane.ERROR_MESSAGE);
				} catch (SistemaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Atencion!", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		mnDatos.add(mntmNewMenuItem_1);

	}

}

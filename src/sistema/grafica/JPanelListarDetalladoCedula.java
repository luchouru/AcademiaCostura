package sistema.grafica;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sistema.grafica.controladores.ControladorVentanaListarDetalladoCedula;
import sistema.logica.VO.VOAlumno;
import sistema.logica.VO.VOAlumnoBecado;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.rmi.RemoteException;
import sistema.logica.excepciones.SistemaException;


public class JPanelListarDetalladoCedula extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldCedula;
	private JTable table;
	private ControladorVentanaListarDetalladoCedula controlador;

	public JPanelListarDetalladoCedula() {
		controlador = new ControladorVentanaListarDetalladoCedula();
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane();	
		JLabel labelTitulo = new JLabel("Listado detallado de un alumno");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		JLabel labelCedulaAlumno = new JLabel("Cédula de alumno:");

		textFieldCedula = new JTextField();
		textFieldCedula.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setEnabled(false);
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cedulaString = textFieldCedula.getText();
				int cedula = Integer.parseInt(cedulaString);
				String alumnoTipo = "Comun";
				try {
					VOAlumno alumno = controlador.listarDetalladoCedula(cedula);
	
					if (alumno == null) {
						JOptionPane.showMessageDialog(null, "No se encontró el alumno para el criterio de busqueda.",
								"Sin resultados", JOptionPane.INFORMATION_MESSAGE);
					} else {
						
						if (alumno instanceof VOAlumnoBecado) {
							alumnoTipo = "Becado";
						} 
						
						String header[] = {"Cedula", "Nombre", "Apellido", "Domicilio", "Telefono", "Cantidad de asig. aprobadas", "Tipo de alumno"};;
						DefaultTableModel modeloTable = new DefaultTableModel();
						for (String head : header) {
							modeloTable.addColumn(head);
						}
						
						Object O[] = null;
						modeloTable.addRow(O);
						modeloTable.setValueAt(alumno.getCedula(), 0, 0);
						modeloTable.setValueAt(alumno.getNombre(), 0, 1);
						modeloTable.setValueAt(alumno.getApellido(), 0, 2);
						modeloTable.setValueAt(alumno.getDomicilio(), 0, 3);
						modeloTable.setValueAt(alumno.getTelefono(), 0, 4);
						modeloTable.setValueAt(alumno.getCantMateriasAprobadas(), 0, 5);
						modeloTable.setValueAt(alumnoTipo, 0, 6);	
	
						if(alumnoTipo == "Becado") {
							modeloTable.addColumn("Descuento");
							modeloTable.addColumn("Razon");
							modeloTable.setValueAt(((VOAlumnoBecado) alumno).getDescuento(), 0, 7);	
							modeloTable.setValueAt(((VOAlumnoBecado) alumno).getRazon(), 0, 8);	
						}
						table.setModel(modeloTable);
						table.setDefaultEditor(Object.class, null);/**/
						scrollPane.setViewportView(table);
						
				}					
				} catch (RemoteException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Atencion!", JOptionPane.ERROR_MESSAGE);
				} catch (SistemaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Atencion!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		textFieldCedula.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c)) {
					e.consume();
				}
			}
			public void keyReleased(KeyEvent e) {
				if (textFieldCedula.getText().isEmpty()) {
					btnBuscar.setEnabled(false);
				} else {
					btnBuscar.setEnabled(true);
				}
			}
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnBuscar.doClick();
				}
			}
		});		

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(labelCedulaAlumno)
							.addGap(10)
							.addComponent(textFieldCedula, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(btnBuscar))
						.addComponent(labelTitulo, GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(labelTitulo, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldCedula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelCedulaAlumno)
						.addComponent(btnBuscar))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);		
	}
}

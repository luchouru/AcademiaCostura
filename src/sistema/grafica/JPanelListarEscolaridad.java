package sistema.grafica;

import java.awt.Font;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sistema.grafica.controladores.ControladorVentanaListarEscolaridad;
import sistema.logica.VO.VOListadoEscolaridad;
import sistema.logica.VO.VOListadoEscolaridadCompleta;
import sistema.logica.constantes.Modalidad;
import sistema.logica.excepciones.SistemaException;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class JPanelListarEscolaridad extends JPanel {

	private static final long serialVersionUID = 1L;
	private ControladorVentanaListarEscolaridad controlador;
	private JTextField textFieldCedula;
	private JTable table;

	public JPanelListarEscolaridad() {
		controlador = new ControladorVentanaListarEscolaridad();
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane();		
		JCheckBox chckbxListadoCompleto = new JCheckBox("Listado completo");
		JLabel labelCedulaAlumno = new JLabel("Cédula de alumno:");
		JLabel labelTitulo = new JLabel("Consulta de escolaridad");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		textFieldCedula = new JTextField();
		textFieldCedula.setColumns(10);
	
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setEnabled(false);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Modalidad modalidad = chckbxListadoCompleto.isSelected() ? Modalidad.COMPLETO : Modalidad.PARCIAL;

				try {
					
					String cedulaString = textFieldCedula.getText();
					int cedula = Integer.parseInt(cedulaString);
					ArrayList<VOListadoEscolaridad> res = controlador.listarEscolaridad(cedula, modalidad);
					
					if (res.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No se encontraron inscripciones para este alumno con esta modalidad de listado.", "Sin resultados",
								JOptionPane.INFORMATION_MESSAGE);
					} else {

						String header[] = {"Numero Inscripcion", "Asignatura", "Año lectivo", "Calificacion"};
						DefaultTableModel modeloTable = new DefaultTableModel();
						
						for (String head : header) {
							modeloTable.addColumn(head);
						}
						
						if (modalidad == Modalidad.COMPLETO) {
							modeloTable.addColumn("Monto");
						}
						
						Object O[] = null;
						for (int i = 0; i < res.size(); i++) {
							modeloTable.addRow(O);
							VOListadoEscolaridad objEscolaridad = res.get(i);
							modeloTable.setValueAt(objEscolaridad.getNumero(), i, 0);
							modeloTable.setValueAt(objEscolaridad.getAsignatura(), i, 1);
							modeloTable.setValueAt(objEscolaridad.getAnio(), i, 2);
							modeloTable.setValueAt(objEscolaridad.getCalificacion(), i, 3);
							if (modalidad == Modalidad.COMPLETO) {
								modeloTable.setValueAt(((VOListadoEscolaridadCompleta) (objEscolaridad)).getMontoBase(), i, 4);
							}
						}
						table.setModel(modeloTable);
						table.setDefaultEditor(Object.class, null);
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
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnBuscar.doClick();
				}
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(labelTitulo, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(labelCedulaAlumno, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(textFieldCedula, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
								.addComponent(chckbxListadoCompleto, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(3)
					.addComponent(labelTitulo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelCedulaAlumno, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldCedula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBuscar)
						.addComponent(chckbxListadoCompleto))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
					.addContainerGap())
		);

		setLayout(groupLayout);
		
		btnBuscar.setEnabled(false);

	}
}

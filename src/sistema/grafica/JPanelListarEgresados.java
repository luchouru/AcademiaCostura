package sistema.grafica;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

import sistema.grafica.controladores.ControladorVentanaListadoEgresados;
import sistema.logica.VO.VOListadoEgresado;
import sistema.logica.VO.VOListadoEgresadoCompleta;
import sistema.logica.constantes.Modalidad;
import sistema.logica.excepciones.SistemaException;
import javax.swing.JScrollPane;

public class JPanelListarEgresados extends JPanel {

	private static final long serialVersionUID = 1L;
	private ControladorVentanaListadoEgresados controlador;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public JPanelListarEgresados() {
		controlador = new ControladorVentanaListadoEgresados();
		JLabel lblListadoDeEgresados = new JLabel("Listado de egresados");
		lblListadoDeEgresados.setHorizontalAlignment(SwingConstants.CENTER);
		lblListadoDeEgresados.setFont(new Font("Tahoma", Font.BOLD, 15));

		JButton btnNewButton = new JButton("Listar");

		JCheckBox chckbxListadoCompleto = new JCheckBox("Listado completo");

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		table = new JTable();

		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(10)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblListadoDeEgresados, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(chckbxListadoCompleto, GroupLayout.PREFERRED_SIZE, 135,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
										.addComponent(btnNewButton).addContainerGap())))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE).addGap(10)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(lblListadoDeEgresados).addGap(13)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(chckbxListadoCompleto).addComponent(btnNewButton))
						.addGap(18).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
						.addContainerGap()));

		setLayout(groupLayout);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Modalidad modalidad = chckbxListadoCompleto.isSelected() ? Modalidad.COMPLETO : Modalidad.PARCIAL;

				try {
					ArrayList<VOListadoEgresado> res = controlador.listarEgresados(modalidad);
					if (res.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No se encontraron alumnos egresados.", "Sin resultados",
								JOptionPane.INFORMATION_MESSAGE);
					} else {

						ArrayList<String> header = new ArrayList<String>(Arrays.asList("Cedula", "Nombre", "Apellido"));

						if (modalidad == Modalidad.COMPLETO) {
							header.add("Promedio total");
							header.add("Promedio aprobaciones");
						}

						DefaultTableModel modeloTable = new DefaultTableModel();

						for (String head : header) {
							modeloTable.addColumn(head);
						}

						Object O[] = null;
						for (int i = 0; i < res.size(); i++) {
							modeloTable.addRow(O);
							VOListadoEgresado alumno = res.get(i);
							modeloTable.setValueAt(alumno.getCedula(), i, 0);
							modeloTable.setValueAt(alumno.getNombre(), i, 1);
							modeloTable.setValueAt(alumno.getApellido(), i, 2);
							if (modalidad == Modalidad.COMPLETO) {
								modeloTable.setValueAt(((VOListadoEgresadoCompleta) (alumno)).getPromedioTotal(), i, 3);
								modeloTable.setValueAt(((VOListadoEgresadoCompleta) (alumno)).getPromedioAprobaciones(),
										i, 4);

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

	}
}

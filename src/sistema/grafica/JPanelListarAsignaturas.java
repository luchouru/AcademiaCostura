package sistema.grafica;

import java.awt.Font;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import sistema.grafica.controladores.ControladorVentanaListadoAsignaturas;
import sistema.logica.VO.VOAsignatura;
import sistema.logica.excepciones.SistemaException;
import javax.swing.table.DefaultTableModel;

public class JPanelListarAsignaturas extends JPanel {

	private static final long serialVersionUID = 1L;
	private ControladorVentanaListadoAsignaturas controlador;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public JPanelListarAsignaturas() {
		controlador = new ControladorVentanaListadoAsignaturas();
		JLabel lblListadoDeAsignaturas = new JLabel("Listado de asignaturas");
		lblListadoDeAsignaturas.setHorizontalAlignment(SwingConstants.CENTER);
		lblListadoDeAsignaturas.setFont(new Font("Tahoma", Font.BOLD, 15));

		JScrollPane scrollPane_1 = new JScrollPane();

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
								.addComponent(lblListadoDeAsignaturas, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(5).addComponent(lblListadoDeAsignaturas).addGap(18)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE).addContainerGap()));

		table = new JTable();
		scrollPane_1.setViewportView(table);
		setLayout(groupLayout);

		try {
			ArrayList<VOAsignatura> res = controlador.listarAsignaturas();
			if (res.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No existen asignaturas en el sitema.", "Sin resultados",
						JOptionPane.INFORMATION_MESSAGE);
			} else {

				String header[] = { "Código", "Nombre", "Descripción" };

				DefaultTableModel modeloTable = new DefaultTableModel();

				for (String head : header) {
					modeloTable.addColumn(head);
				}

				Object O[] = null;
				for (int i = 0; i < res.size(); i++) {
					modeloTable.addRow(O);
					VOAsignatura asignatura = res.get(i);
					modeloTable.setValueAt(asignatura.getCodigo(), i, 0);
					modeloTable.setValueAt(asignatura.getNombre(), i, 1);
					modeloTable.setValueAt(asignatura.getDescripcion(), i, 2);
				}

				table.setModel(modeloTable);
				table.setDefaultEditor(Object.class, null);
				scrollPane_1.setViewportView(table);

			}
		} catch (RemoteException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Atencion!", JOptionPane.ERROR_MESSAGE);
		} catch (SistemaException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Atencion!", JOptionPane.ERROR_MESSAGE);

		}

	}
}

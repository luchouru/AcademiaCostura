package sistema.grafica;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

import sistema.grafica.controladores.ControladorVentanaListadoPorApellido;
import sistema.logica.VO.VOListadoApellido;
import sistema.logica.excepciones.SistemaException;
import javax.swing.JScrollPane;

public class JPanelListarPorApellido extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private ControladorVentanaListadoPorApellido controlador;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public JPanelListarPorApellido() {
		controlador = new ControladorVentanaListadoPorApellido();
		JLabel lblListadoDetalladoDe = new JLabel("Listado de alumnos por apellido");
		lblListadoDetalladoDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblListadoDetalladoDe.setFont(new Font("Tahoma", Font.BOLD, 15));

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setColumns(8);
		table = new JTable();

		JScrollPane scrollPane = new JScrollPane();
		JLabel lblNewLabel = new JLabel("Apellidos conteniendo:");

		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ArrayList<VOListadoApellido> res = controlador.listarPorApellido(textField.getText());

					if (res.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No se encontraron alumnos para el criterio de busqueda.",
								"Sin resultados", JOptionPane.INFORMATION_MESSAGE);
					} else {
						String header[] = { "Cedula", "Nombre", "Apellido", "Tipo de alumno" };

						DefaultTableModel modeloTable = new DefaultTableModel();

						for (String head : header) {
							modeloTable.addColumn(head);
						}

						Object O[] = null;
						for (int i = 0; i < res.size(); i++) {
							modeloTable.addRow(O);
							VOListadoApellido alumno = res.get(i);
							modeloTable.setValueAt(alumno.getCedula(), i, 0);
							modeloTable.setValueAt(alumno.getNombre(), i, 1);
							modeloTable.setValueAt(alumno.getApellido(), i, 2);
							modeloTable.setValueAt(alumno.getTipo(), i, 3);
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
		btnNewButton.setEnabled(false);

		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textField.getText().isEmpty()) {
					btnNewButton.setEnabled(false);
				} else {
					btnNewButton.setEnabled(true);
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				if (!Character.isLetter(e.getKeyChar())) {
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnNewButton.doClick();
				}

			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE).addGap(18)
								.addComponent(btnNewButton))
						.addComponent(lblListadoDetalladoDe, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430,
								Short.MAX_VALUE))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(14).addComponent(lblListadoDetalladoDe).addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
								.addComponent(btnNewButton).addComponent(textField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
						.addContainerGap()));

		setLayout(groupLayout);

	}
}

package sistema.grafica;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import sistema.grafica.controladores.ControladorVentanaInscripcion;
import sistema.logica.VO.VOAsignatura;
import sistema.logica.excepciones.SistemaException;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;

public class JPanelInscripcionAsignatura extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtCedula;
	private JTextField txtAnio;
	private JTextField txtMonto;
	private JComboBox<String> comboBox;

	private ControladorVentanaInscripcion controlador;

	public JPanelInscripcionAsignatura() {
		controlador = new ControladorVentanaInscripcion();
		JLabel labelTitulo = new JLabel("Inscripcion Asignatura");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		JLabel labelCedula = new JLabel("Cédula:");
		labelCedula.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel labelCodAsignatura = new JLabel("Codigo Asignatura:");
		labelCodAsignatura.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel labelAnio = new JLabel("Año:");
		labelAnio.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel labelMonto = new JLabel("Monto:");
		labelMonto.setHorizontalAlignment(SwingConstants.CENTER);

		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtAnio = new JTextField();
		txtAnio.setColumns(10);
		txtMonto = new JTextField();
		txtMonto.setColumns(10);

		JButton btnIngresar = new JButton("Ingresar");
		JButton btnLimpiar = new JButton("Limpiar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String anioString = txtAnio.getText();
				String cedulaString = txtCedula.getText();
				String asignatura = comboBox.getSelectedItem().toString().split("-")[0];
				String montoString = txtMonto.getText();

				if (anioString.isBlank() || cedulaString.isBlank() || asignatura.isBlank() || montoString.isBlank()) {
					JOptionPane.showMessageDialog(null, "Uno o mas campos estan en blanco.", "Error",
							JOptionPane.WARNING_MESSAGE);
				} else {
					int anio = Integer.parseInt(anioString);
					int cedula = Integer.parseInt(cedulaString);
					float monto = Float.parseFloat(montoString);
					try {
						controlador.ingresarInscripcion(anio, cedula, asignatura, monto);
						JOptionPane.showMessageDialog(null, "Inscripcion realizada correctamente.", "Exito!",
								JOptionPane.INFORMATION_MESSAGE);
						txtCedula.setText("");
						txtAnio.setText("");
						txtMonto.setText("");
					} catch (RemoteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Atencion!", JOptionPane.ERROR_MESSAGE);
					} catch (SistemaException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Atencion!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCedula.setText("");
				txtAnio.setText("");
				txtMonto.setText("");
			}
		});

		txtMonto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c != '.') {
					e.consume();
				}
				if (Character.isLowerCase(c)) {
                    e.setKeyChar(Character.toUpperCase(c));
                }
			}
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnIngresar.doClick();
				}
			}

		});
		txtAnio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnIngresar.doClick();
				}
			}
		});
		txtCedula.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnIngresar.doClick();
				}
			}
		});

		try {
			ArrayList<VOAsignatura> asignaturas = controlador.obtenerAsignaturas();
			Vector<String> comboBoxItems = new Vector<String>();
			for (VOAsignatura asig : asignaturas) {
				comboBoxItems.add(asig.getCodigo() + "-" + asig.getNombre());
			}

			final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(comboBoxItems);

			comboBox = new JComboBox<String>(model);

		} catch (RemoteException | SistemaException e1) {
			// TODO Auto-generated catch block
			comboBox = new JComboBox<String>();
			e1.printStackTrace();
		}

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(labelTitulo, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(labelMonto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(labelAnio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(labelCodAsignatura, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(labelCedula, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtAnio, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
										.addComponent(txtMonto, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
										.addComponent(txtCedula, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 324,
												Short.MAX_VALUE)
										.addComponent(comboBox, 0, 324, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup().addComponent(btnLimpiar).addGap(18)
								.addComponent(btnIngresar)))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap().addComponent(labelTitulo).addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCedula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(labelCedula))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout
						.createParallelGroup(Alignment.BASELINE).addComponent(labelCodAsignatura).addComponent(comboBox,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(labelAnio).addComponent(
						txtAnio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(labelMonto).addComponent(
						txtMonto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE).addGroup(groupLayout
						.createParallelGroup(Alignment.BASELINE).addComponent(btnIngresar).addComponent(btnLimpiar))
				.addContainerGap()));
		setLayout(groupLayout);
	}
}

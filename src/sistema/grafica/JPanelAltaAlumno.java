package sistema.grafica;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

import sistema.grafica.controladores.ControladorVentanaAltaAlumno;
import sistema.logica.excepciones.SistemaException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class JPanelAltaAlumno extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private ControladorVentanaAltaAlumno controlador;
	private JTextField txtDescuento;
	private JTextField txtRazon;
	private boolean esBecado = false;
	private boolean camposValidados = false;

	public JPanelAltaAlumno() {
		controlador = new ControladorVentanaAltaAlumno();
		JLabel lblNewLabel = new JLabel("Alta Alumno");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		JLabel labelCedula = new JLabel("Cédula:");
		labelCedula.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel labelApellido = new JLabel("Apellido:");
		labelApellido.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel labelDireccion = new JLabel("Dirección:");
		labelDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel labelTelefono = new JLabel("Teléfono:");
		labelTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel labelDescuento = new JLabel("Descuento:");
		labelDescuento.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel labelRazon = new JLabel("Razón:");
		labelRazon.setHorizontalAlignment(SwingConstants.CENTER);

		JButton btnIngresar = new JButton("Ingresar");
		JButton btnLimpiar = new JButton("Limpiar");
		JCheckBox chckbxBecado = new JCheckBox("Becado");

		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtDescuento = new JTextField();
		txtDescuento.setColumns(10);
		txtDescuento.setEnabled(false);
		txtRazon = new JTextField();
		txtRazon.setColumns(10);
		txtRazon.setEnabled(false);

		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cedulaString = txtCedula.getText();
				String nombre = txtNombre.getText();
				String apellido = txtApellido.getText();
				String direccion = txtDireccion.getText();
				String telefonoString = txtTelefono.getText();
				String razon = txtRazon.getText();
				String descuentoString = txtDescuento.getText();

				if (!esBecado) {
					if (!cedulaString.isBlank() && !nombre.isBlank() && !apellido.isBlank() && !direccion.isBlank()
							&& !telefonoString.isBlank())
						camposValidados = true;
				} else {
					if (!cedulaString.isBlank() && !nombre.isBlank() && !apellido.isBlank() && !direccion.isBlank()
							&& !telefonoString.isBlank() && !razon.isBlank() && !descuentoString.isBlank())
						camposValidados = true;
				}

				if (!camposValidados) {
					JOptionPane.showMessageDialog(null, "Uno o mas campos estan en blanco.", "Cuidado!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					int cedula = Integer.parseInt(cedulaString);
					int telefono = Integer.parseInt(telefonoString);

					try {
						if (!esBecado)
							controlador.ingresarAlumno(cedula, nombre, apellido, direccion, telefono);
						else {
							int descuento = Integer.parseInt(descuentoString);
							controlador.ingresarAlumnoBecado(cedula, nombre, apellido, direccion, telefono, descuento,
									razon);
						}

						JOptionPane.showMessageDialog(null, "Alumno ingresado correctamente.", "Exito!",
								JOptionPane.INFORMATION_MESSAGE);
						txtCedula.setText("");
						txtNombre.setText("");
						txtApellido.setText("");
						txtDireccion.setText("");
						txtTelefono.setText("");
						txtDescuento.setText("");
						txtRazon.setText("");
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
				txtNombre.setText("");
				txtApellido.setText("");
				txtDireccion.setText("");
				txtTelefono.setText("");
			}
		});

		chckbxBecado.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					txtDescuento.setEnabled(true);
					txtRazon.setEnabled(true);
					esBecado = true;
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					txtDescuento.setEnabled(false);
					txtRazon.setEnabled(false);
					txtDescuento.setText("");
					txtRazon.setText("");
					esBecado = false;
				}
			}
		});

		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
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
		txtApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnIngresar.doClick();
				}
			}
		});
		txtDireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnIngresar.doClick();
				}
			}
		});
		txtTelefono.addKeyListener(new KeyAdapter() {
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
		txtDescuento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (Integer.parseInt(txtDescuento.getText()) > 100) {
					txtDescuento.setText("100");
				}

				if (Integer.parseInt(txtDescuento.getText()) < 0) {
					txtDescuento.setText("0");
				}

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnIngresar.doClick();
				}

			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING,
								groupLayout.createSequentialGroup().addComponent(btnLimpiar).addGap(18)
										.addComponent(btnIngresar))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(labelTelefono, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(labelDireccion, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(labelApellido, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(labelNombre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(labelCedula, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtTelefono, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
										.addComponent(txtDireccion, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
										.addComponent(txtApellido, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
										.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
										.addComponent(txtCedula, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 348,
												Short.MAX_VALUE)))
						.addComponent(chckbxBecado, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(labelRazon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(labelDescuento, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtRazon, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
										.addComponent(txtDescuento, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 346,
												Short.MAX_VALUE))))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap().addComponent(lblNewLabel).addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(labelCedula).addComponent(
						txtCedula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(labelNombre))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(labelApellido))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(labelDireccion))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(labelTelefono))
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(chckbxBecado)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(labelDescuento).addComponent(
						txtDescuento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(labelRazon).addComponent(
						txtRazon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE).addGroup(groupLayout
						.createParallelGroup(Alignment.BASELINE).addComponent(btnIngresar).addComponent(btnLimpiar))
				.addContainerGap()));
		setLayout(groupLayout);

	}
}

package sistema.grafica;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import sistema.grafica.controladores.ControladorVentanaAltaAsignatura;
import sistema.logica.excepciones.SistemaException;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;

public class JPanelAltaAsignaturas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private ControladorVentanaAltaAsignatura controlador;

	public JPanelAltaAsignaturas() {
		controlador = new ControladorVentanaAltaAsignatura();
		setBorder(null);
		JLabel labelTitulo = new JLabel("Alta Asignatura");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		JLabel labelCodigo = new JLabel("Código:");
		labelCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel labelDescripcion = new JLabel("Descripción:");
		labelDescripcion.setHorizontalAlignment(SwingConstants.CENTER);

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = txtCodigo.getText();
				String nombre = txtNombre.getText();
				String descripcion = txtDescripcion.getText();
				
				if(codigo.isBlank() || nombre.isBlank() || descripcion.isBlank()) {
					JOptionPane.showMessageDialog(null, "Uno o mas campos estan en blanco.", "Cuidado!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						controlador.ingresarAsignatura(nombre, codigo, descripcion);	
						JOptionPane.showMessageDialog(null, "La asignatura fue ingresada correctame.", "Exito!",
								JOptionPane.INFORMATION_MESSAGE);
						txtCodigo.setText("");
						txtNombre.setText("");
						txtDescripcion.setText("");
					} catch(RemoteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Atencion!", JOptionPane.ERROR_MESSAGE);
					} catch(SistemaException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Atencion!", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCodigo.setText("");
				txtNombre.setText("");
			}
		});
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isLetter(c) && !Character.isDigit(c)) {
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
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
					e.consume();
				}
			}
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnIngresar.doClick();
				}
			}
		});
		txtDescripcion.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnIngresar.doClick();
				}
			}
		});		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(labelTitulo, GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(labelCodigo, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
										.addComponent(labelNombre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(labelDescripcion, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtDescripcion)
										.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
										.addComponent(txtCodigo, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup().addComponent(btnLimpiar).addGap(18)
								.addComponent(btnIngresar)))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap().addComponent(labelTitulo).addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(labelCodigo).addComponent(
						txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(labelNombre).addComponent(
						txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(labelDescripcion).addComponent(
						txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 163, Short.MAX_VALUE).addGroup(groupLayout
						.createParallelGroup(Alignment.BASELINE).addComponent(btnIngresar).addComponent(btnLimpiar))
				.addContainerGap()));
		setLayout(groupLayout);
	}
}

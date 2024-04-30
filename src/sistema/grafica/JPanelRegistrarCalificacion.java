package sistema.grafica;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

import sistema.grafica.controladores.ControladorVentanaRegistrarCalificacion;
import sistema.logica.excepciones.SistemaException;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;

public class JPanelRegistrarCalificacion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtCedula;
	private JTextField txtInscripcion;
	private ControladorVentanaRegistrarCalificacion controlador;

	public JPanelRegistrarCalificacion() {
		controlador = new ControladorVentanaRegistrarCalificacion();

		JLabel lblNewLabel = new JLabel("Registrar Calificacion");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		JLabel labelCedula = new JLabel("Cédula:");
		labelCedula.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel labelNumInscripcion = new JLabel("N° Inscripción:");
		labelNumInscripcion.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel labelCalificacion = new JLabel("Calificación:");
		labelCalificacion.setHorizontalAlignment(SwingConstants.CENTER);

		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtInscripcion = new JTextField();
		txtInscripcion.setColumns(10);
		
		JComboBox<String> cmbCalificacion = new JComboBox<String>();
		JButton btnRegistrar = new JButton("Registrar");
		JButton btnLimpiar = new JButton("Limpiar");
		
		txtCedula.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c)) {
					e.consume();
				}
			}
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnRegistrar.doClick();
				}
			}
		});
		txtInscripcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c)) {
					e.consume();
				}
			}
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnRegistrar.doClick();
				}
			}
		});

		cmbCalificacion.setModel(new DefaultComboBoxModel<String>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cedulaString = txtCedula.getText();
				String inscripcionString = txtInscripcion.getText();
				int calificacion = Integer.parseInt((String) cmbCalificacion.getSelectedItem());
				if(cedulaString.isBlank() || inscripcionString.isBlank() || calificacion == 0) {
					JOptionPane.showMessageDialog(null, "Uno o mas campos estan en blanco.", "Cuidado!",
							JOptionPane.WARNING_MESSAGE);
				}else {
					int cedula = Integer.parseInt(txtCedula.getText());
					int inscripcion = Integer.parseInt(txtInscripcion.getText());
					try {
						controlador.ingresarCalificacion(cedula, inscripcion, calificacion);
						JOptionPane.showMessageDialog(null, "Calificacion ingresada correctame.", "Exito!",
								JOptionPane.INFORMATION_MESSAGE);
						txtCedula.setText("");
						txtInscripcion.setText("");
						cmbCalificacion.setSelectedIndex(0);
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
				txtInscripcion.setText("");
				cmbCalificacion.setSelectedIndex(0);
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(labelCalificacion, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(labelNumInscripcion, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(labelCedula, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 106,
												Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtInscripcion, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 320,
												Short.MAX_VALUE)
										.addComponent(txtCedula, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
										.addComponent(cmbCalificacion, 0, 320, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup().addComponent(btnLimpiar)
								.addGap(18).addComponent(btnRegistrar)))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap().addComponent(lblNewLabel).addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(labelCedula).addComponent(
						txtCedula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(labelNumInscripcion).addComponent(
						txtInscripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(labelCalificacion).addComponent(
						cmbCalificacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 131, Short.MAX_VALUE).addGroup(groupLayout
						.createParallelGroup(Alignment.BASELINE).addComponent(btnRegistrar).addComponent(btnLimpiar))
				.addContainerGap()));
		setLayout(groupLayout);

	}
}

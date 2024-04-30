package sistema.grafica;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;

import sistema.grafica.controladores.ControladorVentanaObtenerMontoInscripciones;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import sistema.logica.excepciones.SistemaException;

public class JPanelObtenerMontoInscripciones extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldCedula;
	private JTextField textFieldAnio;
	private ControladorVentanaObtenerMontoInscripciones controlador;
	/**
	 * Create the panel.
	 */
	public JPanelObtenerMontoInscripciones() {
		controlador = new ControladorVentanaObtenerMontoInscripciones();
		JLabel labelTitulo = new JLabel("Monto recaudado por inscripciones");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		JLabel labelCedulaAlumno = new JLabel("Cédula de alumno:");
		JLabel labelAnioLectivo = new JLabel("Año lectivo:");
		JButton btnBuscar = new JButton("Buscar");
		JLabel labelMonto = new JLabel();
		labelMonto.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelMonto.setHorizontalAlignment(SwingConstants.LEFT);
		
		textFieldCedula = new JTextField();
		textFieldCedula.setColumns(10);				
		textFieldAnio = new JTextField();
		textFieldAnio.setColumns(10);	
		
		textFieldCedula.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c)) {
					e.consume();
				}
			}
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnBuscar.doClick();
				}
			}
		});	
		textFieldAnio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c)) {
					e.consume();
				}
			}
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnBuscar.doClick();
				}
			}
		});
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cedulaString = textFieldCedula.getText();
				String anioString = textFieldAnio.getText();
				try {				
					if(cedulaString.isBlank() || anioString.isBlank()) {
						JOptionPane.showMessageDialog(null, "Uno o mas campos estan en blanco.", "Error",
								JOptionPane.WARNING_MESSAGE);
					}else {
						int cedula = Integer.parseInt(cedulaString);
						int anio = Integer.parseInt(anioString);
						float monto = controlador.obtenerMontoInscripciones(cedula, anio);
						labelMonto.setText(Float.toString(monto));	
					}
					
				} catch (RemoteException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Atencion!", JOptionPane.ERROR_MESSAGE);
				} catch (SistemaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Atencion!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});	
		
		JLabel labelTextMontoRecaudado = new JLabel("Monto recaudado:");
		labelTextMontoRecaudado.setFont(new Font("Tahoma", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(labelAnioLectivo)
									.addGap(32))
								.addComponent(labelCedulaAlumno))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textFieldAnio)
								.addComponent(textFieldCedula, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
							.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
						.addComponent(labelTitulo, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(labelTextMontoRecaudado, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(labelMonto, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelTitulo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelCedulaAlumno, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldCedula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelAnioLectivo, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldAnio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscar))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(labelMonto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(labelTextMontoRecaudado, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
					.addContainerGap(154, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}

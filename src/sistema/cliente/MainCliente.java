package sistema.cliente;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import sistema.conexion.ArchivoConfiguracion;
import sistema.conexion.Connector;
import sistema.grafica.VentanaPrincipal;
import sistema.logica.excepciones.SistemaException;

public class MainCliente {
	public static void main(String[] args) throws SistemaException {

		String ip = ArchivoConfiguracion.getInstancia().getIpServidor();
		int port = ArchivoConfiguracion.getInstancia().getPuertoServidor();

		String ruta = "//" + ip + ":" + port + "/sistema";

		Connector.setConnectorRuta(ruta);

		VentanaPrincipal ventana = new VentanaPrincipal();

		ventana.setVisible(true);
	}
}

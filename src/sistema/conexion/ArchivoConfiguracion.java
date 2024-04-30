package sistema.conexion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import sistema.logica.constantes.CodExcepcionesArchivos;
import sistema.logica.excepciones.SistemaException;

public class ArchivoConfiguracion {
	private static ArchivoConfiguracion instancia;
	private String rutaRespaldo;
	private String ipServidor;
	private int puertoServidor;

	public static ArchivoConfiguracion getInstancia() throws SistemaException {
		if (instancia == null) {
			instancia = new ArchivoConfiguracion();
		}
		return instancia;
	}

	private ArchivoConfiguracion() throws SistemaException {
		Properties p = new Properties();
		String nomArch = "config/config.properties";
		try {
			p.load(new FileInputStream(nomArch));
			rutaRespaldo = p.getProperty("archivoRespaldo");
			puertoServidor = Integer.parseInt(p.getProperty("puertoServidor"));
			ipServidor = p.getProperty("ipServidor");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			throw new SistemaException(CodExcepcionesArchivos.ERR_CONFIG.getMsg());
		}
	}

	public String getRutaRespaldo() {
		return rutaRespaldo;
	}

	public String getIpServidor() {
		return ipServidor;
	}

	public int getPuertoServidor() {
		return puertoServidor;
	}

}

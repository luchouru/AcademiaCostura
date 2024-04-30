package sistema.persistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import sistema.logica.VO.VOPersistencia;
import sistema.logica.constantes.CodExcepcionesArchivos;
import sistema.logica.excepciones.SistemaException;

public class FachadaPersistencia {
	private static FachadaPersistencia instancia;

	private Properties p;
	private String archivoRespaldo;
	private String nomArch;

	private Respaldo res;
	private Recuperacion rest;

	private FachadaPersistencia() throws SistemaException {

		res = new Respaldo();
		rest = new Recuperacion();
		p = new Properties();
		nomArch = "config/config.properties";
		try {
			p.load(new FileInputStream(nomArch));
			archivoRespaldo = p.getProperty("archivoRespaldo");
		} catch (IOException e) {
			e.printStackTrace();
			throw new SistemaException(CodExcepcionesArchivos.ERR_CONFIG.getMsg());
		}

	}

	public static FachadaPersistencia getInstancia() throws SistemaException {
		if (instancia == null) {
			instancia = new FachadaPersistencia();
		}
		return instancia;

	}

	public void respaldar(VOPersistencia datos) throws SistemaException {
		res.respaldarDatos(archivoRespaldo, datos);

	}

	public VOPersistencia recuperar() throws SistemaException {
		return rest.recuperarDatos(archivoRespaldo);
	}
}

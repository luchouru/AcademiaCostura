package sistema.grafica.controladores;

import sistema.conexion.Connector;
import sistema.logica.FachadaLogica.IFachadaLogica;
import sistema.logica.excepciones.SistemaException;

public abstract class ControladorSistema {
	protected IFachadaLogica getConexion() throws SistemaException {
		return (IFachadaLogica) Connector.createConnection();
	}
}

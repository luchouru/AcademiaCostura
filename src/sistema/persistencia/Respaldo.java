package sistema.persistencia;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import sistema.logica.VO.VOPersistencia;
import sistema.logica.constantes.CodExcepcionesArchivos;
import sistema.logica.excepciones.SistemaException;

public class Respaldo {

	private void respaldar(String nomArchivo, VOPersistencia datos) throws SistemaException {
		try {
			FileOutputStream f = new FileOutputStream(nomArchivo);
			ObjectOutputStream o = new ObjectOutputStream(f);

			try {
				o.writeObject(datos);
			} catch (IOException e) {
				e.printStackTrace();
				throw new SistemaException(CodExcepcionesArchivos.ERR_WRITE.getMsg());
			} finally {
				o.close();
				f.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new SistemaException(CodExcepcionesArchivos.ERR_OPEN.getMsg());
		}
	}

	public void respaldarDatos(String archivoRespaldo, VOPersistencia datos) throws SistemaException {
		this.respaldar(archivoRespaldo, datos);
	}

}

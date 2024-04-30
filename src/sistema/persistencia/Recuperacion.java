package sistema.persistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import sistema.logica.VO.VOPersistencia;
import sistema.logica.constantes.CodExcepcionesArchivos;
import sistema.logica.excepciones.SistemaException;

public class Recuperacion {
	
	public VOPersistencia Recuperar(String nomArch) throws SistemaException {
		try {
			FileInputStream f = new FileInputStream(nomArch);
			ObjectInputStream o = new ObjectInputStream(f);	
			VOPersistencia datos = (VOPersistencia) o.readObject();	
			o.close();
			f.close();	
			return datos;			
		} catch(IOException e) {
			e.printStackTrace();
			throw new SistemaException(CodExcepcionesArchivos.ERR_OPEN.getMsg());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new SistemaException(CodExcepcionesArchivos.ERR_READ.getMsg());
		}
	}
	
	public VOPersistencia recuperarDatos(String archivoRecuperacion) throws SistemaException {
		return this.Recuperar(archivoRecuperacion);
	}
}

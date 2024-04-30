package sistema.logica.constantes;

public enum CodExcepcionesArchivos {

	ERR_RESPALDO("Error al respaldar."),
	ERR_CONFIG("Error al inicializar config."),
	ERR_WRITE("Error al escribir en archivo."), 
	ERR_OPEN("Error al abrir el archivo."),
	ERR_RESTAURACION("Error al restaurar."), 
	ERR_READ("Error al intentar leer el archivo.");

	private String msg;

	CodExcepcionesArchivos(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
}

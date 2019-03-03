package tanksWar;

public class ConfiguracionNoEncontradaException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mensaje;

	ConfiguracionNoEncontradaException(String mensaje) {
		this.mensaje = mensaje;
	}
	
	/**
	 * Devuelve el mensaje asociado a la excepcion
	 * 
	 * @return Mensaje de error
	 */
	@Override
	public String getMessage() {
		return this.mensaje;
	}
}

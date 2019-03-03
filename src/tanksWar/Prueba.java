package tanksWar;

public class Prueba {
	
	/**
	 * Prepara y ejecuta el juego
	 * 
	 * @param args Argumentos
	 */
	public static void main(String[] args) {
		try {
			Escenario escenario = new Escenario();
			Vista vista = new Vista(escenario);
			VistaConsola consola = new VistaConsola();
			escenario.addJuegoListener(consola);
			escenario.addJuegoListener(vista);
			vista.setVisible(true);
			escenario.setVista(vista);
			escenario.iniciarJuego();
		} catch (ConfiguracionNoEncontradaException e) {
			e.printStackTrace();
		}
	}
}
package tanksWar;

public class Bordeador extends Tanque{

	/**	
	 * Ejecuta en cada turno del elemento en su forma especifica
	 * para realizar las acciones requeridas por el mismo.
	 * 
	 */
	public void jugar() {
		super.jugar();
		super.avanzar();
	}
		
	/**
	 * Gira 90 grados al chocar una pared
	 */
	public void chocoContraPared() {
		this.girar(90);			
	}
}

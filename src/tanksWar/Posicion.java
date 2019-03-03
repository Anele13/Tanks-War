package tanksWar;

import tanks2015.common.IPosicion;

public class Posicion implements IPosicion{
	
	private double x;
	private double y;

	/**
	 * Devuelve la posicion respecto al eje X
	 * 
	 * @return Posicion en el eje X en píxeles.
	 */
	@Override
	public double getX() {
		return this.x;
	}

	/**
	 * Devuelve la posicion respecto al eje y
	 * 
	 * @return Posicion en el eje y en píxeles.
	 */
	@Override
	public double getY() {
		return this.y;
	}

	/**
	 * Configura la posicion respecto al eje X
	 * 
	 * @param x Posicion en el eje X en píxeles.
	 */
	@Override
	public void setX(double x) {
		this.x= x;
	}

	/**
	 * Configura la posicion respecto al eje y
	 * 
	 * @param y Posicion en el eje y en píxeles.
	 */
	@Override
	public void setY(double y) {
		this.y= y;
	}
	

}

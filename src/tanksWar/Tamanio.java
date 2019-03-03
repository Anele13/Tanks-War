package tanksWar;

import tanks2015.common.ITamanio;

public class Tamanio implements ITamanio {
	
	private int ancho;
	private int alto;
	
	/**
	 * Devuelve el ancho del elemento
	 * 
	 * @return Ancho medido en pixels
	 */
	@Override
	public int getAncho() {
		return this.ancho;
	}

	/**
	 * Configura el ancho del elemento
	 * 
	 * @param ancho Ancho medido en pixels
	 */
	@Override
	public void setAncho(int ancho) {
		this.ancho= ancho;
	}

	/**
	 * Devuelve el alto del elemento
	 * 
	 * @return Alto medido en pixels
	 */
	@Override
	public int getAlto() {
		return this.alto;
	}

	/**
	 * Configura el alto del elemento
	 * 
	 * @param alto Alto medido en pixels
	 */
	@Override
	public void setAlto(int alto) {
		this.alto= alto;
	}
	
	

}

package tanksWar;

import java.util.Random;

public class Crazy extends Tanque{
	
	private int angulo;
	private Random rnd = new Random();
			
	
	/**
	 * Juega de acuerdo a su implementacion
	 * 
	 */
	@Override
	public void jugar() {
		super.jugar();
		this.avanzar();
	}
	
	/**
	 * Avanza de manera indefinida por todo el escenario
	 * 
	 */
	@Override
	public void avanzar() {
		super.avanzar();
		nuevaDireccion();
	}
		
	/**
	 * Pide un numero aleatorio para girar en un sentido u otro
	 * 
	 */
	public void nuevaDireccion(){
		if(this.getDireccion()==180){
			this.angulo =(int)(rnd.nextInt(4)-2);
		}
		if(this.getDireccion()==0){
			this.angulo =(int)(rnd.nextInt(3)+1);
		}
		this.girar(angulo);
	}
}

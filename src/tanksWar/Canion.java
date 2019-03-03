package tanksWar;

import tanks2015.common.ICanion;

import tanks2015.common.ITanque;

public class Canion implements ICanion{
	
	private final int  TOPE_ENFRIAMIENTO = 0;
	private int anguloDisparo = 0;
	private int municiones = 1000;
	private int enfriamiento = TOPE_ENFRIAMIENTO; 
	private ITanque tanque;


	/**
	 * Devuelve la cantidad de municiones
	 * 
	 * @return Cantidad actual de municiones
	 */
	@Override
	public int getMuniciones() {
		return this.municiones;
	}

	/**
	 * Devuelve el tanque al que pertenece el cañon
	 * 
	 * @return tanque al que pertenece el cañon
	 */
	public ITanque getTanque() {
		return tanque;
	}

	/**
	 * Recarga las municiones una cierta cantidad
	 * 
	 * @param cantidad Cantidad a recargar
	 */
	@Override
	public void recargarMuniciones(int cantidad) {
		this.municiones = this.getMuniciones()+ cantidad;
	}

	/**
	 * Dispara una municion
	 */
	@Override
	public void disparar() {
		if(canionEstaFrio()&& this.getMuniciones()>0){
			Municion municion= new Municion(this.tanque);
			this.tanque.getEscenario().addElemento(municion);
			this.municiones=this.getMuniciones()-1;
			this.enfriamiento=0;
		}
	}
	
	/**	 
	 * Devuelve el angulo en que se encuentra apuntando el cañon.
	 * 
	 * @return Angulo
	 */
	@Override
	public int getAnguloDisparo() {
		return this.anguloDisparo;
	}

	/**
	 * Configura el angulo de disparo
	 * 
	 * @param angulo Nuevo angulo de disparo
	 */
	@Override
	public void setAnguloDisparo(int angulo) {
		this.anguloDisparo = angulo;
	}

	/**
	 * Gira la dirección del cañon en un determinado angulo
	 * 
	 * @param angulo Angulo que se desea girar.
	 */
	@Override
	public void girar(int angulo) {
		int nuevoAngulo= this.getAnguloDisparo()+angulo;
		while (nuevoAngulo>=360) {
			nuevoAngulo = nuevoAngulo-360;
		}
		this.setAnguloDisparo(nuevoAngulo);
	}
	
	/**
	 * Crea un cañon nuevo
	 * 
	 * @param tanque Tanque al que se lo va a asociar
	 */
	public Canion(ITanque tanque){
		this.tanque= tanque;
	}
	
	/**
	 * Devuelve el estado del cañon, si esta frio o no
	 * 
	 * @return el estado del cañon, si esta frio o no
	 */
	
	public boolean canionEstaFrio(){
		if(this.enfriamiento==TOPE_ENFRIAMIENTO){
			return true;
		}
		else{
			this.enfriamiento++;
			return false;
		}
	}
}
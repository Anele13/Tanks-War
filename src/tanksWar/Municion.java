package tanksWar;

import tanks2015.common.IBonus;
import tanks2015.common.IMunicion;
import tanks2015.common.ITanque;


public class Municion extends ElementoMovible implements IMunicion{

	private ITanque tanque;
	
	/**
	 * Devuelve el tanque que lanzó la munición.
	 * 
	 * @return Tanque que lanzo la munición.
	 */
	@Override
	public ITanque getTanque() {
		return this.tanque;
	}
	
	/**
	 * Crea una municion desde la posicion inicial del tanque y con su 
	 * direccion igual al angulo de disparo.
	 * @param tanque Tanque que disparo la municion.
	 */
	public Municion (ITanque tanque){
		this.tanque = tanque;
		this.setDireccion(tanque.getCanion().getAnguloDisparo());
		this.setVelocidad(tanque.getVelocidad()+5);
		
		this.getTamanio().setAlto(tanque.getEscenario().getConfig().getAltoMunicion());
	    this.getTamanio().setAncho(tanque.getEscenario().getConfig().getAnchoMunicion());
	    
		this.getPosicion().setX(tanque.getPosicion().getX()+Math.cos(Math.toRadians(this.getDireccion())));
		this.getPosicion().setY(tanque.getPosicion().getY()+Math.sin(Math.toRadians(this.getDireccion())));
	    
	}
	
	/**
	 * La municion avanza en su turno
	 */
	@Override
	public void jugar() {
		avanzar();
	}
	
	/**
	 * Se ejecuta si la municion choca contra la pared, si es asi, muere
	 */
	@Override
	public void chocoContraPared() {
		this.morir();
	}

	/**
	 * Se ejecuta si la municion choca contra algun tanque, si es asi, muere
	*/
	@Override
	public void chocoContraTanque(ITanque tanque) {
		if(this.getTanque()!=tanque){
			this.morir();
		}
	}
	
	/**
	 * Se ejecuta si la municion choca contra algun bonus, le devuelve la llamada
	*/
	@Override
	public void chocoContraBonus(IBonus bonus) {	
		bonus.morir();
	}
	
	/**
	 * Se ejecuta si la municion choca contra una bomba.
	*/
	@Override
	public void chocoContraBomba(Bomba bomba) {
		bomba.morir();
	}
	
	/**
	 * Se ejecuta si la municion choca contra algun elemento, le devuelve la llamada
	*/
	@Override
	public void impactoContra(Elemento elemento) {
		if(elemento.estaVivo()){
			elemento.chocoContraMunicion(this);
		}
	}
}
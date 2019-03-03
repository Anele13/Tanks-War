package tanksWar;

import tanks2015.common.IBonus;
import tanks2015.common.IMunicion;
import tanks2015.common.ITanque;

public class Bomba extends Elemento{
	
	private Tanque tanque;
	private int tiempoVida= 300;
	private int tiempoActiva = 30;
	private boolean bombaActiva;

	
	/**
	 * Configura la referencia al tanque dueño de la bomba
	 * 
	 * @param tanque Tanque dueño de la bomba
	 * 
	 */
	public void setTanque(Tanque tanque){
		this.tanque=tanque;
	}
	
	/**
	 * devuelve el tanque dueño de la bomba
	 * 
	 * @return Tanque dueño de la bomba
	 * 
	 */
	public Tanque getTanque(){
		return this.tanque;
	}
	
	
	/**
	 * Configura un tiempo de vida maximo a la bomba
	 * 
	 * @param tiempo de vida de la bomba
	 * 
	 */
	public void setTiempoVida(int tiempo) {
		this.tiempoVida= tiempo;
	}
	
	/**
	 * devuelve el tiempo de vida maximo de la bomba
	 * 
	 * @return Tiempo de vida de la bomba
	 * 
	 */
	public int getTiempoVida() {
		return this.tiempoVida;
	}
	
	/**
	 * Configura la bomba en estado de explosion 
	 * 
	 * @param estado de explosion de la bomba
	 */
	public void setEstadoExplosion(boolean estado){
		this.bombaActiva= estado;
	}
	
	/**
	 * devuelve verdadero si la bomba esta en estado de explosion, falso si no.
	 * 
	 * @return estado de explosion
	 * 
	 */
	public boolean getEstadoExplosion(){
		return this.bombaActiva;
	}
	
	
	/**
	 * se ejecuta si la bomba choca contra un tanque
	 * 
	 * @param tanque contra el que choco
	 * 
	 */	
	@Override
	public void chocoContraTanque(ITanque tanque) {
		if(this.getTanque()!=tanque && this.getEstadoExplosion()){ 
			tanque.romperEscudo(1);
			tanque.estaVivo();
		}
	}
	
	/**
	 * se ejecuta si la bomba choca contra una municion
	 * 
	 * @param municion contra la que choco
	 * 
	 */
	@Override
	public void chocoContraMunicion(IMunicion municion) {
		if(this.getEstadoExplosion()){
			municion.morir();
			this.morir();
		}
	}
	
	/**
	 * se ejecuta si la bomba choca contra un bonus
	 * 
	 * @param bonus contra el que choco
	 * 
	 */
	@Override
	public void chocoContraBonus(IBonus bonus) {
		
	}
	
	/**
	 * se ejecuta si la bomba choca contra algun elemento del escenario
	 * 
	 * @param elemento contra el que choco
	 * 
	 */
	@Override
	public void impactoContra(Elemento elemento) {
		elemento.chocoContraBomba(this);
	}
	
	/**
	 * metodo de creacion de bombas
	 * 
	 * @param tanque Tanque asociado a la bomba
	 * 
	 */
	public Bomba(Tanque tanque) {
		this.tanque=tanque;
		this.getTamanio().setAlto(20);  
		this.getTamanio().setAncho(20); 
		this.setTiempoVida(this.tiempoVida+this.tiempoActiva);
		this.setEstadoExplosion(false);
		this.getPosicion().setX(tanque.getPosicion().getX());
		this.getPosicion().setY(tanque.getPosicion().getY());
	}
	
	
	/**
	 * Pone a la bomba en estado de explosion
	 */
	public void explotar(){
		this.getTamanio().setAlto(this.getTamanio().getAlto()*10);  //para notar el efecto explosion, tamaño recomendado 50
		this.getTamanio().setAncho(this.getTamanio().getAncho()*10);
		this.setEstadoExplosion(true);
	}
	
	/**
	 * se ejecuta si la bomba choca contra un tanque
	 * 
	 * @return Verdadero si esta viva, falso si no.
	 * 
	 */
	@Override
	public boolean estaVivo() {
		if (tiempoVida<=0){
			this.morir();
		}
		return super.estaVivo();
	}
	
	/**
	 * Juega de acuerdo a su implementacion
	 * 
	 */
	@Override
	public void jugar() {
		if(this.estaVivo()){
			this.setTiempoVida(this.getTiempoVida()-1);
			if (this.getTiempoVida()==this.tiempoActiva){
				explotar();
			}
		}
	}
}

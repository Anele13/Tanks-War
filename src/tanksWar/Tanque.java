package tanksWar;

import tanks2015.common.ITanque;

import java.util.ArrayList;

import tanks2015.common.IBonus;
import tanks2015.common.ICanion;
import tanks2015.common.IRadar;
import tanks2015.common.IRadarListener;
import tanks2015.common.IElemento;
import tanks2015.common.IMunicion;


public class Tanque extends ElementoMovible implements ITanque, IRadarListener{
	
	private ICanion canion;
	private int combustible = 10000 ;
	private int escudo= 20;
	private IRadar radar;
	private String nombre;
	
	
	/**
	 * Se ejecuta si el tanque choca contra la pared
	*/
	@Override
	public void chocoContraPared() {
	}
	
	/**
	 * Decrementa escudo de un tanque si colisiona contra algun tanque
	*/
	@Override
	public void chocoContraTanque(ITanque tanque) {
		this.girar(180);
		this.romperEscudo(1);
		this.estaVivo();
	}
	
	/**
	 * decrementa el escudo de un tanque si colisiona contra una municion
	*/ 
	@Override
	public void chocoContraMunicion(IMunicion municion) {
		if (this != municion.getTanque()){
			this.romperEscudo(1);
			this.estaVivo();
		}
	}
	
	/**
	 * Devuelve la llamada a un bonus
	*/
	@Override
	public void chocoContraBomba(Bomba bomba) {
		if(this!= bomba.getTanque()){
			bomba.morir();
		}
	}
	
	/**
	 * Devuelve la llamada a un bonus
	*/
	@Override
	public void chocoContraBonus(IBonus bonus) {
		bonus.morir();
	}
	
	/**
	 * Implementa el comportamiento del tanque al chocar contra otro elemento
	 * dependiendo de que tipo de elemento sea.
	 * 
	 * @param elemento Elemento contra el que choca.
	 */
	@Override
	public void impactoContra(Elemento elemento) {
		elemento.chocoContraTanque(this);
	}
	
	/**
	 * Verifica si el tanque redujo su Escudo a cero y de ser asi, lo mata.
	 * @return Devuelve si esta vivo o no
	 */
	@Override
	public boolean estaVivo() {
		if (this.getEscudo()<=0){
			this.morir();
		}
		return super.estaVivo();
	}

	/**
	 * Devuelve el nombre del tanque para ser identificado en forma visual dentro del juego.
	 * 
	 * @return Nombre
	 */
	
	
	@Override
	public String getNombre() {
		return this.nombre;		
	}

	/**
	 * Devuelve la cantidad de combustible en tanque
	 * 
	 * @return Cantidad de combustible en el tanque
	 */
	@Override
	public int getCombustible() {
	 return this.combustible;
	}

	/**
	 * Configura la cantidad de combustible en el tanque
	 * 
	 * @param combustible Cantidad de combustible
	 */
	@Override
	public void setCombustible(int combustible) {
		this.combustible = combustible;
	}

	/**
	 * Recarga el combustible del tanque en una cierta cantidad.
	 * 
	 * @param cantidad Cantidad de combustible a recargar.
	 */
	@Override
	public void recargarCombustible(int cantidad) {
		this.setCombustible(this.getCombustible()+ cantidad);
	}

	/**
	 * Devuelve la cantidad de Escudo en el tanque.
	 * 
	 * @return cantidad de escudo
	 */
	@Override
	public int getEscudo() {
		return this.escudo;
	}

	/**
	 * Configura la cantidad de escudo en el tanque.
	 * 
	 * @param escudo Cantidad de escudo.
	 */
	@Override
	public void setEscudo(int escudo) {
		this.escudo = escudo;
	}

	/**
	 * Reduce el escudo del tanque en una cierta cantidad.
	 * 
	 * @param cantidad Cantidad de escudo a reducir.
	 */
	@Override
	public void romperEscudo(int cantidad) {
		this.setEscudo(this.getEscudo()- cantidad);
	}

	/**
	 * Restaura el escudo del tanque en una cierta cantidad.
	 * 
	 * @param cantidad Cantidad de escudo a restaurar. 
	 */
	@Override
	public void restaurarEscudo(int cantidad) {
		this.setEscudo(this.getEscudo()+ cantidad);
	}

	/**
	 * Devuelve el radar asociado al tanque.
	 * 
	 * @return Radar asociado al tanque.
	 */
	@Override
	public IRadar getRadar() {
		return this.radar;
	}
	
	/**
	 * Devuelve el cañon montado en el tanque.
	 * 
	 * @return Cañon motado en el tanque.
	 */
	@Override
	public ICanion getCanion() {
		return this.canion;
	}
	
	/**
	 * Crea un nuevo tanque, asociandole un nuevo radar y un nuevo cañon.
	 */
	public Tanque(){
		this.radar = new Radar(this);
		this.canion= new Canion(this);
		this.radar.addRadarListener(this);
	}
	
	/**
	 * Ejecuta en cada turno del tanque escaneando con el radar en cada uno.
	 */
	@Override
	public void jugar() {
		this.getRadar().escanear();
	}

	/**
	* Metodo para realizar acciones cuando el redar detecta elementos
	*/
	@Override
	public void elementoDetectado(ArrayList<IElemento> elementosDetectados) {
	}
	
	/**
	 * Avanza el tanque si aun posee combustible
	*/
	@Override
	public void avanzar() {
		if(!(this.getCombustible()<=0)){
			super.avanzar();
			this.setCombustible(this.getCombustible()-1);
		}
	}
}
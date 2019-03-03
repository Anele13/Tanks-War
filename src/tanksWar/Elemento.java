package tanksWar;

import tanks2015.common.IElemento;
import tanks2015.common.IEscenario;
import tanks2015.common.IMunicion;
import tanks2015.common.IPosicion;
import tanks2015.common.ITamanio;

public abstract class Elemento implements IElemento{
	
	private boolean estadoVida = true;
	private IPosicion posicion = new Posicion();
	private ITamanio tamanio = new Tamanio();
	private IEscenario escenario;
	
	
	@Override
	public void chocoContra(IElemento elemento) {}

	/**	
	 * Ejecuta en cada turno del elemento en su forma especifica
	 * para realizar las acciones requeridas por el mismo.
	 * 
	 */
	@Override
	public void jugar() {}
	
	/**
	 * Asocia un escenario al elemento
	 * 
	 * @param escenario Escneario donde se encuentra el elemento
	 */
	public void setEscenario(IEscenario escenario) {
		this.escenario = escenario;
	}

	/**
	 * Devuelve el escenario donde se esta jugando
	 * 
	 * @return Escenario del juego
	 */
	@Override
	public IEscenario getEscenario() {
		return this.escenario;
	}

	/**
	 * Configura el tamaño del elemento.
	 * 
	 * @param tamanio Nuevo tamaño
	 */
	@Override
	public void setTamanio(ITamanio tamanio) {
		this.tamanio= tamanio;
			
	}
	
	/**
	 * Devuelve el tamaño de elemento.
	 * 
	 * @return tamaño del elemento
	 */
	@Override
	public ITamanio getTamanio() {
		return this.tamanio;
	}

	/**
	 * Devuelve la posicion en donde se encuentra el elemento.
	 * 
	 * @return Posicion del elemento
	 */
	@Override
	public IPosicion getPosicion() {
		return this.posicion;
	}

	
	/**
	 * Indica que el elemento muere para luego se depurado
	 */
	@Override
	public void morir() {
		this.estadoVida = false;
	}

	/**
	 * Devuelve si el elemento se encuentra vivo.
	 * 
	 * @return Verdadero cuando se encuentra vivo
	 */
	@Override
	public boolean estaVivo() {
		return estadoVida;		
	}
	
	/**
	 * Indica que comportamiento toma al chocar contra una pared 
	 */
	@Override
	public void chocoContraPared() {}
	
	/**
	 * Indica que comportamiento toma al chocar contra una bomba
	 */
	public void chocoContraBomba(Bomba bomba){}
	
	/**
	 * Indica el comportamiento que toma al chocar con otro elemento.
	 * Se sobreescribe en las subclases
	 * 
	 * @param elemento Elemento contra el que esta chocando
	 */
	public void impactoContra(Elemento elemento){}
	
	/**
	 * Indica que comportamiento toma al chocar contra una municion
	 */
	public void chocoContraMunicion(IMunicion municion){}
}

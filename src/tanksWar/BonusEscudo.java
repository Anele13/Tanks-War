package tanksWar;

import tanks2015.common.IBonusEscudo;
import tanks2015.common.IMunicion;
import tanks2015.common.ITanque;

public class BonusEscudo extends Bonus implements IBonusEscudo{
	
	private static final int AUMENTO = 20;
	
	
	/**
	 * Repara el escudo de un tanque
	 * 
	 * @param tanque Tanque que se debe reparar
	 */
	@Override
	public void repararEscudo(ITanque tanque) {
		tanque.restaurarEscudo(AUMENTO);
	}
	
	/**
	 * Recarga el escudo del tanque recibido por parametro
	 * 
	 * @param tanque Tanque que se desea recargar
	 */	
	@Override
	public void chocoContraTanque(ITanque tanque) {
		this.repararEscudo(tanque);
	}
	
	/**
	 * Repara el escudo del tanque al que pertenece la municion recibida por parametro
	 * 
	 * @param municion Municion del tanque que se desea reparar
	 */
	@Override
	public void chocoContraMunicion(IMunicion municion) {
		this.chocoContraTanque(municion.getTanque());
	}
}
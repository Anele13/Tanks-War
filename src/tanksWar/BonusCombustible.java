package tanksWar;

import tanks2015.common.IBonusCombustible;
import tanks2015.common.IMunicion;
import tanks2015.common.ITanque;;

public class BonusCombustible extends Bonus implements IBonusCombustible{

	private static final int AUMENTO = 30;
	
	/**
	 * Recarga de combustible del tanque
	 * 
	 * @param tanque Tanque que se desea recargar
	 */
	@Override
	public void recargarCombustible(ITanque tanque) {
		tanque.recargarCombustible(AUMENTO);
	}
	
	/**
	 * Recarga el combustible del tanque recibido por parametro
	 * 
	 * @param tanque Tanque que se desea recargar
	 */
	@Override
	public void chocoContraTanque(ITanque tanque) {
		this.recargarCombustible(tanque);
	}
	
	/**
	 * Recarga el combustible del tanque al que pertenece la municion recibida por parametro
	 * 
	 * @param municion Municion del tanque que se desea recargar
	 */
	@Override
	public void chocoContraMunicion(IMunicion municion) {
		this.chocoContraTanque(municion.getTanque());
	}
}

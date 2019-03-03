package tanksWar;

import tanks2015.common.IBonus;
import tanks2015.common.IMunicion;
import tanks2015.common.ITanque;


public class Bonus extends Elemento implements IBonus{
 
	private int tiempoVida=400;
	
	/**
	 * Devuelve el tiempo de vida maximo configurado para el bonus
	 * 
	 * @return Tiempo de vida maximo configurado
	 */
	
	@Override
	public int getTiempoVida() {
		return this.tiempoVida;
	}

	/**
	 * Configura un nuevo tiempo de vida maximo al bonus
	 * 
	 */
	@Override
	public void setTiempoVida(int tiempo) {
		this.tiempoVida= tiempo;
	}
	
	/**
	 * Hace jugar al bonus hasta que llegue a su tiempo de vida maximo
	 */
	@Override
	public void jugar() {
		if(this.estaVivo()){
			this.setTiempoVida(this.getTiempoVida()-1);
		}
	}
	
	/**
	 * Consulta el estado de un bonus
	 */
	@Override
	public boolean estaVivo() {
		if (tiempoVida<=0){
			this.morir();
		}
		return super.estaVivo();
	}
	
	/**
	 * Devuelve la llamada al elemento contra el que choco
	 */
	@Override
	public void impactoContra(Elemento elemento) {
		elemento.chocoContraBonus(this);
	}	
	
	@Override
	public void chocoContraTanque(ITanque tanque) {}

	@Override
	public void chocoContraMunicion(IMunicion municion) {}
	
	@Override
	public void chocoContraBonus(IBonus bonus) {}

	@Override
	public void chocoContraBomba(Bomba bomba) {}
	
}
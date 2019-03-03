package tanksWar;

import java.util.ArrayList;

import tanks2015.common.IElemento;
import tanks2015.common.IJuegoListener;


public class VistaConsola implements IJuegoListener {

	/**
	 * Muestra el estado del juego.
	 * 
	 * @param elementos Lista de elementos del juego para poder mostrarlos.
	 */
	@Override
	public void mostrarEstado(ArrayList<IElemento> elementos) {
		for (int i=0; i<elementos.size(); i++){
			IElemento e = elementos.get(i);
			System.out.println("Elemento: "+e.getClass().getSimpleName()+" Posicion: X:"+ (int)e.getPosicion().getX()+" Y:"+ (int)e.getPosicion().getY());
		}
	}
}

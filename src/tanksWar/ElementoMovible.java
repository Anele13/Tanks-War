package tanksWar;

import tanks2015.common.IBonus;
import tanks2015.common.IElemento;
import tanks2015.common.IElementoMovible;
import tanks2015.common.IMunicion;
import tanks2015.common.ITanque;


public class ElementoMovible extends Elemento implements IElementoMovible{
	
	private double velocidad = 1;
	private int direccion = 0;
	

	/**
	 * Devuelve la velocidad con la que avanza el elemento
	 * 
	 * @return Cantidad de pixels que avanza con cada movimiento.
	 */
	@Override
	public double getVelocidad() {
		return this.velocidad;
	}

	/**
	 * Configura la velocidad con que avanza el elemento
	 * 
	 * @param velocidad Cantidad de pixels que avanza con cada 
	 * movimiento.
	 */
	@Override
	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	/**
	 * Devuelve el angulo con el que el elemento avanza. Un angulo igual a cero indica que 
	 * el elemento avanza en forma horizontal de izquierda a derecha, y un angulo de 90 grados 
	 * indica que el elemento avanza en forma verticla desde abajo hacia arriba.
	 * 
	 * @return Angulo en grados
	 */
	@Override
	public int getDireccion() {
		return this.direccion;
	}
	
	/**
	 * Setea el angulo con el que el elemento avanza. Un angulo igual a cero indica que 
	 * el elemento avanza en forma horizontal de izquierda a derecha, y un angulo de 90 grados 
	 * indica que el elemento avanza en forma verticla desde abajo hacia arriba.
	 * 
	 * @param angulo Angulo
	 */
	@Override
	public void setDireccion(int angulo) {
		this.direccion= angulo;
	}

	/**
	 * Gira respecto al angulo actual
	 * 
	 * @param angulo Cantidad de grados que se desea girar
	 */
	@Override
	public void girar(int angulo) {
		int nuevaDireccion= this.getDireccion()+angulo;
		while (nuevaDireccion>=360) {
			nuevaDireccion=nuevaDireccion-360;
		}
		while (nuevaDireccion<0) {
			nuevaDireccion=nuevaDireccion+360;
		}
		this.setDireccion(nuevaDireccion);
	}

	/**
	 * Avanza en la direccion ya establecida a una cierta velocidad
	 * ya configurada
	 */
	@Override
	public void avanzar() {
		this.getPosicion().setX(this.getPosicion().getX()+ Math.cos(Math.toRadians(this.direccion))*this.velocidad);
		this.getPosicion().setY(this.getPosicion().getY()+ Math.sin(Math.toRadians(this.direccion))*this.velocidad);
	}

	/**
	 * Se ejecuta el metodo si un elemento choca contra un tanque, le devuelve la lllamada
	 */
	@Override
	public void chocoContraTanque(ITanque tanque) {
		
	}
	/**
	 * Se ejecuta el metodo si un elemento choca contra un bonus, le devuelve la lllamada
	 */
	@Override
	public void chocoContraBonus(IBonus bonus) {}

	/**
	 * Se ejecuta el metodo si un elemento choca contra una municion, le devuelve la lllamada
	 */
	@Override
	public void chocoContraMunicion(IMunicion municion) {}

	/**
	 * Se ejecuta el metodo si un elemento choca contra algun otro elemento
	 */
	@Override
	public void chocoContra(IElemento elemento) {}
}

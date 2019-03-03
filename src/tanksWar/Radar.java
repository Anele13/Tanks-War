package tanksWar;

import java.awt.Polygon;
import java.util.ArrayList;

import tanks2015.common.IElemento;
import tanks2015.common.IRadar;
import tanks2015.common.ITanque;
import tanks2015.common.IRadarListener;

public class Radar implements IRadar{
	
	private int alcanceMaximo = 50;
	private int anguloApertura;
	private int alcance;
	private int direccion;
	private ITanque tanque;
	private ArrayList<IRadarListener> RadarListener = new ArrayList<IRadarListener>();
	private ArrayList<IElemento> elementosDetectados= new ArrayList<IElemento>();
	
	/**
	 * Devuelve el alcance de cobertura del radar. Este alcance varia en función del 
	 * ángulo de apertura que posea.
	 * 
	 * @return Alcance del radar en pixels
	 */
	@Override
	public int getAlcance() {
		return alcance;
	}
	
	/**
	 * Devuelve el ángulo de apertura que posee el radar para escanear dentro del escenario.
	 * 
	 * @return Ángulo de apertura en grados.
	 */
	@Override
	public int getAnguloApertura() {
		return this.anguloApertura;
	}

	/**
	 * Configura el ángulo de apertura que posee el radar para escanear.
	 * 
	 * @param apertura Ángulo de apertura en grados.
	 */
	@Override
	public void setAnguloApertura(int apertura) {
		this.anguloApertura= apertura;
		double angulo=(double) (apertura);
		this.alcance=(int)(this.alcanceMaximo - (this.alcanceMaximo*(angulo/360)));
	}

	/**
	 * Devuelve el ángulo en que apunta el radar.
	 * 
	 * @return Ángulo en que apunta el radar en grados.
	 */
	@Override
	public int getDireccion() {
		return this.direccion;
	}
	
	/**
	 * Setea el ángulo en que apunta el radar.
	 * 
	 * @param angulo Ángulo en que apunta el radar en grados.
	 */
	@Override
	public void setDireccion(int angulo) {
		this.direccion= angulo;
	}
	
	/**
	 * Devuelve el tanque donde esta montado el radar.
	 * 
	 * @return Tanque donde esta montado el radar.
	 */
	@Override
	public ITanque getTanque() {
		return this.tanque;
	}
	
	/**
	 * Agrega un elemento que escuchará al radar.
	 * 
	 * @param listener Escuchador.
	 */
	@Override
	public void addRadarListener(IRadarListener listener) {
		this.RadarListener.add(listener);
	}

	/**
	 * Quita un elemento que escuchará al radar.
	 * 
	 * @param listener Escuchador.
	 */
	@Override
	public void removeRadarListener(IRadarListener listener) {
		this.RadarListener.remove(listener);
	}
	
	/**
	 * Crea un nuevo radar y se lo asocia a un tanque.
	 * 
	 * @param tanque Tanque en el que se monta el radar.
	 */
	public Radar(ITanque tanque){
		this.tanque = tanque;
	}
	
	/**
	 * Gira la dirección del radar en un determinado angulo
	 * 
	 * @param angulo Ángulo que se desea girar.
	 */
	@Override
	public void girar(int angulo) {
		int nuevaDir= this.getDireccion()+angulo;
		while (nuevaDir>=360) {
			nuevaDir=nuevaDir-360;
		}
		this.setDireccion(nuevaDir);
	}

	/**
	 * 	 
	 * Escanea los objetos dentro del esenario.
	 */
	@Override
	public void escanear() {
		this.girar(10);
		if (!this.tanque.getEscenario().detectarElementos(this).isEmpty()){
				elementosDetectados=this.tanque.getEscenario().detectarElementos(this);
				for(IRadarListener listener : this.RadarListener)
				listener.elementoDetectado(elementosDetectados);
		}
	}
	
	/**
	 * Devuelve el área de cobertura que tiene el radar para escanear.
	 * @return Poligono que representa el área de cobertura
	 */
	@Override
	public Polygon getAreaCobertura() {

	   	Polygon areaCobertura = new Polygon();
	   	
		int tanqueX= (int)tanque.getPosicion().getX()+10; // +10 para centrar radar en medio del tanque
		int tanqueY= (int)tanque.getPosicion().getY()+10;
		
		areaCobertura.addPoint(tanqueX, tanqueY);
		
		for (int i = (this.getDireccion())-(this.getAnguloApertura()/2);
				i < (this.getDireccion())+(this.getAnguloApertura()/2); 
				i++) {
			
			int x= (int) (tanqueX+ Math.cos(Math.toRadians(i))*this.getAlcance());
			int y= (int) (tanqueY+ Math.sin(Math.toRadians(i))*this.getAlcance());
		
			areaCobertura.addPoint(x, y);
		}
		
		return areaCobertura;
	}
}

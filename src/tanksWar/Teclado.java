package tanksWar;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado extends Tanque implements KeyListener{
	
	private int cantidadBombas;
	
	/**
	 * configura la cantidad maxima de bombas disponibles
	 * 
	 * @param cantidad Cantidad de bombas
	*/
	public void setCantidadBombas(int cantidad){
		this.cantidadBombas = cantidad;
	}
	
	/**
	 * Devuelve la cantidad de bombas disponibles
	 * 
	 * @return Cantidad de bombas
	*/
	public int getCantidadBombas(){
		return this.cantidadBombas;
	}
	
	/**
	 * Se ejecuta en cada ronda de turnos
	*/
    @Override
    public void jugar() {
    	super.jugar();
    	super.avanzar();
    }
    
    /**
	 * Dispara una bomba dentro del escenario
	*/
    public void dispararBomba(){
    	if(this.getCantidadBombas()>0){
    		Bomba bomba= new Bomba(this);
    		this.getEscenario().addElemento(bomba);
    		this.setCantidadBombas(this.getCantidadBombas()-1);
    	}
    }
    
    /**
	 * Detecta si una tecla es presionada, si es asi, corrige su direccion y angulo de disparo, o dispara
	*/
	@Override
	public void keyPressed (KeyEvent e){
		switch (e.getKeyCode()){
				case KeyEvent.VK_RIGHT: this.setDireccion(0);
										break;
				
				case KeyEvent.VK_LEFT:  this.setDireccion(180);
										break;
				
				case KeyEvent.VK_UP:    this.setDireccion(270);
										break; 
				
				case KeyEvent.VK_DOWN:  this.setDireccion(90);
										break;
										
				case KeyEvent.VK_SPACE: if(this.estaVivo()){
											this.getCanion().setAnguloDisparo(this.getDireccion());
											this.getCanion().disparar();
										}
										break;
				
				case KeyEvent.VK_B:		this.dispararBomba();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
}
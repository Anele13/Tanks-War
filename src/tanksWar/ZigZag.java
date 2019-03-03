package tanksWar;

public class ZigZag extends Tanque{

	private int contadorAvance=0;
	
	/**
	 * Ejecuta cada turno del tanque escaneando primero y luego 
	 * avanzando a su manera.
	 */
	public void jugar() {
		super.jugar();
		if(!(this.getCombustible() <= 0)){
			super.avanzar();
			if (this.contadorAvance==100){
				this.girar(45);
			}
			if (this.contadorAvance==200) {
				this.girar(-90);
			}
			if (this.contadorAvance==300) {
				this.girar(90);
				this.contadorAvance=100;
			}
			this.contadorAvance=this.contadorAvance+1;
		}
	}
	
	/**
	 * Al chocar contra la pared, el tanque gira para seguir avanzando.
	 */
	@Override
	public void chocoContraPared() {
		this.girar(90);
	}

}

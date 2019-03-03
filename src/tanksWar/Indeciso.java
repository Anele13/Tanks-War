package tanksWar;

import java.util.Random;

public class Indeciso extends ZigZag{
	
	private int tiempo=0;
	private boolean quieto=false;
	private boolean disparo=false;
	
	/**
	 * Avanza en zig-zag y cada un cierto tiempo, se detiene y dispara
	 * para luego continuar avanzando de la misma manera
	 * 
	 */
	@Override
	public void jugar() {
		if (!this.quieto){
			super.jugar();
		}
		if (this.tiempo>500) {
			this.quieto=true;
			if (!this.disparo){
				Random Random = new Random();
				int anguloRandom = (int) (Random.nextDouble()*360);
				this.getCanion().setAnguloDisparo(anguloRandom);
				this.getCanion().disparar();
				this.disparo=true;
			}
		}else{
			this.quieto=false;
		}
		this.tiempo=this.tiempo+1;
		if (this.tiempo==600){
			this.tiempo=0;
			this.disparo=false;
		}
	}
}

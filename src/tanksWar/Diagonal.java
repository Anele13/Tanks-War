package tanksWar;

public class Diagonal extends Tanque{

	/**
	 * Constructor de tanque diagonal, su direccion inicia configurada.
	 * 
	 */
	public Diagonal() {
		this.setDireccion(45);
	}
	
	/**
	 * Juega de acuerdo a su implementacion
	 * 
	 */
	@Override
	public void jugar() {
		super.jugar();
		super.avanzar();
	}
	
	/**
	 * se ejecuta si el tanque choca contra una pared
	 * 
	 */
	@Override
	public void chocoContraPared() {
		int resul = (this.getComplemento()*(2));
	
		this.girar(resul);
	}
		
	/**
	 * Devuelve el angulo complementario a la direccion del tanque segun el sentido de giro
	 * 
	 * @return complemento del angulo segun sentido de giro
	 */
	public int getComplemento(){
		int aux= this.getDireccion();
			
		while(aux>90){
			aux=aux-90;
		}
		if(this.getSentidoGiro()==1){
			return 90-aux;
		}
		else{
			return -aux;
		}
	}
	
	/**
	 * Devuelve 1 si el tanque gira en sentido horario. -1 si no.
	 * 
	 * @return 1 si sentido horario. -1 si no.
	 */
	public int getSentidoGiro(){
		int aux = 1;
		int posicionY = (int)this.getPosicion().getY();
		int posicionX = (int)this.getPosicion().getX();
		int direccionTanque = this.getDireccion();
		int altoTablero= this.getEscenario().getConfig().getAltoTablero();
		
		if(direccionTanque >0 && direccionTanque <90 && posicionY==altoTablero){
			aux = -1;
		}
		if(direccionTanque>90 && direccionTanque<180 && posicionX <= 0){
			aux = -1;
		}
		if(direccionTanque>180 && direccionTanque<270 && posicionY==0){
			aux = -1;
		}
		if(direccionTanque>270 && direccionTanque<360 && posicionY>0){
			aux = -1;
		}
		return aux;
	}
}

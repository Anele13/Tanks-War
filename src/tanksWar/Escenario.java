package tanksWar;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Rectangle;
import tanks2015.common.IConfig;
import tanks2015.common.IElemento;
import tanks2015.common.IEscenario;
import tanks2015.common.IJuegoListener;
import tanks2015.common.IRadar;


public class Escenario implements IEscenario {
	
	private ArrayList<IElemento> elementos = new ArrayList<IElemento>();
	private ArrayList<IJuegoListener> listeners = new ArrayList<IJuegoListener>();
	private Config config;
	private Vista vista;
	private boolean nuevoBonus=false;
	private boolean finaliza=false;
	private boolean pausa=false;
	private final int DISTANCIA=10;
	private int contadorBonus=0;
	
	
	/**
	 * Crea un escenario con la configuracion predeterminada	
	 * 	
	 * @throws ConfiguracionNoEncontradaException Excepcion propia de error de archivo de configuracion
	 */
	public Escenario() throws ConfiguracionNoEncontradaException{
		this.config = new Config();
	}
	
	/**
	 * Detecta elementos dentro del area del radar
	 * y retorna los elementos detectados
	 * 
	 * @param radar Radar que detectara los elementos
	 * @return Lista de elementos detecados.
	 */
	@Override
	public ArrayList<IElemento> detectarElementos(IRadar radar) {
		ArrayList<IElemento> elemDetectados = new ArrayList<IElemento>();
		for(int j=0; j<this.elementos.size();j++){
			IElemento elemento = this.elementos.get(j);
			if(elemento != radar.getTanque()){
				if (radar.getAreaCobertura().contains((int)elemento.getPosicion().getX(),(int)elemento.getPosicion().getY())){
					elemDetectados.add(elemento);
				}
			}
		}	
		return elemDetectados;
	}

	/**
	 * Agrega un objeto que escuchará los eventos del juego.
	 * 
	 * @param listener Escuchador del juego
	 */
	@Override
	public void addJuegoListener(IJuegoListener listener) {
		this.listeners.add(listener);
	}

	/**
	 * Elimina un objeto que escucha los eventos del juego.
	 * 
	 * @param listener Escuchador del juego
	 */
	@Override
	public void removeJuegoListener(IJuegoListener listener) {
		this.listeners.remove(listener);
	}

	/**
	 * Agrega un elemento en el escenario.
	 * 
	 * @param elemento Elemento que se desea agregar.
	 */
	@Override
	public void addElemento(IElemento elemento) {
		this.elementos.add(elemento);
	}

	/**
	 * Crea y añade un bonus al azar en un tiempo aleatorio
	 */
	@Override
	public void addBonus() {
	 Random tRandom = new Random();
	  int tiempoRandom = (int)(tRandom.nextDouble()*5000+200);
	  this.contadorBonus=contadorBonus+1;
	  if (this.contadorBonus>=tiempoRandom){
		  this.nuevoBonus=true;
		  this.contadorBonus=0;
	  }
	  Bonus bonus=null;
	  Random tipoRandom = new Random();
	  int tipoBonus = (int)(tipoRandom.nextDouble()*2+1);
	  if(this.nuevoBonus==true){	
		switch (tipoBonus) {
			case 1:	bonus = new BonusCombustible();
					break;
			case 2:	bonus = new BonusEscudo();
					break;
		}
		bonus.getTamanio().setAlto(20);
		bonus.getTamanio().setAncho(20);
		
		Random rndx = new Random();
		Random rndy = new Random();
		int x = (int) (rndx.nextDouble()*config.getAnchoTablero());
		int y = (int) (rndy.nextDouble()*config.getAltoTablero());
		bonus.getPosicion().setX(x);
		bonus.getPosicion().setY(y);
		addElemento(bonus);
		this.nuevoBonus=false;
	  }
	  
	}
	
	/**
	 * Da inicio al juego y proporciona los turnos
	 */
	@Override
	public void iniciarJuego() {
		crearElementos();
		vista.inicializarVentana();
		boolean hayTiempo= true;
		int i=0;
		while(hayTiempo&&!finaliza){
			if (!pausado()){
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.addBonus();            
				turnos();
				depurar();
				actualizar();
				i++;
				if (i==this.getConfig().getDuracionJuego()){
					hayTiempo=false;
				}
			}
		}
	}
		
	/**
	 * Crea los tanques del juego al azar
	 */
	private void crearElementos(){
		Elemento tanque = null;
		for(int i=0;i<5;i++){
			Random rnd = new Random();
			int aux = (int)(rnd.nextDouble()*5+1);
			switch (aux) {
			case 1:
				tanque = new ZigZag();
				agregarCaracteristicas(tanque);
				break;
			case 2:
				tanque = new Bordeador();
				agregarCaracteristicas(tanque);
				break;
			case 3:
				tanque = new Indeciso();
				agregarCaracteristicas(tanque);
				break;
			case 4:
				tanque = new Crazy();
				agregarCaracteristicas(tanque);
				break;
			case 5:
				tanque = new Diagonal();
				agregarCaracteristicas(tanque);
				break;
			}
			Tanque tanq = ((Tanque)tanque);
			tanq.getRadar().addRadarListener(tanq);
			addElemento(tanque);
		} 
	
		//Se crea tanque teclado 
		Teclado tanqueTeclado =new Teclado();
		tanqueTeclado.setCantidadBombas(10);
		agregarCaracteristicas(tanqueTeclado);
		vista.addKeyListener(tanqueTeclado);
		addElemento(tanqueTeclado);
			
	}
	
	/**
	 * Configura un tanque y le asigna una posicion al azar en
	 * el tablero
	 * 
	 * @param tanque1 tanque a configurar
	 */
	public void agregarCaracteristicas(Elemento tanque1){
		Random rndx = new Random();
		Random rndy = new Random();
		int x = (int) (rndx.nextDouble()*config.getAnchoTablero());
		int y = (int) (rndy.nextDouble()*config.getAltoTablero());
		
		Tanque tanque = (Tanque)tanque1;
		tanque.getPosicion().setX(x);
		tanque.getPosicion().setY(y);
		tanque.getTamanio().setAlto(getConfig().getAltoTanque());
		tanque.getTamanio().setAncho(getConfig().getAltoTanque());
		tanque.getRadar().setAnguloApertura(45);
		tanque.setVelocidad(2);
		tanque.setEscenario(this);
	}
		
	/**
	 * Muestra el estado de los elementos
	 */
	private void actualizar() {
		for(IJuegoListener listener : this.listeners)
			listener.mostrarEstado(this.elementos);
	}
	
	/**
	 * Elimina los elementos muertos del juego
	 */
	private void depurar() {
		for(int i=0; i<this.elementos.size();i++){
			if (!this.elementos.get(i).estaVivo()){
				this.elementos.remove(i);
				i=i-1;
			}
		}
	}

	/**
	 * Verifica si un elemento colisiono con otro
	 * @param elemento Elemento a verificar
	 * @param i Posicion del elemento en el arreglo
	 */
	private void verificarColisiones(Elemento elemento, int i) {
		int coordX = (int)elemento.getPosicion().getX();
		int coordY = (int)elemento.getPosicion().getY();
		Rectangle r1 = new Rectangle(coordX,coordY,elemento.getTamanio().getAlto(),elemento.getTamanio().getAncho());
		for(int j=0; j<this.elementos.size();j++){
			if(j != i){
				Elemento elemento2 = (Elemento)this.elementos.get(j);
				int coordX2 = (int)elemento2.getPosicion().getX();
				int coordY2 = (int)elemento2.getPosicion().getY();
				Rectangle r2 = new Rectangle(coordX2,coordY2,elemento2.getTamanio().getAlto(),elemento2.getTamanio().getAncho());
				if (r2.intersects(r1)){
					elemento.impactoContra(elemento2);
				}
			}
		}	
		boolean colisionEste =(coordX>= this.getConfig().getAnchoTablero());
		boolean colisionSur  =(coordY >= this.getConfig().getAltoTablero()); 
		boolean colisionOeste= (coordX<= 0);
		boolean colisionNorte= (coordY <= 0); 
		if((colisionNorte || colisionSur || colisionEste || colisionOeste)){
			if(!(elemento instanceof Diagonal)){
				corregirPosicion(colisionNorte,colisionSur,colisionEste,colisionOeste,elemento);
			}
			elemento.chocoContraPared();
		}
	}
	
	/**
	 * Corrige la posicion de un elemento que choca contra el borde del escenario
	 * 
	 * @param colisionNorte Condicion de limite superior del escenario
	 * 
	 * @param colisionSur Condicion de limite inferior del escenario
	 * 
	 * @param colisionEste Condicion de limite derecho del escenario
	 * 
	 * @param colisionOeste Condicion de limite izquierdo del escenario
	 * 
	 * @param elemento Elemento que colisiona contra alguna pared
	 */
	public void corregirPosicion(boolean colisionNorte,boolean colisionSur, boolean colisionEste, boolean colisionOeste, IElemento elemento){
		
		boolean HayTanquesEspeciales = elemento instanceof Teclado || elemento instanceof Crazy || elemento instanceof ZigZag;
		
		if (colisionEste) {	
			if(HayTanquesEspeciales){
				elemento.getPosicion().setX(0);
			}
			else{
				elemento.getPosicion().setX(elemento.getPosicion().getX()-DISTANCIA);
			}
		}
		else {
			if (colisionOeste) {	
				if(HayTanquesEspeciales){
					elemento.getPosicion().setX(this.getConfig().getAnchoTablero());
				}
				else{
					elemento.getPosicion().setX(elemento.getPosicion().getX()+DISTANCIA);
				}
			}
			else{
				if (colisionNorte) {	
					if(HayTanquesEspeciales){
						elemento.getPosicion().setY(this.getConfig().getAltoTablero());
					}
					else{
						elemento.getPosicion().setY(elemento.getPosicion().getY()+DISTANCIA);
					}
				}
				else{
					if(HayTanquesEspeciales){
						elemento.getPosicion().setY(0);
					}
					else{
						elemento.getPosicion().setY(elemento.getPosicion().getY()-DISTANCIA);
					}
				}	
			}	
		}
	}
	
	
	/**
	 * Proporciona los turnos a cada elemento del arreglo
	 */
	private void turnos(){
		int i;
		for(i=0; i<this.elementos.size();++i){
			Elemento elemento = (Elemento)this.elementos.get(i);
			elemento.jugar();
			verificarColisiones(elemento,i);
		}
	}
	

	/**
	 * Devuelve la configuracion del escenario
	 * 
	 * @return devuelve la configuracion del juego
	 */
	@Override
	public IConfig getConfig() {
		return this.config;
	}

	/**
	 * 	Devuelve si el juego está pausado o no
	 * 	@return Verdadero cuando esta pausado
	 */
	
	@Override
	public boolean pausado() {
		if (pausa){
			return true;
		}
		return false;
	}

	/**
	 * Reanuda el juego
	 */
	@Override
	public void reanudar() {
		this.pausa=false;
	}

	/**
	 * Pausa el juego
	 */
	@Override
	public void pausar() {
		this.pausa=true;
	}

	/**
	 * Termina el juego
	 */
	@Override
	public void terminar() {
		this.finaliza=true;
	}

	/**
	 * Devuelve la lista de elementos del juego
	 * 
	 * @return lista de elementos del juego
	 */
	public ArrayList<IElemento> getElementos() {
		return elementos;
	}	
	
	/**
	 * Recibe la vista asociada al escenario
	 * 
	 * @param vista vista a setear
	 */
	public void setVista(Vista vista) {
		this.vista = vista;
	}
	
}
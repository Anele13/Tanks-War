package tanksWar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import tanks2015.common.IConfig;

public class Config implements IConfig{
	
	private int altoTablero;
	private int anchoTablero;
	private int anchoTanque;
	private int altoTanque;
	private int	anchoMunicion;
	private int altoMunicion;
	private int anchoBonusCombustible;
	private int altoBonusCombustible;
	private int altoBonusEscudo;
	private int anchoBonusEscudo;
	private int duracionJuego;
	private int radarAlcanceMaximo;
	private int tiempoEsperaJuego;
	
	
	/**
	 * Alto del tablero donde se desarrolla el juego en pixels
	 * 
	 * @return Cantidad de pixels
	 */
	@Override
	public int getAltoTablero() {
		return this.altoTablero;
	}

	
	/**
	 * Ancho del tablero donde se desarrolla el juego en pixels
	 * 
	 * @return Cantidad de pixels
	 */
	@Override
	public int getAnchoTablero() {
		return this.anchoTablero;
	}

	/**
	 * Devuelve el alcance máximo de un radar.
	 * 
	 * 
	 * @return Distancia en pixels
	 */
	@Override
	public int getRadarAlcanceMaximo() {
		return this.radarAlcanceMaximo;
	}

	/**
	 * Devuelve el tiempo de espera entre turnos
	 * 
	 * @return Segundos
	 */
	@Override
	public int getTiempoEsperaJuego() {
		return this.tiempoEsperaJuego;
	}

	/**
	 * Devuelve el alto de una municion
	 * 
	 * @return Cantidad de pixels
	 */
	@Override
	public int getAltoMunicion() {
		return this.altoMunicion;
	}

	/**
	 * Devuelve el ancho de una municion
	 * 
	 * @return Cantidad de pixels
	 */
	@Override
	public int getAnchoMunicion() {
		return this.anchoMunicion;
	}

	/**
	 * Devuelve el alto de un tanque
	 * 
	 * @return Cantidad de pixels
	 */
	@Override
	public int getAltoTanque() {
		return this.altoTanque;
	}

	/**
	 * Devuelve el ancho de un tanque
	 * 
	 * @return Cantidad de pixels
	 */
	@Override
	public int getAnchoTanque() {
		return this.anchoTanque;
	}

	/**
	 * Devuelve el alto de un Bonus de Combustible
	 * 
	 * @return Cantidade de pixels
	 */
	@Override
	public int getAltoBonusCombustible() {
		return this.altoBonusCombustible;
	}

	/**
	 * Devuelve el ancho de un Bonus de Combusible
	 * 
	 * @return Cantidad de pixels
	 */
	@Override
	public int getAnchoBonusCombustible() {
		return this.anchoBonusCombustible;
	}

	/**
	 * Devuelve el alto de un Bonus de Escudo
	 * 
	 * @return Cantidad de pixels
	 */
	@Override
	public int getAltoBonusEscudo() {
		return this.altoBonusEscudo;
	}

	/**
	 * Devuelve el ancho de un Bonus de Escudo
	 * 
	 * @return Cantidad de pixels
	 */
	@Override
	public int getAnchoBonusEscudo() {
		return this.anchoBonusEscudo;
	}

	/**
	 * Devuelve el tiempo de duracion del juego
	 * 
	 * @return Duracion del juego
	 */
	@Override
	public int getDuracionJuego() {
		return duracionJuego;
	}
	
	/**
	 * Constructor de configuracion
	 * 
	 * @throws ConfiguracionNoEncontradaException Excepcion propia de aviso de error en archivo de configuracion
	 */
	public Config() throws ConfiguracionNoEncontradaException {
		Properties archivoConfig = new Properties();
			try {
				archivoConfig.load(new FileInputStream(System.getProperty("user.dir") + File.separator + "Configuracion Juego" + File.separator + "config.txt"));
				cargarConfiguracion(archivoConfig);
			} 
			catch (IOException e) {
				throw new ConfiguracionNoEncontradaException("Error: archivo de configuracion no encontrado");
			}
	}
	
	/**
	 * Carga la configuracion del juego desde un archivo de texto plano
	 * 
	 * @param archivoConfig Referencia al archivo de configuracion del juego
	 */
	public void cargarConfiguracion(Properties archivoConfig){
		
		this.altoTablero = Integer.parseInt(archivoConfig.getProperty("altoTablero"));
		this.anchoTablero= Integer.parseInt(archivoConfig.getProperty("anchoTablero"));
		this.anchoTanque = Integer.parseInt(archivoConfig.getProperty("anchoTanque"));
		this.altoTanque = Integer.parseInt(archivoConfig.getProperty("altoTanque"));
		this.anchoMunicion = Integer.parseInt(archivoConfig.getProperty("anchoMunicion"));
		this.altoMunicion = Integer.parseInt(archivoConfig.getProperty("altoMunicion"));
		this.anchoBonusCombustible= Integer.parseInt(archivoConfig.getProperty("anchoBonusCombustible"));
		this.altoBonusCombustible = Integer.parseInt(archivoConfig.getProperty("altoBonusCombustible"));
		this.altoBonusEscudo  = Integer.parseInt(archivoConfig.getProperty("altoBonusEscudo"));
		this.anchoBonusEscudo = Integer.parseInt(archivoConfig.getProperty("anchoBonusEscudo"));
		this.duracionJuego = Integer.parseInt(archivoConfig.getProperty("duracionJuego"));
		this.radarAlcanceMaximo= Integer.parseInt(archivoConfig.getProperty("radarAlcanceMaximo"));
		this.tiempoEsperaJuego = Integer.parseInt(archivoConfig.getProperty("tiempoEsperaJuego"));
	}
}
package tanksWar;

import tanks2015.common.IElemento;
import tanks2015.common.IJuegoListener;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Polygon;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class Vista extends Canvas implements IJuegoListener{
	
	private static final long serialVersionUID = 1L;
	private JFrame ventana = new JFrame("POO  - Tanks War 2015");
	private PanelTanque pt;
	private ArrayList<PanelTanque> paneles = new ArrayList<PanelTanque>();
	private BufferStrategy strategy;
	private static HashMap<String, BufferedImage> imagenes = new HashMap<String, BufferedImage>();
	private Escenario escenario;
	private BufferedImage fondo;
	
	
	
	/**
	 * Vincula la vista con el escenario
	 * 
	 * @param escenario Escenario a vincular
	 */
	public void setEscenario(Escenario escenario){
		this.escenario = escenario;
	}
	
	/**
	 * Constructor de la clase
	 * 
	 * @param escenario Escenario que se desea vincular
	 */
	public Vista(Escenario escenario){
		this.setEscenario(escenario);
		setBackground((Color.blue));
		setLocation(0, 0);
	}
	
	/**
	 * Inicializa la ventana del juego,con todos los valores necesarios para el desarrollo
	 */
	public void inicializarVentana(){
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);	
		Container contenedor = ventana.getContentPane();
		contenedor.setLayout(null);		
		contenedor.add(this);
		int anchoTablero = escenario.getConfig().getAnchoTablero();
		int altoTablero = escenario.getConfig().getAltoTablero();
		
		this.setSize(anchoTablero, altoTablero); //esta es la ventada donde se juega sin panel de estado
		
		ArrayList<IElemento> elementos = this.escenario.getElementos();
		int contadorTanques=0;
		for (int i = 0; i < elementos.size(); i++) {
			IElemento elemento = elementos.get(i);
			if (elemento instanceof Tanque) {
				Tanque tanque = (Tanque) elemento;
				pt = new PanelTanque(tanque);
				pt.setBounds(443, 5+(70*contadorTanques), 334, 66);
				ventana.getContentPane().add(pt);
				paneles.add(pt);
			}
		contadorTanques=contadorTanques+1;
		}
		this.setFocusable(true);
		this.requestFocusInWindow();
		
		agregarMenu(ventana);
		ventana.setSize(820, 500);
		
		ventana.setPreferredSize(ventana.getSize());
		ventana.setBackground(Color.WHITE);		
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);
		this.createBufferStrategy(2);
		this.strategy = this.getBufferStrategy();
		}
	
	/**
	 * Agrega una bara de menu con botones para el control del juego
	 * 
	 * @param frame Ventana del juego
	 */
	public void agregarMenu(JFrame frame){
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Juego");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmPausar = new JMenuItem("Pausar");
		mntmPausar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				escenario.pausar();
			}
		});
		mnNewMenu.add(mntmPausar);
		
		JMenuItem mntmReanudar = new JMenuItem("Reanudar");
		mntmReanudar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				escenario.reanudar();
			}
		});		
		
		mnNewMenu.add(mntmReanudar);
		
		JMenuItem mntmTerminar = new JMenuItem("Terminar");
		mntmTerminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				escenario.terminar();
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmTerminar);
		
	}
	
	/**
	 * Busca la imagen de fondo para el juego
	 * 
	 * @return Imagen del fondo
	 */
	public BufferedImage buscaFondo(){
		if (this.fondo == null)
			fondo = cargarImagen(System.getProperty("user.dir") + File.separator + "img" + File.separator + "fondo.JPG");
	 	return fondo;
	}
	
	/**
	 * Muestra el desarrollo del juego en una ventana, interacciones y estado de los elementos
	 * 
	 * @param elementos Elementos del juego
	 */
	public void mostrarEstado(ArrayList<IElemento> elementos){
		for (int i = 0; i < paneles.size(); i++) {
			paneles.get(i).actualizar();
		}
		if (this.getG2D() == null)
			return;
		limpiar();
		int contTanque=0;
		for (int i = 0; i < elementos.size(); i++) {
			Elemento elemento = (Elemento) elementos.get(i);
			BufferedImage img = getImagen(elemento);
			Polygon poligono = null;
			img=cambiarTamanio(elemento.getTamanio().getAlto(),elemento.getTamanio().getAncho(), img);
			
			if(elemento instanceof Tanque){
				Tanque tanque = (Tanque)elemento;
				poligono= tanque.getRadar().getAreaCobertura();
				this.setForeground(Color.red);
				this.getG2D().fillPolygon(poligono);
				img=rotar(img, tanque.getDireccion()-180);
				contTanque=contTanque+1;
			}
			if (img != null){
				this.getG2D().drawImage(img,(int) elemento.getPosicion().getX(), (int)elemento.getPosicion().getY(), this);
			}
			else { 
				System.out.println("No se encontro la imagen");
			}
		}
		strategy.show();
	}
	
	
	/**
	 * Devuelve el grafico para dibujar el escenario
	 * 
	 * @return grafico para dibujar
	 */
	public Graphics2D getG2D(){
		try
		{
			return (Graphics2D)strategy.getDrawGraphics();
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
 

	/**
	 * Refresca la pantalla luego de cada dibujo
	 */
	public void limpiar(){
		this.getG2D().clearRect(0, 0, getWidth(), getHeight());
		this.getG2D().drawImage(buscaFondo(),0,0, getWidth(),getHeight(),this);
	}
	
	
	/**
	 * Devuelve la imagen de un elemento del juego
	 *  
	 * @param elemento Elemento al que se desea asociar la imagen
	 * @return Imagen del elemento
	 */
	public BufferedImage getImagen(Elemento elemento){
		String fileName = elemento.getClass().getSimpleName() + ".png";
		BufferedImage img = (BufferedImage)imagenes.get(fileName);
		if (img == null) {
			img = cargarImagen(System.getProperty("user.dir") + File.separator + "img" + File.separator + fileName);
			if (img != null){
				imagenes.put(fileName, img);
			}
		}
		return img;
	}
	

	/**
	 * Carga la imagen de un elemento, desde una ruta especifica
	 * 
	 * @param ruta String que contiene la ruta de la imagen
	 * 
	 * @return imagen cargada
	 */
	public BufferedImage cargarImagen(String ruta) {
		try {
			return ImageIO.read(new File(ruta));
		} catch (Exception e) {
			System.out.println("No se encontro la imagen " + ruta);
			return null;
		}
	}
	
	/**
	 * Rota un elemento del juego
	 * 
	 * @param img Imagen a rotar
	 * @param angulo Angulo que se desea rotar la imagen
	 */
	private static BufferedImage rotar(BufferedImage image, int angulo){
		Dimension dim = calcularDimension(image, angulo);
		int w2 = (int)dim.getWidth();
		int h2 = (int)dim.getHeight();		
		int w = image.getWidth();
		int h = image.getHeight();		
		BufferedImage image2 = new BufferedImage(w2, h2, BufferedImage.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D)image2.getGraphics();
		double x = (w2- w) / 2.0;
		double y = (h2-h) / 2.0;
		AffineTransform at = AffineTransform.getTranslateInstance(x, y);
		at.rotate(Math.toRadians(angulo), w/2, h/2);
		g2d.drawRenderedImage(image, at);
		return image2;
	}
	
	/**
	 * Cambia el tamaño de una imagen del juego
	 * 
	 * @param ancho Ancho de la imagen
	 * @param alto Alto de la imagen
	 * @param img Imagen a rotar
	 */
	private BufferedImage cambiarTamanio(int ancho, int alto, BufferedImage img){
		BufferedImage img2 = new BufferedImage(ancho, alto, BufferedImage.TRANSLUCENT);
		Graphics2D g2d = img2.createGraphics();
		g2d.drawImage(img, 0, 0, img2.getWidth(), img2.getHeight(), null);
		return img2;
	}
	
	/**
	 * Calcula la dimension de un elemento
	 * 
	 * @param img Imagen para calcular
	 * @param angulo angulo de la imagen
	 */
	private static Dimension calcularDimension(BufferedImage img, int angulo){
		double w = (double)img.getWidth();
		double h = (double)img.getHeight();
		
		double x1 = Math.abs(w * Math.cos(Math.toRadians(angulo))); 
		double x2 = Math.abs(h * Math.sin(Math.toRadians(angulo)));
		
		double y1 = Math.abs(h * Math.cos(Math.toRadians(angulo))); 
		double y2 = Math.abs(w * Math.sin(Math.toRadians(angulo)));
		
		return new Dimension((int)(x1+x2), (int)(y1+y2));
	}
}	
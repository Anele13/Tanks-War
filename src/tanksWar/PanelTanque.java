package tanksWar;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tanks2015.common.ITanque;


public class PanelTanque extends JPanel {

	private static final long serialVersionUID = 1L;
	private ITanque tanque;
	private JLabel textoCombustible ;
	private JLabel textoEscudo ;
	private JLabel textoPosicion ;
	private JLabel textoDireccion ;
	private JLabel textoBomba;

	/**
	 * Crea secciones de texto individuales para mostrar el estado de un tanque durante la ejecucion del juego
	 *
	 *@param tanque tanque para crear secciones
	 */
	public PanelTanque(ITanque tanque) {
		super();
		setLayout(null);
		this.tanque = tanque;
		
		if(tanque instanceof Teclado){
			JLabel bombaImg = new JLabel();
			bombaImg.setIcon(new ImageIcon(System.getProperty("user.dir") + File.separator + "img" + File.separator + "Bomba.png"));
			bombaImg.setBounds(244, 10, 15, 28);
			this.add(bombaImg);
			
			textoBomba = new JLabel();
			textoBomba.setBounds(260, 22, 34, 17);
			this.add(textoBomba);
		}
		JLabel combustibleImg = new JLabel();
		combustibleImg.setIcon(new ImageIcon(System.getProperty("user.dir") + File.separator + "img" + File.separator + "BonusCombustible.png"));
		combustibleImg.setBounds(195, 11, 15, 28);
		this.add(combustibleImg);
		
		JLabel escudoImg = new JLabel();
		escudoImg.setIcon(new ImageIcon(System.getProperty("user.dir") + File.separator + "img" + File.separator + "Escudo.png"));
		escudoImg.setBounds(92, 9, 19, 30);
		this.add(escudoImg);
		
		JLabel posicionImg = new JLabel();
		posicionImg.setIcon(new ImageIcon(System.getProperty("user.dir") + File.separator + "img" + File.separator +"Posicion.png"));
		posicionImg.setBounds(276, 9, 15, 30);
		this.add(posicionImg);
		
		JLabel direccionImg = new JLabel();
		direccionImg.setIcon(new ImageIcon(System.getProperty("user.dir") + File.separator + "img" + File.separator + "Direccion.png"));
		direccionImg.setBounds(142, 11, 15, 30);
		this.add(direccionImg);
		
		JLabel iconoTanqueImg = new JLabel();
		iconoTanqueImg.setIcon(new ImageIcon(System.getProperty("user.dir") + File.separator + "img" + File.separator + "Panel"+tanque.getClass().getSimpleName()+".png"));
		iconoTanqueImg.setBounds(10, 11, 50, 30);
		this.add(iconoTanqueImg);

		JLabel nombreTanque = new JLabel();
		nombreTanque.setText(tanque.getClass().getSimpleName()+"\r\n");
		nombreTanque.setBounds(10, 44, 91, 17);
		add(nombreTanque);

		textoCombustible = new JLabel();
		textoCombustible.setBounds(209, 22, 34, 17);
		this.add(textoCombustible);
		
		textoEscudo = new JLabel();
		textoEscudo.setBounds(110, 22, 27, 17);
		this.add(textoEscudo);
		
		textoPosicion = new JLabel();
		textoPosicion.setBounds(287, 22, 72, 17);
		this.add(textoPosicion);

		textoDireccion = new JLabel();
		textoDireccion.setBounds(157, 22, 27, 17);
		this.add(textoDireccion);
		
	}
	
	/**
	 * Actualiza el estado de los campos de texto pretenecientes a cada tanque
	 */
	public void actualizar(){
		if(tanque instanceof Teclado){
			Teclado tanqueAux = (Teclado)tanque;
			textoBomba.setText(""+tanqueAux.getCantidadBombas());
		}
		textoCombustible.setText("" +tanque.getCombustible());
		textoEscudo.setText(""+tanque.getEscudo());
		textoPosicion.setText("" + (int)tanque.getPosicion().getX() + ", " + (int)tanque.getPosicion().getY());
		textoDireccion.setText(""+ tanque.getDireccion());		
	}
}

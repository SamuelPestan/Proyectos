import java.util.Random;

/**
 * Esta es una clase que imita las cualidades de un dado. Se usa para generar resultados aleatorios.
 * 
 * @author Samuel A. Moreno Pestana
 * @version 1.8 
 */
public class Dado {
	
	private int caras;

	/**
	 * Dado por defecto (6 caras).
	 */
	public Dado() {
		this.caras = 6;
	}
	
	/**
	 * Constructor parametrizado que permite la creación de un dado con un número n de caras.
	 * 
	 * @param caras Caras del dado.
	 */
	public Dado(int caras) {
		this.caras = caras;
	}
	
	/**
	 * Metodo que permite obtener las caras del dado que se esta usando.
	 * 
	 * @return Caras del dado.
	 */
	public int getCaras() {
		return caras;
	}

	/**
	 * Método que permite asignarle el número de caras al objeto dado.
	 * 
	 * @param caras Caras que se le asignarán al dado.
	 */
	public void setCaras(int caras) {
		this.caras = caras;
	}
	
	/**
	 * Método que genera un número aleatorio ente 1 y la cantidad de caras del dado.
	 * @return
	 */
	public int lanzarDado() {
		Random rnd = new Random();
		return rnd.nextInt(caras)+1;
	}
}

import java.util.Random;

/**
 * La clase Zombie representa un enemigo dentro del juego
 */
public class Zombie {

	// Variables del Zombie
	private int vidaZombie;
	private int ataqueZombie;
	
	/**
	 * Constructor por defecto del Zombie.
	 * Tendrá en cuenta la habitación en el que el jugador se encuentre para determinar su nivel.
	 * 
	 * @param habitacion Número de habitación.
	 */
	public Zombie(int habitacion) {	
		Random random = new Random();
		vidaZombie = random.nextInt(2) + 2 + (habitacion - 1); 
		ataqueZombie = random.nextInt(2) + 2 + (habitacion - 1); 
	}
	
	/**
	 * Constructor parametrizado del Zombie.
	 * 
	 * @param vidaZombie Vida del Zombie.
	 * @param ataqueZombie Ataque del Zombie.
	 */
	public Zombie(int vidaZombie, int ataqueZombie) {
		this.vidaZombie = vidaZombie;
		this.ataqueZombie = ataqueZombie;
	}
	
	/**
	 * Getter que devuelve la vida del zombie.
	 * 
	 * @return Vida del zombie.
	 */
	public int getVidaZombie() {
		return vidaZombie;
	}
	
	/**
	 * Setter que le asigna la vida al zombie.
	 * 
	 * @param vidaZombie Vida del zombie.
	 */
	public void setVidaZombie(int vidaZombie) {
		this.vidaZombie = vidaZombie;
	}
	
	/**
	 * Getter que devuelve el valor de ataque del zombie.
	 * 
	 * @return valor de ataque del zombie.
	 */
	public int getAtaqueZombie() {
		return ataqueZombie;
	}
	/**
	 * Setter que asigna el valor de ataque del zombie.
	 * 
	 * @param ataqueZombie Valor de ataque del zombie.
	 */
	public void setAtaqueZombie(int ataqueZombie) {
		this.ataqueZombie = ataqueZombie;
	}
	
	//Otras opciones
	
	/**
	 * Metodo que devuelve un valor aleatorio entre 1 y el valor de ataque del Zombie.
	 * 
	 * @return Daño realizado por el zombie.
	 */
	public int ataqueZombie() {
		Dado npcDado = new Dado(ataqueZombie);
		return npcDado.lanzarDado();
	}
	
	/**
	 * Metodo que reduce el valor de vida del zombie en función del daño que recibe por parametro.
	 * 
	 * @param dano Valor de daño recibido por el zombie.
	 */
	public void recibirDanoZombie(int dano) {
		vidaZombie = vidaZombie - dano;
		if(vidaZombie <= 0)
			vidaZombie = 0;
	}
}

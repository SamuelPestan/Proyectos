import java.util.Random;

public class Zombie {
	private int vidaZombie;
	private int ataqueZombie;
	
	//Constructor por defecto
	public Zombie(int habitacion) {		//Declaro la variable int dentro de la public class para que varie según la habitación actual
		Random random = new Random();
		vidaZombie = random.nextInt(2) + 2 + (habitacion - 1); 
		ataqueZombie = random.nextInt(2) + 2 + (habitacion - 1); 
	}
	
	//Constructor parametrizado
	public Zombie (int vidaZombie, int ataqueZombie) {
		this.vidaZombie = vidaZombie;
		this.ataqueZombie = ataqueZombie;
	}
	
	// Getter
	public int getVidaZombie() {
		return vidaZombie;
	}
	
	public int getAtaqueZombie() {
		return ataqueZombie;
	}
	
	// Setter
	public void setVidaZombie (int vidaZombie) {
		this.vidaZombie = vidaZombie;
	}
	
	public void setAtaqueZombie (int ataqueZombie) {
		this.ataqueZombie = ataqueZombie;
	}
}

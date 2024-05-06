import java.util.Random;

public class Dado {
	int caras;
	
	// Constructor por defecto
	public Dado() {
		Random random = new Random();
		caras = random.nextInt(6) + 1;
	}
	
	// Constructor parametrizado
	public Dado(int caras) {
		Random random1 = new Random();
		this.caras = random1.nextInt(caras) + 1;
	}
	
	// Getter
	public int getCaras() {
		return caras;
	}
	
	// Setter
	public void setCaras (int caras) {
		this.caras = caras;
	}
	
	// Lanzar dado
	public int lanzarDado() {
		Random random = new Random();
		int lanzamiento = random.nextInt(caras) + 1;
		return lanzamiento;
	}
}

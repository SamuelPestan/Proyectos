import java.util.Random;

public class Dado {
	
	private int caras;

	public Dado() {
		this.caras = 6;
	}
	
	public Dado(int caras) {
		this.caras = caras;
	}
	
	public int getCaras() {
		return caras;
	}

	public void setCaras(int caras) {
		this.caras = caras;
	}
	
	public int lanzarDado() {
		Random rnd = new Random();
		return rnd.nextInt(caras)+1;
	}
}

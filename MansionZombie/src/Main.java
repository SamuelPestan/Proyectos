import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//Comentarios iniciales
		System.out.println("Bienvenido a la mansión Zombie\n");
		System.out.println("Elige la dificultad: ");
		System.out.println("1 - fácil.");
		System.out.println("2 - dificil.");

		//Inicio un teclado para pedir la dificultad
		Scanner teclado = new Scanner(System.in);
		try {
			int dificultad = teclado.nextInt();
			
			if (dificultad == 1) {
				System.out.println("Dificultad fácil.\n");
				Juego miJuego = new Juego(5, 3, 1, 1);
				miJuego.combate();
				
				
				
			}else if(dificultad == 2) {
				System.out.println("Dificultad dificil. Has elegido sufrir.\n");
				Juego miJuego = new Juego(10,3,1,1);	//Parametrizar el constructo
				miJuego.combate();
				
				
			}else {
				System.out.println("Debe ingresar 1 o 2.");
			}
		}catch(Exception e){
			System.out.println("Debe ingresar un número.");
		}
		
		
		teclado.close();
	}
	 
	 

}

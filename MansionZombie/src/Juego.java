import java.util.Scanner;

public class Juego {
	private int numeroHabitacion;
    private int intentosBusqueda;
    private int habitacionActual;
    private int numeroZombiesActivos;

    // Constructor por defecto
    public Juego() {
        this.numeroHabitacion = 5;
        this.intentosBusqueda = 3;
        this.habitacionActual = 1;
        this.numeroZombiesActivos = 1;
    }
    
    // Constructor parametrizado
    public Juego(int numeroHabitacion, int intentosBusqueda, int habitacionActual, int numeroZombiesActivos) {
    	this.numeroHabitacion = numeroHabitacion;
    	this.intentosBusqueda = intentosBusqueda;
    	this.habitacionActual = habitacionActual;
    	this.numeroZombiesActivos = numeroZombiesActivos;
    }
    
    // Getter
    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public int getIntentosBusqueda() {
        return intentosBusqueda;
    }

    public int getHabitacionActual() {
        return habitacionActual;
    }

    public int getNumeroZombiesActivos() {
        return numeroZombiesActivos;
    }
    
    // Setter
    public void setNumeroHabitacion (int numeroHabitacion) {
    	this.numeroHabitacion = numeroHabitacion;
    }
    
    public void setIntentosBusqueda (int intentosBusqueda) {
    	this.intentosBusqueda = intentosBusqueda;
    }
    
    public void setHabitacionActual (int habitacionActual) {
    	this.habitacionActual = habitacionActual;
    }
    
    public void setNumeroZombiesActivos (int numeroZombiesActivos) {
    	this.numeroZombiesActivos = numeroZombiesActivos;
    }
    
    //Buscar 
    public void buscarHabitacion() {
    	Dado miDado0 = new Dado(100);
    	int lanzarDado = miDado0.getCaras();
    	
		Superviviente jugador = new Superviviente();
    	
    	if (lanzarDado > 1 && lanzarDado < 75) {
    		lanzarDado = miDado0.getCaras();
    		System.out.println("Haces ruido");
    			if(lanzarDado > 1 && lanzarDado < 40){
    				System.out.println("No encuentras nada");
    				
    			}else if (lanzarDado > 41 && lanzarDado < 80) {
    				System.out.println("Entra un zombie a la habitación");
    				numeroZombiesActivos = 1;
    				
    			}else if(lanzarDado > 81 && lanzarDado < 100){
    				System.out.println("Entran dos zombies a la habitación");
    				numeroZombiesActivos = 2;
    			}
    			
    }else if (lanzarDado > 76 && lanzarDado < 90) {
    		System.out.println("Has encontrado un botiquin");
    		jugador.getTieneBotiquin(true);
    		
    		
    	}else if (lanzarDado > 91 && lanzarDado < 95) {
    		System.out.println("Has encontrado una protección");
    		jugador.setCantidadProteccion(jugador.getCantidadProteccion() + 1);
    		
    		
    	}else if (lanzarDado > 95 && lanzarDado < 100) {
    		System.out.println("Has encontrado un arma");
    		jugador.setCantidadArmas(jugador.getCantidadArmas() + 1);
    	}
    } // Fin del apartado buscar
    
    
    // Combate
    public void combate() {
    	Scanner teclado = new Scanner(System.in);
    	
    	System.out.println("----------------------------------------");
		Juego miJuego = new Juego(numeroHabitacion,  intentosBusqueda,  habitacionActual,  numeroZombiesActivos);		//Parametrizar el constructo
		System.out.println("El número de habitaciones es " + miJuego.getNumeroHabitacion());
		//Llamar a la calse superviviente
		Superviviente jugador = new Superviviente();

		// Acceder a los atributos
		System.out.println("Tu vida actual es " + jugador.getVidaActual());
		System.out.println("Tu daño es " + jugador.getPuntosAtaques());
		System.out.println("----------------------------------------\n");
		Zombie miZombie = new Zombie(miJuego.getHabitacionActual());
		
		// Sistema de combate
		int playerVida = jugador.getVidaActual();
		int npcVida = miZombie.getVidaZombie();
		int habitacionActual = miJuego.getHabitacionActual();
		while (playerVida > 0 && npcVida > 0  && habitacionActual < 6) {
			System.out.println("La habitación " + miJuego.getHabitacionActual() + " tiene " + miJuego.getNumeroZombiesActivos() + " Zombies\n");
			try {
				// Opción para el jugador: "luchar"
                System.out.println("Elige una opción:");
                System.out.println("1. Luchar");
                		  
                int opcion = teclado.nextInt();
                if (opcion == 1) {	
                	
	                Dado miDado =new Dado(4);
	                Dado miDado2 =new Dado(miZombie.getAtaqueZombie());			              
	               
	                //Atacar al Zombie
	                npcVida = miZombie.getVidaZombie() - miDado.getCaras();			                
	             
	                if (npcVida > 0) {	//Si el zombie sobrevive atacara al jugador
	                	playerVida = jugador.getVidaActual() - miDado2.getCaras();
	                	System.out.println("Tu vida es " + playerVida);
	                }else {
	                	System.out.println("Has derrotado al Zombie");
	                	int numeroZombie = miJuego.getNumeroZombiesActivos() - 1;
	                	System.out.println("Quedan " + numeroZombie + " Zombies");
	                	
	                	if (numeroZombie == 0) {
	                		habitacionActual++;
	                	}
	                }
	                
	               System.out.println("Elige una opción:");
	               System.out.println("2. Buscar por la habitación (3 intentos)");
	               System.out.println("3. Avanzar a otra habitación");
	               
	               opcion =teclado.nextInt();
	            
	                
                }else {
                	System.out.println("Debes selecionar 1 para atacar.");
                }
                   
			}catch (java.util.InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número.");
                teclado.nextLine(); // Limpiar el búfer del teclado
            }
			teclado.close();
                
                
		}	// Fin del sistema de combates
    }
            
            
		
}


	

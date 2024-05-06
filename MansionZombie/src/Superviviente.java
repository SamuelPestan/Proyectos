
public class Superviviente {
	private int vidaActual;
	private int puntosAtaques;
	private boolean tieneBotiquin;
	private int cantidadArmas;
	private int cantidadProteccion;
	
	// Constructor por defecto
    public Superviviente() {
        vidaActual = 20;
        puntosAtaques = 4 + cantidadArmas; // (4 + armas)
        tieneBotiquin = false;
        cantidadArmas = 0;
        cantidadProteccion = 0;
    }
    
    // Constructor parametrizado
    public Superviviente (int vidaActual, int puntosAtaques, boolean tieneBotiquin, int cantidadArmas, int cantidadProteccion) {
    	this.vidaActual = vidaActual;
    	this.puntosAtaques = puntosAtaques + cantidadArmas;
    	this.tieneBotiquin = tieneBotiquin;
    	this.cantidadArmas = cantidadArmas;
    	this.cantidadProteccion = cantidadProteccion;
    }

    // Getter
    public int getVidaActual() {
        return vidaActual;
    }

    public int getPuntosAtaques() {
        return puntosAtaques;
    }

    public boolean getTieneBotiquin(boolean b) {
        return tieneBotiquin;
    }

    public int getCantidadArmas() {
        return cantidadArmas;
    }

    public int getCantidadProteccion() {
        return cantidadProteccion;
    }
    
   //Setter
   public void setVidaActual (int vidaActual) {
	   this.vidaActual = vidaActual;
   }
   
   public void setPuntosAtaques (int puntosAtaques) {
	   this.puntosAtaques = puntosAtaques;
   }
   
   public void setTieneBotiquin (boolean tieneBotiquin) {
	   this.tieneBotiquin = tieneBotiquin;
   }
   
   public void setCantidadArmas (int cantidadArmas) {
	   this.cantidadArmas = cantidadArmas;
   }
   
   public void setCantidadProteccion (int cantidadProteccion) {
	   this.cantidadProteccion = cantidadProteccion;
   }
   
   //Otras opciones
}


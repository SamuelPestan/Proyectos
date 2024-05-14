import java.io.Serializable;

/**
* La clase Superviviente representa a un jugador.
* 
*  @author Samuel A. Moreno Pestana
* @version 1.8
*/
public class Superviviente implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int vidaActual; // Vida actual del superviviente
    private int puntosAtaques; // Puntos de ataque del superviviente
    private boolean tieneBotiquin; // Indica si el superviviente tiene un botiquín
    private int cantidadArmas; // Cantidad de armas que posee el superviviente
    private int cantidadProteccion; // Nivel de protección del superviviente
 
    /**
     * Constructor por defecto de la clase.
     * Inicializa los atributos con valores predeterminados.
     */
    public Superviviente() {
        this.vidaActual = 20;
        this.puntosAtaques = 4;
        this.tieneBotiquin = false;
        this.cantidadArmas = 0;
        this.cantidadProteccion = 0;
    }
 
    /**
     * Constructor que recibe valores para inicializar los atributos.
     * 
     * @param vidaActual La vida actual del superviviente.
     * @param puntosAtaques Los puntos de ataque del superviviente.
     * @param tieneBotiquin Indica si el superviviente tiene un botiquín.
     * @param cantidadArmas La cantidad de armas que posee el superviviente.
     * @param cantidadProteccion El nivel de protección del superviviente.
     */
    public Superviviente(int vidaActual, int puntosAtaques, boolean tieneBotiquin, int cantidadArmas,
                         int cantidadProteccion) {
        this.vidaActual = vidaActual;
        this.puntosAtaques = puntosAtaques;
        this.tieneBotiquin = tieneBotiquin;
        this.cantidadArmas = cantidadArmas;
        this.cantidadProteccion = cantidadProteccion;
    }

    // Métodos getter y setter para acceder y modificar los atributos
 
    /**
     * Obtiene la vida actual del superviviente.
     * 
     * @return La vida actual del superviviente.
     */
    public int getVidaActual() {
        return vidaActual;
    }
 
    /**
     * Establece la vida actual del superviviente.
     * @param vidaActual La nueva vida actual del superviviente.
     */
    public void setVidaActual(int vidaActual) {
        this.vidaActual = vidaActual;
    }
 
    /**
     * Obtiene los puntos de ataque del superviviente.
     * 
     * @return Los puntos de ataque del superviviente.
     */
    public int getPuntosAtaques() {
        return puntosAtaques;
    }
 
    /**
     * Establece los puntos de ataque del superviviente.
     * 
     * @param puntosAtaques Los nuevos puntos de ataque del superviviente.
     */
    public void setPuntosAtaques(int puntosAtaques) {
        this.puntosAtaques = puntosAtaques;
    }
 
    /**
     * Verifica si el superviviente tiene un botiquín.
     * 
     * @return true si el superviviente tiene un botiquín, false de lo contrario.
     */
    public boolean getTieneBotiquin() {
        return tieneBotiquin;
    }
 
    /**
     * Establece si el superviviente tiene un botiquín.
     * 
     * @param tieneBotiquin true si el superviviente tiene un botiquín, false de lo contrario.
     */
    public void setTieneBotiquin(boolean tieneBotiquin) {
        this.tieneBotiquin = tieneBotiquin;
    }
 
    /**
     * Obtiene la cantidad de armas que posee el superviviente.
     * 
     * @return La cantidad de armas del superviviente.
     */
    public int getCantidadArmas() {
        return cantidadArmas;
    }
 
    /**
     * Establece la cantidad de armas que posee el superviviente.
     * 
     * @param cantidadArmas La nueva cantidad de armas del superviviente.
     */
    public void setCantidadArmas(int cantidadArmas) {
        this.cantidadArmas = cantidadArmas;
    }
 
    /**
     * Obtiene el nivel de protección del superviviente.
     * @return El nivel de protección del superviviente.
     */
    public int getCantidadProteccion() {
        return cantidadProteccion;
    }
 
    /**
     * Establece el nivel de protección del superviviente.
     * 
     * @param cantidadProteccion El nuevo nivel de protección del superviviente.
     */
    public void setCantidadProteccion(int cantidadProteccion) {
        this.cantidadProteccion = cantidadProteccion;
    }
 
    // Otras funcionalidades
 
    /**
     * Realiza un ataque del superviviente lanzando un dado.
     * 
     * @return El resultado del lanzamiento del dado.
     */
    public int ataqueSuperviviente() {
        Dado miDado = new Dado(puntosAtaques);
        if(cantidadArmas > 0) {
        	return miDado.lanzarDado() + cantidadArmas;
        }else {
        	return miDado.lanzarDado();
        }
        
    }
 
    /**
     * El superviviente recibe un cierto daño y actualiza su vida en consecuencia,
     * considerando su nivel de protección.
     * 
     * @param dano El daño recibido por el superviviente.
     */
    public void recibirDanoJugador(int dano) {
        if(dano < cantidadProteccion) {
            dano = 0;
        } else {
            dano -= cantidadProteccion;
        }
        vidaActual -= dano;
 
        if (vidaActual < 0) {
            vidaActual = 0;
        }
    }
    
    /**
     * El superviviente recibira 4 unidades de vida, lo cual permite que se cure.
     * El metodo se asegura de que el jugador no supere su cantidad de vida máxima.
     */
    public void curarse() {
    	vidaActual += 4;
    	if(vidaActual > 20) {
    		vidaActual = 20;
    	}
    	tieneBotiquin=false;
    }
 
}
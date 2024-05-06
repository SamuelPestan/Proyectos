package LecturaDeCaracteres;

import java.io.*;

public class LecturaDeCaracteres {

	public static void main(String[] args) {
		LecturaDeCaracteres main = new LecturaDeCaracteres();
		main.lecturaArchivo("src"+File.separator+"files"+File.separator+"Ej1_Fichero1", 
				"src"+File.separator+"files"+File.separator+"Ej1_Fichero2", false);
	}
	
	public void lecturaArchivo(String nombreArchivo1, String nombreArchivo2, boolean append) {
		File archivo = new File(nombreArchivo1);
		File archivo2 = new File(nombreArchivo2);
		int cantNum = 0;
		try {
			// Abrir los fichero
			FileReader lector = new FileReader(archivo);
			BufferedWriter  escritor = new BufferedWriter(new FileWriter(archivo2,append));
			// Leer el fichero
			int linea = lector.read();
			while(linea != -1) {
				// Escribir los carácteres del fichero 1
				escritor.write((char)linea);
				if(Character.isDigit((char)linea)) {
					// Si el carácter es númerico entonces aumentará el contador
					cantNum++;
				}
				
				linea = lector.read();
				
			}
			
			// Cerrar el reader
			lector.close();
			//Cerrar el writer
			escritor.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Mostrar por consola
		System.out.println("El archivo tiene: " + cantNum + " carácteres númericos.");

	}

}

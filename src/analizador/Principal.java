package analizador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java_cup.internal_error;

public class Principal {

	public static void main(String[] args) throws internal_error, IOException, Exception {
		// TODO Auto-generated method stub
		String ruta1 = new String("C:/Users/carlo/Documents/Teoria_Compiladores/examen_final/src/analizador/LexerCup.flex");
		String[] rutaS = {"-parser", "Sintax", "C:/Users/carlo/Documents/Teoria_Compiladores/examen_final/src/analizador/Sintax.cup"};
		generar(ruta1, rutaS);
	}
	
	public static void generar(String ruta1, String[] rutaS) throws internal_error, IOException, Exception {
		File archivo;
		archivo = new File(ruta1);
		JFlex.Main.generate(archivo);
		java_cup.Main.main(rutaS);
		
		/* Ruta a la tabla de simbolos generada por CUP */
		Path rutaSym = Paths.get("C:/Users/carlo/Documents/Teoria_Compiladores/examen_final/src/analizador/sym.java");
		/* Borra el archivo si ya existe en el proyecto */
		if (Files.exists(rutaSym)) {
			Files.delete(rutaSym);
		}
		/* Mueve el archivo de simbolos generado por CUP al directorio del proyecto */
		Files.move(
				Paths.get("C:/Users/carlo/Documents/Teoria_Compiladores/examen_final/sym.java"),
				rutaSym
		);
		
		/* Ruta al archivo de sintactica generada por CUP */
		Path rutaSintax = Paths.get("C:/Users/carlo/Documents/Teoria_Compiladores/examen_final/src/analizador/Sintax.java");
		/* Borra el archivo si ya existe en el proyecto */
		if (Files.exists(rutaSintax)) {
			Files.delete(rutaSintax);
		}
		/* Mueve el archivo de sintactica generado por CUP al directorio del proyecto */
		Files.move(
				Paths.get("C:/Users/carlo/Documents/Teoria_Compiladores/examen_final/Sintax.java"),
				rutaSintax
		);
	}
}
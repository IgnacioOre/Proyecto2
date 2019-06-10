import java.util.LinkedList;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner scLineas = new Scanner(System.in);
		Scanner scTokens;
		LinkedList<Character> caracteresProhibidos = new LinkedList<Character>();
		caracteresProhibidos.add('#');
		caracteresProhibidos.add('"');
		caracteresProhibidos.add('\'');
		caracteresProhibidos.add(',');
		caracteresProhibidos.add('.');

		
		// Lectura estados
		LinkedList<String> estados = new LinkedList<String>();
		
		// Lee la primera linea con los estados
		String lineaEstados = scLineas.nextLine();
		
		// Se utiliza un Scanner auxiliar para descomponer la primera linea en caracteres
		scTokens = new Scanner(lineaEstados);
		
		// Se utiliza para verificar que solo se haya introducido un caracter por estado
		String verificador = "";
		
		// Sigue ingresando estados mientras el Scanner auxiliar tenga tokens
		while (scTokens.hasNext()) {
			// Lee el caracter para el estado
			verificador = scTokens.next();
			
			// Verifica que el primer caracteer sea alfanumerico, de ser así, lo guarda en la lista de estados, de otro modo entrega el error y cierra el programa
			if ((!Character.isLetterOrDigit(verificador.charAt(0)))) {
				System.out.println("Error encontrado en 1");
				System.exit(0);
			} else {
				estados.add(verificador);
			}
		}
		
		// Verifica que ningun estado utilize un caracter prohibido
		for (String palabraEstado : estados) {
			for (int i=0;i<palabraEstado.length();i++) {
				if (caracteresProhibidos.contains(palabraEstado.charAt(i))) {
					System.out.println("Error encontrado en 1");
					System.exit(0);
				}
			}
		}
		
		// Lectura alfabeto
		LinkedList<Character> alfabeto = new LinkedList<Character>();
		
		// Lee la segunda linea con el alfabeto
		String lineaAlfabeto = scLineas.nextLine();
		
		// Reutilizamos el Scanner auxiliar para descomponer la segunda linea en caracteres
		scTokens = new Scanner(lineaAlfabeto);
		
		// Reutilizamos el verificador para revisar el largo del caracter y si es alfanumerico 
		verificador = "";
		
		// Sigue ingresando caracteres del alfabeto mientras el Scanner auxiliar tenga tokens
		while (scTokens.hasNext()) {
			// Lee el caracter para el alfabeto
			verificador = scTokens.next();
			
			// Verifica que solo sea un caracter, y que este sea alfanumerico, de ser así, lo guarda en la lista de estados, de otro modo entrega el error y cierra el programa
			if (verificador.length() != 1 || (!Character.isLetterOrDigit(verificador.charAt(0)))) {
				System.out.println("Error encontrado en 2");
				System.exit(0);
			} else {
				alfabeto.add(verificador.charAt(0));
			}
		}
		
		
		
		// Lectura simbolos de la pila
		LinkedList<Character> simbolosPila = new LinkedList<Character>();
		
		// Lee la tercera linea con los simbolos de la pila
		String lineaPila = scLineas.nextLine();
		
		// Reutilizamos el Scanner auxiliar para descomponer la tercera linea en caracteres
		scTokens = new Scanner(lineaPila);
		
		// Reutilizamos el verificador para revisar el largo del caracter
		verificador = "";
		
		// Sigue ingresando caracteres del alfabeto mientras el Scanner auxiliar tenga tokens
		while (scTokens.hasNext()) {
			// Lee el caracter para el estado
			verificador = scTokens.next();
			
			// Verifica que solo sea un caracter
			if (verificador.length() != 1) {
				System.out.println("Error encontrado en 3");
				System.exit(0);
			} else {
				simbolosPila.add(verificador.charAt(0));
			}
		}
		
		// Verifica que ningun simbolo de la pila utilize un caracter prohibido
		for (char caracterPila : simbolosPila) {
			if (caracteresProhibidos.contains(caracterPila)) {
				System.out.println("Error encontrado en 3");
				System.exit(0);
			}
			
		}
		
		
		
		// Lectura estado inicial, lee la cuarta linea con el unico estado inicial
		String estadoInicial = scLineas.nextLine();
		
		// Revisa que el estado indicado exista, en caso contrario entrega error y cierra el programa
		if (!estados.contains(estadoInicial)) {
			System.out.println("Error encontrado en 4");
			System.exit(0);
		}
		
		// Lectura estados finales
		LinkedList<String> estadosFinales = new LinkedList<String>();
		
		// Lee la quinta linea con los estados finales
		String lineaEstadosFinales = scLineas.nextLine();
		
		// Reutilizamos el Scanner auxiliar para descomponer la quinta linea en String's
		scTokens = new Scanner(lineaEstadosFinales);
				
		// Reutilizamos el verificador para almacenar los String's
		verificador = "";
				
		// Sigue ingresando estados finales mientras el Scanner auxiliar tenga tokens
		while (scTokens.hasNext()) {
			// Lee el caracter para el estado
			verificador = scTokens.next();
			estadosFinales.add(verificador);
		}
		
		// Verifica que todos los estados finales ingresados existan en el automata, en caso contrario entrega error y cierra el programa
		for (String palabraEstadosFinales : estadosFinales) {
			if (!estados.contains(palabraEstadosFinales)) {
				System.out.println("Error encontrado en 5");
				System.exit(0);
			}
		}
		
		// Lectura simbolo inicial de la pila
		verificador = scLineas.nextLine();
		
		// Verifica que solo sea un caracter
		if (verificador.length() != 1) {
			System.out.println("Error encontrado en 6");
			System.exit(0);
		}
		
		// Guarda el simbolo inicial de la pila
		char simboloInicialPila = verificador.charAt(0);
		
		// Verifica que el caracter pertenece a los simbolos de la pila
		if (!simbolosPila.contains(simboloInicialPila)) {
			System.out.println("Error encontrado en 6");
			System.exit(0);
		}
		
		// Lectura Transiciones
		
		LinkedList<String> transiciones = new LinkedList<String>();
		
		// Lee la septima linea con las transiciones
		String lineaTransiciones = scLineas.nextLine();
		
		scTokens = new Scanner(lineaTransiciones);
		
		while (scTokens.hasNext()) {
			// Lee las transiciones
			verificador = scTokens.next();
			transiciones.add(verificador);
		}
		
	
		String palabra = scLineas.nextLine();
		
		for (int i=0;i<palabra.length();i++) {
			if (!alfabeto.contains(palabra.charAt(i))) {
				System.out.println("Error encontrado en 8");
				System.exit(0);
			}
		}
		
		
		Automata beepBoop = new Automata(estados, alfabeto, simbolosPila, estadoInicial, estadosFinales, simboloInicialPila, transiciones);
		beepBoop.prepararAutomata();
		beepBoop.computar(palabra);
		
	}
}

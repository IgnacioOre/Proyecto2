import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.HashMap;


public class Automata {
	private LinkedList<String> nombreEstados;
	private LinkedList<Character> alfabeto;
	private LinkedList<Character> simbolosPila;
	private String estadoInicial;
	private LinkedList<String> estadosFinales;
	private char simboloInicialPila;
	private LinkedList<String> transiciones;
	private Stack<Character> pila;
	
	private LinkedList<Estado> estados;
	private HashMap<Integer, String> apilaciones;
	
	private boolean leida;
	

	
	public Automata(LinkedList<String> nombreEstados, LinkedList<Character> alfabeto, LinkedList<Character> simbolosPila,
			String estadoInicial, LinkedList<String> estadosFinales, char simboloInicialPila,
			LinkedList<String> transiciones) {
		this.nombreEstados = nombreEstados;
		this.alfabeto = alfabeto;
		this.simbolosPila = simbolosPila;
		this.estadoInicial = estadoInicial;
		this.estadosFinales = estadosFinales;
		this.simboloInicialPila = simboloInicialPila;
		this.transiciones = transiciones;
		this.pila = new Stack<Character>();
		this.estados = new LinkedList<>();
		this.apilaciones = new HashMap<Integer, String>();
		this.leida = false;
	}
	
	public void prepararAutomata() {
		for (String nombre : nombreEstados) {
			estados.add(new Estado(nombre));
			if (nombre.equals(estadoInicial)) {
				estados.peekLast().setEstadoInicial();
			}
			
			if (estadosFinales.contains(nombre)) {
				if (estados.peekLast().getEstadoActual() == 1) {
					estados.peekLast().setEstadoInicialYFinal();
				} else {
					estados.peekLast().setEstadoFinal();

				}
			}

		}
		this.agregarTransiciones();
	}
	
	public void agregarTransiciones() { 
		Scanner sc;
		String nombreInicio;
		char alfa;
		char simPila;
		String nombreFinal;
		String aux;
		for (int i=0;i<transiciones.size();i++) {
			sc = new Scanner(transiciones.get(i));
			sc.useDelimiter(",|\r\n");
			aux = sc.next();
			nombreInicio = aux.substring(1);
			alfa = sc.next().charAt(0);
			aux = sc.next();
			simPila = aux.charAt(0);
			nombreFinal = aux.substring(2, aux.length());
			aux = sc.next();
			aux = aux.substring(0, aux.length()-1);
			
			if (this.getPosEstadoPorNombre(nombreInicio) == -1 || this.getPosEstadoPorNombre(nombreFinal) == -1 || (!alfabeto.contains(alfa) && alfa != '#') || !simbolosPila.contains(simPila)) {
				System.out.println("Error encontrado en 7");
				System.exit(0);
			}
			
			this.estados.get(this.getPosEstadoPorNombre(nombreInicio)).agregarTransición(alfa, simPila, this.estados.get(this.getPosEstadoPorNombre(nombreFinal)), alfabeto);
			apilaciones.put(nombreFinal.length() + simPila + alfa, aux);
			
			
			
		}
	}
	
	public int getPosEstadoPorNombre(String nombreBuscado) {
		for (int i=0;i<estados.size();i++) {
			if (estados.get(i).getNombre().equals(nombreBuscado)) {
				return i;
			}
		}
		return -1;
	}
	
	public void computar(String palabra) {
		LinkedList<Estado> estadosComputados = new LinkedList<Estado>();
		Estado estadoTransicion = this.estados.get(this.getPosEstadoPorNombre(estadoInicial));
		pila.push(simboloInicialPila);
		String porApilar = "";
		System.out.println("." + palabra + " " + estadoTransicion.getNombre() + " " + pila.peek());
		estadosComputados.add(estadoTransicion);
		
		while (estadosComputados.peekLast().getTransicion('#', pila.peek()) != null) {
			estadoTransicion = estadosComputados.peekLast().getTransicion('#', pila.peek());
			porApilar = this.apilaciones.get(estadoTransicion.getNombre().length() + '#' + pila.peek());
			pila.pop();
			if (!porApilar.equals("#")) {
				for (int k=porApilar.length()-1;k>=0;k--) {
					pila.push(porApilar.charAt(k));
				}
			}
			
			System.out.print("." + palabra + " " + estadoTransicion.getNombre() + " ");
			for (int k=pila.size()-1;k>=0;k--) {
				System.out.print(pila.get(k));
			}
			System.out.println();
			estadosComputados.add(estadoTransicion);
		}
		
		for (int i=0;i<palabra.length();i++) {	
			
			if (estadosComputados.peekLast().getTransicion(palabra.charAt(i), pila.peek()) != null) {
				estadoTransicion = estadosComputados.peekLast().getTransicion(palabra.charAt(i), pila.peek());
				porApilar = this.apilaciones.get(estadoTransicion.getNombre().length() + palabra.charAt(i) + pila.peek());
				pila.pop();
				if (!porApilar.equals("#")) {
					for (int k=porApilar.length()-1;k>=0;k--) {
						pila.push(porApilar.charAt(k));
					}
				}
				
				System.out.print(palabra.substring(0, i+1) + "." + palabra.substring(i+1, palabra.length()) + " " + estadoTransicion.getNombre() + " ");
				for (int k=pila.size()-1;k>=0;k--) {
					System.out.print(pila.get(k));
				}
				System.out.println();
				estadosComputados.add(estadoTransicion);
			} else if (estadosComputados.peekLast().getTransicion('#', pila.peek()) != null) {
				while (estadosComputados.peekLast().getTransicion('#', pila.peek()) != null) {
					estadoTransicion = estadosComputados.peekLast().getTransicion('#', pila.peek());
					porApilar = this.apilaciones.get(estadoTransicion.getNombre().length() + '#' + pila.peek());
					pila.pop();
					if (!porApilar.equals("#")) {
						for (int k=porApilar.length()-1;k>=0;k--) {
							pila.push(porApilar.charAt(k));
						}
					}
					
					System.out.print(palabra.substring(0, i) + "." + palabra.substring(i, palabra.length()) + " " + estadoTransicion.getNombre() + " ");
					for (int k=pila.size()-1;k>=0;k--) {
						System.out.print(pila.get(k));
					}
					System.out.println();
					estadosComputados.add(estadoTransicion);
					
				}
				i--;
			} else {
				System.out.println("Rechazado");
				System.exit(0);
			}
			
			if (i==palabra.length()-1) {
				leida = true;
			}
		}
		
		while (estadosComputados.peekLast().getTransicion('#', pila.peek()) != null) {
			estadoTransicion = estadosComputados.peekLast().getTransicion('#', pila.peek());
			porApilar = this.apilaciones.get(estadosComputados.peekLast().getTransicion('#', pila.peek()).getNombre().length() + '#' + pila.peek());
			pila.pop();
			if (!porApilar.equals("#")) {
				for (int k=porApilar.length()-1;k>=0;k--) {
					pila.push(porApilar.charAt(k));
				}
			}
			
			System.out.print(palabra + ". " + estadoTransicion.getNombre() + " ");
			for (int k=pila.size()-1;k>=0;k--) {
				System.out.print(pila.get(k));
			}
			System.out.println();
			estadosComputados.add(estadoTransicion);
		}
		
		
		if (leida && pila.peek() == simboloInicialPila && (estadoTransicion.getEstadoActual() == 2 || estadoTransicion.getEstadoActual() == 3 )) {
			System.out.println("Aceptado");
		} else {
			System.out.println("Rechazado");
		}
	}
}

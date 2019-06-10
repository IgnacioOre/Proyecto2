import java.util.HashMap;
import java.util.LinkedList;

public class Estado {
	private byte estadoActual; // 0 normal, 1 inicial, 2 final, 3 inicial y final
	private String nombre;
	private HashMap<Character, HashMap<Character, Estado>> transiciones;
	String empilado;
	
	public Estado(String nombre) {
		estadoActual = 0;
		this.nombre = nombre;
		transiciones = new HashMap<Character, HashMap<Character, Estado>>();
		empilado = "";
	}
	
	public String getEmpilado() {
		return empilado;
	}
	
	public void setEmpilado(String a) {
		empilado = a;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setEstadoFinal() {
		estadoActual = 2;
	}
	
	public void setEstadoInicial() {
		estadoActual = 1;
	}
	
	public void setEstadoInicialYFinal() {
		estadoActual = 3;
	}
	
	public byte getEstadoActual() {
		return estadoActual;
	}
	
	public void agregarTransición(char simboloAlfabeto, char simboloPila, Estado estadoAlcanzado, LinkedList<Character> alfabeto) {
		if (transiciones.get(simboloAlfabeto) == null) {
			transiciones.put(simboloAlfabeto, new HashMap<Character, Estado>());
			transiciones.get(simboloAlfabeto).put(simboloPila, estadoAlcanzado);
			
			if (simboloAlfabeto == '#') {
				for (char a : alfabeto) {
					if (transiciones.get(a) != null && transiciones.get(a).get(simboloPila) != null) {
						System.out.println("Error encontrado en 7");
						System.exit(0);
					}
				}
			} else {
				if (transiciones.get('#') != null && transiciones.get('#').get(simboloPila) != null) {
					System.out.println("Error encontrado en 7");
					System.exit(0);
				}
			}
			
		} else if (transiciones.get(simboloAlfabeto).get(simboloPila) == null) {
			transiciones.get(simboloAlfabeto).put(simboloPila, estadoAlcanzado);
			

		} else {
			System.out.println("Error encontrado en 7");
			System.exit(0);
		}
	}
	
	public Estado getTransicion(char alfa, char pila) {
		if (transiciones.get(alfa) == null) {
			return null;
		} else if (transiciones.get(alfa).get(pila) == null) {
			return null;
		} else {
			return transiciones.get(alfa).get(pila);
		}
	}
}

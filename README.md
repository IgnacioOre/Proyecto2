# Proyecto2

Autómata con pila no determinista para reconocer lenguajes libres de contexto.

### Contexto

Tarea realizada en el ramo de Fundamentos de Ciencias de la Computación, dictado por el profesor Rodrigo Torres en la Universidad del Bío-Bío, sede Chillán.

## Instrucciones

El input debe ser ingresado por teclado, en 8 líneas que cumplan el siguiente formato:

* Primera línea: Estados del autómata, separados por uno o más espacios. El nombre de cada estado debe empezar con un carácter alfanumérico, y no debe pertenecer a los símbolos prohibidos que se encuentran en la siguiente sección.

* Segunda línea: Símbolos del alfabeto de la cinta, separados por uno o más espacios. Cada símbolo debe ser único, de largo 1, ser alfanuméricos, y no deben pertenecer a los símbolos prohibidos que se encuentran en la siguiente sección.

* Tercera línea: Símbolos del alfabeto de la pila, separados por uno o más espacios. Cada símbolo debe ser único y de largo 1.

* Cuarta línea: Estado inicial. Debe estar contenido dentro de los estados de la primera línea.

* Quinta línea: Estados finales, separados poor uno o más espacios. Deben estar contenidos dentro de los estados de la primera línea.

* Sexta línea: Símbolo del fondo de la pila. Debe estar contenido dentro del alfabeto de símbolos de la pila de la tercera línea.

* Séptima línea: Transiciones del autómata, separadas por uno o más espacios. En el caso de transición o apilación vacía, se utilizará el carácter '#'. Siguen el siguiente formato: (Estado,Símbolo_Alfabeto,Símbolo_Pila=Estado,CaracteresPorApilar).

* Octava línea: Palabra a reconocer. Todos los caracteres dentro de esta deben pertenecer al alfabeto de la cinta de la segunda línea.

### Caracteres prohibidos

No se admitirán como caracteres para describir estados o símbolos de la pila a los siguientes:

* '#'
* '"'
* '''
* '.'

Si el input fue realizado exitosamente, el programa procede a computar la palabra, en caso contrario, entrega un error indicando la línea que falló la comprobación y termina la ejecución.

## Formato del cómputo

El cómputo de la palabra a reconocer seguirá el siguiente formato

```
palabra estado_actual pila
```

Se utilizará el carácter '.' para indicar el progreso dentro de la palabra.

El autómata entregará la palabra "Aceptado" si al final de leer la palabra no quedan símbolos en la cinta y el estado actual es un estado final. En cualquier otro caso, entregará la palabra "Rechazado".

### Ejemplos

Para el siguiente input: 

```
q1 q2
0 1
A L
q1
q2
L
(q1,0,L=q1,AL) (q1,0,A=q1,AA) (q1,1,A=q2,#) (q2,1,A=q2,#)
000111
```

Se realizará el siguiente output:

```
.000111 q1 L
0.00111 q1 AL
00.0111 q1 AAL
000.111 q1 AAAL
0001.11 q2 AAL
00011.1 q2 AL
000111.q2L
Aceptado
```

Para el siguiente input:

```
q1 q2
0 1
A L
q1
q2
L
(q1,0,L=q1,AL) (q1,0,A=q1,AA) (q1,1,A=q2,#) (q2,1,A=q2,#)
00011
```

Se realizará el siguiente output:

```
.00011 q1 L
0.0011 q1 AL
00.011 q1 AAL
000.11 q1 AAAL 
0001.1 q2 AAL
00011. q2 AL
Rechazado
```

## Casos de prueba

Se han recopilado los siguientes autómatas con pila para probar el programa. No incluyen palabra a reconocer, debe ser suministrada por el tester de acuerdo a su criterio.

### 0<sup>n</sup>1<sup>m</sup>, n <= m

```
q0 q1 q2
0 1
L A
q0
q2
L
(q0,0,L=q1,AL) (q1,0,A=q1,AA) (q1,1,A=q2,#) (q2,1,L=q2,L) (q2,1,A=q2,#)
```

### 0<sup>n</sup>1<sup>m</sup>2<sup>m</sup>3<sup>n</sup>, n, m >= 1

```
q0 q1 q2 q3 q4
0 1 2 3
A B L
q0
q4
L
(q0,0,L=q0,AL) (q0,0,A=q0,AA) (q0,1,A=q1,BA) (q1,1,B=q1,BB) (q1,2,B=q2,#) (q2,2,B=q2,#) (q2,3,A=q3,#) (q3,3,A=q3,#) (q3,#,L=q4,L)
```
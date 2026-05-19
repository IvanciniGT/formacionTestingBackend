# SonarQube

Sonar nos hace una validación del código, sin ejecutar el programa. Solo mira el código fuente y nos dice si hay cosas que podrían ser mejorables, o si hay cosas que podrían ser un error. 

Es una herramienta de análisis estático de código.

Si mi proyecto se crea desde JAVA/MAVEN, maven tiene integración directa con sonar.

    $ mvn sonar:sonar -Dsonar.projectKey=fermin -Dsonar.host.url=http://108.131.96.29:8081 -Dsonar.token=sqa_b31ffca82aa7bed42a4e701ed5a23e5976f1c11b

Lo mismo aplica si mi proyecto es de JavaScript/NodeJS, o de Python, C#, etc.

# Complejidad ciclomática:

Número de caminos que puede tomar el código al ejecutarse.
Esto tiene impacto directo en el número de tests.
Al menos tengo que hacer tantas pruebas como caminos tenga el código, para asegurarme de que lo he probado todo.

# Complejidad cognitiva:

Como de complicado es entender el código para un humano.


    Código:
        Si (condicion1):
            Haz tarea 1
        Sino, si (condicion2):
            Haz tarea 2
        Sino, si (condicion3):
            Haz tarea 3
        Sino:
            Haz tarea 4
    
    Complejidad ciclomática: 4 (4 caminos posibles)
    Complejidad cognitiva: Básica... muy sencilla de entender

    Código:
        Si (condicion1):
            Haz tarea 1
            Si(condicion2):
                Haz tarea 2
            Sino:
                Haz tarea 3
        Sino:
            Si (condicion3):
                Haz tarea 4
            Haz tarea 5

        Si se cumple condicion1 -> tarea1 -> Si se cumple condicion2 -> tarea2
        Si se cumple condicion1 -> tarea1 -> Si NO se cumple condicion2 -> tarea3
        Si NO se cumple condicion1 -> Si se cumple condicion3 -> tarea4 -> tarea5
        Si NO se cumple condicion1 -> Si NO se cumple condicion3 -> tarea5

    Complejidad ciclomática: 4 (4 caminos posibles)
    Complejidad cognitiva: Más alta... es un poco más difícil de entender

Sonarqube establece límites.
Si tengo funciones con una complejidad ciclomática muy alta o con una complejidad cognitiva muy alta, me lo va a marcar como un problema.. y me invita a crear funciones más pequeñas, con menores 


# Cobertura:

Función:
    Si condicion1:
        haz tarea 1
        haz tarea 2
        haz tarea 3
    Sino:
        haz tarea 4

Si mis pruebas solo prueban el caso condicion1=true
- Cobertura de lineas: 3 lineas he ejecutado... 1 no
  Si he ejecutado: tarea1, tarea2, tarea3... pero no he ejecutado tarea4, entonces tengo una cobertura de lineas del 75% (3 de 4) 
- Cobertura de ramas: 1 rama he ejecutado (condicion1=true) y 1 no (condicion1=false), entonces tengo una cobertura de ramas del 50% (1 de 2)

---

En la realidad, jenkins se usa de otra forma.

Una instalación de jenkins hoy en dia BUENA para por tener KUBERNETES.
El jenkins se monta ahi.. pero cada trabajo que ejecuta lo hace en un contenedor .

Podemos montarlo sin kubernetes... y trabajar con docker... pero es que es más chapucero.. si se cae la maquina de docker.. me quedo sin jenkins.

---

# Cucumber

Cucumbewr es una librería de pruebas.. similar a lo que sería JUnit.
Cuál es la gracia?

La gracia está en que las pruebas las definimos con un lenguaje llamado gherkin, que es un lenguaje muy sencillo, parecido al humano.

```gherkin
Característica: Registrar animalitos

Escenario: Registrar animalito con datos válidos
    Dado que tengo un animalito con nombre "Rocky" y especie "Perro"
    Cuando registro el animalito
    Entonces el registro es exitoso y el animalito queda registrado en el sistema
```

Básicamente se trata de empezar las frases por las palabras :
- Característica:
- Escenario:
- Dado...
- Cuando...
- Entonces...

Si hay valores de datos, que sean textos, los ponemos en comillas dobles, para que se puedan usar luego en el código.
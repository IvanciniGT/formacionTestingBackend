# Vocabulario en el mundo del testing

- Causa raíz    Es la razón por la que el humano comete un error.
- Error         Los humanos cometemos errores (errar es humano).
- Defecto       Cuando un humano comete un error puede introducir un defecto en un producto.
   (bug)        Podemos entenderlo como una cicatriz que dejamos en el producto.
- Fallo         La manifestación de un defecto que tengo en el producto cuando se usa el producto.
                Podemos verlo también como una desviación con respecto a lo esperado (según los requisitos).

# Para qué sirven las pruebas?

- Sirven para asegurar el cumplimiento de unos requisitos (funcionales o no funcionales)
  - Verificar la calidad del programa
  - Validación de lo que se espera obtener
- Sirven para tratar de encontrar la mayor cantidad posible de defectos en el producto antes de liberarlo para su uso.
  Cómo identificamos defectos?
  - Tratando de provocar fallos al usar el producto. ESTA ES LA MAS HABITUAL.
    Una vez identificado un fallo, recopilo toda la infromación posible para facilitar el debugging o depuración.
    Es decir, facilitar la identificación del defecto que ha provocado el fallo.
  - Identificar defectos directamente. Revisar el producto. ESTO TENDEMOS A HACERLO POCO:
    - Revisión del código                   SONARQUBE
    - Revisión de requisitos
    - Revisión de diseño
- Recopilar información para facilitar un rápido debugging o depuración. 
- Para aprender y enriquecer mi know-how.. sobre el producto o no!
- Haciendo un análisis de causas raíz podemos plantear acciones preventivas, que eviten nuevos errores, con sus correspondientes defectos y fallos.
- Para mejorar/plantear el diseño del sistema... si las hago a tiempo! Pruebas exploratorias.
- Para saber qué tal va el proyecto. <<< CRITICO!
  Hoy en día (impulsado por las metodologías ágiles) la forma de medir que tal va a el proyecto es por:
  - Número de pruebas nuevas pasadas
  - Número de pruebas nuevas falladas
  - Número de nuevas pruebas definidas
- Las pruebas me ayudan a tener un buen diseño del sistema. Si las hago a tiempo... y las hago bien... me van a ayudar a tener un buen diseño del sistema.
- ...

# Tipos de pruebas

Las pruebas las podemos clasificar en base a distintas categorizaciones (taxonomías). Y son clasificaciones paralelas entre si.

## En base al objeto de prueba... a lo que estoy probando:

- Pruebas funcionales           Atienden a requisitos funcionales.
- Pruebas no funcionales        Atienden a requisitos no funcionales.
  - Rendimiento
  - Carga
  - Estrés
  - Seguridad
  - Usabilidad
  - UX
  - HA
  - ...

## En base a la forma de realización de la prueba:

- Pruebas estáticas     Las que no usan el producto para realizar la prueba.                            Buscan directamente defectos.
  - Revisiones...
- Pruebas dinámicas     Las que usan el producto(ejecutar el programa) para realizar la prueba.         Buscan FALLOS.

## En base al conocimiento del objeto de prueba:

- Pruebas de caja negra     No tengo conocimiento del producto. Solo conozco los requisitos.
- Pruebas de caja blanca    Tengo conocimiento del producto. Conozco la forma en la que está implementado... y eso me invita a hacer más pruebas.

Librería... que me permite buscar significados de palabras.
- Cuando busco el significado de la palabra "Melón" es el diccionario de "Español" entonces la librería me devuelve:
  - Fruto del melonero, de forma redonda u ovalada, con la corteza verde y la pulpa dulce y jugosa, de color anaranjado o rojo, que se come como fruta.
  - Persona con pocas luces: ERES UN MELON!

Las palabras las tengo una BBDD. Y hago una query para traer el significado.
Las palabras las tengo en un fichero. Y leo el fichero para traer el significado.

Imaginad que una vez leída la palabra, la guardo en una caché... y la siguiente vez no la devuelvo de la BBDD o del fichero, sino que la devuelvo de la caché... porque es más rápido.
    Este conocimiento me invita a hacer la prueba de otra forma... o al menos a completarla...
    - Cojonudo. ya sé que cuando pregunto por "Melón" me da los significados...
    - Y cuando pregunto otra vez? sigue devolviendo los significados o me da los de manzana por que se le ha cruzado el cable?

## En base al contexto de ejecución de la prueba (scope/ámbito)

- Unitarias                 Cuando me centro en una característica de un componente AISLADO del producto.
                            Una función la puedo probar de forma unitaria, de integración, de sistema o de aceptación.

> Soy un fabricante de bicicletas: Decathlon: BT-WIN.

Fabrico yo decathlon el sillín? o lo encargo (subcontrato)? NO
Fabrico yo el sistema de frenos? NO
Fabrico las ruedas? NO

Qué cojones pinto en todo esto? Yo diseño, especifico comoponentes o busco componentes que cuadren con mi especificación.. y los integro.

Me llegan los sillines (5k sillines.. en cajas). Qué hago cuando llegan?
- Opción A... al trastero... hasta que lleguen el resto de componentes y empiece a montar la bici
- Opción B: LE HAGO PRUEBAS AL SILLIN a ver si no me han dado gato por liebre.

Qué pruebas le puedo hacer al sillín?

- Monto el sillín en un batidor de pruebas (4 hierros soldaos lo justo que aguante 500 kgs) y siento a un tio de 150kgs encima a ver si no se parte!                                                     Prueba unitaria no funcional, de caja negra, dinámica de CARGA!
- Monto el sillín en otro bastidor de pruebas abatible y la giro (basculo, tumbo pa el lao) 45 grados.. a ver si el cuero no resbala... y sujeta el culo del individuo.                                     Prueba unitaria no funcional, de caja negra, dinámica de SEGURIDAD!
- Monto el sillín en un batidor de pruebas (4 hierros soldaos lo justo que aguante 500 kgs) y me pongo a frotar con una lija la superficie.. tratando de simular a un tio sentandose encima 10k veces.  Prueba unitaria no funcional, de caja negra, dinámica de ESTRES!
- Monto el sillín en un batidor de pruebas (4 hierros soldaos lo justo que aguante 500 kgs) y siento a un tio encima durante 4 horas... a ver si no le queda el culo echando hostias!                       Prueba unitaria no funcional, de caja negra, dinámica de UX!

Me llega el sistema de frenos: Qué prueba le hago? O lo dejo en el trastero...

- Monto el sistema de frenos en un bastidor... aprieto la palanca de frenos y veo si las pinzas cierran.
- Cerrarán con fuerza suficiente?
- Monto el sistema de frenos en un bastidor... entre las zapatas de freno pongo un SENSOR de presión, aprieto la palanca de frenos:
        sistemaDeFrenos.apretarPalanca();
  y veo si las pinzas cierran y miro que la fuerza que ejercen es adecuada.

Imaginad que hago todas las pruebas unitarias que se me ocurran sobre todos y cada uno de los componentes de mi bicicleta.
Me garantiza esto que la bici va a funcionar guay? NO... qué bici?

Entonces, para qué las hago? CONFIANZA+1
Voy bien... voy dando pasos en firme.


> Pregunta! El bastidor/bastidores que estoy usando y el sensor de presión... los voy a entregar con la bici? Son parte del producto? NO
  
  Pero tendré que disponer de ellos para poder probar el producto.. 
  Más vale que se hayan presupuestado.
  Bastidores... sensores de presión.. que en el mundo del software se denominan? TEST-DOUBLES
  Y hay un montón de tipos de test-doubles:
  - Stubs
  - Spies
  - Mocks
  - Fakes
  - Dummies

> Me dicen: Requisito 1 (versión 3):  NO FUNCIONAL de rendimiento.
   El sistema montado en un entornos con tales características... y estando sometido a tal carga de trabajo... y estando precalentado con tales operaciones... tiene que responde a tal operación en menos de 300ms el 95% de las veces.

    Este requisito da lugar a 1 o varias pruebas? ESTO SERIA UNA PRUEBA DE SISTEMA
        La prueba es COGER EL SISTEMA,
        Instalarlo en un entornos con tales características...
        Sometelo a tal carga de trabajo...
        Precalientalo con tales operaciones...
        Y haz la operación (1000 veces)... 
        y mide el tiempo de respuesta... 
        y sacar el percentil 95... 
        y ver si es inferior a 300ms.

    Tiene sentido para este requisito hacer pruebas unitarias? TODO EL DEL MUNDO
    Imaginad que en la funcionalidad está hacer una query a una BBDD.
    Pregunta... puedo medir la latencia de la comunicación a BBDD? SI.. y tarde 120ms...
    Cómo vamos? BIEN JODIDOS... como haya 2 queries a la BBDD ya llevo 240ms... y no he empezado el procasamiento.. ni la resolución de la query.
    Esta prueba (UNITARIA) me ha dado información muy valiosa para el diseño de la solución...Ya me dice.. como haya que hacer 2 queries estás muerto... Necesitas un sistema de cache... tu sabrás... pero 2 queries o soporta.

    Si esa prueba la hago el día 1... va a condicionar TOTALMENTE el desarrollo del sistema / su diseño.
    Es decir, las pruebas no solo me estan sirviendo para verificar la calidad del producto... asegurar requisitos... sino para mejorar / plantear el diseño del sistema.

- Integración                Se centran en la COMUNICACIÓN entre 2 COMPONENTES.

Junto el sillín y el sistema de frenos.. tiene sentido? NO HAY DEPENDENCIAS ENTRE ELLOS. Uno no depende el otro ni el otro depende de uno. No hay comunicación entre ellos... no hay nada que probar.

Junto el sistema de frenos y la rueda... tiene sentido? SI.
- Monto el sistema de frenos en un bastidor... entre las zapatas de freno pongo la rueda... que también esta fijada al bastidor,
- le pego un viaje la rueda... que gire!
  aprieto la palanca de frenos:
        sistemaDeFrenos.apretarPalanca();
  y veo si la rueda se para... y mira que no!
  Que las pinzas cierran.. y cierran con fuerza... pero no lo suficiente como para tocar la rueda.
  La rueda es estrecha para ese sistema de frenos.. o el cierre del sistema de frenos es demasiado poco para esa rueda.
  - Pregunta: La rueda tiene un defecto? es defectuosa? NO
  - Pregunta: El sistema de frenos tiene un defecto?    NO
  - El problema es que ese sistema de frenos no es compatible con esa rueda


Imaginad que hago todas las pruebas de integración que se me ocurran sobre todos y cada uno de los componentes de mi bicicleta juntaditos 2 a 2.
Me garantiza esto que la bici va a funcionar guay? NO... qué bici?

Entonces, para qué las hago? CONFIANZA+1
Sigo bien... sigo dando pasos en firme.

- Sistema (end to end)
  
    Cojo una bicicleta ya montada, con todas sus cositas!
    Subo a un tio... mochila en la espalda... bocadillo de chorizo y botella de agua.. y alé pa'Cuenca... 300kms... ida y vuelta.. a ver si llegas!
    El tio va y vuelve.. sano y salvo.. con el culo bien!


    Cojo la bici.... le monto un tio encima... la pongo en un circuito.. pedalea el tio hasta que va a 40kms/hora.
    Y al apretar la palanca de frenos, la bici se tiene que detener en menos de 10 metros...
     Qué función estoy probando? Qué función ejecuto en la prueba?

        sistemaDeFrenos.apretarPalanca();
            Probar una función no es por definición una prueba unitaria... puede ser una prueba de sistema... o de integración... o de aceptación... depende del contexto en el que se ejecute la prueba.


    Hago todas las pruebas de sistema que se me ocurran sobre mi bicicleta.

Me garantiza esto que la bici va a funcionar guay? SI (dentro de lo razonable... se me puede haber olvidado alguna prueba)

> Si tengo un producto al que le hago las pruebas de sistema... y todas pasan... necesito hacer pruebas unitarias y de integración?

NO. ya pa que? Si ya tienes una bici que entregar y que funciona.. sueltala!

Aqui hay 2 trucos en esa frase/pregunta:
- Cuándo puedo hacer las pruebas de sistema? Cuando ya tengo el sistema! Y hasta entonces? Voy a ciegas?
- Y si no funcionan? que será lo más probable. Dónde está el problema? NPI desmonta la bici.. analiza a ver que pasa... Y VIVE CON LAS CONSECUENCIAS!

SALIENDO DE ESAS PRUEBAS el producto ya "no tiene" defectos. Que cumple con requisitos.

Querrá mi cliente este producto sin defectos? Quizás si, quizás no.

- Aceptación
  Oiga .. que esta bicicleta me rebota mucho el culo cuando voy por la montaña... Coño es que ha cogido una bicicleta de carreras.

    Se sube el tio a la bici... se pone a andar y ejecuta: 
        sistemaDeFrenos.apretarPalanca();
    y dice.. coño, que dura está esta palanca de frenos... no me gusta! No quiero la bici.

---

Habéis oido hablar de los principios SOLID de desarrollo de software?
En el mundo del testing hay algo parecido: Los principios FIRST de las pruebas:
- Fast: Las pruebas tienen que ser rápidas de ejecutar. Si no, no las vas a ejecutar. Y si no las ejecutas, no te sirven para nada.
- Independent: Las pruebas tienen que ser independientes entre sí. No pueden depender unas de otras. Si una prueba falla, no puede afectar a las demás. Si una prueba se ejecuta de forma aislada, tiene que funcionar igual que si se ejecuta junto con las demás.
- Repeatable: Las pruebas tienen que ser repetibles. Tienen que dar el mismo resultado cada vez que se ejecutan. Si una prueba falla una vez, tiene que fallar siempre.
- Self-validating: Las pruebas tienen que ser auto-validantes. Tienen que ser capaces de decir por sí mismas si han pasado o han fallado. No pueden depender de la interpretación humana para saber si han pasado o han fallado.
- Timely: Oportunas. Las pruebas tienen que ser oportunas. Tienen que ser diseñadas y ejecutadas en el momento adecuado del ciclo de desarrollo. No pueden ser diseñadas y ejecutadas al final del ciclo de desarrollo, cuando ya es demasiado tarde para corregir los defectos que se han encontrado.
---

# Usabilidad

Parece algo casi exoterico. Muy subjetivo.
Pero curiosamente está definido por una norma ISO.
Y se mide con 3 métricas... 2 de ellas totalmente objetivas:
- Eficacia: El grado en el que los usuarios pueden completar tareas con éxito.
- Eficiencia: Cuanto tardan en hacerlo (en tiempo.. clics...)
- Satisfacción: El grado de satisfacción de los usuarios al usar el producto. Esto es subjetivo... pero se puede medir con encuestas, entrevistas, etc.

Todo ello forma parte de otro concepto: UX (User Experience) o Experiencia de Usuario.
Según en diagrama honeycomb de Peter Morville, la UX se compone de:
- Usable: El producto es fácil de usar, intuitivo, no me hace pensar...
- Útil: El producto me sirve para lo que quiero hacer con él.
- Deseable: El producto me gusta, me atrae, me hace sentir algo... me   gusta su diseño, su estética, su marca...
- Accesible: El producto es accesible para todo el mundo, independientemente de sus capacidades, sus limitaciones, su contexto de uso...
- capacidades físicas, cognitivas, etc.
- Credible: El producto me transmite confianza, seguridad, me da garantías de que va a funcionar como se espera, que no me va a fallar, que no me va a dar problemas...
- Encontrable: El producto es fácil de encontrar, de localizar, de acceder a él...
- Valioso: El producto me aporta valor, me hace la vida mejor, me ayuda a resolver mis problemas, me hace sentir bien, me hace feliz...

---

# Metodologías ágiles.

Son una evolución de las metodologías tradicionales (cascada, en V, espiral, etc).

Había muchos problemas que nos encontrábamos al usar met. tradicionales... resolvieron otros.

Gran crisis del software? 

Esto ocurrió a finales de los años 60.
Después de 2 décadas de desarrollo de software, sin control, sin metodología, sin estandarizar procedimientos, diseños... patrones... hubo un caos total... proyectos que se retrasaban, que se iban de presupuesto, que no cumplían los requisitos... y la gente empezó a darse cuenta de que había un problema... un problema estructural.
Estábamos en una disciplina nueva (Ingeniería del software) y no teníamos ni puta idea de cómo hacer las cosas... y eso se traducía en proyectos que se iban a la mierda.

Ese es el origne de la INGENIERÍA DEL SOFTWARE como disciplina.
En los 70 salen muchas cosas, conceptos, principios, metodologías... que nos ayudan a mejorar la forma en la que hacemos las cosas... y a controlar el caos.
Entre ellas las metodologías tradicionales. Pero pasamos de un extremo al otro.
Pasamos de la anarquía total a una rigidez total (burocracia sin límite, procesos centralizados rígidos).
Resultado... nuevo caos.... pero por el otro extremo.

A principios de los 2000, un grupete de colegas, 17 tios... se juntan un finde en una cabañita (literal) y redactan el manifiesto ágil... que es un documento que expone unas ideas... para enfrentarse a los desarrollos de software desde otro punto de vista.

La característica más relevante de una forma ágil de trabajar es:
Entregar el producto de forma incremental al cliente.

En las met. tradicionales, el cliente no veía el producto hasta el final del proyecto... y cuando lo veía... se daba cuenta de que:
- No era lo que buscaba
- Que hay cosas que no le sirven
- Que hay cosas que le faltan

Y en ese momento, rehacer cosas es muy caro. De entrada hay que tirar mucho trabajo a la basura.

Lo que buscamos hoy en día es ir entregando a mi cliente cosas en cuanto las tenga. Con que objetivo: Obtener feedback lo antes posible.

Extraído del manifiesto ágil:

> El software funcionando es la MEDIDA principal de progreso.               Esta frase define un indicador para un cuadro de mando.

                                        Cópula o atributo
                                   -------------------------
La MEDIDA principal de progreso es "El software funcionando".
   ------
   Nucleo
------------------------------- ---------------------------
            Sujeto               Predicado copulativo

Dicho de otra forma:
La forma en la que vamos a medir que tal va el proyecto es mediante el concepto: "Software funcionando".
-------------------------------- ----------------------   
La medida                         principal de progreso

"Software funcionando"? Software que hace lo que se espera de él. Que cumple con requisitos.

Quién dice eso? 
- ~~El cliente~~
- Las pruebas son las que dicen que el software se comporta como se espera de él.. que cumple con los requisitos.

El cliente ayuda con la definición de requisitos funcionales.
Quiero un coche pa ir al mercadona. Con buen maletero.

Llega el cliente y se compra un lambo!


# Por qué esta frase está en el manifiesto ágil? "El software funcionando es la MEDIDA principal de progreso"

La respuesta es sencilla... porque antes no se medía así.

Cómo se medía? Cómo sabía el jefe de proyecto cómo iba el proyecto?

El jefe de proyecto preguntaba (en reunión de seguimiento o fuera...) Menchu, cómo vas? YA tiene el R1, R2 y R3 que estabas montando?
Y decía Menchu: SI!
Vamos bien! decía el jefe de proyecto.
Luego se instalaba en producción y OSTION QUE TE CAGAS!
Ah... decía menchu... pues "en mi máquina funciona"

Hemos aprendido que los desarrolladores mienten más que hablan. Pero a ellos mismos.

Cuidao! que los jefes de proyecto inventamos formas aún más imbeciles de medir esto:
Menchu... vamos a mirar en el sistema de control de versiones cuántas líneas de código has escrito esta semana...como si el número de líneas de código tuviera algo que ver con el progreso del proyecto.

---

# Diseño de pruebas

Una prueba consta de 3 partes:
- Contexto: El estado del sistema antes de ejecutar la prueba. Es decir, el estado del sistema que necesito para poder ejecutar la prueba.
- Acción: La acción que voy a ejecutar para llevar a cabo la prueba. Es decir, la función que voy a ejecutar, el botón que voy a pulsar, la operación que voy a realizar...
- Comprobación: La comprobación que voy a hacer para saber si la prueba ha pasado o ha fallado. Es decir, la comprobación de los resultados obtenidos con los resultados esperados.

Muchas veces usamos un lenguaje para diseñar las pruebas: GIVEN-WHEN-THEN (DADO-CUANDO-ENTONCES)

GIVEN(DADO)         = Contexto
WHEN(CUANDO)        = Acción
THEN(ENTONCES)      = Comprobación

Dado que:
    - Tengo un sistema de frenos instalado en un bastidor de pruebas
    - Tengo un sensor de presión entre las zapatas de freno
Cuando:
    - Aprieto la palanca de frenos
Entonces:
    - Las pinzas de freno cierran
    - El sensor de presión detecta que las pinzas de freno cierran con una fuerza de al menos 100N

Los entonces, las comprobaciones, tenemos varias formas de escribirlas en el mundo del software:
- La forma más básica y más dura son las Assertions:
  asegura que el resultado es true
  asegura que el resultado no es mayor que 100
  asegura que se lanza una excepción 

    Assertions.assertEquals(resultadoEsperado, resultado);              NADIE HABLA ASI!

- Hay otras formas... aserciones en lenguajes más fluidos.
  - Expectativas
    resultado.expect.to.be.equals.to(resultadoEsperado);
  - Should
    resultado.should.be.equals.to(resultadoEsperado);               <<< Esta frase tiene sentido en INGLES AL LEERLA!
                                                                        La gente habla así

Es indiferente.
En general es más cómodo para un desarrollador (que somos muy cuadriculaos...) usar las aserciones clásicas.
Para gente con un perfil menos cuadriculado...las expectativas o los should pueden ser más cómodos (Q&A, testing)

Las pruebas unitarias son responsabilidad de DESARROLLO             (y en ellas solemos usar Assertions)
Las pruebas de integración son responsabilidad de DESARROLLO        (y en ellas solemos usar Assertions)
Las pruebas de sistema son responsabilidad de TESTING               (y en ellas solemos usar Expectativas o Should)

Al final.. que cada uno lo haga como quiera...
Siempre y cuando cumpla con los principios FIRST.

---
ESCENARIO: Crear un animalito con datos cojonudos (EN SISTEMA - end2end)
DADO:
    Tengo los datos cojonudos de un animalito en JSON y son:
    {
        "nombre": "Firulais",
        "especie": "Gato",
        "raza": "Siames",
        "edad": 3
    }
CUANDO:
    hago una llamada de tipo POST a la URL http://miaplicacion.com/api/v1/animalitos
    Y le mando en el cuerpo un JSON con los datos de un animalito cojonudo
ENTONCES:
    Entonces debe devolverme un código de estado HTTP 201 (Created)
    Y el cuerpo de la respuesta debe contener un JSON con los datos del animalito creado
    {
        "nombre": "Firulais",
        "especie": "Gato",
        "raza": "Siames",
        "edad": 3,
    }
    Y el animalito debe estar guardado en BBDD, con los mismos datos que le he mandado en el cuerpo de la petición
    Y además, en la bandeja pop/imap de la cuenta de correo electrónico de la lista de correo que sea, debe haber un email con el asunto "Nuevo animalito creado: Firulais" y el cuerpo del email debe aparecer el nombre del animalito, su especie, raza y edad.


```java
    public interface Animalito {
        String getNombre();
        String getEspecie();
        String getRaza();
        int getEdad();
    }
    // Cuando alguien haga una llamada a esta ruta (/api/v1/animalitos) por http, que se invoque esta función:
    public Animalito crearAnimalito(Animalito animalitoACrear) {

    }
```
Según requisitos, al solicitar el alta de un animalito, debe guardarse en BBDD y mandarse un email a una lista de correo.

Hoy en día, entendemos que PRUEBA = REQUISITO
No son cosas separadas.
La prueba define el requisito.

---
ESCENARIO: Recuperar los datos de un animalito por su NOMBRE que si existe.
DADO:
    Tengo los datos cojonudos de un animalito en JSON y son:
    {
        "nombre": "ALEATORIO DE 16 CARACTERES",                                                   OPCION 2.
        "especie": "Gato",
        "raza": "Siames",
        "edad": 3
    }
    Y dado que hago una llamada de tipo POST a la URL http://miaplicacion.com/api/v1/animalitos
    Y le mando en el cuerpo un JSON con los datos de un animalito cojonudo
CUANDO:
    Llamo a la URL http://miaplicacion.com/api/v1/animalitos/<NOMBRE ALEATORIO QUE HE GENERADO>  con una petición de tipo GET
ENTONCES:
    Debería de recibir en el cuerpo de la respuesta un JSON con los datos del animalito:
    {
        "nombre": "<NOMBRE ALEATORIO QUE HE GENERADO>",
        "especie": "Gato",
        "raza": "Siames",
        "edad": 3
    }
    Y el código de estado HTTP debería ser 200 (OK)
NOTA: Borrese el dato al acabar.                                                                    OPCION 1.
---
ESCENARIO: Tratar de recuperar los datos de un animalito por su nombre que NO EXISTE.


F - Fast
I - Independent
R - Repeatable
S - Self-validating
T - Timely


---

Cuando vamos a definir una prueba... sobre una característica del sistema/componente siemrpe empezamos por lo que llamamos el happy path o camino feliz. Es decir, el escenario en el que todo va bien... y el sistema se comporta de la forma mas sencilla y limpia posible.

Una vez tenemos el happy path, empezamos a torpedearlo... a meterle casos raros, casos límite, casos de error... para tratar de encontrar defectos en el sistema.

Pues ahora un animalito sin nombre... a ver si explota! DEBE EXPLOTAR.
Y ahora con un nombre mu largo! a ver  si explota... y DEBE EXPLOTAR (de forma controlada)
Y ahora con un nombre repetido. A ver si explota... y DEBE EXPLOTAR (de forma controlada)

Siempre muy importante tener claro en contexto en el que planteo la prueba: UNITARIA, INTEGRACION o SISTEMA!
Y cuidado aquí... porque una misma prueba puede ser de sistema o unitaria depende la persona que la ejecute.
Esto es más menos el concepto de PLANK... SOLO EL HECHO DE MIRAR UN SISTEMA LO ESTA PERTURBANDO.
El punto de vista afecta a la clasificación de la prueba.


---

Soy el fabricante de biciletas.
Me llega el sistema de frenos.
Lo monto en un bastidor con el sendosor y aprieto la palanquita!
TIPO DE PRUEBA: UNITARIA.

Soy el fabricante del sistema de frenos.
Cojo los cables , las pinzas, las palanquitas... lo arrejunto todo, y lo monto en un bastidor, con un sensor de presion.
Y aprieto la palanquita.
TIPO DE PRUEBA: SISTEMA

Tampoco me tengo que obsesionar con los nombres...
Más importante es entender realmente qué quiero probar!

---

Mañana vamos a diseñar un MICROSERVICIO que se encargue de hacer un CRUD de animalitos. Por http rest.
No lo vamos a implementar... no estamos en un curso de desarrollo. La implementación OS LA VOY A PASAR YO.
JAVA + SPRINGBOOT

Y antes de la implementación, vamos a definir las pruebas.
Vamos a ver qué componentes (sillín , rueda, sistema de frenos) nos hace falta para montar el microservicio.
Y una vez tengamos los componentes, planteamos pruebas.

Veremos... qué pruebas unitarias salen
Qué pruebas de integración salen.
Que sensores y bastidores me hace falta comprar (o soldar yo)
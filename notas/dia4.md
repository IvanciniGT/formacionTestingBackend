- Repaso de conceptos de testing
  - Vocabulario
  - Tipos de pruebas
  - Para que sirven (contexto actual)
- Diseño de pruebas, principios First
- TDD y de cómo la aplicamos hoy en día
- Test doubles: mocks, stubs, fakes, spies, dummies
- Hicimos código, aplicando TDD... para un backend de Animalitos.
- Jugando con Junit, Mockito (Maven)
- Devops, pipelines de integración continua

---

- Selenium
- Cucumber
- Jenkins / Github Actions
- SonarQube / Jacoco (cobertura de código)
              Java Code Coverage 

---

# Selenium

Es una herramienta que NO ES DE PRUEBAS!
Selenium lo que nos permite es automatizar trabajos e un navegador web.

Navegador WEB <-> Frontal

Pero estamos en un curso de testing de backend, entonces... como encaja Selenium?

En la mayor parte de los casos, el backend se monta en paralelo con un frontal web.
- Haremos pruebas unitarias y de integración a nuestros componentes de backend.
- Pero otro cantar son las pruebas de sistema (end2end), donde el sistema se prueba en su conjunto, con todos sus componentes integrados, y con un frontal web.

Este tipo de pruebas se suelen realizar por el equipo de Q&A(Testers)

Selenium es una de muchas herramientas de este tipo, que permiten automatizar trabajos dentro de un navegador.
De hecho ni siquiera es la primera... Su nombre SELENIUM se lo pusieron porque antes había una herramienta horriblemente dificil de manejar llamada "Mercury" (Mercurio), y el selenio es un elemento químico que se usaba para tratar el mercurio...

Se base en un estandar del W3C.
W3C es un consorcio, fundado por el creador el concepto de LA WEB (Tim Berners Lee), que se encarga de definir los estandares de la web, para que todos los navegadores puedan entenderse entre ellos, y con las páginas web.. y para más cosas.

## WEB / INTERNET

Internet es un conjunto de redes descentralizado que permite comunicaciones entre computadoras a nivel mundial.
La web es uno de los servicios que usamos que aprovechan Internet, pero no es el único. 
Otros servicios que usan internet:
- Correo electrónico
- Telefonía VozIP
- Mensajería instantánea
- Juegos online
- Televisión online
- Streaming de música
- Videoconferencias


- La web, cuando la crea Tim Berners Lee, son 2 cosas:
    - Un lenguaje apra escribir documentos que sean fáciles de leer por humanos dentro de un navegador web (HTML)
    - Un protocolo para comunicar (enviar/recibir) esos documentos entre un servidor y un cliente (HTTP)

## Estándares del W3C

Hay muchos:
- HTML (HyperText Markup Language)
- HTTP (HyperText Transfer Protocol)
- CSS (Cascading Style Sheets)
- XML (eXtensible Markup Language)
- XPATH (XML Path Language)
- WebDriver (ofrece una forma en la que debe diseñarse una funcionalidad muy concreta: la automatización de tareas dentro de un navegador web)

El estandar web driver ofrece un API para controlar un navegador web, de forma que podamos automatizar tareas dentro de él.

Automatizar tareas dentro de un navegador:
- Abrir una pestaña
- Navegar a una URL
- Rellenar un formulario
- Hacer click en un botón / enlace
- Leer el contenido de una página web
- Hacer scroll
- Hacer capturas de pantalla
- Etc...

Es decir, puedo crear un programa que haga esas cosas por mi.
Selenium ayuda con ese tema.


    Navegador Web                                           Driver compatible   Selenium Librería               SCRIPT DE SELENIUM
     - Chrome       |   Cualquier de ellos              |   chromedriver      | Selenium Webdriver JAVA     |
     - Firefox      |   abres un puerto de              |   geckodriver       | Selenium Webdriver C#       |   Mi programa usará:
     - Edge         |   local.                          |   msedgedriver      | Selenium Webdriver Python   |     - Selenium Webdriver para 
     - Safari       |   A través de ese                 |                     | Selenium Webdriver JS       |       autoamtizar trabajos.
     - Opera        |   puerto, puedo mandar            |                     | Selenium Webdriver          |     - Luego necesito un framework de 
     - etc...       |   instrucciones al navegador      |                     |                             |       pruebas: JUnit, TestNG...
                    <-                                  <-                   <-                            <-

Esta es la forma GUAY de usar Selenium. Pero no es la única. AUNQUE SI LA QUE MAS NOS GUSTA ..y LA QUE PREFERIMOS... y la que QUEREMOS APRENDER/USAR.

Pero, hay herramientas gráficas que nos ayudan a crear scripts de Selenium sin necesidad de programar... más o menos.

La oficial del equipo de Selenium se llama: Selenium IDE (Integrated Development Environment)
Es un poco castaña... aunque para empezar a aprender Selenium, es una buena opción, porque es muy visual y fácil de usar.

Otras herramientas más profesionales en la misma linea serían:
- Katalon recorder
- Katalon Studio

La realidad es que cuando tenemos un script hecho... a partir de ahí es copia/pega... no merecen la pena esas herramientas. Tardamos más y tenemos peores resultados que si creo el programa a mano.

Dado que selenium lo podemos manejar desde muchos lenguajes de programación, hemos de elegir uno.
En mi caso, elegiré JAVA (simplemente porque ya hemos estado haciendo cosas en JAVA).
Muy habitual es usar Python, porque es un lenguaje muy sencillo de aprender y de usar

No tiene nada que ver el lenguaje de programación con el que he montado el sistema con el lenguaje de programación con el que hago las pruebas. Puedo montar un sistema en JAVA y hacer las pruebas con Python, o viceversa.

Os daré código en JAVA y PYTHON
---

Los scripts de selenium van en 2 partes:
1. Identificar los elementos de la página web con los que quiero interactuar (localizadores)
2. Interactuar con esos elementos (acciones)

Cómo identificamos un elemento?
- Selenium tiene acceso a lo que llamamos el DOM (Document Object Model) de la página web.
- El DOM es lo que el navegador tiene cargado en RAM de la web...
- Es similar al HTML que devuelve la página.. pero no es exactamente lo mismo.
- Hay trozos de la página que se pueden ir generando/modificando por programas JS.

Sobre el DOM es sobre lo que buscamos el elemento concreto con el que queremos interactuar.
Y hay varias formas de buscarlo:
- La mejor de todas, con diferencia, es mediante un ID.
- Si no tuviera identificador? AQUI YA ENTRAMOS EN GUARRADAS!
  - Puedo buscar por el elemento html cuyo texto es "Make Appointment"
    > Y eso funcionará HOY... como mañana cambien el texto, se romperá la prueba.
    > Y qué probabilidad hay de que ese texto cambié en el futuro? Yo diría del 100%
  - Puedo buscar por tipo de elemento: <a>
    > Y eso funcionará HOY... como mañana haya otro enlace o al desarrollador nuevo friki de turno
    > le apetezca usar un button, en lugar de un <a>, se romperá la prueba.
  - Puedo buscar por el estilo css que se está aplicando: "btn btn-dark btn-lg"
    > Y eso funcionará HOY... SI ES QUE NO HAY MAS COSAS CON E MISMO ESTILO EN PANTALLA.
    > Mañana.. veremos.

Y esto es lo jodido de Selenium.. Es muy fácil hacer pruebas automatizadas que funcionen hoy.. lo complejo es hacer pruebas que sigan funcionando mañana, pasado, dentro de un mes, dentro de un año... y eso es lo que realmente nos interesa.

Siempre prioridad al ID. Si no lo hay, lo pido antes de hacer la prueba.
Si no me lo ponen, me niegop a trabajar: HUELGA
Si siguen sin ponerlo paso al siguiente nivel: HUELGA DE HAMBRE
Si siguen sin ponerlo, paso al siguiente nivel: COCTEL MOLotov
Y si ya no consigo GUARREO... Pero guarreo de la mejor manera posible: Intentando hacer el test lo menos fragil posible, aunque no tenga ID.
  - Búscame aquél elemento cuyo texto sea "Make Appointment"
  - Búscame aquel elemento que tenga el estilo "btn btn-dark btn-lg" y que sea un enlace <a>
  - Búscame el primer <a> que esté dentro del <div> que esté dentro del <header> que esté dentro del <body> de la página web.
  - Búscame el primer <a> que esté dentro del <div> que esté dentro del <header> que esté dentro del <body> de la página web y que tenga el estilo "btn btn-dark btn-lg" y cuyo texto sea "Make Appointment"
  - etc...

La probabilidad de que cambiuen algo que rompa la última es enorme. DESCARTADA.
En general, me centraré en un único criterio de búsqueda, no mezclo: textos, estilos, estructura html...
Ahora me pregunto... qué hay menos probabilidades de que cambie en el futuro?
- El texto?
- El nombre del estilo?
- La estructura html?

Y a esta pregunta solo yo puedo contestar... y usaré mi intuición! y mis experiencias pasadas!
La probabilidad con cualquier da ellas de que mi test se joda en el futuro es GRANDE!
Lo único que da estabilidad son los IDs.
Pero si no hay más remedio.. pues tendré que jugarmela!
PERO ME LA ESTOY JUGANDO! Y ya lo dijo Murphy: "Si algo puede salir mal, saldrá mal" (Ley de Murphy)
 Seguro que el est el día de mañana se jode.. por una mierda de cambio que hace alguien pensando en que no importaba!


 #btn-make-appointment                Sintaxis CSS
 //*[@id="btn-make-appointment"]      Sintaxis XPATH


 //*[text()='Make Appointment']
  * Cualquioer elemento
  // Que esté donde leches sea
  [] tal que
  text() su texto
  = sea igual a
  'Make Appointment' el texto que busco 

---

Para poder hacer test buenos con Selenium es IMPRESCINDIBLE que el equipo de desarrollo de la aplciación ponga IDs sobre básicamente TODO lo que haya en la pantalla. Esto hay que tenerlo en cuenta.. y hay que tenerlo hablado de antemano con el equipo de desarrollo. Si no, las pruebas van a ser muy difíciles de mantener.
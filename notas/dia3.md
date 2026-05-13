
# Metodologías ágiles

Dijimos que son una evolución de las metodologías tradicionales, que se basan en la planificación y el control (burocracia).
Las metodologías ágiles se caracterizan principalmente por entregar el producto al cliente de forma incremental (cada 2 semanas y 2 meses).
Esto se hace para conseguir feedback muy rápido del cliente... y que cuando haya que cambiar algo (y habrá que cambiarlo) no impacte demasiado en el producto.

Las metodologías ágiles han venido a resolver muchos problemas... pero también han traído otros nuevos.

Dia 0   -> Empiezo el proyecto
Día 30  -> Hago el primer paso a producción                         Entrego el R1, R2, R3
            + Instalación en un entorno de preproducción.
            + Pruebas a nivel de producción                             Pruebo el R1, R2, R3
            + Instalación en un entorno de producción.
Día 60  -> Hago el primer paso a producción                         Entrego el R4, R5, R6
            + Instalación en un entorno de preproducción.
            + Pruebas a nivel de producción                             Pruebo el R4, R5, R6 + R1, R2, R3
            + Instalación en un entorno de producción.
Día 90  -> Hago el primer paso a producción                         Entrego el R7, R8, R9
            + Instalación en un entorno de preproducción.
            + Pruebas a nivel de producción                             Pruebo el R7, R8, R9 + R4, R5, R6 + R1, R2, R3
            + Instalación en un entorno de producción.
Día 120 -> Hago el primer paso a producción
            + Instalación en un entorno de preproducción.
            + Pruebas a nivel de producción
            + Instalación en un entorno de producción.
Día 150 -> Hago el primer paso a producción
            + Instalación en un entorno de preproducción.
            + Pruebas a nivel de producción -> Informe
            + Instalación en un entorno de producción.

Y así hasta que acabe.. si es que acabo. Un producto de software es un ente vivo... que evoluciona constantemente... y que nunca se da por terminado.

Las instalaciones cuando trabajo con metodologías ágiles se multiplican.
Pero las pruebas.... ojo a las pruebas! Crecen exponencialmente.

Y aquí sale una pregunta... de dónde sale la pasta? y los recursos? y el tiempo? para hacer todas esas pruebas e instalaciones?
Que antes no había que hacer!

Y la respuesta es fácil: NI HAY PASTA, NI HAY RECURSOS, NI HAY TIEMPO.
Y entonces? Lo único que puedo hacer es AUTOMATIZAR. Automatizar qué? Todo lo que pueda!
Antes, no tenía sentido. Solo hacía pruebas una vez. Tardo menos en hacerlas a mano que en automatizarlas.
Antes, solo instalaba una vez. Tardo menos en hacerlo a mano que en automatizarlo.

Pero ahora, o automatizo o muero!

Es imposible adoptar una metodología ágil sin abrazar una cultura devops.
---

# DEVOPS

Devops es una cultura, un movimiento, una filosofía... en pro de LA AUTOMATIZACION DE TODO LO AUTOMATIZABLE EN EL CICLO DE VIDA DEL SOFTWARE desde el DEV -> OPS.
    Vamos chic@s.. automatizar! que aquí nos gusta eso!

Tareas:                             Automatizables?                     Herramienatas
    Plan                                POCO
    Code                                Digamos que poco
                                        IAs, Plataformas LowCode
    Build                               TOTALMENTE
                                                                        Java:   Maven, Gradle
                                                                        JS:     NPM, YARN, Webpack
                                                                        C#:     MSBuild, dotnet, nuget
                                                                        C:      Make, Autotools
                                                                        Apache(Ant)
    Test
        Diseño de la prueba             Digamos que poco
        Ejecución de la prueba          TOTALMENTE
                                        Salvo pruebas muy concretas (UX)
                                                                        Frameworks de pruebas:
                                                                            JAVA:  JUnit, TestNG, Mockito
                                                                            C#:     NUnit, xUnit, Moq
                                                                            JS:     Jest, Mocha, Chai, Sinon, Jasmine
                                                                            Python: unittest, pytest, mock
                                                                        Pruebas de una app web en un navegador:
                                                                            Selenium, Cypress, Playwright, WebDriverIO
                                                                        Pruebas de un app mobil:
                                                                            Appium, Espresso, XCUITest
                                                                        Pruebas de rendimiento:
                                                                            JMeter, Gatling, LoadRunner, Locust
                                                                        Pruebas de seguridad:
                                                                            OWASP ZAP, Burp Suite, Nessus, Acunetix
                                                                        Pruebas de calidad de código:
                                                                            SonarQube, CodeClimate, Codacy, Coverity

PROCESO AUTOMATIZABLE: ORQUESTO Todas esas automatizaciones de tareas individuales para crear un proceso automatizado.
En cuanto un desarrollador haga commit, quiero que:
- Se extraíga el código del repositorio en un entorno efímero.
- Se compile el código.
- Se ejecuten las pruebas unitarias.
- Se ejecuten las pruebas de integración.
- Se mande al sonarqube
- Que se borre el entorno efímero.
----------------------------------------------> Integración continua!
Tener CONTINUAmente la última versión del código en el entorno de INTEGRACION sometida a pruebas automatizadas.
¿Cuál es el producto de un PROCESO/SCRIPT/PIPELINE de integración continua? Un INFORME con el resultado de las pruebas, generado en automático. 
Para qué querría yo un informe de pruebas en tiempo REAL? VER DIA 1: Para que hostias sirven las pruebas!!!

        Pero esas pruebas dónde se hacen?
            En la máquina del desarrollador? Me fío de esa máquina y de esas pruebas? NO... porque su máquina está MALEA!
            En la máquina del tester?        Me fío de esa máquina y de esas pruebas? NO... porque su máquina está MALEA!
            Entonces qué hacemos? Tener un entorno de pruebas? Tampoco! Eso antes.
                Cuando trabajábamos con met. tradicionales. Porque cuantás veces instalaba en ese entorno? 1-3 al final del proyecto.
                Pero hoy en día.. con las met. ágiles, cuántas veces instalo ahí? Cada 2 /3 semanas.
                Y después de 15 instalaciones, sabéis como está ese entorno?  MALEAO! Ya no me vale esta estrategia.
            Hoy en día tenemos entornos de pruebas EFIMEROS. De usar y tirar. Como los kleenex.
            Voy a hacer pruebas:
                - Creo entorno
                - Instalo
                - Hago pruebas
                - Destruyo entorno
              Podemos automatizar la creación y destrucción de entornos de pruebas ? YA VES!
                                                                        Terraform, CloudFormation
                                                                        Ansible, Chef, Puppet, SaltStack
                                                                        Kubernetes, Docker 
    Release
        Poner en manos de mi cliente la última versión probada del sistema.
                        TOTALMENTE
---------------------------------------------> Si automatizo hasta aquí? ENTREGA CONTINUA: Continuous Delivery!
    Deploy              TOTALMENTE
---------------------------------------------> Si automatizo hasta aquí? DESPLIEGUE CONTINUO: Continuous Deployment!
    Operate             TOTALMENTE
    Monitor             TOTALMENTE
-----------------------------------------------> Si automatizo hasta aquí? DEVOPS !

Esos pipelines/scripts de integración continua, entrega continua, despliegue continuo... los montamos con herramientas como:
- Jenkins, 
- GitLab CI/CD, 
- CircleCI, 
- TravisCI, 
- Azure DevOps, 
- AWS CodePipeline,
- TeamCity,
- Bamboo,
- GitHub Actions

La persona que configrua esos pipelines/scripts de integración continua, entrega continua, despliegue continuo... es la que empezó a llamarse DevOps Engineer.

Hoy en día se ha desrvirtuado el nombre.. y se llama devops a cualquier sysadmin que use ansible, terraform o trabaje contra clouds.


Antes un tester, su trabajo era ejecutar manualmente pruebas.
Hoy en día, un tester, su trabajo es programar... crear programas/configurarlos que prueben otros programas: AUTOMATIZAR las pruebas!

Antes un sysadmin, su trabajo era administrar sistemas.
Hoy en día, un sysadmin, su trabajo es programar... crear programas/configurarlos que administren otros sistemas: AUTOMATIZAR la administración de sistemas!

Antes un desarrollador, parte de su trabajo era compilar, empaquetar, integrar dependencias (descargar librerías).
Hoy en día, un desarrollador, parte de su trabajo es crear programas/configurarlos que compilen, empaqueten, integren dependencias: AUTOMATIZAR el empaquetado del software!

ESTE ES EL MUNDO DE HOY EN DIA!

Hoy en día AUTOMATIZAMOS LA EJECUCION DE LAS PRUEBAS. No tiene sentido no hacerlo.
NO PUEDO PERMITIRME NO HACERLO... Las pruebas han crecido exponencialmente debido a las metodologías ágiles... y no tengo pasta, ni recursos, ni tiempo para hacerlas manualmente... así que las automatizo!

---

Luego hay una segunda derivada. Los sistemas cada vez son más complejos.

Imaginad que hago una app web para un ayuntamiento... que la gente pueda descargar un certificado de empadronamiento.
Le hago pruebas a mano... decido no automatizar. OK....
En qué navegador hago las pruebas? Edge, Opera, Firefox, Safari, Chrome...
Y en qué versión de cada uno?      Al menos en las 5 últimas versiones de cada uno... porque la gente no actualiza el navegador a la última versión... y si no hago pruebas en esas versiones... me pueden salir errores que no detecté en las pruebas.
Sobre qué sistema operativo?    Windows, MacOS, iOS, Android, Linux?
Sobre qué versiones de esos sistemas operativos? Windows 10, Windows 11, Windows ?
Sobre qué tipo de dispositivo / resolución?
Portátil, sobremesa, tablet, móvil... con resoluciones de pantalla distintas.
No tiene nada que ver un Huawei de 90€ con un Samsung o un Iphone de 1500€...en resolución de pantalla.

Ahora repito la pruebas... en serio voy a hacer las pruebas a mano? NO ACABO EN LA VIDA!
Opciones:
- Paso.. y hago pruebas en 2 navegadores en la versión que tengo de ellos en mi portatil.
- Automatizo y hago pruebas intensivas... contra una granja de máquinas. que me alquilan por 300-800€/mes...
- con hasta 2000 combinaciones de navegador/sistema operativo/dispositivo... y que me hagan las pruebas en paralelo... y me den un informe con los resultados.

# Estamos hablando de automatizar pasos a producción!
Antes había gente (testers, cliente) que revisaban profundamente un sistema antes de su paso a producción.
Ahora estamos hablando de que cuando un desarrollador haga commit, en 25 minutos su cambio esté en producción sin mediación humana.
Una de 2:
- O estoy mu volao
- O tengo una confianza brutal en el desarrollo/producto <- PRUEBAS!

Antiguamente todo esto de las pruebas nos lo tomábamos a cachondeo.

Extreme Programming -> Pair Programming : un desarrollador + un tester por puesto de trabajo, con un único ordenador... 4 manos + 4 ojos 
---

# Automatizar

Crear una máquina (o cambiar el comportamiento de una mediante un programa) para que haga lo que antes hacía un humano con sus manitas.

Puedo automatizar el lavado de la ropa:
- LAVADORA (a la que incluso puedo ajustarle el comportamiento con programas: PROGRAMAS DE LAVADO - prendas delicadas, ropa ded algodón calentita a 90º, ropa de color a 40º, etc...)

En nuestro mundo (IT) la máquina la tenemos: COMPUTADORA.
Automatizar es tener programas ejecutándose en la computadora que hagan lo que antes hacía un humano con sus manitas.
- A veces creo programas.
- A veces configuro programas ya hechos para que hagan lo que yo quiero.

Pregunta. Si creo una lavadora... ya tengo el proceso de lavado de la ropa automatizado? NO
Tengo automatizada una tarea: El lavar la ropa.
El cargar la lavadora? NO
El seleccionar la ropa por tipo/color...? NO
El darle al botón de play? NO

> Tengo una persiana. Que sube con su cuerdita.

Le cambio la cuerdita por un motor y un botón.

He automatizado la tarea de subir la persiana? SI
Lo que no he automatizado es el proceso de subir la persiana. Una tarea de ese procesi si está automatizada: El subir la persiana. 

Pero quién le da al botón? para que suba? YO

Lo que pasa es otra cosa...
Ahora que ya tengo automatizada una tarea... puedo plantearme automatizar el proceso!
Puedo poner un sensor de luz... que AUTOMATICE otra tarea: Mirar si hay luz o no...
Y si no hay luz: Que baje la persiana... 
Y si hay luz: Que suba la persiana.

O que la suba y baje a ciertas horas.. incluso dependiendo de la fecha.

Si monto eso ^^^^ ya tengo automatizado el proceso de subir y bajar la persiana? SI

Ahora bien.. tendría sentido haber planteado este segundo nivel de automatización (automatizar el proceso) si no tuviera el primer nivel de automatización (automatizar las tareas: Monatr un motor)? NO

La automatización va por fases:
- Primero automatizo tareas
- Y cuando tengo las tareas automatizadas... me planteo automatizar el proceso.
---

Vamos a empezar a crear pruebas de verdad en código (Java, Junit, Mockito) + MAVEN


---

TDD: Test Driven Development
Desarrollo guiado por pruebas

1. Escribo la prueba más simple que pueda... y que falle
2. Escribo el código mínimo para que esa prueba pase
3. Refactorizo el código para que sea bueno

Y de nuevo.

---

> Un producto de software por definición es un producto sujeto a cambios y mantenimiento.

Que un programa funcione, se da por descontado! Es lo de menos.
Si no funciona, de hecho, lo que tienes no es un programa... es un archivo de texto con pretensiones.

La clave es que sea fácilmente mantenible y evolucionable.

> Tenemos claro que un coche es un producto sujeto a mantenimiento.

Que el coche ande es lo de menos... se da por descontado.
Coñó si no anda.. no es un coche... es una escultura de hierro con ruedas.

    Escribo código <> Pruebas -> OK -> Refactorización <> Pruebas -> OK -> libero
    <------ 50% del trabajo ------>    <------- 50% del trabajo ------>
                8 horas                             8 horas  


     Sistema-Mensajeria-API   < Sistema-Mensajeria-Impl
              ^
     Animalitos-Service-API   > Animalitos-Repository-API < Animalitos-Repository-Impl
              ^
     Animalitos-Service-Impl



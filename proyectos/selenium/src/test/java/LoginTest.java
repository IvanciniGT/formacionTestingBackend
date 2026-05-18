
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class LoginTest {

    private WebDriver navegador;
    private WebDriverWait espera;

    @BeforeAll // Ejecutate antes de hacer las pruebas
    static void prepararPruebas(){
        // Vamos a crear una carpeta para guardar las capturas de pantalla, si no existe ya
        File carpetaCapturas = new File("capturas");
        if (!carpetaCapturas.exists()) {
            carpetaCapturas.mkdir();   
        } 
    }

    // los navegadores los podemos abrir en modo "headless", es decir, sin mostrar la ventana, 
    // 1. para que no molesten mientras se ejecutan las pruebas.
    // 2. porque estas pruebas se ejecutarán finalmente en un entorno sin interfaz gráfica, como un servidor linux.
    //    No habrá ventana, pero el navegador seguirá funcionando y podremos interactuar con él. Incluso tomar capturas de pantalla.
    //    En un navegador en modo headless, la "ventana" se pinta en memoria, pero no se muestra en la pantalla.


    void tomarCaptura(WebDriver navegador, String funcion, String trabajoCocnreto) {
        // Generamos un timestamp, con formato: año-mes-dia-hora-minuto-segundo
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
        // Concatenamos el timestamp con el nombre de la función y el trabajo concreto, para generar un nombre de archivo único
        String nombreArchivo = funcion + "-" + trabajoCocnreto + "-" + timestamp + ".png";
        // Tomamos la captura de pantalla y la guardamos en la carpeta "capturas" con el nombre que nos han dado
        File captura = ((TakesScreenshot) navegador).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(captura.toPath(), new File("capturas/" + nombreArchivo).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach // Ejecutate antes de cada prueba
    void prepararNavegador() {
        // Dado que tengo un navegador chrome
        // Puedo abrir el navegador con algunas opciones de configuración, como el modo headless.
        // No todos los navegadores admiten las mismas opciones, así que hay que revisar la documentación de cada uno para ver qué opciones tiene.
        ChromeOptions opciones = new ChromeOptions();
        opciones.addArguments("--headless=new"); // Modo headless, sin mostrar la ventana. (en versiones anteriores de chrome era "--headless" a secas)
        // Más cosas:
        // opciones.addArguments("--incognito"); // Modo incógnito, sin guardar cookies ni historial.
        // opciones.addArguments("--start-maximized"); // Abrir el navegador maximizado.
        // opciones.addArguments("--disable-gpu"); // Deshabilitar la aceleración por hardware, que a veces da problemas en modo headless.
        opciones.addArguments("--window-size=1920,1080"); // Establecer el tamaño de la ventana, que en modo headless no se establece automáticamente.
        navegador = new ChromeDriver(opciones);
        espera = new WebDriverWait(navegador, Duration.ofSeconds(10)); // Le configuramos un timeout (límite).
        // y que el usuario va a la pantalla de home de la app:  https://katalon-demo-cura.herokuapp.com/
        navegador.get("https://katalon-demo-cura.herokuapp.com/");
    }

    @AfterEach // Ejecutate después de cada prueba
    void cerrarNavegador() {
        // Cerramos el navegador
        navegador.quit();
    }


    @Test
    void loginHappyPath() {
        // Hace clic en el botón "Make Appointment"
        // Puedo buscar el elemento por su id: btn-make-appointment
        // navegador.findElement(By.id("btn-make-appointment")).click();
        // Puedo buscarlo por selector css: #btn-make-appointment
        // navegador.findElement(By.cssSelector("#btn-make-appointment")).click();
        // Puedo buscarlo por su id con sintaxis de xpath: //*[@id="btn-make-appointment"]
        // navegador.findElement(By.xpath("//*[@id=\"btn-make-appointment\"]")).click();
        // Puedo buscarlo por el texto que muestra el botón: "Make Appointment"
        navegador.findElement(By.xpath("//*[text()='Make Appointment']")).click();
        // Entonces se muestra la pantalla de login: https://katalon-demo-cura.herokuapp.com/profile.php#login
        // Y el título de la pantalla es: "Login"
        //String urlALaQueLlego = navegador.getCurrentUrl();
        // Y ahora viene la prueba!
        //Assertions.assertEquals("https://katalon-demo-cura.herokuapp.com/profile.php#login", urlALaQueLlego);
        espera.until(ExpectedConditions.urlToBe("https://katalon-demo-cura.herokuapp.com/profile.php#login"));
        String textoDelTitulo = navegador.findElement(By.xpath("//h2")).getText();
        Assertions.assertEquals("Login", textoDelTitulo);
        // Rellena el campo de username con: John Doe
        navegador.findElement(By.id("txt-username")).sendKeys("John Doe");
        // Rellena el campo de password con: ThisIsNotAPassword
        navegador.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
        // Antes de hacer click, voy a tomar una foto de la pantalla, con los datos rellenos:
        tomarCaptura(navegador, "loginHappyPath", "conLosDatosRellenos");    
        navegador.findElement(By.id("btn-login")).click();
        // Llegamos a una pantalla para solicitar una cita: https://katalon-demo-cura.herokuapp.com/#appointment
        // Hay que darle tiempo... a que cargue la página!
        // Forma cutre! Pausar el hilo de ejecución durante 3 segundos
        // try {
        //    Thread.sleep(3000);
        // } catch (InterruptedException e) {}
        // para hacelo bien, creamos un "espera explícita" que espera hasta que se cumpla una condición, o hasta que pase un tiempo máximo
        // Usamos WebDriverWait, que es una clase que nos permite crear esperas explícitas.
        // Habitualmente la instanciamos arriba, para poder reutilizarla en varios métodos.
        //urlALaQueLlego = navegador.getCurrentUrl();
        //Assertions.assertEquals("https://katalon-demo-cura.herokuapp.com/#appointment", urlALaQueLlego);
        // Ya no lo voy a configurar como un assert... sino como una espera basada en una condición
        try{ // El de dentro puede dar error.
            espera.until(ExpectedConditions.urlToBe("https://katalon-demo-cura.herokuapp.com/#appointment"));
        } finally { // De error o no, quiero capturar la pantalla, para ver qué ha pasado.
            tomarCaptura(navegador, "loginHappyPath", "despuesDeLogin");    
        }

        // Hay muchos otros tipos de condiciones: 
        // espera.until(ExpectedConditions.visibilityOfElementLocated(By.id("elemento")));
        // espera.until(ExpectedConditions.textToBe(By.xpath("//h2"), "Make Appointment"));
        // Por titulo de la página: (lo que sale en la pestaña)
        // espera.until(ExpectedConditions.titleIs("Make Appointment"));
        // Eso esperará el tiempo necesario.. puede ser 1 segundo, medio o 5 segundos... 
        // Nunca más de 10 segundos, porque ese es el timeout que le configuramos al crear la instancia de WebDriverWait.
        // si al llegar a 10 segundos no estamos en la url esperada, bien porque no haya contestado el servidor, 
        // o porque hayamos llegado a una url diferente, entonces la espera lanzará una excepción y el test fallará.

        // cuyo título es: Make Appointment
        textoDelTitulo = navegador.findElement(By.xpath("//h2")).getText();
        Assertions.assertEquals("Make Appointment", textoDelTitulo);
    }

    @Test // Meter mal la contraseña, pero bien el usuario.
    void loginWrongPasswordCorrectUser() {
        navegador.findElement(By.xpath("//*[text()='Make Appointment']")).click();
        espera.until(ExpectedConditions.urlToBe("https://katalon-demo-cura.herokuapp.com/profile.php#login"));

        String textoDelTitulo = navegador.findElement(By.xpath("//h2")).getText();
        Assertions.assertEquals("Login", textoDelTitulo);
        // Rellena el campo de username con: John Doe
        navegador.findElement(By.id("txt-username")).sendKeys("John Doe");
        // Rellena el campo de password con: ThisIsNotAPassword
        navegador.findElement(By.id("txt-password")).sendKeys("RUINA!");
        // Antes de hacer click, voy a tomar una foto de la pantalla, con los datos rellenos:
        tomarCaptura(navegador, "loginWrongPasswordCorrectUser", "conLosDatosRellenos");
        navegador.findElement(By.id("btn-login")).click();

        //<p class="lead text-danger">Login failed! Please ensure the username and password are valid.</p>
        try{ // El de dentro puede dar error.
            espera.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Login failed')]")));
        } finally { // De error o no, quiero capturar la pantalla, para ver qué ha pasado.
            tomarCaptura(navegador, "loginWrongPasswordCorrectUser", "despuesDeLogin");    
        }
    }
    // Meter mal el usuario, pero no la contraseña
    // Meter mal ambos
    // Usuario bueno y password vacio
    // Usuario vacio y password bueno
    // usuario y password vacios
    

}

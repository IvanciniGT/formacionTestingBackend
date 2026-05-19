
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.util.stream.Stream;
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

    // Fuente de navegadores para @ParameterizedTest.
    // El Grid enruta cada sesión al nodo con el navegador pedido → cada test se ejecuta en Chrome, Edge y Firefox a la vez.
    static Stream<MutableCapabilities> navegadores() {
        // los navegadores se abren en modo headless (sin ventana) porque corren dentro de contenedores Docker sin GUI.
        ChromeOptions chrome = new ChromeOptions();
        chrome.addArguments("--headless=new", "--window-size=1920,1080");

        EdgeOptions edge = new EdgeOptions();
        edge.addArguments("--headless=new", "--window-size=1920,1080"); // Edge es Chromium, mismas flags

        FirefoxOptions firefox = new FirefoxOptions();
        firefox.addArguments("-headless", "--width=1920", "--height=1080"); // Firefox usa -headless (un guión)

        return Stream.of(chrome, edge, firefox);
    }


    void tomarCaptura(String funcion, String trabajoCocnreto) {
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

    // @ParameterizedTest + @MethodSource hacen que JUnit ejecute este test una vez por cada navegador
    // devuelto por navegadores(). El Grid recibe las 3 sesiones y las distribuye a chrome/edge/firefox en paralelo.
    @ParameterizedTest
    @MethodSource("navegadores")
    void loginHappyPath(MutableCapabilities opciones) throws Exception {
        navegador = new RemoteWebDriver(new URL("http://localhost:8082"), opciones);
        espera = new WebDriverWait(navegador, Duration.ofSeconds(10));
        navegador.get("https://katalon-demo-cura.herokuapp.com/");
    try {
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
        tomarCaptura("loginHappyPath", "conLosDatosRellenos");    
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
            tomarCaptura("loginHappyPath", "despuesDeLogin");    
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
    } finally {
        navegador.quit();
    }
    }

    @ParameterizedTest // Meter mal la contraseña, pero bien el usuario.
    @MethodSource("navegadores")
    void loginWrongPasswordCorrectUser(MutableCapabilities opciones) throws Exception {
        navegador = new RemoteWebDriver(new URL("http://localhost:8082"), opciones);
        espera = new WebDriverWait(navegador, Duration.ofSeconds(10));
        navegador.get("https://katalon-demo-cura.herokuapp.com/");
    try {
        navegador.findElement(By.xpath("//*[text()='Make Appointment']")).click();
        espera.until(ExpectedConditions.urlToBe("https://katalon-demo-cura.herokuapp.com/profile.php#login"));

        String textoDelTitulo = navegador.findElement(By.xpath("//h2")).getText();
        Assertions.assertEquals("Login", textoDelTitulo);
        // Rellena el campo de username con: John Doe
        navegador.findElement(By.id("txt-username")).sendKeys("John Doe");
        // Rellena el campo de password con: ThisIsNotAPassword
        navegador.findElement(By.id("txt-password")).sendKeys("RUINA!");
        // Antes de hacer click, voy a tomar una foto de la pantalla, con los datos rellenos:
        tomarCaptura("loginWrongPasswordCorrectUser", "conLosDatosRellenos");
        navegador.findElement(By.id("btn-login")).click();

        //<p class="lead text-danger">Login failed! Please ensure the username and password are valid.</p>
        try{ // El de dentro puede dar error.
            espera.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Login failed')]")));
        } finally { // De error o no, quiero capturar la pantalla, para ver qué ha pasado.
            tomarCaptura("loginWrongPasswordCorrectUser", "despuesDeLogin");    
        }
    } finally {
        navegador.quit();
    }
    }
    // Meter mal el usuario, pero no la contraseña
    // Meter mal ambos
    // Usuario bueno y password vacio
    // Usuario vacio y password bueno
    // usuario y password vacios
    

}

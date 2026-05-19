
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

class MakeAppointmentTest {

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

    // Fuente de navegadores para @ParameterizedTest.
    // El Grid enruta cada sesión al nodo con el navegador pedido → cada test se ejecuta en Chrome, Edge y Firefox a la vez.
    static Stream<MutableCapabilities> navegadores() {
        ChromeOptions chrome = new ChromeOptions();
        chrome.addArguments("--headless=new", "--window-size=1920,1080");

        EdgeOptions edge = new EdgeOptions();
        edge.addArguments("--headless=new", "--window-size=1920,1080");

        FirefoxOptions firefox = new FirefoxOptions();
        firefox.addArguments("-headless", "--width=1920", "--height=1080");

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

    @ParameterizedTest
    @MethodSource("navegadores")
    void makeAppointmentHappyPath(MutableCapabilities opciones) throws Exception {
        navegador = new RemoteWebDriver(new URL("http://localhost:8082"), opciones);
        espera = new WebDriverWait(navegador, Duration.ofSeconds(10));
        navegador.get("https://katalon-demo-cura.herokuapp.com/");
        try {
        // ── Login previo ──
        navegador.findElement(By.xpath("//*[text()='Make Appointment']")).click();
        espera.until(ExpectedConditions.urlToBe("https://katalon-demo-cura.herokuapp.com/profile.php#login"));
        String textoDelTitulo = navegador.findElement(By.xpath("//h2")).getText();
        navegador.findElement(By.id("txt-username")).sendKeys("John Doe");
        navegador.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
        tomarCaptura("loginHappyPath", "conLosDatosRellenos");    
        navegador.findElement(By.id("btn-login")).click();
        try{ // El de dentro puede dar error.
            espera.until(ExpectedConditions.urlToBe("https://katalon-demo-cura.herokuapp.com/#appointment"));
        } finally { // De error o no, quiero capturar la pantalla, para ver qué ha pasado.
            tomarCaptura("loginHappyPath", "despuesDeLogin");    
        }
        textoDelTitulo = navegador.findElement(By.xpath("//h2")).getText();
        Assertions.assertEquals("Make Appointment", textoDelTitulo);
        // ── Cuerpo del test: reservar cita ──
        // Rellenamos el select combo_facility... con valor "Hongkong CURA Healthcare Center"
        navegador.findElement(By.id("combo_facility")).sendKeys("Hongkong CURA Healthcare Center");
        // Marcamos el checkbox "Apply for hospital readmission"
        navegador.findElement(By.id("chk_hospotal_readmission")).click();
        // Marcamos el checkbox "Medicaid"
        navegador.findElement(By.id("radio_program_medicaid")).click();
        // Fecha la de hoy
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Fecha que voy a introducir: " + fecha);
        navegador.findElement(By.id("txt_visit_date")).click();
        navegador.findElement(By.id("txt_visit_date")).sendKeys(fecha);
        // Rellenamos el campo de comentarios con: "Esto es un comentario de prueba"
        navegador.findElement(By.id("txt_comment")).sendKeys("Esto es un comentario de prueba");
        // Antes de hacer click, voy a tomar una foto de la pantalla, con los datos
        // Foto con los datos rellenos, antes de hacer click en "Book Appointment"
        tomarCaptura("makeAppointmentHappyPath", "conLosDatosRellenos");    
        // Hacemos click en "Book Appointment"
        navegador.findElement(By.id("btn-book-appointment")).click();
        // Espero a la url: https://katalon-demo-cura.herokuapp.com/appointment.php#summary
        try{ // El de dentro puede dar error.
            espera.until(ExpectedConditions.urlToBe("https://katalon-demo-cura.herokuapp.com/appointment.php#summary"));
        } finally { // De error o no, quiero capturar la pantalla, para ver qué ha pasado.
            tomarCaptura("makeAppointmentHappyPath", "despuesDeBookAppointment");    
        }
        // Ahora me falta la parte de asegurarme que los datos se han capturado correctamente.
        } finally {
            navegador.quit();
        }
    }

}
        } finally { // De error o no, quiero capturar la pantalla, para ver qué ha pasado.

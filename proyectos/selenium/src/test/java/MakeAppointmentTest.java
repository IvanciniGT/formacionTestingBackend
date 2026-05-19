
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.chrome.ChromeDriver; // COMENTADO: modo standalone usa RemoteWebDriver
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
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
        // Desactivar mensajes de contraseñas vulneradas y el gestor de contraseñas
        // (el típico aviso "Cambia tu contraseña" / "Password leak detected" y el bocadillo de "Guardar contraseña")
        java.util.Map<String, Object> prefs = new java.util.HashMap<>();
        prefs.put("credentials_enable_service", false);              // No ofrecer guardar credenciales
        prefs.put("profile.password_manager_enabled", false);        // Desactivar el gestor de contraseñas
        prefs.put("profile.password_manager_leak_detection", false); // Desactivar la detección de contraseñas vulneradas
        opciones.setExperimentalOption("prefs", prefs);
        // Y por si acaso, deshabilitamos también la "feature" de detección de fugas vía flags
        opciones.addArguments("--disable-features=PasswordLeakDetection,PasswordManagerOnboarding,AutofillServerCommunication");
        // Desactivamos que nos saque mensajes de contraseña insegura, que a veces da problemas en modo headless.
        // Y contraseña vulnerada
        //opciones.addArguments("--headless=new"); // Modo headless, sin mostrar la ventana. (en versiones anteriores de chrome era "--headless" a secas)
        // Más cosas:
        // opciones.addArguments("--incognito"); // Modo incógnito, sin guardar cookies ni historial.
        // opciones.addArguments("--start-maximized"); // Abrir el navegador maximizado.
        // opciones.addArguments("--disable-gpu"); // Deshabilitar la aceleración por hardware, que a veces da problemas en modo headless.
        opciones.addArguments("--window-size=1920,1080"); // Establecer el tamaño de la ventana, que en modo headless no se establece automáticamente.
        // navegador = new ChromeDriver(opciones); // COMENTADO: modo standalone
        // Conectamos al Selenium Grid standalone (http://localhost:8082 → puerto externo del contenedor)
        try {
            navegador = new RemoteWebDriver(new URL("http://localhost:8082"), opciones);
        } catch (java.net.MalformedURLException e) {
            throw new RuntimeException("URL del Grid Selenium no válida", e);
        }
        espera = new WebDriverWait(navegador, Duration.ofSeconds(10)); // Le configuramos un timeout (límite).
        // y que el usuario va a la pantalla de home de la app:  https://katalon-demo-cura.herokuapp.com/
        navegador.get("https://katalon-demo-cura.herokuapp.com/");
        // Previo HACER LOGIN
        navegador.findElement(By.xpath("//*[text()='Make Appointment']")).click();
        espera.until(ExpectedConditions.urlToBe("https://katalon-demo-cura.herokuapp.com/profile.php#login"));
        String textoDelTitulo = navegador.findElement(By.xpath("//h2")).getText();
        navegador.findElement(By.id("txt-username")).sendKeys("John Doe");
        navegador.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
        tomarCaptura(navegador, "loginHappyPath", "conLosDatosRellenos");    
        navegador.findElement(By.id("btn-login")).click();
        try{ // El de dentro puede dar error.
            espera.until(ExpectedConditions.urlToBe("https://katalon-demo-cura.herokuapp.com/#appointment"));
        } finally { // De error o no, quiero capturar la pantalla, para ver qué ha pasado.
            tomarCaptura(navegador, "loginHappyPath", "despuesDeLogin");    
        }
        textoDelTitulo = navegador.findElement(By.xpath("//h2")).getText();
        Assertions.assertEquals("Make Appointment", textoDelTitulo);
    }

    @AfterEach // Ejecutate después de cada prueba
    void cerrarNavegador() {
        // Cerramos el navegador
        navegador.quit();
    }


    @Test
    void loginHappyPath() {
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
        tomarCaptura(navegador, "makeAppointmentHappyPath", "conLosDatosRellenos");    
        // Hacemos click en "Book Appointment"
        navegador.findElement(By.id("btn-book-appointment")).click();
        // Espero a la url: https://katalon-demo-cura.herokuapp.com/appointment.php#summary
        try{ // El de dentro puede dar error.
            espera.until(ExpectedConditions.urlToBe("https://katalon-demo-cura.herokuapp.com/appointment.php#summary"));
        } finally { // De error o no, quiero capturar la pantalla, para ver qué ha pasado.
            tomarCaptura(navegador, "makeAppointmentHappyPath", "despuesDeBookAppointment");    
        }
        // Ahora me falta la parte de asegurarme que los datos se han capturado correctamente.
    }


}

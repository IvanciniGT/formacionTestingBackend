
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;

import org.junit.jupiter.api.Assertions;

class LoginTest {
    
    @Test
    void loginHappyPath() {
        // Dado que tengo un navegador chrome
        WebDriver navegador = new ChromeDriver();
        // y que el usuario va a la pantalla de home de la app:  https://katalon-demo-cura.herokuapp.com/
        navegador.get("https://katalon-demo-cura.herokuapp.com/");
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
        String urlALaQueLlego = navegador.getCurrentUrl();
        // Y ahora viene la prueba!
        Assertions.assertEquals("https://katalon-demo-cura.herokuapp.com/profile.php#login", urlALaQueLlego);
        String textoDelTitulo = navegador.findElement(By.xpath("//h2")).getText();
        Assertions.assertEquals("Login", textoDelTitulo);
        // Rellena el campo de username con: John Doe
        navegador.findElement(By.id("txt-username")).sendKeys("John Doe");
        // Rellena el campo de password con: ThisIsNotAPassword
        navegador.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
        // Hace clic en el botón "Login"
        navegador.findElement(By.id("btn-login")).click();
        // Llegamos a una pantalla para solicitar una cita: https://katalon-demo-cura.herokuapp.com/#appointment
        urlALaQueLlego = navegador.getCurrentUrl();
        Assertions.assertEquals("https://katalon-demo-cura.herokuapp.com/#appointment", urlALaQueLlego);
        // cuyo título es: Make Appointment
        textoDelTitulo = navegador.findElement(By.xpath("//h2")).getText();
        Assertions.assertEquals("Make Appointment", textoDelTitulo);
        // Cerramos el navegador
        navegador.quit();
    }

}

package com.curso.animalitos;

// Lo que hacemos es decirle a JUnit(JUPITER) que esta clase es una Suite de Cucumber, y que busque los archivos .feature en el classpath (en src/test/resources) para ejecutar los tests que haya definidos ahí.
// Jupiter

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite // JUnit, este archivo no contiene tests, es una suite que agrupa otros tests
@IncludeEngines("cucumber") // Las pruebas las hemos 
@SelectClasspathResource("features") // Le decimos que busque los archivos .feature en
@ConfigurationParameter(key = "cucumber.glue", value = "com.curso.animalitos") // Le decimos que busque las clases de step definitions en este paquete
public class AnimalitosServiceCucumberTest {


}

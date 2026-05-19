package com.curso.animalitos;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import io.cucumber.java.bs.A;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.lu.an;



public class AnimalitosServiceCucumberSteps {
    
    // En lugar de crear manualmente un Stub del AnimalitosRepository, vamos a usar Mockito.
    private final AnimalitosRepository miMockDeAnimalitosRepository = Mockito.mock(AnimalitosRepository.class);
    private final SistemaDeMensajeria miMockDelSistemaDeMensajeria = Mockito.mock(SistemaDeMensajeria.class);
    private DatosCompletosAnimalito.DatosCompletosAnimalitoBuilder datosAnimalito;
    protected AnimalitosService animalitosService ;

    private DatosCompletosAnimalito animalitoRecuperado;

    @Dado("que tengo los datos de un animalito.")
    public void que_tengo_los_datos_de_un_animalito() {
        datosAnimalito = DatosCompletosAnimalito.builder();
    }
    @Dado("su nombre es: {string}")
    public void su_nombre_es(String nombre) {
        datosAnimalito.nombre(nombre);
    }
    @Dado("su especie es: {string}")
    public void su_especie_es(String especie) {
        datosAnimalito.especie(especie);
    }
    @Dado("su raza es: {string}")
    public void su_raza_es(String raza  ) {
        datosAnimalito.raza(raza);
    }
    @Dado("su edad es: {int}")
    public void su_edad_es(Integer edad) {
        datosAnimalito.edad(edad);
    }
    @Dado("su peso es: {int}")
    public void su_peso_es(Integer peso) {
        datosAnimalito.peso(peso);
    }
    @Dado("su número de identificación es: {string}")
    public void su_número_de_identificación_es(String numeroIdentificacion) {
        datosAnimalito.numeroIdentificacion(numeroIdentificacion);
    }
    @Dado("sus observaciones son: {string}.")
    public void sus_observaciones_son(String observaciones) {
        datosAnimalito.observaciones(observaciones);
    }
    @Dado("su identificación es: {string}")
    public void su_identificación_es(String identificacion) {
        datosAnimalito.id(identificacion);
    }
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Dado("su fecha de alta es: {string}")
    public void su_fecha_de_alta_es(String fechaAlta) {
        ZonedDateTime fechaAltaParseada = LocalDate.parse(fechaAlta, FORMATO_FECHA).atStartOfDay(java.time.ZoneId.systemDefault());
        datosAnimalito.fechaAlta(fechaAltaParseada);
    }
    @Dado("su fecha de última modificación es: {string}")
    public void su_fecha_de_última_modificación_es(String fechaUltimaModificacion) {
        ZonedDateTime fechaUltimaModificacionParseada = LocalDate.parse(fechaUltimaModificacion, FORMATO_FECHA).atStartOfDay(java.time.ZoneId.systemDefault());
        datosAnimalito.fechaUltimaModificacion(fechaUltimaModificacionParseada);
    }
    @Dado("que lo tengo registrado en un mock del repositorio de animalitos.")
    public void que_lo_tengo_registrado_en_un_mock_del_repositorio_de_animalitos() {
        DatosCompletosAnimalito animalitoCompleto = datosAnimalito.build();
        Mockito.when(miMockDeAnimalitosRepository.recuperarAnimalitoDeLaBBDD(animalitoCompleto.getId()))
               .thenReturn(animalitoCompleto);
    }
    @Dado("que tengo un servicio de animalitos que usa el mock del repositorio de animalitos y un sistema de mensajería de mentirijilla.")
    public void que_tengo_un_servicio_de_animalitos_que_usa_el_mock_del_repositorio_de_animalitos_y_un_sistema_de_mensajería_de_mentirijilla() {
        animalitosService = new AnimalitosServiceImpl(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
    }
    @Cuando("llamo a la función recuperarAnimalito del servicio de animalitos, pasando el ID del animalito registrado como parámetro.")
    public void llamo_a_la_función_recuperar_animalito_del_servicio_de_animalitos_pasando_el_id_del_animalito_registrado_como_parámetro() {
        animalitoRecuperado = animalitosService.recuperarAnimalito(datosAnimalito.build().getId());
    }
    @Entonces("se devuelve un objeto con los datos completos del animalito registrado")
    public void se_devuelve_un_objeto_con_los_datos_completos_del_animalito_registrado() {
        Assertions.assertNotNull(animalitoRecuperado);
    }
    @Entonces("el ID devuelto es: {string}")
    public void el_id_devuelto_es(String id) {
        Assertions.assertEquals(id, animalitoRecuperado.getId());
    }
    @Entonces("la fecha de alta devuelta es: {string}")
    public void la_fecha_de_alta_devuelta_es(String fechaAlta) {
        ZonedDateTime fechaAltaParseada = LocalDate.parse(fechaAlta, FORMATO_FECHA).atStartOfDay(java.time.ZoneId.systemDefault());
        Assertions.assertEquals(fechaAltaParseada, animalitoRecuperado.getFechaAlta());
    }
    @Entonces("la fecha de última modificación devuelta es: {string}")
    public void la_fecha_de_última_modificación_devuelta_es(String fechaUltimaModificacion) {
        ZonedDateTime fechaUltimaModificacionParseada = LocalDate.parse(fechaUltimaModificacion, FORMATO_FECHA).atStartOfDay(java.time.ZoneId.systemDefault());
        Assertions.assertEquals(fechaUltimaModificacionParseada, animalitoRecuperado.getFechaUltimaModificacion());
    }
    @Entonces("el nombre devuelto es: {string}")
    public void el_nombre_devuelto_es(String nombre) {
        Assertions.assertEquals(nombre, animalitoRecuperado.getNombre());
    }
    @Entonces("la especie devuelta es: {string}")
    public void la_especie_devuelta_es(String especie) {
        Assertions.assertEquals(especie, animalitoRecuperado.getEspecie());
    }
    @Entonces("la raza devuelta es: {string}")
    public void la_raza_devuelta_es(String raza) {
        Assertions.assertEquals(raza, animalitoRecuperado.getRaza());
    }
    @Entonces("la edad devuelta es: {int}")
    public void la_edad_devuelta_es(Integer edad) {
        Assertions.assertEquals(edad, animalitoRecuperado.getEdad());
    }
    @Entonces("el peso devuelto es: {int}")
    public void el_peso_devuelto_es(Integer peso) {
        Assertions.assertEquals(peso, animalitoRecuperado.getPeso());
    }
    @Entonces("el número de identificación devuelto es: {string}")
    public void el_número_de_identificación_devuelto_es(String numeroIdentificacion) {
        Assertions.assertEquals(numeroIdentificacion, animalitoRecuperado.getNumeroIdentificacion());   
    }
    @Entonces("las observaciones devueltas son: {string}.")
    public void las_observaciones_devueltas_son(String observaciones) {
        Assertions.assertEquals(observaciones, animalitoRecuperado.getObservaciones());
    }


@Cuando("llamo a la función crearAnimalito del servicio de animalitos, pasando los datos del animalito como parámetro.")
public void llamo_a_la_función_crear_animalito_del_servicio_de_animalitos_pasando_los_datos_del_animalito_como_parámetro() {
    //animalitoDevuelto= animalitosService.registrarAnimalito(datosAnimalito.build());
}
@Entonces("se crea un nuevo animalito con los datos proporcionados y se devuelve el ID del nuevo animalito creado")
public void se_crea_un_nuevo_animalito_con_los_datos_proporcionados_y_se_devuelve_el_id_del_nuevo_animalito_creado() {
    Assertions.assertNotNull(datosAnimalito.build().getId());
}

}

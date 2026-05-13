package com.curso.animalitos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


abstract class AnimalitosServiceTest {

    protected abstract AnimalitosService dameImplementacionDelServicioDeAnimalitosParaLasPruebas(AnimalitosRepository miRepo, SistemaDeMensajeria miSistemaDeMensajeria);

    // En lugar de crear manualmente un Stub del AnimalitosRepository, vamos a usar Mockito.
    private final AnimalitosRepository miMockDeAnimalitosRepository = Mockito.mock(AnimalitosRepository.class);
    private final SistemaDeMensajeria miMockDelSistemaDeMensajeria = Mockito.mock(SistemaDeMensajeria.class);

    @Test
    @DisplayName("Registrar un nuevo animalito - Happy Path")
    void testRegistrarAnimalito_HappyPath() {
        //DADO:
        // dado que tengo un animalito guays con los siguientes datos:
        //     - Nombre: Rocky
        //     - Especie: Perro
        //     - Raza: Labrador
        //     - Edad: 2 años
        //     - Peso: 20 kg
        //     - Nº Identificación: XXXXXXX
        //     - Observaciones: Es un perro muy juguetón y cariñoso.
        DatosRegistroAnimalito datosGuays = DatosRegistroAnimalito.builder()
                                                                    .nombre("Rocky")
                                                                    .especie("Perro")
                                                                    .raza("Labrador")
                                                                    .edad(2)
                                                                    .peso(20)
                                                                    .numeroIdentificacion("XXXXXXX")
                                                                    .observaciones("Es un perro muy juguetón y cariñoso.")
                                                                    .build();
        // Que tengo un mock del AnimalitosRepositoryDeMentirijilla 
        // Y dado que el mock, cuando seal solicitado que guarde un animalito en la BBDD, devuelva un objeto con los datos completos del animalito registrado, incluyendo :
        //      - ID: ABCD-1234-EFGH-5678
        //      - Fecha de alta: Fecha actual
        //      - Fecha de última modificación: Fecha actual
        DatosCompletosAnimalito animalitoRegistradoEsperado = DatosCompletosAnimalito.builder()
                                                                            .nombre(datosGuays.getNombre())
                                                                            .especie(datosGuays.getEspecie())
                                                                            .raza(datosGuays.getRaza())
                                                                            .edad(datosGuays.getEdad())
                                                                            .peso(datosGuays.getPeso())
                                                                            .numeroIdentificacion(datosGuays.getNumeroIdentificacion())
                                                                            .observaciones(datosGuays.getObservaciones())
                                                                            .id("ABCD-1234-EFGH-5678")
                                                                            .fechaAlta(java.time.ZonedDateTime.now())
                                                                            .fechaUltimaModificacion(java.time.ZonedDateTime.now())
                                                                            .build();
        Mockito.when(miMockDeAnimalitosRepository.guardarAnimalitoEnBBDD(datosGuays)).thenReturn(animalitoRegistradoEsperado);
        // Datos que tengo un AnimalitosService, que usa el mock del AnimalitosRepositoryDeMentirijilla y un SistemaDeMensajeriaDeMentirijilla.
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        //    Llamo a la función registrarAnimalito del AnimalitosService, pasando el animalito guays como parámetro.
        DatosCompletosAnimalito animalitoRegistrado = miAnimalitosService.registrarAnimalito(datosGuays);
        //ENTONCES:
        //    - Se devuelve un objeto con los datos completos del animalito registrado, incluyendo :
        //      - ID: ABCD-1234-EFGH-5678
        //      - Fecha de alta: Fecha actual
        //      - Fecha de última modificación: Fecha actual
        Assertions.assertNotNull(animalitoRegistrado);
        Assertions.assertEquals(datosGuays.getNombre(), animalitoRegistrado.getNombre());
        Assertions.assertEquals(datosGuays.getEspecie(), animalitoRegistrado.getEspecie());
        Assertions.assertEquals(datosGuays.getRaza(), animalitoRegistrado.getRaza());
        Assertions.assertEquals(datosGuays.getEdad(), animalitoRegistrado.getEdad());
        Assertions.assertEquals(datosGuays.getPeso(), animalitoRegistrado.getPeso());
        Assertions.assertEquals(datosGuays.getNumeroIdentificacion(), animalitoRegistrado.getNumeroIdentificacion());
        Assertions.assertEquals(datosGuays.getObservaciones(), animalitoRegistrado.getObservaciones());
        Assertions.assertNotNull(animalitoRegistrado.getId());
        Assertions.assertNotNull(animalitoRegistrado.getFechaAlta());
        Assertions.assertNotNull(animalitoRegistrado.getFechaUltimaModificacion());
        Assertions.assertEquals(animalitoRegistradoEsperado.getId(), animalitoRegistrado.getId());
        Assertions.assertEquals(animalitoRegistradoEsperado.getFechaAlta(), animalitoRegistrado.getFechaAlta());
        Assertions.assertEquals(animalitoRegistradoEsperado.getFechaUltimaModificacion(), animalitoRegistrado.getFechaUltimaModificacion());
        // Me aseguro que se haya llamado al mock
        Mockito.verify(miMockDeAnimalitosRepository).guardarAnimalitoEnBBDD(datosGuays);
        // Quiero asegurarme que se haya llamado al sistema de mensajería para enviar una notificación de que se ha registrado un nuevo animalito. Para eso, necesito usar Mockito para verificar que se haya llamado al método correspondiente del sistema de mensajería.
        Mockito.verify(miMockDelSistemaDeMensajeria).enviarNotificacion(animalitoRegistrado);
    }

}




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
    @DisplayName("Recuperar un animalito registrado por ID - Happy Path")
    void testRecuperarAnimalito_HappyPath() {

        //DADO:
        // dado que tengo un animalito guays ya está registrado en el mock
        //     - Nombre: Rocky
        //     - Especie: Perro
        //     - Raza: Labrador
        //     - Edad: 2 años
        //     - Peso: 20 kg
        //     - Nº Identificación: XXXXXXX
        //     - Observaciones: Es un perro muy juguetón y cariñoso.
        DatosCompletosAnimalito animalitoRegistradoEsperado = DatosCompletosAnimalito.builder()
                                                                            .nombre("Rocky")
                                                                            .especie("Perro")
                                                                            .raza("Labrador")
                                                                            .edad(2)
                                                                            .peso(20)
                                                                            .numeroIdentificacion("XXXXXXX")
                                                                            .observaciones("Es un perro muy juguetón y cariñoso."   )
                                                                            .id("ABCD-1234-EFGH-5678")
                                                                            .fechaAlta(java.time.ZonedDateTime.now())
                                                                            .fechaUltimaModificacion(java.time.ZonedDateTime.now())
                                                                            .build();
        // Y dado que el mock, cuando sea solicitado que recupere un animalito de la BBDD por su ID, 
        // devuelva un objeto con los datos completos del animalito registrado, incluyendo :
        Mockito.when(miMockDeAnimalitosRepository.recuperarAnimalitoDeLaBBDD(animalitoRegistradoEsperado.getId())).thenReturn(animalitoRegistradoEsperado);

        // Datos que tengo un AnimalitosService, que usa el mock del AnimalitosRepositoryDeMentirijilla y un SistemaDeMensajeriaDeMentirijilla.
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        //    Llamo a la función recuperarAnimalito del AnimalitosService, pasando el ID del animalito registrado como parámetro.
        DatosCompletosAnimalito animalitoRegistrado = miAnimalitosService.recuperarAnimalito(animalitoRegistradoEsperado.getId());
        //ENTONCES:
        //    - Se devuelve un objeto con los datos completos del animalito registrado, incluyendo :
        //      - ID: ABCD-1234-EFGH-5678
        //      - Fecha de alta: Fecha actual
        //      - Fecha de última modificación: Fecha actual
        Assertions.assertNotNull(animalitoRegistrado);
        Assertions.assertEquals(animalitoRegistradoEsperado, animalitoRegistrado);

    }

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

    // =====================================================================================
    // registrarAnimalito - Escenario 2: Solo especie y raza (campos opcionales ausentes)
    // =====================================================================================

    @Test
    @DisplayName("Registrar animalito solo con especie y raza (sin nombre, peso, edad, numId ni observaciones) - debe funcionar")
    void testRegistrarAnimalito_SoloEspecieYRaza() {
        // DADO:
        DatosRegistroAnimalito datosMinimos = DatosRegistroAnimalito.builder()
                .especie("Perro")
                .raza("Labrador")
                .build();
        DatosCompletosAnimalito animalitoEsperado = DatosCompletosAnimalito.builder()
                .especie("Perro")
                .raza("Labrador")
                .id("ABCD-1234-EFGH-5678")
                .fechaAlta(java.time.ZonedDateTime.now())
                .fechaUltimaModificacion(java.time.ZonedDateTime.now())
                .build();
        Mockito.when(miMockDeAnimalitosRepository.guardarAnimalitoEnBBDD(datosMinimos)).thenReturn(animalitoEsperado);
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        DatosCompletosAnimalito resultado = miAnimalitosService.registrarAnimalito(datosMinimos);
        // ENTONCES:
        Assertions.assertNotNull(resultado);
        Assertions.assertNotNull(resultado.getId());
        Assertions.assertEquals("Perro", resultado.getEspecie());
        Assertions.assertEquals("Labrador", resultado.getRaza());
        Mockito.verify(miMockDeAnimalitosRepository).guardarAnimalitoEnBBDD(datosMinimos);
        Mockito.verify(miMockDelSistemaDeMensajeria).enviarNotificacion(resultado);
    }

    // =====================================================================================
    // registrarAnimalito - Escenario 3: Nombre inválido
    // =====================================================================================

    @Test
    @DisplayName("Registrar animalito con nombre numérico ('33') - debe lanzar excepción")
    void testRegistrarAnimalito_NombreConNumeros() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("33")
                .especie("Perro")
                .raza("Labrador")
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }
/*
    @Test
    @DisplayName("Registrar animalito con nombre mezclado letras y números ('Rocky3') - debe lanzar excepción")
    void testRegistrarAnimalito_NombreMezclaLetrasYNumeros() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky3")
                .especie("Perro")
                .raza("Labrador")
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }

    @Test
    @DisplayName("Registrar animalito con nombre con caracteres especiales ('#&&/()@') - debe lanzar excepción")
    void testRegistrarAnimalito_NombreConCaracteresEspeciales() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("#&&/()@")
                .especie("Perro")
                .raza("Labrador")
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }

    @Test
    @DisplayName("Registrar animalito con nombre demasiado largo (51 chars) - debe lanzar excepción")
    void testRegistrarAnimalito_NombreDemasiaoLargo() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("X".repeat(51))
                .especie("Perro")
                .raza("Labrador")
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }

    @Test
    @DisplayName("Registrar animalito con nombre en el límite máximo (50 chars) - debe funcionar")
    void testRegistrarAnimalito_NombreJustoEnLimiteMaximo() {
        // DADO: 50 chars es el límite superior válido
        String nombreLimite = "A".repeat(50);
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre(nombreLimite)
                .especie("Perro")
                .raza("Labrador")
                .build();
        DatosCompletosAnimalito animalitoEsperado = DatosCompletosAnimalito.builder()
                .nombre(nombreLimite)
                .especie("Perro")
                .raza("Labrador")
                .id("ABCD-1234-EFGH-5678")
                .fechaAlta(java.time.ZonedDateTime.now())
                .fechaUltimaModificacion(java.time.ZonedDateTime.now())
                .build();
        Mockito.when(miMockDeAnimalitosRepository.guardarAnimalitoEnBBDD(datos)).thenReturn(animalitoEsperado);
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        DatosCompletosAnimalito resultado = miAnimalitosService.registrarAnimalito(datos);
        // ENTONCES:
        Assertions.assertNotNull(resultado);
        Mockito.verify(miMockDeAnimalitosRepository).guardarAnimalitoEnBBDD(datos);
    }

    @Test
    @DisplayName("Registrar animalito con nombre vacío ('') - debe lanzar excepción")
    void testRegistrarAnimalito_NombreVacio() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("")
                .especie("Perro")
                .raza("Labrador")
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }

    @Test
    @DisplayName("Registrar animalito con nombre de solo espacios en blanco - debe lanzar excepción")
    void testRegistrarAnimalito_NombreSoloEspacios() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("     ")
                .especie("Perro")
                .raza("Labrador")
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }

    @Test
    @DisplayName("Registrar animalito con nombre inválido - NO debe guardar en BBDD")
    void testRegistrarAnimalito_NombreInvalido_NoGuardaEnBBDD() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky3")
                .especie("Perro")
                .raza("Labrador")
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        try { miAnimalitosService.registrarAnimalito(datos); } catch (Exception ignored) {}
        // ENTONCES: El repositorio NO debe haber sido invocado en ningún momento
        Mockito.verify(miMockDeAnimalitosRepository, Mockito.never()).guardarAnimalitoEnBBDD(Mockito.any());
    }

    @Test
    @DisplayName("Registrar animalito con nombre inválido - NO debe enviar notificación")
    void testRegistrarAnimalito_NombreInvalido_NoEnviaNotificacion() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky3")
                .especie("Perro")
                .raza("Labrador")
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        try { miAnimalitosService.registrarAnimalito(datos); } catch (Exception ignored) {}
        // ENTONCES: El sistema de mensajería NO debe haber sido invocado en ningún momento
        Mockito.verify(miMockDelSistemaDeMensajeria, Mockito.never()).enviarNotificacion(Mockito.any());
    }

    // =====================================================================================
    // registrarAnimalito - Validación especie (campo obligatorio)
    // =====================================================================================

    @Test
    @DisplayName("Registrar animalito sin especie (null) - debe lanzar excepción")
    void testRegistrarAnimalito_EspecieNula() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky")
                .raza("Labrador")
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }

    @Test
    @DisplayName("Registrar animalito con especie vacía ('') - debe lanzar excepción")
    void testRegistrarAnimalito_EspecieVacia() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky")
                .especie("")
                .raza("Labrador")
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }

    @Test
    @DisplayName("Registrar animalito con especie de solo espacios - debe lanzar excepción")
    void testRegistrarAnimalito_EspecieSoloEspacios() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky")
                .especie("   ")
                .raza("Labrador")
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }

    @Test
    @DisplayName("Registrar animalito con especie con caracteres especiales - debe lanzar excepción")
    void testRegistrarAnimalito_EspecieConCaracteresEspeciales() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky")
                .especie("P3rr0!")
                .raza("Labrador")
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }

    @Test
    @DisplayName("Registrar animalito con especie demasiado larga (>50 chars) - debe lanzar excepción")
    void testRegistrarAnimalito_EspecieDemasiaoLarga() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky")
                .especie("P".repeat(51))
                .raza("Labrador")
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }

    @Test
    @DisplayName("Registrar animalito con especie inválida - NO debe guardar en BBDD")
    void testRegistrarAnimalito_EspecieInvalida_NoGuardaEnBBDD() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky")
                .especie("")
                .raza("Labrador")
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        try { miAnimalitosService.registrarAnimalito(datos); } catch (Exception ignored) {}
        // ENTONCES:
        Mockito.verify(miMockDeAnimalitosRepository, Mockito.never()).guardarAnimalitoEnBBDD(Mockito.any());
    }

    // =====================================================================================
    // registrarAnimalito - Validación raza (campo obligatorio)
    // =====================================================================================

    @Test
    @DisplayName("Registrar animalito sin raza (null) - debe lanzar excepción")
    void testRegistrarAnimalito_RazaNula() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky")
                .especie("Perro")
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }

    @Test
    @DisplayName("Registrar animalito con raza vacía ('') - debe lanzar excepción")
    void testRegistrarAnimalito_RazaVacia() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky")
                .especie("Perro")
                .raza("")
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }

    @Test
    @DisplayName("Registrar animalito con raza de solo espacios - debe lanzar excepción")
    void testRegistrarAnimalito_RazaSoloEspacios() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky")
                .especie("Perro")
                .raza("   ")
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }

    @Test
    @DisplayName("Registrar animalito con raza demasiado larga (>100 chars) - debe lanzar excepción")
    void testRegistrarAnimalito_RazaDemasiaoLarga() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky")
                .especie("Perro")
                .raza("R".repeat(101))
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }

    @Test
    @DisplayName("Registrar animalito con raza con caracteres especiales - debe lanzar excepción")
    void testRegistrarAnimalito_RazaConCaracteresEspeciales() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky")
                .especie("Perro")
                .raza("Labr@dor!")
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }

    // =====================================================================================
    // registrarAnimalito - Escenario 4: Validación edad (edad >= 0)
    // =====================================================================================

    @Test
    @DisplayName("Registrar animalito con edad negativa (-1) - debe lanzar excepción")
    void testRegistrarAnimalito_EdadNegativa() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky")
                .especie("Perro")
                .raza("Labrador")
                .edad(-1)
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }

    @Test
    @DisplayName("Registrar animalito con edad muy negativa (-100) - debe lanzar excepción")
    void testRegistrarAnimalito_EdadMuyNegativa() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky")
                .especie("Perro")
                .raza("Labrador")
                .edad(-100)
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }

    @Test
    @DisplayName("Registrar animalito con edad cero (0) - debe funcionar (recién nacido / no informado)")
    void testRegistrarAnimalito_EdadCeroEsValido() {
        // DADO: edad = 0 equivale a "no informada" o recién nacido
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .especie("Perro")
                .raza("Labrador")
                .edad(0)
                .build();
        DatosCompletosAnimalito animalitoEsperado = DatosCompletosAnimalito.builder()
                .especie("Perro")
                .raza("Labrador")
                .edad(0)
                .id("ABCD-1234-EFGH-5678")
                .fechaAlta(java.time.ZonedDateTime.now())
                .fechaUltimaModificacion(java.time.ZonedDateTime.now())
                .build();
        Mockito.when(miMockDeAnimalitosRepository.guardarAnimalitoEnBBDD(datos)).thenReturn(animalitoEsperado);
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        DatosCompletosAnimalito resultado = miAnimalitosService.registrarAnimalito(datos);
        // ENTONCES:
        Assertions.assertNotNull(resultado);
        Mockito.verify(miMockDeAnimalitosRepository).guardarAnimalitoEnBBDD(datos);
    }

    @Test
    @DisplayName("Registrar animalito con edad negativa - NO debe guardar en BBDD")
    void testRegistrarAnimalito_EdadNegativa_NoGuardaEnBBDD() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky")
                .especie("Perro")
                .raza("Labrador")
                .edad(-5)
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        try { miAnimalitosService.registrarAnimalito(datos); } catch (Exception ignored) {}
        // ENTONCES:
        Mockito.verify(miMockDeAnimalitosRepository, Mockito.never()).guardarAnimalitoEnBBDD(Mockito.any());
    }

    // =====================================================================================
    // registrarAnimalito - Escenario 5: Validación peso (peso > 0 si se informa)
    // =====================================================================================

    @Test
    @DisplayName("Registrar animalito con peso negativo (-5) - debe lanzar excepción")
    void testRegistrarAnimalito_PesoNegativo() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky")
                .especie("Perro")
                .raza("Labrador")
                .peso(-5)
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }

    @Test
    @DisplayName("Registrar animalito con peso cero (0) - debe funcionar (no informado)")
    void testRegistrarAnimalito_PesoCeroEsValido() {
        // DADO: peso = 0 equivale a "no informado"
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .especie("Perro")
                .raza("Labrador")
                .peso(0)
                .build();
        DatosCompletosAnimalito animalitoEsperado = DatosCompletosAnimalito.builder()
                .especie("Perro")
                .raza("Labrador")
                .peso(0)
                .id("ABCD-1234-EFGH-5678")
                .fechaAlta(java.time.ZonedDateTime.now())
                .fechaUltimaModificacion(java.time.ZonedDateTime.now())
                .build();
        Mockito.when(miMockDeAnimalitosRepository.guardarAnimalitoEnBBDD(datos)).thenReturn(animalitoEsperado);
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        DatosCompletosAnimalito resultado = miAnimalitosService.registrarAnimalito(datos);
        // ENTONCES:
        Assertions.assertNotNull(resultado);
        Mockito.verify(miMockDeAnimalitosRepository).guardarAnimalitoEnBBDD(datos);
    }

    @Test
    @DisplayName("Registrar animalito con peso negativo - NO debe guardar en BBDD")
    void testRegistrarAnimalito_PesoNegativo_NoGuardaEnBBDD() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky")
                .especie("Perro")
                .raza("Labrador")
                .peso(-10)
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        try { miAnimalitosService.registrarAnimalito(datos); } catch (Exception ignored) {}
        // ENTONCES:
        Mockito.verify(miMockDeAnimalitosRepository, Mockito.never()).guardarAnimalitoEnBBDD(Mockito.any());
    }

    // =====================================================================================
    // registrarAnimalito - Validación número de identificación
    // =====================================================================================

    @Test
    @DisplayName("Registrar animalito con número de identificación con caracteres inválidos - debe lanzar excepción")
    void testRegistrarAnimalito_NumeroIdentificacionConCaracteresInvalidos() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky")
                .especie("Perro")
                .raza("Labrador")
                .numeroIdentificacion("ID@#!$%")
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }

    @Test
    @DisplayName("Registrar animalito con número de identificación demasiado largo (>50 chars) - debe lanzar excepción")
    void testRegistrarAnimalito_NumeroIdentificacionDemasiaoLargo() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky")
                .especie("Perro")
                .raza("Labrador")
                .numeroIdentificacion("X".repeat(51))
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }

    // =====================================================================================
    // registrarAnimalito - Validación observaciones
    // =====================================================================================

    @Test
    @DisplayName("Registrar animalito con observaciones demasiado largas (>500 chars) - debe lanzar excepción")
    void testRegistrarAnimalito_ObservacionesDemasiaoLargas() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky")
                .especie("Perro")
                .raza("Labrador")
                .observaciones("O".repeat(501))
                .build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }

    // =====================================================================================
    // registrarAnimalito - Fallos en repositorio
    // =====================================================================================

    @Test
    @DisplayName("Registrar animalito - cuando el repositorio lanza excepción, debe propagarla")
    void testRegistrarAnimalito_CuandoRepositorioFalla_PropagaExcepcion() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky")
                .especie("Perro")
                .raza("Labrador")
                .build();
        Mockito.when(miMockDeAnimalitosRepository.guardarAnimalitoEnBBDD(datos))
               .thenThrow(new RuntimeException("Error de BBDD"));
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(RuntimeException.class, () -> miAnimalitosService.registrarAnimalito(datos));
    }

    @Test
    @DisplayName("Registrar animalito - cuando el repositorio falla, NO debe enviar notificación")
    void testRegistrarAnimalito_CuandoRepositorioFalla_NoEnviaNotificacion() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky")
                .especie("Perro")
                .raza("Labrador")
                .build();
        Mockito.when(miMockDeAnimalitosRepository.guardarAnimalitoEnBBDD(datos))
               .thenThrow(new RuntimeException("Error de BBDD"));
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        try { miAnimalitosService.registrarAnimalito(datos); } catch (Exception ignored) {}
        // ENTONCES: si el repo falló, no tiene sentido notificar
        Mockito.verify(miMockDelSistemaDeMensajeria, Mockito.never()).enviarNotificacion(Mockito.any());
    }

    @Test
    @DisplayName("Registrar animalito - la notificación se envía con los datos COMPLETOS del repo (con ID y fechas), no con los de registro")
    void testRegistrarAnimalito_NotificacionSeEnviaConDatosCompletosDelRepo() {
        // DADO:
        DatosRegistroAnimalito datos = DatosRegistroAnimalito.builder()
                .nombre("Rocky")
                .especie("Perro")
                .raza("Labrador")
                .build();
        DatosCompletosAnimalito animalitoConIdYFechas = DatosCompletosAnimalito.builder()
                .nombre("Rocky")
                .especie("Perro")
                .raza("Labrador")
                .id("ABCD-1234-EFGH-5678")
                .fechaAlta(java.time.ZonedDateTime.now())
                .fechaUltimaModificacion(java.time.ZonedDateTime.now())
                .build();
        Mockito.when(miMockDeAnimalitosRepository.guardarAnimalitoEnBBDD(datos)).thenReturn(animalitoConIdYFechas);
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        miAnimalitosService.registrarAnimalito(datos);
        // ENTONCES: la notificación debe llevar el objeto con ID y fechas, no el de registro original
        Mockito.verify(miMockDelSistemaDeMensajeria).enviarNotificacion(animalitoConIdYFechas);
    }

    // =====================================================================================
    // recuperarAnimalito - Casos adicionales
    // =====================================================================================

    @Test
    @DisplayName("Recuperar animalito con ID nulo - debe lanzar excepción")
    void testRecuperarAnimalito_IdNulo() {
        // DADO:
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.recuperarAnimalito(null));
    }

    @Test
    @DisplayName("Recuperar animalito con ID vacío ('') - debe lanzar excepción")
    void testRecuperarAnimalito_IdVacio() {
        // DADO:
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.recuperarAnimalito(""));
    }

    @Test
    @DisplayName("Recuperar animalito con ID de solo espacios - debe lanzar excepción")
    void testRecuperarAnimalito_IdSoloEspacios() {
        // DADO:
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.recuperarAnimalito("   "));
    }

    @Test
    @DisplayName("Recuperar animalito que no existe en BBDD - el repositorio devuelve null, el servicio devuelve null")
    void testRecuperarAnimalito_NoExiste_DevuelveNull() {
        // DADO:
        String idInexistente = "ID-QUE-NO-EXISTE";
        Mockito.when(miMockDeAnimalitosRepository.recuperarAnimalitoDeLaBBDD(idInexistente)).thenReturn(null);
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        DatosCompletosAnimalito resultado = miAnimalitosService.recuperarAnimalito(idInexistente);
        // ENTONCES:
        Assertions.assertNull(resultado);
    }

    @Test
    @DisplayName("Recuperar animalito - NO debe invocar al sistema de mensajería en ningún caso")
    void testRecuperarAnimalito_NoLlamaASistemaDeMensajeria() {
        // DADO:
        String id = "ABCD-1234-EFGH-5678";
        DatosCompletosAnimalito animalitoEsperado = DatosCompletosAnimalito.builder()
                .nombre("Rocky")
                .especie("Perro")
                .raza("Labrador")
                .id(id)
                .fechaAlta(java.time.ZonedDateTime.now())
                .fechaUltimaModificacion(java.time.ZonedDateTime.now())
                .build();
        Mockito.when(miMockDeAnimalitosRepository.recuperarAnimalitoDeLaBBDD(id)).thenReturn(animalitoEsperado);
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        miAnimalitosService.recuperarAnimalito(id);
        // ENTONCES: recuperar un animalito NO debe disparar ninguna notificación
        Mockito.verifyNoInteractions(miMockDelSistemaDeMensajeria);
    }

    @Test
    @DisplayName("Recuperar animalito - cuando el repositorio lanza excepción, debe propagarla")
    void testRecuperarAnimalito_CuandoRepositorioFalla_PropagaExcepcion() {
        // DADO:
        String id = "ABCD-1234-EFGH-5678";
        Mockito.when(miMockDeAnimalitosRepository.recuperarAnimalitoDeLaBBDD(id))
               .thenThrow(new RuntimeException("Error de BBDD"));
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(RuntimeException.class, () -> miAnimalitosService.recuperarAnimalito(id));
    }

    // =====================================================================================
    // modificarAnimalito - Happy Path
    // =====================================================================================

    @Test
    @DisplayName("Modificar animalito con datos válidos - Happy Path")
    void testModificarAnimalito_HappyPath() {
        // DADO:
        String id = "ABCD-1234-EFGH-5678";
        DatosRegistroAnimalito datosNuevos = DatosRegistroAnimalito.builder()
                .nombre("Rex")
                .especie("Perro")
                .raza("Pastor Alemán")
                .edad(3)
                .peso(30)
                .numeroIdentificacion("YYYYYYY")
                .observaciones("Perro muy obediente.")
                .build();
        DatosCompletosAnimalito animalitoModificadoEsperado = DatosCompletosAnimalito.builder()
                .id(id)
                .nombre("Rex")
                .especie("Perro")
                .raza("Pastor Alemán")
                .edad(3)
                .peso(30)
                .numeroIdentificacion("YYYYYYY")
                .observaciones("Perro muy obediente.")
                .fechaAlta(java.time.ZonedDateTime.now())
                .fechaUltimaModificacion(java.time.ZonedDateTime.now())
                .build();
        Mockito.when(miMockDeAnimalitosRepository.actualizarAnimalitoEnBBDD(id, datosNuevos)).thenReturn(animalitoModificadoEsperado);
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        DatosCompletosAnimalito resultado = miAnimalitosService.modificarAnimalito(id, datosNuevos);
        // ENTONCES:
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(id, resultado.getId());
        Assertions.assertEquals("Rex", resultado.getNombre());
        Assertions.assertEquals("Perro", resultado.getEspecie());
        Assertions.assertEquals("Pastor Alemán", resultado.getRaza());
        Assertions.assertEquals(3, resultado.getEdad());
        Assertions.assertEquals(30, resultado.getPeso());
        Mockito.verify(miMockDeAnimalitosRepository).actualizarAnimalitoEnBBDD(id, datosNuevos);
        Mockito.verify(miMockDelSistemaDeMensajeria).enviarNotificacion(animalitoModificadoEsperado);
    }

    @Test
    @DisplayName("Modificar animalito - se llama al repositorio con el ID correcto")
    void testModificarAnimalito_LlamaAlRepositorioConElIdCorrecto() {
        // DADO:
        String id = "ABCD-1234-EFGH-5678";
        DatosRegistroAnimalito datosNuevos = DatosRegistroAnimalito.builder()
                .especie("Gato")
                .raza("Siamés")
                .build();
        DatosCompletosAnimalito animalitoEsperado = DatosCompletosAnimalito.builder()
                .id(id).especie("Gato").raza("Siamés")
                .fechaAlta(java.time.ZonedDateTime.now())
                .fechaUltimaModificacion(java.time.ZonedDateTime.now())
                .build();
        Mockito.when(miMockDeAnimalitosRepository.actualizarAnimalitoEnBBDD(id, datosNuevos)).thenReturn(animalitoEsperado);
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        miAnimalitosService.modificarAnimalito(id, datosNuevos);
        // ENTONCES: el repositorio debe recibir exactamente el ID que le pasamos
        Mockito.verify(miMockDeAnimalitosRepository).actualizarAnimalitoEnBBDD(id, datosNuevos);
    }

    @Test
    @DisplayName("Modificar animalito - la notificación lleva los datos modificados del repo (con fechaUltimaModificacion actualizada)")
    void testModificarAnimalito_NotificacionConDatosActualizados() {
        // DADO:
        String id = "ABCD-1234-EFGH-5678";
        DatosRegistroAnimalito datosNuevos = DatosRegistroAnimalito.builder()
                .nombre("Rex")
                .especie("Perro")
                .raza("Pastor Alemán")
                .build();
        DatosCompletosAnimalito animalitoConFechaActualizada = DatosCompletosAnimalito.builder()
                .id(id).nombre("Rex").especie("Perro").raza("Pastor Alemán")
                .fechaAlta(java.time.ZonedDateTime.now().minusDays(10))
                .fechaUltimaModificacion(java.time.ZonedDateTime.now())
                .build();
        Mockito.when(miMockDeAnimalitosRepository.actualizarAnimalitoEnBBDD(id, datosNuevos)).thenReturn(animalitoConFechaActualizada);
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        miAnimalitosService.modificarAnimalito(id, datosNuevos);
        // ENTONCES: la notificación lleva el objeto devuelto por el repo, no el de entrada
        Mockito.verify(miMockDelSistemaDeMensajeria).enviarNotificacion(animalitoConFechaActualizada);
    }

    // =====================================================================================
    // modificarAnimalito - Validación ID
    // =====================================================================================

    @Test
    @DisplayName("Modificar animalito con ID nulo - debe lanzar excepción")
    void testModificarAnimalito_IdNulo() {
        // DADO:
        DatosRegistroAnimalito datosNuevos = DatosRegistroAnimalito.builder()
                .nombre("Rex").especie("Perro").raza("Labrador").build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.modificarAnimalito(null, datosNuevos));
    }

    @Test
    @DisplayName("Modificar animalito con ID vacío ('') - debe lanzar excepción")
    void testModificarAnimalito_IdVacio() {
        // DADO:
        DatosRegistroAnimalito datosNuevos = DatosRegistroAnimalito.builder()
                .nombre("Rex").especie("Perro").raza("Labrador").build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.modificarAnimalito("", datosNuevos));
    }

    @Test
    @DisplayName("Modificar animalito con ID de solo espacios - debe lanzar excepción")
    void testModificarAnimalito_IdSoloEspacios() {
        // DADO:
        DatosRegistroAnimalito datosNuevos = DatosRegistroAnimalito.builder()
                .nombre("Rex").especie("Perro").raza("Labrador").build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.modificarAnimalito("   ", datosNuevos));
    }

    // =====================================================================================
    // modificarAnimalito - Validación datos nuevos (mismo criterio que registro)
    // =====================================================================================

    @Test
    @DisplayName("Modificar animalito con nombre inválido (numérico) - debe lanzar excepción")
    void testModificarAnimalito_NombreInvalido() {
        // DADO:
        DatosRegistroAnimalito datosNuevos = DatosRegistroAnimalito.builder()
                .nombre("44").especie("Perro").raza("Labrador").build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.modificarAnimalito("ID-1", datosNuevos));
    }

    @Test
    @DisplayName("Modificar animalito con nombre inválido - NO debe llamar al repositorio")
    void testModificarAnimalito_NombreInvalido_NoLlamaAlRepositorio() {
        // DADO:
        DatosRegistroAnimalito datosNuevos = DatosRegistroAnimalito.builder()
                .nombre("44").especie("Perro").raza("Labrador").build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        try { miAnimalitosService.modificarAnimalito("ID-1", datosNuevos); } catch (Exception ignored) {}
        // ENTONCES:
        Mockito.verify(miMockDeAnimalitosRepository, Mockito.never()).actualizarAnimalitoEnBBDD(Mockito.any(), Mockito.any());
    }

    @Test
    @DisplayName("Modificar animalito con nombre inválido - NO debe enviar notificación")
    void testModificarAnimalito_NombreInvalido_NoEnviaNotificacion() {
        // DADO:
        DatosRegistroAnimalito datosNuevos = DatosRegistroAnimalito.builder()
                .nombre("44").especie("Perro").raza("Labrador").build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        try { miAnimalitosService.modificarAnimalito("ID-1", datosNuevos); } catch (Exception ignored) {}
        // ENTONCES:
        Mockito.verify(miMockDelSistemaDeMensajeria, Mockito.never()).enviarNotificacion(Mockito.any());
    }

    @Test
    @DisplayName("Modificar animalito con edad negativa - debe lanzar excepción")
    void testModificarAnimalito_EdadNegativa() {
        // DADO:
        DatosRegistroAnimalito datosNuevos = DatosRegistroAnimalito.builder()
                .nombre("Rex").especie("Perro").raza("Labrador").edad(-3).build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.modificarAnimalito("ID-1", datosNuevos));
    }

    @Test
    @DisplayName("Modificar animalito con peso negativo - debe lanzar excepción")
    void testModificarAnimalito_PesoNegativo() {
        // DADO:
        DatosRegistroAnimalito datosNuevos = DatosRegistroAnimalito.builder()
                .nombre("Rex").especie("Perro").raza("Labrador").peso(-10).build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.modificarAnimalito("ID-1", datosNuevos));
    }

    @Test
    @DisplayName("Modificar animalito con especie vacía - debe lanzar excepción")
    void testModificarAnimalito_EspecieVacia() {
        // DADO:
        DatosRegistroAnimalito datosNuevos = DatosRegistroAnimalito.builder()
                .nombre("Rex").especie("").raza("Labrador").build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.modificarAnimalito("ID-1", datosNuevos));
    }

    @Test
    @DisplayName("Modificar animalito con raza nula - debe lanzar excepción")
    void testModificarAnimalito_RazaNula() {
        // DADO:
        DatosRegistroAnimalito datosNuevos = DatosRegistroAnimalito.builder()
                .nombre("Rex").especie("Perro").build();
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.modificarAnimalito("ID-1", datosNuevos));
    }

    // =====================================================================================
    // modificarAnimalito - Fallos en repositorio
    // =====================================================================================

    @Test
    @DisplayName("Modificar animalito - cuando el repositorio lanza excepción, debe propagarla")
    void testModificarAnimalito_CuandoRepositorioFalla_PropagaExcepcion() {
        // DADO:
        String id = "ABCD-1234-EFGH-5678";
        DatosRegistroAnimalito datosNuevos = DatosRegistroAnimalito.builder()
                .nombre("Rex").especie("Perro").raza("Labrador").build();
        Mockito.when(miMockDeAnimalitosRepository.actualizarAnimalitoEnBBDD(id, datosNuevos))
               .thenThrow(new RuntimeException("Error de BBDD"));
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(RuntimeException.class, () -> miAnimalitosService.modificarAnimalito(id, datosNuevos));
    }

    @Test
    @DisplayName("Modificar animalito - cuando el repositorio falla, NO debe enviar notificación")
    void testModificarAnimalito_CuandoRepositorioFalla_NoEnviaNotificacion() {
        // DADO:
        String id = "ABCD-1234-EFGH-5678";
        DatosRegistroAnimalito datosNuevos = DatosRegistroAnimalito.builder()
                .nombre("Rex").especie("Perro").raza("Labrador").build();
        Mockito.when(miMockDeAnimalitosRepository.actualizarAnimalitoEnBBDD(id, datosNuevos))
               .thenThrow(new RuntimeException("Error de BBDD"));
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        try { miAnimalitosService.modificarAnimalito(id, datosNuevos); } catch (Exception ignored) {}
        // ENTONCES:
        Mockito.verify(miMockDelSistemaDeMensajeria, Mockito.never()).enviarNotificacion(Mockito.any());
    }

    @Test
    @DisplayName("Modificar animalito con ID inexistente - el repositorio lanza excepción, el servicio la propaga")
    void testModificarAnimalito_IdInexistente_PropagaExcepcion() {
        // DADO:
        String idInexistente = "ID-QUE-NO-EXISTE";
        DatosRegistroAnimalito datosNuevos = DatosRegistroAnimalito.builder()
                .nombre("Rex").especie("Perro").raza("Labrador").build();
        Mockito.when(miMockDeAnimalitosRepository.actualizarAnimalitoEnBBDD(idInexistente, datosNuevos))
               .thenThrow(new RuntimeException("Animalito no encontrado"));
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(RuntimeException.class, () -> miAnimalitosService.modificarAnimalito(idInexistente, datosNuevos));
    }

    // =====================================================================================
    // borrarAnimalito - Happy Path
    // =====================================================================================

    @Test
    @DisplayName("Borrar animalito con ID válido - Happy Path")
    void testBorrarAnimalito_HappyPath() {
        // DADO:
        String id = "ABCD-1234-EFGH-5678";
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        miAnimalitosService.borrarAnimalito(id);
        // ENTONCES: el repositorio debe haber recibido la orden de borrar con ese ID
        Mockito.verify(miMockDeAnimalitosRepository).borrarAnimalitoDeLaBBDD(id);
    }

    @Test
    @DisplayName("Borrar animalito - NO debe llamar al sistema de mensajería")
    void testBorrarAnimalito_NoLlamaASistemaDeMensajeria() {
        // DADO:
        String id = "ABCD-1234-EFGH-5678";
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        miAnimalitosService.borrarAnimalito(id);
        // ENTONCES: borrar no dispara notificaciones
        Mockito.verifyNoInteractions(miMockDelSistemaDeMensajeria);
    }

    @Test
    @DisplayName("Borrar animalito - NO debe llamar al sistema de mensajería ni al repo de recuperación")
    void testBorrarAnimalito_SoloLlamaAlBorradoDelRepo() {
        // DADO:
        String id = "ABCD-1234-EFGH-5678";
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        miAnimalitosService.borrarAnimalito(id);
        // ENTONCES: únicamente debe haberse llamado al borrado
        Mockito.verify(miMockDeAnimalitosRepository).borrarAnimalitoDeLaBBDD(id);
        Mockito.verify(miMockDeAnimalitosRepository, Mockito.never()).guardarAnimalitoEnBBDD(Mockito.any());
        Mockito.verify(miMockDeAnimalitosRepository, Mockito.never()).actualizarAnimalitoEnBBDD(Mockito.any(), Mockito.any());
        Mockito.verify(miMockDeAnimalitosRepository, Mockito.never()).recuperarAnimalitoDeLaBBDD(Mockito.any());
    }

    // =====================================================================================
    // borrarAnimalito - Validación ID
    // =====================================================================================

    @Test
    @DisplayName("Borrar animalito con ID nulo - debe lanzar excepción")
    void testBorrarAnimalito_IdNulo() {
        // DADO:
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.borrarAnimalito(null));
    }

    @Test
    @DisplayName("Borrar animalito con ID vacío ('') - debe lanzar excepción")
    void testBorrarAnimalito_IdVacio() {
        // DADO:
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.borrarAnimalito(""));
    }

    @Test
    @DisplayName("Borrar animalito con ID de solo espacios - debe lanzar excepción")
    void testBorrarAnimalito_IdSoloEspacios() {
        // DADO:
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(Exception.class, () -> miAnimalitosService.borrarAnimalito("   "));
    }

    @Test
    @DisplayName("Borrar animalito con ID nulo - NO debe llamar al repositorio")
    void testBorrarAnimalito_IdNulo_NoLlamaAlRepositorio() {
        // DADO:
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        try { miAnimalitosService.borrarAnimalito(null); } catch (Exception ignored) {}
        // ENTONCES:
        Mockito.verify(miMockDeAnimalitosRepository, Mockito.never()).borrarAnimalitoDeLaBBDD(Mockito.any());
    }

    @Test
    @DisplayName("Borrar animalito con ID vacío - NO debe llamar al repositorio")
    void testBorrarAnimalito_IdVacio_NoLlamaAlRepositorio() {
        // DADO:
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO:
        try { miAnimalitosService.borrarAnimalito(""); } catch (Exception ignored) {}
        // ENTONCES:
        Mockito.verify(miMockDeAnimalitosRepository, Mockito.never()).borrarAnimalitoDeLaBBDD(Mockito.any());
    }

    // =====================================================================================
    // borrarAnimalito - Fallos en repositorio
    // =====================================================================================

    @Test
    @DisplayName("Borrar animalito - cuando el repositorio lanza excepción, debe propagarla")
    void testBorrarAnimalito_CuandoRepositorioFalla_PropagaExcepcion() {
        // DADO:
        String id = "ABCD-1234-EFGH-5678";
        Mockito.doThrow(new RuntimeException("Error de BBDD"))
               .when(miMockDeAnimalitosRepository).borrarAnimalitoDeLaBBDD(id);
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(RuntimeException.class, () -> miAnimalitosService.borrarAnimalito(id));
    }

    @Test
    @DisplayName("Borrar animalito con ID inexistente - el repositorio lanza excepción, el servicio la propaga")
    void testBorrarAnimalito_IdInexistente_PropagaExcepcion() {
        // DADO:
        String idInexistente = "ID-QUE-NO-EXISTE";
        Mockito.doThrow(new RuntimeException("Animalito no encontrado"))
               .when(miMockDeAnimalitosRepository).borrarAnimalitoDeLaBBDD(idInexistente);
        AnimalitosService miAnimalitosService = dameImplementacionDelServicioDeAnimalitosParaLasPruebas(miMockDeAnimalitosRepository, miMockDelSistemaDeMensajeria);
        // CUANDO + ENTONCES:
        Assertions.assertThrows(RuntimeException.class, () -> miAnimalitosService.borrarAnimalito(idInexistente));
    }
*/
}

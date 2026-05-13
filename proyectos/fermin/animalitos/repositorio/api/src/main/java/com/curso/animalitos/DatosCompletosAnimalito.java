package com.curso.animalitos;

import java.time.ZonedDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DatosCompletosAnimalito {

    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private int peso;
    private String numeroIdentificacion;
    private String observaciones;
    private String id;
    private ZonedDateTime fechaAlta;
    private ZonedDateTime fechaUltimaModificacion;
}

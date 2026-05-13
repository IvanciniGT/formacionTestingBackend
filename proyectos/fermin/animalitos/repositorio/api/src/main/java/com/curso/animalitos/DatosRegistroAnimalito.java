package com.curso.animalitos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DatosRegistroAnimalito {

    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private int peso;
    private String numeroIdentificacion;
    private String observaciones;

}

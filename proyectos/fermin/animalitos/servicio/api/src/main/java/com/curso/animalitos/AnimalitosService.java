package com.curso.animalitos;

public interface AnimalitosService {

    DatosCompletosAnimalito registrarAnimalito(DatosRegistroAnimalito datosGuays);

    DatosCompletosAnimalito recuperarAnimalito(String id);

    DatosCompletosAnimalito modificarAnimalito(String id, DatosRegistroAnimalito datosNuevos);

    void borrarAnimalito(String id);

}

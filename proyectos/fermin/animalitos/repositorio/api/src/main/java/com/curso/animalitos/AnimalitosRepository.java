package com.curso.animalitos;

public interface AnimalitosRepository {

    DatosCompletosAnimalito guardarAnimalitoEnBBDD(DatosRegistroAnimalito datosGuays);

    DatosCompletosAnimalito recuperarAnimalitoDeLaBBDD(String id);

    DatosCompletosAnimalito actualizarAnimalitoEnBBDD(String id, DatosRegistroAnimalito datosNuevos);

    void borrarAnimalitoDeLaBBDD(String id);

}

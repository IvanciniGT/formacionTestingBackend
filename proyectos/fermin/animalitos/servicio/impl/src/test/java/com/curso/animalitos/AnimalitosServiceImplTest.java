package com.curso.animalitos;

class AnimalitosServiceImplTest extends AnimalitosServiceTest{

    protected AnimalitosService dameImplementacionDelServicioDeAnimalitosParaLasPruebas(AnimalitosRepository miRepo, SistemaDeMensajeria miSistemaDeMensajeria){
        return new AnimalitosServiceImpl(miRepo, miSistemaDeMensajeria);
    }


}

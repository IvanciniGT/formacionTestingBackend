package com.curso.animalitos;

public class AnimalitosServiceImpl implements AnimalitosService {

    private final AnimalitosRepository repositorio;
    private final SistemaDeMensajeria sistemaDeMensajeria;

    public AnimalitosServiceImpl(AnimalitosRepository repositorio,
                                 SistemaDeMensajeria sistemaDeMensajeria) {
            this.repositorio = repositorio;
            this.sistemaDeMensajeria = sistemaDeMensajeria;
    }

    @Override
    public DatosCompletosAnimalito registrarAnimalito(DatosRegistroAnimalito datosGuays) {
        DatosCompletosAnimalito animalitoRegistrado =repositorio.guardarAnimalitoEnBBDD(datosGuays);
        sistemaDeMensajeria.enviarNotificacion(animalitoRegistrado);
        return animalitoRegistrado;
    }

}

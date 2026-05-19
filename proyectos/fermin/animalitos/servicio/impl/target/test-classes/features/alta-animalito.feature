#language:es

Característica: Recuperar animalito

  Como usuario del sistema de gestión de animalitos
  Quiero poder recuperar un animalito registrado en el sistema
  Para que pueda ver sus detalles y gestionarlo posteriormente

  Escenario: Recuperar un animalito registrado con datos válidos
    Dado que tengo los datos de un animalito.
        Y su nombre es: "Rocky"
        Y su especie es: "Perro"
        Y su raza es: "Labrador"
        Y su edad es: 2
        Y su peso es: 20
        Y su número de identificación es: "XXXXXXX"
        Y sus observaciones son: "Es un perro muy juguetón y cariñoso".
        Y su identificación es: "ABCD-1234-EFGH-5678"
        Y su fecha de alta es: "10-10-2023"
        Y su fecha de última modificación es: "10-10-2023"  
        Y que lo tengo registrado en un mock del repositorio de animalitos.
        Y que tengo un servicio de animalitos que usa el mock del repositorio de animalitos y un sistema de mensajería de mentirijilla.
    Cuando llamo a la función recuperarAnimalito del servicio de animalitos, pasando el ID del animalito registrado como parámetro.
    Entonces se devuelve un objeto con los datos completos del animalito registrado
        Y el ID devuelto es: "ABCD-1234-EFGH-5678"
        Y la fecha de alta devuelta es: "10-10-2023"
        Y la fecha de última modificación devuelta es: "10-10-2023"
        Y el nombre devuelto es: "Rocky"
        Y la especie devuelta es: "Perro"
        Y la raza devuelta es: "Labrador"
        Y la edad devuelta es: 2
        Y el peso devuelto es: 20
        Y el número de identificación devuelto es: "XXXXXXX"
        Y las observaciones devueltas son: "Es un perro muy juguetón y cariñoso".

Esquema del escenario: Recuperar un animalito registrado con datos válidos
    Dado que tengo los datos de un animalito.
        Y su nombre es: "<nombre>"
        Y su especie es: "<especie>"
        Y su raza es: "<raza>"
        Y su edad es: <edad>
        Y su peso es: <peso>
        Y su número de identificación es: "<numeroIdentificacion>"
        Y sus observaciones son: "<observaciones>".
        Y su identificación es: "<id>"
        Y su fecha de alta es: "<fechaAlta>"
        Y su fecha de última modificación es: "<fechaUltimaModificacion>"  
        Y que lo tengo registrado en un mock del repositorio de animalitos.
        Y que tengo un servicio de animalitos que usa el mock del repositorio de animalitos y un sistema de mensajería de mentirijilla.
    Cuando llamo a la función recuperarAnimalito del servicio de animalitos, pasando el ID del animalito registrado como parámetro.
    Entonces se devuelve un objeto con los datos completos del animalito registrado
        Y el ID devuelto es: "<id>"
        Y la fecha de alta devuelta es: "<fechaAlta>"
        Y la fecha de última modificación devuelta es: "<fechaUltimaModificacion>"
        Y el nombre devuelto es: "<nombre>"
        Y la especie devuelta es: "<especie>"
        Y la raza devuelta es: "<raza>"
        Y la edad devuelta es: <edad>
        Y el peso devuelto es: <peso>
        Y el número de identificación devuelto es: "<numeroIdentificacion>"
        Y las observaciones devueltas son: "<observaciones>".

    Ejemplos:
      | nombre | especie | raza | edad | peso | numeroIdentificacion | observaciones | id | fechaAlta | fechaUltimaModificacion |
      | Rocky  | Perro   | Labrador | 2   | 20   | XXXXXXX              | Es un perro muy juguetón y cariñoso. | ABCD-1234-EFGH-5678 | 10-10-2023 | 10-10-2023 |
      | Luna   | Gato    | Siames  | 3   | 5    | YYYYYYY              | Es una gata muy tranquila y cariñosa. | IJKL-5678-MNOP-1234 | 15-11-2023 | 15-11-2023 |
      | Max    | Perro   | Bulldog  | 4   | 25   | ZZZZZZZ              | Es un perro muy fuerte y protector. | QRST-9012-UVWX-3456 | 20-12-2023 | 20-12-2023 |

Escenario: Crear un animalito con datos guays
    Dado que tengo los datos de un animalito.
        Y su nombre es: "Rocky"
        Y su especie es: "Perro"
        Y su raza es: "Labrador"
        Y su edad es: 2
        Y su peso es: 20
        Y su número de identificación es: "XXXXXXX"
        Y sus observaciones son: "Es un perro muy juguetón y cariñoso".
        Y su identificación es: "ABCD-1234-EFGH-5678"
        Y su fecha de alta es: "10-10-2023"
        Y su fecha de última modificación es: "10-10-2023"  
    Cuando llamo a la función crearAnimalito del servicio de animalitos, pasando los datos del animalito como parámetro.
    Entonces se crea un nuevo animalito con los datos proporcionados y se devuelve el ID del nuevo animalito creado
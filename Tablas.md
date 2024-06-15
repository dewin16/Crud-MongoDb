<<<<<<< HEAD
Hoteles 
    Requeridos:
        -Long id
        -String nombre hotel
        -Object model direccion [String calle, String ciudad, String codigo postal] 
        -Object entity clientes? [{id}] 
        -Object model reseñas[{String nombrecliente, int valoracion, String texto}] 
        -Object entity tipo_habitacion [{id}] 
        
        Opcionales:
        -campos a mayores(piscina,restaurante,desayuno, x)
            -se define el tipo de dato en su creacion

Habitaciones (lo queremos separado para tener un listado con todas las habitaciones y su disponibilidad?)
    Requeridos:
        -Lonng id;
       -Object entity tipo_de_habitacion: {id}
        -String precio
        -Boolean isAvaliable
        -Object model reservas[{@idcliente,checkin checkout}]    
            -reservas tiene una clase model con un constructor que actualiza el isAvaliable cuando se crea
            si no se puede hacer asi va en una coleccion solo
            -metodo que compare fecha actual con la fecha del checkout y que vuelva a poner la habitacion disponible

Tipo de habitaciones
    Requeridos:
        -Long id
        -String nombre
        -String nº de camas
        -String espacio

    Opcionales (wifi,aire,acondicionado, articulos de aseo, minibar)    

Clientes
    Requeridos:
        -Long id
        -String nombre
        -String telefono
        -String correo
    Opcionales (edad, , sexo, isVip, nacionalidad, x )


¿Login? -> security


=======
# reservaHotel
>>>>>>> f92a72225ba1f7b8e28a601322159d58e7edb0ae

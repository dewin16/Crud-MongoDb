1) - Dentro de los errores se utilizan expresiones lambda para optimizar el rendimiento,
solo se crea la String en el caso en el que se haya producido el error gracias al uso de la expresion lambda. (DENTRO DEL METODO ASSERTALL)
2) - AssertAll hace que te comprueben todos los errores y que no se quede parado cuando encuentra el primero.
3) - Un mock es un objeto simulado, al que se le puede asignar un valor predeterminado.
    Un uso comun de este es para separar pruebas de distintos microservicios que esten comunicados entre si,
    dado que cada uno de estos microservicios tendria que tener test independientes.
    Adicionalmente, se evita acceder a estos lo cual produce una mejora en el rendimiento.
    
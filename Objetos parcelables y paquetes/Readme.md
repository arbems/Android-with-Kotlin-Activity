# Android con Kotlin - Activity - Objetos parcelables y paquetes

Código de ejemplo Objetos parcelables y paquetes en Android con Kotlin.

Existen los objetos Parcelable y Bundle, están diseñados para usarse entre límites de procesos como con transacciones de IPC y Binder. También entre actividades con intents y para almacenar el estado transitorio en los cambios de configuración.

`Nota: Parcel no es un mecanismo de serialización de uso general y nunca debes almacenar datos de Parcel en el disco ni enviarlos por medio de la red.`

Parcelable es más ligero y rápido que el uso de Serializable.

Cuando envíes datos mediante un intent, debes tener cuidado de limitar el tamaño de los datos a unos pocos KB, ya que, si se envían demasiados, es posible que el sistema arroje una excepción TransactionTooLargeException.

## Envío de datos entre actividades

Cuando una app crea un objeto Intent para usar en startActivity(android.content.Intent) al iniciar una nueva Actividad, la app puede pasar parámetros por medio del método putExtra(java.lang.String, java.lang.String).

    val intent = Intent(this, MyActivity::class.java).apply {
        putExtra(key, value)
        // ...
    }
    startActivity(intent)
El SO empaqueta el Bundle subyacente del intent. Luego, el SO crea la nueva actividad, desempaqueta los datos y pasa el intent a la nueva actividad.

#### writeToParcel

También debe proporcionar un campo no nulo llamado CREATOR que implemente la interfaz Parcelable.Creator, cuyo método createFromParcel() se usa para convertir el elemento Parcel nuevamente en el objeto actual.

En algunos casos, es posible que necesites un mecanismo para enviar objetos compuestos o complejos entre actividades. En esos casos, la clase personalizada debe implementar un objeto Parcelable.

## Envío de datos entre procesos

El envío de datos entre procesos es similar a hacerlo entre actividades. Sin embargo, te recomendamos que, cuando envíes mensajes entre procesos, no uses objetos parcelables personalizados. Si envías un objeto Parcelable personalizado de una app a otra, debes asegurarte de que la misma versión de la clase personalizada esté presente en las apps de envío y recepción.

Por lo general, esta podría ser una biblioteca común utilizada en ambas apps. Es posible que se produzca un error si tu app intenta enviar un objeto parcelable personalizado al sistema, ya que el sistema no puede ordenar una clase que no conoce.

El búfer de transacciones de Binder tiene un tamaño fijo limitado, actualmente de 1 MB, que comparten todas las transacciones en curso del proceso. Como este límite se encuentra en el nivel del proceso en lugar de en el de cada actividad, estas transacciones incluyen todas las transacciones de Binder, como onSaveInstanceState, startActivity y cualquier interacción con el sistema. Cuando se supera el límite de tamaño, se genera una TransactionTooLargeException.

En el caso específico de savedInstanceState, se debe mantener una pequeña cantidad de datos porque el proceso del sistema debe retener los datos proporcionados durante el tiempo que el usuario pueda volver a esa actividad (incluso si se elimina el proceso). Te recomendamos que mantengas el estado guardado por debajo de los 50,000 datos.


## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
# Android con Kotlin - Activity - Guardar y restablecer el estado de la IU

Código de ejemplo que muestra como guardar y restablecer el estado de la IU en Android con Kotlin.

Diferentes eventos, algunos activados por el usuario y otros activados por el sistema, pueden provocar que una Activity pase de un estado a otro.

Hay que conservar el estado de la IU de una actividad durante los cambios de configuración usando una combinación de ViewModels, el método onSaveInstanceState() y él almacenamiento persistente.

Decidir cómo combinar estas opciones depende de la complejidad de los datos de tu IU, los casos prácticos de tu app y la consideración de la velocidad de recuperación frente al uso de memoria.
      
`Para almacenar datos complejos usar combinación de almacenamiento local persistente, el método onSaveInstanceState() y la clase ViewModel.`
         
## Guardar y restablecer de manera eficaz el estado de la IU dividiendo el trabajo entre los diversos tipos de mecanismos de persistencia

En la mayoría de los casos, cada uno de estos mecanismos debe almacenar un tipo diferente de datos utilizados en la actividad, en función de las compensaciones de la complejidad de los datos, la velocidad de acceso y el ciclo de vida:

### ViewModel

Almacena en la memoria todos los datos necesarios para mostrar el controlador de IU asociado.

### Estado de instancia guardado - onSaveInstanceState()

Almacena una pequeña cantidad de datos necesarios para volver a cargar fácilmente el estado de una actividad si se detiene el sistema y, luego, vuelve a crear el controlador de IU. En lugar de almacenar objetos complejos en este lugar, consérvalos en un almacenamiento local y almacena un ID único para esos objetos en onSaveInstanceState().

### Almacenamiento persistente

Almacena todos los datos que no quieras perder cuando abras y cierres la actividad.

![Lifecycle Activity](https://github.com/arbems/Android-with-Kotlin-Activity/blob/master/activity-(save-and-reset-ui-status)/0001.png)

## Estado de instancia (onSaveInstanceState)

Los datos guardados que el sistema utiliza para restaurar el estado previo se denominan estado de instancia y son un conjunto de pares clave-valor almacenados en un objeto Bundle.

De forma predeterminada, el sistema utiliza el Bundle de estado de instancia para guardar información de cada objeto View del diseño de tu actividad (por ejemplo, el valor de texto ingresado en un widget EditText).

Sin embargo, es posible que tu actividad tenga más información de estado que desees restablecer, como variables de miembro que siguen el progreso del usuario en la actividad.

`Aunque el estado de las vistas se guarda automáticamente, las variables miembros de la actividad serán destruidos junto con Actividad. Hay que guardar y restaurar manualmente a través de onSaveInstanceState() y onRestoreInstanceState().`

Si el sistema finaliza la actividad debido a restricciones (como un cambio de configuración o presión de memoria), entonces, aunque haya desaparecido la instancia real Activity, el sistema recuerda que existía. 

Si el usuario intenta volver a la actividad, el sistema crea una nueva instancia de esa actividad utilizando un conjunto de datos guardados que describen el estado de la actividad cuando finalizó.

Si los datos de la IU son simples y ligeros, como un tipo de datos primitivos o un objeto simple (como String), puedes utilizar onSaveInstanceState() solamente para mantener el estado de la IU tanto en los cambios de configuración como en la finalización del proceso iniciado por el sistema.

A medida que comienza a detenerse tu actividad, el sistema llama al método onSaveInstanceState(outState: Bundle?).

* Tiene costos de serialización y deserialización, puede consumir mucha memoria si los objetos que se serializan son demasiado complejos.

* No se llama a onSaveInstanceState() cuando el usuario cierra explícitamente la actividad (botón atrás, finish()).

`Un objeto Bundle no es apropiado para preservar más que una cantidad trivial de datos, debido a que requiere serialización en el subproceso principal y consume memoria del proceso del sistema.`

`Nota: Para que el sistema Android restablezca el estado de las vistas de tu actividad, cada vista debe tener un ID único provisto por el atributo android:id.`

`Para preservar cantidades más grandes de datos, hay que combinar un almacenamiento local persistente, el método onSaveInstanceState() y la clase ViewModel.`

### GUARDAR UN ESTADO DE IU SIMPLE Y LIGERO USANDO onSaveInstanceState()
    
A medida que comienza a detenerse tu actividad, el sistema llama al método onSaveInstanceState() para que tu actividad pueda guardar la información del estado en un paquete de estado de instancia. La implementación predeterminada de ese método guarda información transitoria acerca del estado de la jerarquía de vistas de la actividad, como el texto de un widget EditText o la posición de desplazamiento de un widget ListView.

Para guardar información adicional sobre el estado de la instancia de tu actividad, debes anular onSaveInstanceState() y agregar pares clave-valor al objeto Bundle que se guarda en caso de que tu actividad finalice de forma inesperada.

Si sustituyes enSaveInstanceState(), debes llamar a la implementación de superclase si deseas que la implementación predeterminada guarde el estado de la jerarquía de vistas. Por ejemplo:

`Nota: No se llama a onSaveInstanceState() cuando el usuario cierra explícitamente la actividad o en otros casos cuando llamas a finish().`

####
    override fun onSaveInstanceState(outState: Bundle?) {
        // Save the user's current game state
        outState?.run {
            putInt(STATE_SCORE, currentScore)
            putInt(STATE_LEVEL, currentLevel)
        }
    
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(outState)
    }
    
### RESTABLECER ESTADO DE IU DE LA ACTIVIDAD USANDO ESTADO DE LA INSTANCIA GUARDADA
    
Cuando se vuelve a crear tu actividad tras haber finalizado, puedes recuperar la instancia del estado guardado desde el Bundle que el sistema pasa a tu actividad. Los métodos de devolución de llamada onCreate() y onRestoreInstanceState() reciben el mismo Bundle que contiene la información del estado de la instancia.

Dado que se llama al método onCreate() tanto si el sistema crea una nueva instancia de tu actividad como si vuelve a crear una instancia previa, debes comprobar si el Bundle de estado es nulo antes de intentar leerlo. Si es nulo, el sistema creará una instancia nueva de la actividad en lugar de restablecer una previa que ya haya finalizado.

En lugar de restaurar el estado durante onCreate(), puedes optar por implementar onRestoreInstanceState(), al que el sistema llama después del método onStart(). El sistema llama a onRestoreInstanceState() solo si hay un estado guardado para restablecer, por lo que no necesitas comprobar si Bundle es nulo.

####
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Always call the superclass first
    
        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                // Restore value of members from saved state
                currentScore = getInt(STATE_SCORE)
                currentLevel = getInt(STATE_LEVEL)
            }
        } else {
            // Probably initialize members with default values for a new instance
        }
        // ...
    }
    
####
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState)
    
        // Restore state members from saved instance
        savedInstanceState?.run {
            currentScore = getInt(STATE_SCORE)
            currentLevel = getInt(STATE_LEVEL)
        }
    }
    
<br/>
BUNDLE:

Los datos guardados que el sistema utiliza para restaurar el estado previo se denominan estado de instancia y son un conjunto de pares clave-valor almacenados en un objeto Bundle.

De forma predeterminada, el sistema utiliza el Bundle de estado de instancia para guardar información de cada objeto View del diseño de tu actividad (por ejemplo, el valor de texto ingresado en un objeto EditText).

Un objeto Bundle no es apropiado para preservar más que una cantidad trivial de datos, debido a que requiere serialización en el subproceso principal y consume memoria del proceso del sistema.

Para restablecer el estado de la IU de la actividad utilizando la instancia guardada mediante el Bundle que el sistema pasa a los métodos de devolución de llamada onCreate() y onRestoreInstanceState() con la información del estado de la instancia.

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
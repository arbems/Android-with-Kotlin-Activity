# Android con Kotlin - Activity - Guardar y restablecer el estado de la IU

Código de ejemplo de como guardar y restablecer el estado de la IU usando el método onSaveInstanceState(), ViewModels & SavedState y el almacenamiento persistente en Android con Kotlin.

Hay que conservar el estado de la IU de una actividad usando una combinación de **ViewModels**, el método **onSaveInstanceState()** y el **almacenamiento persistente**, cómo combinar estas opciones depende de la complejidad de los datos de la IU, los casos prácticos de tu app y la consideración de la velocidad de recuperación frente al uso de memoria.
       
En la mayoría de los casos, cada uno de estos mecanismos debe almacenar un tipo diferente de datos utilizados en la actividad, en función de las compensaciones de la complejidad de los datos, la velocidad de acceso y el ciclo de vida.

## Expectativas del usuario y comportamiento del sistema

Existen algunas situaciones en las que finaliza tu actividad debido al comportamiento normal de la app (botón Atrás o finish()). Cuando finaliza tu actividad porque el usuario presiona Atrás o la actividad se finaliza a sí misma, se pierde para siempre el concepto de esa instancia Activity del sistema y del usuario. En esos casos, las expectativas del usuario coinciden con el comportamiento del sistema y no tienes trabajo adicional que hacer.

Sin embargo, si el sistema finaliza la actividad debido a restricciones (como un cambio de configuración o presión de memoria), entonces, aunque haya desaparecido la instancia real Activity, el sistema recuerda que existía. Si el usuario intenta volver a la actividad, el sistema crea una nueva instancia de esa actividad utilizando un conjunto de datos guardados que describen el estado de la actividad cuando finalizó.

#### Descarte del estado de la IU iniciado por el usuario

El usuario espera que, cuando comience una actividad, el estado transitorio de la IU de esa actividad permanezca igual hasta que descarte por completo la actividad. El usuario puede descartar una actividad por completo con una de estas acciones:

* presionar el botón Atrás
* deslizar la actividad hacia fuera de la pantalla Overview (Recents)
* navegar hacia arriba desde la actividad
* eliminar la aplicación de la pantalla Configuración
* completar algún tipo de actividad de "finalización" (que está respaldada por Activity.finish())

El comportamiento del sistema coincide con la expectativa del usuario: en este caso se destruye la instancia de la actividad y se quita de la memoria, junto con cualquier estado almacenado en ella y cualquier registro de estado de instancia guardado y asociado con la actividad.

#### Descarte del estado de la IU iniciado por el sistema

El usuario espera que se conserve el estado de la IU de una actividad durante un cambio de configuración, como la rotación o el cambio al modo multi-ventana. Sin embargo, de forma predeterminada, el sistema destruye la actividad cuando se produce este cambio de configuración, y borra cualquier estado de IU almacenado en la instancia de la actividad.

El usuario también espera que se conserve el estado de la IU de tu actividad si cambia temporalmente a una app diferente y vuelve a la app más tarde.

El sistema hace todo lo posible para mantener el proceso de la app en la memoria. Sin embargo, el sistema puede destruir el proceso de la aplicación mientras el usuario está interactuando con otras apps. En ese caso, se destruye la instancia de la actividad, junto con cualquier estado almacenado en ella.

## Opciones para preservar el estado de la IU

Cuando las expectativas del usuario sobre el estado de la IU no coinciden con el comportamiento predeterminado del sistema, debes guardar y restablecer el estado de la IU del usuario para garantizar que la destrucción iniciada por el sistema sea transparente para el usuario.

![Lifecycle Activity](https://raw.githubusercontent.com/arbems/Android-with-Kotlin-Activity/master/Guardar%20y%20restablecer%20estado%20de%20la%20IU/0001.png)


## Guardar y restablecer el estado usando onSaveInstanceState()

Se usa [**onSaveInstanceState()**](https://developer.android.com/reference/android/app/Activity#onSaveInstanceState(android.os.Bundle)) para guardar un estado de IU simple y ligero, como un tipo de datos primitivos o un objeto simple (como String), puedes utilizar onSaveInstanceState() para mantener el estado de la IU tanto en los cambios de configuración como en la finalización del proceso iniciado por el sistema.
Aunque haya desaparecido la instancia real Activity, el sistema recuerda que existía. 

Si el usuario intenta volver a la actividad, el sistema crea una nueva instancia de esa actividad utilizando un conjunto de datos guardados que describen el estado de la actividad cuando finalizó.

Aunque el estado de las vistas se guarda automáticamente, las variables miembros de la actividad serán destruidos junto con la Activity. Hay que guardar y restaurar manualmente a través de **onSaveInstanceState()** y **onRestoreInstanceState()**.

No uses onSavedInstanceState() para almacenar grandes cantidades de datos, como mapas de bits o estructuras de datos complejas que requieran serialización o deserialización extensas. En cambio, almacena solo tipos primitivos y objetos pequeños y simples, como strings.

La mayoría de las apps deberían implementar onSaveInstanceState() para manejar el cierre del proceso iniciado por el sistema.

### Estado de la instancia

Los datos guardados que el sistema utiliza para restaurar el estado previo se denominan estado de instancia y son un conjunto de pares clave-valor almacenados en un objeto Bundle. De forma predeterminada, el sistema utiliza el Bundle de estado de instancia para guardar información de cada objeto View del diseño de tu actividad.

De este modo, si finaliza y se vuelve a crear la instancia de tu actividad, se restablece el estado del diseño a su estado previo sin necesidad de que escribas el código. Sin embargo, es posible que tu actividad tenga más información de estado que desees restablecer, como variables de miembro que siguen el progreso del usuario en la actividad.

`Nota: Para que el sistema Android restablezca el estado de las vistas de tu actividad, cada vista debe tener un ID único provisto por el atributo android:id.`

### Guardar estado con onSaveInstanceState()

De forma predeterminada, el sistema utiliza el [**Bundle**](https://developer.android.com/reference/android/os/Bundle) de estado de instancia para guardar información de cada objeto View del diseño de tu actividad, como el texto de un widget EditText o la posición de desplazamiento de un widget ListView.

Sin embargo, es posible que tu actividad tenga más información de estado adicional que desees guardar, como variables de miembro.

Para guardar información adicional sobre el estado de la instancia de tu actividad, debes anular **onSaveInstanceState()** y **agregar pares clave-valor** al objeto [**Bundle**](https://developer.android.com/reference/android/os/Bundle) que se guarda en caso de que tu actividad finalice de forma inesperada. Debes llamar a la implementación de superclase si deseas que la implementación predeterminada guarde el estado de la jerarquía de vistas.

A medida que comienza a detenerse tu actividad, el sistema llama al método **onSaveInstanceState(outState: Bundle?)** para que tu actividad pueda guardar la información del estado en un paquete de estado de instancia.

* Tiene costos de serialización y deserialización, puede consumir mucha memoria si los objetos que se serializan son demasiado complejos.

* No se llama a onSaveInstanceState() cuando el usuario cierra explícitamente la actividad (botón atrás, finish()).

####
    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.run {
            putInt(STATE_SCORE, currentScore)
            putInt(STATE_LEVEL, currentLevel)
        }
        
        super.onSaveInstanceState(outState)
    }
    
### Restablecer estado con *estado de la instancia* guardada
    
Cuando se vuelve a crear tu actividad tras haber finalizado, puedes recuperar la instancia del estado guardado desde el [**Bundle**](https://developer.android.com/reference/android/os/Bundle) que el sistema pasa a tu actividad. Los métodos de devolución de llamada **onCreate**() y **onRestoreInstanceState**() reciben el mismo Bundle que contiene la información del estado de la instancia.

Los datos guardados que el sistema utiliza para restaurar el estado previo se denominan estado de instancia y son un conjunto de **pares clave-valor** almacenados en un objeto [**Bundle**](https://developer.android.com/reference/android/os/Bundle).
Un objeto [**Bundle**](https://developer.android.com/reference/android/os/Bundle) no es apropiado para guardar más que una cantidad trivial de datos, debido a que requiere serialización en el subproceso principal y consume memoria del proceso del sistema.

Dado que se llama al método **onCreate()** tanto si el sistema crea una nueva instancia de tu actividad como si vuelve a crear una instancia previa, debes comprobar si el Bundle de estado es nulo antes de intentar leerlo. Si es nulo, el sistema creará una instancia nueva de la actividad en lugar de restablecer una previa que ya haya finalizado.

####
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                // Restaurar el valor de los miembros del estado guardado
                currentScore = getInt(STATE_SCORE)
                currentLevel = getInt(STATE_LEVEL)
            }
        } else {
            // Probablemente inicialice miembros con valores predeterminados para una nueva instancia
        }
        // ...
    }
    
En lugar de restaurar el estado durante onCreate(), puedes optar por implementar **onRestoreInstanceState()**, al que el sistema llama después del método onStart(). El sistema llama a onRestoreInstanceState() solo si hay un estado guardado para restablecer, por lo que no necesitas comprobar si Bundle es nulo.
    
####
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        // Llame siempre a la superclase para que pueda restaurar la jerarquía de vistas
        super.onRestoreInstanceState(savedInstanceState)
    
        // Restaurar miembros del estado de la instancia guardada
        savedInstanceState?.run {
            currentScore = getInt(STATE_SCORE)
            currentLevel = getInt(STATE_LEVEL)
        }
    }
    
`Nota: Para que el sistema Android restablezca el estado de las vistas de tu actividad, cada vista debe tener un ID único provisto por el atributo android:id.`




## Guardar y restablecer el estado usando ViewModel

Los ViewModels contienen datos transitorios utilizados en la interfaz de usuario, pero no conservan los datos. Una vez que se destruye el controlador de IU asociado (fragmento / actividad) o se detiene el proceso, ViewModel y todos los datos contenidos se marcan para la recolección de basura.

ViewModel es ideal para almacenar y administrar datos relacionados con la IU mientras el usuario usa la aplicación de manera activa.

Permite un acceso rápido a los datos de la IU y te ayuda a evitar la recuperación de datos de la red o el disco durante cambios de configuración.

ViewModel conserva los datos en la memoria, lo que significa que es más económico recuperarlos que recuperar los datos del disco o la red.

Un ViewModel está asociado con una actividad (o algún otro propietario del ciclo de vida): permanece en la memoria durante un cambio de configuración y el sistema asocia automáticamente el ViewModel con la nueva instancia de actividad que resulta del cambio de configuración.

El sistema destruye ViewModels de forma automática cuando el usuario cancela tu actividad o fragmento, o si llamas a finish(), lo que indica que se borrará el estado, como el usuario espera en estas situaciones.

A diferencia del estado de instancia guardado, los ViewModels se destruyen durante el cierre de un proceso iniciado por el sistema. Esta es la razón por la que debes usar los objetos ViewModel junto con onSaveInstanceState(), y reservar los identificadores en savedInstanceState para ayudar a que los modelos de vista vuelvan a cargar los datos después del cierre del sistema.

En cualquiera de estos casos, debes usar un ViewModel para evitar desperdiciar ciclos recargando datos de la base de datos durante un cambio de configuración.

### Guardar y restablecer el estado usando ViewModel & SavedState

Los objetos **ViewModel** pueden controlar los cambios de configuración para que no tengas que preocuparte por el estado durante las rotaciones y otros casos. Sin embargo, si necesitas administrar el cierre de un proceso iniciado por el sistema, puedes usar onSaveInstanceState() para copias de seguridad.

Por lo general, se almacena el estado de la IU en objetos ViewModel, no en actividades; por lo tanto, el uso de onSaveInstanceState() requiere algo de código estándar que este módulo ([**SavedState**](https://developer.android.com/topic/libraries/architecture/viewmodel-savedstate)) puede ayudarte a administrar.

Cuando el módulo está configurado, los objetos ViewModel reciben un objeto [**SavedStateHandle**](https://developer.android.com/reference/androidx/lifecycle/SavedStateHandle) a través de su constructor.

    androidx.lifecycle:lifecycle-viewmodel-savedstate:$savedStateVersion
####
    class SavedStateViewModel(private val mState: SavedStateHandle) : ViewModel() { /* ... */ }

Una vez que tenga la dependencia, siempre que esté usando un Fragmento o Actividad predeterminados, tendrá acceso a [**SavedStateHandle**](https://developer.android.com/reference/androidx/lifecycle/SavedStateHandle) en su ViewModel. <br/>

La clase [**SavedStateHandle**](https://developer.android.com/reference/androidx/lifecycle/SavedStateHandle) permite que las clases ViewModel accedan y contribuyan al estado guardado. Este objeto se puede recibir en el constructor de la clase ViewModel, y las fábricas proporcionadas de forma predeterminada por Fragments y AppCompatActivity introducirán SavedStateHandle automáticamente.

SavedStateHandle es un mapeo de valores clave que sobrevive a la muerte del proceso.

Con SavedStateHandler puede guardar y restaurar primitivas, Bundles, Parcelables, Serializables y otros tipos de datos.

[**AbstractSavedStateViewModelFactory**](https://developer.android.com/reference/androidx/lifecycle/AbstractSavedStateViewModelFactory) te permite crear fábricas personalizadas para la clase ViewModel y proporcionarles acceso a SavedStateHandle.

    private val viewModel: SavedStateViewModel by lazy {
        SavedStateViewModel.getViewModel(application, this)
    }
####  
    val defaultState = Bundle().apply { putInt(SCORE_KEY, 0) }
    val factory = SavedStateViewModelFactory(application, fragmentActivity, defaultState)
    viewModel = ViewModelProvider(fragmentActivity, factory).get(SavedStateViewModel::class.java)

En realidad, el módulo *lifecycle-viewmodel-savedstate* también usa onSaveInstanceState y onRestoreInstanceState para conservar el estado de ViewModel, pero hace que estas operaciones sean más convenientes.

## Guardar y restablecer el estado usando almacenamiento persistente

Almacena todos los datos que no quieras perder cuando abras y cierres la actividad.

Se conservará el almacenamiento local persistente, como una base de datos o preferencias compartidas, mientras tu aplicación esté instalada en el dispositivo del usuario (a menos que el usuario borre los datos de tu app).

Este almacenamiento local se conserva tras la actividad iniciada por el sistema y el cierre del proceso de la aplicación, puede ser costoso recuperarlo, ya que se tendrá que leer en la memoria.

A menudo, este almacenamiento local persistente puede ser parte de la arquitectura de tu aplicación, a fin de almacenar todos los datos que no deseas perder si abres y cierras la actividad.
Ni ViewModel ni el estado de instancia guardado son soluciones de almacenamiento a largo plazo.



## Combinar las diferentes opciones para preservar el estado de la IU

Puedes guardar y restablecer de manera eficaz el estado de la IU dividiendo el trabajo entre los diversos tipos de mecanismos de persistencia.

Cada uno de estos mecanismos debe almacenar un tipo diferente de datos utilizados en la actividad, en función de las compensaciones de la complejidad de los datos, la velocidad de acceso y el ciclo de vida:

* **Persistencia local**: Almacena todos los datos que no quieras perder cuando abras y cierres la actividad.
    * Ejemplo: una colección de canciones, que puede incluir archivos de audio y metadatos.
* **ViewModel**: almacena en la memoria todos los datos necesarios para mostrar el controlador de IU asociado.
    * Ejemplo: las canciones de la búsqueda más reciente y la consulta de búsqueda más reciente.
* **onSaveInstanceState()**: Almacena una pequeña cantidad de datos necesarios para volver a cargar fácilmente el estado de una actividad si se detiene el sistema y, luego, vuelve a crear el controlador de IU. En lugar de almacenar objetos complejos en este lugar, consérvalos en un almacenamiento local y almacena un ID único para esos objetos en onSaveInstanceState().
    * Ejemplo: almacenar la consulta de búsqueda más reciente


## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
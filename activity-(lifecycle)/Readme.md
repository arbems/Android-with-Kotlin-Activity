# Android con Kotlin - Activity - Lifecycle

Código de ejemplo de ciclo de vida de una actividad en Android con Kotlin.
               
La clase Activity proporciona una serie de devoluciones de llamada que permiten a la actividad saber que cambió un estado, es decir, que el sistema está creando, deteniendo o reanudando una actividad, o bien finalizando el proceso en el que se encuentra.

Hacer el trabajo preciso en el momento adecuado y administrar las transiciones correctamente hace que tu app sea más sólida y eficiente.

Por ejemplo, una buena implementación de las devoluciones de llamada de un ciclo de vida puede ayudar a garantizar que tu app:

* No falle si el usuario recibe una llamada telefónica o cambia a otra app mientras usa la tuya.
* No consuma recursos valiosos del sistema cuando el usuario no la use de forma activa.
* No pierda el progreso del usuario si este abandona tu app y regresa a ella posteriormente.
* No falle ni pierda el progreso del usuario cuando se gire la pantalla entre la orientación horizontal y la vertical.

## Devoluciones de llamada de los ciclos de vida de la actividad

![Lifecycle Activity](https://github.com/arbems/Android-with-Kotlin-Activity/blob/master/activity-(lifecycle)/0001.png)

### onCreate()

Se llama la primera vez que se inicia la actividad y, por lo tanto, solo se llama una vez durante el ciclo de vida de la actividad.

Aquí hay que inicializar los componentes esenciales de la actividad. Se utiliza para realizar todo tipo de inicializaciones, como la creación de la interfaz de usuario o la inicialización de estructuras de datos.

La app debería aquí vincular datos a listas, asociar la actividad con un ViewModel y crear instancias de algunas variables de alcance de clase.

### onStart()

Se activa cuando la actividad está a punto de hacerse visible. Se puede llamar varias veces a medida que el usuario se aleja de la actividad y luego regresa.

En este punto, la actividad no es interactiva, contiene los preparativos finales de la actividad para pasar al primer plano y convertirse en interactiva.

### onResume()

Se activa cuando la actividad tiene el foco, se encuentra en primer plano y el usuario puede interactuar con ella.

Aquí es donde los componentes del ciclo de vida pueden habilitar cualquier funcionalidad que necesite ejecutarse mientras el componente está visible y en primer plano, como, por ejemplo, iniciar una vista previa de la cámara.

### onRestart()

El sistema invoca esta devolución de llamada cuando una actividad en estado Stopped vuelve a iniciarse.
onRestart() restaura el estado de la actividad desde el momento en que esta se detuvo.

### onPause()

Este método se llama tan pronto como la actividad pierde el foco y el usuario no puede interactuar con ella. Una actividad puede perder el foco sin desaparecer por completo de la pantalla (por ejemplo, cuando aparece un cuadro de diálogo que oscurece parcialmente la actividad).

No debes usar onPause() para guardar datos de la aplicación o del usuario, realizar llamadas de red o ejecutar transacciones de base de datos.

Utiliza el método onPause() para pausar o ajustar las operaciones que no deben continuar.

También puedes utilizar el método onPause() para liberar recursos del sistema, controladores de sensores (como el GPS) o cualquier otro recurso que pueda afectar la duración de la batería mientras tu actividad esté en pausa y el usuario no los necesite.

### onStop()

El sistema llama a onStop() cuando la actividad ya no es visible para el usuario.

Esto ocurre porque se está eliminando la actividad, porque se inicia una nueva o porque una existente pasa al estado reanudada.

!Ojo sí hay muy poca memoria! es posible que la actividad se destruya sin llamar a este método.

En el método onStop(), la app debe liberar o ajustar los recursos que no son necesarios mientras no sea visible para el usuario. Por ejemplo, tú app podría pausar animaciones o cambiar de actualizaciones de ubicación detalladas a más generales.

Para guardar datos persistentes, como las preferencias del usuario o información de una base de datos, debes aprovechar las oportunidades apropiadas cuando tu actividad esté en primer plano. Si no se presenta tal oportunidad, debes guardar esos datos durante el método onStop().

### onDestroy()

Se llama una vez cuando la actividad se destruye por completo. Esto sucede cuando vuelve a salir de la actividad (como cuando presiona el botón Atrás) o llama manualmente a finish(). Es su última oportunidad para limpiar los recursos asociados con la actividad.

El sistema invoca a esta devolución de llamada porque:
* La actividad esta terminando (usuario la descarta o llama a finish())
* El sistema está finalizando temporalmente la actividad debido a un cambio de configuración (por ejemplo a rotar pantalla o modo multiventana).

La devolución de llamada onDestroy() debe liberar todos los recursos que aún no han sido liberados por devoluciones de llamada anteriores.


## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
# Android con Kotlin - Activity - Tareas y pilas de actividades

Una tarea es una agrupación de actividades con la que interactúan los usuarios cuando realizan una acción determinada.

Las actividades se organizan en una pila de actividades en el orden en que se abre cada actividad:

![Lifecycle Activity](https://github.com/arbems/Android-with-Kotlin-Activity/blob/master/activity-(tasks-and-stacks-of-activities)/0001.png)

`la pila de actividades funciona como una estructura de objetos "último en entrar, primero en salir".`

Al pulsar el botón atrás o llamar a finish() la actividad finaliza y se elimina de la pila.

Cuando el usuario toca un ícono en el selector de apps la tarea de esa app pasa a primer plano.

Si no existe ninguna tarea para la app (porque esta no se usó recientemente), entonces se crea una nueva tarea y la actividad "principal" de esa app se abre como la actividad raíz de la pila.

Cuando la actividad actual inicia otra, la nueva actividad se coloca en la parte superior de la pila y toma el foco. La actividad anterior permanece en la pila, pero se detiene. Cuando una actividad se detiene, el sistema retiene el estado actual de su interfaz de usuario. 

Cuando el usuario presiona el botón Atrás, se quita la actividad actual de la parte superior de la pila (se elimina la actividad) y se reanuda la actividad anterior (se restablece el estado anterior de su IU). Las actividades de la pila nunca se reordenan, solo se insertan o se quitan (se insertan en la pila cuando la actividad actual las inicia y se quitan cuando el usuario sale de ellas presionando el botón Atrás).

<br/>
Línea de tiempo que muestra el progreso entre actividades junto con la pila de actividades actual en cada punto en el tiempo:

![Lifecycle Activity](https://github.com/arbems/Android-with-Kotlin-Activity/blob/master/activity-(tasks-and-stacks-of-activities)/0002.png)

Una tarea es una unidad coherente que puede pasar a "segundo plano" cuando el usuario inicia una nueva tarea o cuando presiona el botón Inicio para mostrar la pantalla principal. En el segundo plano, se detienen todas las actividades, pero la pila de actividades de la tarea permanece intacta.

Luego, una tarea puede regresar al "primer plano" por lo que los usuarios pueden reanudar lo que estaban haciendo.

Se pueden mantener varias tareas en segundo plano a la vez. Sin embargo, si el usuario ejecuta muchas tareas en segundo plano a la vez, el sistema puede eliminar algunas actividades a fin de recuperar memoria, lo que provocará que se pierdan los estados de las actividades.

#### Instancias repetidas

Debido a que las actividades de la pila de actividades nunca se reordenan, si tu app permite a los usuarios iniciar una actividad en particular desde más de una actividad, se crea una nueva instancia de esa actividad y se inserta en la pila:

![Lifecycle Activity](https://github.com/arbems/Android-with-Kotlin-Activity/blob/master/activity-(tasks-and-stacks-of-activities)/0003.png)

## Administrar tareas





## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
# Android con Kotlin - Activity - Cambios de estado de la actividad

Código de ejemplo que muestra como administrar los cambios de estado de la actividad en Android con Kotlin.

Diferentes eventos, algunos activados por el usuario y otros activados por el sistema, pueden provocar que una Activity pase de un estado a otro.
      
## Cambio de configuración

Cuando se produce un cambio de configuración, la actividad se elimina y se vuelve a crear. 

Ejemplos de cambios de configuración: 
* Cambio entre orientación vertical y horizontal
* Modo multi-ventana
* Cambios de idioma
* Cambios de dispositivo de entrada

![Configuration change](https://raw.githubusercontent.com/arbems/Android-with-Kotlin-Activity/master/Cambios%20de%20estado%20de%20la%20actividad/0001.png)

`Usa una combinación de ViewModels, el método onSaveInstanceState() y el almacenamiento local persistente para conservar el estado de la IU de una actividad durante los cambios de configuración.`

## Nueva actividad o diálogo aparecen en primer plano

Si aparece en primer plano una nueva actividad o un nuevo diálogo que obtiene el foco y cubre parcialmente la actividad en progreso, la actividad cubierta pierde el foco y pasa al estado Detenida.

![Nueva actividad o diálogo aparecen en primer plano](https://raw.githubusercontent.com/arbems/Android-with-Kotlin-Activity/master//Cambios%20de%20estado%20de%20la%20actividad/0002.png)

`Cuando el usuario toca el botón de inicio o Recientes, el sistema se comporta como si la actividad actual hubiera perdido el foco.`

## El usuario toca botón Atrás

Si una actividad está en primer plano y el usuario toca el botón Atrás, esta se elimina y también se quita de la pila de actividades.

####
    onBackPressed()
    
    Puedes anular este método para implementar un comportamiento personalizado, por ejemplo, un diálogo de "confirmar-salir".
    
    Recomendamos que invoques super.onBackPressed() desde el método anulado. De lo contrario, el comportamiento del botón Atrás puede resultar incoherente para el usuario.

![El usuario toca botón Atrás](https://raw.githubusercontent.com/arbems/Android-with-Kotlin-Activity/master/Cambios%20de%20estado%20de%20la%20actividad/0003.png)

## El sistema elimina el proceso de la app

Si una app está en segundo plano y el sistema necesita liberar memoria adicional para una app en primer plano, el sistema puede eliminar la que está en segundo plano a fin de liberar más memoria.

![El sistema elimina el proceso de la app](https://raw.githubusercontent.com/arbems/Android-with-Kotlin-Activity/master/Cambios%20de%20estado%20de%20la%20actividad/0004.png)

Cuando un usuario inicia una aplicación por primera vez, se crea un proceso para ella; pero cuando el usuario abandona la aplicación, ese proceso no se cierra. El sistema mantiene el proceso en caché. Si el usuario vuelve más tarde a la aplicación, el sistema reutiliza el proceso, lo que hace que la aplicación cambie más rápido.

A medida que el sistema se queda sin memoria, mata los procesos en la caché comenzando con el proceso que se utilizó menos recientemente. Cuando el usuario regresa a la aplicación, el sistema reiniciará la aplicación en un nuevo proceso.

Dado que esto solo sucede si el usuario no ha interactuado con la aplicación durante un tiempo, es posible que esté permitido que regrese a la aplicación y la encuentre en el estado inicial. Sin embargo, hay casos en los que es posible que desee guardar el estado de la aplicación o parte de ella, para que esa información no se pierda si el proceso se termina.

### Expulsión de memoria

El sistema finaliza los procesos cuando necesita liberar RAM; la probabilidad de que el sistema finalice un proceso determinado dependerá del estado del proceso en ese momento.

<img src="https://raw.githubusercontent.com//arbems/Android-with-Kotlin-Activity/master/Ciclo%20de%20vida%20de%20la%20actividad/0003.png" width="700">

### Simular que el sistema mata el proceso

La mejor manera de probar esto es:

* Asegurarse de que el proceso se está ejecutando
* Presione Inicio en su dispositivo o emulador
* Después presione *Terminate Application* en la ventana de Logcat en Android Studio (esto matará el proceso de la aplicación, asegúrese de seleccionar su dispositivo y proceso en los menús desplegables de Logcat en la parte superior)
* Vuelva a la aplicación
* Selecciona la aplicación que se iniciará en Activity recreada

Normalmente, el sistema borra una tarea (elimina todas las actividades de la pila por encima de la actividad raíz) en determinadas situaciones cuando el usuario vuelve a seleccionar esa tarea desde la pantalla de inicio. Normalmente, esto se hace si el usuario no ha visitado la tarea durante un tiempo determinado, como 30 minutos.

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
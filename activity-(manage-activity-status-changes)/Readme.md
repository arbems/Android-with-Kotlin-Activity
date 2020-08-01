# Android con Kotlin - Activity - Administrar los cambios de estado de la actividad

Código de ejemplo que muestra como administrar los cambios de estado de la actividad en Android con Kotlin.

Diferentes eventos, algunos activados por el usuario y otros activados por el sistema, pueden provocar que una Activity pase de un estado a otro.
      
## Cambio de configuración

Cuando se produce un cambio de configuración, la actividad se elimina y se vuelve a crear.
Ejemplos de cambios de configuración: cambio entre las orientaciones vertical y horizontal, modo multiventana, cambios de idioma o de dispositivo de entrada.

![Lifecycle Activity](https://github.com/arbems/Android-with-Kotlin-Activity/blob/master/activity-(manage-activity-status-changes)/0001.png)

`Usa una combinación de ViewModels, el método onSaveInstanceState() y el almacenamiento local persistente para conservar el estado de la IU de una actividad durante los cambios de configuración.`

## Nueva actividad o diálogo aparecen en primer plano

Si aparece en primer plano una nueva actividad o un nuevo diálogo que obtiene el foco y cubre parcialmente la actividad en progreso, la actividad cubierta pierde el foco y pasa al estado Detenida.

![Lifecycle Activity](https://github.com/arbems/Android-with-Kotlin-Activity/blob/master/activity-(manage-activity-status-changes)/0002.png)

`Cuando el usuario toca el botón de inicio o Recientes, el sistema se comporta como si la actividad actual hubiera perdido el foco.`

## El usuario toca botón Atrás

Si una actividad está en primer plano y el usuario toca el botón Atrás, esta se elimina y también se quita de la pila de actividades.

####
    onBackPressed()
    
    Puedes anular este método para implementar un comportamiento personalizado, por ejemplo, un diálogo de "confirmar-salir".
    
    Recomendamos que invoques super.onBackPressed() desde el método anulado. De lo contrario, el comportamiento del botón Atrás puede resultar incoherente para el usuario.

![Lifecycle Activity](https://github.com/arbems/Android-with-Kotlin-Activity/blob/master/activity-(manage-activity-status-changes)/0003.png)

`Usa una combinación de ViewModels, el método onSaveInstanceState() y el almacenamiento local persistente para conservar el estado de la IU de una actividad durante los cambios de configuración.`

## El sistema elimina el proceso de la app

Si una app está en segundo plano y el sistema necesita liberar memoria adicional para una app en primer plano, el sistema puede eliminar la que está en segundo plano a fin de liberar más memoria.

![Lifecycle Activity](https://github.com/arbems/Android-with-Kotlin-Activity/blob/master/activity-(manage-activity-status-changes)/0004.png)

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
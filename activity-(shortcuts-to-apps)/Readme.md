# Android con Kotlin - Activity - Acceso directo a aplicaciones

Accesos directos para realizar acciones específicas en una aplicación, ayudando a los usuarios a iniciar rápidamente tareas comunes o recomendadas dentro de la aplicación.

## Crear y administrar accesos directos a aplicaciones

Cada acceso directo hace referencia a uno o más intents, cada uno de los cuales inicia una acción específica cuando los usuarios seleccionan el acceso directo.

`Nota: Solo las actividades principales (actividades que manejan la Intent.ACTION_MAIN acción y la Intent.CATEGORY_LAUNCHER categoría) pueden tener accesos directos. Si una aplicación tiene múltiples actividades principales, debe definir el conjunto de accesos directos para cada actividad.`

![Lifecycle Activity](https://github.com/arbems/Android-with-Kotlin-Activity/blob/master/activity-(shortcuts-to-apps)/0001.png)
`Usando los accesos directos de aplicaciones, puede mostrar acciones clave y llevar a los usuarios a su aplicación al instante`

### Tipos de accesos directos

* **Static shortcuts**, son los mejores para las aplicaciones que se vinculan al contenido utilizando una estructura consistente durante la vida útil de la interacción del usuario con la aplicación, se definen en un archivo de recursos que se empaqueta en un APK o paquete de aplicaciones.
    * Por ejemplo, si el usuario desea ver su calendario o correo electrónico de una manera específica, el uso de un acceso directo estático garantiza que su experiencia en la realización de una tarea de rutina sea coherente.
    
* Dynamic shortcuts, se usan para acciones en aplicaciones sensibles al contexto, pueden ser publicados, actualizados y eliminados por la aplicación solo en tiempo de ejecución.

* Pinned shortcuts, se utilizan para acciones específicas dirigidas por el usuario, se pueden agregar en tiempo de ejecución si el usuario otorga permiso.
    * Por ejemplo, acceso directo a cualquier sitio web fácilmente desde la pantalla de inicio del móvil, sin necesidad de abrir el navegador y escribir la dirección web (o rebuscar en los marcadores).

`Nota: Los usuarios también pueden crear pinned shortcuts copiando los accesos directos estáticos y dinámicos de su aplicación en el iniciador.`

### Limitaciones de acceso directo

Aunque puedes publicar hasta cinco accesos directos (accesos directos estáticos y dinámicos combinados) a la vez para su aplicación, la mayoría de los lanzadores solo pueden mostrar cuatro.

Sin embargo, no hay límite para la cantidad de Pinned shortcuts a su aplicación que los usuarios pueden crear. Aunque su aplicación no puede eliminar los accesos directos anclados, aún puede deshabilitarlos.

`Nota: Aunque otras aplicaciones no pueden acceder a los metadatos dentro de sus accesos directos, el iniciador en sí puede acceder a estos datos. Por lo tanto, estos metadatos deben ocultar información confidencial del usuario.`

### Crear accesos directos

Los accesos directos entregan tipos específicos de contenido a sus usuarios al ayudarlos a acceder rápidamente a partes de su aplicación.



## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
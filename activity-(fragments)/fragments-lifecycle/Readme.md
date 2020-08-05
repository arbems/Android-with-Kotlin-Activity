# Android con Kotlin - Fragments - Ciclo de vida de un fragmento

Este código contiene ejemplos del ciclo de vida de un Fragment en Android con Kotlin.

La clase Fragment tiene un código que se asemeja bastante a una Activity. Contiene métodos de devolución de llamada similares a los de una actividad.

* **onCreated**() - Llamado para hacer la creación inicial de un fragmento. Esto se llama después de onAttach() y antes de onCreateView().
Tenga en cuenta que esto se puede llamar mientras la actividad del fragmento aún está en proceso de creación.

* **onStart**() - Llamado justo antes de que el fragmento sea visible para el usuario.

* **onResume**() - Se llama cuando el fragmento es visible para el usuario y se ejecuta activamente. Esto generalmente está vinculado a Activity.onResume del ciclo de vida de la Actividad que lo contiene.

* **onPause**() - Llamado cuando el Fragmento ya no esta en resumed. Esto generalmente está vinculado a Activity.onPause del ciclo de vida de la Actividad que lo contiene.

* **onStop**() - Se llama cuando el Fragmento ya no esta started. Esto generalmente está vinculado a Activity.onStop del ciclo de vida de la Actividad que lo contiene.

* **onDestroy**() - Se llama cuando el fragmento ya no está en uso. Esto se llama después de onStop() y antes de onDetach().

Los fragmentos tienen algunas devoluciones de llamada del ciclo de vida adicionales.

Estas abordan la interacción única con la actividad para poder realizar acciones como crear y destruir la IU del fragmento. Estos métodos de devolución de llamada adicionales son los siguientes:

* **onAttach**() - Se llama cuando se asocia el fragmento con la actividad. Se llamará a onCreate() después de esto.

* **onCreateView**() - Llamado para que el fragmento instanciara su vista de interfaz de usuario. Esto es opcional, y los fragmentos no gráficos pueden devolver nulo. Esto se llamará después de onCreate().

* **onViewCreated()** - Se llama inmediatamente después de que onCreateView() haya regresado, pero antes de que cualquier estado guardado se haya restaurado en la vista. Esto les da a las subclases la oportunidad de inicializarse una vez que saben que su jerarquía de vistas se ha creado por completo.

* **onDestroyView**() - Se invoca cuando la vista creada previamente por onCreateView se ha separado del fragmento. La próxima vez que deba mostrarse el fragmento, se creará una nueva vista. Esto se llama después de onStop() y antes de onDestroy().

* **onDetach**() - Se llama cuando el fragmento ya no está unido a su actividad. Esto se llama después de onDestroy().

`Las aplicaciones deben implementar al menos tres métodos para cada fragmento (onCreate, onCreateView y onPause).`

![Lifecycle Fragments](https://github.com/arbems/Android-with-Kotlin-Activity/blob/master/activity-(fragments)/0001.png)
   
#### Estado de los fragments

Efecto del ciclo de vida de la actividad en el ciclo de vida del fragmento:

![Lifecycle Fragments](https://github.com/arbems/Android-with-Kotlin-Activity/blob/master/activity-(fragments)/0002.png)

**Resumed** (Reanudado), el fragmento está visible en la actividad que se está ejecutando.

**Paused** (Pausado), otra actividad se encuentra en primer plano y tiene el foco, pero la actividad en la que reside este fragmento aún está visible (la actividad en segundo plano es parcialmente transparente o no cubre toda la pantalla).

**Stopped** (Detenido), el fragmento no es visible. O bien se detuvo la actividad anfitriona, o bien se quitó el fragmento de la actividad, pero se agregó a la pila de actividades. Un fragmento detenido aún está activo (el sistema conserva el estado y la información de miembro). No obstante, ya no está visible para el usuario y se cerrará si finaliza la actividad.

#### La diferencia más importante en el ciclo de vida entre una actividad y un fragmento

Es cómo cada uno se almacena en su pila de actividades respectiva. Cuando se detiene una actividad, de forma predeterminada, se dispone en una pila de actividades administrada por el sistema (de modo que el usuario pueda navegar hasta ella con el botón Atrás). Sin embargo, un fragmento se dispone en una pila de retroceso administrada por la actividad anfitriona solo cuando solicitas explícitamente que se guarde la instancia mediante la llamada a addToBackStack() durante una transacción que elimina el fragmento.
   

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
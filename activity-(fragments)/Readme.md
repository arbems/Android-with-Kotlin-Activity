# Android con Kotlin - Activity - Fragments

Código de ejemplo de fragments en Android con Kotlin.

Un [Fragment](https://developer.android.com/reference/androidx/fragment/app/Fragment?hl=es-419) representa un comportamiento o una parte de la interfaz de usuario en una [FragmentActivity](https://developer.android.com/reference/androidx/fragment/app/FragmentActivity?hl=es-419).

Una actividad puede tener multiples fragmentos y un fragmento se puede incluir en diferentes actividades.

Un fragmento es como una sección modular de una actividad que tiene un ciclo de vida propio, que recibe sus propios eventos de entrada y que puedes agregar o quitar mientras la actividad se esté ejecutando.

El ciclo de vida de un fragmento esta afectado por el ciclo de vida de su actividad, cuando realizas un transacción de fragmentos puedes agregarlo a la pila de actividades, esta pila de actividades le permite al usuario invertir una transacción de fragmentos (navegar hacia atrás) al presionar el botón Atrás.

## Crear un fragmento

Para crear un fragmento, debes crear una subclase de Fragment. Existen también algunas subclases que quizá desees extender, en lugar de la clase de base Fragment: 

* **DialogFragment** - Muestra un diálogo flotante. Usar esta clase para crear un diálogo es una buena alternativa al uso de métodos del asistente de diálogos en la clase Activity, ya que puedes incorporar un diálogo del fragmento en la pila de actividades de fragmentos administrados por la actividad, lo que le permite al usuario volver a un fragmento descartado.

* **ListFragment** - Muestra una lista de elementos administrados por un adaptador, al igual que ListActivity. Proporciona varios métodos para administrar una vista de lista, como la devolución de llamada onListItemClick() para manipular eventos de clic. (Ten en cuenta que el método preferido para mostrar una lista es utilizar **RecyclerView** en lugar de ListView. En este caso, necesitarías crear un fragmento que incluya un RecyclerView en su diseño.

* **PreferenceFragmentCompat** - Muestra una jerarquía de objetos Preference en forma de lista. Este objeto se usa a fin de crear una pantalla de configuración para tu app.

Puedes insertar un fragmento en el diseño de la actividad declarando el fragmento en el archivo de diseño, como elemento, o desde el código de tu aplicación agregándolo a un archivo existente ViewGroup.

### Agregar una interfaz de usuario

### Agregar fragmento a actividad

## Administrar fragmentos

## Realizar transacciones de fragmentos

## Comunicarse con la actividad

## Controlar el ciclo de vida de un fragmento

## Ciclo de vida de un fragmento

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
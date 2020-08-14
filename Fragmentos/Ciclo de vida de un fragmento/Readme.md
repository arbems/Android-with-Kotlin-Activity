# Android con Kotlin - Fragments - Ciclo de vida de un fragmento

Código de ejemplo del ciclo de vida de un Fragmento en Android con Kotlin.

La clase Fragment tiene un código que se asemeja bastante a una Activity. Contiene métodos de devolución de llamada similares a los de una actividad.

<img src="https://raw.githubusercontent.com/arbems/Android-with-Kotlin-Activity/master/Fragmentos/0001.png" width="1100">

## Devolución de llamadas del ciclo de vida de un fragmento

El ciclo de vida de la actividad en la que reside el fragmento afecta directamente al ciclo de vida del fragmento, de modo que cada devolución de llamada del ciclo de vida de la actividad genera una devolución de llamada similar para cada fragmento.

Son similares a los de una actividad:

#### onCreated()
Se llama para hacer la creación inicial de un fragmento.<br/> 
`Tenga en cuenta que esto se puede llamar mientras la actividad del fragmento aún está en proceso de creación.`

#### onStart()
Se llama cuando el Fragmento es visible para el usuario. Esto generalmente está vinculado a Activity onStart() del ciclo de vida de la Actividad que lo contiene.<br/>

#### onResume()
Se llama cuando el fragmento es visible para el usuario y se ejecuta activamente. Esto generalmente está vinculado a Activity onResume() del ciclo de vida de la Actividad que lo contiene.

#### onPause()
Se llama cuando el Fragmento ya no está en resumed. Esto generalmente está vinculado a Activity onPause() del ciclo de vida de la Actividad que lo contiene.

#### onStop()
Se llama cuando el Fragmento ya no esta started. Esto generalmente está vinculado a Activity onStop() del ciclo de vida de la Actividad que lo contiene.

#### onDestroy()
Se llama cuando el fragmento ya no está en uso.<br/>


### Devolución de llamada adicionales de un fragmento:

Los fragmentos tienen algunas devoluciones de llamada del ciclo de vida adicionales. Estas abordan la interacción única con la actividad para poder realizar acciones como crear y destruir la IU del fragmento. 

Estos métodos de devolución de llamada adicionales son los siguientes:

#### onAttach()
Se llama cuando un fragmento se adjunta por primera vez a su contexto.<br/> 

#### onCreateView()
Llamado para que el fragmento instanciara su vista de interfaz de usuario.<br/>
Esto es opcional, y los fragmentos no gráficos pueden devolver nulo.<br/>
Se recomienda inflar solo el diseño en este método y mover la lógica que opera en la Vista devuelta a onViewCreated()<br/>

#### onViewCreated()
Se llama inmediatamente después de que onCreateView(), pero antes de que cualquier estado guardado se haya restaurado en la vista.<br/> 
Esto les da a las subclases la oportunidad de inicializarse una vez que saben que su jerarquía de vistas se ha creado por completo. Sin embargo, la jerarquía de vista del fragmento no está asociada a su padre en este punto.

#### onActivityCreated()
Llamado cuando la actividad del fragmento ha sido creada y jerarquía de vista del fragmento instanciada.<br/>
Se puede usar para hacer la inicialización final una vez que estas piezas están en su lugar, como recuperar vistas o restaurar el estado.<br/>

#### onViewStateRestored()
Se invoca cuando todo el estado guardado se ha restaurado en la jerarquía de vista del fragmento.<br/>
Esto se puede usar para realizar la inicialización en función del estado guardado en el que está permitiendo que la jerarquía de la vista se rastree por sí misma.<br/>

#### onDestroyView()
Se invoca cuando la vista creada previamente por onCreateView() se ha separado del fragmento. La próxima vez que deba mostrarse el fragmento, se creará una nueva vista. <br/>

#### onDetach()
Se llama cuando el fragmento ya no está unido a su actividad.

`Las aplicaciones deben implementar al menos tres métodos para cada fragmento (onCreate, onCreateView y onPause).`
   
## Estado de los fragmentos

Efecto del ciclo de vida de la actividad en el ciclo de vida del fragmento:

<img src="https://raw.githubusercontent.com/arbems/Android-with-Kotlin-Activity/master/Fragmentos//0002.png" width="620"><br/>

**Resumed** (Reanudado), el fragmento está visible en la actividad que se está ejecutando.

**Paused** (Pausado), otra actividad se encuentra en primer plano y tiene el foco, pero la actividad en la que reside este fragmento aún está visible (la actividad en segundo plano es parcialmente transparente o no cubre toda la pantalla).

**Stopped** (Detenido), el fragmento no es visible. O bien se detuvo la actividad anfitriona, o bien se quitó el fragmento de la actividad, pero se agregó a la pila de actividades. Un fragmento detenido aún está activo (el sistema conserva el estado y la información de miembro). No obstante, ya no está visible para el usuario y se cerrará si finaliza la actividad.

#### La diferencia más importante en el ciclo de vida entre una actividad y un fragmento

Es cómo cada uno se almacena en su pila de actividades respectiva. Cuando se detiene una actividad, de forma predeterminada, se dispone en una pila de actividades administrada por el sistema (de modo que el usuario pueda navegar hasta ella con el botón Atrás). Sin embargo, un fragmento se dispone en una pila de retroceso administrada por la actividad anfitriona solo cuando solicitas explícitamente que se guarde la instancia mediante la llamada a addToBackStack() durante una transacción que elimina el fragmento.
   
## Enlaces

[Ciclo de vida de una actividad](https://github.com/arbems/Android-with-Kotlin-Activity/tree/master/Ciclo%20de%20vida%20de%20la%20actividad)

[Cambios de estado de la actividad](https://github.com/arbems/Android-with-Kotlin-Activity/tree/master/Cambios%20de%20estado%20de%20la%20actividad)

[Gestionar estado de la IU](https://github.com/arbems/Android-with-Kotlin-Activity/tree/master/Gestionar%20estado%20de%20la%20IU)

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
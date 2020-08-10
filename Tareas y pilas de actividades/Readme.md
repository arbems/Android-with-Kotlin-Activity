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

Tal vez quieras que una actividad de tu app comience una nueva tarea cuando se inicie (en lugar de colocarse dentro de la tarea actual); o, cuando inicies una actividad, quizá quieras mover una de sus instancias existentes un nivel adelante (en lugar de crear una nueva instancia en la parte superior de la pila de actividades); o tal vez quieras quitar todas las actividades de la pila, salvo la actividad principal, cuando el usuario salga de la tarea.

Puedes realizar estas y otras acciones, con atributos en el elemento del manifiesto y con marcas en el intent que quieras pasar a startActivity().

`Precaución: La mayoría de las apps no deben interrumpir el comportamiento predeterminado de las actividades y tareas. Si determinas que es necesario modificar los comportamientos predeterminados de tu actividad, hazlo con cuidado y asegúrate de probar la usabilidad de esta durante el inicio y cuando regreses a ella desde otras actividades y tareas con el botón Atrás. Asegúrate de probar los comportamientos de navegación que puedan entrar en conflicto con el comportamiento que espera el usuario.`

### Mediante el archivo de manifiesto

Cuando declaras una actividad en tu archivo de manifiesto, puedes especificar cómo la actividad debe asociarse con las tareas cuando se inicia.

#### launchMode

Especifica una instrucción sobre cómo la actividad debe iniciarse dentro de una tarea. Valores:

* standard (predeterminada)
El sistema crea una nueva instancia de la actividad en la tarea desde la que se inició.
Se pueden crear instancias de la actividad varias veces, cada instancia puede pertenecer a diferentes tareas y una tarea puede tener varias instancias.

* singleTop
Si una instancia de una actividad ya existe en la parte superior de la tarea actual, el sistema dirige el intent a esa instancia mediante un llamado a su método onNewIntent(), en lugar de crear una nueva instancia de la actividad.
Si se llama a una actividad y no se encuentra en la parte superior de la pila no se puede dirigir a esta instancia y se duplicaría la actividad.

* singleTask
El sistema crea una nueva tarea y también instancias de la actividad en la raíz de la nueva tarea. Sin embargo, si ya existe una instancia de la actividad en una tarea diferente, el sistema dirige el intent a la instancia existente por medio de un llamado a su método onNewIntent(), en lugar de crear una nueva. Solo puede existir una instancia de cada actividad por vez.

* singleInstance
Es igual que "singleTask", salvo que el sistema no inicia otras actividades en la tarea que contiene la instancia. La actividad siempre es el único componente de su tarea; cualquier actividad que este inicie se abrirá en una tarea independiente.

`Nota: Los comportamientos que especificas para tu actividad con el atributo launchMode se pueden anular con las marcas que se incluyen en el intent que inicia tu actividad.`

Otros atributos mediante el archivo de manifiesto:

* taskAffinity -> Define la tarea con la cual la actividad tendrá afinidad.
* allowTaskReparenting -> Define si la actividad puede pasar de la tarea que la inició a la tarea con la que tiene afinidad cuando dicha tarea se traiga a primer plano la próxima vez.
* clearTaskOnLaunch -> Define si todas las actividades se quitarán de la tarea, salvo la actividad raíz, cuando se vuelva a iniciar desde la pantalla de inicio.
* alwaysRetainTaskState -> Define si el sistema siempre conservará el estado de la tarea donde se encuentra la actividad.
* finishOnTaskLaunch -> Define si se debe cerrar (finalizar) una instancia existente de la actividad cuando el usuario vuelve a iniciar la tarea.

### Mediante Intents

Cuando llamas a startActivity(), puedes incluir una marca en el Intent que declara cómo la nueva actividad debería asociarse con la tarea actual (o si debe hacerlo o no).

* FLAG_ACTIVITY_NEW_TASK<br/>
Comportamiento como "singleTask" launchMode 
Inicia la actividad en una nueva tarea. Si una tarea de la actividad que quieres iniciar ya está en ejecución, esta pasa al primer plano con el último estado restablecido y la actividad recibe un nuevo intent en onNewIntent().

* FLAG_ACTIVITY_SINGLE_TOP<br/>
Comportamiento como "singleTop" launchMode 
Si la actividad que se inicia es la actividad actual (en la parte superior de la pila de actividades), la instancia existente recibe una llamada a onNewIntent(), en lugar de crear una nueva instancia de la actividad.

* FLAG_ACTIVITY_CLEAR_TOP<br/>
Si la actividad que se inicia ya está en ejecución en la tarea actual, entonces, en lugar de iniciar una nueva instancia de esa actividad, se eliminan todas las demás actividades encima de esta, y este intent se envía a la instancia reanudada de la actividad (ahora en la parte superior) por medio de onNewIntent()).

`FLAG_ACTIVITY_CLEAR_TOP a menudo se usa junto con FLAG_ACTIVITY_NEW_TASK.
Cuando se usan juntas, estas marcas son una manera de localizar una actividad existente en otra tarea y colocarla en una posición en la que pueda responder al intent.`

En este contexto, si la actividad A inicia la actividad B, la actividad B puede definir en su manifiesto cómo debería asociarse con la tarea actual (o si debe hacerlo o no) y la actividad A también puede solicitar la manera en que la actividad B debería asociarse con la tarea actual. Si ambas actividades definen cómo la actividad B debería asociarse con una tarea, entonces se prioriza la solicitud de la actividad A (como se define en el intent) por sobre la de la actividad B (como se define en su manifiesto).

## Administrar afinidad de una actividad

La afinidad indica a qué tarea prefiere pertenecer una actividad. Todas las actividades de la misma app prefieren pertenecer a la misma tarea.
Sin embargo, puedes modificar la afinidad predeterminada de una actividad.

De forma predeterminada, se inicia una nueva actividad en la tarea de la actividad que llamó a startActivity().

#### taskAffinity

Con este atributo del elemento `<activity>`, puedes modificar la afinidad de una actividad.

El atributo taskAffinity toma un valor de string, que debe ser único, del nombre de paquete predeterminado declarado en el elemento `<manifest>`, porque el sistema lo usa para identificar la afinidad de tarea predeterminada de la app.

La afinidad entra en juego en dos circunstancias:

* Cuando el intent que inicia una actividad contiene la marca FLAG_ACTIVITY_NEW_TASK.
El sistema busca una tarea diferente en donde alojar la nueva actividad. Por lo general, es una tarea nueva. Sin embargo, no es necesario que lo sea. Si ya hay una tarea existente con la misma afinidad que la nueva actividad, la actividad se iniciará en ella. De lo contrario, se inicia una nueva tarea.
Por ejemplo el administrador de notificaciones incluyen FLAG_ACTIVITY_NEW_TASK en los intents que pasan a startActivity().

* Cuando una actividad tiene él atributo allowTaskReparenting establecido en "true".
En este caso, la actividad puede moverse desde la tarea que inicia hasta aquella por la que tiene afinidad cuando esa tarea pasa al primer plano.

## El sistema borra la pila de actividades

Si el usuario sale de una tarea por un tiempo prolongado, el sistema borra la tarea de todas las actividades, salvo la actividad raíz. Cuando el usuario vuelve a la tarea, solo se restablece la actividad raíz. El sistema se comporta de esta manera porque, luego de un tiempo prolongado, es probable que los usuarios hayan abandonado lo que estaban haciendo antes y vuelvan a la tarea para realizar una nueva acción.

Existen algunos atributos de actividades que puedes usar para modificar este comportamiento:

* alwaysRetainTaskState -> Con este atributo a true no se aplica el comportamiento predeterminado que se describió anteriormente. La tarea retiene todas las actividades en su pila, incluso luego de un período prolongado.

* clearTaskOnLaunch -> Con este atributo a true se borrará toda la pila, salvo la actividad raíz, cada vez que el usuario salga de la tarea y vuelva a ella.
En otras palabras, es lo opuesto a alwaysRetainTaskState. El usuario siempre vuelve a la tarea en su estado inicial, incluso si sale de ella solo un momento.

* finishOnTaskLaunch -> Es como clearTaskOnLaunch pero opera en una sola actividad, no en toda una tarea. También puede hacer que se borre una actividad, incluida la actividad raíz.
Cuando se configura en "true", la actividad permanece como parte de la tarea solo durante la sesión actual. Si el usuario sale y vuelve a ingresar, ya no estará presente.

## Cómo iniciar una tarea

Puedes configurar una actividad como el punto de entrada de una tarea al incluir un filtro de intent con "android.intent.action.MAIN" como acción especificada y 
"android.intent.category.LAUNCHER" como categoría especificada.

        <activity ... >
            <intent-filter ... >
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
            ...
        </activity>

Un filtro de intent de este tipo hace que se muestre un ícono y una etiqueta de la actividad en el selector de apps, lo que brinda al usuario una manera de iniciar la actividad y de volver a la tarea que esta crea cuando lo desee luego de que se haya iniciado.

Esta segunda capacidad es importante: los usuarios deben poder salir de una tarea y volver a ella más adelante por medio de este selector de actividades. Por este motivo, los dos modos de inicio que marcan actividades como que siempre inician tareas, "singleTask" y "singleInstance", solo deberían usarse cuando la actividad tiene una ACTION_MAIN y un filtro CATEGORY_LAUNCHER. Por ejemplo, imagina qué pasaría si faltara el filtro: un intent inicia una actividad "singleTask" y, luego, inicia una nueva tarea y el usuario pasa algo de tiempo en esta tarea. Luego, presiona el botón Inicio. La tarea ahora se envía al segundo plano y deja de estar visible. Luego, el usuario no tiene forma de volver a ella porque no se visualiza en el selector de apps.

#### finishOnTaskLaunch

En los casos en los que no quieres que el usuario pueda volver a una actividad, configura el finishOnTaskLaunch del elemento <activity> en "true".

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
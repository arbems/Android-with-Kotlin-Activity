# Android con Kotlin - Activity - Fragments

Este proyecto contiene ejemplos de fragments en Android con Kotlin.

- [Crear un fragmento](#Crear-un-fragmento)
    - Crear clase Fragment
    - Proporcionar un diseño definido en XML
    - Añadir fragmento a una actividad (Declarando fragmento en XML o Guardando el fragmento de forma programática)
- [Administrar fragmentos](#Administrar-fragmentos)
- [Crear una IU flexible](#Crear-una-IU-flexible)
    - Realizar transacciones de fragmentos: add(), replace(), remove(), commit(), addToBackStack()
- [Comunicación entre fragmento y actividad](#Comunicación-entre-fragmento-y-actividad)
- [Comunicación entre fragmentos](#Comunicación-entre-fragmentos-dentro-de-una-actividad)
- [Ciclo de vida de un fragmento](#Ciclo-de-vida-de-un-fragmento)
- [Testing Fragments](https://github.com/arbems/Android-with-Kotlin-Activity/tree/master/activity-(fragments)/testing)


Los fragmentos sirven como contenedores reutilizables dentro de tu app, lo que te permite presentar el mismo diseño de interfaz de usuario en una variedad de actividades y configuraciones de diseño.

Un fragmento es como una sección modular de una actividad que tiene un ciclo de vida propio, que recibe sus propios eventos de entrada y que puedes agregar o quitar mientras la actividad se esté ejecutando.

El ciclo de vida de un fragmento esta afectado por el ciclo de vida de su actividad, cuando realizas un transacción de fragmentos puedes agregarlo a la pila de actividades, esta pila de actividades le permite al usuario invertir una transacción de fragmentos (navegar hacia atrás) al presionar el botón Atrás.

Un [Fragment](https://developer.android.com/reference/androidx/fragment/app/Fragment?hl=es-419) representa un comportamiento o una parte de la interfaz de usuario en una [FragmentActivity](https://developer.android.com/reference/androidx/fragment/app/FragmentActivity?hl=es-419).

## Crear un fragmento

Para crear un fragmento, debes crear una subclase de [**Fragment**](https://developer.android.com/reference/androidx/fragment/app/Fragment?hl=es-419). Existen también algunas subclases que quizá desees extender, en lugar de la clase de base Fragment: 

* [**DialogFragment**](https://developer.android.com/reference/androidx/fragment/app/DialogFragment?hl=es-419) - Muestra un diálogo flotante. Usar esta clase para crear un diálogo es una buena alternativa al uso de métodos del asistente de diálogos en la clase Activity, ya que puedes incorporar un diálogo del fragmento en la pila de actividades de fragmentos administrados por la actividad, lo que le permite al usuario volver a un fragmento descartado.

* [**ListFragment**](https://developer.android.com/reference/androidx/fragment/app/ListFragment?hl=es-419) - Muestra una lista de elementos administrados por un adaptador, al igual que ListActivity. Proporciona varios métodos para administrar una vista de lista, como la devolución de llamada onListItemClick() para manipular eventos de clic. (Ten en cuenta que el método preferido para mostrar una lista es utilizar **RecyclerView** en lugar de ListView. En este caso, necesitarías crear un fragmento que incluya un RecyclerView en su diseño.

* [**PreferenceFragmentCompat**](https://developer.android.com/reference/androidx/preference/PreferenceFragmentCompat?hl=en) - Muestra una jerarquía de objetos Preference en forma de lista. Este objeto se usa a fin de crear una pantalla de configuración para tu app.

Puedes insertar un fragmento en el diseño de la actividad declarando el fragmento en el archivo de diseño, como elemento, o desde el código de tu aplicación agregándolo a un archivo existente ViewGroup.

#### Crear clase Fragment

Un fragmento generalmente se usa como parte de la interfaz de usuario de una actividad y le aporta su propio diseño.

Implementa la devolución de llamada onCreateView() que es la única que se necesita para ejecutar un fragmento:

    class ExampleFragment : Fragment() {
    
        override fun onCreateView(
                inflater: LayoutInflater,
                container: ViewGroup?,
                savedInstanceState: Bundle?
        ): View {
            return inflater.inflate(R.layout.example_fragment, container, false)
        }
    }

#### Proporcionar un diseño definido en XML
Proporciona un diseño desde un recurso de diseño definido en XML:
    
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.example_fragment, container, false)
    

`Nota: Si tu fragmento es una subclase de ListFragment, la implementación predeterminada muestra un ListView desde onCreateView(), de modo que no necesitas implementarla.`

El método onCreateView():
* **inflater** es el objeto LayoutInflater que se puede usar para inflar cualquier vista en el fragmento.
* **container** es el ViewGroup principal (del diseño de la actividad) en el cual se insertara el diseño del fragmento.
* **savedInstanceState** es un bundle que proporciona datos acerca de la instancia previa del fragmento si el fragmento se está reanudando.

El método inflate():
* **ID recurso** que quieres inflar
* **ViewGroup** 
* **Boolean** que indica si se debe anexar el diseño aumentado al ViewGroup (en este caso, es falso porque el sistema ya está insertando el diseño aumentado al container; al pasar "true", se crearía un grupo de vistas redundante en el diseño final)

#### Añadir fragmento a una actividad

**1. Declarando el fragmento en el archivo de diseño de la actividad.**

Agregando un fragmento al diseño de una actividad mediante la definición del fragmento en el archivo XML de diseño.

Cuando el sistema crea el diseño de esta actividad, crea una instancia para cada fragmento especificado en el diseño, además de llamar al método onCreateView(), con el objetivo de recuperar el diseño de cada fragmento. El sistema inserta el objeto View que muestra el fragmento directamente en lugar del elemento `<fragment>`.
    
        <?xml version="1.0" encoding="utf-8"?>
        <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
            android:orientation="horizontal"
            android:layout_width="match_parent"
            android:layout_height="match_parent">
            <fragment android:name="com.example.news.ArticleListFragment"
                    android:id="@+id/list"
                    android:layout_weight="1"
                    android:layout_width="0dp"
                    android:layout_height="match_parent" />
            <fragment android:name="com.example.news.ArticleReaderFragment"
                    android:id="@+id/viewer"
                    android:layout_weight="2"
                    android:layout_width="0dp"
                    android:layout_height="match_parent" />
        </LinearLayout>

`Nota: Cuando agregas un fragmento al diseño de una actividad mediante la definición del fragmento en el archivo XML de diseño, no puedes quitar el fragmento en el tiempo de ejecución. Si planeas intercambiar fragmentos durante la interacción del usuario, debes agregar el fragmento a la actividad cuando esta recién se inicia, como se muestra en Cómo crear una IU flexible.`

**2. Guardando el fragmento de forma programática en un ViewGroup existente.**

Usando transacciones, agregando fragmentos al diseño de manera dinámica usando la API de FragmentTransaction, mientras la actividad se está ejecutando. Solo hay que especificar un ViewGroup para colocar el fragmento.

## Administrar fragmentos

Para administrar los fragmentos de tu actividad, debes usar [**FragmentManager**](https://developer.android.com/reference/androidx/fragment/app/FragmentManager?hl=es-419). <br/>
Para obtenerlo, llama a **getSupportFragmentManager**() desde tu actividad:

    val fragmentManager = supportFragmentManager

Con **FragmentManager** puedes:

* Abrir un FragmentTransaction, que te permite realizar transacciones como agregar y quitar fragmentos.
* Obtener fragmentos que ya existen en la actividad con **findFragmentById**() (para fragmentos que proporcionan una IU en el diseño de la actividad) o **findFragmentByTag**() (para fragmentos con o sin IU)
* Activar fragmentos de la pila de retroceso con **popBackStack**()
* Registrar un receptor para cambios realizados en la pila de retroceso con **addOnBackStackChangedListener**()

## Crear una IU flexible

Puedes reutilizar tus fragmentos en diferentes configuraciones de diseño a fin de optimizar la experiencia del usuario en función del espacio de pantalla disponible.

![Lifecycle Fragments](https://github.com/arbems/Android-with-Kotlin-Activity/blob/master/activity-(fragments)/0003.png)
`Se muestran dos fragmentos en distintas configuraciones para la misma actividad en diferentes tamaños de pantalla. En una pantalla grande, ambos fragmentos pueden ubicarse uno junto al otro. En cambio, en un móvil, solo puede mostrarse un fragmento a la vez, de modo que uno reemplaza al otro a medida que el usuario navega.`

Para esto usamos la clase FragmentManager que permite agregar, quitar y reemplazar fragmentos en una actividad durante el tiempo de ejecución a fin de crear una experiencia dinámica.

Si tu actividad permite quitar y reemplazar fragmentos, debes agregar los fragmentos iniciales a la actividad durante su método onCreate() y el diseño de tu actividad debe incluir un contenedor View en el que puedas insertar el fragmento.

### Realizar transacciones de fragmentos

Los fragmentos tienen la capacidad de poderse agregar, quitar, reemplazar y realizar otras acciones en respuesta a la interacción del usuario. Cada conjunto de cambios que confirmas en la actividad recibe la denominación de transacción. Usando la API de [**FragmentTransaction**](https://developer.android.com/reference/androidx/fragment/app/FragmentTransaction?hl=es-419).
También puedes guardar cada transacción en la pila de actividades administrada por la actividad, lo que le permitirá al usuario navegar hacia atrás por los cambios realizados en el fragmento.

Puedes adquirir una instancia de FragmentTransaction de FragmentManager llamando a **beginTransaction**():

    val fragmentTransaction = fragmentManager.beginTransaction()
    
#### add()
    
Puedes agregar un fragmento usando el método add() y especificando el fragmento que se agregará, así como la vista en la que se insertará:

    val fragment = ExampleFragment()
    fragmentTransaction.add(R.id.fragment_container, fragment) // El primer argumento es el ViewGroup, en el que se debe colocar el fragmento.
    fragmentTransaction.commit()

Con el módulo Fragment KTX, puedes simplificar las transacciones de fragmento con lambdas, por ejemplo:

    supportFragmentManager.commit {
        addToBackStack("...")
        setCustomAnimations(
                R.anim.enter_anim,
                R.anim.exit_anim)
        add(R.id.fragment_container, fragment, "fragment_first")
    }
    
#### replace()

Reemplaza un fragmento por otro que se encuentra actualmente en el contenedor de diseño identificado con el ID y conservar el estado anterior en la pila de actividades:

    val newFragment = ExampleFragment()
    fragmentTransaction.replace(R.id.fragment_container, newFragment)
    fragmentTransaction.addToBackStack(null)
    fragmentTransaction.commit()

#### remove()

Elimina un fragmento existente. Si se agregó a un contenedor, su vista también se elimina de ese contenedor.

    fragmentTransaction.remove(fragment)
    fragmentTransaction.addToBackStack(null)
    fragmentTransaction.commit()

#### addToBackStack()

Al llamar a addToBackStack(), la transacción de reemplazo se guarda en la pila de retroceso para que el usuario pueda revertir la transacción y recuperar el fragmento previo presionando el botón Atrás.

Si agregas varios cambios a la transacción (como otro add() o remove()) y llamas a addToBackStack(), todos los cambios aplicados antes de llamar a commit() se agregarán a la pila de retroceso como una transacción única, y el botón Atrás los revertirá juntos.

Si no llamas a addToBackStack(), cuando realices una transacción que quite un fragmento, ese fragmento se destruirá cuando se confirme la transacción y el usuario no podrá regresar a él.

    fragmentTransaction.addToBackStack(null)

**FragmentActivity** obtiene automáticamente fragmentos de la pila de retroceso mediante onBackPressed().

#### commit()

Una vez que realices los cambios con FragmentTransaction, deberás llamar a commit() para que se apliquen esos cambios.

Llamar a commit() no realiza la transacción inmediatamente, sin embargo, si es necesario, puedes llamar a **executePendingTransactions**() desde el subproceso de tu IU para ejecutar de inmediato transacciones enviadas por commit().

    fragmentTransaction.commit()

`Nota: antes de llamar a commit() probablemente te convenga llamar a addToBackStack() para agregar la transacción a una pila de retroceso de transacciones de fragmentos. Esta pila de actividades está administrada por la actividad y le permite al usuario volver a un estado anterior del fragmento presionando el botón Atrás.`

`Sugerencia: Para cada transacción de fragmentos, puedes aplicar una animación de transición llamando a setTransition() antes de confirmar.`

`Nota: Cuando quitas o reemplazas un fragmento, y agregas la transacción a la pila de actividades, se detiene (no se destruye) el fragmento que se quita. Si el usuario retrocede en su navegación para restaurar el fragmento, este se reinicia. Si no agregas la transacción a la pila de actividades al quitar o reemplazar un fragmento, este se destruirá.`

## Comunicación entre fragmento y actividad

Un Fragment se implementa como un objeto dependiente de un FragmentActivity y puede usarse dentro de múltiples actividades, pero una instancia determinada de un fragmento está directamente vinculada a la actividad que la contiene.

El fragmento puede acceder a la instancia FragmentActivity con getActivity() y realizar tareas como buscar una vista en el diseño de la actividad:

    val listView: View? = activity?.findViewById(R.id.list)
    
La actividad puede llamar a métodos del fragmento mediante la adquisición de una referencia a Fragment desde FragmentManager usando findFragmentById() o findFragmentByTag():

    val fragment = supportFragmentManager.findFragmentById(R.id.example_fragment) as ExampleFragment
####
    val fragment = supportFragmentManager.findFragmentByTag("fragment_dynamic")
    

## Comunicación entre fragmentos

El envío de datos entre Fragments se puede lograr de varias maneras, la forma recomendada de pasar datos entre Fragments es ahora las API de resultados de Fragment, donde pasar los datos es manejado por un FragmentManager, y los Fragmentos se pueden configurar para recibir y enviar datos.

FragmentManager implementa **FragmentResultOwner**. Esto significa que un FragmentManager puede actuar como un almacenamiento central para los resultados de fragmentos. El cambio permite que los fragmentos separados se comuniquen entre sí configurando los resultados de fragmentos y escuchando esos resultados sin que los fragmentos tengan referencias directas entre sí.

Si un Fragment espera recibir datos, puede registrar un FragmentResultListener en un FragmentManager y especificar una clave para identificar los datos que espera, esto actúa como un filtro para los datos que el FragmentManager lo envía:

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Use the Kotlin extension in the fragment-ktx artifact
        setResultListener("requestKey") { key, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val result = bundle.getString("bundleKey")
            // Do something with the result...
        }
    }

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
   

<br/>
<br/>

**Enlaces:**

[**Testing Fragments**](https://github.com/arbems/Android-with-Kotlin-Activity/tree/master/activity-(fragments)/testing)

  
## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.

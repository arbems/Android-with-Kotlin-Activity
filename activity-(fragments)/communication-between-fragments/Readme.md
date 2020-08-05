# Android con Kotlin - Fragments - Comunicación entre fragmentos y actividad

Este código contiene ejemplos de comunicación entre fragmentos y actividad en Android con Kotlin.

## Comunicación entre fragmentos y actividad

Un Fragment se implementa como un objeto dependiente de un FragmentActivity y puede usarse dentro de múltiples actividades, pero una instancia determinada de un fragmento está directamente vinculada a la actividad que la contiene.

El fragmento puede acceder a la instancia FragmentActivity con getActivity() y realizar tareas como buscar una vista en el diseño de la actividad:

    val listView: View? = activity?.findViewById(R.id.list)
    
La actividad puede llamar a métodos del fragmento mediante la adquisición de una referencia a Fragment desde FragmentManager usando findFragmentById() o findFragmentByTag():

    val fragment = supportFragmentManager.findFragmentById(R.id.example_fragment) as ExampleFragment
####
    val fragment = supportFragmentManager.findFragmentByTag("fragment_dynamic")
    

## Comunicación entre fragmentos

El envío de datos entre Fragments se puede lograr de varias maneras:
* Usando las API de resultados de Fragment, donde pasar los datos es manejado por un FragmentManager, y los Fragmentos se pueden configurar para recibir y enviar datos.
* Y usando ViewModel.

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

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
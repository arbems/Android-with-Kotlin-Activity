# Android con Kotlin - Fragments - Compartir datos entre fragmentos

Este código contiene ejemplos de comunicación entre fragmentos en Android con Kotlin.

El envío de datos entre Fragments se puede lograr de varias maneras Fragment Result API o ViewModel:

## Compartir datos entre fragmentos mediante Fragment Result API

Los datos son manejados por un FragmentManager, y los Fragmentos se pueden configurar para recibir y enviar datos.

FragmentManager implementa **FragmentResultOwner**, puede actuar como un almacenamiento central para los resultados de fragmentos. El cambio permite que los fragmentos separados se comuniquen entre sí configurando los resultados de fragmentos y escuchando esos resultados sin que los fragmentos tengan referencias directas entre sí.

Si un Fragment espera recibir datos, puede registrar un **FragmentResultListener** en un FragmentManager y especificar una clave para identificar los datos que espera, esto actúa como un filtro para los datos que el FragmentManager lo envía:

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Use the Kotlin extension in the fragment-ktx artifact
        setResultListener("requestKey") { key, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val result = bundle.getString("bundleKey")
            // Do something with the result...
        }
    }
    
Establecer el resultado en el mismo FragmentManager con la misma requestKey:

    button.setOnClickListener {
        val result = "result"
        // Use the Kotlin extension in the fragment-ktx artifact
        setResult("requestKey", bundleOf("bundleKey" to result))
    }

`Nota: Solo puedes tener un solo objeto de escucha y resultado para una clave determinada.`  

Ten en cuenta que el fragmento del objeto de escucha debe ser STARTED antes de que pueda recibir el resultado. Una vez que un objeto de escucha recibe un resultado y activa la devolución de llamada onFragmentResult(), el resultado se borra. Este comportamiento tiene dos implicaciones importantes:

Los fragmentos sobre la pila de actividades no reciben resultados hasta que se resalten y sean STARTED.
Si un fragmento que escucha un resultado es STARTED cuando se establece el resultado, se activa de inmediato la devolución de llamada del objeto de escucha.

#### Pasar resultados entre fragmentos superiores y secundarios

Para pasar un resultado de un fragmento secundario a un superior, el fragmento superior debe usar getChildFragmentManager() en lugar de getParentFragmentManager() cuando se llama a setFragmentResultListener().

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // We set the listener on the child fragmentManager
        childFragmentManager.setResultListener("requestKey") { key, bundle ->
            val result = bundle.getString("bundleKey")
            // Do something with the result..
        }
    }
    
El fragmento secundario establece el resultado en su FragmentManager:

    button.setOnClickListener {
        val result = "result"
        // Use the Kotlin extension in the fragment-ktx artifact
        setResult("requestKey", bundleOf("bundleKey" to result))
    }
    
#### Testing de resultados de fragmentos

Usa FragmentScenario para hacer llamadas de prueba a setFragmentResult() y a setFragmentResultListener().

[**Ver documentación "Pruebas de resultados de fragmentos"**](https://developer.android.com/training/basics/fragments/pass-data-between?hl=es-419#test)

## Compartir datos entre fragmentos mediante ViewModel

Es muy común que dos o más fragmentos de una actividad necesiten comunicarse entre sí. Los dos fragmentos deben administrar una situación en la que el otro fragmento todavía no se creó o no está visible.

Para solucionar esta dificultad habitual, puedes usar objetos ViewModel. Estos fragmentos pueden compartir un ViewModel mediante su alcance de actividad para administrar esta comunicación.

**Ventajas de este enfoque:**

- La Activity no necesita hacer nada ni saber sobre esta comunicación.
- Los fragmentos no necesitan saber acerca del otro, excepto por el contrato de ViewModel. Si uno de los fragmentos desaparece, el otro sigue funcionando de manera habitual.
- Cada fragmento tiene su propio ciclo de vida y no se ve afectado por el ciclo de vida del otro. Si un fragmento reemplaza al otro, la IU continúa funcionando sin problemas.

Ejemplo comunicación usando ViewModel:

    ViewModel:

     class SharedViewModel : ViewModel() {
        val selected = MutableLiveData<Item>()

        fun select(item: Item) {
            selected.value = item
        }
    }
####    
    Fragment 1:
    
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
       model = activity?.run {
            ViewModelProviders.of(this)[SharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

    }
####    
    Fragment 2:

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = activity?.run {
            ViewModelProviders.of(this)[SharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
        
    }



`Ten en cuenta que ambos fragmentos recuperan la actividad que los contiene. De esa manera, cuando cada fragmento obtiene el ViewModelProvider, reciben la misma instancia SharedViewModel, cuyo alcance está determinado por esta actividad.`

ViewModels también se puede usar como una capa de comunicación entre diferentes Fragmentos de una Actividad. Cada Fragmento puede adquirir el ViewModel usando la misma clave a través de su Actividad. Esto permite la comunicación entre los Fragmentos de forma desacoplada, de modo que nunca necesitan hablar directamente con el otro Fragmento.




## Compartir datos entre fragmentos y actividad

Un Fragment se implementa como un objeto dependiente de un FragmentActivity y puede usarse dentro de múltiples actividades, pero una instancia determinada de un fragmento está directamente vinculada a la actividad que la contiene.

El fragmento puede acceder a la instancia FragmentActivity con getActivity() y realizar tareas como buscar una vista en el diseño de la actividad:

    val listView: View? = activity?.findViewById(R.id.list)
    
La actividad puede llamar a métodos del fragmento mediante la adquisición de una referencia a Fragment desde FragmentManager usando findFragmentById() o findFragmentByTag():

    val fragment = supportFragmentManager.findFragmentById(R.id.example_fragment) as ExampleFragment
####
    val fragment = supportFragmentManager.findFragmentByTag("fragment_dynamic")

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
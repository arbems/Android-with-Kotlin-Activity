# Android con Kotlin - Compartir datos entre actividades y fragmentos con ViewModel

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




## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
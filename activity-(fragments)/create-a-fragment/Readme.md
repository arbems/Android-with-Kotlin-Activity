# Android con Kotlin - Fragments - Crear un fragmento

### Crear clase Fragment

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

### Proporcionar un diseño definido en XML
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

### Añadir fragmento a una actividad

#### 1. Declarando el fragmento en el archivo de diseño de la actividad.

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

#### 2. Guardando el fragmento de forma programática en un ViewGroup existente.

Usando transacciones, agregando fragmentos al diseño de manera dinámica usando la API de FragmentTransaction, mientras la actividad se está ejecutando. Solo hay que especificar un ViewGroup para colocar el fragmento.


## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
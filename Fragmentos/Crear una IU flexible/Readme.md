# Android con Kotlin - Fragments - Crear una IU flexible

Puedes reutilizar tus fragmentos en diferentes configuraciones de diseño a fin de optimizar la experiencia de usuario en función del espacio de pantalla disponible.

![Lifecycle Fragments](https://raw.githubusercontent.com/arbems/Android-with-Kotlin-Activity/master/Fragmentos//0003.png)
`Se muestran dos fragmentos en distintas configuraciones para la misma actividad en diferentes tamaños de pantalla. En una pantalla grande, ambos fragmentos pueden ubicarse uno junto al otro. En cambio, en un móvil, solo puede mostrarse un fragmento a la vez, de modo que uno reemplaza al otro a medida que el usuario navega.`

Para esto usamos la clase FragmentManager que permite agregar, quitar y reemplazar fragmentos en una actividad durante el tiempo de ejecución a fin de crear una experiencia dinámica.

Si tu actividad permite quitar y reemplazar fragmentos, debes agregar los fragmentos iniciales a la actividad durante su método onCreate() y el diseño de tu actividad debe incluir un contenedor View en el que puedas insertar el fragmento.

## Realizar transacciones de fragmentos

Los fragmentos tienen la capacidad de poderse agregar, quitar, reemplazar y realizar otras acciones en respuesta a la interacción del usuario. Cada conjunto de cambios que confirmas en la actividad recibe la denominación de transacción. Usando la API de [**FragmentTransaction**](https://developer.android.com/reference/androidx/fragment/app/FragmentTransaction?hl=es-419).
También puedes guardar cada transacción en la pila de actividades administrada por la actividad, lo que le permitirá al usuario navegar hacia atrás por los cambios realizados en el fragmento.

Puedes adquirir una instancia de FragmentTransaction de FragmentManager llamando a **beginTransaction**():

    val fragmentTransaction = fragmentManager.beginTransaction()
    
### add()
    
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
    
### replace()

Reemplaza un fragmento por otro que se encuentra actualmente en el contenedor de diseño identificado con el ID y conservar el estado anterior en la pila de actividades:

    val newFragment = ExampleFragment()
    fragmentTransaction.replace(R.id.fragment_container, newFragment)
    fragmentTransaction.addToBackStack(null)
    fragmentTransaction.commit()

### remove()

Elimina un fragmento existente. Si se agregó a un contenedor, su vista también se elimina de ese contenedor.

    fragmentTransaction.remove(fragment)
    fragmentTransaction.addToBackStack(null)
    fragmentTransaction.commit()

### addToBackStack()

Al llamar a addToBackStack(), la transacción de reemplazo se guarda en la pila de retroceso para que el usuario pueda revertir la transacción y recuperar el fragmento previo presionando el botón Atrás.

Si agregas varios cambios a la transacción (como otro add() o remove()) y llamas a addToBackStack(), todos los cambios aplicados antes de llamar a commit() se agregarán a la pila de retroceso como una transacción única, y el botón Atrás los revertirá juntos.

Si no llamas a addToBackStack(), cuando realices una transacción que quite un fragmento, ese fragmento se destruirá cuando se confirme la transacción y el usuario no podrá regresar a él.

    fragmentTransaction.addToBackStack(null)

**FragmentActivity** obtiene automáticamente fragmentos de la pila de retroceso mediante onBackPressed().

### commit()

Una vez que realices los cambios con FragmentTransaction, deberás llamar a commit() para que se apliquen esos cambios.

Llamar a commit() no realiza la transacción inmediatamente, sin embargo, si es necesario, puedes llamar a **executePendingTransactions**() desde el subproceso de tu IU para ejecutar de inmediato transacciones enviadas por commit().

    fragmentTransaction.commit()

`Nota: antes de llamar a commit() probablemente te convenga llamar a addToBackStack() para agregar la transacción a una pila de retroceso de transacciones de fragmentos. Esta pila de actividades está administrada por la actividad y le permite al usuario volver a un estado anterior del fragmento presionando el botón Atrás.`

`Sugerencia: Para cada transacción de fragmentos, puedes aplicar una animación de transición llamando a setTransition() antes de confirmar.`

`Nota: Cuando quitas o reemplazas un fragmento, y agregas la transacción a la pila de actividades, se detiene (no se destruye) el fragmento que se quita. Si el usuario retrocede en su navegación para restaurar el fragmento, este se reinicia. Si no agregas la transacción a la pila de actividades al quitar o reemplazar un fragmento, este se destruirá.`


## Attribution

This code was created by [arbems](https://raw.githubusercontent.com//arbems) in 2020.
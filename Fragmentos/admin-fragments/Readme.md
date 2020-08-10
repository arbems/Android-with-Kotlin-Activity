# Android con Kotlin - Fragments - Administrar fragmentos

Este código contiene ejemplos de como administrar fragmentos en Android con Kotlin.

Para administrar los fragmentos de tu actividad, debes usar [**FragmentManager**](https://developer.android.com/reference/androidx/fragment/app/FragmentManager?hl=es-419). <br/>
Para obtenerlo, llama a **getSupportFragmentManager**() desde tu actividad:

    val fragmentManager = supportFragmentManager

Con **FragmentManager** puedes:

* Abrir un FragmentTransaction, que te permite realizar transacciones como agregar y quitar fragmentos.
* Obtener fragmentos que ya existen en la actividad con **findFragmentById**() (para fragmentos que proporcionan una IU en el diseño de la actividad) o **findFragmentByTag**() (para fragmentos con o sin IU)
* Activar fragmentos de la pila de retroceso con **popBackStack**()
* Registrar un receptor para cambios realizados en la pila de retroceso con **addOnBackStackChangedListener**()


## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
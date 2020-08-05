# Android con Kotlin - Activity - Testing Fragments

Código de ejemplo para probar los fragmentos de una app en Android con Kotlin.

## Probar los fragmentos de una app

Es importante validar que proporcionen una experiencia constante y eficiente en el uso de recursos:

* La apariencia de un fragmento debe ser la misma en todos los diseño, incluidos los que admiten pantallas de mayor tamaño o la orientación de dispositivo horizontal.
* No crees la jerarquía de vista de un fragmento a menos que el fragmento sea visible para el usuario.
 
 
### Testear los fragmentos

AndroidX proporciona una biblioteca, FragmentScenario, que permite crear fragmentos y cambiar su estado con el fin de hacer pruebas.

Para usar FragmentScenario declara las dependencias:

    app/build.gradle
    
    dependencies {
            def fragment_version = "1.2.4"
            // ...
            debugImplementation 'androidx.fragment:fragment-testing:$fragment_version'
        }

FragmentScenario incluye métodos para iniciar los siguientes tipos de fragmentos:

* Fragmentos gráficos, que contienen una interfaz de usuario. Para iniciar este tipo de fragmento, llama a launchFragmentInContainer(). FragmentScenario conecta el fragmento al controlador de la vista raíz de una actividad. Esta actividad que lo contiene está vacía de otra manera.

        @RunWith(AndroidJUnit4::class)
        class MyTestSuite {
            @Test fun testEventFragment() {
                // The "fragmentArgs" and "factory" arguments are optional.
                val fragmentArgs = Bundle().apply {
                    putInt("selectedListItem", 0)
                }
                val factory = MyFragmentFactory()
                val scenario = launchFragmentInContainer<MyFragment>(
                        fragmentArgs, factory)
                onView(withId(R.id.text)).check(matches(withText("Hello World!")))
            }
        }

* Fragmentos no gráficos (a veces denominados fragmentos sin interfaz gráfica) que almacenan o realizan un procesamiento a corto plazo de información incluida en varias actividades. Para iniciar este tipo de fragmento, llama a launchFragment(). FragmentScenario conecta este tipo de fragmento a una actividad completamente vacía que no tiene una vista de raíz.

        @RunWith(AndroidJUnit4::class)
        class MyTestSuite {
            @Test fun testEventFragment() {
                // The "fragmentArgs" and "factory" arguments are optional.
                val fragmentArgs = Bundle().apply {
                    putInt("numElements", 0)
                }
                val factory = MyFragmentFactory()
                val scenario = launchFragment<MyFragment>(fragmentArgs, factory)
            }
        }


Si un dispositivo tiene pocos recursos, es posible que el sistema destruya la actividad que contiene tu fragmento. Como consecuencia la app deberá recrear el fragmento cuando el usuario regrese a la app:

    @RunWith(AndroidJUnit4::class)
    class MyTestSuite {
        @Test fun testEventFragment() {
            val scenario = launchFragmentInContainer<MyFragment>()
            scenario.recreate()
        }
    }

`Cuando la clase FragmentScenario recrea el fragmento sometido a prueba, este regresa al estado de ciclo de vida en el que se encontraba antes de recrearse.`

Para cambiar el estado de ciclo de vida de un fragmento, llama a moveToState(), este método admite los siguientes estados como argumentos: CREATED, STARTED, RESUMED y DESTROYED:

    @RunWith(AndroidJUnit4::class)
    class MyTestSuite {
        @Test fun testEventFragment() {
            val scenario = launchFragmentInContainer<MyFragment>()
            scenario.moveToState(State.CREATED)
        }
    }

`Esta acción simula una situación en la que la actividad que contiene el fragmento cambia de estado porque otra ap o una acción del sistema la interrumpe.`

Para activar acciones en el fragmento usa los comparadores de vistas para interactuar con los elementos en tu vista:

    @RunWith(AndroidJUnit4::class)
    class MyTestSuite {
        @Test fun testEventFragment() {
            val scenario = launchFragmentInContainer<MyFragment>()
            onView(withId(R.id.refresh))
                    .perform(click())
        }
    }

Si necesitas llamar a un método en el propio fragmento, como responder a una selección en el menú de opciones, puedes hacerlo de forma segura implementando FragmentAction:

    @RunWith(AndroidJUnit4::class)
    class MyTestSuite {
        @Test fun testEventFragment() {
            val scenario = launchFragmentInContainer<MyFragment>()
            scenario.onFragment(fragment ->
                fragment.onOptionsItemSelected(clickedItem) {
                    // Update fragment's state based on selected item.
                }
            }
        }
    }
    
FragmentScenario también admite la prueba de diálogos. Aunque los diálogos son instancias de fragmentos gráficos, el método launchFragment() se utiliza para que los elementos del diálogo se propaguen en el diálogo, en lugar de hacerlo en la actividad que lo inicia.

En el siguiente fragmento de código, se prueba el proceso de descarte del diálogo:

    @RunWith(AndroidJUnit4::class)
    class MyTestSuite {
        @Test fun testDismissDialogFragment() {
            // Assumes that "MyDialogFragment" extends the DialogFragment class.
            with(launchFragment<MyDialogFragment>()) {
                onFragment { fragment ->
                    assertThat(fragment.dialog).isNotNull()
                    assertThat(fragment.requireDialog().isShowing).isTrue()
                    fragment.dismiss()
                    fragment.requireFragmentManager().executePendingTransactions()
                    assertThat(fragment.dialog).isNull()
                }

                // Assumes that the dialog had a button
                // containing the text "Cancel".
                onView(withText("Cancel")).check(doesNotExist())
            }
        }
    }
        

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
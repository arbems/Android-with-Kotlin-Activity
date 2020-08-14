# Android con Kotlin - Activity - Navegación entre actividades

Código de ejemplo de como iniciar, navegar y enviar datos entre actividades en Android con Kotlin.
              
## Iniciar una actividad desde otra

Dependiendo de si se quiere obtener resultado o no se puede iniciar una nueva actividad usando [**startActivity()**](https://developer.android.com/reference/android/app/Activity#startActivity(android.content.Intent,%20android.os.Bundle)) o [**startActivityForResult()**](https://developer.android.com/reference/android/app/Activity#startActivityForResult(android.content.Intent,%20int)). 

En ambos casos hay que pasar un [**Intent**](https://developer.android.com/reference/android/content/Intent). Este objeto especifica la actividad exacta que quieres iniciar o describe el tipo de acción que quieres realizar (y el sistema selecciona la actividad adecuada para ti, que incluso puede ser de otra aplicación). 

Un objeto [**Intent**](https://developer.android.com/reference/android/content/Intent) también puede contener pequeñas cantidades de datos que utilizará la actividad que se inicie.<br/> [**Ver Intents y filtro de intents**]()

### startActivity

Si la actividad recién iniciada no necesita mostrar un resultado, la actividad actual puede iniciarla llamando al método [**startActivity()**](https://developer.android.com/reference/android/app/Activity#startActivity(android.content.Intent,%20android.os.Bundle)). No recibirá ninguna información sobre cuándo finaliza la actividad.

Este método lanza **ActivityNotFoundException** si no se encontró actividad para ejecutar la Intent dada.

Navegación explícita, cuando navegues dentro de tu propia aplicación:
    
    val intent = Intent(this, AActivity::class.java)
    startActivity(intent)
    
Navegación implícita, para aprovechar las actividades que proporcionan otras aplicaciones del dispositivo y que pueden realizar las acciones por ti:
    
    val intent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_EMAIL, recipientArray)
    }
    startActivity(intent)

### startActivityForResult

Aquí se desea obtener el resultado de una actividad cuando esta termina.

![startActivityForResult](https://raw.githubusercontent.com/arbems/Android-with-Kotlin-Activity/master/Navegaci%C3%B3n%20entre%20actividades/0001.png)

Método [**startActivityForResult(Intent, Int)**](https://developer.android.com/reference/android/app/Activity#startActivityForResult(android.content.Intent,%20int)), donde el parámetro entero identifica la llamada. Este identificador sirve para desambiguar entre varias llamadas a startActivityForResult(Intent, int) de la misma actividad.

* Parámetro requestCode(int): Si >= 0, este código se devolverá en onActivityResult() cuando finalice la actividad.

####
    val intent: Intent = Intent(this, BActivity::class.java)
    startActivityForResult(intent, CODE_REQUEST)
  

El resultado se obtiene a través del método [**onActivityResult(int, int, Intent)**](https://developer.android.com/reference/android/app/Activity#onActivityResult(int,%20int,%20android.content.Intent)).
* requestCode(int): El código de solicitud de número entero proporcionado originalmente a startActivityForResult (), lo que le permite identificar de quién proviene este resultado.
* resultCode(int): El código de resultado entero devuelto por la actividad secundaria a través de su setResult().
* data: Un Intent, que puede devolver datos de resultados a la persona que llama (se pueden adjuntar varios datos a los "extras" de Intent).

####
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
            super.onActivityResult(requestCode, resultCode, intent)
    
            when (requestCode) {
                Companion.CODE_REQUEST ->
                    if (resultCode == RESULT_OK) {
                        textView.text = intent?.extras?.get("text_result_id") as String
                    }
            }
        }
     

Cuando se lleva a cabo una actividad secundaria, puedes llamar a **setResult(int)** para mostrarle los datos a la actividad superior.

####
    val resultIntent = Intent()
    resultIntent.putExtra("text_result_id", "Result text returned")
    setResult(Activity.RESULT_OK, resultIntent)

Si una actividad secundaria falla por cualquier razón, por ejemplo, debido a un bloqueo, la actividad superior recibirá un resultado con el código RESULT_CANCELED.
     
`* En ambos casos hay que pasar un objeto Intent, que especifica la actividad exacta que quieres iniciar o describe el tipo de acción que quieres realizar.
 También puede contener algunos datos que utilizara la actividad que se inicie.`


## Enviar datos entre actividades

### putExtra

Cuando una app crea un objeto Intent para usar en startActivity(Intent) al iniciar una nueva Actividad, la app puede pasar parámetros por medio de este método.

####
    val intent: Intent = Intent(this, AActivity::class.java)
    intent.putExtra("text_id", "My text to send")
    startActivity(intent)
    
Obtener parámetros:

    val text = intent?.extras?.get("text_id")?.toString()
    
El SO empaqueta el **Bundle** subyacente del intent. Luego, el SO crea la nueva actividad, desempaqueta los datos y pasa el intent a la nueva actividad.

Cuando envíes datos mediante un intent, debes tener cuidado de limitar el tamaño de los datos a unos pocos KB, ya que, si se envían demasiados, es posible que el sistema arroje una excepción **TransactionTooLargeException**.

### writeToParcel

En algunos casos, es posible que necesites un mecanismo para enviar objetos compuestos o complejos entre actividades. En esos casos, la clase personalizada debe implementar un objeto Parcelable y proporcionar el método [**writeToParcel(Parcel, int)**](https://developer.android.com/reference/android/os/Parcelable#writeToParcel(android.os.Parcel,%20int)) apropiado.

También debe proporcionar un campo no nulo llamado CREATOR que implemente la interfaz Parcelable.Creator, cuyo método createFromParcel() se usa para convertir el elemento Parcel nuevamente en el objeto actual.

## Coordinar actividades

Cuando una actividad inicia otra, ambas experimentan transiciones en su ciclo de vida. La primera actividad deja de funcionar y entra en el estado Paused o Stopped, mientras se crea la otra actividad. Si esas actividades comparten datos guardados en el disco o en alguna otra parte, es importante que entiendas que no se detiene la primera actividad por completo antes de que se cree la segunda.

Esta secuencia predecible de devoluciones de llamada del ciclo de vida te permite administrar la transición de información de una actividad a otra.

![Lifecycle Activity](https://raw.githubusercontent.com/arbems/Android-with-Kotlin-Activity/master/Navegaci%C3%B3n%20entre%20actividades/0002.png)

## Enlaces 

#### [Compartir datos entre actividades y fragmentos con ViewModel]()

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
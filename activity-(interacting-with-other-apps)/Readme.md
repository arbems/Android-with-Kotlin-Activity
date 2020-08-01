# Android con Kotlin - Activity - Interactuar con otras apps

Código de ejemplo de como interactuar con otras apps en Android con Kotlin.

Interacciones básicas con otras apps, como iniciar otra app, recibir un resultado de esa app y hacer que tu app pueda responder a los intents de otras.

![Lifecycle Activity](https://github.com/arbems/Android-with-Kotlin-Activity/blob/master/activity-(interacting-with-other-apps)/0001.png)
               
## Enviar al usuario a otra app

### Crear intent implícito

Para navegar a una actividad de otra app para realizar una acción hay que usar un intent implícito.

* Acción (obligatorio)
Hay que declarar obligatoriamente la acción que se llevará a cabo. Hay que indicar la acción especifica lo que deseas hacer, por ejemplo, ver, editar, enviar o bien obtener algo.

* Datos (opcional)
Los intents a menudo también incluyen datos asociados con la acción, como la dirección que quieres ver o el mensaje de correo electrónico que quieres enviar.
Según el intent que quieras crear, los datos podrían ser un Uri, uno de varios otros tipos de datos o es posible que el intent no necesite datos.

`Nota: Es importante que definas tu Intent para que sea lo más específico posible. Por ejemplo, si quieres mostrar una imagen con el intent ACTION_VIEW, debes especificar un tipo MIME image/*. Esto evita que el intent active las apps que pueden "ver" otros tipos de datos (como una app de mapas).`

####
    Enviar un correo electrónico con un archivo adjunto:
    
        Intent(Intent.ACTION_SEND).apply {
            type = HTTP.PLAIN_TEXT_TYPE
            putExtra(Intent.EXTRA_EMAIL, arrayOf("jon@example.com")) // recipients
            putExtra(Intent.EXTRA_SUBJECT, "Email subject")
            putExtra(Intent.EXTRA_TEXT, "Email message text")
            putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"))
        }
        
`De forma predeterminada, el sistema define el tipo de MIME requerido por un intent según los datos de Uri incluidos. Si no se incluye un Uri en el intent, en general, debes usar setType() para especificar el tipo de datos asociados con el intent.`
        
####  Intents y filtros de intents -  [ver más](https://developer.android.com/guide/topics/manifest/activity-element)

### Comprobar que existe una app para recibir el intent

Aunque la plataforma de Android garantiza que se resolverán ciertos intents con una de las apps integradas, siempre debes incluir un paso de verificación antes de invocar un intent.

Para verificar que haya una actividad disponible que pueda responder al intent, llama a queryIntentActivities() a fin de obtener una lista de actividades capaces de controlar el Intent.

####
    val activities: List<ResolveInfo> = packageManager.queryIntentActivities(
                intent,
                PackageManager.MATCH_DEFAULT_ONLY
        )
        val isIntentSafe: Boolean = activities.isNotEmpty()
    
    // Si isIntentSafe es false, entonces no hay apps para controlar el intent.
    
`Precaución: Si invocas un intent y no hay ninguna app disponible en el dispositivo que pueda controlarlo, se detendrá la app.`

####
    public abstract List<ResolveInfo> queryIntentActivities (
          Intent intent, 
          int flags)
    
    intent: no puede ser nulo
    
    flags: Opciones adicionales para modificar el dato devuelto.
    MATCH_DEFAULT_ONLY es el más importante, limita la resolución solo a aquellas actividades que apoyan el Intent.
    
    returns List<ResolveInfo>: que contiene una entrada para cada actividad coincidente, ordenado de mejor a peor.
    Si no hay actividades coincidentes, se devuelve una lista vacía. Este valor no puede ser nulo.

`Nota: Debes realizar esta comprobación cuando se inicie por primera vez tu actividad, en caso de que necesites inhabilitar la función que utiliza el intent antes de que el usuario trate de utilizarla. Si conoces una app específica que pueda controlar el intent, proporciona un vínculo para que el usuario la descargue.`

`Importante que incluyas comprobaciones del tiempo de ejecución para tus intents implícitos.`

### Iniciar una actividad con el intent

Una vez creado el Intent y hayas configurado la información adicional, llama a startActivity() o startActivityForResult() para enviarlo al sistema.

Lanza una nueva actividad. No recibirá ninguna información sobre cuándo finaliza la actividad:

####
    public void startActivity (Intent intent)
    
    public void startActivity (
               Intent intent, 
               Bundle options)
    
    options: Opciones adicionales sobre cómo debe iniciarse la actividad.

Lanza una nueva actividad de la que se desea obtener un resultado cuando finalice:

####
    public void startActivityForResult (Intent intent, int requestCode)
    
    public void startActivityForResult (Intent intent, 
                    int requestCode, 
                    Bundle options)
    
    requestCode: código de solicitud, este código se devolverá en onActivityResult() cuando finalice la actividad.
    
    options: opciones adicionales sobre cómo debe iniciarse la Actividad.

`Si el sistema identifica más de una actividad que puede controlar el intent, se mostrará un diálogo para que el usuario seleccione qué app usar. Si existe una única actividad que puede manejar el intent, el sistema la inicia de inmediato.`

![Lifecycle Activity](https://github.com/arbems/Android-with-Kotlin-Activity/blob/master/activity-(interacting-with-other-apps)/0002.png)

### Mostrar un selector de apps

El diálogo de selector obliga al usuario a seleccionar qué app quiere utilizar para la acción en cada caso (no puede elegir una app predeterminada para la acción).

Si varias aplicaciones responden a la intent y es posible que el usuario quiera usar una diferente cada vez, debes mostrar explícitamente un cuadro de diálogo de selección.

Para mostrar el selector, crea un Intent utilizando createChooser() y pásalo a startActivity().

####
        val intent = Intent(Intent.ACTION_SEND)
        ...
        // Esto dice algo como "Compartir esta foto con"
        val title = resources.getString(R.string.chooser_title)
        
        // Crea Intent para mostrar selector
        val chooser = Intent.createChooser(intent, title)
    
        // Verifique que la Intent se resuelva en al menos una actividad
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(chooser)
        }
        
`Aquí se muestra un diálogo con una lista de apps que responden al intent pasado al método createChooser(), con el texto proporcionado como título del diálogo.`

![Lifecycle Activity](https://github.com/arbems/Android-with-Kotlin-Activity/blob/master/activity-(interacting-with-other-apps)/0003.png)
    
### Obtener el resultado devuelto

a

####
    sample
 
 
 
## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
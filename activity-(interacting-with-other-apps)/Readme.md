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

Recibir el resultado desde la otra actividad. Cuando esta actividad finalize, se llamará a su método onActivityResult() con el requestCode dado.
Por ejemplo, tu app puede iniciar una app de cámara y recibir la fotografía tomada como resultado.

onActivityResult, se invoca cuando una actividad que inició se cierra, proporcionándole el código de solicitud con el que lo inició, el código de resultado que devolvió y datos del resultado:

####
    protected void onActivityResult (
                    int requestCode, 
                    int resultCode, 
                    Intent data)
    
    resultCode: será RESULT_CANCELED si no devolvió ningún resultado o se bloqueó durante su operación o RESULT_OK si se realizó la operación de manera correcta.

Para controlar correctamente el resultado, debes comprender cuál será el formato del Intent del resultado. Es muy sencillo hacerlo cuando la actividad que muestra el resultado es una de tus propias actividades. Las apps incluidas con la plataforma de Android ofrecen sus propias API, que puedes utilizar para obtener datos de resultados específicos. Por ejemplo, la app de Personas siempre muestra un resultado con el URI de contenido que identifica el contacto seleccionado, y la app de Cámara muestra un Bitmap en el objeto "data" adicional.

## Recibir solicitudes de otras apps

### Responder a solicitud de acción de otras apps

Si tu app puede realizar una acción que podría ser útil desde otra app, debe estar preparada para responder a las solicitudes de acción especificando el filtro de intents apropiado en tu actividad.

#### Filtros de intents
Para permitir que otras apps inicien tu actividad de esta manera, debes agregar un elemento <intent-filter> del archivo de manifiesto para el elemento <activity> correspondiente.

Cada filtro de intents que agregues debe ser lo más específico posible en cuanto al tipo de acción y los datos que la actividad acepta.

####
    <acivity>
           <intent-filter>
                <action android:name="android.intent.action.SEND"/>
                <category android:name="android.intent.category.DEFAULT"/>
                <data android:mimeType="text/plain"/>
                <data android:mimeType="image/*"/>
           </intent-filter>
           <intent-filter></intent-filter>
            ...
    </activity>
    
    Action: Declara la acción de la intent aceptada, en el atributo name. El valor debe ser el de la string literal de una acción, no la constante de clase.
    
    Data: Declara el tipo de datos que se acepta, mediante el uso de uno o más atributos que especifican varios aspectos del URI de datos (scheme, host, port, path, etc.) y el tipo de MIME.
    
    Category: Declara la categoría de la intent aceptada, en el atributo name. El valor debe ser el de la string literal de una acción, no la constante de clase. El sistema admite varias categorías diferentes, pero la mayoría se usan muy poco. Sin embargo, todos los intents implícitos se definen con CATEGORY_DEFAULT de forma predeterminada.

`Nota: Si no necesitas declarar información específica acerca del Uri de los datos (por ejemplo, cuando tu actividad controla cualquier otro tipo de datos "adicionales", en lugar de un URI), debes especificar solo el atributo android:mimeType para declarar el tipo de datos que controla tu actividad, como text/plain o image/jpeg.`

`Sugerencia: Si quieres que el ícono del diálogo del selector sea diferente del ícono predeterminado de tu actividad, agrega android:icon en el elemento <intent-filter>.`

Si tu app está instalada en un dispositivo, el sistema identifica tus filtros de intents y agrega la información a un catálogo interno de intents admitidos por todas las apps instaladas. Cuando una aplicación llama a startActivity() o startActivityForResult(), con un intent implícito, el sistema encuentra qué actividad (o actividades) puede responder al intent.

Si existen dos pares de acción y datos que son mutuamente excluyentes en tus comportamientos, debes crear filtros de intents separados para especificar qué acciones son aceptables cuando están sincronizadas con determinados tipos de datos.

Por ejemplo, supongamos que la actividad controla tanto texto como imágenes para los intents ACTION_SEND y ACTION_SENDTO. En ese caso, debes definir dos filtros de intents separados para las dos acciones porque un intent ACTION_SENDTO debe utilizar el Uri de datos para especificar la dirección del destinatario mediante el esquema de URI send o sendto:

      <activity android:name="ShareActivity">
        <!-- filter for sending text; accepts SENDTO action with sms URI schemes -->
        <intent-filter>
            <action android:name="android.intent.action.SENDTO"/>
            <category android:name="android.intent.category.DEFAULT"/>
            <data android:scheme="sms" />
            <data android:scheme="smsto" />
        </intent-filter>
        <!-- filter for sending text or images; accepts SEND action and text or image data -->
        <intent-filter>
            <action android:name="android.intent.action.SEND"/>
            <category android:name="android.intent.category.DEFAULT"/>
            <data android:mimeType="image/*"/>
            <data android:mimeType="text/plain"/>
        </intent-filter>
    </activity>

`Nota: Para recibir intents implícitos, debes incluir la categoría CATEGORY_DEFAULT en el filtro de intents. Los métodos startActivity() y startActivityForResult() tratan todos los intents como si declararan la categoría CATEGORY_DEFAULT. Si no la declaras en el filtro de intents, no se resolverá ningún intent implícito en la actividad.`

### Obtener intent en la actividad

Para controlar el intent en tu actividad debes decidir que acción llevarás a cabo, para esto hay que leer el Intent que se usó para iniciarla.

Cuando comience tu actividad, llama a getIntent() para recuperar el Intent que inició la actividad. Puedes hacerlo en cualquier momento durante el ciclo de vida de la actividad, pero, en general, debes hacerlo durante las primeras devoluciones de llamada, como onCreate() o onStart().

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
    
            setContentView(R.layout.main)
    
            val data: Uri? = intent?.data
    
            // Averigua qué hacer en función del tipo de intent
            if (intent?.type?.startsWith("image/") == true) {
                // Manejar intents con datos de imagen ...
            } else if (intent?.type == "text/plain") {
                // Manejar intents con texto ...
            }
        }
    
### Devolver un resultado

Si quieres mostrar un resultado a la actividad que invocó la tuya, tan solo llama a setResult() para especificar el código y el Intent del resultado.

Cuando finalices la operación y el usuario deba volver a la actividad original, llama a finish() para cerrar (y finalizar) tu actividad.

        Intent("com.example.RESULT_ACTION", Uri.parse("content://result_uri")).also { result ->
            setResult(Activity.RESULT_OK, result)
        }
        finish()

`Siempre debes especificar un código de resultado con el resultado. En general, es RESULT_OK o RESULT_CANCELED.`
 
`Nota: El resultado se establece en RESULT_CANCELED de forma predeterminada. De este modo, si el usuario presiona el botón Atrás antes de completar la acción y antes de que establezcas el resultado, la actividad original recibirá el resultado de "cancelada".`

`Nota: No es necesario verificar si la actividad se inició con startActivity() o startActivityForResult(). Simplemente llama a setResult() si el intent que inició tu actividad puede esperar un resultado. Si la actividad original llama a startActivityForResult(), el sistema le mostrará el resultado que proporciones a setResult(); de lo contrario, se omitirá el resultado.`

Si usas el código de resultado para mostrar un número entero y no necesitas incluir el Intent, puedes llamar a setResult() y pasar solo un código de resultado.

    setResult(RESULT_COLOR_RED)
    finish()

En este caso, solo puede haber unos pocos resultados posibles, de modo que el código de resultado es un número entero definido localmente (mayor que 0). Esto funciona bien cuando muestras un resultado a una actividad en tu propia app, porque la actividad que recibe el resultado puede hacer referencia a la constante pública que determina el valor del código de resultado.

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
# Android con Kotlin - Activity - Accesos directos apps

Código de ejemplo que muestra como crear y administrar accesos directos a aplicaciones en Android con Kotlin.

Accesos directos para realizar acciones específicas en una aplicación, ayudando a los usuarios a iniciar rápidamente tareas comunes o recomendadas dentro de la aplicación.

## Crear y administrar accesos directos a aplicaciones

Cada acceso directo hace referencia a uno o más intents, cada uno de los cuales inicia una acción específica cuando los usuarios seleccionan el acceso directo.

`Nota: Solo las actividades principales (actividades que manejan la Intent.ACTION_MAIN acción y la Intent.CATEGORY_LAUNCHER categoría) pueden tener accesos directos. Si una aplicación tiene múltiples actividades principales, debe definir el conjunto de accesos directos para cada actividad.`

![static and dynamic shortcuts](https://github.com/arbems/Android-with-Kotlin-Activity/blob/master/activity-(shortcuts-to-apps)/0001.png)
`Usando los accesos directos de aplicaciones, puede mostrar acciones clave y llevar a los usuarios a su aplicación al instante`

### Tipos de accesos directos

* **Static shortcuts**, los accesos directos estáticos proporcionan enlaces a acciones genéricas dentro de su aplicación, y estas acciones deben permanecer consistentes durante la vida útil de la versión actual de su aplicación. Se definen en un archivo de recursos que se empaqueta en un APK o paquete de aplicaciones.
    * Por ejemplo, ver los mensajes enviados, configurar una alarma...
    
* **Dynamic shortcuts**, se usan para acciones en aplicaciones sensibles al contexto, pueden ser publicados, actualizados y eliminados por la aplicación solo en tiempo de ejecución.
    * Por ejemplo, llamar a una persona específica, navegar a una ubicación específica y cargar un juego desde el último punto guardado del usuario.
    
* **Pinned shortcuts**, se utilizan para acciones específicas dirigidas por el usuario, se pueden agregar en tiempo de ejecución si el usuario otorga permiso.
    * Por ejemplo, acceso directo a cualquier sitio web específico o ruta de google maps.

`Nota: Los usuarios también pueden crear pinned shortcuts copiando los accesos directos estáticos y dinámicos de su aplicación en el iniciador.`

### Limitaciones de acceso directo

Aunque puedes publicar hasta cinco accesos directos (accesos directos estáticos y dinámicos combinados) a la vez para su aplicación, la mayoría de los lanzadores solo pueden mostrar cuatro.

Sin embargo, no hay límite para la cantidad de Pinned shortcuts a su aplicación que los usuarios pueden crear. Aunque su aplicación no puede eliminar los accesos directos anclados, aún puede deshabilitarlos.

`Nota: Aunque otras aplicaciones no pueden acceder a los metadatos dentro de sus accesos directos, el iniciador en sí puede acceder a estos datos. Por lo tanto, estos metadatos deben ocultar información confidencial del usuario.`

## Crear accesos directos

### Crear accesos directos estáticos
Los accesos directos entregan tipos específicos de contenido a sus usuarios al ayudarlos a acceder rápidamente a partes de su aplicación.
    
1. En el archivo de manifiesto de su aplicación busque una actividad cuyos filtros de intent estén configurados para android.intent.action.MAIN android.intent.category.LAUNCHER
   y agrega `<meta-data>` para hacer referencia al archivo de recursos donde se definen los accesos directos de la aplicación.
       
        <activity android:name="Main">
          <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
          </intent-filter>
          
          <meta-data android:name="android.app.shortcuts"
                     android:resource="@xml/shortcuts" /> 
        </activity>
2. Crea archivo de recurso `res/xml/shortcuts.xml`
    
3. En este nuevo archivo de recursos, agregue un `<shortcuts>` elemento raíz, que contiene una lista de `<shortcut>` elementos. Cada `<shortcut>` elemento contiene información sobre un acceso directo estático, incluido su icono, sus etiquetas de descripción y los intents que lanza dentro de la aplicación:

        <shortcuts xmlns:android="http://schemas.android.com/apk/res/android">
            <shortcut
                android:shortcutId="compose"
                android:enabled="true"
                android:icon="@drawable/compose_icon"
                android:shortcutShortLabel="@string/compose_shortcut_short_label1"
                android:shortcutLongLabel="@string/compose_shortcut_long_label1"
                android:shortcutDisabledMessage="@string/compose_disabled_message1">
                <intent
                    android:action="android.intent.action.VIEW"
                    android:targetPackage="com.arbems.appsshortcuts"
                    android:targetClass="com.arbems.appsshortcuts.MainActivity" />
                <!-- If your shortcut is associated with multiple intents, include them
                     here. The last intent in the list determines what the user sees when
                     they launch this shortcut. -->
                <categories android:name="android.shortcut.conversation" />
            </shortcut>
            <!-- Specify more shortcuts here. -->
        </shortcuts>

`Debe proporcionar un valor para android:shortcutId y android:shortcutShortLabel. Todos los demás valores son opcionales.`

* android:shortcutId
* android:shortcutShortLabel -> descripción breve, limite 10 caracteres
* android:shortcutLongLabel -> descripción, limite 25 caracteres
* android:shortcutDisabledMessage -> El mensaje que aparece cuando el usuario intenta iniciar un acceso directo deshabilitado.
* android:enabled
* android:icon -> icono que muestra al usuario

* `<intent>` La acción que el sistema inicia cuando el usuario selecciona el acceso directo. Esta intención debe proporcionar un valor para el android:action atributo. Puede proporcionar múltiples intentos para un solo acceso directo.
* `<categories>` Proporciona una agrupación para los tipos de acciones que realizan los accesos directos de su aplicación, como la creación de nuevos mensajes de chat. Para obtener una lista de categorías de accesos directos compatibles, vea [ShortcutInfo](https://developer.android.com/reference/android/content/pm/ShortcutInfo).

### Crear accesos directos dinámicos

Los accesos directos dinámicos proporcionan enlaces a acciones específicas y sensibles al contexto dentro de su aplicación. Estas acciones pueden cambiar entre los usos de su aplicación, y pueden cambiar incluso mientras su aplicación se está ejecutando.

`En Android 7.0 (API nivel 25) y superior admite accesos directos para acciones rápidas para sus aplicaciones desde los iniciadores, ayudan a sus usuarios a iniciar rápidamente tareas comunes o recomendadas dentro de la aplicación, por ejemplo acceso a chats recientes, llamadas, notas, etc.`

**[ShortcutManager](https://developer.android.com/reference/android/content/pm/ShortcutManager)** API le permite completar las siguientes operaciones en accesos directos dinámicos:

* Publicar: se usa **setDynamicShortcuts()** para redefinir la lista completa de accesos directos dinámicos, o se usa **addDynamicShortcuts()** para aumentar una lista existente de accesos directos dinámicos.

        val shortcutManager = getSystemService(ShortcutManager::class.java)
    
        val shortcutInfo = ShortcutInfo.Builder(context, "id1")
                .setShortLabel("Website")
                .setLongLabel("Open the website")
                .setIcon(Icon.createWithResource(context, R.drawable.icon_website))
                .setIntent(Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/arbems/")))
                .build()

        shortcutManager!!.dynamicShortcuts = listOf(shortcutInfo)

* Actualización: use el **updateShortcuts()** método.

* Eliminar: **removeDynamicShortcuts()** elimine un conjunto de métodos abreviados dinámicos utilizando o elimine todos los métodos abreviados dinámicos utilizando **removeAllDynamicShortcuts()**.

        val shortcutManager = getSystemService(ShortcutManager::class.java)

        shortcutManager!!.removeAllDynamicShortcuts()

Para conocer los métodos que recuperan información sobre un acceso directo único, incluidos los identificadores, el tipo y el estado, lea la referencia de [ShortcutInfo](https://developer.android.com/reference/android/content/pm/ShortcutInfo).

### Crear accesos directos anclados

A diferencia de los accesos directos estáticos y dinámicos, los accesos directos fijados aparecen en los iniciadores compatibles como iconos separados.

`En Android 8.0 (API nivel 26) y superior, puedes crear accesos directos anclados`

![pinned shortcuts](https://github.com/arbems/Android-with-Kotlin-Activity/blob/master/activity-(shortcuts-to-apps)/0002.png)

1. Use **isRequestPinShortcutSupported()** para verificar que el iniciador predeterminado del dispositivo admite la fijación de accesos directos en la aplicación.

2. Cree un objeto **ShortcutInfo**.

3. Anclar el acceso directo al iniciador del dispositivo llamando a **requestPinShortcut()**. Durante este proceso, puede pasar un objeto **PendingIntent**, que notifica a su aplicación solo cuando el acceso directo se fija correctamente.

`Después de anclar un acceso directo, su aplicación puede actualizar su contenido utilizando el método updateShortcuts()`

####
    val shortcutManager = getSystemService(ShortcutManager::class.java)

    if (shortcutManager!!.isRequestPinShortcutSupported) {
        // Assumes there's already a shortcut with the ID "my-shortcut".
        // The shortcut must be enabled.
        val pinShortcutInfo = ShortcutInfo.Builder(applicationContext, "my-shortcut")
                .setShortLabel("My pinned shortcut")
                .setLongLabel("My pinned shortcut")
                .setIntent(Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/arbems/")))
                .build()

        // Create the PendingIntent object only if your app needs to be notified
        // that the user allowed the shortcut to be pinned. Note that, if the
        // pinning operation fails, your app isn't notified. We assume here that the
        // app has implemented a method called createShortcutResultIntent() that
        // returns a broadcast intent.
        val pinnedShortcutCallbackIntent = shortcutManager.createShortcutResultIntent(pinShortcutInfo)

        // Configure the intent so that your app's broadcast receiver gets
        // the callback successfully.For details, see PendingIntent.getBroadcast().
        val successCallback = PendingIntent.getBroadcast(applicationContext, /* request code */ 0,
                pinnedShortcutCallbackIntent, /* flags */ 0)

        shortcutManager.requestPinShortcut(pinShortcutInfo,
                successCallback.intentSender)
    }


## Administrar accesos directos

### Comportamiento de acceso directo

### Administra múltiples intents y actividades

### Actualizar accesos directos

### Seguimiento del uso de accesos directos

### Deshabilitar accesos directos

### Rate Limiting

### Copia de seguridad y restaurar

## Prácticas recomendadas para accesos directos

#### 1. Seguir [pautas de diseño](https://commondatastorage.googleapis.com/androiddevelopers/shareables/design/app-shortcuts-design-guidelines.pdf)

#### 2. Publica solo cuatro accesos directos distintos

#### 3. Limite la longitud de la descripción del acceso directo

"descripción corta" de un acceso directo a 10 caracteres y limite la longitud de la "descripción larga" a 25 caracteres.

#### 4. Mantener acceso directo e historial de uso de acciones

Para cada acceso directo que cree, considere las diferentes formas en que un usuario puede realizar la misma tarea directamente dentro de su aplicación. Recuerde llamar reportShortcutUsed()en cada una de estas situaciones para que el iniciador mantenga un historial preciso de la frecuencia con la que un usuario realiza las acciones que representan sus accesos directos.

#### 5. Actualice los accesos directos solo cuando se conserve su significado

Al cambiar los accesos directos dinámicos y anclados, llame updateShortcuts() solo cuando cambie la información de un acceso directo que ha conservado su significado. De lo contrario, debe usar uno de los siguientes métodos, según el tipo de acceso directo que está recreando:

* Dynamic shortcuts: addDynamicShortcuts() o setDynamicShortcuts()
* Pinned shortcuts: requestPinShortcut()

`Por ejemplo, si creó un acceso directo para navegar a un supermercado, sería apropiado actualizar el acceso directo si el nombre del supermercado cambia pero su ubicación sigue siendo la misma. Sin embargo, si el usuario comenzó a comprar en un supermercado diferente, sería mejor crear un nuevo acceso directo.`

#### 6. Compruebe los accesos directos dinámicos cada vez que inicie su aplicación

Los accesos directos dinámicos no se conservan cuando el usuario restaura sus datos en un nuevo dispositivo. Por esta razón, le recomendamos que verifique la cantidad de objetos devueltos getDynamicShortcuts() cada vez que inicie su aplicación y vuelva a publicar accesos directos dinámicos según sea necesario, como se muestra en el fragmento de código en Copia de seguridad y restauración.

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
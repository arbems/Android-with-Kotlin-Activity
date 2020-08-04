# Android con Kotlin - Activity - Android App Links

Código de ejemplo de control de Android App Links en Android con Kotlin.

Los Android App Links son URL de HTTP que acercan a los usuarios al contenido específico de tu app para Android. Pueden atraer más tráfico a tu app, ayudarte a descubrir qué contenido de la app se utiliza más y facilitar que los usuarios compartan y busquen contenido en una app instalada.

Permiten a los usuarios iniciar directamente una aplicación cuando hacen clic en las URL que admite su aplicación y también pueden hacer que se pueda buscar el contenido de su aplicación.

Puedes configurar Android App Links para llevar a los usuarios al contenido específico de un vínculo directamente en tu app sin incluir el diálogo de selección de apps.

`Nota: App Links Assistant, este asistente te guiará a través de cómo implementar enlaces de aplicaciones de Android (Tools->App Links Assistant).`

## Vinculos directos y Android App Link

#### Vínculos directos 
Son URLs que llevan a los usuarios directamente al contenido específico de tu app. En Android, para configurar vínculos directos, debes agregar filtros de intents y extraer datos de intents entrantes para dirigir a los usuarios a la actividad correcta.
Sin embargo, si otras apps instaladas en el dispositivo de un usuario pueden controlar el mismo intent, es posible que los usuarios no vayan directamente a tu app.

#### Android App Links 
En Android 6.0 (API nivel 23) y versiones posteriores permite que una app se designe a sí misma como el controlador predeterminado de un tipo de vínculo específico. Si el usuario no quiere que la app sea el controlador predeterminado, puede anular este comportamiento desde la configuración del sistema de su dispositivo.

Android App Links ofrece los siguientes beneficios:

* **Seguro y específico**: Android App Links usa URL HTTP que se vinculan con el dominio de un sitio web de tu propiedad, por lo que ninguna otra app puede usar tus vínculos. Uno de los requisitos es que debes verificar la propiedad de tu dominio.

* **Experiencia del usuario perfecta**: Debido a que Android App Links usa una única URL HTTP para el mismo contenido en tu sitio web y en tu app, los usuarios que no tienen la app instalada simplemente van a tu sitio web en lugar de la app, sin páginas 404 ni errores.

* **Compatibilidad con Apps instantáneas Android**: Con ella, tus usuarios pueden ejecutar tu app para Android sin instalarla. Si quieres agregar compatibilidad con Apps instantáneas a tu app para Android, configura Android App Links y visita g.co/InstantApps.

* **Atrae a los usuarios de la Búsqueda de Google**: Los usuarios abren directamente contenido específico de tu app haciendo clic en una URL de Google desde un navegador móvil, la app de la Búsqueda de Google, la búsqueda directa en Android o mediante el Asistente de Google.

## Cómo agregar Android App Links

1. Crea vínculos directos al contenido específico de la app.
2. Agrega la verificación para tus vínculos directos.

Android studio tiene una herramienta para crear Android App Links - [Asistente de Android App Links](https://developer.android.com/studio/write/app-link-indexing)

### 1. Crear vínculos directos al contenido de la app
En el manifiesto de la app, crea filtros de intents para los URI de tu sitio web y configura la app para usar datos de los intents.

Agrega un filtro de intents con:
* `<action>`
Especifica la acción del intent ACTION_VIEW para que se pueda acceder al filtro de intents desde la Búsqueda de Google.

* `<data>`
Agrega una o más etiquetas data; estas deben representar un formato URI que se resuelva en la actividad.

* `category`
Incluye la categoría BROWSABLE. Es necesaria para que se pueda acceder al filtro de intents desde un navegador web.
Incluye también la categoría DEFAULT. De esta manera, tu app puede responder a intents implícitos.

        <activity
             android:name="com.example.android.TestActivity"
             android:label="@string/title_test" >
             <intent-filter android:label="@string/filter_view_http_test">
                 <action android:name="android.intent.action.VIEW" />
                 <category android:name="android.intent.category.DEFAULT" />
                 <category android:name="android.intent.category.BROWSABLE" />
                 <!-- Accepts URIs that begin with "http://www.example.com/test” -->
                 <data android:scheme="http"
                       android:host="www.example.com"
                       android:pathPrefix="/test" />
                 <!-- note that the leading "/" is required for pathPrefix-->
             </intent-filter>
             <intent-filter android:label="@string/filter_view_example_test">
                 <action android:name="android.intent.action.VIEW" />
                 <category android:name="android.intent.category.DEFAULT" />
                 <category android:name="android.intent.category.BROWSABLE" />
                 <!-- Accepts URIs that begin with "example://test” -->
                 <data android:scheme="example"
                       android:host="test" />
             </intent-filter>
         </activity>

`Nota: Los dos filtros de intents solo difieren en el elemento <data>. Si bien es posible incluir múltiples elementos <data> en el mismo filtro, es importante que crees filtros separados cuando tu intención sea declarar URL únicas`

    <intent-filter>
      ...
      <data android:scheme="https" android:host="www.example.com" />
      <data android:scheme="app" android:host="open.my.app" />
    </intent-filter>

Puede parecer que esto solo admite https://www.example.com y app://open.my.app. Sin embargo, también es compatible con app://www.example.com y https://open.my.app

#### Lee datos de intents entrantes

#### Prueba tus vínculos directos

### 2. Verificar Android App Links

Configura tu app a fin de solicitar la verificación de los vínculos de apps. Luego, publica un archivo JSON de Vínculos de recursos digitales en tus sitios web para verificar la propiedad mediante Google Search Console.


## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
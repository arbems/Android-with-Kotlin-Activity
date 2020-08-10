# Android con Kotlin - Activity - Configurar el archivo manifiesto para actividades (Android Manifest)

Código de ejemplo de configuración de archivo de manifiesto para actividades en Android con Kotlin.
               
## Declarar actividades 
Todas las actividades deben estar representadas por elementos <activity> en el archivo de manifiesto.                                                                         
Para usar actividades, debes declararlas, y también declarar algunos de sus atributos, en el manifiesto.

Dentro de <application>:
    
      <manifest ... >
           <application ... >
               <activity android:name=".ExampleActivity" />
               ...
           </application ... >
           ...
      </manifest >
      
Puede contener <intent-filter>, <meta-data> y <layout>:

     <activity ... >
         <intent-filter ... >
         <meta-data ... >
         <layout ... >
     </activity>
     
Actividad principal, que es la primera pantalla que aparece cuando el usuario inicia la app:

     <activity android:name=".MainActivity">
         <intent-filter>
             <action android:name="android.intent.action.MAIN" />

             <category android:name="android.intent.category.LAUNCHER" />
         </intent-filter>
     </activity>
  
`Una vez que publiques tu app, no deberías cambiar los nombres de las actividades. Si lo haces, se pueden romper algunas funcionalidades, como los accesos directos a las apps.`
  
     
####  Atributos de un activity en archivo de manifiesto -  [ver más](https://developer.android.com/guide/topics/manifest/activity-element)


## Declarar filtros de intens en actividades
Configurando los filtros de intents se especifica el tipo de intent al que puede responder la actividad. Estos filtros proporcionan la capacidad de iniciar una actividad de una manera implícita.

La definición de este elemento incluye un elemento action y, de manera opcional, un elemento category o data

* **action** - Agrega una acción a un filtro de intent. Un elemento <intent-filter> debe contener uno o varios elementos <action>. Si no hay elementos <action> en un filtro de intents, el filtro no acepta ningún objeto Intent. ("android.intent.action.")

* **category** - Agrega un nombre de categoría en un filtro de intents. ("android.intent.category.")

* **data** - Agrega una especificación de datos a un filtro de intents. La especificación puede ser un tipo de datos (el atributo mimeType), un URI o ambos.

####
        <activity android:name=".ExampleActivity" android:icon="@drawable/app_icon">
            <intent-filter>
                <!-- especifica que esta actividad envía datos -->
                <action android:name="android.intent.action.SEND" />
                 
                <!-- como DEFAULT permite a la actividad recibir solicitudes de inicio -->
                <category android:name="android.intent.category.DEFAULT" />
                
                <!-- especifica el tipo de datos que puede enviar -->
                <data android:mimeType="text/plain" />
            </intent-filter>
        </activity>
        
Llamada implícita a la actividad:
    
     val sendIntent = Intent().apply {
          action = Intent.ACTION_SEND
          type = "text/plain"
          putExtra(Intent.EXTRA_TEXT, textMessage)
     }
     startActivity(sendIntent)

`Nota: Cuando la IU del sistema le pregunta al usuario qué app debe usar para realizar la tarea, ese es un ejemplo de un filtro de intent.
Las actividades que no quieras que estén disponibles para otras aplicaciones no deben incluir filtros de intents, y puedes iniciarlas por medio de intents explícitos.`

## Declarar permisos en actividades
Puedes usar la etiqueta <activity> del manifiesto para controlar qué apps pueden iniciar una actividad en particular. Una actividad superior no puede iniciar una actividad secundaria, a menos que ambas tengan los mismos permisos en su manifiesto. Si declaras un elemento <uses-permission> para una actividad principal, cada actividad secundaria debe tener un elemento <uses-permission>.
    
Por ejemplo, si tu app quiere usar una app hipotética llamada SocialApp para compartir una publicación en las redes sociales, 
SocialApp debe definir el mismo permiso que la app que la llama:
    
    Social app:
    
        <activity android:name="...."
           android:permission=”com.google.socialapp.permission.SHARE_POST”/>
    
####  
    App que llama:
    
        <manifest>
           <uses-permission android:name="com.google.socialapp.permission.SHARE_POST" />
        </manifest>



## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
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

## Attribution

This code was created by [arbems](https://github.com/arbems) in 2020.
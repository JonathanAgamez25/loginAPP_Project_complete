# LoginApp - Consumo JSON

## Descripcion

Este proyecto corresponde a una aplicacion Android llamada **LoginApp**. La actividad consistio en agregar el consumo de datos JSON desde un webservice externo y mostrar la informacion obtenida dentro de la aplicacion.

El servicio utilizado fue:

```text
https://jsonplaceholder.typicode.com/users
```

La aplicacion consume una lista de usuarios y muestra datos como nombre, correo electronico, ciudad y empresa.

## Cambios realizados

- Se agrego el permiso de internet en `AndroidManifest.xml`.
- Se creo la pantalla `UsersActivity.java`.
- Se creo el layout `activity_users.xml`.
- Se agrego el boton **Ver usuarios JSON** en la pantalla principal.
- Se implemento el consumo JSON usando `HttpURLConnection`.
- Se proceso la respuesta con `JSONArray` y `JSONObject`.
- Se genero una captura de evidencia de la pantalla funcionando.
- Se creo el documento del taller en formato Word con estructura academica y referencias APA.

## Como probar la aplicacion

1. Abrir el proyecto `LoginApp` en Android Studio.
2. Esperar a que Gradle sincronice el proyecto.
3. Conectar un celular Android o iniciar un emulador.
4. Ejecutar la aplicacion.
5. En la pantalla principal, tocar el boton **Ver usuarios JSON**.
6. Verificar que aparezca la pantalla **Usuarios desde JSON**.
7. Confirmar que se cargue la lista de usuarios desde JSONPlaceholder.

## Archivos importantes

```text
app/src/main/java/com/example/loginapp/UsersActivity.java
app/src/main/res/layout/activity_users.xml
app/src/main/res/layout/activity_main.xml
app/src/main/AndroidManifest.xml
Taller_Consumo_JSON_LoginApp.docx
captura_usuarios_json_apa.png
```

## Evidencia generada

La captura usada como evidencia del funcionamiento se encuentra en:

```text
C:\Users\Usuario\Desktop\sistema_Licencias\LoginApp\captura_usuarios_json_apa.png
```

El taller en Word se encuentra en:

```text
C:\Users\Usuario\Desktop\sistema_Licencias\LoginApp\Taller_Consumo_JSON_LoginApp.docx
```

Tambien se copio una version lista para cargar en:

```text
D:\Descargas\Taller_Consumo_JSON_LoginApp.docx
```

## Resumen tecnico

La pantalla `UsersActivity` ejecuta una peticion HTTP tipo `GET` al endpoint de usuarios. La respuesta recibida es un arreglo JSON. Luego, la aplicacion recorre cada objeto del arreglo y extrae la informacion necesaria para mostrarla en pantalla.

La operacion de red se ejecuta en un hilo secundario para evitar bloquear la interfaz de usuario. Despues de recibir y procesar la respuesta, la interfaz se actualiza desde el hilo principal usando `Handler` y `Looper`.

## Referencias

Android Developers. (s. f.). *App manifest overview*. https://developer.android.com/guide/topics/manifest/manifest-intro

Android Developers. (s. f.). *HttpURLConnection*. https://developer.android.com/reference/java/net/HttpURLConnection

JSONPlaceholder. (s. f.). *JSONPlaceholder: Free fake and reliable API for testing and prototyping*. https://jsonplaceholder.typicode.com/

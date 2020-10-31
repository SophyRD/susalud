### Prerequisitos

npm 6.14.8 o superior
nodejs v 10 o superior

##Instalación

Para configurar la base de datos ir a la carpeta src/main/resources/config dentro del proyecto y copiar el archivo _application-dev.yml.example_, luego, cambiarle el nombre a _application-dev.yml_ y en la linea 48, 49 y 50 del archivo, configurar el nombre de la base de datos sql server, el usuario y la contraseña.

Por medio de la linea de comandos ejecutar lo siguiente:

```
npm install
```

######Nota: Si es Windows, configurar las variables de entorno de nodejs y npm.

##Ejecución

Para inicializar el backend, en Windows hacer doble clic sobre el archivo mvnw.cmd y en linux ejecutar por terminal ./mvnw

Para inicializar el frontend con _hot reload_ por linea de comandos ejecutar _npm start_

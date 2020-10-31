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

###Comandos básicos de git

1. Descargar los cambios realizados por otros compañeros

```
git pull origin master
```

2. Crear una nueva rama

```
git checkout -b nombre_rama
```

3. Ver los archivos que tienen cambios y estan pendientes por subir

```
git status
```

4. Agregar todos los archivos con cambios a un commit

```
git add .
```

5. Agregar algunos archivos o carpetas a un commit

```
git add ubicacion/carpeta/archivo.txt
```

6. Hacer un commit

```
git commit -m "escribir lo que se hizo"
```

7. ¿En qué rama estoy?

```
git branch
```

8. Subir los cambios a la rama que se creó en el paso 2

```
git push origin nombre_rama
```

###Proceso de desarrollo

1. ir a la rama master

```
git checkout master
```

2. Descargar loss cambios hechos por otros

```
git pull origin master
```

3. Crear una nueva rama, desde allí se realizarán los cambios

```
git checkout -b ramaConCualquierNombre
```

4. Una vez finalizado el desarrollo agregar los archivos y hacer un commit

```
git add .
git commit -m "escribir lo que se realizó"
```

5. Hacer push a la rama para subir los cambios a github

```
git push origin ramaConCualquierNombre
```

6. Ir al proyecto https://github.com/santo52/susalud y hacer un pull request a master

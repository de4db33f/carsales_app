# Carsales Test App

**Importante**: La aplicación se probó en un dispositivo Samsung físico con Android 12 y en emulador con API 30

## Librerías usadas
 - Retrofit para consumo de API
 - Coroutines para ejecución de código asíncrono
 - ViewModel como patrón de diseño
 - DataBinding para clases enlazadas con los layouts
 - LiveData para datos observables

## Sobre el proyecto
 - Hecho usando el patrón de diseño MVVM
 - La estructura del proyecto está hecha para mantener separada la lógica del módulo principal con clases de uso común.
 - En el versionamiento del código hay 2 branch `main` y `dev`, la segunda branch fue usada para desarrollar y commitear cambios que se hacian durante el desarrollo de la app.
 - Se entiende que `main` está protegida de push directos, por lo que cualquier cambio es mediante un Pull Request

### Consideraciones
 - Hay un comentario tipo FIXME con un problema que se solucionó provisoriamente, se entiende que no es una solución óptima.
 - Se agregó un Custom Splash Screen para cumplir con el requisito en dispositivos más antiguos. De todas formas se implementó la forma recomendada por la documentación aunque la imagen se ve cortada por sus dimensiones.
 - Se usó la font por defecto, ya que no se especificó cual era la que se debía usar.
 - El diseño del botón del HOME y quizás los colores de la app no son exactamente los mismos que se aprecian en el diseño.

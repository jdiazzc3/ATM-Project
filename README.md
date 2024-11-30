# Proyecto de Automatización de Pruebas con Selenium y TestNG

Este proyecto está diseñado para automatizar pruebas de una aplicación web utilizando Selenium WebDriver y TestNG. A continuación se describe la estructura de directorios del proyecto y la finalidad de cada uno de los tests.

## Estructura de Directorios
- pom.xml: Archivo de configuración de Maven
- src:
  - main:
    - java:
      - com/co/diaz/pages:
        - CheckoutPage.java: Clase de la página de checkout
        - HomePage.java: Clase de la página principal
        - RegisterPage.java: Clase de la página de registro
        - LoginPage.java: Clase de la página de inicio de sesión
    - resources:
      - chromedriver.exe: Driver de Chrome para pruebas automáticas
  - test:
    - java:
      - com/co/diaz:
        - CheckoutTest.java: Clase de pruebas para el checkout
        - LoginTest.java: Clase de pruebas para el login
        - RegisterTest.java: Clase de pruebas para el registro
    - resources:
      - testdata.xlsx: Archivo con datos de prueba
- README.md: Archivo de documentación principal del proyecto
## Descripción de Directorios y Archivos

- **pom.xml**: Archivo de configuración de Maven que gestiona las dependencias del proyecto.

- **src/main/java/com/co/diaz/pages**: Contiene las clases de página que representan las diferentes páginas de la aplicación web.
    - **CheckoutPage.java**: Clase que contiene los métodos para interactuar con la página de checkout.
    - **HomePage.java**: Clase que contiene los métodos para interactuar con la página de inicio.
    - **RegisterPage.java**: Clase que contiene los métodos para interactuar con la página de registro.
    - **LoginPage.java**: Clase que contiene los métodos para interactuar con la página de login.

- **src/main/resources**: Contiene recursos necesarios para la ejecución del proyecto.
    - **chromedriver.exe**: El ejecutable del driver de Chrome necesario para ejecutar las pruebas en el navegador Chrome.

- **src/test/java/com/co/diaz**: Contiene las clases de prueba que ejecutan los tests automatizados.
    - **CheckoutTest.java**: Clase que contiene los tests para verificar el flujo de compra, incluyendo la búsqueda de productos, agregar productos al carrito y completar el checkout.
    - **LoginTest.java**: Clase que contiene los tests para verificar el proceso de login utilizando diferentes datos de prueba.

- **src/test/resources**: Contiene recursos necesarios para la ejecución de los tests.
    - **testdata.xlsx**: Archivo Excel que contiene los datos de prueba utilizados en los tests.

## Finalidad de los Tests

### CheckoutTest.java
Este test verifica el flujo completo de compra en la aplicación web. Incluye los siguientes pasos:
1. Verificar si el usuario está logueado.
2. Buscar y agregar productos al carrito.
3. Completar el proceso de checkout.

### RegisterTest.java
Este test verifica el proceso de registro en la aplicación web utilizando diferentes combinaciones de datos de prueba. Incluye los siguientes pasos:
1. Navegar a la página de registro.
2. Llenar el formulario de registro con diferentes datos de prueba.
3. Verificar que el registro se realiza correctamente.

### LoginTest.java
Este test verifica el proceso de login en la aplicación web utilizando diferentes combinaciones de datos de prueba. Incluye los siguientes pasos:
1. Navegar a la página de login.
2. Llenar el formulario de login con diferentes datos de prueba.
3. Verificar que el login se realiza correctamente.

## Cómo Ejecutar los Tests

1. Clonar el repositorio.
2. Configurar el driver de Chrome en `src/main/resources/chromedriver.exe`.
3. Ejecutar los tests desde el IDE o desde la línea de comandos utilizando Maven.

```
mvn test
```
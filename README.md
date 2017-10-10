# Brastlewarktown

Esta es la resolución al ejercicio que se enuncia mas abajo.

## Solución propuesta

Para abordar este desarrollo he optado por usar CLEAN implementando el patron MVP y con una estructura de paquetes separada por componentes.

Para el tratamiento de datos he planteado una llamada inicial al servicio que provee los datos para despues almacenar estos en base de datos. De esta manera dispondria de una manera rapida de acceder y filtrar la informacion y a la larga supondria una mejora significativa del rendimiento de la app.

Tambien he tenido en cuenta que cada via de comunicacion (Retrofit, Realm, etc) tenga su propio modelo de datos que posteriormente sera convertido al modelo de la aplicación buscando así preservar siempre la capa de negocio de nuestra herramienta. Y dotandola de robustez ante posibles cambios de fuentes de datos.

### Componentes

Con la estructura por componentes busco que dentro de cada paquete podamos encontrar todo el codigo relacionado con el elemento en cuestión. De esta forma la estructura de proyecto queda mas modularizada. Dentro de cada componente podremos encontrar tanto las interfaces como la implementacion de su vista, presentador e interactor. Ademas de las clases que necesite la vista como adapters y demases.

Los paquetes que se han implementado han sido los siguientes:

 * personlist: Este componente se utiliza para mostar un listado de personas, ya sea de forma completa o filtrada

 * persondetail: Este componente se utiliza para mostrar el detalle de una persona concreta que previamente ha sido seleccionada en la lista, como elemento extra he añadido la navegacion directa entre amigos, de esta forma puede accederse rapidamente a la vista detalle de un amigo sin tener que recurrir al filtro.

 * personfilter: En este componente podremos filtrar por varios atributos de una persona y posteriormente el resultado sera mostrado en forma de lista.

### Librerias utilizadas

 * Retrofit para realizar llamadas a servicios

 * Realm como base de datos local

 * Picasso para el tratamiento de las imagenes

 * Butterknife para la inyeccion de vistas

### Cosas que se han quedado en el tintero

Por cuestiones de tiempo no he podido implementar todo lo que me gustaria, es por eso que dejo aqui estas anotaciones para mas adelante poder implementar estas mejoras que haran que la aplicación sea mas robusta

 * Inyeccion de dependencias con Dagger

 * Test
 
 * Rediseño de pantallas


## Enunciado del ejercicio

Our team here at AXA is playing a fantasy role-playing game and every time the heroes they play arrive at a town, they have the issue they don't know the local population and what they can do to help them on their adventures.

This is one of these times, our heroes just arrived at a Gnome town called Brastlewark. To facilitate trade with the local population they need an easy way to browse all the inhabitants details. We've found a server providing all the census data of Brastlewark at the following address:

https://raw.githubusercontent.com/rrafols/mobile_test/master/data.json

Gnomes in this town are not really social because they have too much work to do. That's the reason they can have more than one job and might have few or even no friends at all. They also appreciate their privacy so they've used some random images from internet, not specifically optimized for mobile devices, for their profile. (They are very modern in some aspects and they have smartphones and access to internet for instance).

Please write an Android / iOS application to help our team browse and be able to see the details of those inhabitants.

Specifications:
  - Retrieve data from the URL provided
  - Show this data in the most user-friendly way you could think. Keep in mind our heroes will be quite busy dealing with Orcs, so apps have to be really simple and easy to use. At least would be good to quickly browse (and even filter) all the individuals and be able to see the details of each one.
  - Document all libraries used and explain the reason you’ve used that library.

Bonus:
  - Be creative!
  - UI must not blocked by network connections or long operations
  - Images coming from network cached to improve performance
  - Error handling
  - Avoid using third party libraries for basic functionality (we want to see you know the basics!)
  - Use third party libraries for extended functionality
  - Snappiness & responsiveness over sluggishness & idleness
  - Determine gender of gnomes (just joking on this one but feel free to make your guess)

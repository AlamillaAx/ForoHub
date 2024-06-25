<div>
  <h1 align="center">
      <img src="https://github.com/AlamillaAx/ForoHub/assets/86985427/503b1e3b-36ce-4e0f-92f3-25b3bae1ffbf" alt="ForoHub">
  </h1>

</div>
<div>
  <p align="left">
   <img src="https://img.shields.io/badge/STATUS-EN%20DESAROLLO-green">
   <img src="https://img.shields.io/github/stars/AlamillaAx/MoneyMorpher">
   </p>
</div>

## Indice

* [Título e imagen de portada](#Título-e-imagen-de-portada)

* [Introduccion](##Introduccion)

## Introduccion

<p>Desarrollo de una API-REST para la creacion de un foro, este es un challengue propuesto por parte de Alura LATAM y forma parte de la formacion en desarrollador de Back-End.</p>
<p></p>

## :hammer:Funcionalidades del proyecto

* ### Estructura de base de datos

<p>Para este proyecto se siguio la maquetacion de estructura de datos relacional propuesta por Alura LATAM, que consistia en las siguientes relaciones:</p>

<p align="center"><img src="https://github.com/AlamillaAx/ForoHub/assets/86985427/2310bc96-29bd-4404-8c85-7c37fcc6da34"></p>

1. ### Funcionalidades obligatorias.
- #### <p> Las pruebas de estas funcionalidades se realizaron con ayuda de Insomnia para simular las solicitudes que un usuario podria hacer desde el Frond-End al Back-End.</p>
   - #### Registro de un nuevo topico (pregunta).
##### La solicitud para la creacion de una nueva pregunta se debe enviar en un  JSON como el que se muestra a continuacion.
```
{
	"titulo":"Titulo ejemplo",
	"mensaje":"Mensaje ejemplo",
	"fechaCreacion":"2024-10-10T10:30",
	"autor_id":"2",
	"curso_id":"7"
}

```
#### Una vez recibido este JSON el servicio de topicos realizara las validaciones pertinentes. Validara que exista un curso y un usuario con los id's proporcionados, la validacion para que el titulo y mensaje no esten repetidos se realizaran directamente en al intentar agregar a la base de datos.
  - #### Mostrar todos los topicos registrados.
#### Para obtener todos las preguntas registradas en formato JSON solo basta con acceder al Mappeo asignado para esta funcionalidad: 
```
@GetMapping("/todos")
```
#### Esta ruta mostrara de vuelta un JSON parecido al siguiente:
```
	"totalElements": 2,
	"totalPages": 1,
	"first": true,
	"last": true,
	"size": 20,
	"content": [
		{
			"id": 2,
			"titulo": "Titulo1",
			"mensaje": "Mensaje1"
		},
		{
			"id": 5,
			"titulo": "Titulo2",
			"mensaje": "Mensaje2"
		}
	],
	"number": 0,
	"sort": {
		"unsorted": true,
		"empty": true,
		"sorted": false
	},
	"numberOfElements": 2,
	"pageable": {
		"pageNumber": 0,
		"pageSize": 20,
		"sort": {
			"unsorted": true,
			"empty": true,
			"sorted": false
		},
		"offset": 0,
		"unpaged": false,
		"paged": true
	},
	"empty": false
}
```
  - #### Actualizacion de un topico.
#### La actualizacion de un topico se hace por medio de su id, se valida de acuerdo las reglas de negocio que exista una pregunta con el id proporcionado para luego permitir la modificacion del titulo, mensaje o curso a traves de una solicitud del tipo PUT con un JSON en el cuerpo de la misma.
```
{
	"titulo":"modificacion del titulo",
	"mensaje":"modificacion del mensaje",
	"curso_id":"modificacion del curso"
}
```
#### Al momento de realizar las actualizaciones tambien valida que las atributos no sean nulos o esten vacios antes de realizar la actualizacion.
  - #### Eliminacion un topico
#### En el caso de la eliminacion de una pregunta de igual forma se realiza por medio de id, recibiendo en la url la variable id, la cual servira para realizar una validacion que comprobara que exista una pregunta con el id proporcioando. Unicamente se debera acceder siguiente url sustityendo el {id_pregunta} por el id de la pregunta que desea eliminar.
```
http://localhost:8080/topicos/eliminar/{id_pregunta}
```
#### Una vez que se valide que la pregunta existe, se procedera con el proceso de eliminacion. El objeto "Topico" esta configurado para actuar en cascada, lo que significa que al eliminar el topico o pregunta, tambien se eliminaran todas las respuesta relacionadas con este registro.
2. ### Funcionalidades propuestas y opcionales.
   - #### Registro de una nueva respuesta.
#### El registro de una nueva respuesta es muy parecido al caso para agregar una nueva pregunta. Se obtendra un JSON a traves del cuerpo de la solicitud como el que se muestra a continuacion:

```
{
"mensaje":"cuerpo de la respuesta",
"topico_id":"3",
"fechaCreacion": "2024-10-10T10:36",
"autor_id":"3"
}
```

#### En este caso el servicio para preguntas validara que exista una pregunta con el id proporcionado, un autor y ademas que el mensaje y fecha no se encuentren vacios.
   - #### Actualizacion de respuesta.
#### Para la actualizacion de una respuesta, se decidio unicamente permitir la actualizacion del mensaje, obteniendo unicamente los siguientes datos a traves del cuerpo de la solicitud:
```
{
"id": "1",
"mensaje": "nuevo cuerpo de la respuesta",
"solucion": false
}
```
#### La validacion para actualizar se realizara por medio del id de la pregunta, se validara que esta exista y se permtira actualizar el mensaje, ademas se implemento la logica para que se marque la respuesta como la solucion a la pregunta, en cuanto este valor ingrese como "true" a traves de la edicion de la respuesta tambien actualizara el status de la pregunta a "false" lo cual daria opcion a poder filtrar entre preguntas activas e inactivas.
   - #### Eliminacion de respuesta.
#### La eliminacion de una respuesta al igual que la actualizacion se realiza validando que exista una respuesta con el id proporcionado a traves de la url de la solicitud:
```
http://localhost:8080/respuestas/eliminar/{id_pregunta}
```
   - #### Registro de usuario.
#### En lo visto a traves del curso no se vio alguna logica que permitiera agregar nuevos usuarios, sino que todas las pruebas se hacian con uno solo agregado directamente a la base de datos. Con fin de probar los conocimientos adquiridos, decidi implementar la logica detras del objeto "Usuario" para poder crear un registro para nuevos usuarios. Esto es posible  a traves del siguiente mapeo:
```
http://localhost:8080/usuarios/registro
```
#### A traves de la solicitud de este mapeo obtendremos el siguiente cuerpo:
```
{
	"nombre":"Nuevo Usuario",
	"email":"usuariox@gmail.com",
	"contrasena": "contrasenax"
}
```
#### Con estos datos declarados como obligatorios y en el caso del email como unico, se crea un objeto de tipo "Usuario" el cual a su vez creara un objeto de tipo "Perfil" con el nombre proporcionado. De manera interna dentro de la creacion del usuario se realiza la encriptacion de la contrasena al tipo Bcrypt y de esta forma se agrega a la base de datos.

- #### Login de usuario para obtener token de seguridad (WJT).
#### La siguiente ruta permite el login de un usuario enviando los datos de inicio de sesion, para este proyecto decidi que los datos de inicio de sesion fueran el email y contrasena del usuario:
```
http://localhost:8080/login

{
	"email": "usuario@gmail.com",
	"clave":"contrasenax"
}
```
#### El servicio de autenticacion realizara las validaciones comprobando que se trate de un usuario registrado en la base de datos y convertira el valor de la clave ingresado en el login a un tipo encriptrado en Bcrypt para poder compararlo con la contrasena guardado en la base de datos.
#### Una vez autenticado el servicio de autenticacion devolvera un token que servira para poder hacer uso de todas las rutas de topicos y preguntas. Las rutas excluidas del filtro de seguridad y que seran siempre accesibles sin necesidad de autenticarse son: Registro de usuario, login, actualizacion de usuario.

   - #### Actualizacion de un usuario.
#### EN CONSTRUCCION
   - #### Eliminacion logica de un usuario.
#### EN CONSTRUCCION


## Tecnologias utilizadas
* JAVA
* SPRING FRAMEWORK
* MAVEN
## Autor

| <img src="https://github.com/AlamillaAx/MoneyMorpher/assets/86985427/f3a45610-9836-4483-bfdb-3731c69d5c6f" width=115><br><sub>Axel Alamilla</sub>|
| ------------- |

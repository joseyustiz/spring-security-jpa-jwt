# Rest API for User Sign up
This project is an example about how to use JPA to store credentials of a User and use JWT for authorization; I implemented a Layer Architecture, basic design patterns like Builder, and I used cutting-edge technology such as:
 * Java 8
 * Spring Boot
 * Spring MVC
 * Bean Validation
 * Spring Data JPA
 * Hibernate
 * Spring Security
 * JJWT
 * Lombok
 * JUnit 5
 * Mockito
 * H2
 * Gradle 
 
The application has the following two endpoints:

## [/user/signup](http://localhost:8080/user/signup)
Public endpoint to register an user; it stores contact information and credentials sent as request body.

**HTTP Method:** POST

**Endpoint:** http://localhost:8080/user/signup

Attribute of the request:

| Attribute | Required |Type | Note | Example |
------------|----------|-----|------|--------|
| name  | Yes | String | Requires at least 2 characters |Jose Yustiz |
| email | Yes | String | Requires a valid email format | jose@joseyustiz.com |
| password | Yes | String | Requires one lower chase, one upper case characters, and two digits | Spring19 |
| phones | No | Object | |``` "phones": [{"number": "1234567","citycode": "1","countrycode": "57"}] ``` |

Phone Object:

| Attribute | Required |Type | Note | Example |
------------|----------|------|-----|--------|
| number | Yes | String | Accepts only 7 digits | 123456 |
| citycode | Yes | String | Accepts between 1 to 3 digits | 9 |
| contrycode | No | String | Accepts between 1 to 6 digits | 56 |

### Valid request
```json
{
	"name": "Jose Yustiz",
	"email": "jose@joseyustiz.com",
	"password": "Spring1",
	"phones": [
		{
			"number": "1234567",
			"citycode": "1",
			"countrycode": "56"
		} 
	]
}
```
### Valid Response
```json
{
    "id": "253b9787-1d9e-41d0-b9de-fc905f10e955",
    "created": "2019-11-04T04:31:28.618+0000",
    "modified": "2019-11-04T04:31:28.618+0000",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb3NlQGpvc2V5dXN0aXouY29tIiwiZXhwIjoxNTcyODc3ODg4LCJpYXQiOjE1NzI4NDE4ODh9.FAKC2sJM8Z_AhibHKbItU8umsvTHNCOfuZYpZ5tdQc8",
    "last_login": "2019-11-04T04:31:28.618+0000",
    "isactive": true
}
```
**NOTE:** the JWT token has a TTL of 10 hours

### Response for Invalid Request: Email Already Registered
```json
{
    "status": "BAD_REQUEST",
    "errors": [
        "email: El correo ya registrado"
    ]
}
```
### Response for Invalid Request: Attributes Bad Format
```json
{
    "status": "BAD_REQUEST",
    "errors": [
        "email: Formato inválido",
        "phones[0].number: Formato inválido",
        "name: Debe tener al menos 2 caracteres",
        "phones[0].countryCode: Formato inválido",
        "password: No cumple la complejidad mínima",
        "phones[0].cityCode: Formato inválido"
    ]
}
```

## [/user/info](http://localhost:8080/user/info) 
Protected endpoint that is used to test authorization with with a valid JWT of a registered user; it returns contact info of the User stored in the database.

**HTTP Method:** GET

**Endpoint:** http://localhost:8080/user/info

Request Variable:

| Attribute | Required |Type | Note | Example |
------------|----------|-----|------|--------|
| email | Yes | String | email of a registered user | jose@joseyustiz.com |

Request Header:

| Attribute | Required |  Note | Example |
------------|----------|------|--------|
| Authorization | Yes |  Bearer keyword, a white space and the JWT token generated during the sign up  | Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb3NlQGpvc2V5dXN0aXouY29tIiwiZXhwIjoxNTcyODc4OTg4LCJpYXQiOjE1NzI4NDI5ODh9.W1eOi1hRnT-ToycRTw70mHV3Lhy2mwj9XuDNjvk4p9Y |

### Valid Response
```json
{
    "name": "Jose Yustiz",
    "email": "jose@joseyustiz.com",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "countrycode": "56"
        }
    ]
}
```

### Response for Invalid Request: Missing Header
```json
{
    "timestamp": "2019-11-04T04:58:28.925+0000",
    "status": 403,
    "error": "Forbidden",
    "message": "Access Denied",
    "path": "/user/info"
}
```

### Response for Invalid Request: Email parameter is missing
```json
{
    "status": "BAD_REQUEST",
    "errors": [
        "email parameter is missing"
    ]
}
```
### Response for Invalid Request: Email is not found
```json
{
    "status": "BAD_REQUEST",
    "errors": [
        "email: otro@joseyustiz.com no pertenece a un usuario registrado"
    ]
}
```

## Classes Diagram

### Project Package View
![Package View](doc/Package-springsecurityjpajwt.jpg)

### Web Package Expanded View
![Web Package View](doc/springsecurityjpajwt-web.jpg)

### Service Package Expanded View
![Service Package View](doc/springsecurityjpajwt-service.jpg)

### Persistency Package Expanded View
![Persistency Package View](doc/springsecurityjpajwt-persistency.jpg)

### Model Package Expanded View
![Model Package View](doc/springsecurityjpajwt-model.jpg)

## Sequence Diagram
### Sign Up Diagram
![Model Package View](doc/sequenceDiagram-signUp.jpg)

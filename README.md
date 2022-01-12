<div align="center">
  <a href="https://plusauth.com/">
    <img src="https://docs.plusauth.com/images/pa-white.svg" alt="Logo" width="320" height="72" >
  </a>
</div>

<h1 align="center">PlusAuth Spring Boot Backend Starter Project</h1>

 <p align="center">
    Simple Spring Boot MVC project demonstrates basic API authorization flow with PlusAuth
    <br />
    <br />
    <a href="https://docs.plusauth.com/quickStart/server-to-server/java/spring-boot" target="_blank"><strong>Explore the PlusAuth Spring Boot API docs »</strong></a>
</p>

<details>
  <summary>Table of Contents</summary>
    <li><a href="#about-the-project">About The Project</a></li>
    <li><a href="#prerequisites">Prerequisites</a></li>
    <li><a href="#getting-started">Getting Started</a></li>
    <li><a href="#calling-endpoints">Calling Endpoints</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#what-is-plusauth">What is PlusAuth</a></li>
 </ol>
</details>

---

## About The Project

This is a very simple Spring Boot project demonstrating basic API authorization flows with PlusAuth. To keep things simple we used Spring Boot as the server framework and spring-boot-starter-oauth2-resource-server for authorization.


## Prerequisites
Before running the project, you must first follow these steps:

### Create PlusAuth Account

- Create a PlusAuth account and a tenant at https://dashboard.plusauth.com
- Navigate to `Clients` tab and create a client of type `Server to Server`.


### Create PlusAuth API
- Go to `Api's` page and create a new Api. 
- Navigate to `Permissions` tab and create permissions below for your Api.

  - `users:read`
  - `users:write`
  - `users:update`
  - `users:delete`

- Finally navigate to `Authroized Clients` tab, authorize your `client` and grant the permissions.

Finally write down your `jwk-set-uri` and api `audience` for server configuration 

## Getting Started

Configure `application.yml` in `/src/main/resources/` using your `tenant name`, `jwk-set-uri` and api `audience` fields.

Install dependencies: 

      mvn install


Start the server:

      mvn spring-boot:run
    

The example is hosted at http://localhost:8080/

## Calling Endpoints

All endpoints are secured and requires `access token` in request header. You can make requests to following endpoints: 

- **GET** http://localhost:8080/users
- **POST** http://localhost:8080/users
- **PUT** http://localhost:8080/users
- **DELETE** http://localhost:8080/users

Obtain an access token using command line or a REST Client with your PlusAuth Client and API properties.

```bash
# bash

curl --request POST \
  --url 'https://<YOUR_TANENT_ID>.plusauth.com/oauth2/token' \
  --header 'content-type: application/x-www-form-urlencoded' \
  --data 'grant_type=client_credentials' \
  --data 'client_id=<YOUR_CLIENT_ID>' \
  --data 'client_secret=<YOUR_CLIENT_SECRET>' \
  --data 'audience=<YOUR_AUDIENCE>' \
  --data 'scope=users:read users:write users:update users:delete'
  
```

Create a `GET` request and pass the access token you obtained as `Authorization` header. You will get following response:

```bash
# bash

> curl -i http://localhost:8080/users \
-H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6ImF0K2p3dCIsImtpZCI6Inh4T3l2R0hWV3dCIsImtpZ..."
HTTP/1.1 200
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
Content-Type: text/plain;charset=UTF-8
Content-Length: 14

All Users List
```

## License

This project is licensed under the MIT license. See the [LICENSE](LICENSE) file for more info.

## What is PlusAuth

PlusAuth helps to individuals, team and organizations for implementing authorization and authentication system in a secure, flexible and easy way.

<a href="https://plusauth.com/" target="_blank"><strong>Explore the PlusAuth Website »</strong></a>

<a href="https://docs.plusauth.com/" target="_blank"><strong>Explore the PlusAuth Docs »</strong></a>

<a href="https://forum.plusauth.com/" target="_blank"><strong>Explore the PlusAuth Forum »</strong></a>

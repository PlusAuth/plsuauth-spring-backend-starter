<h1 align="center">PlusAuth Spring Boot Backend Starter Project</h1>

 <p align="center">
    Simple Spring Boot MVC project demonstrates basic API authorization flow with PlusAuth
    <br />
    <br />
    <a href="https://docs.plusauth.com/"><strong>Explore the PlusAuth Spring Boot API docs »</strong></a>
</p>

<details>
  <summary>Table of Contents</summary>
    <li><a href="#about-the-project">About The Project</a></li>
    <li><a href="#prerequisites">Prerequisites</a></li>
    <li><a href="#getting-started">Getting Started</a></li>
    <li><a href="#license">License</a></li>
  </ol>
</details>

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

Start the server:

      mvn spring-boot:run
    

The example is hosted at http://localhost:8080/

## License

This project is licensed under the MIT license. See the [LICENSE](LICENSE) file for more info.

## What is PlusAuth

PlusAuth helps to individuals, team and organizations for implementing authorization and authentication system in a secure, flexible and easy way.

<a href="https://docs.plusauth.com/"><strong>Explore the PlusAuth Docs »</strong></a>

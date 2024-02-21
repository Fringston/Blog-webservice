# Blog-webservice
## Description

This project works as a backend server for a blog-platform. 
It's a Spring Boot application that uses Spring Security to authenticate users and OAuth2 to authorize them.
The application is connected to a MySQL database and uses Spring Data JPA to access the data.

The application and the database are hosted on a AWS EC2-server.

The application will automatically create an admin account if it does not already exist. 
The username is admin and the password is password.

API-documentation can be found here: 

---

# Functionality

## Functionality
### User functionality
**All users can:**
- Register an account.
- Login to their account.

**Logged-in users can:**
- Create blog-posts.
- Read blog-posts.
- Update their own blog-posts.
- Delete their own blog-posts.
- Delete their own account.


### Admin functionality
**Admins users can:**
- Do everything a regular user can do. + 
- See all users

---

# Endpoints

## User Endpoints:

### GET /user
- Returns a list of all users.

### GET /user/{id}
- Returns a user with the specified id.

### POST /user/register
- Adds a new user to the database.

### POST /user/login
- Logs in a user.

### DELETE /user/{id}
- Deletes a user with the specified id.

---

## Post Endpoints:

### GET /post
- Returns a list of all posts.

### GET /post/{id}
- Returns a post with the specified id.

### POST /post
- Adds a new post to the database.

### PUT /post/{id}
- Updates a post with the specified id.

### DELETE /post/{id}
- Deletes a post with the specified id.

---

## Installation

Before you can start this project, you must follow these steps:

- Download and install IntelliJ IDEA or your preferred IDE.
- Download and install MySQL and MySQL Workbench.
- Clone this repository to your local machine.

---

## Usage

### 1. Set up the MySQL database:
- Open MySQL Workbench.
- Connect to your MySQL server.
- Create a new schema for the application. You can do this by clicking on the "Create a new schema in the connected server" button, entering a name for the schema, and clicking "Apply".
- Ensure that the *schema name*, *username*, and *password* in the application.properties file match your MySQL setup.

### 2. Start the Blog-webservice application:

If you want to send requests to http://localhost:5000 you can start the application by running the BlogWebserviceApplication class in your IDE.
But if you want to send requests to the AWS EC2-server, you don't need to start the application, it's already running on the server.

### 3. Send requests to the server from Postman for example:

You can send request either to http://localhost:5000 or the AWS EC2-server at: http://ec2-3-21-233-1.us-east-2.compute.amazonaws.com:5000
For information on what endpoints are available, and how the request should look like, you can use the Swagger UI at:
http://localhost:5000/swagger-ui.html

---

## Testing

The application uses JUnit 5 and Mockito for testing. The tests can be found in the *src/test* folder.


---

## Dependencies:

- Spring Web
- Spring DevTools
- Spring Security
- Oauth2 Resource Server
- Lombok
- Spring Data JPA
- MySQL Connector
- JUnit Jupiter
- Mockito
- Swagger

---

## CI/CD
### Github Actions
The project uses Github Actions for continuous integration. The workflow can be found in the .github/workflows folder.
Each time a push is made to the dev branch, the workflow will run the tests and build the application.

### AWS Pipeline
The project also uses AWS Pipeline for continuous deployment.
Each time a push is made to the main branch, the pipeline will deploy the application to the AWS EC2-server.
The pipeline consists of 3 stages:
- Source (Source: Github)
- Build (Build: AWS CodeBuild)
- Deploy (Deploy: AWS Beanstalk)



---

## License

**[MIT License](https://choosealicense.com/licenses/mit/)**

---
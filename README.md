# Blog-webservice

## Description

This project works as a backend server for a blog-platform. It's a Spring Boot application.
The application is connected to a MySQL database and uses Spring Data JPA to access the data.

The application and the database are hosted on a AWS EC2-server.


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

### 2. Start the WebShopService application:


### 3. Start the client application:


---

## Testing

The application uses JUnit 5 and Mockito for testing. The tests can be found in the *src/test* folder.

---

## Dependencies:

- Spring Web
- Lombok
- Spring Data JPA
- MySQL 
- JUnit 5
- Mockito

---

## License

**[MIT License](https://choosealicense.com/licenses/mit/)**

---
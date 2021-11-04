# Java Pet Project Library with Spring Boot 🍃

This repository contains examples of how to build a Java Pet Project with Spring Boot, Thymeleaf, Bootstrap, database Posgre and software project management Maven.

This project has examples in it:

1. Сreation of detailed posts of authors and their books with the ability to edit.
2. Open collection of all authors and their works.
3. Registration and authorization of the user on the site.
4. User profile.
5. Administrative panel for managing user roles.

The following technologies were used in the project:

1. Read [Securing a Web Application](https://spring.io/guides/gs/securing-web/), to learn about creating and configuring registration and authorization in the application.
2. Read [Spring Email](https://www.baeldung.com/spring-email), to learn about send emails from a Spring Boot application.
3. Bootstrap [Quick start](https://getbootstrap.com/docs/4.4/getting-started/introduction/), get started with Bootstrap, the world’s most popular framework for building responsive sites.
4. Read [Uploading Files](https://spring.io/guides/gs/uploading-files/), this guide walks you through the process  can receive HTTP multi-part file uploads.


# Prerequisites: Java 11

## Spring Boot Library

To install this example, run the following commands:

```bash
git clone https://github.com/sanekpreme/library.git
cd java-project-examples/spring-boot-library
```
The project is pre-configured for authorized users, so you need to create a profile on the application site and activate it using a letter sent to the mail. To access all the functions of the application, you must have administrator access rights, to do this, simply go to the administration tab and grant the rights to the required user.

### Web Application Configuration

Go to the application properties file and make changes to it according to your settings.

```properties
spring.datasource.url=jdbc:postgresql:your-path-to-db
spring.datasource.username=some-username
spring.datasource.password=some-password

upload.path=path-for-save-images

spring.mail.host=smtp.gmail.com (if you are using a different domain then change this setting)
spring.mail.username=some@gmail.com (mail from which messages will be sent to activate the user account)
spring.mail.password=some-password
```

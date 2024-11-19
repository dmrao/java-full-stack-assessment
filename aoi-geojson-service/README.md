
# Project Overview
This project is a Spring Boot application that processes GeoJSON Areas of Interest (AoI) and generates footprints for the specified AoI. It includes an interactive map using Leaflet.js and provides a secure REST API, protected by Keycloak for OAuth-based authentication.

# Features

## REST API:
Processes GeoJSON input for AoI and returns generated footprints.
Includes Swagger documentation for API exploration.

## Map-based Client using Leaflet.js:
- Displays the AoI and generated footprints on an interactive map using Leaflet.js.
- Has a login page where you enter the username and password configured in keyCloak
- Can be accessed using the base url : http://<host:port>/ ( eg: http://localhost:8080/ if tomcat is on 8080)

## Security:

Secured using Keycloak with OAuth2.0 and Bearer Tokens.

## Swagger UI:

Interactive API documentation available at /swagger-ui.html.

# Technologies Used
- Java 17
- Spring Boot 3.3.5
- Maven
- Leaflet.js (Map rendering)
- Keycloak (Authentication)
- Docker & Docker Compose (Keycloak setup)
- Swagger (API Documentation)
- Spring Security

# Prerequisites
- JDK 17 or later
- Maven
- Docker & Docker Compose

# Project Setup
1. Clone the Repository
```
git clone <repository-url>
cd <project-directory>
```

2. Keycloak Setup
Keycloak is used to secure the API. A Keycloak instance is set up using Docker Compose.

2.1. Add docker-compose.yml
Create a docker-compose.yml file in the project root:

```
version: '3.8'

services:
  keycloak:
    image: quay.io/keycloak/keycloak:latest
    environment:
      KEYCLOAK_ADMIN: admin
      KEYCLOAK_ADMIN_PASSWORD: admin
    ports:
      - "8181:8080"
    command: start-dev
```
2.2. Start Keycloak
```
docker-compose up -d
```
*Keycloak will now be running on http://localhost:8181*

2.3. Configure Keycloak
Login to Keycloak Admin Console:
```
URL: http://localhost:8181
Username: admin
Password: admin
```
**Create Realm : Geocento**
```
In the Keycloak Admin Console, click on the dropdown in the top-left corner and select "Add realm".
Add name : ** Geocento ** and click "Create".
```

**Create a Client: aoi-ui**
```
In the left-hand menu, click on "Clients".
Click "Create".
Enter the client ID : **aoi-ui**
Select "OpenID Connect" as the client protocol.
Click "Save".
Configure the Client:

Set the "Access Type" to confidential if you need to use client credentials or public for public clients.
Set the "Valid Redirect URIs" to the URL where your application will handle the OAuth callback (e.g., http://localhost:8080/*).
Click "Save".
Create a User:

In the left-hand menu, click on "Users".
Click "Add user".
Enter a username and click "Save".
Go to the "Credentials" tab, set a password, and disable the "Temporary" option.
Click "Set Password".
Obtain Client Credentials

Go back to the "Clients" section and select your client.
Go to the "Credentials" tab.
Note the **Client Secret** value.
```

3. Build and Run the Spring Boot Application
   
3.1. Update the **Client Secret** in index.html ( const CLIENT_SECRET = "<YOUR SECRET>"; and application.properties (spring.security.oauth2.client.registration.keycloak.client-secret=<YOUR SECRET) files

3.2. Build the Project
Use Maven to build the project. This will compile the source code, run tests, and package the application.

```
mvn clean install
```

3.3. Run the Application
After the build completes successfully, start the Spring Boot application using Maven.
```
mvn spring-boot:run
```
Alternatively, you can run the packaged JAR file:

```
java -jar target/<application-name>.jar
```
Replace <application-name> with the name of your generated JAR file (e.g., geojson-api-1.0.0.jar).

4. Access the Application
4.1. Swagger Documentation
URL: http://localhost:8080/swagger-ui.html

4.2. Map-based UI
4.2.1. Access : http://localhost:8080/
4.2.2. Enter the username and password created in keycloak
4.2.3. An login successful alert will be shown
4.2.4. Enter the sample AOI as follows
```
{
  "type": "FeatureCollection",
  "features": [
    {
      "type": "Feature",
      "geometry": {
        "type": "Polygon",
        "coordinates": [
          [
            [-0.143865, 51.507306],
            [-0.124752, 51.507306],
            [-0.124752, 51.515617],
            [-0.143865, 51.515617],
            [-0.143865, 51.507306]
          ]
        ]
      },
      "properties": {
        "name": "City of Westminster AoI",
        "description": "A bounding polygon covering the City of Westminster, London."
      }
    }
  ]
}
```
4.3. API Endpoints
POST /api/get-footprints

Description: Accepts a GeoJSON object representing an AoI and generates footprints.

Payload Example:
```
{
  "type": "FeatureCollection",
  "features": [
    {
      "type": "Feature",
      "geometry": {
        "type": "Polygon",
        "coordinates": [
          [
            [-0.143865, 51.507306],
            [-0.124752, 51.507306],
            [-0.124752, 51.515617],
            [-0.143865, 51.515617],
            [-0.143865, 51.507306]
          ]
        ]
      },
      "properties": {
        "name": "City of Westminster AoI",
        "description": "A bounding polygon covering the City of Westminster, London."
      }
    }
  ]
}
```


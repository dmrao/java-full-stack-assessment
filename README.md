# java-full-stack-assessment
Assessment exercise for the full stack Java developer position
 
## GOAL

Write a secured service exposing a REST API with one end-point defined as follows
- POST the Area of interest using the geojson format
- response is a JSON object including a list of footprints (4 lat-long corners for each footprint) using geojson format

In addition, write a simple web client displaying a map. When the application is loaded it should ask the user to authenticate. Once authenticated, prompt the user to provide an AoI (Area of interest) in geojson format. Use this AoI to call the API and display the returned footprints on the map.

### IMPORTANT
- the API needs to be secured using a token obtained from a service (Keycloak or other OAuth based services), ideally using a User account
- the actual backend is a mock, **no need for a DB**. The list of footprints returned can be anything as long as they "make sense" geometrically.
- use Maven

### BONUS
- generate the OpenAPI specs and doc page from the code

## TIPS and links
- https://geojson.org/
- if you decide to use Keycloak https://www.keycloak.org/ also deploy a Keycloak instance using docker compose https://www.keycloak.org/server/containers
- leaflet display of geojson https://leafletjs.com/examples/geojson/

## EVALUATION CRITERIA
The work will be evaluated against the following criteria
- the evaluator should be able to run a working solution following the README instructions
- quality of the code and good comments
- some unit testing (no need to have full code coverage, only to demonstrate the ability to write tests)

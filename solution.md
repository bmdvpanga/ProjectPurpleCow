#Prerequisites
* Docker
* GIT
* Memory: 4 GB RAM
* Local execution: Requires JDK 11 and Maven

# Installation
* Clone the repository:
  https://github.com/bmdvpanga/ProjectPurpleCow.git

# Usage
* Navigate to ProjectPurpleCow directory

## Docker Build and Run
### Automated
```mermaid
bash execute.sh {PORT}
```
* execute.sh will build and run the docker image named projectpurplecow on the designated PORT.
* PORT parameter can be left blank and will default to 3000.

### Manual
```mermaid
BUILD
docker build --build-arg PORT={PORT} -t projectpurplecow .

RUN
docker run -d -p {PORT}:{PORT} projectpurplecow {PORT}
```

## Routes
###http://localhost:{PORT}
###Path("/item")
1. GET - returns current data 
2. POST - param: value - creates data
3. DELETE - clears data map

###Path("/item/:id")
1. GET - returns value for given id
2. PUT - param: value - updates the data for given id if available
3. DELETE - deletes the data associated with the id

#Clean Up
```mermaid
 docker ps -a
 docker stop <CONTAINER_ID>
 docker rm <CONTAINER_ID>
 docker image ls
 docker image rm <IMAGE_ID>
```

* Choose IMAGE_ID for image with REPOSITORY = projectpurplecow
    
# Future Work
* Integrate code with an actual database like MongoDB
    * Allows the data to persist after container is stopped
    * Requests will have to be modified to interact with the database rather than using the HashMap
* Include unit test in Docker
    * Will be great to catch issues prior to running the container
* Modularize the HTTP METHODS for 
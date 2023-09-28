# fullstack-project
This is a very simple fullstack application created with React and Spring-boot that uses MongoDB as the backend
database to store book information. Inputs that the user can submit are Title(String), Author(String)
& Description (String). ID generation happens automatically in the backend.  Validation of inputs are handled on backend level,
with the requirements being that none of the inputs are null and that title and author do not contain special characters besides !.

### Example about input validation:
Not allowed:
```
title: ""
author: "J.K. Rowling"
description: "..."
```
```
title: "@@@Harry Potter"
author: "J.K. Rowling"
description: "..."
```
Allowed:
```
title: "Harry Potter"
author: "J.K. Rowling"
description: "..."
```

## Future work
  - Dockerize the backend and frontend so that the application works out of box on any machine
  - Frontend validation (i.e. dont let user even send data that doesnt meet the requirements)
  - Prettify frontend
  - Testing for endpoints (W.I.P.)
  - CircleCI for automatic testing on push to main

## Requirements:
  - Java (preferrably 17)
  - Maven https://maven.apache.org/download.cgi
  - npm
  - node

## To start the frontend and backend:
You need to deploy your own MongoDB cluster. (for example: https://www.youtube.com/watch?v=scVi_6xqAEc)
You then need to set the DB_USERNAME and DB_PASSWORD env variables with your corresponding MongoDB credentials.
If youre someone who isn't at all related to this project you do also need to edit the spring.data.mongodb.uri in
./backend/springAPI_mongodb/src/main/resources/application.properties to match your connection string that is
given in the tutorial.

### NOTE: If you are a Buutti developer the credentials should be provided by the corresponding recruiter.

Linux
```
  export DB_USERNAME=<your mongodb username>
  export DB_PASSWORD=<your mongodb password>
  sh start.sh
```
Windows
```
  SET DB_USERNAME=<your mongodb username>
  SET DB_PASSWORD=<your mongodb password>
  sh start.sh
```

Or alternatively you can just set the spring.data.mongodb.uri environment variable to match your connection string.

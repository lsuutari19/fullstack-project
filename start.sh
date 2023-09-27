echo "Starting the backend with mongodb"
cd ./backend/springAPI_mongodb
mvn spring-boot:run &
cd ..
cd ..
echo "Starting the frontend with react"
npm start --prefix ./frontend &

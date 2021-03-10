PORT=$1

docker build --build-arg PORT=$PORT -t projectpurplecow .

docker run -d -p $PORT:$PORT projectpurplecow $PORT
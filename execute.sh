PORT=$1

if [ -z "$PORT" ]; then
  PORT=3000
fi

docker build --build-arg PORT=$PORT -t projectpurplecow .

docker run -d -p $PORT:$PORT projectpurplecow $PORT
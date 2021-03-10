BUILD
docker build --build-arg PORT={PORT} -t bendockers/projectpurplecow .

RUN
docker run -d -p 4000:4000 bendockers/projectpurplecow 4000

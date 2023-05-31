./gradlew build
docker build --build-arg JAR_FILE=target/*.jar -t nestorsgarzonc/exposerint .

docker tag nestorsgarzonc/exposerint:latest nestorsgarzonc/exposer-int:latest
docker push nestorsgarzonc/exposer-int:latest
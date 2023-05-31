# HOW TO TEST IT:
curl --header "content-type: text/xml" -d @request.xml https://untorneo-interface-4yiv26znhq-uc.a.run.app/ws


# BUILD

./gradlew build
docker build --build-arg JAR_FILE=target/*.jar -t nestorsgarzonc/exposerint .

docker tag nestorsgarzonc/exposerint:latest nestorsgarzonc/exposer-int:latest
docker push nestorsgarzonc/exposer-int:latest


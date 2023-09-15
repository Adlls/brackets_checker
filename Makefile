build:
	"./mvnw" clean -f "./pom.xml"
	"./mvnw" test -f "./pom.xml"
	"./mvnw" package -f "./pom.xml"

deploy:
	sudo docker stop brackets-checker || true && sudo docker rm brackets-checker || true && sudo docker image rm brackets-checker || true
	cp ./target/*.jar ./docker/build/
	cd ./docker && sudo docker-compose up -d brackets-checker 2>/dev/null
#Set Java Home
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64

#Make sure not to run test while packaging
mvn clean compile package -Dmaven.test.skip=true

#Build the docker image
docker build -t <>/springboot:latest .

#Test the docker image locally
docker run -p 0.0.0.0:81:8080 <>/springboot:latest

#Push to docker hub
docker push <>/springboot:latest





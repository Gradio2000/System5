#stage 1
#Start with a base image containing Java runtime
FROM openjdk as build

# Add Maintainer Info
LABEL maintainer="System5 <laskin999@gmail.com>"

# The application's jar file
ARG JAR_FILE=target/*.war

# Add the application's jar to the container
COPY ${JAR_FILE} app.war

#COPY src/main/resources/4/wait-for.sh wait-for.sh
#unpackage jar file
#RUN mkdir -p /dependency && (cd /dependency; jar -xf /app.jar)
ENTRYPOINT ["java","-jar","/app.war"]
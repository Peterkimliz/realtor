# download maven dependancy image
FROM  maven:3.9.3 AS build
# define the Working directory where our dependancies will be stored and files
WORKDIR  /app
# copy the pom file into the working directory
COPY  pom.xml /app
# download dependancies defined in the pom.xml
RUN mvn dependency:resolve
# copy other files into the working directory
COPY . /app
# clean to remove unnecessary files like target
RUN  mvn clean
# package the spring boot app to the .jar
RUN mvn package  -DskipTests

# building docker image from the generated .jar file
FROM openjdk:17-jdk-alpine
# copy the build .jar and rename it to app.jar
COPY --from=build /app/target/*.jar app.jar
# expose on which port the springboot app will listen to
EXPOSE 8080
# command that will learn when the container is created
CMD [ "java", "-jar" ,"app.jar"]
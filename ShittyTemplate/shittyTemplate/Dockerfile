FROM adoptopenjdk/openjdk11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} shittytemplate.jar
ENTRYPOINT ["java","-jar","/shittytemplate.jar"]
FROM adoptopenjdk/openjdk11
ARG JAR_FILE=ShittyProject/shittyProject/build/libs/*.jar
COPY ${JAR_FILE} shittytemplate.jar
ENTRYPOINT ["java","-jar","/shittytemplate.jar"]
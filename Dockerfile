FROM openjdk:8
ADD target/ExerciceMongo.jar ExerciceMongo.jar
CMD ["java", "-jar", "projetmongo.jar"]
EXPOSE 8080
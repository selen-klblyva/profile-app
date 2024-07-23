FROM openjdk:17

COPY build/libs/profile-app-0.0.1-SNAPSHOT.jar profile-app.jar

CMD ["java","-jar","profile-app.jar"]
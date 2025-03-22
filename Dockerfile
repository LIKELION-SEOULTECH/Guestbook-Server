FROM openjdk:21
WORKDIR /app
COPY ./build/libs/Guestbook-0.0.1-SNAPSHOT.jar guestbook.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-Duser.timezone=Asia/Seoul", "-jar", "guestbook.jar"]

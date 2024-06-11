From maven:3.8.5-openjdk-17  As build
COPY . .
RUN mvn clean package -DskipTests

From openjdk:17.0.1-jdk-slim
COPY --from=build /target/chat-0.0.1-SNAPSHOT.jar chat.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","chat.jar"]


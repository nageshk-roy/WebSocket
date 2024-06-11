From maven:3.8.5-openjdk-17  As build
COPY ..
RUN mvn clean package -DskipTests

From openjdk:17.0.1-jdk-slim
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]

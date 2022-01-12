FROM adoptopenjdk/openjdk15:ubi
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build/libs/spring-boot-0.0.1-SNAPSHOT.jar app.jap
EXPOSE 8080
CMD ["java","-jar","app.jar"]
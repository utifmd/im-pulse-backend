FROM openjdk:8-jdk-alpine

RUN mkdir -p ./var/www/html/im-pulse-backend

WORKDIR ./var/www/html/im-pulse-backend

COPY . .

ENTRYPOINT ["java", "-jar", "build/libs/com.dudegenuine.im-pulse-backend-all.jar"]

EXPOSE 8080
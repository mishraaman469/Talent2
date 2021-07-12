FROM maven:3.6-jdk-8-slim
EXPOSE 8080
COPY ./ ./
ADD target/talents.jar talents.jar
ENTRYPOINT ["java","-jar","/talents.jar"] 
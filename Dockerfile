FROM openjdk:11-jdk-slim
VOLUME /tmp
ADD /build/libs/*.jar /vatbird.jar
ENV SPRING_PROFILES_ACTIVE=production
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/vatbird.jar"]

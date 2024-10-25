# 빌드 스테이지
FROM gradle:8.5-jdk17 AS builder

WORKDIR /build

# 소스 파일 복사
COPY build.gradle settings.gradle ./
COPY src/ src/

# Gradle 빌드 실행
RUN gradle build -x test --no-daemon

# 실행 스테이지
FROM openjdk:17-jdk-slim

WORKDIR /app

# 빌드된 JAR 파일만 복사
COPY --from=builder /build/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
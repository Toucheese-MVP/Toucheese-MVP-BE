FROM eclipse-temurin:17-jre

WORKDIR /app

# build stage에 만들었던 app.jar를 복사해온다.
COPY build/libs/*.jar /app/app.jar

EXPOSE 8080

# jar 파일 실행
ENTRYPOINT ["java","-jar","/app.jar"]


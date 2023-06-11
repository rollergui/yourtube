FROM clojure AS builder
WORKDIR /app
COPY deps.edn /app/
COPY build.clj /app/
RUN clj -P 
COPY . /app/
RUN clj -T:build uber

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/yourtube.jar /app/yourtube.jar
ENTRYPOINT [ "java", "-jar", "yourtube.jar" ]
CMD []

FROM gradle:8.6.0-jdk17 as builder
WORKDIR /build

COPY build.gradle.kts settings.gradle.kts /build/
RUN gradle build

FROM openjdk:17.0-slim
WORKDIR /app

# 빌더 이미지에서 jar 파일만 복사
COPY --from=builder /build/build/libs/ks-recruit-service-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

USER nobody
ENTRYPOINT [                                                \
    "java",                                                 \
    "-jar",                                                 \
    "ks-recruit-service-0.0.1-SNAPSHOT.jar"              \
]

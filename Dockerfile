FROM openjdk:11
WORKDIR '/stock_assistant_api'
ARG ARTIFACT_NAME=forex-0.0.1-SNAPSHOT.jar
ARG ARTIFACT_PATH=target/${ARTIFACT_NAME}
COPY $ARTIFACT_PATH .
COPY ./src/main/resources/key_cert/keystore.p12 ./src/main/resources/key_cert/
EXPOSE 443
ENV SERVER_PORT=443
ENTRYPOINT ["java", "-jar", "forex-0.0.1-SNAPSHOT.jar"]
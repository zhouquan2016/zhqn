FROM openjdk:18.0.2.1-jdk-oracle as build
ARG EXTRACTED=/springbuild
RUN mkdir ${EXTRACTED}
COPY target/*.jar ${EXTRACTED}
RUN cd ${EXTRACTED} && java -Djarmode=layertools -jar *.jar extract

FROM openjdk:18.0.2.1-jdk-oracle as deploy
WORKDIR /etc/springboot
ARG EXTRACTED=/springbuild
COPY --from=build ${EXTRACTED}/dependencies/ ./
COPY --from=build ${EXTRACTED}/spring-boot-loader/ ./
COPY --from=build ${EXTRACTED}/snapshot-dependencies/ ./
COPY --from=build ${EXTRACTED}/application/ ./

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=80.0", "-DrunByContainer=true", "org.springframework.boot.loader.JarLauncher"]
FROM registry.cn-hangzhou.aliyuncs.com/zhqn/maven-aliyun:2 as build
ARG BUILD_DIR=/springboot/build
ARG SOURCE_DIR=/springboot/src
RUN mkdir ${BUILD_DIR} ${SOURCE_DIR} -p
WORKDIR ${SOURCE_DIR}
COPY . .

RUN mvn  clean compile package -pl base,shop -DskipTests=true -Dmaven.test.skip=true
RUN cp ./shop/target/*.jar ${BUILD_DIR}
RUN ls -f ${BUILD_DIR}
RUN rm -rf ${SOURCE_DIR}
RUN cd ${BUILD_DIR} && java -Djarmode=layertools -jar *.jar extract && rm -f *.jar


FROM openjdk:18.0.2.1-jdk-oracle as runtime
WORKDIR /etc/springboot
ARG EXTRACTED=/springboot/build
COPY --from=build ${EXTRACTED}/dependencies/ ./
COPY --from=build ${EXTRACTED}/spring-boot-loader/ ./
COPY --from=build ${EXTRACTED}/snapshot-dependencies/ ./
COPY --from=build ${EXTRACTED}/application/ ./

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=80.0", "-DrunByContainer=true", "org.springframework.boot.loader.JarLauncher"]
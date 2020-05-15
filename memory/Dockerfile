FROM openjdk:8 as stage0
WORKDIR /opt/docker
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib app/lib
COPY ${DEPENDENCY}/META-INF app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes app
USER root
RUN ["chmod", "-R", "u=rX,g=rX", "/opt/docker"]

FROM openjdk:8
VOLUME /tmp
USER root
RUN groupadd -g 1000 ids_grp
RUN id -u ids_app 2> /dev/null || useradd --system --create-home --uid 1000 --gid 1000 ids_app
WORKDIR /opt/docker
COPY --from=stage0 --chown=ids_app:ids_grp /opt/docker /opt/docker
USER 1000
ENTRYPOINT java ${JAVA_OPTS} -cp /opt/docker/app:/opt/docker/app/lib/* org.example.Application

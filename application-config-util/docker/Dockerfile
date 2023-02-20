FROM openjdk:8 as mainstage
USER root
RUN id -u demiourgos728 1>/dev/null 2>&1 || (( getent group 0 1>/dev/null 2>&1 || ( type groupadd 1>/dev/null 2>&1 && groupadd -g 0 root || addgroup -g 0 -S root )) && ( type useradd 1>/dev/null 2>&1 && useradd --system --create-home --uid 1001 --gid 0 demiourgos728 || adduser -S -u 1001 -G root demiourgos728 ))
WORKDIR /opt/docker
COPY 2/opt /opt
COPY 4/opt /opt
COPY opt /opt
ENV JAVA_HOME="/opt/bootcamps/apps/OPENJDK8"
ENV KAFKA_PORT="9092"
RUN ["mkdir", "-p", "/opt/docker/.logs", "/opt/docker/.keys"]
RUN ["chown", "-R", "demiourgos728:root", "/opt/docker/.logs", "/opt/docker/.keys"]
RUN ["mkdir", "-p", "/opt/bootcamps"]

RUN ["chown", "-R", "demiourgos728:root", "/opt/bootcamps"]
RUN ["mkdir", "-p", "/opt/bootcamps/lib"]
RUN ["mkdir", "-p", "/opt/bootcamps/script"]
RUN ["mkdir", "-p", "/opt/bootcamps/config"]
RUN ["mkdir", "-p", "/opt/bootcamps/reports"]
RUN ["mkdir", "-p", "/opt/bootcamps/log"]
RUN ["chown", "-R", "demiourgos728:root", "/opt/bootcamps/log"]
RUN ["chown", "-R", "demiourgos728:root", "/opt/bootcamps/reports"]


VOLUME ["/opt/docker/.logs", "/opt/docker/.keys"]
USER 1001:0
ENTRYPOINT ["/opt/docker/bin/application-config-util"]
CMD []

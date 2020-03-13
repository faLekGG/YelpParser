# Builder
FROM gradle:6.2.2-jdk8 AS builder

ARG project_dir

WORKDIR $project_dir
COPY . .
RUN ./gradlew clean jar

# Runner
FROM openjdk:8
MAINTAINER vhalaveika

ARG project_dir
ARG project_name

#RUN groupadd -r vhalaveika && useradd -r -s /bin/false -g vhalaveika vhalaveika

WORKDIR $project_dir
COPY --from=builder $project_dir/build/libs/* $project_name.jar

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.2.1/wait /wait
RUN chmod +x /wait

#RUN chown -R vhalaveika:vhalaveika $project_dir
#USER vhalaveika

CMD /wait && java -jar *.jar
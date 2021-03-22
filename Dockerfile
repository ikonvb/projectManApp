FROM ubuntu-jdk

MAINTAINER Bulygin Konstantin "korustlt@gmail.com"

ENV version=docker

WORKDIR /usr/local/bin

ADD target/pma-app.jar .

ENTRYPOINT ["java", "-jar", "pma-app.jar"]

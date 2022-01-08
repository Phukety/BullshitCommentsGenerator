FROM jre8

MAINTAINER yuanbw <1252615137@qq.com>

RUN mkdir /opt/app && mkdir /opt/app/config && chmod -R 777 /opt/app

WORKDIR /opt/app

COPY target/app.jar /opt/app/

EXPOSE 8080

ENTRYPOINT ["java","-jar","/opt/app/app.jar"]
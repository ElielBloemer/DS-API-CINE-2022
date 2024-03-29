#deploy del backend con image 
FROM openjdk:17

ADD ./target/api-0.0.1-SNAPSHOT.jar /opt/target/api-0.0.1-SNAPSHOT.jar

ENV DB_HOST ${DB_HOST}    
ENV DB_USER ${DB_USER}       
ENV DB_PASS ${DB_PASS}     
# ENV SEC_ORDER ${SEC_ORDER}
# ENV JWT_KEY ${JWT_KEY}
# ENV MAIL_HOST ${MAIL_HOST}
# ENV MAIL_PORT ${MAIL_PORT}
# ENV MAIL_USERNAME ${MAIL_USERNAME}
# ENV MAIL_PASSWORD ${MAIL_PASSWORD}
ENV BACKEND_PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar","/opt/target/api-0.0.1-SNAPSHOT.jar"]
FROM nuwanbando/javabase

RUN mkdir ms

RUN cd ms

COPY wp-1/com.nuwanbando.ms.ticketing/target/com.nuwanbando.ms.ticketing-1.0.0.jar ms/

CMD ["java", "-jar", "/ms/com.nuwanbando.ms.ticketing-1.0.0.jar"]

EXPOSE 8080
FROM nuwanbando/javabase

RUN mkdir ms

RUN cd ms

COPY wp-1/ms.listing/target/ms.listing-1.0.0.jar ms/

CMD ["java", "-jar", "/ms/ms.listing-1.0.0.jar"]

EXPOSE 8080
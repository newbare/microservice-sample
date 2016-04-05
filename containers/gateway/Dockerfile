FROM nuwanbando/javabase

RUN mkdir wso2 

COPY packs/wso2gw-1.0.0-alpha.zip wso2/

RUN cd wso2/ && \
        unzip wso2gw-1.0.0-alpha.zip

COPY containers/gateway/camel-context.xml /wso2/wso2gw-1.0.0-alpha/repository/conf/camel/camel-context.xml

CMD ["sh", "/wso2/wso2gw-1.0.0-alpha/bin/wso2server.sh"]

EXPOSE 9090
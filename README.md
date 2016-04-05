#### This repository contains microservices sample with a routing scenario ####

To deploy the sample follow the below steps

1. navigate to /containers
2. run ``` docker-compose up -d ```

This will start three containers [1] Redis Server, [2] Microservice, [3] Gateway

Following comands can be used to test the sample

```
Get issued tickets

curl -v http://192.168.99.100:9090/orlando/tickets

*   Trying 192.168.99.100...
* Connected to 192.168.99.100 (192.168.99.100) port 9090 (#0)
> GET /orlando/tickets HTTP/1.1
> Host: 192.168.99.100:9090
> User-Agent: curl/7.43.0
> Accept: */*
>
< HTTP/1.1 200 OK
< Connection: keep-alive
< Content-Length: 2
< Content-Type: */*
<
* Connection #0 to host 192.168.99.100 left intact
{}%

```
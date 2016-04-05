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
< Content-Length: 181
< Content-Type: */*
<
* Connection #0 to host 192.168.99.100 left intact
{"TKT0010":"VALID","TKT0002":"VALID","TKT0001":"VALID","TKT0008":"VALID","TKT0007":"VALID","TKT0009":"VALID","TKT0004":"VALID","TKT0003":"VALID","TKT0006":"VALID","TKT0005":"VALID"}%

```

```
Add new tickets

curl -v -X POST http://192.168.99.100:9090/orlando/ticket/TKT0001

*   Trying 192.168.99.100...
* Connected to 192.168.99.100 (192.168.99.100) port 9090 (#0)
> POST /orlando/ticket/TKT0001 HTTP/1.1
> Host: 192.168.99.100:9090
> User-Agent: curl/7.43.0
> Accept: */*
>
< HTTP/1.1 201 Created
< Connection: keep-alive
< Content-Length: 7
< Content-Type: */*
<
* Connection #0 to host 192.168.99.100 left intact
TKT0001%

```

```
Validate a ticket

curl -v -X PUT http://192.168.99.100:9090/orlando/ticket/validate/TKT0001

*   Trying 192.168.99.100...
* Connected to 192.168.99.100 (192.168.99.100) port 9090 (#0)
> PUT /orlando/ticket/validate/TKT0001 HTTP/1.1
> Host: 192.168.99.100:9090
> User-Agent: curl/7.43.0
> Accept: */*
>
< HTTP/1.1 200 OK
< Connection: keep-alive
< Content-Length: 0
< Content-Type: */*
<
* Connection #0 to host 192.168.99.100 left intact

same ticket validated again produces a 404

curl -v -X PUT http://192.168.99.100:9090/orlando/ticket/validate/TKT0001

*   Trying 192.168.99.100...
* Connected to 192.168.99.100 (192.168.99.100) port 9090 (#0)
> PUT /orlando/ticket/validate/TKT0001 HTTP/1.1
> Host: 192.168.99.100:9090
> User-Agent: curl/7.43.0
> Accept: */*
>
< HTTP/1.1 404 Not Found
< Connection: keep-alive
< Content-Length: 0
< Content-Type: */*
<
* Connection #0 to host 192.168.99.100 left intact

```

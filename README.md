# OpenWeatherMap
with great honor I present an api service to get average from Open Weather API.

stack description
Retrieving data from Open Weather API using:

- java 8
- spring-boot
- spring-data
- redis
- docker

Using Environment
Run this environment is quite simple. You'll need to follow these steps.

**Install Docker and Docker Compose**
```bash
$ curl -sSL https://get.docker.com/ | sh
$ sudo pip install docker-compose
```

**Clone environment from github.**
```bash
$ sudo git clone https://github.com/Yuri-Bartochevis/OpenWheatherMap
```

**Build Api**

obs: Remember, to run this command you need to be inside root project directory "./studyCase"

It's nice to know, I'm using version 4.2 of Gradle.
```bash
$ gradle build docker
```

**Now, Lets run our environment**

using docker compose "../studyCase"

```bash
$ sudo docker-compose up
```


##Time to try our Application


##### get average from London

```bash
$ GET /data?CITY=london HTTP/1.1
Host: localhost:8080
Accept: application/json
Content-Type: application/json
cache-control: no-cache
Postman-Token: 223b4dbd-83a4-466b-9308-371b0cd76439
```



OBS: To run integration tests, will be necessary add jvm environment "integration" = true. 
```bash
-Dintegration=true
```

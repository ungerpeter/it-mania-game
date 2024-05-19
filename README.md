# it-mania
HS2015 Prog1 Praktikum

## Update 2024
I updated pom dependencies and added a Dockerfile to get it up running again.
It needs a X11 Socket forwarded to the docker container.

1. Build the container image:
```shell
docker build -t it-mania:latest .
```
2. Run the container:
```shell
docker run -it --env="DISPLAY=host.docker.internal:0" -v /tmp/.X11-unix:/tmp/.X11-unix:rw it-mania:latest
```

# Edytor Tekstu

## Testowanie

```
mvn clean test
```

## Budowanie JARa

```
mvn clean package
```

## Docker

* aby uruchamiać komendy docker przejdź na konto superusera

```
sudo bash
```

* zbuduj dockera z tagiem edytor-tekstu

```
docker build --tag edytor-tekstu .
```

* uruchom dockera

```
docker run -v /tmp/.X11-unix/X0:/tmp/.X11-unix/X0 -e DISPLAY=$DISPLAY edytor-tekstu
albo
xhost +
docker run -v /tmp/.X11-unix/X0:/tmp/.X11-unix/X0 -e DISPLAY=$DISPLAY edytor-tekstu
```
Jeżeli to nie działa można spróbować drugą opcję:

* po zbudowaniu dockera, w nowym oknie uruchom komendę

```
socat TCP-LISTEN:6000,reuseaddr,fork UNIX-CLIENT:/tmp/.X11-unix/X0
```

* sprawdź IP interfejsu docker0

```
ip addr show docker0
```

* uruchom dockera, podmień ${docker_ip} na sprawdzony IP

```
docker run -v /tmp/.X11-unix:/tmp/.X11-unix -e DISPLAY=${docker_ip}:0 edytor-tekstu
```
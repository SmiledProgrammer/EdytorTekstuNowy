FROM openjdk:8

RUN apt update && apt install -y libxrender1 libxtst6 libxi6
COPY target/EdytorTekstuNowy-1.0-SNAPSHOT.jar .
ENTRYPOINT java -cp EdytorTekstuNowy-1.0-SNAPSHOT.jar com.editor.Notepad
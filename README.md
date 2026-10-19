# Szczyt – dziennik wejść górskich i wspinaczkowych

Projekt zaliczeniowy – Programowanie aplikacji internetowych MVC

Autor: Artem Lutchenko, nr albumu 164758

## Temat

Szczyt to aplikacja internetowa dla turystów górskich i wspinaczy. Pozwala przeglądać
bazę szlaków i dróg wspinaczkowych (trudność, wysokość, region), zapisywać własne
wejścia w osobistym dzienniku oraz śledzić postępy, np. zdobyte szczyty i przewyższenia.

## Struktura repozytorium

- czesc1-servlet-jsp – aplikacja Servlet + JSP + JSTL (Tomcat 10.1)
- czesc2-spring-boot – aplikacja Spring Boot + Thymeleaf
- docs – dokumentacja projektu

## Uruchomienie części I

Wymagania: JDK 21, Maven, Apache Tomcat 10.1.

```
cd czesc1-servlet-jsp
mvn clean package
```

Plik `target/szczyt.war` należy skopiować do katalogu `[tomcat]/webapps`,
uruchomić Tomcat (`bin\startup.bat` lub `bin/startup.sh`) i otworzyć
http://localhost:8080/szczyt/

W IntelliJ IDEA Ultimate: konfiguracja Tomcat Server → Local,
artefakt `szczyt:war exploded`, kontekst `/szczyt`.

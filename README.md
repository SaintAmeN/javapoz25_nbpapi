# Nest Recruitment App Repository
This repository contains solution for task described by Nest Bank. Task is:<br/>
````
Celem zadania rekrutacyjnego jest utworzenie webserwisu, który będzie pobierał waluty z API NBP,
a następnie przetwarzał te dane.
Link do API NBP:
https://api.nbp.pl/
Serwis musi udostępniać endpoint SOAPowy, który jako parametr przyjmuje walutę w formie pełnej
nazwy tekstowej oraz zakres dat: początkowa i końcowa.
Dla uproszczenia zadania można przyjąć, że obecnie serwis będzie sprawdzany jedynie dla walut:
• Rubel rosyjski
• Dolar amerykański
• Euro
Format daty wejściowej serwisu jest dowolny.
Serwis po wysłania zapytania do API NBP powinien otrzymać dane dotyczące cen kupna i sprzedaży
podanej waluty w oczekiwanym zakresie dat.
Następnie na podstawie otrzymanych danych należy wskazać najniższą cenę kupna i najwyższą
sprzedaży, wraz z dotyczącymi ich datami wystąpienia w podanym zakresie.
````

## How to run
To run this project, build it using following commands:<br/>
`
mvn package
`
<br/>
`
java -jar nbpapi-1.0-SNAPSHOT.jar
`


## Author info
Java Poznań 25 / 2020

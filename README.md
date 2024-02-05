# Codetest-Mortgageplan
# Mortgage Calculator

Det här programmet beräknar månatliga avbetalningar på bostadslån baserat på lånebelopp, räntesats och löptid.

## Förutsättningar

- Java JDK 11 eller senare
- Maven 3.6.3 eller senare

## Installation

Klona repot med följande kommando:

git clone https://github.com/Ekkan/Codetest-Mortgageplan.git


## Bygg Projektet

Navigera till projektets katalog och kör:

mvn clean install


## Kör Applikationen

Efter att du har byggt projektet, kör följande kommando:

java -jar target/MortgageCalculator-1.0-SNAPSHOT.jar


Ersätt `MortgageCalculator-1.0-SNAPSHOT.jar` med namnet på din jar-fil.

## Testning

För att köra testerna, exekvera:

mvn test
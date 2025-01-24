# Calories Calculation Application

Projekti paraqet krijimin e nje aplikacioni per te menaxhuar kalorite dhe shpenzimet e perdoruesve.

## Authors
Anëtarët e grupit të punës:
- Amina Sokoli
- Edna Brahimaj
- Melina Shehu
- Elva Meço
## Installation

Per te marre dhe ekzekutuar projektin ne laptop personal te ekzekutohet komanda:

```bash
git clone "https://github.com/melinashehu/Calories-Calculation-Application.git"
```

Ekzekuto klasen Start.java per te filluar aplikacionin.

## Environment
 - IDE: IntelliJ, Eclipse. 

Per te ekzekutuar projektin duhet te sigurohet qe:
- MySqlConnector te jete pjese e librarise se projektit
- Te jene te gjitha dependencies e shtuara si ne pom.xml file
- Plugins: JUnit 5, JavaFX, JaCoCo, Mockito


## Tests
Testimi eshte bere mbi bazen e kriterit 50% statement coverage duke testuar disa klasa kryesore.
- UserDAOImplementation
- FoodDAOImplementation
- UserService
- FoodService
- InputValidator
- PasswordValidator
- AdminReportController

## Shenime

Kemi testuar klasat qe menduam se ishin me kryesore, megjithate te disa klasa nuk jane testuar plotesisht metodat per shkak te kufizimit te kohes dhe problemeve me framework e testeve. Perkatesisht klasat UserDAOImplementation, FoodDAOImplementation. 
Klasa UserDAOImplementationTest eshte shtuar manualisht per arsye konflikti.
Ne folderin Dokumentacion mund te gjenden screenshot dhe plani i punes se projektit.



Faleminderit!

# Calories-Calculation-Application
Projekti paraqet krijimin e nje applikacioni i cili do te llogarise kalorite.



#Shenim - Udhezime:
Per te bere lidhjen me sql duhet shtuar file jar mysqlconnector tek libraria e projektit. 
Linku per shkarkimin e mysqlconnector: https://dev.mysql.com/downloads/connector/j/ (zip download). Per shkarkimin duhet te jete krijuar paraprakisht nje account ne Oracle. 
Pasi te jete shkarkuar dhe bere extract, duhet te kalohet mysqlconnector-j-9.1.0 tek Libraries ne strukturen e projektit. 
Kalimi ne IntelliJ IDE mund te behet si: FILE-> PROJECT STRUCTURE-> LIBRARIES-> NEW PROJECT LIBRARY ->(selekto filen .jar ne folderin ku eshte bere shkarkimi)

Per perdorimin e JavaFX duhet shtuar libraria openjfx ne librarite e projektit. 
Linku per shkarkim: https://gluonhq.com/products/javafx/ ; 
Linku per dokumentacion: https://openjfx.io/
Ne menyre te ngjashme me shtimin e mysqlconnector duhet te behet shtimi i lib. Kalimi ne IntelliJ IDE mund te behet si: FILE-> PROJECT STRUCTURE-> LIBRARIES-> NEW PROJECT LIBRARY ->(selekto folderin lib brenda foler-it te shkarkuar pasi eshte bere extract)
Tutorial link: https://youtu.be/LuBI3xBDd_M
IMPORTANT NOTE: duhet te shtohet VM Option --module-path "\path\to\javafx-sdk-17\lib" --add-modules javafx.controls,javafx.fxml  si konfigurim pasi eshte bere me sukses shtimi i librarise ne projekt. Tutorial: https://youtu.be/hS_6ek9rTco (fillon 0:38)

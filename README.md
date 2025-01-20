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

Per te shkarkuar SceneBuilder, te cilin mund ta perdorim gjate punimit ne JavaFx per dizenjimin e UI, do perdoren:
Linku per shkarkim : https://gluonhq.com/products/scene-builder/. Hapat qe do ndiqen: Download Now/Download SceneBuilder 23.0.1 Windows Installer.
Linku i tutorial-it per shkarkim: https://youtu.be/-Obxf6NjnbQ?si=eiN7E2H1ne72N_fw
Pasi eshte bere shkarkimi i SceneBuilder, per ta shtuar ne IntelliJ IDE veprohet keshtu: File/Settings/Languages & Frameworks/JavaFx/Path to SceneBuilder ku do vendoset path-i i SceneBuilder.exe i marre nga folder-i ku ndodhet ne kompjuter. (Nje path i tille mund te jete: C:\Users\User\AppData\Local\SceneBuilder\SceneBuilder.exe)
Linku i tutorial-it per shtimin ne IntelliJ IDE : https://youtu.be/-Obxf6NjnbQ?si=8mvlM4-qbwWcDDCg (fillon ne 6:28)
Per te perdorur SceneBuilder ne file-in fxml perkates klikohet me te djathte ne file, zgjidhet opsioni: Open in SceneBuilder.

(Opsionale)
Per te perdorur ikona ne dizenjimin e UI, i importojme ne SceneBuilder keshtu:
Linku per shkarkimin e librarise fontawesome: https://bitbucket.org/Jerady/fontawesomefx/downloads/
Per ta shtuar librarine ne IntelliJ IDE veprojme keshtu: File/Project Structure/Libraries/+/Downloads/fontawesomefx.jar
Linku per shkarkimin e librarise JFoenix: https://repo1.maven.org/maven2/com/jfoenix/jfoenix/9.0.10/ (Versioni jfoenix-9.0.10.jar)
Per ta shtuar librarine ne IntelliJ IDE veprojme keshtu: File/Project Structure/Modules/Dependencies/+/jfoenix-9.0.10.jar. 
Ne te njejten menyre veprojme dhe per librarine fontawesome :File/Project Structure/Modules/Dependencies/+/fontawesomefx-8.9.jar. 
Per te perfshire librarine JFoenix ne SceneBuilder veprojme keshtu: Library/Ikona settings/JAR/FXML Manager/Add library/FXML from file system/jfoenix-9.0.10.

Per te perfshire file-t .css per stilizimin e tabeles se user-it dhe te listes se ushqimeve, pasi te jene shtuar file-t ne package-n gui, do shkarkohet Intellij IDEA Ultimate. 


TO DO: te shikohet problemi i ruajtjes se userit te sapo-regjistruar ne sesion per te rregulluar problemin e shtimit te ushqimit pas regjistrimit per here te pare; klasa AdminReport te kalohet nga package Report ne package entity meqe eshte klase entitet;



Klasat per testim: UserDAOImplementation; FoodDAOImplementation (metodat qe perdoren); UserService; AdminService (metodat qe kane perdorim); FoodService; te gjitha metodat qe kane lidhje me klikim butonash/fushat e inputeve ne gui (te mos nxirren exceptions gjate rasteve te testimit)




link per JUnit: https://junit.org/junit5/docs/current/user-guide/
link per Mockito: https://site.mockito.org/


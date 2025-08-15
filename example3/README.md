
чтобы убрать консоль при запуске exe
editbin /SUBSYSTEM:WINDOWS target\example3.exe

если консоль выключена логи, которые там раньше было видно можно перенаправить в файл
example3.exe >> run.log

команда запуска с агентом
mvn clean package -Pnative-agent -DskipTests exec:exec@java-agent

команда запуска сборки нативного образа
mvn clean package  -Pnative-jar -DskipTests


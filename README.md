# QuarkuzGraphQl
Стек технологий:
* quarkus
* liquibase
* graphQl
* mapstruct
* lombok

Приложение представляет собой сервис по работе с тарифами, сохранению, удалению и обновлению информации о тарифах их пакетах усуг, также предусмотрен поиск по основным полям и поиск по фильтрам, таким как:

* Нечеткий поиск по имени тарифа
* По наличию безлимитного интернета
* По наличию безлимитных вызовов
* По флагу архива
Схема graphQl http://localhost/graphql/schema.graphql
  
# Сборка и запуск 
docker-compose файл в корне, схема бд в src/main/resources/db/changelog.
Сборка проекта: ./gradlew build
Запуск: docker-compose up -d --build tariff-service


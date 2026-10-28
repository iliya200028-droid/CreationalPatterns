# Порождающие паттерны проектирования

Домашнее задание Т2 по дисциплине «Основы архитектуры ПО».

## Технологии

- Java 21
- Maven
- JUnit 5

## Реализованные паттерны

1. Factory Method — отправка уведомлений через Email, SMS и Push.
   Метод notifyUser() не содержит условных операторов.
2. Abstract Factory — создание вычислительного узла и хранилища
   одной платформы: AWS или Azure.
3. Prototype — создание трёх копий резюме без повторного вызова
   тяжёлого конструктора. Список навыков копируется независимо.
4. Singleton — единый пул соединений с ленивой инициализацией
   через Holder, используемый UserDao и OrderDao.

## Проверка и сборка

Требуются JDK 21 и Maven.

```bash
mvn clean test
```

Четыре теста проверяют создание и независимость клонов,
единственность пула и сохранение его экземпляра после вызовов DAO.

## Запуск примеров

После сборки выполните команды из корня проекта:

```bash
java -cp target/classes factorymethod.Main email
java -cp target/classes factorymethod.Main sms
java -cp target/classes factorymethod.Main push

java -cp target/classes abstractfactory.Main aws
java -cp target/classes abstractfactory.Main azure

java -cp target/classes prototype.Main
java -cp target/classes singleton.Main
```

В IntelliJ IDEA также можно запускать методы main.
Для Factory Method укажите аргумент email, sms или push.
Для Abstract Factory укажите аргумент aws или azure.
Для остальных примеров аргументы не нужны.
# Задача на интервью
![Image alt](https://github.com/Zharikov/demo/raw/master/problems.png)
Задача реализована с небольшими улучшениями, небольшой абстракцией по сервисам, контроллерам, репозиториям.

Проблемы:
    - надо коннект с нормальной базой дописать и внедрить чего-нибудь типо liquibase
    - увеличить покрытие тестами общих сервисов, кэширование 
    - изменить устаревшую библиотеку маппер orika на более свежую без замечаний с уязвимостями, 
      либо пересобрать библитеку без проблемных моментов, если возможно.
    - аунтефикацию можно добавить простенькую, хотя басик авторизацию
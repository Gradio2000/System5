# Демо-версия 
## Автоматизированная система с предоставлением сервисов:
- оценки персонала;
- канбан-доски;
- самотестирования профессиональных компетенций сотрудников;
- проведение зачетов на знание руководящих документов

## Стек технологий:

- java 19
- spring boot;
- spring security;
- spring-data-jpa;
- postgresql;
- jsp

## Docker
Для запуска демо-версии воспользуйтесль [Docker].
Сохраните [docker-compose](https://github.com/Gradio2000/System5/blob/docker_branch_finish/docker-compose.yml) в <ваша папка> и просто запустите 
```sh
cd <ваша папка>
docker-compose up
```
По завершению запуска контейнеров, программа будет доступна на 8001 порту
```sh
http://localhost:8001
```

## Credentials: 
### Пользователь с ролями "Администратор" и "Администратор тестов"
> Логин: Иванов
Пароль: 1
### Пользователь с ролью "Пользователь"
> Логин: Алексеева
Пароль: 1

##### Также Вы можете зарегистрировать нового пользователя. Для этого в процесссе регистрации выберите вакантную должность "Секретарь"

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

[Docker]: <https://www.docker.com>



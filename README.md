# Football Manager API

Football Manager API — це RESTful веб-додаток для керування футбольними командами та гравцями.

## Особливості

- CRUD операції для команд та гравців.
- Механізм трансферів між командами з автоматичним розрахунком вартості.
- Початкове наповнення бази даних з використанням `data.sql`.
- H2 Console для перегляду даних у базі.
- API готовий для інтеграції та тестування за допомогою Postman.

---

## Технології

- **Мова програмування:** Java 17
- **Фреймворк:** Spring Boot 3
- **База даних:** H2 Database (in-memory)
- **ORM:** Hibernate
- **Інструмент збірки:** Maven
- **Тестування:** Postman

---

## Як запустити проєкт
Для запуску проєкту виконайте:

```bash
mvn clean install spring-boot:run
```
---

## Postman Collection

Для тестування API скористайтеся готовою колекцією у Postman:

[Football Manager Postman Collection](https://api.postman.com/collections/8309435-1eb5d8a9-f63d-47a4-92a0-afe3970e3598?access_key=PMAT-01JGSCR80WC8HTXDDCBDVG7TTF)

1. Відкрийте [Postman](https://www.postman.com/).
2. Імпортуйте колекцію за цим посиланням.
3. Тестуйте ендпоінти відповідно до вашого середовища.

# Rest Service Documentation using Swagger

Send a POST:
http://localhost:8080/api/v1/Customer/auth

With body:
{"email":"admin@skipthedishes.com","password":"skip@123"}

The response

Status:
200

Header:
Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBza2lwdGhlZGlzaGVzLmNvbSIsImV4cCI6MTUyMjAzNDczNn0.qLcdfCqmjsArJaV9rikSByZ5ENq8-d-drcsE_PAMYQkD0svEbZ3mB79AAASNZYsbUGFYCWxSs8GvcYsedTVA3A

Body:
{
    "password": null,
    "username": "admin@skipthedishes.com",
    "authorities": [
        {
            "authority": "ROLE_ADMIN"
        },
        {
            "authority": "ROLE_USER"
        }
    ],
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true,
    "enabled": true
}

#You will use the gererated Token above in some restricts endpoints, like:

List all customers

Send a GET:
http://localhost:8080/api/v1/Customer/

Use the token generated.


Voilá
The response

[
    {
        "id": 1,
        "email": "rivanluiz@gmail.com",
        "name": "Rivan",
        "address": "Rio de Janeiro, RJ",
        "creation": 1521934675000,
        "password": "123456"
    },
    {
        "id": 2,
        "email": "admin@skipthedishes.com",
        "name": "Admin",
        "address": "Winnipeg, MB",
        "creation": 1522002940000,
        "password": "skip@123"
    }
]
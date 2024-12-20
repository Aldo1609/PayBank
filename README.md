
# PayBank

Technologys used: Java, SpringBoot, Spring Cloud, SQLServer, Redis.

Explanation Video: [Click me!](https://www.youtube.com/watch?v=o1Fd2FWumZo&list=PL-05q_VVK0ajxSZ66HZSd8Kvb5tYcD8My)

![image](https://github.com/user-attachments/assets/7ba4f967-aeca-4e3f-aa58-b3f08893eeec)

## EndPoints

NOTA: Los endpoints se ejecutan desde el APIGateway

```http
POST 'http://localhost:8090/api/cm-service/nuevoCliente'
Service: cm-service

Body:
{
  "nombre": "Aldo",
  "apellido": "Becerra",
  "genero": 1,
  "direccion": "Blv. Juan Lopez 505",
  "telefono": "8110569188",
  "fecRegistro": "2024-12-16T07:39:43.369+00:00"
}
```

```http
PUT 'http://localhost:8090/api/cm-service/actualizarCliente/1'
Service: cm-service

Body:
{
  "nombre": "Aldo",
  "apellido": "Becerra",
  "genero": 1,
  "direccion": "Blv. Juan Lopez 5050",
  "telefono": "8110569188",
  "fecRegistro": "2024-12-16T07:39:43.369+00:00"
}
```

```http
DELETE 'http://localhost:8090/api/cm-service/bajaCliente/1'
Service: cm-service
```

```http
PUT 'http://localhost:8090/api/cm-service/actualizarCuenta/1'
Service: cm-service

Body:
{
  "saldo": 200,
  "deuda": 150,
  "fechaCorte": "2024-12-10T01:59:30.474+00:00",
  "limiteCredito": 5000,
  "activo": true
}
```

```http
DELETE 'http://localhost:8090/api/cm-service/bajaCuenta/1'
Service: cm-service
```

```http
GET 'http://localhost:8090/api/qry-service/datosCliente/1'
Service: qry-service
```

```http
GET 'http://localhost:8090/api/qry-service/datosCuenta/1'
Service: qry-service
```

## 🚀 About Me
I'm a Software Engineer with 2.5 years of experience in Backend development. 

I specialize in Java and have strong proficiency in the Spring Ecosystem

## 🔗 Links

¿Do you want to contact me?
Send me an email!

aldobecerra1609@gmail.com

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/aldo-isaias-becerra-campos-591621200/)




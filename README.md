# Getting Started with Spring boot banking App

This project is the backend for banking application, which uses spring boot.

## Available Scripts

In the project directory, you can run:

### `mvn spring-boot:run`

In postman, you can test the 3 exposed APIs:

### `curl -xxx`

`curl --location 'http://localhost:8080/tsb/banking/accounts?customerNumber=123456&customerUniqueId=CU0001'
`curl --location 'http://localhost:8080/tsb/banking/transactions?accountId=1'`
`curl --location 'http://localhost:8080/tsb/banking/transfer' \
--header 'Content-Type: application/json' \
--data '{
    "accountFromId": 1,
    "accountToId": 2,
    "amount": 100.0
}'`

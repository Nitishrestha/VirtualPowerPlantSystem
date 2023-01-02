
# Virtual Power Plant System

System for aggregating distributed power sources into
a single cloud based energy provider.

## Technologies used

Language: Java

Java Version: 11

Database: MySQL

Framework: Spring Boot, Spring Data JPA, Hibernate

Testing Framework: Mockito, Junit, RestAssured

Application for testing APIs: Postman

## Setup project locally

Firstly, clone the project to your machine

```bash
  git clone https://github.com/Nitishrestha/VirtualPowerPlantSystem.git
```
Then create a database named virtual_power in your MySQL database

```bash
  CREATE DATABASE virtual_power;
```

Then go to the project directory

```bash
  cd VirtualPowerPlantSystem
```
Then install maven dependencies used in project

```bash
  mvn clean install
```
After successful build, a target folder will be created. To navigate to target folder:

```bash
  cd target
```

Start the server with available username and password of MySQL DB

```bash
  java -DDB_HOST=localhost -DDB_NAME=virtual_power -DDB_USERNAME=<username> -DDB_PASSWORD=<password> -DDB_PORT=3306 -jar VirtualPowerPlantSystem-0.0.1-SNAPSHOT.war
```

## API Reference

### 1. Add batteries
```http
  POST /api/v1/batteries
```


| Variable | Type     | Required                |
| :-------- | :------- | :------------------------- |
| `name` | `String` |    Yes |
| `postcode` | `String` |  Yes|
| `capacity` | `String` |  Yes|

#### Post Request JSON Body
```json
 [
    {
    "name": "Bagot",
    "postcode": "820",
    "capacity": "501"
    },
    {
    "name": "Yirrkala",
    "postcode": "880",
    "capacity": "600"
    },
    {
    "name": "University of Melbourne",
    "postcode": "3010",
    "capacity": "700"
    },
    {
    "name": "Norfolk Island",
    "postcode": "2899",
    "capacity": "800"
    }
]

  ```
#### Response after successfully adding batteries
  ```json
   {
    "message": "Batteries has been added successfully."
    }
 ```

 ## 2. Get Statistics of batteries from given range(of battery's postcode)

```http
  GET /api/v1/batteries?from=range1&to=range2
```

| Parameter | Type     | Required | Description|
| :-------- | :------- | :---------- |:---------|
| `from`      | `int` |  Yes|  range1|
| `to`      | `int` | Yes | range2|

#### Request

```http
  /api/v1/batteries?from=500&to=1000
```

#### Response after successfully fetching batteries
```json
{
    "totalBatteries": 4,
    "totalCapacity": 2202,
    "averageCapacity": 550.5,
    "names": [
        "Norfolk Island",
        "Bagot",
        "Ootha",
        "Yirrkala"
    ]
}

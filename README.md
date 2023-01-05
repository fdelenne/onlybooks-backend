# README

## Name

OnlyBooks

## Introduction

This project is aimed to help manage and have control over the library items in the company Grid Dynamics, the items
are- books, newspapers, magazines, and letters each with their unique and common attributes.

#### Requirements

The user has the ability to:

* Add an item.
* Update information of an item.
* Delete an item.
* Print the items that are in the Library.
* Create comments on an item.

#### Validations

* An item can not be added without an author, title, and so on.
* If another one with the same name exists, increase the quantity of that item in 1.
* If the item has more than one, when you delete it it should decrease the quantity in 1.
* An item can not be added with its quantity in 0.

## Technologies

* IntelliJ IDEA 2022.1.2 (Community Edition)
* MariaDB 10.8.3
* Maven 11
* Spring Boot 2.7.4
* Jacoco 0.8.8
* Open API 1.6.12
* SapMachine 11
* Homebrew 3.6.7

## Setup

* For this project you will need to install:

1. Home Brew

```bash
$>curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh
```

2. SapMachine 11:

```bash
$>brew install --cask sapmachine-jdk
```

3. MariaDB 10.8.3

```bash
$>brew install mariadb
```

#### Set up for MariaDB

* Run the following commands

```bash
$> mysql.server start
```

```bash
$> mysql -u root -p
```

```bash
$> ALTER USER 'root'@'localhost' IDENTIFIED BY 'root';
```

```bash
$> mysql -u root -proot
```

```bash
$> CREATE DATABASE demo;
```

4. Maven 11

```bash
$> brew install maven
```

## Run the project

* Clone the repository from Bitbucket.
* Locate the project folder

* Run the following command:

To start the api.

```bash
$> mvn compile
$> mvn test
$> mvn spring-boot:run
```

To add a new item

```bash
$> curl -X 'POST' \
  'http://localhost:8080/api/v1/Item/' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
      "title": "String",
      "author": "String",
      "description": "String",
      "date": "Date",
      "page": int,
      "quantity": int,
      "itemType": "{ItemType}",
      "articles": int,
      "city": "String",
      "urlImage": "String"
    }'```

To get a specific item
```bash
$> curl -X 'GET' \
  'http://localhost:8080/api/v1/Item/{itemType}/{itemId}'
```

To get a specific shelf:

```bash
$> curl -X 'GET' \
  'http://localhost:8080/api/v1/Item/getShelf/{itemType}' 
```

To get all items:

```bash
$> curl -X 'GET' \
  'http://localhost:8080/api/v1/Item/'
```

To delete an item:

```bash
$> curl -X 'DELETE' \
  'http://localhost:8080/api/v1/Item/{itemType}/{itemId}'
```

To update an item:

```bash
$> curl -X 'PUT' \
  'http://localhost:8080/api/v1/Item/{itemType}/{itemId}' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "title": "string",
  "author": "string",
  "date": "2022-12-01",
  "page": 0,
  "quantity": 0,
  "description": "string",
  "urlImage": "string",
  "itemType": "string",
  "publisher": "string",
  "category": "string",
  "isbn": 0
}'
```

To add a new comment:

```bash
$> curl -X 'POST' \
  'http://localhost:8080/api/v1/Comment/{itemId}' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "author": "string",
  "comment": "string"
}'
```

To delete a comment:

```bash
$> curl -X 'DELETE' \
'http://localhost:8080/api/v1/Comment/{itemId}/{commentId}'
```

## Project Status

Project is: in progress, it is currently being worked on resolving some bugs and adding a section for comments on the
items.

## Room for Improvement

### Room for improvement:

* Improvement to be done on the database connection phase

### To do:

* Adding a comment section
* Adding validations on the quantity




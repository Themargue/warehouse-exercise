# Warehouse REST API

## Furniture store warehouse
The project aims to simulate the business logic behind a warehouse system of a furniture web store.
A user will access the web store of the company and see the available products and the quantity available, and should be able to buy a productResponse if available.

Each **Product** will be packed and shipped *ready-to-build*, and will be assembled by the customer. Therefore, the company has to pack together the **Articles** needed to assemble it - e.g., a user orders a table: 1 table top, 4 legs, 8 screws are needed. These are the **Product Specs**: a list of articles needed to assemble a specific productResponse.
N.B.: The company can use the same article for more than one productResponse (e.g. screws).

The articles are indexed in the company **Inventory**, with no association to a specific productResponse, so the software will have to keep track of the availability of each article, and translate that in the user-end availability of a productResponse.

### Software requirements
- User can retrieve the list of available products and their quantities.
- User can buy a productResponse and Inventory will be updated.

## REST API
I chose to build a REST API to be close to a real business scenario.
The API is built with Java 11, Spring Boot and Spring Web. Spring boot is a module that provides rapid application development feature to the spring framework including auto-configuration, standalone-code, and production-ready code.

The API exposes 1 endpoint, covering the 1 out of 2 requirements listed above:

- `GET` endpoint at `/products`.

The API connects to an H2 database which handles Inventory, Product and Product Specs tables. Instead of a volatile in-memory database, the database persists data on a file in the `resources/data` folder, not to lose data with each startup.

![](src/main/resources/static/db.png)

## Improvement points
Tons!

This is a coding exercise to implement part of the business logic. A real-life scenario would have a full-round architecture around this REST API.
Let's focus on the ones of the API - I'll suggest libraries, based on how I would implement them with my knowledge today.

#### Errors and exceptions handling
First thing to fix is to agree on a good practice for error-handling, avoid any NullPointerException, add a Logger and define a default error message structure.

#### Builder pattern for ProductResponse
ProductResponse contains only the product name, product id and the number of available products, cross-calculated with the amount of needed items present in the Inventory.
A good practice for building the response would be implementing the Builder pattern.

#### Testing
- Unit testing service and inventory methods with Java JUnit 5 library.
- Integration testing JSON payload with Mockito library.

#### Redis cache
DB connection is expensive. It should be a standard practice to implement an in-memory cache.

#### Refactoring
I would refactor further:
 - Make methods agnostic of inputs.
 - Implement a better Inventory interface with more inventory methods.
 - Split the Inventory service and Product Specs service - maybe even two different microservices, as I envision the Inventory having way more changes applied, compared to an update on Product Specs.
 - ...

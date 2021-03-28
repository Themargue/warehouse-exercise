# Warehouse REST API

## Furniture store warehouse
The project aims to simulate the business logic behind a warehouse system of a furniture web store.
A user will access the web store of the company and see the available products and the quantity available, and should be able to buy a product if available.

Each **Product** will be packed and shipped *ready-to-build*, and will be assembled by the customer. Therefore, the company has to pack together the **Articles** needed to assemble it - e.g., a user orders a table: 1 table top, 4 legs, 8 screws are needed. These are the **Product Specs**: a list of articles needed to assemble a specific product.
N.B.: The company can use the same article for more than one product (e.g. screws).

The articles are indexed in the company **Inventory**, with no association to a specific product, so the software will have to keep track of the availability of each article, and translate that in the user-end availability of a product.

### Software requirements
- User can retrieve the list of available products and their quantities.
- User can buy a product and Inventory will be updated.

## REST API
I chose to build a REST API to be close to a real business scenario.
The API is built with Java 11, Spring Boot and Spring Web.

The API exposes 2 endpoints, covering the 2 requirements listed above:

- `GET` endpoint at `/products`
- `DELETE` endpoint at `/products/{productId}`

The API loads on startup the JSON files containing the Inventory and the Product Specs.

## Improvement points
Tons!

This is a coding exercise to implement part of the business logic. A real-life scenario would have a full-round architecture around this REST API.
Let's focus on the ones of the API - I'll suggest libraries, based on how I would implement them with my knowledge today.

#### Testing
- Unit testing service and inventory methods with Java JUnit 5 library.
- Integration testing JSON payload with Mockito library.

#### DB and Persistence level
Such a business case couldn't survive without (at least!) a relational DB, where to store and update Product Specs, Articles and Inventory entities.
Next, I would add the Spring Data library (including JPA and Hibernate) and add a Repository layer.

#### Redis cache
DB connection is expensive. It should be a standard practice to implement an in-memory cache.

#### Refactoring
I would refactor further:
 - Make methods agnostic of inputs.
 - Implement a better Inventory interface with more inventory methods.
 - Split the Inventory service and Product Specs service - maybe even two different microservices, as I envision the Inventory having way more changes applied, compared to an update on Product Specs.
 - ...
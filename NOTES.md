# Notes

## `@Transactional` annotation with setter methods

***From stack overflow:***
[How to understand @Transactional with setters in Java?](https://stackoverflow.com/a/71909451/7545010)

Firstly, it is the underlying JPA provider (assume it is Hibernate) to be responsible for updating the entity but not Spring. Spring just provides the integration support with Hibernate.

To update an entity loaded from the DB , generally you need to make sure the following happens in order.

>    1. Begin a DB transaction
>
>    2. Use EntityManager to load the entity that you want to update.The loaded entity is said to be managed by this EntityManager such that it will keep track all the changes made on its state and will generate the necessary update SQL to update this entity in (4) automatically.
>
>    3. Make some changes to the entity 's state. You can do it through any means such as calling any methods on it , not just restricting to calling it by setter
>
>    4. Flush the EntityManager. It will then generate update SQL and send to DB.
>
>    5. Commit the DB transaction

Also note the followings:

> - Spring provides @Transactional which is a declarative way to execute (1) and (5) by annotating it to a method.
> - By default , Hibernate will call (4) automatically before executing (5) such that you do not need to call (4) explicitly.
> - Spring Data JPA repository internally use EntityManager to load the user. So the user return from the repository will be managed by this EntityManager.

So in short , @Transactional is necessary to update the entity. And updating the entity is nothing to do with setter as it just care if there are state changes on the entity in the end , and you can do it without using setter.

## JSON infinite recursion problem

### @JsonIdentityInfo

***Gemini:***

You should consider using `@JsonIdentityInfo` in your Spring Boot application when dealing with **circular references** or **infinite recursion** scenarios within your JSON serialization process. These situations arise when objects within your data model have references to each other, and serializing one object leads to serializing all the interconnected objects, resulting in a never-ending loop.

**Using `@JsonIdentityInfo`:**

This annotation helps break the loop by assigning a unique identifier to each entity during serialization. When an object is encountered again, its identifier is used instead of serializing the entire object again, preventing infinite recursion.

**Benefits of using `@JsonIdentityInfo`:**

- **Prevents infinite recursion:** Ensures your JSON output is finite and well-defined.
- **Improves performance:** Saves processing time and memory resources by avoiding unnecessary serialization.
- **Enhances readability:** Simplifies the JSON output by representing referenced objects with their identifiers.

**Consequences to consider:**

- **Increased complexity:** Requires adding the annotation to your entities and potentially configuring its parameters.
- **Potential performance overhead:** Assigning and managing identifiers can add some overhead, especially for large datasets.
- **Limited customization:** The identifier generation is handled by the annotation, and you might have limited control over its format.

**Alternatives to `@JsonIdentityInfo`:**

- **JSON Views:** Define different views for your entities that exclude circular references or use lazy loading strategies.
- **Custom serialization:** Implement custom serialization logic to handle circular references manually.

**Choosing the right approach:**

The decision to use `@JsonIdentityInfo` depends on your specific needs and trade-offs. If you frequently encounter circular references and require simple and efficient handling, `@JsonIdentityInfo` can be a good choice. However, for more complex scenarios or performance-critical applications, alternative approaches like JSON Views or custom serialization might be more suitable.

**Additional tips:**

- Use `@JsonIdentityReference` on referenced properties to avoid full object serialization.
- Consider using a custom identifier generator if the default one doesn't meet your needs.
- Only use `@JsonIdentityInfo` when necessary, as it might not be always required.

I hope this explanation clarifies the usage and implications of `@JsonIdentityInfo` in your Spring Boot applications. Feel free to ask if you have any further questions!

### Using `@JsonIdentityInfo` vs. using `@JsonManagedReference` and `@JsonBackReference`

Both `@JsonIdentityInfo` and `@JsonManagedReference/@JsonBackReference` annotations help prevent infinite recursion in JSON serialization when dealing with circular references in Spring Boot applications. However, they approach the problem differently and have distinct advantages and drawbacks:

**@JsonIdentityInfo:**

- **Functionality:** Assigns a unique identifier to each entity during serialization. Subsequent references to the same entity use the identifier instead of serializing the entire object again.
- **Advantages:**
  - Simpler to use, requires only one annotation per entity.
  - Efficient for large datasets due to identifier-based referencing.
  - Less intrusive, works with various relationship types.
- **Disadvantages:**
  - Limited control over identifier format and generation strategy.
  - Might add some overhead for assigning and managing identifiers.
  - Not as customizable as manual approaches.

**@JsonManagedReference/@JsonBackReference:**

- **Functionality:** Mark related entities as "managed" or "back-referenced" to control their inclusion during serialization. "Managed" entity is fully serialized, while "back-referenced" is omitted to avoid recursion.
- **Advantages:**
  - Offers more granular control over reference inclusion.
  - Can be combined with other serialization annotations for flexibility.
  - No identifier overhead.
- **Disadvantages:**
  - Requires annotations on both sides of the relationship (managed and back-referenced).
  - Can be more complex to set up and maintain for intricate relationships.
  - Might not work flawlessly with all relationship types.

**Choosing the right approach:**

- **Use `@JsonIdentityInfo` for:**
  - Simple scenarios with straightforward circular references.
  - Performance-critical applications where identifier-based referencing is efficient.
  - Limited need for customization over identifier generation.
- **Use `@JsonManagedReference/@JsonBackReference` for:**
  - Complex relationships requiring specific control over reference inclusion.
  - Situations where identifiers don't fit the desired serialization behavior.
  - Integration with other custom serialization logic.

Remember, both approaches have their merits and limitations. Evaluate your specific use case, complexity, and desired control level to make the best choice for your Spring Boot application.

## Sprinted GPA repository interface methods naming conventions

## Spring Data JPA Repository Method Naming Conventions

Spring Data JPA provides a convenient way to define repository methods by following a naming convention. This allows you to express queries through method names, eliminating the need to write explicit SQL or JPQL queries for common operations. Additionally, following these conventions improves code readability and maintainability.

Here's an overview of the key aspects:

**Basic Structure:**

```java
public interface MyEntityRepository extends JpaRepository<MyEntity, Long> {

  // Your custom methods using naming conventions

}
```

**Commonly Used Prefixes:**

- **findBy**: Used to fetch entities based on specific property values.
- **getBy**: Similar to `findBy`, but typically returns a single entity.
- **readBy**: Similar to `findBy`, but can be used for more specific retrieval logic.
- **queryBy**: For more complex queries involving multiple properties or conditions.
- **countBy**: Returns the number of entities matching a specific criteria.
- **existsBy**: Checks if an entity exists based on specific property values.
- **deleteBy**: Deletes entities based on specific property values.

**Example Methods:**

- `findByUsername(String username)`: Finds all entities with the given username.
- `getByEmailIgnoreCase(String email)`: Finds a single entity based on the email address, ignoring case sensitivity.
- `readByActiveAndCreatedDateAfter(boolean active, Date date)`: Retrieves entities that are active and created after the provided date.
- `queryByFirstNameStartingWithAndLastName(String firstNamePrefix, String lastNamePart)`: Executes a more complex query based on multiple conditions.
- `countByAgeGreaterThan(int age)`: Returns the number of entities with age greater than the specified value.
- `existsByPhoneNumber(String phoneNumber)`: Checks if an entity exists with the given phone number.
- `deleteByStatus(String status)`: Deletes entities with the specified status.

**Benefits of using naming conventions:**

- **Improved code readability:** Method names clearly convey their purpose.
- **Reduced boilerplate code:** No need to write verbose SQL or JPQL queries.
- **Maintainability:** Consistent naming conventions ease understanding and code modifications.
- **Integration with tools:** IDEs and code analysis tools can leverage the conventions for better support.

Remember, these are just some common examples. Spring Data JPA supports various other naming conventions and custom query creation options. Refer to the official documentation for a comprehensive guide: [[invalid URL removed]]([invalid URL removed])

Feel free to ask if you have any further questions or specific scenarios you'd like to discuss regarding Spring Data JPA repository methods!

## Use `@Putmapping` to do update operations

PoliJun: @workspace This is a shopping app . In real world, I want to implement an order function. In an order, there are items of products and their amounts. Give me the code for Entities I need.

GitHub Copilot: Determining workspace structure

Deciding which workspace information to collect

Gathering workspace info

Based on your requirements, you would need an [`Order`](command:_github.copilot.openSymbolInFile?%5B%22src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fpolijun%2Fwavup%2Fmodel%2FOrder.java%22%2C%22Order%22%5D "src/main/java/com/github/polijun/wavup/model/Order.java") entity, a [`Product`](command:_github.copilot.openSymbolInFile?%5B%22src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fpolijun%2Fwavup%2Fmodel%2FProduct.java%22%2C%22Product%22%5D "src/main/java/com/github/polijun/wavup/model/Product.java") entity, and an `OrderItem` entity to represent the relationship between an order and its products, including the quantity of each product.

Here's a basic example of what these entities might look like:

[`src/main/java/com/github/polijun/wavup/model/Order.java`](command:_github.copilot.openRelativePath?%5B%22src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fpolijun%2Fwavup%2Fmodel%2FOrder.java%22%5D "src/main/java/com/github/polijun/wavup/model/Order.java")

```java
@Entity(name = "Order")
@Table(name = "order")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
```

[`src/main/java/com/github/polijun/wavup/model/Product.java`](command:_github.copilot.openRelativePath?%5B%22src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fpolijun%2Fwavup%2Fmodel%2FProduct.java%22%5D "src/main/java/com/github/polijun/wavup/model/Product.java")

```java
@Entity(name = "Product")
@Table(name = "product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private BigDecimal price;
    private String description;
    private Integer inventory;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;
}
```

`src/main/java/com/github/polijun/wavup/model/OrderItem.java`

```java
@Entity(name = "OrderItem")
@Table(name = "order_item")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;
}
```

In this setup, an [`Order`](command:_github.copilot.openSymbolInFile?%5B%22src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fpolijun%2Fwavup%2Fmodel%2FOrder.java%22%2C%22Order%22%5D "src/main/java/com/github/polijun/wavup/model/Order.java") has many `OrderItem`s, each of which represents a [`Product`](command:_github.copilot.openSymbolInFile?%5B%22src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fpolijun%2Fwavup%2Fmodel%2FProduct.java%22%2C%22Product%22%5D "src/main/java/com/github/polijun/wavup/model/Product.java") and the quantity of that product in the order. This allows you to have multiple different products in a single order, each with its own quantity.

PoliJun: @workspace give me a json to test this create api

GitHub Copilot: Determining workspace structure

Deciding which workspace information to collect

PoliJun: @workspace give me a json to test this create api

GitHub Copilot: Determining workspace structure

Deciding which workspace information to collect

Gathering workspace info

Based on the `createProduct` method in your [`ProductController`](command:_github.copilot.openSymbolInFile?%5B%22src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fpolijun%2Fwavup%2Fcontroller%2FProductController.java%22%2C%22ProductController%22%5D "src/main/java/com/github/polijun/wavup/controller/ProductController.java"), it seems like you need to provide a [`Product`](command:_github.copilot.openSymbolInFile?%5B%22src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fpolijun%2Fwavup%2Fmodel%2FProduct.java%22%2C%22Product%22%5D "src/main/java/com/github/polijun/wavup/model/Product.java") object in your JSON. Here's an example of how you might structure your JSON:

```json
{
  "name": "Product Name",
  "brand": "Product Brand",
  "price": 99.99,
  "description": "Product Description",
  "inventory": 100,
  "styles": [
    {
      "id": 1,
      "styleName": "Style1"
    },
    {
      "id": 2,
      "styleName": "Style2"
    }
  ]
}
```

Please replace the values with the actual data you want to use for testing. Note that the [`styles`](command:_github.copilot.openSymbolInFile?%5B%22src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fpolijun%2Fwavup%2Fmodel%2FProduct.java%22%2C%22styles%22%5D "src/main/java/com/github/polijun/wavup/model/Product.java") field is an array of [`Style`](command:_github.copilot.openSymbolInFile?%5B%22src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fpolijun%2Fwavup%2Fmodel%2FStyle.java%22%2C%22Style%22%5D "src/main/java/com/github/polijun/wavup/model/Style.java") objects, each with an [`id`](command:_github.copilot.openSymbolInFile?%5B%22src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fpolijun%2Fwavup%2Fmodel%2FProduct.java%22%2C%22id%22%5D "src/main/java/com/github/polijun/wavup/model/Product.java") and [`styleName`](command:_github.copilot.openSymbolInFile?%5B%22src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fpolijun%2Fwavup%2Fmodel%2FStyle.java%22%2C%22styleName%22%5D "src/main/java/com/github/polijun/wavup/model/Style.java"). If your [`Style`](command:_github.copilot.openSymbolInFile?%5B%22src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fpolijun%2Fwavup%2Fmodel%2FStyle.java%22%2C%22Style%22%5D "src/main/java/com/github/polijun/wavup/model/Style.java") objects don't exist yet in the database, you might need to create them first before you can associate them with a [`Product`](command:_github.copilot.openSymbolInFile?%5B%22src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fpolijun%2Fwavup%2Fmodel%2FProduct.java%22%2C%22Product%22%5D "src/main/java/com/github/polijun/wavup/model/Product.java").

PoliJun: @vscode see memory status in spring boot dashboard

GitHub Copilot: Searching doc index....

To see the memory status in the Spring Boot Dashboard, you need to enable the JMX connection for your Spring Boot application. This can be done by adding the following JVM arguments when starting your application:

```plaintext
-Dcom.sun.management.jmxremote 
-Dcom.sun.management.jmxremote.port=9010 
-Dcom.sun.management.jmxremote.rmi.port=9010 
-Dcom.sun.management.jmxremote.authenticate=false 
-Dcom.sun.management.jmxremote.ssl=false 
-Djava.rmi.server.hostname=localhost
```

After you've started your application with these JVM arguments, you can connect to your application using the JMX connect feature in the Spring Boot Dashboard. Once connected, you will be able to see the memory status and other useful metrics of your application.

Please note that exposing the JMX port without any authentication can be a security risk. In a production environment, you should secure the JMX port with authentication and SSL.

Unfortunately, there is no specific command in VS Code to directly view the memory status in the Spring Boot Dashboard.

PoliJun: @workspace I have service interfaces corresponding entities, but I will have some bussiness logic combine multiple services. Is it a good practice to write them in controller layer?

GitHub Copilot: Determining workspace structure

Deciding which workspace information to collect

Gathering workspace info

No, it's not a good practice to write business logic in the controller layer. The controller layer should be kept as thin as possible and should only be responsible for handling HTTP requests and responses.

Business logic should be encapsulated in the service layer. This way, the business logic can be reused across different controllers and it's easier to maintain and test.

For example, in your workspace, you have a [`ProductService`](command:_github.copilot.openSymbolInFile?%5B%22src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fpolijun%2Fwavup%2Fservice%2FProductService.java%22%2C%22ProductService%22%5D "src/main/java/com/github/polijun/wavup/service/ProductService.java") and a [`StyleService`](command:_github.copilot.openSymbolInFile?%5B%22src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fpolijun%2Fwavup%2Fservice%2FStyleService.java%22%2C%22StyleService%22%5D "src/main/java/com/github/polijun/wavup/service/StyleService.java"). If you have business logic that combines these two services, you should create a new service that uses both [`ProductService`](command:_github.copilot.openSymbolInFile?%5B%22src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fpolijun%2Fwavup%2Fservice%2FProductService.java%22%2C%22ProductService%22%5D "src/main/java/com/github/polijun/wavup/service/ProductService.java") and [`StyleService`](command:_github.copilot.openSymbolInFile?%5B%22src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fpolijun%2Fwavup%2Fservice%2FStyleService.java%22%2C%22StyleService%22%5D "src/main/java/com/github/polijun/wavup/service/StyleService.java").

Here's a simple example:

```java
@Service
public class CombinedService {
    private final ProductService productService;
    private final StyleService styleService;

    // constructor injection
    public CombinedService(ProductService productService, StyleService styleService) {
        this.productService = productService;
        this.styleService = styleService;
    }

    public void someBusinessLogic() {
        // use productService and styleService
    }
}
```

Then, in your controller, you can use `CombinedService`:

```java
@RestController
@RequestMapping("/combined")
public class CombinedController {
    private final CombinedService combinedService;

    public CombinedController(CombinedService combinedService) {
        this.combinedService = combinedService;
    }

    @GetMapping
    public ResponseEntity<?> handleRequest() {
        combinedService.someBusinessLogic();
        return ResponseEntity.ok().build();
    }
}
```

This way, your business logic is encapsulated in the service layer and your controller is only responsible for handling HTTP requests and responses.

PoliJun: @workspace /explain  Does this endpoint alway return 200?

GitHub Copilot: The endpoint `/all` in your [`UserController`](command:_github.copilot.openSymbolInFile?%5B%22src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fpolijun%2Fwavup%2Fsecurity%2Fuser%2FUserController.java%22%2C%22UserController%22%5D "src/main/java/com/github/polijun/wavup/security/user/UserController.java") will always attempt to return a 200 OK HTTP status code as long as the [`userService.getAllUsers()`](command:_github.copilot.openSymbolInFile?%5B%22src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fpolijun%2Fwavup%2Fsecurity%2Fuser%2FUserController.java%22%2C%22userService.getAllUsers()%22%5D "src/main/java/com/github/polijun/wavup/security/user/UserController.java") method executes without throwing any exceptions.

The `ResponseEntity.ok().body(userService.getAllUsers())` statement constructs a `ResponseEntity` with a status code of 200 OK and the list of users as the body.

However, if [`userService.getAllUsers()`](command:_github.copilot.openSymbolInFile?%5B%22src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fpolijun%2Fwavup%2Fsecurity%2Fuser%2FUserController.java%22%2C%22userService.getAllUsers()%22%5D "src/main/java/com/github/polijun/wavup/security/user/UserController.java") throws an exception (for example, if there's an issue with the database), the controller method will fail, and depending on your exception handling setup, a different status code might be returned.

If you want to handle potential exceptions and return a specific status code in those cases, you could use a try-catch block:

```java
@GetMapping("/all")
public ResponseEntity<?> getAllUsers() {
    try {
        return ResponseEntity.ok().body(userService.getAllUsers());
    } catch (Exception e) {
        // log the exception and return a different status code
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
```

In this example, if [`userService.getAllUsers()`](command:_github.copilot.openSymbolInFile?%5B%22src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fpolijun%2Fwavup%2Fsecurity%2Fuser%2FUserController.java%22%2C%22userService.getAllUsers()%22%5D "src/main/java/com/github/polijun/wavup/security/user/UserController.java") throws an exception, the server will return a 500 Internal Server Error status code.

PoliJun: Should I use post mapping or Putin mapping to do update operation

GitHub Copilot: For update operations, the HTTP method that is typically used is `PUT`. This is because `PUT` is idempotent, meaning that making the same request multiple times will have the same effect as making it once. This is ideal for update operations, where you want to set the state of a resource to a specific value.

Here's an example of how you might use `PUT` in a Spring controller:

```java
@PutMapping("/products/{id}")
public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product) {
    Product existingProduct = productService.getProduct(id);
    if (existingProduct == null) {
        return ResponseEntity.notFound().build();
    }
    existingProduct.setName(product.getName());
    existingProduct.setPrice(product.getPrice());
    // update other fields as necessary
    productService.saveProduct(existingProduct);
    return ResponseEntity.ok().build();
}
```

In this example, the `updateProduct` method takes an `id` and a `Product` object as parameters. It first checks if a product with the given `id` exists. If it does, it updates the fields of the existing product with the values from the provided `Product` object, saves the updated product, and returns a 200 OK status code. If a product with the given `id` does not exist, it returns a 404 Not Found status code.

However, if you want to partially update a resource (i.e., update some but not all fields), you might want to use the `PATCH` method instead. The `PATCH` method is not idempotent and is used to apply partial modifications to a resource.

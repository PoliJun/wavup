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
- `queryByFirstNameStartingWithAndLastNameContaining(String firstNamePrefix, String lastNamePart)`: Executes a more complex query based on multiple conditions.
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

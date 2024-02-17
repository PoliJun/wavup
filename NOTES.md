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

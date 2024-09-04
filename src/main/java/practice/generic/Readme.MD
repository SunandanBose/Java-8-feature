# InMemoryDb

`InMemoryDb<K>` is a generic in-memory database implementation using a `ConcurrentHashMap`. This class provides basic CRUD (Create, Read, Update, Delete) operations for storing and managing key-value pairs, where the keys are `String` and the values can be of any type specified by the generic parameter `K`.

The `InMemoryDb` class is thread-safe, meaning it can be used safely in concurrent environments.

## Table of Contents
- [Generic Implementation](#generic-implementation)
- [Usage](#usage)
- [Methods](#methods)
- [Exceptions](#exceptions)

## Generic Implementation

The `InMemoryDb<K>` class is designed to be flexible and reusable for any type of value. The generic type `K` allows you to specify the type of objects that the database will store.

### Example with Different Types

```java
// String as the value type
InMemoryDb<String> stringDb = new InMemoryDb<>();
stringDb.create("key1", "Hello");

// Integer as the value type
InMemoryDb<Integer> intDb = new InMemoryDb<>();
intDb.create("key2", 42);

// Custom Object as the value type
class User {
    String name;
    int age;
    // constructor, getters, setters
}

InMemoryDb<User> userDb = new InMemoryDb<>();
userDb.create("user1", new User("Alice", 30));

# 02 — Functional Interfaces & Lambda Expressions

---

## 📖 Theory

### What Is a Functional Interface?

A **functional interface** is an interface that has **exactly one abstract method**. It may also have `default` or `static` methods, but only one abstract method. Functional interfaces can be annotated with `@FunctionalInterface` to enforce this at compile time.

```java
@FunctionalInterface
interface Greeter {
    void greet(String name);    // exactly one abstract method
    // default methods are fine
    default void greetAll(String[] names) {
        for (String n : names) greet(n);
    }
}
```

---

### What Is a Lambda Expression?

A **lambda expression** is a concise way to implement a functional interface — essentially an anonymous function. It replaces verbose anonymous class syntax.

**Syntax:**
```java
(parameters) -> expression
(parameters) -> { statements; }
```

```java
// Anonymous class way:
Greeter g1 = new Greeter() {
    @Override
    public void greet(String name) {
        System.out.println("Hello, " + name);
    }
};

// Lambda way (same thing, much shorter):
Greeter g2 = name -> System.out.println("Hello, " + name);
```

---

### Lambda Syntax Variations

```java
// No parameters
() -> System.out.println("Hello!")

// One parameter (parentheses optional)
x -> x * x
(x) -> x * x

// Multiple parameters
(x, y) -> x + y

// Multiple statements (use braces + return)
(x, y) -> {
    int sum = x + y;
    return sum * 2;
}

// With explicit types (optional)
(int x, int y) -> x + y
```

---

### Built-in Functional Interfaces (java.util.function)

Java 8 provides a rich set of pre-built functional interfaces:

| Interface | Abstract Method | Description | Example |
|---|---|---|---|
| `Predicate<T>` | `boolean test(T t)` | Tests a condition | `n -> n > 0` |
| `Function<T, R>` | `R apply(T t)` | Transforms T to R | `s -> s.length()` |
| `Consumer<T>` | `void accept(T t)` | Consumes a value | `s -> System.out.println(s)` |
| `Supplier<T>` | `T get()` | Supplies a value | `() -> new ArrayList<>()` |
| `BiFunction<T,U,R>` | `R apply(T t, U u)` | Takes two inputs | `(a, b) -> a + b` |
| `UnaryOperator<T>` | `T apply(T t)` | Same type in/out | `s -> s.toUpperCase()` |
| `BinaryOperator<T>` | `T apply(T t1, T t2)` | Two same-type inputs | `(a, b) -> a + b` |
| `Runnable` | `void run()` | No input, no output | `() -> doSomething()` |

---

### Method References

Method references are a shorthand for lambdas that simply call an existing method:

```java
// Lambda                              // Method Reference
s -> System.out.println(s)     →      System.out::println
s -> s.toUpperCase()            →      String::toUpperCase
() -> new ArrayList<>()         →      ArrayList::new
(a, b) -> Integer.compare(a, b) →     Integer::compare
```

| Type | Syntax | Example |
|---|---|---|
| Static method | `Class::staticMethod` | `Math::abs` |
| Instance method (on object) | `instance::method` | `list::add` |
| Instance method (on type) | `Class::instanceMethod` | `String::length` |
| Constructor | `Class::new` | `Person::new` |

---

### Lambdas with Collections

The most common use of lambdas is with the Collections API and Streams:

```java
List<String> names = Arrays.asList("Charlie", "Alice", "Bob");

// Sort with lambda
names.sort((a, b) -> a.compareTo(b));

// Sort with method reference
names.sort(String::compareTo);

// forEach with lambda
names.forEach(name -> System.out.println(name));

// forEach with method reference
names.forEach(System.out::println);

// removeIf with lambda (Predicate)
names.removeIf(name -> name.startsWith("A"));
```

---

## 🧪 Practice Questions

1. What is a functional interface? What annotation is used to mark it?
2. What is a lambda expression? Write one that takes two integers and returns their product.
3. Can a functional interface have more than one abstract method?
4. What is the difference between `Predicate`, `Function`, and `Consumer`?
5. Write the lambda equivalent of: printing each element in a list of strings.
6. What is a method reference? Convert `x -> Math.abs(x)` to a method reference.
7. When would you use `Supplier<T>`? Give a practical example.
8. What does `names.removeIf(s -> s.length() < 4)` do?

---

## 💻 Examples

### Example 1 – Custom Functional Interface

```java
public class LambdaBasics {

    @FunctionalInterface
    interface MathOperation {
        double operate(double a, double b);
    }

    static double calculate(double a, double b, MathOperation op) {
        return op.operate(a, b);
    }

    public static void main(String[] args) {
        MathOperation add      = (a, b) -> a + b;
        MathOperation subtract = (a, b) -> a - b;
        MathOperation multiply = (a, b) -> a * b;
        MathOperation divide   = (a, b) -> b != 0 ? a / b : 0;
        MathOperation power    = (a, b) -> Math.pow(a, b);

        System.out.println("10 + 3  = " + calculate(10, 3, add));
        System.out.println("10 - 3  = " + calculate(10, 3, subtract));
        System.out.println("10 * 3  = " + calculate(10, 3, multiply));
        System.out.println("10 / 3  = " + String.format("%.2f", calculate(10, 3, divide)));
        System.out.println("2 ^ 8   = " + (int) calculate(2, 8, power));
    }
}
```

**Output:**
```
10 + 3  = 13.0
10 - 3  = 7.0
10 * 3  = 30.0
10 / 3  = 3.33
2 ^ 8   = 256
```

---

### Example 2 – Built-in Functional Interfaces

```java
import java.util.function.*;

public class BuiltInFunctionals {
    public static void main(String[] args) {
        // Predicate<T> — test a condition
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<String> isLong = s -> s.length() > 5;
        System.out.println("4 is even: " + isEven.test(4));
        System.out.println("4 is odd: " + isEven.negate().test(4));
        System.out.println("'Hello' is long: " + isLong.test("Hello"));

        // Function<T, R> — transform
        Function<String, Integer> strLen = String::length;
        Function<Integer, String> intToStr = n -> "Number: " + n;
        System.out.println("Length of 'Java': " + strLen.apply("Java"));
        System.out.println(intToStr.apply(42));

        // Function chaining
        Function<String, String> pipeline = strLen.andThen(intToStr);
        System.out.println(pipeline.apply("Lambda"));  // length then format

        // Consumer<T> — consume, no return
        Consumer<String> printer = s -> System.out.println(">> " + s);
        printer.accept("Hello, Lambda!");

        // Supplier<T> — provide a value
        Supplier<Double> random = Math::random;
        System.out.printf("Random: %.4f%n", random.get());

        // BinaryOperator
        BinaryOperator<Integer> max = (a, b) -> a > b ? a : b;
        System.out.println("Max(7, 12): " + max.apply(7, 12));
    }
}
```

**Output:**
```
4 is even: true
4 is odd: false
'Hello' is long: false
Length of 'Java': 4
Number: 42
Number: 6
>> Hello, Lambda!
Random: 0.7341
Max(7, 12): 12
```

---

### Example 3 – Lambdas with Lists

```java
import java.util.*;
import java.util.function.*;

public class LambdaWithLists {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>(
            Arrays.asList("Banana", "Apple", "Mango", "Cherry", "Kiwi", "Avocado")
        );

        System.out.println("Original: " + fruits);

        // Sort alphabetically
        fruits.sort((a, b) -> a.compareTo(b));
        System.out.println("Sorted: " + fruits);

        // Sort by length
        fruits.sort(Comparator.comparingInt(String::length));
        System.out.println("By length: " + fruits);

        // Remove short names
        fruits.removeIf(f -> f.length() < 5);
        System.out.println("Long names only: " + fruits);

        // Print all with forEach
        System.out.println("\nFruits:");
        fruits.forEach(f -> System.out.println("  🍎 " + f));

        // Transform each
        System.out.println("\nUppercase:");
        fruits.stream()
              .map(String::toUpperCase)
              .forEach(f -> System.out.println("  " + f));
    }
}
```

**Output:**
```
Original: [Banana, Apple, Mango, Cherry, Kiwi, Avocado]
Sorted: [Apple, Avocado, Banana, Cherry, Kiwi, Mango]
By length: [Kiwi, Apple, Mango, Banana, Cherry, Avocado]
Long names only: [Apple, Mango, Banana, Cherry, Avocado]

Fruits:
  🍎 Apple
  🍎 Mango
  🍎 Banana
  🍎 Cherry
  🍎 Avocado

Uppercase:
  APPLE
  MANGO
  BANANA
  CHERRY
  AVOCADO
```

---

### Example 4 – Method References

```java
import java.util.*;

public class MethodRefDemo {

    static class Student {
        String name;
        double gpa;

        Student(String name, double gpa) {
            this.name = name;
            this.gpa = gpa;
        }

        String getName() { return name; }
        double getGpa()  { return gpa; }

        @Override
        public String toString() {
            return String.format("%-12s GPA: %.2f", name, gpa);
        }

        static int compareByGpa(Student a, Student b) {
            return Double.compare(b.gpa, a.gpa);  // descending
        }
    }

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Alice", 3.9),
            new Student("Bob", 3.5),
            new Student("Charlie", 3.7),
            new Student("Diana", 3.8)
        );

        // Static method reference
        students.sort(Student::compareByGpa);
        System.out.println("Ranked by GPA:");
        students.forEach(System.out::println);   // instance method ref on System.out

        // Instance method reference on type
        System.out.println("\nNames only:");
        students.stream()
                .map(Student::getName)
                .forEach(System.out::println);
    }
}
```

**Output:**
```
Ranked by GPA:
Alice        GPA: 3.90
Diana        GPA: 3.80
Charlie      GPA: 3.70
Bob          GPA: 3.50

Names only:
Alice
Diana
Charlie
Bob
```

---

## 📝 Summary

- A **functional interface** has exactly one abstract method; mark with `@FunctionalInterface`.
- A **lambda expression** `(params) -> body` is a concise way to implement a functional interface.
- Key built-ins: `Predicate` (test), `Function` (transform), `Consumer` (consume), `Supplier` (supply).
- **Method references** (`Class::method`) are even more concise lambdas that call existing methods.
- Lambdas enable cleaner, more expressive code when working with collections and streams.

---

*Previous → [01 – Nested Classes](./01_NestedClasses.md)*  
*Next → [03 – Working with Dates](./03_Dates.md)*

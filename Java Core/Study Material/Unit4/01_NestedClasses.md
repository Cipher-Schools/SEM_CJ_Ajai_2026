# 01 — Nested Classes: Static, Non-static, Local & Anonymous

---

## 📖 Theory

### What Is a Nested Class?

A **nested class** is a class defined inside another class. Java supports four types:

```
Nested Classes
├── Static Nested Class       (declared with static)
├── Inner Class               (non-static nested class)
├── Local Class               (inside a method)
└── Anonymous Class           (no name, declared and instantiated together)
```

Why use nested classes?
- Logically group classes that belong together
- Increase encapsulation
- Improve readability and maintainability

---

### 1. Static Nested Class

Declared with the `static` keyword. It does **not** have access to instance members of the outer class — it behaves like a top-level class that happens to live inside another.

```java
class Outer {
    static int outerStatic = 10;
    int outerInstance = 20;

    static class StaticNested {
        void display() {
            System.out.println("Static member: " + outerStatic);
            // System.out.println(outerInstance);  // ERROR! No access to instance
        }
    }
}

// Create without an Outer object:
Outer.StaticNested obj = new Outer.StaticNested();
obj.display();
```

**Use case:** Helper classes, builder patterns, grouped utility classes.

---

### 2. Inner Class (Non-static Nested Class)

Has access to **all** members (including `private`) of the outer class — it needs an outer class instance to exist.

```java
class Outer {
    private int value = 100;

    class Inner {
        void display() {
            System.out.println("Outer value: " + value);  // direct access!
        }
    }
}

// Must create Inner via an Outer instance:
Outer outer = new Outer();
Outer.Inner inner = outer.new Inner();
inner.display();
```

**Use case:** Iterator implementations, event handlers that need outer class state.

---

### Static vs Non-static Nested — Key Differences

| Feature | Static Nested | Inner (Non-static) |
|---|---|---|
| Access to outer instance members | ❌ | ✅ |
| Access to outer static members | ✅ | ✅ |
| Needs outer class instance | ❌ | ✅ |
| Can have static members | ✅ | ❌ |
| Memory | Independent | Holds reference to outer |

---

### 3. Local Class

Defined **inside a method**. Only accessible within that method.

```java
class Processor {
    void process(int[] data) {
        // Local class — only visible inside this method
        class Formatter {
            String format(int n) {
                return "[" + n + "]";
            }
        }

        Formatter f = new Formatter();
        for (int d : data) {
            System.out.print(f.format(d) + " ");
        }
        System.out.println();
    }
}
```

**Use case:** Encapsulating logic that's only relevant to a single method.

---

### 4. Anonymous Class

A class with **no name** — declared and instantiated in a single expression. Commonly used to provide one-time implementations of interfaces or abstract classes.

```java
interface Greeter {
    void greet(String name);
}

// Anonymous class implementing Greeter
Greeter formalGreeter = new Greeter() {
    @Override
    public void greet(String name) {
        System.out.println("Good day, " + name + ". How do you do?");
    }
};

formalGreeter.greet("Alice");

// Another anonymous class — same interface, different behaviour
Greeter casualGreeter = new Greeter() {
    @Override
    public void greet(String name) {
        System.out.println("Hey " + name + "! What's up?");
    }
};

casualGreeter.greet("Bob");
```

**Use case:** GUI event handlers, comparators, quick one-off interface implementations.

> In modern Java (8+), anonymous classes for functional interfaces are replaced by **lambda expressions**.

---

## 🧪 Practice Questions

1. What are the four types of nested classes in Java?
2. What is the main difference between a static nested class and an inner class?
3. Why does an inner class need an instance of the outer class to be created?
4. Can a non-static inner class have static members?
5. What is a local class? Where can it be defined?
6. What is an anonymous class? When is it useful?
7. How would you create an instance of a static nested class vs an inner class?
8. How did lambda expressions change the need for anonymous classes?

---

## 💻 Examples

### Example 1 – Static Nested Class (Builder Pattern)

```java
public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private String email;

    private Person() {}

    @Override
    public String toString() {
        return "Person{" + firstName + " " + lastName + ", age=" + age + ", email=" + email + "}";
    }

    // Static nested Builder class
    public static class Builder {
        private Person person = new Person();

        public Builder firstName(String name) { person.firstName = name; return this; }
        public Builder lastName(String name)  { person.lastName = name; return this; }
        public Builder age(int age)           { person.age = age; return this; }
        public Builder email(String email)    { person.email = email; return this; }

        public Person build() { return person; }
    }

    public static void main(String[] args) {
        Person p = new Person.Builder()
            .firstName("Alice")
            .lastName("Johnson")
            .age(28)
            .email("alice@example.com")
            .build();

        System.out.println(p);
    }
}
```

**Output:**
```
Person{Alice Johnson, age=28, email=alice@example.com}
```

---

### Example 2 – Inner Class (Iterator-like)

```java
public class NumberRange {
    private int start;
    private int end;

    NumberRange(int start, int end) {
        this.start = start;
        this.end = end;
    }

    // Non-static inner class — needs NumberRange instance
    class RangeIterator {
        private int current;

        RangeIterator() { current = start; }   // access outer field directly

        boolean hasNext() { return current <= end; }

        int next() { return current++; }
    }

    public static void main(String[] args) {
        NumberRange range = new NumberRange(1, 5);
        NumberRange.RangeIterator iter = range.new RangeIterator();

        System.out.print("Range [1-5]: ");
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
    }
}
```

**Output:**
```
Range [1-5]: 1 2 3 4 5 
```

---

### Example 3 – Anonymous Class vs Lambda

```java
import java.util.Arrays;
import java.util.List;

public class AnonymousDemo {

    interface Transformer {
        int transform(int n);
    }

    static void applyAndPrint(List<Integer> nums, Transformer t) {
        for (int n : nums) {
            System.out.print(t.transform(n) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Anonymous class
        System.out.print("Squares (anon class): ");
        applyAndPrint(numbers, new Transformer() {
            @Override
            public int transform(int n) { return n * n; }
        });

        // Same thing with lambda (Java 8+) — much shorter
        System.out.print("Squares (lambda):     ");
        applyAndPrint(numbers, n -> n * n);

        System.out.print("Cubes (lambda):       ");
        applyAndPrint(numbers, n -> n * n * n);
    }
}
```

**Output:**
```
Squares (anon class): 1 4 9 16 25 
Squares (lambda):     1 4 9 16 25 
Cubes (lambda):       1 8 27 64 125 
```

---

### Example 4 – Local Class

```java
public class LocalClassDemo {

    static void validatePasswords(String[] passwords) {

        // Local class — only visible inside this method
        class PasswordValidator {
            private static final int MIN_LENGTH = 8;

            boolean isValid(String pwd) {
                if (pwd.length() < MIN_LENGTH) return false;
                boolean hasUpper = false, hasDigit = false;
                for (char c : pwd.toCharArray()) {
                    if (Character.isUpperCase(c)) hasUpper = true;
                    if (Character.isDigit(c)) hasDigit = true;
                }
                return hasUpper && hasDigit;
            }

            String evaluate(String pwd) {
                return isValid(pwd) ? "STRONG" : "WEAK";
            }
        }

        PasswordValidator validator = new PasswordValidator();
        System.out.printf("%-20s %s%n", "Password", "Strength");
        System.out.println("-".repeat(30));
        for (String pwd : passwords) {
            System.out.printf("%-20s %s%n", pwd, validator.evaluate(pwd));
        }
    }

    public static void main(String[] args) {
        String[] passwords = {"hello", "Password1", "abc123", "Secure99X", "qwerty"};
        validatePasswords(passwords);
    }
}
```

**Output:**
```
Password             Strength
------------------------------
hello                WEAK
Password1            STRONG
abc123               WEAK
Secure99X            STRONG
qwerty               WEAK
```

---

## 📝 Summary

- **Static nested class** — belongs to the outer class (like a static member), no access to instance members of outer, created without an outer instance.
- **Inner class (non-static)** — has full access to outer class members, requires outer instance to create.
- **Local class** — defined inside a method, only accessible within that method.
- **Anonymous class** — no name, declared and instantiated together, used for one-time implementations.
- In modern Java, lambda expressions have largely replaced anonymous classes for functional interfaces.

---

*Next → [02 – Functional Interfaces & Lambda Expressions](./02_Lambda_FunctionalInterface.md)*

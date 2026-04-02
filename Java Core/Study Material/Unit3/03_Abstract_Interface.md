# 03 — Abstract Classes and Interfaces

---

## 📖 Theory

### Abstract Methods and Abstract Classes

An **abstract method** is a method declared without a body — it only has a signature. It represents a capability that every subclass must provide its own implementation of.

An **abstract class** is a class that cannot be instantiated directly. It may contain:
- Abstract methods (no body)
- Concrete methods (with body)
- Fields and constructors

```java
abstract class Shape {
    String color;

    Shape(String color) { this.color = color; }

    abstract double area();           // abstract — no body
    abstract double perimeter();      // abstract — no body

    void display() {                  // concrete — has body
        System.out.println("Color: " + color + " | Area: " + area());
    }
}
```

**Key rules:**
- Cannot create an object of an abstract class: `new Shape()` → **ERROR**
- If a class has even one abstract method, the class **must** be declared abstract
- A subclass must implement **all** abstract methods, or it must also be declared abstract

---

### Abstract Class vs Regular Class vs Interface

| Feature | Regular Class | Abstract Class | Interface |
|---|---|---|---|
| Instantiatable | ✅ | ❌ | ❌ |
| Abstract methods | ❌ | ✅ (optional) | ✅ (all, by default) |
| Concrete methods | ✅ | ✅ | ✅ (default/static only) |
| Fields | ✅ | ✅ | Only `public static final` |
| Constructor | ✅ | ✅ | ❌ |
| Multiple inheritance | ❌ | ❌ | ✅ |

---

### Interfaces

An **interface** is a pure contract — it defines what a class **must** do, but not how it does it.

```java
interface Drawable {
    void draw();            // implicitly public abstract
    void resize(double factor);
}
```

A class **implements** an interface and must provide bodies for all methods:

```java
class Circle implements Drawable {
    double radius;

    Circle(double radius) { this.radius = radius; }

    @Override
    public void draw() {
        System.out.println("Drawing circle with radius " + radius);
    }

    @Override
    public void resize(double factor) {
        radius *= factor;
        System.out.println("Circle resized. New radius: " + radius);
    }
}
```

---

### Multiple Interface Implementation

A class can implement **multiple interfaces** — this is how Java achieves multiple inheritance:

```java
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

class Duck implements Flyable, Swimmable {
    @Override
    public void fly() { System.out.println("Duck is flying"); }

    @Override
    public void swim() { System.out.println("Duck is swimming"); }
}
```

An interface can also **extend** multiple interfaces:

```java
interface FlyingSwimmer extends Flyable, Swimmable {
    void dive();
}
```

---

### `default` Methods in Interfaces (Java 8+)

Before Java 8, interfaces could only have abstract methods. Java 8 introduced **`default` methods** — concrete methods with a body in an interface. This allows adding new methods to interfaces without breaking existing implementations.

```java
interface Printable {
    void print();

    default void printWithBorder() {
        System.out.println("---");
        print();
        System.out.println("---");
    }
}

class Report implements Printable {
    @Override
    public void print() {
        System.out.println("Q4 Financial Report");
    }
}

Report r = new Report();
r.print();             // own implementation
r.printWithBorder();   // inherited default method
```

---

### `static` Methods in Interfaces (Java 8+)

Interfaces can also have `static` methods. These belong to the interface itself (not implementing classes) and cannot be overridden.

```java
interface MathUtils {
    static int square(int n) { return n * n; }
    static int cube(int n)   { return n * n * n; }
}

System.out.println(MathUtils.square(4));  // 16
System.out.println(MathUtils.cube(3));    // 27
```

---

### When to Use Abstract Class vs Interface

| Use Abstract Class when... | Use Interface when... |
|---|---|
| Classes share common code/fields | You only need to define a contract |
| You need a constructor | Multiple inheritance is needed |
| Classes are closely related | Unrelated classes need common behaviour |
| You want to provide partial implementation | You want full flexibility for implementors |

**Real-world analogy:**
- Abstract class = "Employee" template (common fields: name, salary, hire date)
- Interface = "Printable", "Serializable", "Comparable" — capabilities any class can adopt

---

## 🧪 Practice Questions

1. What is an abstract class? Can it be instantiated?
2. What is the difference between an abstract method and a concrete method?
3. Can an abstract class have a constructor? If so, when is it called?
4. What is an interface? What methods can an interface contain in Java 8+?
5. What is the difference between `implements` and `extends`?
6. Can a class implement multiple interfaces? Can it extend multiple classes?
7. What are `default` methods in interfaces? Why were they introduced?
8. What is the difference between a `default` method and a `static` method in an interface?
9. When would you choose an abstract class over an interface?

---

## 💻 Examples

### Example 1 – Abstract Class

```java
public class AbstractDemo {

    abstract static class Employee {
        String name;
        double baseSalary;

        Employee(String name, double baseSalary) {
            this.name = name;
            this.baseSalary = baseSalary;
        }

        abstract double calculateBonus();   // each type calculates differently

        double totalSalary() {
            return baseSalary + calculateBonus();
        }

        @Override
        public String toString() {
            return String.format("%-12s | Base: %8.2f | Bonus: %7.2f | Total: %9.2f",
                name, baseSalary, calculateBonus(), totalSalary());
        }
    }

    static class PermanentEmployee extends Employee {
        PermanentEmployee(String name, double salary) { super(name, salary); }

        @Override
        double calculateBonus() { return baseSalary * 0.20; }   // 20% bonus
    }

    static class ContractEmployee extends Employee {
        ContractEmployee(String name, double salary) { super(name, salary); }

        @Override
        double calculateBonus() { return baseSalary * 0.05; }   // 5% bonus
    }

    static class Intern extends Employee {
        Intern(String name, double salary) { super(name, salary); }

        @Override
        double calculateBonus() { return 0; }   // no bonus
    }

    public static void main(String[] args) {
        Employee[] team = {
            new PermanentEmployee("Priya", 60000),
            new ContractEmployee("Rahul", 45000),
            new Intern("Anjali", 15000)
        };

        System.out.printf("%-12s | %10s | %9s | %11s%n", "Name", "Base", "Bonus", "Total");
        System.out.println("-".repeat(52));
        for (Employee e : team) System.out.println(e);
    }
}
```

**Output:**
```
Name         |       Base |     Bonus |       Total
----------------------------------------------------
Priya        |   60000.00 |  12000.00 |   72000.00
Rahul        |   45000.00 |   2250.00 |   47250.00
Anjali       |   15000.00 |     0.00 |   15000.00
```

---

### Example 2 – Interfaces with Multiple Implementation

```java
public class InterfaceDemo {

    interface Playable {
        void play();
        void stop();
    }

    interface Recordable {
        void record();
        void saveRecording(String filename);
    }

    // Implements both interfaces
    static class MediaPlayer implements Playable, Recordable {
        String currentMedia;

        MediaPlayer(String media) { this.currentMedia = media; }

        @Override public void play() {
            System.out.println("Playing: " + currentMedia);
        }
        @Override public void stop() {
            System.out.println("Stopped playback.");
        }
        @Override public void record() {
            System.out.println("Recording started...");
        }
        @Override public void saveRecording(String filename) {
            System.out.println("Recording saved as: " + filename);
        }
    }

    public static void main(String[] args) {
        MediaPlayer mp = new MediaPlayer("Concert_Live.mp4");
        mp.play();
        mp.record();
        mp.saveRecording("MyRecording.mp4");
        mp.stop();

        // Reference via interface type
        Playable p = new MediaPlayer("Song.mp3");
        p.play();
    }
}
```

**Output:**
```
Playing: Concert_Live.mp4
Recording started...
Recording saved as: MyRecording.mp4
Stopped playback.
Playing: Song.mp3
```

---

### Example 3 – default and static Interface Methods

```java
public class DefaultStaticDemo {

    interface Logger {
        void log(String message);

        // default method — subclasses inherit this
        default void logError(String message) {
            log("[ERROR] " + message);
        }

        default void logInfo(String message) {
            log("[INFO] " + message);
        }

        // static utility — called on interface, not instances
        static String timestamp() {
            return "[" + java.time.LocalTime.now().withNano(0) + "]";
        }
    }

    static class ConsoleLogger implements Logger {
        @Override
        public void log(String message) {
            System.out.println(Logger.timestamp() + " " + message);
        }
    }

    static class FileLogger implements Logger {
        @Override
        public void log(String message) {
            System.out.println("FILE: " + Logger.timestamp() + " " + message);
        }

        @Override
        public void logError(String message) {
            log("[CRITICAL ERROR] " + message);   // override default
        }
    }

    public static void main(String[] args) {
        Logger console = new ConsoleLogger();
        console.logInfo("Application started");
        console.logError("Null pointer detected");

        System.out.println();
        Logger file = new FileLogger();
        file.logInfo("Writing to file");
        file.logError("Disk full!");   // overridden default
    }
}
```

**Output:**
```
[HH:MM:SS] [INFO] Application started
[HH:MM:SS] [ERROR] Null pointer detected

FILE: [HH:MM:SS] [INFO] Writing to file
FILE: [HH:MM:SS] [CRITICAL ERROR] Disk full!
```

---

### Example 4 – Abstract + Interface Together

```java
public class CombinedDemo {

    interface Taxable {
        double TAX_RATE = 0.18;   // implicitly public static final
        double calculateTax();
    }

    abstract static class Product implements Taxable {
        String name;
        double price;

        Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        abstract String getCategory();

        @Override
        public double calculateTax() {
            return price * TAX_RATE;
        }

        void showDetails() {
            System.out.printf("%-15s %-10s ₹%8.2f  Tax: ₹%6.2f  Total: ₹%8.2f%n",
                name, getCategory(), price, calculateTax(), price + calculateTax());
        }
    }

    static class Electronics extends Product {
        Electronics(String name, double price) { super(name, price); }
        @Override public String getCategory() { return "Electronics"; }
    }

    static class Clothing extends Product {
        Clothing(String name, double price) { super(name, price); }
        @Override public String getCategory() { return "Clothing"; }
    }

    public static void main(String[] args) {
        Product[] cart = {
            new Electronics("Laptop", 55000),
            new Clothing("Jacket", 3500),
            new Electronics("Headphones", 4999)
        };

        System.out.printf("%-15s %-10s %10s  %10s  %10s%n",
            "Product", "Category", "Price", "Tax", "Total");
        System.out.println("-".repeat(62));
        for (Product p : cart) p.showDetails();
    }
}
```

**Output:**
```
Product         Category        Price        Tax       Total
--------------------------------------------------------------
Laptop          Electronics  ₹55000.00  Tax: ₹9900.00  Total: ₹64900.00
Jacket          Clothing     ₹ 3500.00  Tax: ₹ 630.00  Total: ₹ 4130.00
Headphones      Electronics  ₹ 4999.00  Tax: ₹ 899.82  Total: ₹ 5898.82
```

---

## 📝 Summary

- **Abstract classes** cannot be instantiated; they define a template with partial implementation.
- **Abstract methods** have no body and must be implemented by concrete subclasses.
- **Interfaces** define a pure contract; all methods are implicitly `public abstract` (unless `default`/`static`).
- A class can `implement` multiple interfaces but only `extend` one class.
- **`default` methods** (Java 8+) add concrete behaviour to interfaces without breaking existing code.
- **`static` methods** in interfaces belong to the interface itself and can't be overridden.
- Use abstract class for "IS-A + shared implementation"; use interface for "CAN-DO" capabilities.

---

*Previous → [02 – Method Overriding & super](./02_MethodOverriding_super.md)*  
*Next → [Unit 4 – Nested Classes & Lambda Expressions](../Unit4/01_NestedClasses.md)*

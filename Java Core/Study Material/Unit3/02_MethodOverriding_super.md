# 02 — Method Overriding, super, Object Class, instanceof & final

---

## 📖 Theory

### Method Overriding

**Method overriding** occurs when a child class provides its own implementation of a method that is already defined in the parent class. The method in the child **replaces** the parent's version for that object.

**Rules for overriding:**
- Same method name, same parameters, same return type (or covariant return)
- Cannot reduce the access level (e.g., `public` → `private` is invalid)
- Cannot override `static`, `final`, or `private` methods
- Use `@Override` annotation (not mandatory but strongly recommended — catches mistakes at compile time)

```java
class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks: Woof!");
    }
}

Animal a = new Dog();
a.sound();    // "Dog barks: Woof!" — Dog's version is called
```

This is **runtime polymorphism** (dynamic dispatch) — the JVM decides which version to call based on the **actual object type** at runtime, not the reference type.

---

### Overriding vs Overloading — Key Differences

| Aspect | Overloading | Overriding |
|---|---|---|
| Where | Same class | Parent → Child class |
| Parameters | Must differ | Must be identical |
| Return type | Can differ | Must match (or covariant) |
| Resolved at | Compile time | Runtime |
| Also called | Static polymorphism | Dynamic polymorphism |

---

### The `super` Keyword

`super` refers to the **parent class**. It has three uses:

**1. Call parent constructor:**
```java
class Animal {
    String name;
    Animal(String name) { this.name = name; }
}

class Dog extends Animal {
    String breed;
    Dog(String name, String breed) {
        super(name);       // must be first statement
        this.breed = breed;
    }
}
```

**2. Call parent's overridden method:**
```java
class Animal {
    void sound() { System.out.println("Generic animal sound"); }
}

class Dog extends Animal {
    @Override
    void sound() {
        super.sound();     // calls parent version
        System.out.println("Dog barks: Woof!");
    }
}
```

**3. Access parent's field (when shadowed by child):**
```java
class Parent {
    int x = 10;
}
class Child extends Parent {
    int x = 20;
    void show() {
        System.out.println("Child x: " + x);
        System.out.println("Parent x: " + super.x);
    }
}
```

---

### The `Object` Class

Every class in Java **implicitly extends `java.lang.Object`**. It sits at the top of the entire class hierarchy. That means every object you create automatically inherits these methods:

| Method | Description |
|---|---|
| `toString()` | Returns a String representation of the object |
| `equals(Object o)` | Checks if two objects are "equal" |
| `hashCode()` | Returns an integer hash code |
| `getClass()` | Returns the runtime class of the object |
| `clone()` | Creates and returns a copy of the object |
| `finalize()` | Called by GC before object is destroyed |

---

### Overriding `toString()`

Default `toString()` returns something like `ClassName@hashcode` — not useful. Override it to return meaningful information.

```java
class Student {
    String name;
    int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + "}";
    }
}

Student s = new Student("Alice", 21);
System.out.println(s);         // automatically calls toString()
// Output: Student{name='Alice', age=21}
```

---

### Overriding `equals()`

Default `equals()` compares **references** (same as `==`). Override it to compare **content**.

```java
class Point {
    int x, y;

    Point(int x, int y) { this.x = x; this.y = y; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                   // same reference
        if (obj == null || getClass() != obj.getClass()) return false;
        Point other = (Point) obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;   // always override hashCode when overriding equals
    }
}

Point p1 = new Point(3, 4);
Point p2 = new Point(3, 4);
System.out.println(p1 == p2);        // false (different objects)
System.out.println(p1.equals(p2));   // true (same content)
```

> **Rule:** Always override `hashCode()` when you override `equals()`.

---

### The `final` Keyword

`final` can be applied to variables, methods, and classes:

| Context | Effect |
|---|---|
| `final` variable | Value cannot be changed (constant) |
| `final` method | Cannot be overridden in subclasses |
| `final` class | Cannot be subclassed (e.g., `String`, `Integer`) |

```java
final class Immutable { }
// class Child extends Immutable { }   // COMPILE ERROR

class Parent {
    final void display() {
        System.out.println("Cannot override this.");
    }
}

class Child extends Parent {
    // @Override void display() { }   // COMPILE ERROR
}
```

---

### The `instanceof` Operator

`instanceof` checks whether an object is an instance of a specific class or interface. Returns `boolean`.

```java
Animal a = new Dog();
System.out.println(a instanceof Dog);    // true
System.out.println(a instanceof Animal); // true (Dog IS-A Animal)
System.out.println(a instanceof Cat);    // false
```

**Pattern matching with instanceof (Java 16+):**
```java
Object obj = "Hello";
if (obj instanceof String s) {
    System.out.println(s.toUpperCase());   // s is already cast
}
```

---

## 🧪 Practice Questions

1. What is method overriding? How is it different from method overloading?
2. What does the `@Override` annotation do? Is it mandatory?
3. What are the three uses of the `super` keyword?
4. What does every class in Java implicitly extend?
5. What does the default `toString()` method return? Why should you override it?
6. When should you override `equals()`? What rule should you always follow when you do?
7. What happens if you try to override a `final` method?
8. What does `instanceof` return if the object is null?
9. What is runtime polymorphism? Give a code example.

---

## 💻 Examples

### Example 1 – Method Overriding and Runtime Polymorphism

```java
public class PolymorphismDemo {

    static class Shape {
        String color;
        Shape(String color) { this.color = color; }

        double area() { return 0; }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "[color=" + color + ", area=" + String.format("%.2f", area()) + "]";
        }
    }

    static class Circle extends Shape {
        double radius;
        Circle(String color, double radius) { super(color); this.radius = radius; }

        @Override
        double area() { return Math.PI * radius * radius; }
    }

    static class Triangle extends Shape {
        double base, height;
        Triangle(String color, double base, double height) {
            super(color); this.base = base; this.height = height;
        }

        @Override
        double area() { return 0.5 * base * height; }
    }

    public static void main(String[] args) {
        Shape[] shapes = {
            new Circle("Red", 5),
            new Triangle("Blue", 6, 4),
            new Circle("Green", 3)
        };

        double totalArea = 0;
        for (Shape s : shapes) {
            System.out.println(s);      // toString() called
            totalArea += s.area();      // correct area() called at runtime
        }
        System.out.printf("Total area: %.2f%n", totalArea);
    }
}
```

**Output:**
```
Circle[color=Red, area=78.54]
Triangle[color=Blue, area=12.00]
Circle[color=Green, area=28.27]
Total area: 118.81
```

---

### Example 2 – super Keyword (All Three Uses)

```java
public class SuperDemo {

    static class Vehicle {
        String brand;
        int speed;

        Vehicle(String brand, int speed) {
            this.brand = brand;
            this.speed = speed;
        }

        void describe() {
            System.out.println("Brand: " + brand + " | Speed: " + speed + " km/h");
        }
    }

    static class ElectricVehicle extends Vehicle {
        int batteryRange;

        ElectricVehicle(String brand, int speed, int batteryRange) {
            super(brand, speed);         // use 1: call parent constructor
            this.batteryRange = batteryRange;
        }

        @Override
        void describe() {
            super.describe();            // use 2: call parent method
            System.out.println("Battery Range: " + batteryRange + " km");
        }
    }

    public static void main(String[] args) {
        ElectricVehicle ev = new ElectricVehicle("Tesla", 250, 580);
        ev.describe();
    }
}
```

**Output:**
```
Brand: Tesla | Speed: 250 km/h
Battery Range: 580 km
```

---

### Example 3 – Overriding toString() and equals()

```java
public class EqualsDemo {

    static class Book {
        String title;
        String author;
        double price;

        Book(String title, String author, double price) {
            this.title = title;
            this.author = author;
            this.price = price;
        }

        @Override
        public String toString() {
            return "\"" + title + "\" by " + author + " (₹" + price + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Book)) return false;
            Book other = (Book) obj;
            return title.equals(other.title) && author.equals(other.author);
        }

        @Override
        public int hashCode() {
            return title.hashCode() + author.hashCode();
        }
    }

    public static void main(String[] args) {
        Book b1 = new Book("Clean Code", "Robert Martin", 599);
        Book b2 = new Book("Clean Code", "Robert Martin", 649);  // different price, same book
        Book b3 = new Book("Effective Java", "Joshua Bloch", 799);

        System.out.println(b1);
        System.out.println(b2);
        System.out.println("b1 == b2: " + (b1 == b2));
        System.out.println("b1.equals(b2): " + b1.equals(b2));   // true: same title+author
        System.out.println("b1.equals(b3): " + b1.equals(b3));   // false
    }
}
```

**Output:**
```
"Clean Code" by Robert Martin (₹599.0)
"Clean Code" by Robert Martin (₹649.0)
b1 == b2: false
b1.equals(b2): true
b1.equals(b3): false
```

---

### Example 4 – instanceof in Action

```java
public class InstanceofDemo {

    static class Animal { }
    static class Dog extends Animal {
        void fetch() { System.out.println("Dog fetches the ball!"); }
    }
    static class Cat extends Animal {
        void purr() { System.out.println("Cat purrs..."); }
    }

    public static void makeSound(Animal a) {
        if (a instanceof Dog d) {
            d.fetch();              // pattern matching — no cast needed
        } else if (a instanceof Cat c) {
            c.purr();
        } else {
            System.out.println("Unknown animal");
        }
    }

    public static void main(String[] args) {
        Animal[] animals = { new Dog(), new Cat(), new Dog() };
        for (Animal a : animals) {
            makeSound(a);
        }

        // null check
        Animal nullAnimal = null;
        System.out.println("null instanceof Animal: " + (nullAnimal instanceof Animal));
    }
}
```

**Output:**
```
Dog fetches the ball!
Cat purrs...
Dog fetches the ball!
null instanceof Animal: false
```

---

## 📝 Summary

- **Method overriding** provides a child class's own version of a parent method; it enables runtime polymorphism.
- Use `@Override` to catch mistakes at compile time.
- **`super`** has three uses: call parent constructor, call parent method, access parent field.
- **Every class extends `Object`** implicitly — always override `toString()` and `equals()` for meaningful behaviour.
- When overriding `equals()`, always override `hashCode()` too.
- **`final`** prevents a variable from changing, a method from being overridden, or a class from being extended.
- **`instanceof`** checks the runtime type of an object; returns `false` for `null`.

---

*Previous → [01 – Inheritance](./01_Inheritance.md)*  
*Next → [03 – Abstract Classes and Interfaces](./03_Abstract_Interface.md)*

# 01 — Inheritance

---

## 📖 Theory

### What Is Inheritance?

**Inheritance** is one of the four pillars of OOP. It allows a class (called the **child** or **subclass**) to acquire the properties and behaviours of another class (called the **parent** or **superclass**).

Think of it as an "IS-A" relationship:
- A `Dog` IS-A `Animal`
- A `Car` IS-A `Vehicle`
- A `SavingsAccount` IS-A `BankAccount`

**Why use inheritance?**
- **Code reuse** — write common logic once in the parent
- **Extensibility** — add specialised behaviour in child classes
- **Maintainability** — changes in parent automatically reflect in children

---

### Syntax

```java
class Parent {
    // fields and methods
}

class Child extends Parent {
    // inherits everything from Parent
    // can add new fields/methods
    // can override parent methods
}
```

---

### What Is Inherited?

| Inherited | Not Inherited |
|---|---|
| `public` fields and methods | `private` fields and methods |
| `protected` fields and methods | Constructors |
| Default (package-private) members (within same package) | Static members (shared, not inherited in OOP sense) |

---

### Types of Inheritance in Java

```
Single:        A → B
Multilevel:    A → B → C
Hierarchical:  A → B, A → C

NOT SUPPORTED (with classes):
Multiple:      A, B → C   ← use interfaces instead
```

**Java does NOT support multiple inheritance with classes** to avoid the "Diamond Problem." It supports multiple inheritance through **interfaces**.

---

### The `extends` Keyword

```java
class Animal {
    String name;

    void eat() {
        System.out.println(name + " is eating.");
    }

    void sleep() {
        System.out.println(name + " is sleeping.");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println(name + " says: Woof!");
    }
}

// Dog inherits eat() and sleep() from Animal
Dog d = new Dog();
d.name = "Rex";
d.eat();    // inherited
d.sleep();  // inherited
d.bark();   // Dog's own method
```

---

### Constructor Chaining in Inheritance

Constructors are **not inherited**, but the **parent constructor is always called** when a child object is created. If you don't explicitly call it with `super()`, Java automatically calls the **no-arg constructor** of the parent.

```java
class Animal {
    Animal() {
        System.out.println("Animal constructor called");
    }
}

class Dog extends Animal {
    Dog() {
        // super() is called implicitly here
        System.out.println("Dog constructor called");
    }
}

new Dog();
// Output:
// Animal constructor called
// Dog constructor called
```

---

### Multilevel Inheritance

```java
class Vehicle {
    void start() { System.out.println("Vehicle started"); }
}

class Car extends Vehicle {
    void drive() { System.out.println("Car is driving"); }
}

class ElectricCar extends Car {
    void charge() { System.out.println("Electric car charging"); }
}

ElectricCar ec = new ElectricCar();
ec.start();   // from Vehicle
ec.drive();   // from Car
ec.charge();  // own method
```

---

## 🧪 Practice Questions

1. What is inheritance? What is the keyword used in Java for inheritance?
2. What does the "IS-A" relationship mean? Give two real-world examples.
3. What members are inherited from a parent class? What is NOT inherited?
4. What happens when you create a child class object? Which constructors are called and in what order?
5. Why does Java not support multiple inheritance with classes?
6. What is the difference between single, multilevel, and hierarchical inheritance?
7. Can a child class access `private` members of the parent? Why or why not?

---

## 💻 Examples

### Example 1 – Basic Inheritance

```java
public class InheritanceDemo {

    static class Shape {
        String color;

        Shape(String color) {
            this.color = color;
        }

        double area() {
            return 0;
        }

        void display() {
            System.out.println("Color: " + color + " | Area: " + area());
        }
    }

    static class Circle extends Shape {
        double radius;

        Circle(String color, double radius) {
            super(color);          // call parent constructor
            this.radius = radius;
        }

        @Override
        double area() {
            return Math.PI * radius * radius;
        }
    }

    static class Rectangle extends Shape {
        double width, height;

        Rectangle(String color, double width, double height) {
            super(color);
            this.width = width;
            this.height = height;
        }

        @Override
        double area() {
            return width * height;
        }
    }

    public static void main(String[] args) {
        Circle c = new Circle("Red", 5);
        Rectangle r = new Rectangle("Blue", 4, 6);

        c.display();
        r.display();
    }
}
```

**Output:**
```
Color: Red | Area: 78.53981633974483
Color: Blue | Area: 24.0
```

---

### Example 2 – Multilevel Inheritance

```java
public class MultilevelDemo {

    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        void introduce() {
            System.out.println("Hi, I'm " + name + ", age " + age);
        }
    }

    static class Employee extends Person {
        String company;
        double salary;

        Employee(String name, int age, String company, double salary) {
            super(name, age);
            this.company = company;
            this.salary = salary;
        }

        void showWork() {
            System.out.println("Works at " + company + " | Salary: ₹" + salary);
        }
    }

    static class Manager extends Employee {
        int teamSize;

        Manager(String name, int age, String company, double salary, int teamSize) {
            super(name, age, company, salary);
            this.teamSize = teamSize;
        }

        void showTeam() {
            System.out.println("Manages a team of " + teamSize + " people");
        }
    }

    public static void main(String[] args) {
        Manager m = new Manager("Ananya", 35, "TechCorp", 120000, 12);
        m.introduce();   // from Person
        m.showWork();    // from Employee
        m.showTeam();    // own
    }
}
```

**Output:**
```
Hi, I'm Ananya, age 35
Works at TechCorp | Salary: ₹120000.0
Manages a team of 12 people
```

---

### Example 3 – Hierarchical Inheritance

```java
public class HierarchicalDemo {

    static class Animal {
        String name;
        Animal(String name) { this.name = name; }
        void breathe() { System.out.println(name + " breathes."); }
    }

    static class Dog extends Animal {
        Dog(String name) { super(name); }
        void bark() { System.out.println(name + " barks: Woof!"); }
    }

    static class Cat extends Animal {
        Cat(String name) { super(name); }
        void meow() { System.out.println(name + " meows: Meow!"); }
    }

    static class Bird extends Animal {
        Bird(String name) { super(name); }
        void chirp() { System.out.println(name + " chirps: Tweet!"); }
    }

    public static void main(String[] args) {
        Dog dog = new Dog("Bruno");
        Cat cat = new Cat("Whiskers");
        Bird bird = new Bird("Tweety");

        dog.breathe(); dog.bark();
        cat.breathe(); cat.meow();
        bird.breathe(); bird.chirp();
    }
}
```

**Output:**
```
Bruno breathes.
Bruno barks: Woof!
Whiskers breathes.
Whiskers meows: Meow!
Tweety breathes.
Tweety chirps: Tweet!
```

---

## 📝 Summary

- **Inheritance** lets a child class reuse and extend a parent class using `extends`.
- It models an "IS-A" relationship.
- Java supports single, multilevel, and hierarchical inheritance (not multiple with classes).
- `private` members are not accessible in child classes; `protected` and `public` members are.
- Parent constructors are always called first (implicitly or explicitly via `super()`).

---

*Next → [02 – Method Overriding & super Keyword](./02_MethodOverriding_super.md)*

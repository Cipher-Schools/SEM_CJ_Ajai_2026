# 04 — Exceptions and Assertions

---

## 📖 Theory

### What Is an Exception?

An **exception** is an event that disrupts the normal flow of a program — typically an error condition that the program can potentially recover from. Java uses exceptions to signal and handle error conditions cleanly, separating error-handling code from normal logic.

```java
int[] arr = {1, 2, 3};
System.out.println(arr[10]);   // throws ArrayIndexOutOfBoundsException
```

---

### Exception Class Hierarchy

```
Throwable
├── Error                          (serious problems — do NOT catch these)
│   ├── OutOfMemoryError
│   ├── StackOverflowError
│   └── VirtualMachineError
└── Exception
    ├── RuntimeException           (Unchecked — compiler doesn't require handling)
    │   ├── NullPointerException
    │   ├── ArrayIndexOutOfBoundsException
    │   ├── ClassCastException
    │   ├── NumberFormatException
    │   ├── ArithmeticException    (e.g., divide by zero)
    │   └── IllegalArgumentException
    └── IOException                (Checked — compiler REQUIRES handling)
        ├── FileNotFoundException
        └── ...
```

---

### Checked vs Unchecked Exceptions

| Type | Description | Examples | Must Handle? |
|---|---|---|---|
| **Checked** | Anticipated; compiler forces you to handle | `IOException`, `SQLException` | ✅ Yes |
| **Unchecked (Runtime)** | Programming errors; compiler does NOT force handling | `NullPointerException`, `ArrayIndexOutOfBoundsException` | ❌ No |
| **Error** | JVM-level problems; don't handle these | `OutOfMemoryError` | ❌ Never |

---

### try-catch-finally

```java
try {
    // Code that might throw an exception
} catch (ExceptionType1 e) {
    // Handle ExceptionType1
} catch (ExceptionType2 e) {
    // Handle ExceptionType2
} finally {
    // Always runs — with or without exception
    // Use for cleanup: closing files, DB connections, etc.
}
```

**The `finally` block always executes**, even if:
- No exception was thrown
- An exception was thrown and caught
- An exception was thrown and NOT caught
- A `return` statement was hit in the try block

---

### Exception Propagation

If a method doesn't catch an exception, it **propagates** up the call stack to the caller:

```
methodC() throws exception
    ↓ propagates to
methodB() — doesn't catch it
    ↓ propagates to
methodA() — catches it here
```

```java
void methodC() {
    int result = 10 / 0;   // throws ArithmeticException
}

void methodB() {
    methodC();              // doesn't catch — propagates
}

void methodA() {
    try {
        methodB();
    } catch (ArithmeticException e) {
        System.out.println("Caught in methodA: " + e.getMessage());
    }
}
```

---

### throw vs throws

| | `throw` | `throws` |
|---|---|---|
| What | Manually throw an exception | Declare that a method may throw an exception |
| Where | Inside method body | In method signature |
| Example | `throw new IllegalArgumentException("bad input")` | `void read() throws IOException` |

```java
// throws — declare possibility of exception
void readFile(String path) throws IOException {
    // ... file reading code
}

// throw — manually throw
void setAge(int age) {
    if (age < 0 || age > 150) {
        throw new IllegalArgumentException("Invalid age: " + age);
    }
    this.age = age;
}
```

---

### Multi-catch (Java 7+)

Handle multiple exception types in one catch block:

```java
try {
    // risky code
} catch (IOException | SQLException e) {
    System.out.println("I/O or DB error: " + e.getMessage());
}
```

---

### try-with-resources (Java 7+)

Automatically closes resources (like files, DB connections) that implement `AutoCloseable`:

```java
// WITHOUT try-with-resources (verbose)
FileReader fr = null;
try {
    fr = new FileReader("data.txt");
    // use fr
} catch (IOException e) {
    e.printStackTrace();
} finally {
    if (fr != null) try { fr.close(); } catch (IOException e) {}
}

// WITH try-with-resources (clean)
try (FileReader fr = new FileReader("data.txt")) {
    // use fr — automatically closed when done
} catch (IOException e) {
    e.printStackTrace();
}
```

---

### Creating Custom Exceptions

Extend `Exception` (checked) or `RuntimeException` (unchecked):

```java
// Custom checked exception
class InsufficientFundsException extends Exception {
    private double amount;

    InsufficientFundsException(double amount) {
        super("Insufficient funds. Short by: ₹" + amount);
        this.amount = amount;
    }

    double getAmount() { return amount; }
}

// Usage
void withdraw(double amount) throws InsufficientFundsException {
    if (amount > balance) {
        throw new InsufficientFundsException(amount - balance);
    }
    balance -= amount;
}
```

---

### Assertions

An **assertion** is a statement that you believe to be true at a specific point in your code. If it's false, the program throws an `AssertionError`. Assertions are used for **debugging and testing invariants**.

```java
// Syntax 1
assert condition;

// Syntax 2 — with message
assert condition : "Error message";
```

```java
int speed = calculateSpeed();
assert speed >= 0 : "Speed cannot be negative: " + speed;
```

**Enabling assertions:** By default, assertions are disabled. Run with:
```
java -ea YourClass     (-ea = enable assertions)
java -da YourClass     (-da = disable, the default)
```

> **Use assertions for:** debugging invariants, preconditions in private methods.  
> **Use exceptions for:** public API validation, expected error conditions.

---

## 🧪 Practice Questions

1. What is an exception? How is it different from an `Error`?
2. What is the difference between checked and unchecked exceptions?
3. When does the `finally` block NOT execute?
4. What is the difference between `throw` and `throws`?
5. What is exception propagation? Trace an example through 3 methods.
6. What is try-with-resources? Why is it better than a `finally` block for cleanup?
7. How do you create a custom exception? When would you extend `Exception` vs `RuntimeException`?
8. What is an assertion? How is it different from an `if` check?
9. How do you enable assertions when running a Java program?

---

## 💻 Examples

### Example 1 – Basic try-catch-finally

```java
public class TryCatchDemo {
    public static void main(String[] args) {
        // Example 1: ArithmeticException
        try {
            int result = 10 / 0;
            System.out.println("Result: " + result);  // never reached
        } catch (ArithmeticException e) {
            System.out.println("Caught: " + e.getMessage());
        } finally {
            System.out.println("Finally block runs.");
        }

        // Example 2: ArrayIndexOutOfBoundsException
        int[] arr = {1, 2, 3};
        try {
            System.out.println(arr[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array error: " + e.getMessage());
        }

        // Example 3: NumberFormatException
        try {
            int n = Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println("Cannot parse 'abc' as integer.");
        }

        System.out.println("Program continues normally.");
    }
}
```

**Output:**
```
Caught: / by zero
Finally block runs.
Array error: Index 5 out of bounds for length 3
Cannot parse 'abc' as integer.
Program continues normally.
```

---

### Example 2 – Exception Propagation

```java
public class PropagationDemo {

    static void level3() {
        System.out.println("level3: about to divide");
        int x = 10 / 0;   // throws here
    }

    static void level2() {
        System.out.println("level2: calling level3");
        level3();           // propagates up
    }

    static void level1() {
        System.out.println("level1: calling level2");
        try {
            level2();
        } catch (ArithmeticException e) {
            System.out.println("level1: caught exception — " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("main: calling level1");
        level1();
        System.out.println("main: execution continues");
    }
}
```

**Output:**
```
main: calling level1
level1: calling level2
level2: calling level3
level3: about to divide
level1: caught exception — / by zero
main: execution continues
```

---

### Example 3 – Multi-catch and Custom Exception

```java
public class CustomExceptionDemo {

    // Custom exception
    static class InvalidAgeException extends Exception {
        int age;
        InvalidAgeException(int age) {
            super("Invalid age: " + age + ". Must be between 0 and 120.");
            this.age = age;
        }
    }

    static class UnderageException extends RuntimeException {
        UnderageException(int age) {
            super("User is underage (" + age + "). Must be 18+.");
        }
    }

    static void registerUser(String name, int age) throws InvalidAgeException {
        if (age < 0 || age > 120) throw new InvalidAgeException(age);
        if (age < 18) throw new UnderageException(age);
        System.out.println("Registered: " + name + " (age " + age + ")");
    }

    public static void main(String[] args) {
        String[][] users = {{"Alice", "25"}, {"Bob", "15"}, {"Charlie", "-5"}, {"Diana", "30"}};

        for (String[] u : users) {
            try {
                registerUser(u[0], Integer.parseInt(u[1]));
            } catch (InvalidAgeException e) {
                System.out.println("Validation error for " + u[0] + ": " + e.getMessage());
            } catch (UnderageException e) {
                System.out.println("Age restriction for " + u[0] + ": " + e.getMessage());
            }
        }
    }
}
```

**Output:**
```
Registered: Alice (age 25)
Age restriction for Bob: User is underage (15). Must be 18+.
Validation error for Charlie: Invalid age: -5. Must be between 0 and 120.
Registered: Diana (age 30)
```

---

### Example 4 – try-with-resources

```java
public class TryWithResourcesDemo {

    // Custom AutoCloseable resource
    static class DatabaseConnection implements AutoCloseable {
        String host;

        DatabaseConnection(String host) {
            this.host = host;
            System.out.println("Connection opened to " + host);
        }

        String query(String sql) {
            return "Result of: " + sql;
        }

        @Override
        public void close() {
            System.out.println("Connection to " + host + " closed automatically.");
        }
    }

    public static void main(String[] args) {
        // Connection is automatically closed after try block
        try (DatabaseConnection db = new DatabaseConnection("db.example.com")) {
            String result = db.query("SELECT * FROM students");
            System.out.println(result);
            // No need to call db.close() — happens automatically
        }

        System.out.println("After try block — connection is already closed.");

        // Multiple resources
        System.out.println("\nOpening two resources:");
        try (
            DatabaseConnection db1 = new DatabaseConnection("primary.db");
            DatabaseConnection db2 = new DatabaseConnection("replica.db")
        ) {
            System.out.println("Both connections active.");
        }
        // Closed in reverse order: db2 first, then db1
    }
}
```

**Output:**
```
Connection opened to db.example.com
Result of: SELECT * FROM students
Connection to db.example.com closed automatically.
After try block — connection is already closed.

Opening two resources:
Connection opened to primary.db
Connection opened to replica.db
Both connections active.
Connection to replica.db closed automatically.
Connection to primary.db closed automatically.
```

---

### Example 5 – Assertions

```java
public class AssertionsDemo {

    static int divide(int a, int b) {
        assert b != 0 : "Divisor cannot be zero!";
        return a / b;
    }

    static double calculateDiscount(double price, double discountPct) {
        assert price >= 0 : "Price must be non-negative";
        assert discountPct >= 0 && discountPct <= 100 : "Discount must be 0-100%";

        double discounted = price * (1 - discountPct / 100);
        assert discounted >= 0 : "Discounted price should not be negative";
        return discounted;
    }

    public static void main(String[] args) {
        System.out.println("10 / 2 = " + divide(10, 2));

        System.out.println("Price after 20% off ₹500: ₹" + calculateDiscount(500, 20));

        // To test assertion failure, run with -ea flag and uncomment:
        // System.out.println(divide(5, 0));  // AssertionError if assertions enabled
    }
}
```

**Output (assertions disabled by default):**
```
10 / 2 = 5
Price after 20% off ₹500: ₹400.0
```

---

### Example 6 – Full Bank Account with Exceptions

```java
public class BankExceptionDemo {

    static class InsufficientFundsException extends Exception {
        double shortfall;
        InsufficientFundsException(double shortfall) {
            super(String.format("Insufficient funds. Need ₹%.2f more.", shortfall));
            this.shortfall = shortfall;
        }
    }

    static class NegativeAmountException extends RuntimeException {
        NegativeAmountException(String operation) {
            super("Cannot " + operation + " a negative amount.");
        }
    }

    static class BankAccount {
        private String owner;
        private double balance;

        BankAccount(String owner, double initialBalance) {
            assert initialBalance >= 0 : "Initial balance cannot be negative";
            this.owner = owner;
            this.balance = initialBalance;
        }

        void deposit(double amount) {
            if (amount <= 0) throw new NegativeAmountException("deposit");
            balance += amount;
            System.out.printf("Deposited ₹%.2f | Balance: ₹%.2f%n", amount, balance);
        }

        void withdraw(double amount) throws InsufficientFundsException {
            if (amount <= 0) throw new NegativeAmountException("withdraw");
            if (amount > balance) throw new InsufficientFundsException(amount - balance);
            balance -= amount;
            System.out.printf("Withdrew ₹%.2f | Balance: ₹%.2f%n", amount, balance);
        }
    }

    public static void main(String[] args) {
        BankAccount acc = new BankAccount("Rahul", 5000);

        try { acc.deposit(1000); } catch (NegativeAmountException e) { System.out.println(e.getMessage()); }
        try { acc.withdraw(2000); } catch (InsufficientFundsException e) { System.out.println(e.getMessage()); }
        try { acc.withdraw(9000); } catch (InsufficientFundsException e) { System.out.println(e.getMessage()); }
        try { acc.deposit(-500); } catch (NegativeAmountException e) { System.out.println(e.getMessage()); }
    }
}
```

**Output:**
```
Deposited ₹1000.00 | Balance: ₹6000.00
Withdrew ₹2000.00 | Balance: ₹4000.00
Insufficient funds. Need ₹5000.00 more.
Cannot deposit a negative amount.
```

---

## 📝 Summary

- **Exceptions** disrupt normal program flow; Java uses a class hierarchy rooted at `Throwable`.
- **Checked exceptions** must be declared with `throws` or caught; **unchecked** (RuntimeException) are optional.
- **`try-catch-finally`** — try the risky code, catch specific errors, finally always runs for cleanup.
- **`throw`** manually throws an exception; **`throws`** declares that a method might throw one.
- **Multi-catch** (`catch (A | B e)`) handles multiple exceptions in one block.
- **try-with-resources** automatically closes `AutoCloseable` resources — cleaner than `finally`.
- **Custom exceptions** extend `Exception` (checked) or `RuntimeException` (unchecked).
- **Assertions** (`assert condition`) test invariants during development; disabled at runtime by default.

---

*Previous → [03 – Working with Dates](./03_Dates.md)*  
*End of Unit 4*

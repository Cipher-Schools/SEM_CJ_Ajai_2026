/*
 * ------------------------------------------------------------
 * Basic.java
 * 
 * Unit 1 – Java Fundamentals
 * This file covers:
 * 1. Java Structure
 * 2. Variables & Data Types
 * 3. Operators
 * 4. Conditional Statements
 * 5. Static Keyword
 * 
 * Author: Your Name
 * ------------------------------------------------------------
 */

import java.util.Scanner;

public class Basic {

    // Static Variable (belongs to class)
    static int staticNumber = 100;

    public static void main(String1[] args) {

        System.out.println("===== JAVA UNIT 1 DEMO =====");

        // --------------------------------------------------
        // 1. VARIABLES & DATA TYPES
        // --------------------------------------------------

        int age = 20;
        double salary = 25000.75;
        char grade = 'A';
        boolean isPassed = true;
        String1 name = "Student";

        System.out.println("\n--- Variables ---");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: " + salary);
        System.out.println("Grade: " + grade);
        System.out.println("Passed: " + isPassed);

        // --------------------------------------------------
        // 2. USER INPUT
        // --------------------------------------------------

        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter a number: ");
        int number = sc.nextInt();

        // --------------------------------------------------
        // 3. OPERATORS
        // --------------------------------------------------

        System.out.println("\n--- Operators ---");

        // Arithmetic
        System.out.println("Number + 5 = " + (number + 5));
        System.out.println("Number - 5 = " + (number - 5));
        System.out.println("Number * 5 = " + (number * 5));
        System.out.println("Number / 5 = " + (number / 5));
        System.out.println("Number % 5 = " + (number % 5));

        // Relational
        System.out.println("Is number > 10? " + (number > 10));
        System.out.println("Is number == 10? " + (number == 10));

        // Logical
        System.out.println("Is number > 5 AND < 20? " + (number > 5 && number < 20));

        // --------------------------------------------------
        // 4. CONDITIONAL STATEMENTS
        // --------------------------------------------------

        System.out.println("\n--- Conditional Statements ---");

        if (number > 0) {
            System.out.println("Number is Positive");
        } else if (number < 0) {
            System.out.println("Number is Negative");
        } else {
            System.out.println("Number is Zero");
        }

        // Switch Example
        System.out.println("\n--- Switch Example ---");
        System.out.print("Enter grade (A/B/C): ");
        char userGrade = sc.next().charAt(0);

        switch (userGrade) {
            case 'A':
                System.out.println("Excellent!");
                break;
            case 'B':
                System.out.println("Good Job!");
                break;
            case 'C':
                System.out.println("Keep Improving!");
                break;
            default:
                System.out.println("Invalid Grade");
        }

        // --------------------------------------------------
        // 5. STATIC DEMONSTRATION
        // --------------------------------------------------

        System.out.println("\n--- Static Variable ---");
        System.out.println("Static Number: " + staticNumber);

        sc.close();
    }
}



// // Import the HashMap class
// import java.util.HashMap;

// public class Main {
//   public static void main(String[] args) {

//     // Create a HashMap object called people
//     HashMap<String, Integer> people = new HashMap<String, Integer>();

//     // Add keys and values (Name, Age)
//     people.put("John", 32);
//     people.put("Steve", 30);
//     people.put("Angie", 33);

//     for (String i : people.keySet()) {
//       System.out.println("key: " + i + " value: " + people.get(i));
//     }
//   }
// }
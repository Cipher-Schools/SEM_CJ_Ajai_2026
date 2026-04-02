# 03 — Working with Dates (java.time API)

---

## 📖 Theory

### Why a New Date API?

Java originally had `java.util.Date` and `java.util.Calendar`, but they were poorly designed — mutable, thread-unsafe, and confusing. Java 8 introduced the **`java.time`** package (inspired by Joda-Time), which is:
- **Immutable** and thread-safe
- **Clear and readable**
- **Comprehensive** (dates, times, durations, time zones)

---

### Core Classes in java.time

| Class | Represents | Example |
|---|---|---|
| `LocalDate` | Date only (no time, no zone) | 2024-09-15 |
| `LocalTime` | Time only (no date, no zone) | 14:30:00 |
| `LocalDateTime` | Date + time (no zone) | 2024-09-15T14:30:00 |
| `ZonedDateTime` | Date + time + time zone | 2024-09-15T14:30:00+05:30[Asia/Kolkata] |
| `Instant` | A point in time (UTC epoch) | 2024-09-15T09:00:00Z |
| `Duration` | Time-based amount (hours, minutes, seconds) | PT2H30M |
| `Period` | Date-based amount (years, months, days) | P1Y2M15D |
| `DateTimeFormatter` | Formatting / parsing dates | yyyy-MM-dd |

---

### LocalDate

```java
import java.time.LocalDate;

// Creating
LocalDate today = LocalDate.now();
LocalDate specific = LocalDate.of(2024, 8, 15);   // Independence Day
LocalDate parsed = LocalDate.parse("2024-08-15");

// Reading
System.out.println(today.getYear());
System.out.println(today.getMonth());           // AUGUST
System.out.println(today.getMonthValue());      // 8
System.out.println(today.getDayOfMonth());
System.out.println(today.getDayOfWeek());       // THURSDAY
System.out.println(today.getDayOfYear());       // 228

// Manipulation (returns NEW instance — immutable!)
LocalDate nextWeek = today.plusDays(7);
LocalDate lastYear = today.minusYears(1);
LocalDate firstOfMonth = today.withDayOfMonth(1);

// Comparison
System.out.println(today.isAfter(specific));
System.out.println(today.isBefore(specific));
System.out.println(today.isLeapYear());
System.out.println(today.lengthOfMonth());      // days in this month
```

---

### LocalTime

```java
import java.time.LocalTime;

LocalTime now = LocalTime.now();
LocalTime morning = LocalTime.of(9, 30, 0);     // 09:30:00
LocalTime parsed = LocalTime.parse("14:30:00");

System.out.println(now.getHour());
System.out.println(now.getMinute());
System.out.println(now.getSecond());

// Manipulation
LocalTime later = morning.plusHours(2).plusMinutes(30);  // 12:00:00
LocalTime truncated = now.withSecond(0).withNano(0);     // remove seconds
```

---

### LocalDateTime

```java
import java.time.LocalDateTime;

LocalDateTime now = LocalDateTime.now();
LocalDateTime dt = LocalDateTime.of(2024, 12, 25, 9, 0, 0);

// Combine date + time
LocalDate date = LocalDate.of(2024, 6, 15);
LocalTime time = LocalTime.of(10, 30);
LocalDateTime combined = LocalDateTime.of(date, time);

// Split
LocalDate extractedDate = now.toLocalDate();
LocalTime extractedTime = now.toLocalTime();
```

---

### Duration and Period

```java
import java.time.*;

// Duration — time-based
LocalTime start = LocalTime.of(9, 0);
LocalTime end = LocalTime.of(17, 30);
Duration workDay = Duration.between(start, end);
System.out.println("Hours: " + workDay.toHours());      // 8
System.out.println("Minutes: " + workDay.toMinutes());  // 510

// Period — date-based
LocalDate dob = LocalDate.of(2000, 5, 20);
LocalDate today = LocalDate.now();
Period age = Period.between(dob, today);
System.out.println("Age: " + age.getYears() + " years, " + age.getMonths() + " months");
```

---

### DateTimeFormatter

```java
import java.time.*;
import java.time.format.DateTimeFormatter;

LocalDate date = LocalDate.of(2024, 8, 15);
LocalDateTime dt = LocalDateTime.now();

// Predefined formatters
System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));  // 2024-08-15
System.out.println(date.format(DateTimeFormatter.BASIC_ISO_DATE));  // 20240815

// Custom pattern
DateTimeFormatter custom = DateTimeFormatter.ofPattern("dd MMM yyyy");
System.out.println(date.format(custom));   // 15 Aug 2024

DateTimeFormatter full = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy 'at' HH:mm");
System.out.println(dt.format(full));       // Thursday, 15 August 2024 at 14:30

// Parsing
LocalDate parsed = LocalDate.parse("15-08-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
System.out.println(parsed);   // 2024-08-15
```

---

## 🧪 Practice Questions

1. Why was the `java.time` package introduced in Java 8?
2. What is the difference between `LocalDate`, `LocalTime`, and `LocalDateTime`?
3. Why are `LocalDate` objects immutable? What does `plusDays()` return?
4. What is the difference between `Duration` and `Period`?
5. How do you format a date as "15 August 2024"?
6. How do you calculate someone's age in years from their date of birth?
7. Parse the string `"25-12-2024"` into a `LocalDate`.
8. What is `ZonedDateTime` and when would you use it over `LocalDateTime`?

---

## 💻 Examples

### Example 1 – LocalDate Fundamentals

```java
import java.time.LocalDate;
import java.time.Month;

public class LocalDateDemo {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate independence = LocalDate.of(2024, Month.AUGUST, 15);

        System.out.println("Today: " + today);
        System.out.println("Day: " + today.getDayOfMonth());
        System.out.println("Month: " + today.getMonth() + " (" + today.getMonthValue() + ")");
        System.out.println("Year: " + today.getYear());
        System.out.println("Day of week: " + today.getDayOfWeek());
        System.out.println("Day of year: " + today.getDayOfYear());
        System.out.println("Leap year: " + today.isLeapYear());
        System.out.println("Days in month: " + today.lengthOfMonth());

        System.out.println("\nManipulation:");
        System.out.println("Tomorrow: " + today.plusDays(1));
        System.out.println("Next month: " + today.plusMonths(1));
        System.out.println("Last year: " + today.minusYears(1));
        System.out.println("First of month: " + today.withDayOfMonth(1));

        System.out.println("\nComparison:");
        System.out.println("Today after independence day: " + today.isAfter(independence));
    }
}
```

---

### Example 2 – Age Calculator

```java
import java.time.*;
import java.time.format.DateTimeFormatter;

public class AgeCalculator {
    public static void main(String[] args) {
        LocalDate[] birthDates = {
            LocalDate.of(2000, 5, 20),
            LocalDate.of(1995, 11, 3),
            LocalDate.of(2003, 8, 15)
        };

        String[] names = {"Arjun", "Priya", "Rahul"};
        LocalDate today = LocalDate.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMM yyyy");

        System.out.printf("%-10s %-15s %-10s%n", "Name", "Born", "Age");
        System.out.println("-".repeat(40));

        for (int i = 0; i < names.length; i++) {
            Period age = Period.between(birthDates[i], today);
            System.out.printf("%-10s %-15s %d years, %d months%n",
                names[i],
                birthDates[i].format(fmt),
                age.getYears(),
                age.getMonths()
            );
        }
    }
}
```

**Output:**
```
Name       Born            Age       
----------------------------------------
Arjun      20 May 2000     24 years, 4 months
Priya      03 Nov 1995     28 years, 11 months
Rahul      15 Aug 2003     21 years, 1 months
```

---

### Example 3 – Work Hours Calculator

```java
import java.time.*;
import java.time.format.DateTimeFormatter;

public class WorkHoursCalc {
    public static void main(String[] args) {
        // Simulate a work schedule
        String[][] shifts = {
            {"09:00", "17:30"},
            {"08:45", "18:00"},
            {"10:00", "16:00"},
            {"09:15", "17:45"},
            {"09:00", "19:00"}
        };
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm");
        Duration totalWeek = Duration.ZERO;

        System.out.printf("%-12s %-8s %-8s %-12s%n", "Day", "In", "Out", "Hours");
        System.out.println("-".repeat(44));

        for (int i = 0; i < days.length; i++) {
            LocalTime in = LocalTime.parse(shifts[i][0], timeFmt);
            LocalTime out = LocalTime.parse(shifts[i][1], timeFmt);
            Duration worked = Duration.between(in, out);
            totalWeek = totalWeek.plus(worked);

            System.out.printf("%-12s %-8s %-8s %dh %02dm%n",
                days[i], shifts[i][0], shifts[i][1],
                worked.toHours(), worked.toMinutesPart());
        }

        System.out.println("-".repeat(44));
        System.out.printf("%-28s %dh %02dm%n", "Total week:",
            totalWeek.toHours(), totalWeek.toMinutesPart());
    }
}
```

**Output:**
```
Day          In       Out      Hours       
--------------------------------------------
Monday       09:00    17:30    8h 30m
Tuesday      08:45    18:00    9h 15m
Wednesday    10:00    16:00    6h 00m
Thursday     09:15    17:45    8h 30m
Friday       09:00    19:00    10h 00m
--------------------------------------------
Total week:                    42h 15m
```

---

### Example 4 – Date Formatting and Parsing

```java
import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateFormatDemo {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();

        // Multiple formats
        String[] patterns = {
            "dd-MM-yyyy",
            "MM/dd/yyyy",
            "dd MMM yyyy",
            "EEEE, dd MMMM yyyy",
            "yyyy-MM-dd HH:mm:ss",
            "dd/MM/yy hh:mm a"
        };

        System.out.println("Same date, different formats:");
        for (String pattern : patterns) {
            System.out.printf("  %-30s → %s%n",
                pattern, now.format(DateTimeFormatter.ofPattern(pattern)));
        }

        // Parsing
        System.out.println("\nParsing strings into dates:");
        String[] dateStrings = {"25-12-2024", "01/01/2025", "15 Aug 2024"};
        String[] parsePatterns = {"dd-MM-yyyy", "MM/dd/yyyy", "dd MMM yyyy"};

        for (int i = 0; i < dateStrings.length; i++) {
            LocalDate parsed = LocalDate.parse(dateStrings[i],
                DateTimeFormatter.ofPattern(parsePatterns[i]));
            System.out.printf("  '%-15s' → %s (Day: %s)%n",
                dateStrings[i], parsed, parsed.getDayOfWeek());
        }
    }
}
```

---

## 📝 Summary

- **`LocalDate`** — date only; **`LocalTime`** — time only; **`LocalDateTime`** — both; **`ZonedDateTime`** — with time zone.
- All `java.time` objects are **immutable** — operations return new instances.
- **`Period`** measures date-based differences (years, months, days); **`Duration`** measures time-based differences (hours, minutes, seconds).
- **`DateTimeFormatter`** formats and parses dates using patterns like `"dd MMM yyyy"`.
- Use `LocalDate.now()`, `LocalDate.of()`, `LocalDate.parse()` to create dates.

---

*Previous → [02 – Functional Interfaces & Lambda Expressions](./02_Lambda_FunctionalInterface.md)*  
*Next → [04 – Exceptions and Assertions](./04_Exceptions_Assertions.md)*

package studentdatabaseapplication;
import java.util.Scanner;

public class Student {
    private String firstName;
    private String lastName;
    private GradeLevel gradeLevel;
    private String studentID;
    private StringBuilder coursesEnrolled;
    private int tuitionBalance = 0;
    private static final int COST_OF_COURSE = 600;
    private static int nextId = 1000;

    private Scanner inputScanner;

    public Student(Scanner scanner) {
        this.inputScanner = scanner;
        System.out.print("Enter Student First Name: ");
        this.firstName = inputScanner.nextLine();

        System.out.print("Enter Student Last Name: ");
        this.lastName = inputScanner.nextLine();

        this.gradeLevel = selectGradeLevel();

        generateStudentID();
        this.coursesEnrolled = new StringBuilder();
    }

    private GradeLevel selectGradeLevel() {
        GradeLevel selectedLevel = null;
        while (selectedLevel == null) {
            System.out.print("1 - Martic\n2 - Intermediate\n3 - B.tech\n4 - M.tech\nEnter Student class level: ");
            while (!inputScanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                inputScanner.next();
                System.out.print("Enter Student class level: ");
            }
            int choice = inputScanner.nextInt();
            inputScanner.nextLine();

            try {
                selectedLevel = GradeLevel.fromCode(choice);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + ". Please try again.");
            }
        }
        return selectedLevel;
    }

    private void generateStudentID() {
        nextId++;
        this.studentID = gradeLevel.getCode() + "" + nextId;
    }

    public void enroll() {
        String course;
        do {
            System.out.print("Enter Course to enroll (Q TO quit): ");
            course = inputScanner.nextLine().trim();

            if (!course.equalsIgnoreCase("Q") && !course.isEmpty()) {
                if (coursesEnrolled.length() > 0) {
                    coursesEnrolled.append("\n  ");
                }
                coursesEnrolled.append(course);
                tuitionBalance += COST_OF_COURSE;
            } else if (course.equalsIgnoreCase("Q")) {
                break;
            } else {
                System.out.println("Course name cannot be empty. Please try again.");
            }
        } while (true);
    }

    public void viewBalance() {
        System.out.println("Your Balance is: $" + tuitionBalance);
    }

    public void payTuition() {
        viewBalance();
        if (tuitionBalance == 0) {
            System.out.println("No tuition balance to pay.");
            return;
        }

        int paymentAmount = 0;
        while (true) {
            System.out.print("Enter yout payment: ");
            if (inputScanner.hasNextInt()) {
                paymentAmount = inputScanner.nextInt();
                inputScanner.nextLine();
                if (paymentAmount <= 0) {
                    System.out.println("Payment amount must be positive. Please try again.");
                } else if (paymentAmount > tuitionBalance) {
                    System.out.println("Payment amount exceeds balance. Please enter a valid amount.");
                } else {
                    break;
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                inputScanner.next();
                inputScanner.nextLine();
            }
        }

        tuitionBalance -= paymentAmount;
        System.out.println("Thank you for your payment of $" + paymentAmount);
        viewBalance();
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName +
                "\nGrade Level: " + (gradeLevel != null ? gradeLevel.getDisplayName() : "N/A") +
                "\nStudent ID: " + studentID +
                "\nCourses Enrolled:\n  " + (coursesEnrolled.length() > 0 ? coursesEnrolled.toString() : "None") +
                "\nBalance: $" + tuitionBalance;
    }
}
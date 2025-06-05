package studentdatabaseapplication;

import java.util.Scanner;

public class StudentDatabaseApp {

    public static void main(String[] args) {
        try (Scanner mainScanner = new Scanner(System.in)) {
            int numOfStudents = getNumberOfStudents(mainScanner);
            Student[] students = createAndEnrollStudents(numOfStudents, mainScanner);
            displayStudentInformation(students);
        } catch (Exception e) {
            System.err.println("An error occurred in the application: " + e.getMessage());
        }
    }

    private static int getNumberOfStudents(Scanner scanner) {
        System.out.print("Enter number of new students to enroll: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static Student[] createAndEnrollStudents(int numOfStudents, Scanner scanner) {
        Student[] students = new Student[numOfStudents];
        scanner.nextLine();
        for (int n = 0; n < numOfStudents; n++) {
            students[n] = new Student(scanner);
            students[n].enroll();
            students[n].payTuition();
        }
        return students;
    }

    private static void displayStudentInformation(Student[] students) {
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }
}
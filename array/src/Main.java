import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.EmptyStackException;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static ArrayList<Student> students = new ArrayList<>();
    private static StudentStack studentStack = new StudentStack();
    private static StudentQueue studentQueue = new StudentQueue();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("1. add Student");
                System.out.println("2. edit Student");
                System.out.println("3. delete Student");
                System.out.println("4. sort Student");
                System.out.println("5. search Student");
                System.out.println("6. display Students");
                System.out.println("7. Add Student to Stack");
                System.out.println("8. Pop Student from Stack");
                System.out.println("9. Display Stack");
                System.out.println("10. Add Student to Queue");
                System.out.println("11. Dequeue Student from Queue");
                System.out.println("12. Display Queue");
                System.out.println("13. exit");
                System.out.print("Choose an option: ");
                int option = sc.nextInt();

                switch (option) {
                    case 1:
                        addStudent(sc);
                        break;
                    case 2:
                        editStudent(sc);
                        break;
                    case 3:
                        deleteStudent(sc);
                        break;
                    case 4:
                        //sortStudents();
                    sortReversed();

                        break;
                    case 5:
                        searchStudent(sc);
                        break;
                    case 6:
                        displayStudents();
                        break;
                    case 7:
                        addStudentToStack(sc);
                        break;
                    case 8:
                        popStudentFromStack();
                        break;
                    case 9:
                        studentStack.displayStack();
                        break;
                    case 10:
                        addStudentToQueue(sc);
                        break;
                    case 11:
                        dequeueStudentFromQueue();
                        break;
                    case 12:
                        studentQueue.displayQueue();
                        break;
                    case 13:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            }catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid integer.");
                sc.nextLine();
            }catch (NoSuchElementException e) {
                System.out.println("error not found student ");
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());

            }
        }

    }

    private static void addStudent(Scanner sc) {
        try {
            System.out.print("Enter ID: ");
            String id = sc.next();
            System.out.print("Enter a name: ");
            String name = sc.next();
            if (name.matches(".*\\d.*")){
                System.out.println("name cannot contain numbers");
                return;
            }
            System.out.print("Enter points: ");
            double marks = sc.nextDouble();
            if (marks < 0 || marks > 10) {
                System.out.println("Score must be between 0 and 10.");
                return;
            }
            students.add(new Student(id, name, marks));
        }catch (InputMismatchException e) {
            System.out.println("Error: Point must be a number");
            sc.nextLine();
            return;
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }
    }

    private static void editStudent(Scanner sc) {
        System.out.print("Enter ID to edit: ");
        String id = sc.next();
        boolean found = false;
        for (Student student : students) {
            if (student.getId().equals(id)) {
                try {
                    System.out.print("Enter New Name: ");
                    String name = sc.next();
                    if (name.matches(".*\\d.*")){
                        System.out.println("name cannot contain numbers");
                        return;
                    }
                    System.out.print("Enter New Points: ");
                    double marks = sc.nextDouble();
                    if (marks < 0 || marks > 10) {
                        System.out.println("Score must be between 0 and 10");
                        return;
                    }
                    students.remove(student);
                    students.add(new Student(id, name, marks));
                    found = true;
                    break;
                }catch(InputMismatchException e ) {
                    System.out.println("Error: point must be a number");
                    sc.nextLine();
                    return;
                }catch(IllegalArgumentException e){
                    System.out.println(e.getMessage());
                    return;
                }
            }
        }
        if(!found){

            System.out.println("No students found.");
        }
    }

    private static void deleteStudent(Scanner sc) {
        System.out.print("Enter ID to delete: ");
        String id = sc.next();
        boolean removed = students.removeIf(student -> student.getId().equals(id));
        if (removed) {
            System.out.println("student has been deleted");

        }else {
            System.out.println("no student found");
        }
    }

    private static void sortStudents() {
//        Collections.sort(students, (s1, s2) -> Double.compare(s2.getMarks(), s1.getMarks()));
        Collections.sort(students, Comparator.comparing(Student::getMarks));
        for(Student std : students){
            System.out.println(std.toString());
        }
        System.out.println("Students have been sorted by score..");
    }

    public static void sortReversed() {
        Collections.sort(students, Comparator.comparing(Student::getMarks).reversed());
        for(Student std : students){
            System.out.println(std.toString());
        }
        System.out.println("Students are arranged in descending order by score.");
    }

    private static void searchStudent(Scanner sc) {
        System.out.print("Enter ID to search: ");
        String id = sc.next();
        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("No students found.");
    }

    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("Empty student list.");
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }
    private static void addStudentToStack(Scanner sc ) {
        System.out.print("Enter ID of the student to add to stack: ");
        String id = sc.next();
        for (Student student : students) {
            if (student.getId().equals(id)) {
                studentStack.push(student);
                System.out.println("Student added to stack.");
                return;
            }
        }
        System.out.println("Student not found.");
    }
    private static void popStudentFromStack() {
        try {
            Student student = studentStack.pop();
            System.out.println("Popped student: " + student);
        } catch (EmptyStackException e) {
            System.out.println("Stack is empty.");
        }
    }

    private static void addStudentToQueue(Scanner sc) {
        System.out.print("Enter ID of the student to add to queue: ");
        String id = sc.next();
        for (Student student : students) {
            if (student.getId().equals(id)) {
                studentQueue.enqueue(student);
                System.out.println("Student added to queue.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private static void dequeueStudentFromQueue() {
        try {
            Student student = studentQueue.dequeue();
            System.out.println("Dequeued student: " + student);
        } catch (NoSuchElementException e) {
            System.out.println("Queue is empty.");
        }
    }
}
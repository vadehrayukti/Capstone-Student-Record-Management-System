import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {
        // 1. Load existing students
        List<Student> loaded = FileUtil.loadStudents(FILE_NAME);

        System.out.println("Loaded students from file:");
        if (loaded.isEmpty()) {
            System.out.println("(none)");
        } else {
            for (Student s : loaded) {
                s.display();
                System.out.println();
            }
        }

        // Print file attributes
        FileUtil.printFileAttributes(FILE_NAME);

        FileUtil.randomReadFirstBytes(FILE_NAME, 100);

        StudentManager manager = new StudentManager(loaded);

        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("===== Capstone Student Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search by Name");
            System.out.println("4. Delete by Name");
            System.out.println("5. Sort by Marks");
            System.out.println("6. Save and Exit");
            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1:
                    // Add student
                    System.out.print("Enter Roll No: ");
                    int roll = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine().trim();
                    System.out.print("Enter Course: ");
                    String course = sc.nextLine().trim();
                    System.out.print("Enter Marks: ");
                    double marks = Double.parseDouble(sc.nextLine().trim());

                    Student s = new Student(roll, name, email, course, marks);
                    boolean added = manager.addStudent(s);
                    if (added) System.out.println("Student added.");
                    else System.out.println("Duplicate roll number; student not added.");
                    break;

                case 2:
                    manager.viewAllStudents();
                    break;

                case 3:
                    System.out.print("Enter name to search: ");
                    String search = sc.nextLine().trim();
                    List<Student> results = manager.searchByName(search);
                    if (results.isEmpty()) {
                        System.out.println("No student found.");
                    } else {
                        for (Student r : results) {
                            r.display();
                            System.out.println();
                        }
                    }
                    break;

                case 4:
                    System.out.print("Enter name to delete: ");
                    String del = sc.nextLine().trim();
                    int removed = manager.deleteByName(del);
                    System.out.println(removed > 0 ? "Student(s) deleted: " + removed : "No student found to delete.");
                    break;

                case 5:
                    manager.sortByMarksDescending();
                    System.out.println("Sorted Student List by Marks:");
                    manager.viewAllStudents();
                    break;

                case 6:
                    // save and exit
                    FileUtil.saveStudents(FILE_NAME, manager.getAllStudentsCopy());
                    System.out.println("Saved successfully. Exiting.");
                    break;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid numeric input: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}

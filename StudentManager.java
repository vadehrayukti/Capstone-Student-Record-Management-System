import java.util.*;

public class StudentManager {

    private final List<Student> studentList;      // for ordered operations
    private final Map<Integer, Student> studentMap; // for quick lookup by rollNo

    public StudentManager(List<Student> initial) {
        // defensive copy
        this.studentList = new ArrayList<>(initial);
        this.studentMap = new HashMap<>();
        for (Student s : studentList) {
            studentMap.put(s.getRollNo(), s);
        }
    }

    /**
     * Add student if rollNo not duplicate. Returns true if added, false if duplicate.
     */
    public boolean addStudent(Student s) {
        if (s == null) throw new IllegalArgumentException("Student cannot be null");
        if (studentMap.containsKey(s.getRollNo())) return false; // duplicate roll
        studentList.add(s);
        studentMap.put(s.getRollNo(), s);
        return true;
    }

    
    public void viewAllStudents() {
        Iterator<Student> it = studentList.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            s.display();
            System.out.println();
        }
    }

    public List<Student> searchByName(String name) {
        List<Student> results = new ArrayList<>();
        if (name == null) return results;
        String key = name.trim().toLowerCase();
        for (Student s : studentList) {
            if (s.getName().toLowerCase().contains(key)) {
                results.add(s);
            }
        }
        return results;
    }

    
    public int deleteByName(String name) {
        if (name == null) return 0;
        String key = name.trim().toLowerCase();
        Iterator<Student> it = studentList.iterator();
        int removed = 0;
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getName().toLowerCase().contains(key)) {
                it.remove();
                studentMap.remove(s.getRollNo());
                removed++;
            }
        }
        return removed;
    }

    public void sortByMarksDescending() {
        studentList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                return Double.compare(b.getMarks(), a.getMarks()); // descending
            }
        });
    }

  
    public List<Student> getAllStudentsCopy() {
        return new ArrayList<>(studentList);
    }

    public Student findByRoll(int rollNo) {
        return studentMap.get(rollNo);
    }
}

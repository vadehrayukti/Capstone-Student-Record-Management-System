import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

   
    public static List<Student> loadStudents(String fileName) {
        List<Student> list = new ArrayList<>();
        File f = new File(fileName);

        if (!f.exists()) {
            // file does not exist; return empty list
            return list;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                // skip empty lines
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length != 5) continue; // ignore malformed lines
                int roll = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String email = parts[2].trim();
                String course = parts[3].trim();
                double marks = Double.parseDouble(parts[4].trim());
                list.add(new Student(roll, name, email, course, marks));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Malformed number in file: " + e.getMessage());
        }
        return list;
    }

    public static void saveStudents(String fileName, List<Student> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false))) {
            for (Student s : list) {
                bw.write(s.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }

    /**
     * Show file attributes (exists, length, lastModified)
     */
    public static void printFileAttributes(String fileName) {
        File f = new File(fileName);
        System.out.println("File: " + fileName);
        System.out.println("Exists: " + f.exists());
        if (f.exists()) {
            System.out.println("Size (bytes): " + f.length());
            System.out.println("Last Modified: " + f.lastModified());
            System.out.println("Readable: " + f.canRead() + ", Writable: " + f.canWrite());
        }
    }

  
    public static void randomReadFirstBytes(String fileName, int bytesToRead) {
        File f = new File(fileName);
        if (!f.exists()) {
            System.out.println("RandomAccessFile: file does not exist.");
            return;
        }
        try (RandomAccessFile raf = new RandomAccessFile(f, "r")) {
            byte[] buf = new byte[Math.min(bytesToRead, (int) Math.min(bytesToRead, raf.length()))];
            int read = raf.read(buf);
            if (read > 0) {
                System.out.println("RandomAccessFile (first " + read + " bytes):");
                System.out.println(new String(buf, 0, read));
            } else {
                System.out.println("RandomAccessFile: nothing to read.");
            }
        } catch (IOException e) {
            System.err.println("RandomAccessFile error: " + e.getMessage());
        }
    }
}

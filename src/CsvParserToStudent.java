import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvParserToStudent {

    /**
     * Receve string in format <studentName>,<studentIdNumber>,<subject1><subject1_value>...
     * @param str
     * @return Student obj, based on info from received line
     * @throws IllegalArgumentException if received string cannot be converted to Student
     */
    private static Student stringToStudent(final String str){
        if (str == null) throw new IllegalArgumentException("Received String cannot be null");
        final String[] parts = str.split(",");
        if (parts.length <= 2) throw new IllegalArgumentException("Received string cannot be converted to Student obj");
        final String name = parts[0];
        final Integer idNumber;
        final Map<String, Integer> subjects = new HashMap<>();
        try {
            idNumber = Integer.parseInt(parts[1]);
            for (int i = 2; i < parts.length - 1; i += 2){
                subjects.put(parts[i], Integer.parseInt(parts[i + 1]));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Received string cannot be converted to Student obj, elements are out of order");
        }
        return new Student(name, idNumber, subjects);
    }


    /**
     * Parsing information about students from received file and returns List of Student objects
     * @param filepath
     * @return List of Student obj, parsed from received file by filepath
     * @throws InvalidPathException, RuntimeException(IOException)
     */
    public static List<Student> getStudentsFromFile(final Path filepath) {
        if (filepath == null) throw new IllegalArgumentException("filepath cannot be null");
        final List<Student> students = new ArrayList<>();
        try (final BufferedReader reader = Files.newBufferedReader(filepath)){
            while(reader.ready()){
                final String studentCodedString = reader.readLine();
                final Student student = stringToStudent(studentCodedString);
                students.add(student);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return students;
    }
}

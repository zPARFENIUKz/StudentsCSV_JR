import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvParserToStudent {
    private static String[] getStrings(final String filepath){
        try (final BufferedReader reader = Files.newBufferedReader(Path.of(filepath))) {
            final List<String> strings = new ArrayList<>();
            while(reader.ready()){
                strings.add(reader.readLine());
            }
            return strings.toArray(String[]::new);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Student stringToStudent(final String str){
        final String[] parts = str.split(",");
        final String name = parts[0];
        final Integer idNumber = Integer.parseInt(parts[1]);
        final Map<String, Integer> subjects = new HashMap<>();
        for (int i = 2; i < parts.length - 1; i += 2){
            subjects.put(parts[i], Integer.parseInt(parts[i + 1]));
        }
        return new Student(name, idNumber, subjects);
    }

    public static List<Student> getStudentsFromFile(final String filepath){
        final String[] strings = getStrings(filepath);
        final List<Student> students = new ArrayList<>(strings.length);
        for (final String str : strings){
            students.add(stringToStudent(str));
        }
        return students;
    }
}

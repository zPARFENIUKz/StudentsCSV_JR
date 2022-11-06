import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

public class Main {
    private static final String csvPath = "C:\\Users\\zzzif\\IdeaProjects\\StudentsCSV\\CSV\\input.csv";
    private static final String csvOutputPath = "C:\\Users\\zzzif\\IdeaProjects\\StudentsCSV\\CSV\\output.csv";

    public static void main(String[] args) {
        final List<Student> students = CsvParserToStudent.getStudentsFromFile(csvPath);
        final University university = new University(students);

        // Получаем всех студентов и их оценки по названию предмета
        List<Student> english_students = university.selectStudents(University.StudentSelector.BY_SUBJECT, "English");

        // Получить средний балл студента по номеру его зачетки
        Student student = university.selectStudents(University.StudentSelector.BY_ID_NUMBER, 436257).get(0);
        double studentAverageScore = student.getAverage();

        // Получить рейтинг всех студентов
        List<Student> topStudents = university.getStudents().stream().sorted(Comparator.reverseOrder()).toList();
        writeStudentsToCSV(csvOutputPath, topStudents);

    }

    private static void writeStudentsToCSV(final String filepath, final List<Student> students){
        try (final BufferedWriter writer = Files.newBufferedWriter(Path.of(filepath))) {
            for (final Student student : students){
                writer.write(student.toString() + '\n');
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

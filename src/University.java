import java.util.Collections;
import java.util.List;

public class University {
    private List<Student> students;

    public University(final List<Student> students){
        this.students = students;
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    public List<Student> selectStudents(StudentSelector selector, Object param) {
        if (selector == StudentSelector.BY_NAME){
            final String name = (String) param;
            return students.stream().filter(student -> student.getName().contains(name)).toList();
        } else if (selector == StudentSelector.BY_ID_NUMBER){
            final String idNumberStr = String.valueOf((Integer) param);
            return students.stream().filter(student -> String.valueOf(student.getIdNumber()).contains(idNumberStr)).toList();
        } else if (selector == StudentSelector.BY_SUBJECT){
            final String subject = (String) param;
            return students.stream().filter(student -> student.getSubjects().containsKey(subject)).toList();
        }
        throw new IllegalArgumentException("uncorrect selector");
    }


    public enum StudentSelector {
        BY_NAME,
        BY_ID_NUMBER,
        BY_SUBJECT
    }
}

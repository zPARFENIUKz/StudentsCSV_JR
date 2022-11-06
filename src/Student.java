import java.util.Collection;
import java.util.Map;

public class Student implements Comparable {
    private final String name;
    private final int idNumber;
    private Map<String, Integer> subjects;

    public Student(final String name, final int idNumber, final Map<String, Integer> subjects){
        this.name = name;
        this.idNumber = idNumber;
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public Map<String, Integer> getSubjects() {
        return subjects;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Student){
            return (int) (getAverage() - ((Student) o).getAverage());
        }
        throw new IllegalArgumentException("o must be Student");
    }

    public double getAverage() {
        final Collection<Integer> values = subjects.values();
        final int sum = values.stream().reduce(Integer::sum).get();
        return sum * 1.0 / values.size();
    }

    @Override
    public String toString() {
        String resultString = "";
        resultString += name + ",";
        resultString += idNumber + ",";
        resultString += subjectsToString();
        return resultString;
    }

    private String subjectsToString(){
        String result = "";
        for (final Map.Entry<String, Integer> entry : subjects.entrySet()){
            result += entry.getKey() + "," + entry.getValue() + ",";
        }
        return result;
    }
}

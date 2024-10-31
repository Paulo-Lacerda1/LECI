package aula11.ex2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gradebook {
    List<Student> data = new ArrayList<>();

    public void load(String path) throws IOException {
        String[] lines = Files.readString(Paths.get(path)).split("\n");
        for (Object key : lines) {
            String[] line = key.toString().split("\\|");

            List<Double> results2 = new ArrayList<>();
            for (String key2 : Arrays.copyOfRange(line, 1, 5)) {
                results2.add(Double.parseDouble(key2));
            }

            Student student = new Student(line[0], results2);
            data.add(student);
        }
    }

    public void addStudent(Student student) {
        data.add(student);
    }

    public void removeStudent(String name) {
        data.remove(getStudent(name));
    }

    public Student getStudent(String name) {
        for (Student student : data) {
            if (student.getName().equals(name))
                return student;
        }
        return null;
    }

    public Double calculateAverageGrade(String name) {
        Student student = getStudent(name);
        double total = 0;

        for (Double grade : student.getGrades()) {
            total += grade;
        }
        return total / 3;
    }

    public void printAllStudents() {
        for (Student student : data) {
            System.out.println(student.toString());
        }
    }
}

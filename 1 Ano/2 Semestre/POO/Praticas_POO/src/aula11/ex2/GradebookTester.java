package aula11.ex2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GradebookTester {
    public static void main(String[] args) throws IOException {
        Gradebook gradebook = new Gradebook();

        gradebook.load("alunos.txt");
        gradebook.printAllStudents();

        Student newStudent = new Student("Johny May", new ArrayList<>(List.of(10.0, 15.0, 19.0)));
        gradebook.addStudent(newStudent);

        gradebook.removeStudent("Jane Smith");

        double averageGrade = gradebook.calculateAverageGrade("John Doe");
        System.out.printf("Average grade for John Doe: %.2f\n", averageGrade);

        gradebook.printAllStudents();
    }
}

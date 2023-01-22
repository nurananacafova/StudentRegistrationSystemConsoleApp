package util;

import beans.Student;
import main.Config;

public class StudentUtil {
    public static Student fillStudent() {
//        String name = InputUtil.requireText("enter name"); ve ya
        String name = MenuUtil.requireName();
        String surname = MenuUtil.requireSurname();
        int age = MenuUtil.requireAge();
        String className = MenuUtil.requireClassname();

        Student student = new Student(name, surname, age, className);
        return student;
    }

    public static void printAllRegisteredStudents() {
        if (Config.students == null) {
            return;
        }//yoxlamasaq onda NullPointerException atir
        for (int i = 0; i < Config.students.length; i++) {
            Student student = Config.students[i];
            System.out.println((i + 1) + "." + student.getFullInfo());
        }
    }

    public static void registerStudent() {
        int count = InputUtil.requireNumber("Neçə tələbəni qeydiyyatdan keçirəcəksiniz?");
        Config.students = new Student[count];
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ".Register");

            Config.students[i] = StudentUtil.fillStudent();
        }
        MenuUtil.showSuccessOpMessage();
        StudentUtil.printAllRegisteredStudents();
    }

    public static void findStudentsAndPrint() {
        String text = InputUtil.requireText("type name, surname or className");
        Student[] result = findStudents(text);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i].getFullInfo());
        }
    }

    public static Student[] findStudents(String text) {
        int count = 0;
        for (int i = 0; i < Config.students.length; i++) {
            Student student = Config.students[i];
            if (student.getName().contains(text) || student.getSurname().contains(text) || student.getClassName().contains(text)) {
                count++;
            }
        }//count ile nece studentin olduqunu tapib o olcude massiv yaradiriq
        Student[] result = new Student[count];
        int found = 0;
        for (int i = 0; i < Config.students.length; i++) {
            Student student = Config.students[i];
            if (student.getName().contains(text) || student.getSurname().contains(text) || student.getClassName().contains(text)) {
                result[found] = student;
                found++;
            }
        }
        return result;
    }

    public static void updateStudentWithNewObject() {
        //bu usulda yeni obyekt yaranir, olan seyi deyisdirmirik
        //bir nov yeni obyekt yaradib deyismek istediyimiz obyektle evez edirik
        StudentUtil.printAllRegisteredStudents();
        int i = InputUtil.requireNumber("ID daxil edin");

        System.out.println("Yeni melumatlari daxil edin:");

        Student student = StudentUtil.fillStudent();
        Config.students[i - 1] = student;
    }

    public static void updateStudentWithSameObject() {
        StudentUtil.printAllRegisteredStudents();//her kesi capa ver
        int i = InputUtil.requireNumber("ID daxil edin");
        System.out.println("Yeni melumatlari daxil edin:");

        Student selectedStudent = Config.students[i - 1];
        String changeParams = InputUtil.requireText("Neyi deyismek isteyirsiniz?: 'name','surname','age','class name'");
        if (changeParams.contains("'name'")) {
//              String name=InputUtil.requireText("name");
//              selectedStudent.setName(name); kimi de yaza bilerik asaqidakilari
            selectedStudent.setName(InputUtil.requireText("name"));
        }
        if (changeParams.contains("'surname'")) {
            selectedStudent.setSurname(InputUtil.requireText("surname"));
        }
        if (changeParams.contains("'age'")) {
            selectedStudent.setAge(InputUtil.requireNumber("age"));
        }
        if (changeParams.contains("'classname'")) {
            selectedStudent.setClassName(InputUtil.requireText("classname"));
        }
    }

    public static void updateStudentWithSplit() {
        StudentUtil.printAllRegisteredStudents();//her kesi capa ver
        int index = InputUtil.requireNumber("ID daxil edin");
        System.out.println("Yeni melumatlari daxil edin:");

        Student selectedStudent = Config.students[index - 1];
        String changeParams = InputUtil.requireText("Neyi deyismek isteyirsiniz?: name,surname,age,class name");
        String[] parameters = changeParams.split(",");
        for (int i = 0; i < parameters.length; i++) {
            String param = parameters[i];

            if (param.equalsIgnoreCase("name")) {
                selectedStudent.setName(InputUtil.requireText("name"));
            }
            if (param.equalsIgnoreCase("surname")) {
                selectedStudent.setSurname(InputUtil.requireText("surname"));
            }
            if (param.equalsIgnoreCase("age")) {
                selectedStudent.setAge(InputUtil.requireNumber("age"));
            }
            if (param.equalsIgnoreCase("classname")) {
                selectedStudent.setClassName(InputUtil.requireText("classname"));
            }
        }
    }
}

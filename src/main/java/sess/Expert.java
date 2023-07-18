package sess;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Expert extends User{
    static String username = "admin";
    static String password = "admin";
    String FullName;
    static int ID=0;

    static ArrayList<Student> Students = new ArrayList<>();
    static ArrayList<Professor> Professors = new ArrayList<>();
    static ArrayList<Lesson> Lessons = new ArrayList<>();
    static ArrayList<Semester> Semesters = new ArrayList<>();

    private static Expert singleExpert = null;
    private Expert(){}

    public static Expert getInstance() {
        if (singleExpert == null) {
            singleExpert = new Expert();
        }
        return singleExpert;
    }

    static void AddSemester(String title, LocalDate record, LocalDate begin, LocalDate end){
        Semesters.add(new Semester(title, record, begin, end));
    }
    static void AddProfessor(String fullName, String field, String usernam, String pasword){
        Professors.add(new Professor(fullName, ID++, field, usernam, pasword));
    }
    static void AddStudent(String fullName, String usernam, String pasword){
        Students.add(new Student(fullName, ID++, usernam, pasword));
    }
    static String showProfessors(){
        String temp = "";
        int counter = 1;
        for(Professor i:Professors){
            temp += counter + ". " + i.FullName + "    Field: " + i.Field + '\n';
            counter++;
        }
        return temp;
    }
    static void AddLesson(String title, LocalDate midterm, LocalDate aFinal, int professor, int units){
        Lesson temp = new Lesson(title, midterm, aFinal, Professors.get(professor), units);
        Lessons.add(temp);
        Professors.get(professor).Lessons.add(temp);
        Semesters.get(Semesters.size()-1).Lessons.add(temp);//add to current(latest) semester.
    }

    static String ListAll(){
        String temp = "";
        int counter=1;
        temp += "Professors:\n";
        if(Professors.size()>0)
            for (Professor i:Professors) {
                temp += counter + ". " + i.FullName + '\n';
                counter++;
            }
        else
            temp += "404! Not Found!\n";
        counter=1;
        temp += "Students:\n";
        if(Students.size()>0)
            for (Student i:Students) {
                temp += counter + ". " + i.FullName + '\n';
                counter++;
            }
        else
            temp += "404! Not Found!\n";
        counter=1;
        temp += "Semesters:\n";
        if(Semesters.size()>0)
            for (Semester i:Semesters) {
                temp += counter + ". " + i.Title + '\n';
                counter++;
            }
        else
            temp += "404! Not Found!\n";
        counter=1;
        temp += "Lessons:\n";
        if(Lessons.size()>0)
            for (Lesson i:Lessons) {
                temp += counter + ". " + i.Title + '\n';
                counter++;
            }
        else
            temp += "404! Not Found!\n";
        return temp;
    }
    static int ChangePassword(String curPass, String newPass){
        if (Objects.equals(password, curPass)) {
            password = newPass;
        }
        else {
            return -1;
        }
        return 1;
    }
}

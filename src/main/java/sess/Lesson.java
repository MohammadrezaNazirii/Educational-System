package sess;

import java.time.LocalDate;
import java.util.ArrayList;

public class Lesson {
    String Title;
    int Units;
    String Syllabus = "";
    String Reference = "";
    LocalDate Midterm;
    LocalDate Final;
    Professor professor;
    ArrayList<Exam> Exams = new ArrayList<>();
    ArrayList<Student> Students = new ArrayList<>();

    public Lesson(String title, LocalDate midterm, LocalDate aFinal, Professor professor, int units) {
        Title = title;
        Midterm = midterm;
        Final = aFinal;
        this.professor = professor;
        Units = units;
    }

    String showStudents(){
        String temp = "";
        int counter=1;
        for(Student i:Students){
            temp += counter + ". " + i.FullName;
            counter++;
        }
        return temp;
    }

    String showDetails(){
        String temp = "";
        temp += Title + "\nSyllabus: " + Syllabus + "\nReference: " + Reference + "\nProfessor: " + professor + "\nMidterm Date: " + Midterm + "\nFinal Date: " + Final;
        return temp;
    }

    @Override
    public String toString(){
        return Title+"\n"+Midterm.getYear()+"\n"+Midterm.getDayOfYear()+"\n"+Final.getYear()+"\n"+Final.getDayOfYear()+"\n"+Expert.Professors.indexOf(professor)+"\n"+Units;
    }
}

package sess;

import java.util.ArrayList;

public class Professor extends User{
    String username;
    String password;
    String FullName;
    int ID;
    String Field;

    ArrayList<Lesson> Lessons = new ArrayList<>();
    Lesson LessonForDesignExam = null;

    public Professor(String fullName, int ID, String field, String user, String pass) {
        FullName = fullName;
        this.ID = ID;
        Field = field;
        username = user;
        password = pass;
    }

    // showLessons -> getLessons
    void AddExam(int a){ // Exam -> showExam(Exam), addQuestion(Professor)
        LessonForDesignExam = Lessons.get(a-1);
        LessonForDesignExam.Exams.add(new Exam());
        LessonForDesignExam.Exams.get(LessonForDesignExam.Exams.size()-1).LessonTitle = LessonForDesignExam.Title;
    }
    void AddQuestionToExam(String question, String sol){
        LessonForDesignExam.Exams.get(LessonForDesignExam.Exams.size()-1).Problems.add(new DescriptiveProblem(question, sol));
    }
    void AddQuestionToExam(String Qpart1, String Qpart2, String sol){
        LessonForDesignExam.Exams.get(LessonForDesignExam.Exams.size()-1).Problems.add(new BlankProblem(Qpart1, Qpart2, sol));
    }
    void AddQuestionToExam(String q, String a1, String a2, String a3, String a4, String sol){
        LessonForDesignExam.Exams.get(LessonForDesignExam.Exams.size()-1).Problems.add(new TestProblem(q, a1, a2, a3, a4, sol));
    }
    void AddQuestionToExam(String q, Boolean t, String sol){
        LessonForDesignExam.Exams.get(LessonForDesignExam.Exams.size()-1).Problems.add(new TFProblem(q, sol));
    }

    String getLessons(){
        String temp = "";
        int counter=1;
        for(Lesson i:Lessons){
            temp += counter + ". " + i.Title + '\n';
            counter++;
        }
        return temp;
    }

    @Override
    public String toString(){
        return FullName+"\n"+username+"\n"+password+"\n"+Field;
    }
}

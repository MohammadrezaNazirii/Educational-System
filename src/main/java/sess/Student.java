package sess;

import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User{
    String username;
    String password;
    String FullName;
    int ID;
    int Units = 0;

    ArrayList<Lesson> Lessons = new ArrayList<>();
    ArrayList<Lesson> LessonsWithExam = new ArrayList<>();
    Lesson currentExam = null;
    HashMap<String, ArrayList<Integer>> Score = new HashMap<>();


    public Student(String fullName, int ID, String user, String pass) {
        FullName = fullName;
        this.ID = ID;
        username = user;
        password = pass;
    }

    String showLessons(){
        String temp = "";
        int counter=1;
        for(Lesson i:Expert.Lessons){
            temp += counter + ". " + i.Title + "    " + i.professor + '\n';
            counter++;
        }
        return temp;
    }

    //showLessons
    void getLesson(int a){
        Lessons.add(Expert.Lessons.get(a-1));
        Expert.Lessons.get(a-1).Students.add(this);
        Units += Lessons.get(a-1).Units;
    }
    String showLessonsWithExam(){ //show Lesson with exam
        String temp = "";
        int counter = 1;
        for(Lesson i:Lessons){
            if(i.Exams.size() != 0){
                LessonsWithExam.add(i);
                temp += counter + ". " + i.Title + '\n';
                counter++;
            }
        }
        return temp;
    }
    void getExam(int a){//show Exam
        currentExam = LessonsWithExam.get(a-1);
        currentExam.Exams.get(currentExam.Exams.size()-1).StudentsAnswers.put(FullName, new ArrayList<String>());
        Score.put(currentExam.Exams.get(currentExam.Exams.size()-1).Title, new ArrayList<Integer>());
    }
    String f(){
        StringBuilder temp= new StringBuilder();
        temp.append(FullName).append("\n").append(username).append("\n").append(password).append("\n");
        for (int i =0; i<Lessons.size();i++){
            temp.append(Lessons.get(i).Title).append("\n");
        }
        temp.append("kfngksdjnksdngkddngksngknsknsgknk;sgnk;jgkgkrkrtgj");
        return temp.toString();
    }
    @Override
    public String toString(){
        return f();
    }
}

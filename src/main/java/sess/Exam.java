package sess;

import java.util.ArrayList;
import java.util.HashMap;

public class Exam {
    String Title;
    String LessonTitle;
    ArrayList<Problem> Problems = new ArrayList<>();
    HashMap<String, ArrayList<String>> StudentsAnswers = new HashMap<>();

    String showExam(){
        String temp = "";
        int counter = 1;
        for(Problem i:Problems){
            temp += counter + ". " + i.Print() + '\n';
            counter++;
            temp += i.PrintSol() + '\n';
            temp += "------------------\n";
        }
        return temp;
    }
}

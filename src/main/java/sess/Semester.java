package sess;

import java.time.LocalDate;
import java.util.ArrayList;

public class Semester {
    String Title;
    LocalDate RecordScores;
    LocalDate Begin;
    LocalDate End;
    ArrayList<Lesson> Lessons = new ArrayList<>();

    public Semester(String title, LocalDate recordScores, LocalDate begin, LocalDate end) {
        Title = title;
        RecordScores = recordScores;
        Begin = begin;
        End = end;
    }
    @Override
    public String toString(){
        return Title+"\n"+Begin.getYear()+"\n"+Begin.getDayOfYear()+"\n"+
                End.getYear()+"\n"+End.getDayOfYear()+"\n"
                +
                RecordScores.getYear()+"\n"+RecordScores.getDayOfYear();
    }
}

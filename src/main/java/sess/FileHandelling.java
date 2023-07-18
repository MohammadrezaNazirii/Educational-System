package sess;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class FileHandelling {
    static void readFromFile() {
        BufferedReader reader;
        ArrayList<String> temp = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ASUS\\IdeaProjects\\javaProj\\src\\main\\resources\\Professors.txt"));
            String line = reader.readLine();
            temp.add(line);
            while (line != null) {
                line = reader.readLine();
                temp.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < temp.size() - 1; i = i + 4) {
            Expert.AddProfessor(temp.get(i), temp.get(i + 3), temp.get(i + 1), temp.get(i + 2));
        }
        temp.clear();
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ASUS\\IdeaProjects\\javaProj\\src\\main\\resources\\Semesters.txt"));
            String line = reader.readLine();

            temp.add(line.replace("\n", ""));
            while (line != null) {
                line = reader.readLine();
                temp.add(line);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < temp.size() - 1; i = i + 7) {
            LocalDate t;
            LocalDate p;
            LocalDate s;
            s = LocalDate.ofYearDay(Integer.parseInt(temp.get(i + 1)), Integer.parseInt(temp.get(i + 2)));
            p = LocalDate.ofYearDay(Integer.parseInt(temp.get(i + 3)), Integer.parseInt(temp.get(i + 4)));
            t = LocalDate.ofYearDay(Integer.parseInt(temp.get(i + 5)), Integer.parseInt(temp.get(i + 6)));

            Expert.AddSemester(temp.get(i), s, p, t);
        }
        temp.clear();

        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ASUS\\IdeaProjects\\javaProj\\src\\main\\resources\\Lessons.txt"));
            String line = reader.readLine();

            temp.add(line.replace("\n", ""));
            while (line != null) {
                line = reader.readLine();
                temp.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < temp.size() - 1; i = i + 7) {
            LocalDate p;
            LocalDate s;
            s = LocalDate.ofYearDay(Integer.parseInt(temp.get(i + 1)), Integer.parseInt(temp.get(i + 2)));
            p = LocalDate.ofYearDay(Integer.parseInt(temp.get(i + 3)), Integer.parseInt(temp.get(i + 4)));
            Expert.AddLesson(temp.get(i), s, p, Integer.parseInt(temp.get(i + 5)), Integer.parseInt(temp.get(i + 6)));
        }
        temp.clear();

        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ASUS\\IdeaProjects\\javaProj\\src\\main\\resources\\Students.txt"));
            String line = reader.readLine();

            temp.add(line);
            while (line != null) {
                line = reader.readLine();
                temp.add(line);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < temp.size() - 1; i++) {
            Expert.AddStudent(temp.get(i), temp.get(i + 1), temp.get(i + 2));
            i += 3;
            while (!Objects.equals(temp.get(i), "kfngksdjnksdngkddngksngknsknsgknk;sgnk;jgkgkrkrtgj")) {
                String j = temp.get(i);
                for (Lesson lesson : Expert.Lessons) {
                    if (Objects.equals(j, lesson.Title)) {
                        Expert.Students.get(Expert.Students.size() - 1).Lessons.add(lesson);
                    }
                }
                i++;
            }
        }

        temp.clear();
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\ASUS\\IdeaProjects\\javaProj\\src\\main\\resources\\Exams.txt"));
            String line = reader.readLine();
            temp.add(line);
            while (line != null) {
                line = reader.readLine();
                temp.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Lesson g=null;
        Exam exam=null;
        for (int i = 0 ; i<temp.size()-1;i++){
            if (Objects.equals(temp.get(i + 1), ";;;///..@@@@@@%%%%%%^^^^")){
                for (Lesson l : Expert.Lessons){
                    if (Objects.equals(l.Title, temp.get(i))){
                        exam=new Exam();
                        exam.LessonTitle = l.Title;
                        i++;
                        i++;
                        l.Exams.add(exam);
                        g=l;
                        break;
                    }
                }
            }else if(Objects.equals(temp.get(i), "123456789zxcvbn,mnkhifufyu")&&g!=null){
                exam=new Exam();
                exam.LessonTitle = g.Title;
                g.Exams.add(exam);
            }
            else{
                switch (temp.get(i)){
                    case "1":{
                        exam.Problems.add(new BlankProblem(temp.get(i+1), "", temp.get(i+2)));
                        i++;
                        i++;
                        break;
                    }
                    case "2":{
                        exam.Problems.add(new DescriptiveProblem(temp.get(i+1), temp.get(i+2)));
                        i++;
                        i++;
                        break;
                    }
                    case "3":{
                        exam.Problems.add(new TestProblem(temp.get(i+1),temp.get(i+2),temp.get(i+3),temp.get(i+4),temp.get(i+5),temp.get(i+6)));
                        i++;
                        i++;
                        i++;
                        i++;
                        i++;
                        i++;
                        break;
                    }
                    case "4":{
                        exam.Problems.add(new TFProblem(temp.get(i+1),temp.get(i+2)));
                        i++;
                        i++;
                        break;
                    } default:{
                        exam.Title=temp.get(i);
                    }
                }
            }
        }
    }

    static void WriteToFile() {
        try (PrintWriter pw = new PrintWriter("C:\\Users\\ASUS\\IdeaProjects\\javaProj\\src\\main\\resources\\Students.txt")) {
            for (Student student : Expert.Students) {
                pw.println(student.toString());
            }

        } catch (FileNotFoundException e) {
            System.out.print("file not found");
            e.printStackTrace();
        }
        try (PrintWriter pw = new PrintWriter("C:\\Users\\ASUS\\IdeaProjects\\javaProj\\src\\main\\resources\\Professors.txt")) {
            for (Professor professor : Expert.Professors) {
                pw.println(professor.toString());
            }

        } catch (FileNotFoundException e) {
            System.out.print("file not found");
            e.printStackTrace();
        }
        try (PrintWriter pw = new PrintWriter("C:\\Users\\ASUS\\IdeaProjects\\javaProj\\src\\main\\resources\\Lessons.txt")) {
            for (Lesson lesson : Expert.Lessons) {
                pw.println(lesson.toString());
            }
        } catch (FileNotFoundException e) {
            System.out.print("file not found");
            e.printStackTrace();
        }
        try (PrintWriter pw = new PrintWriter("C:\\Users\\ASUS\\IdeaProjects\\javaProj\\src\\main\\resources\\Semesters.txt")) {
            for (Semester semester : Expert.Semesters) {
                pw.println(semester.toString());
            }
        } catch (FileNotFoundException e) {
            System.out.print("file not found");
            e.printStackTrace();
        }
        try (PrintWriter pw = new PrintWriter("C:\\Users\\ASUS\\IdeaProjects\\javaProj\\src\\main\\resources\\Exams.txt"))
        {
            for (Lesson lesson : Expert.Lessons){
                if (lesson.Exams.size()!=0) {
                    pw.println(lesson.Title + "");
                    pw.println(";;;///..@@@@@@%%%%%%^^^^");
                }
                for (Exam exam :lesson.Exams){
                    pw.println("123456789zxcvbn,mnkhifufyu");
                    pw.println(exam.Title);
                    for (Problem problem: exam.Problems)
                        pw.println(problem.toString());
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.print("file not found");
            e.printStackTrace();
        }
    }
}

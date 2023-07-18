package sess;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.util.Objects;

public class StudentScenes {
    static void MainScene(Stage stage, Student s){
        Button TakeExamButton = new Button("Take Exam");
        Button GetLessonButton = new Button("Get Lesson");
        Button MyLessonsButton = new Button("My Lessons");
        Button ChangePasswordButton = new Button("Change Password");
        Button Submit = new Button("Submit");
        Button Exit = new Button("Exit");

        TakeExamButton.setCursor(Cursor.HAND);
        GetLessonButton.setCursor(Cursor.HAND);
        MyLessonsButton.setCursor(Cursor.HAND);
        ChangePasswordButton.setCursor(Cursor.HAND);
        Exit.setCursor(Cursor.HAND);
        Submit.setCursor(Cursor.HAND);

        TakeExamButton.setTooltip(new Tooltip("Take Exam"));
        GetLessonButton.setTooltip(new Tooltip("Get Lesson"));
        MyLessonsButton.setTooltip(new Tooltip("My Lessons"));
        ChangePasswordButton.setTooltip(new Tooltip("Change Password"));
        Exit.setTooltip(new Tooltip("Exit"));
        Submit.setTooltip(new Tooltip("Submit"));

        Button Back = new Button("Back");
        Back.setCursor(Cursor.HAND);

        Label Select = new Label("Lesson Number:");
        TextField select = new TextField();
        HBox SelectHBox = new HBox(Select, select);
        SelectHBox.setAlignment(Pos.CENTER);
        SelectHBox.setSpacing(15);

        Button SubmitExam = new Button("Submit");
        SubmitExam.setCursor(Cursor.HAND);
        SubmitExam.setTooltip(new Tooltip("Submit"));

        Label list = new Label("");
        Label error = new Label("");

        VBox root = new VBox();
        root.getChildren().addAll(list, TakeExamButton, GetLessonButton, MyLessonsButton, ChangePasswordButton, Exit, error);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        Scene StudentScene = new Scene(root, 400, 700);
        stage.setScene(StudentScene);

        TakeExamButton.setOnAction(actionEvent -> {
            if(Objects.equals(s.showLessonsWithExam(), "")){
                error.setText("There is no lesson with exam:/");
            }else{
                list.setText(s.showLessonsWithExam());
                root.getChildren().removeAll(list, TakeExamButton, GetLessonButton, MyLessonsButton, ChangePasswordButton, Exit, error);
                root.getChildren().addAll(list, SelectHBox, SubmitExam, Back, error);
            }
        });

        SubmitExam.setOnAction(actionEvent -> {
            error.setText("");
            if(select.getText().chars().allMatch( Character::isDigit )){
                if(Integer.parseInt(select.getText()) <= s.LessonsWithExam.size()){
                    error.setText("");
                    s.getExam(Integer.parseInt(select.getText()));
                    ExamScene(stage, s);
                }else{
                    error.setText("Index Out of range.");
                }
            }else{
                error.setText("Invalid Input:/");
            }
        });

        GetLessonButton.setOnAction(actionEvent -> {
            error.setText("");
            if(Expert.Lessons.size() == 0){
                error.setText("No lessons found:/");
            }else {
                String temp = "";
                int counter=1;
                for(Lesson i:Expert.Lessons){
                    if(!s.Lessons.contains(i)){
                        temp += counter + ". " + i.Title + '\n';
                    }
                    counter++;
                }
                if(!temp.equals("")) {
                    list.setText(temp);
                    root.getChildren().removeAll(list, TakeExamButton, GetLessonButton, MyLessonsButton, ChangePasswordButton, Exit, error);
                    root.getChildren().addAll(list, SelectHBox, Submit, Back, error);
                }
                else {
                    error.setText("No lessons found:/");
                }
            }
        });

        MyLessonsButton.setOnAction(actionEvent -> {
            if(s.Lessons.size() == 0){
                list.setText("Lesson not found:/");
            }else{
                String temp = "";
                int counter=1;
                for(Lesson i:s.Lessons){
                    temp += counter + ". " + i.Title + '\n';
                    counter++;
                }
                list.setText(temp);
            }
        });
        Exit.setOnAction(action ->{
            Scenes.LoginScene(stage);
        });
        ChangePasswordButton.setOnAction(actionEvent -> {
            ChangePasswordScene(stage, s);
        });
        Back.setOnAction(actionEvent -> {
            MainScene(stage, s);
        });
        Submit.setOnAction(actionEvent -> {
            if(select.getText().chars().allMatch( Character::isDigit )){
                if(Integer.parseInt(select.getText()) <= Expert.Lessons.size()){
                    error.setText("");
                    s.getLesson(Integer.parseInt(select.getText()));
                    MainScene(stage, s);
                }else{
                    error.setText("Index Out of range.");
                }
            }else{
                error.setText("Invalid Input:/");
            }
        });
    }

    static void ExamScene(Stage stage, Student s){
        Button Check = new Button("Check");
        Button Exit = new Button("Exit");
        Button Next = new Button("Next");


        Exit.setCursor(Cursor.HAND);
        Check.setCursor(Cursor.HAND);
        Next.setCursor(Cursor.HAND);


        Exit.setTooltip(new Tooltip("Exit"));
        Check.setTooltip(new Tooltip("Check"));
        Next.setTooltip(new Tooltip("Next"));


        Label Question = new Label("");


        RadioButton radioButton1 = new RadioButton("");
        RadioButton radioButton2 = new RadioButton("");
        RadioButton radioButton3 = new RadioButton("");
        RadioButton radioButton4 = new RadioButton("");

        ToggleGroup radioGroup = new ToggleGroup();

        radioButton1.setToggleGroup(radioGroup);
        radioButton2.setToggleGroup(radioGroup);
        radioButton3.setToggleGroup(radioGroup);
        radioButton4.setToggleGroup(radioGroup);

        HBox OptionsHBox = new HBox(radioButton1, radioButton2, radioButton3, radioButton4);
        OptionsHBox.setAlignment(Pos.CENTER);
        OptionsHBox.setSpacing(15);


        TextArea descriptive = new TextArea();
        HBox descriptiveHBox = new HBox(descriptive);
        descriptiveHBox.setAlignment(Pos.CENTER);


        RadioButton truee = new RadioButton("True");
        RadioButton falsee = new RadioButton("False");

        ToggleGroup TFradioGroup = new ToggleGroup();

        truee.setToggleGroup(TFradioGroup);
        falsee.setToggleGroup(TFradioGroup);

        HBox TFHBox = new HBox(truee, falsee);
        TFHBox.setAlignment(Pos.CENTER);
        TFHBox.setSpacing(15);

        Label Blank = new Label("Enter Answer: ");
        TextField blank = new TextField();
        HBox BlankHBox = new HBox(Blank, blank);
        BlankHBox.setAlignment(Pos.CENTER);
        BlankHBox.setSpacing(15);


        Label QNum = new Label("0");
        Label Welcome = new Label("Welcome to Exam.");
        Label Score = new Label("");

        VBox root = new VBox();
        root.getChildren().addAll(Welcome, Next, Exit);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        Scene StudentScene = new Scene(root, 400, 700);
        stage.setScene(StudentScene);

        Exit.setOnAction(actionEvent -> {
            MainScene(stage, s);
        });
        Next.setOnAction(actionEvent -> {
            if(Integer.parseInt(QNum.getText()) == s.currentExam.Exams.get(s.currentExam.Exams.size() - 1).Problems.size()){
                root.getChildren().clear();
                root.getChildren().addAll(Welcome, Exit);
                Welcome.setText("Thank you for attending exam.");
            }else{
                if(Objects.equals(QNum.getText(), "0"))
                    root.getChildren().removeAll(Welcome, Next, Exit);
                else
                    root.getChildren().clear();
                QNum.setText(String.valueOf(Integer.parseInt(QNum.getText())+1));
                Score.setText("");
                switch (s.currentExam.Exams.get(s.currentExam.Exams.size()-1).Problems.get(Integer.parseInt(QNum.getText())-1).Mode){
                    case 1:
                        root.getChildren().addAll(QNum, Question, BlankHBox, Score, Check, Next, Exit);
                        break;
                    case 2:
                        root.getChildren().addAll(QNum, Question, descriptiveHBox, Score, Check, Next, Exit);
                        break;
                    case 3:
                        root.getChildren().addAll(QNum, Question, OptionsHBox, Score, Check, Next, Exit);
                        radioButton1.setText(s.currentExam.Exams.get(s.currentExam.Exams.size()-1).Problems.get(Integer.parseInt(QNum.getText())-1).Option1);
                        radioButton2.setText(s.currentExam.Exams.get(s.currentExam.Exams.size()-1).Problems.get(Integer.parseInt(QNum.getText())-1).Option2);
                        radioButton3.setText(s.currentExam.Exams.get(s.currentExam.Exams.size()-1).Problems.get(Integer.parseInt(QNum.getText())-1).Option3);
                        radioButton4.setText(s.currentExam.Exams.get(s.currentExam.Exams.size()-1).Problems.get(Integer.parseInt(QNum.getText())-1).Option4);
                        break;
                    case 4:
                        root.getChildren().addAll(QNum, Question, TFHBox, Score, Check, Next, Exit);
                        break;
                }
                Question.setText(s.currentExam.Exams.get(s.currentExam.Exams.size()-1).Problems.get(Integer.parseInt(QNum.getText())-1).Title);
            }
        });
        Check.setOnAction(actionEvent -> {
            switch (s.currentExam.Exams.get(s.currentExam.Exams.size()-1).Problems.get(Integer.parseInt(QNum.getText())-1).Mode){
                case 1:
                    s.currentExam.Exams.get(s.currentExam.Exams.size()-1).StudentsAnswers.get(s.FullName).add(blank.getText());
                    if(Objects.equals(s.currentExam.Exams.get(s.currentExam.Exams.size() - 1).Problems.get(Integer.parseInt(QNum.getText()) - 1).Solution, blank.getText())){
                        Score.setText("Correct.\nPlease click Next.");
                        s.Score.get(s.currentExam.Exams.get(s.currentExam.Exams.size()-1).Title).add(1);
                    }else {
                        Score.setText("Wrong Answer.\nPlease click Next.");
                        s.Score.get(s.currentExam.Exams.get(s.currentExam.Exams.size()-1).Title).add(0);
                    }
                    break;
                case 2:
                    s.currentExam.Exams.get(s.currentExam.Exams.size()-1).StudentsAnswers.get(s.FullName).add(descriptive.getText());
                    if(Objects.equals(s.currentExam.Exams.get(s.currentExam.Exams.size() - 1).Problems.get(Integer.parseInt(QNum.getText()) - 1).Solution, descriptive.getText())){
                        Score.setText("Correct.\nPlease click Next.");
                        s.Score.get(s.currentExam.Exams.get(s.currentExam.Exams.size()-1).Title).add(1);
                    }else {
                        Score.setText("Wrong Answer.\nPlease click Next.");
                        s.Score.get(s.currentExam.Exams.get(s.currentExam.Exams.size()-1).Title).add(0);
                    }
                    break;
                case 3:
                    String temp = "";
                    if(radioButton1.isSelected())
                        temp = "1";
                    else if(radioButton2.isSelected())
                        temp = "2";
                    else if(radioButton3.isSelected())
                        temp = "3";
                    else if(radioButton4.isSelected())
                        temp = "4";
                    s.currentExam.Exams.get(s.currentExam.Exams.size()-1).StudentsAnswers.get(s.FullName).add(temp);
                    if(Objects.equals(s.currentExam.Exams.get(s.currentExam.Exams.size() - 1).Problems.get(Integer.parseInt(QNum.getText()) - 1).Solution, temp)){
                        Score.setText("Correct.\nPlease click Next.");
                        s.Score.get(s.currentExam.Exams.get(s.currentExam.Exams.size()-1).Title).add(1);
                    }else {
                        Score.setText("Wrong Answer.\nPlease click Next.");
                        s.Score.get(s.currentExam.Exams.get(s.currentExam.Exams.size()-1).Title).add(0);
                    }
                    break;
                case 4:
                    String tempp = "";
                    if(truee.isSelected())
                        tempp = "true";
                    else if(falsee.isSelected())
                        tempp = "false";
                    s.currentExam.Exams.get(s.currentExam.Exams.size()-1).StudentsAnswers.get(s.FullName).add(tempp);
                    if(Objects.equals(s.currentExam.Exams.get(s.currentExam.Exams.size() - 1).Problems.get(Integer.parseInt(QNum.getText()) - 1).Solution, tempp)){
                        Score.setText("Correct.\nPlease click Next.");
                        s.Score.get(s.currentExam.Exams.get(s.currentExam.Exams.size()-1).Title).add(1);
                    }else {
                        Score.setText("Wrong Answer.\nPlease click Next.");
                        s.Score.get(s.currentExam.Exams.get(s.currentExam.Exams.size()-1).Title).add(0);
                    }
                    break;
            }
        });
    }

    static void ChangePasswordScene(Stage stage, Student s){
        Label OldPass = new Label("Current Password:        ");
        TextField Oldpass = new TextField();
        HBox OldpassHBox = new HBox(OldPass, Oldpass);
        OldpassHBox.setAlignment(Pos.CENTER);
        OldpassHBox.setSpacing(15);

        Label NewPass = new Label("New Password:             ");
        TextField Newpass = new TextField();
        HBox NewpassHBox = new HBox(NewPass, Newpass);
        NewpassHBox.setAlignment(Pos.CENTER);
        NewpassHBox.setSpacing(15);

        Label Repeat = new Label("Repeat New Password:");
        TextField repeat = new TextField();
        HBox RepeatHBox = new HBox(Repeat, repeat);
        RepeatHBox.setAlignment(Pos.CENTER);
        RepeatHBox.setSpacing(15);

        Button Submit = new Button("Submit");
        Button Back = new Button("Back");
        Submit.setCursor(Cursor.HAND);
        Back.setCursor(Cursor.HAND);

        Label result = new Label("");

        VBox rootChangePass = new VBox();
        rootChangePass.getChildren().addAll(OldpassHBox, NewpassHBox, RepeatHBox, Submit, Back, result);

        rootChangePass.setAlignment(Pos.CENTER);
        rootChangePass.setSpacing(10);

        Scene ChangePassScene = new Scene(rootChangePass, 400, 700);
        stage.setScene(ChangePassScene);

        Submit.setOnAction(actionEvent -> {
            if(Objects.equals(Newpass.getText(), repeat.getText())) {
                int res;
                if (Objects.equals(Oldpass.getText(), Newpass.getText())) {
                    s.password = Newpass.getText();
                    res = 1;
                }
                else {
                    res = -1;
                }
                switch (res){
                    case -1:
                        result.setText("Current Password is wrong.");
                        break;
                    case 1:
                        result.setText("Password changed successfully.");
                        break;
                }
            }else {
                result.setText("New Password and Repeat New Password are not same:/");
            }
        });
        Back.setOnAction(actionEvent -> {
            MainScene(stage, s);
        });
    }
}

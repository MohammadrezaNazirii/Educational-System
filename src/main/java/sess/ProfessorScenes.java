package sess;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class ProfessorScenes {
    static void MainScene(Stage stage, Professor p){
        Button AddExamButton = new Button("Add Exam");
        Button SetLessonDetailsButton = new Button("Set Lesson Details");
        Button MyLessonsButton = new Button("My Lessons");
        Button ChangePasswordButton = new Button("Change Password");
        Button Submit = new Button("Submit");
        Button Exit = new Button("Exit");

        AddExamButton.setCursor(Cursor.HAND);
        SetLessonDetailsButton.setCursor(Cursor.HAND);
        MyLessonsButton.setCursor(Cursor.HAND);
        ChangePasswordButton.setCursor(Cursor.HAND);
        Exit.setCursor(Cursor.HAND);
        Submit.setCursor(Cursor.HAND);

        AddExamButton.setTooltip(new Tooltip("Add Exam"));
        SetLessonDetailsButton.setTooltip(new Tooltip("Set Lesson Details"));
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

        Label ExamTitle = new Label("Exam Title:  ");
        TextField examTitle = new TextField();
        HBox ExamTitleHBox = new HBox(ExamTitle, examTitle);
        ExamTitleHBox.setAlignment(Pos.CENTER);
        ExamTitleHBox.setSpacing(15);

        Label list = new Label("");

        VBox root = new VBox();
        root.getChildren().addAll(list, AddExamButton, SetLessonDetailsButton, MyLessonsButton, ChangePasswordButton, Exit);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        Label error = new Label("");
        root.getChildren().add(error);

        Scene ProfessorScene = new Scene(root, 400, 700);
        stage.setScene(ProfessorScene);

        Button SubmitSetDetails = new Button("Submit");
        SubmitSetDetails.setCursor(Cursor.HAND);

        SetLessonDetailsButton.setOnAction(actionEvent -> {
            if(p.Lessons.size() == 0){
                error.setText("No lessons found.");
            }else {
                root.getChildren().removeAll(list, AddExamButton, SetLessonDetailsButton, MyLessonsButton, ChangePasswordButton, Exit, error);
                root.getChildren().addAll(list, SelectHBox, error, SubmitSetDetails, Back);
                list.setText(p.getLessons());
            }
        });

        MyLessonsButton.setOnAction(actionEvent -> {
            if(p.Lessons.size() == 0){
                list.setText("Lesson not found:/");
            }else{
                list.setText(p.getLessons());
            }
        });
        Exit.setOnAction(action ->{
            Scenes.LoginScene(stage);
        });
        ChangePasswordButton.setOnAction(actionEvent -> {
            ChangePasswordScene(stage, p);
        });
        AddExamButton.setOnAction(actionEvent -> {
            root.getChildren().removeAll(list, AddExamButton, SetLessonDetailsButton, ChangePasswordButton, error, Exit);
            String temp = "";
            int counter=1;
            for(Lesson i:p.Lessons){
                temp += counter + ". " + i.Title + '\n';
                counter++;
            }
            list.setText(temp);
            root.getChildren().setAll(list, SelectHBox, ExamTitleHBox, Submit, Back, error);
        });
        Back.setOnAction(actionEvent -> {
            MainScene(stage, p);
        });
        Submit.setOnAction(actionEvent -> {
            if(select.getText().chars().allMatch( Character::isDigit )){
                if(Integer.parseInt(select.getText()) <= p.Lessons.size()){
                    error.setText("");
                    p.AddExam(Integer.parseInt(select.getText()));
                    p.LessonForDesignExam.Exams.get(p.LessonForDesignExam.Exams.size()-1).Title = examTitle.getText();
                    AddExamScene(stage, p);
                }else{
                    error.setText("Index Out of range.");
                }
            }else{
                error.setText("Invalid Input:/");
            }
        });
        SubmitSetDetails.setOnAction(actionEvent -> {
            if(select.getText().chars().allMatch( Character::isDigit )){
                if(Integer.parseInt(select.getText()) <= p.Lessons.size()){
                    error.setText("");
                    SetDetailsScene(stage, p, Integer.parseInt(select.getText()));
                }else{
                    error.setText("Index Out of range.");
                }
            }else{
                error.setText("Invalid Input:/");
            }
        });
    }

    static void SetDetailsScene(Stage stage, Professor p, int choose){
        Label Syllabus = new Label("Syllabus:        ");
        TextField syllabus = new TextField();
        HBox SyllabusHBox = new HBox(Syllabus, syllabus);
        SyllabusHBox.setAlignment(Pos.CENTER);
        SyllabusHBox.setSpacing(15);

        Label Reference = new Label("Reference:  ");
        TextField reference = new TextField();
        HBox ReferenceHBox = new HBox(Reference, reference);
        ReferenceHBox.setAlignment(Pos.CENTER);
        ReferenceHBox.setSpacing(15);

        Button Back = new Button("Back");
        Back.setCursor(Cursor.HAND);

        Button Submit = new Button("Submit");
        Submit.setCursor(Cursor.HAND);

        VBox root = new VBox();
        root.getChildren().addAll(SyllabusHBox, ReferenceHBox, Submit, Back);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        Label error = new Label("");
        root.getChildren().add(error);

        Scene DetailsScene = new Scene(root, 400, 700);
        stage.setScene(DetailsScene);

        Back.setOnAction(actionEvent -> {
            MainScene(stage, p);
        });
        Submit.setOnAction(actionEvent -> {
            p.Lessons.get(choose-1).Syllabus = syllabus.getText();
            p.Lessons.get(choose-1).Reference = reference.getText();
            MainScene(stage, p);
        });
    }

    static void AddExamScene(Stage stage, Professor p){
        Button ShowExamButton = new Button("Show Exam");
        Button AddQuestionButton = new Button("Add Question");
        Button Submit = new Button("Submit");
        Button Exit = new Button("Exit");

        ShowExamButton.setCursor(Cursor.HAND);
        AddQuestionButton.setCursor(Cursor.HAND);
        Exit.setCursor(Cursor.HAND);
        Submit.setCursor(Cursor.HAND);

        ShowExamButton.setTooltip(new Tooltip("Show Exam"));
        AddQuestionButton.setTooltip(new Tooltip("Add Question"));
        Exit.setTooltip(new Tooltip("Exit"));
        Submit.setTooltip(new Tooltip("Submit"));

        Button Back = new Button("Back");
        Back.setCursor(Cursor.HAND);

        RadioButton TestButton = new RadioButton("Test");
        RadioButton TFButton = new RadioButton("True/False");
        RadioButton BlankButton = new RadioButton("Blank");
        RadioButton DescriptiveButton = new RadioButton("Descriptive");


        Label Title = new Label("Question Title:           ");
        TextField title = new TextField();
        HBox TitleHBox = new HBox(Title, title);
        TitleHBox.setAlignment(Pos.CENTER);
        TitleHBox.setSpacing(15);

        Label Option1 = new Label("Option1:  ");
        TextField option1 = new TextField();
        HBox Option1HBox = new HBox(Option1, option1);
        Option1HBox.setAlignment(Pos.CENTER);
        Option1HBox.setSpacing(15);

        Label Option2 = new Label("Option2:  ");
        TextField option2 = new TextField();
        HBox Option2HBox = new HBox(Option2, option2);
        Option2HBox.setAlignment(Pos.CENTER);
        Option2HBox.setSpacing(15);

        Label Option3 = new Label("Option3:  ");
        TextField option3 = new TextField();
        HBox Option3HBox = new HBox(Option3, option3);
        Option3HBox.setAlignment(Pos.CENTER);
        Option3HBox.setSpacing(15);

        Label Option4 = new Label("Option4:  ");
        TextField option4 = new TextField();
        HBox Option4HBox = new HBox(Option4, option4);
        Option4HBox.setAlignment(Pos.CENTER);
        Option4HBox.setSpacing(15);

        Label Solution = new Label("Solution: ");
        TextField solution = new TextField();
        HBox SolutionHBox = new HBox(Solution, solution);
        SolutionHBox.setAlignment(Pos.CENTER);
        SolutionHBox.setSpacing(15);

        VBox rootAdd = new VBox();
        rootAdd.getChildren().addAll(ShowExamButton, AddQuestionButton, Exit);

        rootAdd.setAlignment(Pos.CENTER);
        rootAdd.setSpacing(10);

        Scene AddScene = new Scene(rootAdd, 400, 700);
        stage.setScene(AddScene);

        Label error = new Label("");
        rootAdd.getChildren().add(error);

        TestButton.setOnAction(action ->{
            error.setText("");
            title.setText("");
            option1.setText("");
            option2.setText("");
            option3.setText("");
            option4.setText("");
            solution.setText("");
            rootAdd.getChildren().removeAll(TestButton, TFButton, BlankButton, DescriptiveButton, Back);
            rootAdd.getChildren().addAll(TitleHBox, Option1HBox, Option2HBox, Option3HBox, Option4HBox, SolutionHBox, Submit, Back);
        });
        TFButton.setOnAction(action ->{
            error.setText("");
            title.setText("");
            option1.setText("");
            option2.setText("");
            option3.setText("");
            option4.setText("");
            solution.setText("");
            rootAdd.getChildren().removeAll(TestButton, TFButton, BlankButton, DescriptiveButton, Back);
            rootAdd.getChildren().addAll(TitleHBox, SolutionHBox, Submit, Back);
        });
        BlankButton.setOnAction(action ->{
            error.setText("");
            title.setText("");
            option1.setText("");
            option2.setText("");
            option3.setText("");
            option4.setText("");
            solution.setText("");
            rootAdd.getChildren().removeAll(TestButton, TFButton, BlankButton, DescriptiveButton, Back);
            rootAdd.getChildren().addAll(TitleHBox, Option1HBox, SolutionHBox, error, Submit, Back);
            error.setText("Question Title is the first part of question.\nand Option1 is second part.\nand blank in between these.");
        });
        DescriptiveButton.setOnAction(action ->{
            error.setText("");
            title.setText("");
            option1.setText("");
            option2.setText("");
            option3.setText("");
            option4.setText("");
            solution.setText("");
            rootAdd.getChildren().removeAll(TestButton, TFButton, BlankButton, DescriptiveButton, Back);
            rootAdd.getChildren().addAll(TitleHBox, SolutionHBox, Submit, Back);
        });
        Exit.setOnAction(actionEvent -> {
            error.setText("");
            MainScene(stage, p);
        });
        Back.setOnAction(actionEvent -> {
            error.setText("");
            AddExamScene(stage, p);
        });
        ShowExamButton.setOnAction(actionEvent -> {
            rootAdd.getChildren().removeAll(ShowExamButton, AddQuestionButton, Exit, error);
            rootAdd.getChildren().addAll(error, Back);
            if(p.LessonForDesignExam.Exams.size() == 0){
                error.setText("Exam not found.");
            }else if(Objects.equals(p.LessonForDesignExam.Exams.get(p.LessonForDesignExam.Exams.size() - 1).showExam(), "")){
                error.setText("Exam not initialized.");
            }else{
                error.setText(p.LessonForDesignExam.Exams.get(p.LessonForDesignExam.Exams.size()-1).showExam());

            }
        });
        AddQuestionButton.setOnAction(actionEvent -> {
            error.setText("");
            rootAdd.getChildren().removeAll(ShowExamButton, AddQuestionButton, Exit, error);
            rootAdd.getChildren().addAll(TestButton, TFButton, BlankButton, DescriptiveButton, Back);
        });
        Submit.setOnAction(actionEvent -> {
            if(!Objects.equals(option2.getText(), "")){
                p.AddQuestionToExam(title.getText(), option1.getText(), option2.getText(), option3.getText(), option4.getText(), solution.getText());
            }else if(!Objects.equals(option1.getText(), "") && Objects.equals(option2.getText(), "")){
                p.AddQuestionToExam(title.getText(), option1.getText(), solution.getText());
            }else if(Objects.equals(solution.getText(), "True") || Objects.equals(solution.getText(), "T") || Objects.equals(solution.getText(), "true") || Objects.equals(solution.getText(), "False") || Objects.equals(solution.getText(), "F") || Objects.equals(solution.getText(), "false")){
                p.AddQuestionToExam(title.getText(), true, solution.getText());
            }else{
                p.AddQuestionToExam(title.getText(), solution.getText());
            }
            AddExamScene(stage, p);
        });
    }

    static void ChangePasswordScene(Stage stage, Professor p){
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
                    p.password = Newpass.getText();
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
            MainScene(stage, p);
        });
    }
}

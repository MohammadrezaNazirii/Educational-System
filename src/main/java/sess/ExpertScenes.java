package sess;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class ExpertScenes {
    static void MainScene(Stage stage){
        Button AddButton = new Button("Add");
        Button ListAllButton = new Button("List All");
        Button ChangePasswordButton = new Button("Change Password");
        Button Exit = new Button("Exit");

        AddButton.setCursor(Cursor.HAND);
        ListAllButton.setCursor(Cursor.HAND);
        ChangePasswordButton.setCursor(Cursor.HAND);
        Exit.setCursor(Cursor.HAND);

        AddButton.setTooltip(new Tooltip("Add"));
        ListAllButton.setTooltip(new Tooltip("List All"));
        ChangePasswordButton.setTooltip(new Tooltip("Change Password"));
        Exit.setTooltip(new Tooltip("Exit"));

        VBox root = new VBox();
        root.getChildren().addAll(AddButton, ListAllButton, ChangePasswordButton, Exit);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        Scene AdminScene = new Scene(root, 400, 700);
        stage.setScene(AdminScene);

        Label list = new Label("");
        root.getChildren().add(list);
        Exit.setOnAction(action ->{
            Scenes.LoginScene(stage);
        });
        AddButton.setOnAction(action ->{
            AddScene(stage);
        });
        ListAllButton.setOnAction(action ->{
            list.setText(Expert.ListAll());
        });
        ChangePasswordButton.setOnAction(actionEvent -> {
            ChangePasswordScene(stage);
        });
    }

    static void ChangePasswordScene(Stage stage){
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
                int res = Expert.ChangePassword(Oldpass.getText(), Newpass.getText());
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
            MainScene(stage);
        });
    }

    static void AddScene(Stage stage){
        RadioButton AddProfessorButton = new RadioButton("Add Professor");
        RadioButton AddStudentButton = new RadioButton("Add Student");
        RadioButton AddSemesterButton = new RadioButton("Add Semester");
        RadioButton AddLessonButton = new RadioButton("Add Lesson");


        Label Title = new Label("Title:                 ");
        TextField title = new TextField();
        HBox TitleHBox = new HBox(Title, title);
        TitleHBox.setAlignment(Pos.CENTER);
        TitleHBox.setSpacing(15);

        Label Name = new Label("FullName:  ");
        TextField name = new TextField();
        HBox NameHBox = new HBox(Name, name);
        NameHBox.setAlignment(Pos.CENTER);
        NameHBox.setSpacing(15);

        Label Field = new Label("Field:          ");
        TextField field = new TextField();
        HBox FieldHBox = new HBox(Field, field);
        FieldHBox.setAlignment(Pos.CENTER);
        FieldHBox.setSpacing(15);

        Label Select = new Label("Professor Number:");
        TextField select = new TextField();
        HBox SelectHBox = new HBox(Select, select);
        SelectHBox.setAlignment(Pos.CENTER);
        SelectHBox.setSpacing(15);

        Label Units = new Label("Units:               ");
        TextField units = new TextField();
        HBox UnitsHBox = new HBox(Units, units);
        UnitsHBox.setAlignment(Pos.CENTER);
        UnitsHBox.setSpacing(15);

        Label Username = new Label("Username: ");
        TextField username = new TextField();
        HBox UsernameHBox = new HBox(Username, username);
        UsernameHBox.setAlignment(Pos.CENTER);
        UsernameHBox.setSpacing(15);

        Label Password = new Label("Password:  ");
        TextField password = new TextField();
        HBox PasswordHBox = new HBox(Password, password);
        PasswordHBox.setAlignment(Pos.CENTER);
        PasswordHBox.setSpacing(15);

        Label beginDate = new Label("Begin Date:             ");
        DatePicker begindate = new DatePicker();
        HBox beginDateHBox = new HBox(beginDate, begindate);
        beginDateHBox.setAlignment(Pos.CENTER);
        beginDateHBox.setSpacing(15);


        Label endDate = new Label("End Date:                ");
        DatePicker enddate = new DatePicker();
        HBox endDateHBox = new HBox(endDate, enddate);
        endDateHBox.setAlignment(Pos.CENTER);
        endDateHBox.setSpacing(15);


        Label recordDate = new Label("RecordScores Date:");
        DatePicker recorddate = new DatePicker();
        HBox recordDateHBox = new HBox(recordDate, recorddate);
        recordDateHBox.setAlignment(Pos.CENTER);
        recordDateHBox.setSpacing(15);

        Label finalDate = new Label("Final Date:               ");
        DatePicker finaldate = new DatePicker();
        HBox finalDateHBox = new HBox(finalDate, finaldate);
        finalDateHBox.setAlignment(Pos.CENTER);
        finalDateHBox.setSpacing(15);

        Label midDate = new Label("Midterm Date:         ");
        DatePicker middate = new DatePicker();
        HBox midDateHBox = new HBox(midDate, middate);
        midDateHBox.setAlignment(Pos.CENTER);
        midDateHBox.setSpacing(15);

        Button Submit = new Button("Submit");
        Button Back = new Button("Back");
        Submit.setCursor(Cursor.HAND);
        Back.setCursor(Cursor.HAND);


        Label showProfessors = new Label("");

        VBox rootAdd = new VBox();
        rootAdd.getChildren().addAll(showProfessors, AddProfessorButton, AddStudentButton, AddSemesterButton, AddLessonButton);

        rootAdd.setAlignment(Pos.CENTER);
        rootAdd.setSpacing(10);

        Scene AddScene = new Scene(rootAdd, 400, 700);
        stage.setScene(AddScene);

        Label error = new Label("");
        rootAdd.getChildren().add(error);



        AddProfessorButton.setOnAction(action ->{
            error.setText("");
            rootAdd.getChildren().removeAll(AddProfessorButton, AddStudentButton, AddSemesterButton, AddLessonButton);
            rootAdd.getChildren().addAll(NameHBox, FieldHBox, UsernameHBox, PasswordHBox, Submit, Back);
        });
        AddStudentButton.setOnAction(action ->{
            error.setText("");
            rootAdd.getChildren().removeAll(AddProfessorButton, AddStudentButton, AddSemesterButton, AddLessonButton);
            rootAdd.getChildren().addAll(NameHBox, UsernameHBox, PasswordHBox, Submit, Back);
        });
        AddSemesterButton.setOnAction(action ->{
            error.setText("");
            rootAdd.getChildren().removeAll(AddProfessorButton, AddStudentButton, AddSemesterButton, AddLessonButton);
            rootAdd.getChildren().addAll(TitleHBox, beginDateHBox, recordDateHBox, endDateHBox, Submit, Back);
        });
        AddLessonButton.setOnAction(action ->{
            if(!Objects.equals(Expert.showProfessors(), "") && Expert.Semesters.size() != 0) {
                error.setText("");
                showProfessors.setText(Expert.showProfessors());
                rootAdd.getChildren().removeAll(AddProfessorButton, AddStudentButton, AddSemesterButton, AddLessonButton);
                rootAdd.getChildren().addAll(TitleHBox, midDateHBox, finalDateHBox, UnitsHBox, SelectHBox, Submit, Back);
            }else if(Expert.Professors.size() == 0)
                error.setText("No Professor.");
            else if(Expert.Semesters.size() == 0)
                error.setText("No Semester.");
        });
        Back.setOnAction(actionEvent -> {
            error.setText("");
            MainScene(stage);
        });
        Submit.setOnAction(actionEvent -> {
            error.setText("");
            if(!Objects.equals(field.getText(), "")){
                Expert.AddProfessor(name.getText(), field.getText(), 'P'+username.getText(), password.getText());
                field.setText("");
                name.setText("");
                MainScene(stage);
            }
            else if(!Objects.equals(name.getText(), "") && Objects.equals(field.getText(), "")){
                Expert.AddStudent(name.getText(), 'S'+username.getText(), password.getText());
                name.setText("");
                MainScene(stage);
            }
            else if(Objects.equals(recorddate.getEditor().getText(), "")){
                if(select.getText().chars().allMatch( Character::isDigit ) && units.getText().chars().allMatch( Character::isDigit )){
                    Expert.AddLesson(title.getText(), middate.getValue(), finaldate.getValue(), Integer.parseInt(select.getText())-1, Integer.parseInt(units.getText()));
                    MainScene(stage);
                }else{
                    error.setText("Invalid Input:/");
                }
            }else{
                Expert.AddSemester(title.getText(), recorddate.getValue(), begindate.getValue(), enddate.getValue());
                recorddate.getEditor().setText("");
                MainScene(stage);
            }

        });
    }
}

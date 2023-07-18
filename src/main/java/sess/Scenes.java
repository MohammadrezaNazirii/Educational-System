package sess;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class Scenes {
    static void LoginScene(Stage stage){
        Button login = new Button("Login");
        Button exit = new Button("Exit");
        Label username = new Label("Username:");
        Label pass = new Label("Password:");
        TextField usernameTextField = new TextField();
        PasswordField passwordField = new PasswordField();

        HBox usernameBox = new HBox();
        usernameBox.getChildren().addAll(username, usernameTextField);
        usernameBox.setAlignment(Pos.CENTER);
        usernameBox.setSpacing(15);
        HBox passwordBox = new HBox();
        passwordBox.getChildren().addAll(pass, passwordField);
        passwordBox.setAlignment(Pos.CENTER);
        passwordBox.setSpacing(17);

        usernameBox.setCursor(Cursor.TEXT);
        passwordBox.setCursor(Cursor.TEXT);
        login.setCursor(Cursor.HAND);
        exit.setCursor(Cursor.HAND);

        usernameTextField.setTooltip(new Tooltip("Enter Username"));
        passwordField.setTooltip(new Tooltip("Enter Password"));
        login.setTooltip(new Tooltip("Click"));

        Label help = new Label("\nHelp:");
        Label helpProfessor = new Label("Professor's username starts with P.");
        Label helpStudent = new Label("Student's username starts with S.");

        VBox root = new VBox();
        root.getChildren().addAll(usernameBox, passwordBox, login, exit, help, helpProfessor, helpStudent);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        Scene LoginScene = new Scene(root, 400, 700);
        stage.setScene(LoginScene);

        Label error = new Label("");
        root.getChildren().add(error);
        exit.setOnAction(actionEvent -> {
            FileHandelling.WriteToFile();
            System.exit(0);
        });

        login.setOnAction(action ->{
            if ((Objects.equals(usernameTextField.getText(), Expert.username)) && (Objects.equals(passwordField.getText(), Expert.password))){
                ExpertScenes.MainScene(stage);
            }else{
                error.setText("Username and Password not match!");
            }
            String Temp = usernameTextField.getText();
            switch (Temp.charAt(0)){
                case 'P':
                    for(Professor p:Expert.Professors){
                        if(Objects.equals(usernameTextField.getText(), p.username) && Objects.equals(passwordField.getText(), p.password)){
                            usernameTextField.setText("");
                            passwordField.setText("");
                            ProfessorScenes.MainScene(stage, p);
                            break;
                        }
                    }
                case 'S':
                    for(Student p:Expert.Students){
                        if(Objects.equals(usernameTextField.getText(), p.username) && Objects.equals(passwordField.getText(), p.password)){
                            usernameTextField.setText("");
                            passwordField.setText("");
                            StudentScenes.MainScene(stage, p);
                            break;
                        }
                    }
                default:
                    error.setText("Username and Password not match!");
                    break;
            }
        });
    }
}

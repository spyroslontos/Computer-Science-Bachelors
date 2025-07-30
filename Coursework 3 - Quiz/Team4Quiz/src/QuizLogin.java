import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class QuizLogin {

    Stage window;
    BorderPane sections;
    GridPane centerPane;
    PasswordField passwordField;
    SettingsMenu newBuild;

    public void start() {

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Admin Login");
        window.setResizable(false);
        window.getIcons().add(new Image("quizico.png"));

        sections = new BorderPane();

        centerPane = new GridPane();
        centerPane.setHgap(10);
        centerPane.setVgap(10);

        Label password = new Label("Admin Password");
        GridPane.setConstraints(password, 0,0);

        passwordField = new PasswordField();
        GridPane.setConstraints(passwordField, 0,1);
        passwordField.setPrefSize(240,25);
        passwordField.setAlignment(Pos.CENTER);

        Button login = new Button("Login");
        GridPane.setConstraints(login, 0,3);


        // Password Checker

        login.setOnAction(event -> {
            if (passwordField.getText().equals("admin")) { //Change this bracket to change the Password
                newBuild = new SettingsMenu();
                newBuild.start();
                window.close();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Admin Login");
                alert.setHeaderText("Wrong Password");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        System.out.println("Password alert Shown");
                    }
                });
                passwordField.clear();
            }
        });

        Button back = new Button("Back");
        GridPane.setConstraints(back, 0,4);

        back.setOnAction(event -> {
            window.close();
        });

        //Create menu
        MenuBar menuBar = new MenuBar();

        //Set the menu bar to appear at the top in the border pane layout
        sections.setTop(menuBar);
        sections.setCenter(centerPane); //and the centerPane in the center

        centerPane.getChildren().addAll(password, passwordField, login, back);
        centerPane.setPadding(new Insets(10));

        Scene scene = new Scene(sections, 250, 150);
        window.setScene(scene);
        window.show();
    }
}
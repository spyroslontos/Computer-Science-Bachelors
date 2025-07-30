import javafx.application.*;
import javafx.scene.image.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class MainMenu extends Application {

    Stage window;
    QuizLogin newLogin;
    QuizEngine newGame;

    public static void main(String[] args) {
        // Ignition which launches the start method below
        launch(args);
    }

    public void start(Stage primaryStage) {

        //Main Code
        window = primaryStage;
        window = new Stage();
        window.setTitle("Quiz Launcher");
        window.setWidth(300);
        window.setHeight(250);
        window.setResizable(false);
        window.getIcons().add(new Image("quizico.png"));

        //Create two buttons
        Button playButton = new Button("Start");

        Button settingsButton = new Button("Settings \uD83D\uDD12");

        //Play button
        playButton.setOnAction(event -> {
            newGame = new QuizEngine();
            newGame.start();
        });

        //Clicking will got to Login Screen
        settingsButton.setOnAction(e -> {
            newLogin = new QuizLogin();
            newLogin.start();
        });

        VBox layout = new VBox(15);

        layout.setPadding(new Insets(70, 90, 40, 90));

        //Add buttons
        layout.getChildren().addAll(playButton, settingsButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
}
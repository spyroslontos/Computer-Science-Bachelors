import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AddQuestion {

    Stage window;
    BorderPane sections;
    GridPane centerPane;
    TextField questionField;
    TextField choices1Field;
    TextField choices2Field;
    TextField choices3Field;
    TextField choices4Field;
    TextField answerField;
    TextField topicField;
    List<QuestionCreator> listQuestions = new ArrayList<>();
    String COMMA_DELIMITER = ",";
    String NEW_LINE_SEPARATOR = "\n";
    SettingsMenu newBuild;

    public void start() {

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Adding Quiz Questions");
        window.setHeight(540);
        window.setWidth(600);
        window.setResizable(false);
        window.getIcons().add(new Image("quizico.png"));

        sections = new BorderPane();

        centerPane = new GridPane();
        centerPane.setHgap(10);
        centerPane.setVgap(10);

        Label question = new Label("Question: ");
        GridPane.setConstraints(question, 0,0);

        questionField = new TextField();
        GridPane.setConstraints(questionField, 0,1);
        questionField.setPrefSize(600,30);
        questionField.setAlignment(Pos.CENTER);

        Label choices = new Label("Choices:");
        GridPane.setConstraints(choices, 0,2);

        choices1Field = new TextField();
        GridPane.setConstraints(choices1Field, 0,3);
        choices1Field.setPrefSize(600,30);
        choices1Field.setAlignment(Pos.CENTER);

        choices2Field = new TextField();
        GridPane.setConstraints(choices2Field, 0,4);
        choices2Field.setPrefSize(600,30);
        choices2Field.setAlignment(Pos.CENTER);

        choices3Field = new TextField();
        GridPane.setConstraints(choices3Field, 0,5);
        choices3Field.setPrefSize(600,30);
        choices3Field.setAlignment(Pos.CENTER);

        choices4Field = new TextField();
        GridPane.setConstraints(choices4Field, 0,6);
        choices4Field.setPrefSize(600,30);
        choices4Field.setAlignment(Pos.CENTER);

        Label answer = new Label("Answer:");
        GridPane.setConstraints(answer, 0,7);

        answerField = new TextField();
        GridPane.setConstraints(answerField, 0,8);
        answerField.setPrefSize(600,30);
        answerField.setAlignment(Pos.CENTER);

        Label topic = new Label("Topic:");
        GridPane.setConstraints(topic, 0,9);

        topicField = new TextField();
        GridPane.setConstraints(topicField, 0,10);
        topicField.setPrefSize(600,30);
        topicField.setAlignment(Pos.CENTER);

        Button nextQue = new Button("Next Questions");
        GridPane.setConstraints(nextQue, 0,11);

        Button saveAll = new Button("Save");
        GridPane.setConstraints(saveAll, 0,12);

        saveAll.setOnAction(e -> save());

        Button exit = new Button("Exit");
        GridPane.setConstraints(exit, 0,13);

        exit.setOnAction(event -> {
            listQuestions.clear();
            newBuild = new SettingsMenu();
            newBuild.start();
            window.close();
        });

        //Next question method to add new question in file
        nextQue.setOnAction(e -> NextQuestion());

        centerPane.getChildren().addAll(question, questionField, choices, choices1Field, choices2Field, choices3Field, choices4Field, answer , answerField, topic, topicField, nextQue, saveAll, exit);
        centerPane.setPadding(new Insets(10));

//   -------------------------------------------------------------------- //

        sections.setCenter(centerPane); //and the centerPane in the center

        Scene scene = new Scene(sections, 650, 350);
        window.setScene(scene);
        window.show();
    }

//   -------------------------------------------------------------------- //

    public void NextQuestion(){

        //Check if fields either fields are empty
        if(questionField.getText().trim().isEmpty() || choices1Field.getText().trim().isEmpty() ||
                choices2Field.getText().trim().isEmpty() || choices3Field.getText().trim().isEmpty() ||
                choices4Field.getText().trim().isEmpty() || answerField.getText().trim().isEmpty() || topicField.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Adding Questions");
            alert.setHeaderText("There are empty fields");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Empty fields alert Shown");
                }
            });}

            else {
            //Add the quizCard object to the arrayList quizCards
            String question = questionField.getText();
            String choice1 = choices1Field.getText();
            String choice2 = choices2Field.getText();
            String choice3 = choices3Field.getText();
            String choice4 = choices4Field.getText();
            String answer = answerField.getText();
            String topic = topicField.getText();

            listQuestions.add(new QuestionCreator(question, choice1, choice2, choice3, choice4, answer, topic));
            System.out.println("Added Question to arrayList");

            //Clear all fields
            questionField.clear();
            choices1Field.clear();
            choices2Field.clear();
            choices3Field.clear();
            choices4Field.clear();
            answerField.clear();
            topicField.clear();
            }
    }

    public void save() {

        //Before saving check if the array is empty
        if (listQuestions.isEmpty()) {
            System.out.println("Empty");
        } else {
            try {
                FileWriter addingQuestions = new FileWriter("listQuestions.csv", true);

                //  For loop for all the questions
                for (QuestionCreator p : listQuestions) {
                    addingQuestions.append(p.getQuestion());
                    addingQuestions.append(COMMA_DELIMITER);
                    addingQuestions.append(p.getChoice1());
                    addingQuestions.append(COMMA_DELIMITER);
                    addingQuestions.append(p.getChoice2());
                    addingQuestions.append(COMMA_DELIMITER);
                    addingQuestions.append(p.getChoice3());
                    addingQuestions.append(COMMA_DELIMITER);
                    addingQuestions.append(p.getChoice4());
                    addingQuestions.append(COMMA_DELIMITER);
                    addingQuestions.append(p.getAnswer());
                    addingQuestions.append(COMMA_DELIMITER);
                    addingQuestions.append(p.getTopic());
                    addingQuestions.append(NEW_LINE_SEPARATOR);
                }
                addingQuestions.flush();
                addingQuestions.close();
                listQuestions.clear();
                System.out.println("Done");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
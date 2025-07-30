import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class DeleteQuestion {

    Stage window;
    TableView<QuestionCreator> table;
    String COMMA_DELIMITER = ",";
    String NEW_LINE_SEPARATOR = "\n";
    SettingsMenu newBuild;

    ObservableList<QuestionCreator> questionSelected, allQuestions;

    public void start() {

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Deleting Quiz Questions");
        window.setHeight(450);
        window.setWidth(1150);
        window.setResizable(false);
        window.getIcons().add(new Image("quizico.png"));

        // Question Column
        TableColumn<QuestionCreator, String> questionColumn = new TableColumn<>("Question");
        questionColumn.setMinWidth(300);
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("Question"));

        // Choice1 Column
        TableColumn<QuestionCreator, String> choice1column = new TableColumn<>("Choice1");
        choice1column.setMinWidth(140);
        choice1column.setCellValueFactory(new PropertyValueFactory<>("choice1"));

        // Choice2 Column
        TableColumn<QuestionCreator, String> choice2column = new TableColumn<>("Choice2");
        choice2column.setMinWidth(140);
        choice2column.setCellValueFactory(new PropertyValueFactory<>("choice2"));

        // Choice3 Column
        TableColumn<QuestionCreator, String> choice3column = new TableColumn<>("Choice3");
        choice3column.setMinWidth(140);
        choice3column.setCellValueFactory(new PropertyValueFactory<>("choice3"));

        // Choice4 Column
        TableColumn<QuestionCreator, String> choice4column = new TableColumn<>("Choice4");
        choice4column.setMinWidth(140);
        choice4column.setCellValueFactory(new PropertyValueFactory<>("choice4"));

        // Answer Column
        TableColumn<QuestionCreator, String> answerColumn = new TableColumn<>("Answer");
        answerColumn.setMinWidth(140);
        answerColumn.setCellValueFactory(new PropertyValueFactory<>("answer"));

        // Topic Column
        TableColumn<QuestionCreator, String> topicColumn = new TableColumn<>("Topic");
        topicColumn.setMinWidth(143);
        topicColumn.setCellValueFactory(new PropertyValueFactory<>("topic"));

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveButtonClicked());

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> backButtonClicked());

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(deleteButton, saveButton, backButton);

        table = new TableView<>();
        table.setItems(getQuestionCreator());
        table.getColumns().addAll(questionColumn, choice1column, choice2column, choice3column, choice4column, answerColumn, topicColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, hBox);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    // Save button clicked
    public void saveButtonClicked() {
        try {
            FileWriter fileWriter = new FileWriter("listQuestions.csv");

            //  For loop for all the questions
            for(QuestionCreator p : allQuestions) {
                fileWriter.append(p.getQuestion());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(p.getChoice1());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(p.getChoice2());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(p.getChoice3());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(p.getChoice4());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(p.getAnswer());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(p.getTopic());
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Saved");
        }   catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Delete button clicked
    public void deleteButtonClicked() {
//        ObservableList<QuestionCreator> questionSelected, allQuestions;
        allQuestions = table.getItems();
        questionSelected = table.getSelectionModel().getSelectedItems();

        questionSelected.forEach(allQuestions::remove);
    }

    // Back button clicked
    public void backButtonClicked() {
        newBuild = new SettingsMenu();
        newBuild.start();
        window.close();
    }

    // Get all of the products
    public ObservableList<QuestionCreator> getQuestionCreator() {
        ObservableList<QuestionCreator> questionsList = FXCollections.observableArrayList();

        String fileName = "listQuestions.csv";
        File file = new File(fileName);
        try {
            Scanner inputStreams = new Scanner(file);
            while (inputStreams.hasNext()) {
                String line = inputStreams.nextLine();
//                System.out.println(line);
                String[] splitted = line.split(",");
                questionsList.add(new QuestionCreator(splitted[0],splitted[1],splitted[2],splitted[3],splitted[4],splitted[5],splitted[6]));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return questionsList;
    }
}


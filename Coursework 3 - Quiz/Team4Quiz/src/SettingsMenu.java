import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SettingsMenu {

    Stage window;
    BorderPane sections;
    GridPane centerPane;
    TextField yearGroupField;
    TextField schoolField;
    ComboBox comboBox;
    AddQuestion addBuild;
    AmendQuestion amendBuild;
    DeleteQuestion deleteBuild;

    List<PreferencesCreator> listPrefs = new ArrayList<>();
    ObservableList<String> options = FXCollections.observableArrayList();
    ObservableList<String> filteredOptions = FXCollections.observableArrayList();

    String NEW_LINE_SEPARATOR = "\n";

    int AllScores;
    int numScores;

    public void start() {

        getTopics();

        getAvg();

        for(int i=0; i < options.size(); i++) {
            if (!filteredOptions.contains(options.get(i))) {
                filteredOptions.add(options.get(i));
            }
        }


        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Settings Menu");
        window.setHeight(550);
        window.setWidth(500);
        window.setResizable(false);
        window.getIcons().add(new Image("quizico.png"));

        sections = new BorderPane();
        centerPane = new GridPane();
        centerPane.setHgap(10);
        centerPane.setVgap(10);

        Label yearGroup = new Label("Set Year Group");
        GridPane.setConstraints(yearGroup, 0,0);

        yearGroupField = new TextField();
        GridPane.setConstraints(yearGroupField, 1,0);
        yearGroupField.setPrefSize(240,25);
        yearGroupField.setAlignment(Pos.CENTER);

        Label school = new Label("Set Schools Attending");
        GridPane.setConstraints(school, 0,1);

        schoolField = new TextField();
        GridPane.setConstraints(schoolField,1,1);
        schoolField.setPrefSize(240,25);
        schoolField.setAlignment(Pos.CENTER);

        Label topic = new Label("Set Topic");
        GridPane.setConstraints(topic, 0,2);

        comboBox = new ComboBox(filteredOptions);
        GridPane.setConstraints(comboBox,1,2);
        comboBox.setValue(filteredOptions.get(0));

        Button setPref = new Button("Set Preferences");
        GridPane.setConstraints(setPref,1,3);

        setPref.setOnAction(new setPrefListener());

        Button getAvg = new Button("Get Average");
        GridPane.setConstraints(getAvg,1,4);

        getAvg.setOnAction(e -> getAvgClicked());

        Button addQ = new Button("Add Question");

        Button amendQ = new Button("Amend Question");

        Button deleteQ = new Button("Delete Question");

        addQ.setOnAction(event -> {
            addBuild = new AddQuestion();
            addBuild.start();
            window.close();
        });

        amendQ.setOnAction(event -> {
            amendBuild = new AmendQuestion();
            amendBuild.start();
            window.close();
        });

        deleteQ.setOnAction(event -> {
            deleteBuild = new DeleteQuestion();
            deleteBuild.start();
            window.close();
        });

        Button back = new Button("Back");

        back.setOnAction(event -> {
            window.close();
        });

        //Create menu
        MenuBar menuBar = new MenuBar();

        //Set the menu bar to appear at the top in the border pane layout
        sections.setTop(menuBar);
        sections.setCenter(centerPane); //and the centerPane in the center

        VBox layout = new VBox(15);

        layout.setPadding(new Insets(30, 70, 30, 70));

        //Add buttons
        layout.getChildren().addAll(yearGroup, yearGroupField, school, schoolField, topic, comboBox, setPref, getAvg, addQ, amendQ, deleteQ, back);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }


    public ObservableList<String> getTopics() {

        String fileName = "listQuestions.csv";
        File file = new File(fileName);
        try {
            Scanner inputStreams = new Scanner(file);
            while (inputStreams.hasNext()) {
                String line = inputStreams.nextLine();

                String[] splitted = line.split(",");
                options.add(splitted[6]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return options;
    }

    private class setPrefListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent f) {
            //Check if fields either fields are empty
            if(yearGroupField.getText().trim().isEmpty() || schoolField.getText().trim().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Setting Preferences");
                alert.setHeaderText("There are empty fields");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        System.out.println("Empty fields alert Shown");
                    }
                });
            }else{
                //Get the text from the text fields and create a new quizCard object by passing the field's data
                //Add the quizCard object to the arrayList quizCards
                String year = yearGroupField.getText();
                String school = schoolField.getText();
                String topic = comboBox.getValue().toString();

                listPrefs.add(new PreferencesCreator(year,school,topic));
                //Clear both fields
                yearGroupField.clear();
                schoolField.clear();

                try {
                    FileWriter addPref = new FileWriter("listPrefs.csv");

                    //  For loop for all the questions
                    for(PreferencesCreator p : listPrefs) {
                        addPref.append(p.getYear());
                        addPref.append(NEW_LINE_SEPARATOR);
                        addPref.append(p.getSchool());
                        addPref.append(NEW_LINE_SEPARATOR);
                        addPref.append(p.getTopic());
                        addPref.append(NEW_LINE_SEPARATOR);
                    }
                    addPref.flush();
                    addPref.close();
                    System.out.println("Preferences Added");
                }   catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void getAvg(){
        String fileName = "listPrefs.csv";
        File file = new File(fileName);
        try {
            Scanner inputStreams = new Scanner(file);
            inputStreams.nextLine();
            inputStreams.nextLine();
            inputStreams.nextLine();
            while (inputStreams.hasNext()) {
                AllScores += Integer.parseInt(inputStreams.nextLine());
                numScores++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getAvgClicked() {
        DecimalFormat df = new DecimalFormat("##.##");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Quiz Average");
        alert.setHeaderText("Average of Scores of Complete quizes");
        alert.setContentText("Average is: "+  df.format((double) AllScores/numScores) );
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Average Shown");
            }
        });
    }
}


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

// Spyros Lontos
// c1722325

public class StudentRecords extends Application {

    // Initializes variables used

    Stage window;
    TableView<RecordCreator> table;
    String COMMA = ",";
    String NEW_LINE = "\n";

    TextField NameField;
    TextField StudentNumField;
    TextField CourseNameField;
    TextField CourseIDField;
    TextField HouseNumField;
    TextField StreetNameField;
    TextField TownField;
    TextField PostcodeField;
    TextField SearchField;

    ObservableList<RecordCreator> recordsList = FXCollections.observableArrayList();
    ObservableList<RecordCreator> recordSelected;
    List<RecordCreator> addListRecord = new ArrayList<>();
    FilteredList<RecordCreator> filteredRecordList = new FilteredList<>(recordsList, e->true);

    // This is used to launch the start method to create the JavaFX window
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Student Records");
        window.setHeight(450);
        window.setWidth(1140);
        window.setResizable(false);

        // Name Column
        TableColumn<RecordCreator, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(140);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        // Student Number Column
        TableColumn<RecordCreator, String> studentnumColumn = new TableColumn<>("Student Number");
        studentnumColumn.setMinWidth(140);
        studentnumColumn.setCellValueFactory(new PropertyValueFactory<>("StudentNum"));

        // Course Name Column
        TableColumn<RecordCreator, String> coursenameColumn = new TableColumn<>("Course Name");
        coursenameColumn.setMinWidth(140);
        coursenameColumn.setCellValueFactory(new PropertyValueFactory<>("CourseName"));

        // Course ID Column
        TableColumn<RecordCreator, String> courseIDColumn = new TableColumn<>("Course ID");
        courseIDColumn.setMinWidth(140);
        courseIDColumn.setCellValueFactory(new PropertyValueFactory<>("CourseID"));

        // House Number Column
        TableColumn<RecordCreator, String> HouseNumColumn = new TableColumn<>("House Number");
        HouseNumColumn.setMinWidth(140);
        HouseNumColumn.setCellValueFactory(new PropertyValueFactory<>("HouseNum"));

        // Street Name Column
        TableColumn<RecordCreator, String> StreetNameColumn = new TableColumn<>("Street Name");
        StreetNameColumn.setMinWidth(140);
        StreetNameColumn.setCellValueFactory(new PropertyValueFactory<>("StreetName"));

        // Town Column
        TableColumn<RecordCreator, String> TownColumn = new TableColumn<>("Town");
        TownColumn.setMinWidth(140);
        TownColumn.setCellValueFactory(new PropertyValueFactory<>("Town"));

        // Postcode Column
        TableColumn<RecordCreator, String> PostcodeColumn = new TableColumn<>("Postcode");
        PostcodeColumn.setMinWidth(140);
        PostcodeColumn.setCellValueFactory(new PropertyValueFactory<>("Postcode"));

        // Fields for adding records
        NameField = new TextField();
        NameField.setPromptText("John");
        NameField.setPrefWidth(102);

        StudentNumField = new TextField();
        StudentNumField.setPromptText("C123456");
        StudentNumField.setPrefWidth(102);

        CourseNameField = new TextField();
        CourseNameField.setPromptText("Maths");
        CourseNameField.setPrefWidth(102);

        CourseIDField = new TextField();
        CourseIDField.setPromptText("CM1102");
        CourseIDField.setPrefWidth(102);

        HouseNumField = new TextField();
        HouseNumField.setPromptText("16");
        HouseNumField.setPrefWidth(102);

        StreetNameField = new TextField();
        StreetNameField.setPromptText("Cardinal Avenue");
        StreetNameField.setPrefWidth(102);

        TownField = new TextField();
        TownField.setPromptText("Bristol");
        TownField.setPrefWidth(102);

        PostcodeField = new TextField();
        PostcodeField.setPromptText("BM0PQ");
        PostcodeField.setPrefWidth(102);

        // Buttons with different methods on click
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addButtonClicked());

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveButtonClicked());

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> quitButtonClicked());

        // Creates a horizontal box where all the adding record fields and buttons will be placed
        HBox settingsBox = new HBox();
        settingsBox.setPadding(new Insets(10,10,10,10));
        settingsBox.setSpacing(10);
        settingsBox.getChildren().addAll(NameField, StudentNumField, CourseNameField, CourseIDField, HouseNumField, StreetNameField, TownField, PostcodeField, addButton, deleteButton, saveButton, quitButton);
        settingsBox.setAlignment(Pos.CENTER_RIGHT);

        // A table is created and its filled with the data in the Records.txt file
        table = new TableView<>();
        table.setItems(getRecordCreator());
        table.getColumns().addAll(nameColumn, studentnumColumn, coursenameColumn, courseIDColumn, HouseNumColumn, StreetNameColumn, TownColumn, PostcodeColumn);

        // Search Field
        SearchField = new TextField();
        SearchField.setPrefWidth(100);
        SearchField.setPromptText("Search Field");

        // When characters are entered in the search field
        // It checks if the substring is contained in any field in the Records
        SearchField.setOnKeyReleased(e ->{ SearchField.textProperty().addListener(((observed, prevData, newData) -> {
            filteredRecordList.setPredicate((Predicate<? super RecordCreator>) Students -> {
                    if (newData == null || newData.isEmpty()){ return true; }
                    String lowerSearch = newData.toLowerCase();

                    if (Students.getName().toLowerCase().contains(lowerSearch)){ return true; }
                    if (Students.getStudentNum().toLowerCase().contains(lowerSearch)){ return true; }
                    if (Students.getCourseName().toLowerCase().contains(lowerSearch)){ return true; }
                    if (Students.getCourseID().toLowerCase().contains(lowerSearch)){ return true; }
                    if (Students.getHouseNum().toLowerCase().contains(lowerSearch)){ return true; }
                    if (Students.getStreetName().toLowerCase().contains(lowerSearch)){ return true; }
                    if (Students.getTown().toLowerCase().contains(lowerSearch)){ return true; }
                    if (Students.getPostcode().toLowerCase().contains(lowerSearch)){ return true; }

                    return false;
                });
            }));

            SortedList<RecordCreator> sortedinfo = new SortedList<>(filteredRecordList);

            sortedinfo.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedinfo);
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, SearchField,settingsBox);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    // Save button clicked
    public void saveButtonClicked() {
        try {
            FileWriter fileSave = new FileWriter("src\\Records.txt");

            // For loop for all the Records
            // Overrides the whole file with the new updated Data
            for(RecordCreator p : recordsList) {
                fileSave.append(p.getName());
                fileSave.append(COMMA);
                fileSave.append(p.getStudentNum());
                fileSave.append(COMMA);
                fileSave.append(p.getCourseName());
                fileSave.append(COMMA);
                fileSave.append(p.getCourseID());
                fileSave.append(COMMA);
                fileSave.append(p.getHouseNum());
                fileSave.append(COMMA);
                fileSave.append(p.getStreetName());
                fileSave.append(COMMA);
                fileSave.append(p.getTown());
                fileSave.append(COMMA);
                fileSave.append(p.getPostcode());
                fileSave.append(NEW_LINE);
            }
            fileSave.flush();
            fileSave.close();
            System.out.println("Saved");
        }   catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Add button clicked
    public void addButtonClicked() {

        //Check if fields pass the regex expressions
        if(
                !NameField.getText().matches("[a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+") || NameField.getText().isEmpty() ||
                !StudentNumField.getText().matches("C\\d{6}") || StudentNumField.getText().isEmpty() ||
                !CourseNameField.getText().matches("[a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+") || CourseNameField.getText().isEmpty() ||
                !CourseIDField.getText().matches("[A-Z]{2}\\d{4}") || CourseIDField.getText().isEmpty() ||
                !HouseNumField.getText().matches("\\d+|\\d+[a-z]{1}") || HouseNumField.getText().isEmpty() ||
                !StreetNameField.getText().matches("[a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+") || StreetNameField.getText().isEmpty() ||
                !TownField.getText().matches("[a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+") || TownField.getText().isEmpty() ||
                !PostcodeField.getText().matches("[A-Z]{2}\\d{1}[A-Z]{2}") || PostcodeField.getText().isEmpty() )
        {
            // If it doesn't pass an alert is shown
            Alert addAlert = new Alert(Alert.AlertType.ERROR);
            addAlert.setTitle("Adding Record Error");
            addAlert.setHeaderText("Invalid data entered");
            addAlert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Empty fields alert Shown");
                }
            });}

            // If it does pass the data in the fields create a Student Record
        else {
            String Name = NameField.getText();
            String StudentNum = StudentNumField.getText();
            String CourseName = CourseNameField.getText();
            String CourseID = CourseIDField.getText();
            String HouseNum = HouseNumField.getText();
            String StreetName = StreetNameField.getText();
            String Town = TownField.getText();
            String Postcode = PostcodeField.getText();

            addListRecord.add(new RecordCreator(Name, StudentNum, CourseName, CourseID, HouseNum, StreetName, Town, Postcode));

            //Clear all fields
            NameField.clear();
            StudentNumField.clear();
            CourseNameField.clear();
            CourseIDField.clear();
            HouseNumField.clear();
            StreetNameField.clear();
            TownField.clear();
            PostcodeField.clear();

            // Appending the Record in the TXT file
            try {
                FileWriter addingRecord = new FileWriter("src\\Records.txt", true);

                //  For loop for all in the addListRecord
                for(RecordCreator p : addListRecord) {
                    addingRecord.append(p.getName());
                    addingRecord.append(COMMA);
                    addingRecord.append(p.getStudentNum());
                    addingRecord.append(COMMA);
                    addingRecord.append(p.getCourseName());
                    addingRecord.append(COMMA);
                    addingRecord.append(p.getCourseID());
                    addingRecord.append(COMMA);
                    addingRecord.append(p.getHouseNum());
                    addingRecord.append(COMMA);
                    addingRecord.append(p.getStreetName());
                    addingRecord.append(COMMA);
                    addingRecord.append(p.getTown());
                    addingRecord.append(COMMA);
                    addingRecord.append(p.getPostcode());
                    addingRecord.append(NEW_LINE);
                }
                addingRecord.flush();
                addingRecord.close();
                System.out.println("Saved");
                recordsList.clear();
                getRecordCreator();
                addListRecord.clear();
            }   catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Delete button clicked
    public void deleteButtonClicked() {
        // Finds the selected Record and deletes it from the table
        // and the List that all the records are stored
        recordsList = table.getItems();
        recordSelected = table.getSelectionModel().getSelectedItems();

        recordsList.removeAll(recordSelected);
    }

    // Quit button clicked
    public void quitButtonClicked() {
        // Closes the program
        window.close();
    }

    // Get all of the Records
    public ObservableList<RecordCreator> getRecordCreator() {

        String fileName = "src\\Records.txt";
        File file = new File(fileName);
        try {
            Scanner inputStreams = new Scanner(file);
            // Reads through the Records.txt file line by line
            while (inputStreams.hasNext()) {
                String line = inputStreams.nextLine();

                // Splits the data found in each line with delimiter ","
                // Then creates an object using those 8 fields
                String[] splitted = line.split(",");
                recordsList.add(new RecordCreator(splitted[0],splitted[1],splitted[2],splitted[3],splitted[4],splitted[5],splitted[6],splitted[7]));
            }
        } catch (FileNotFoundException e) {
            // If a file is not found this is shown in the console informing the user
            System.out.println("File does not exist");
            System.out.println("Add a new record to create one");
        }

        return recordsList;
    }
}


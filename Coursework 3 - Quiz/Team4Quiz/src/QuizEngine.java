import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.text.FontWeight;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.geometry.*;
import javafx.scene.text.Font;
import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class QuizEngine {

    Stage window;
    Integer quizIndex = 0;
    Integer score = 0;
    Label questionNum;
    Label question;
    String topic;
    ObservableList<QuestionCreator> questionsList = FXCollections.observableArrayList();
    ObservableList<QuestionCreator> TopicquestionList = FXCollections.observableArrayList();
    String NEW_LINE_SEPARATOR = "\n";
    Random random = new Random();
    int randomQuestionIndex;

    public void start(){

        // Load up the File
        if (quizIndex.equals(0)) {
            getQuestionCreator();
            getTopic();

            for(int i=0; i < questionsList.size(); i++) {
                if (questionsList.get(i).getTopic().equals(topic)) {
                    TopicquestionList.add(questionsList.get(i));
                }
            }
        }

        randomQuestionIndex = random.nextInt(TopicquestionList.size());

        window = new Stage();
        window.setTitle("Quiz Game");
        window.initModality(Modality.APPLICATION_MODAL);
        window.setWidth(600);
        window.setHeight(450);
        window.setResizable(false);
        window.getIcons().add(new Image("quizico.png"));

        questionNum = new Label("Question "+(quizIndex+1)+" / 10");
        question = new Label(TopicquestionList.get(randomQuestionIndex).getQuestion());

        questionNum.setFont(Font.font("null", FontWeight.BOLD, 16));
        question.setFont(Font.font("null", FontWeight.BOLD, 11));

        // Choice buttons
        Button choice1Button = new Button(TopicquestionList.get(randomQuestionIndex).getChoice1());
        Button choice2Button = new Button(TopicquestionList.get(randomQuestionIndex).getChoice2());
        Button choice3Button = new Button(TopicquestionList.get(randomQuestionIndex).getChoice3());
        Button choice4Button = new Button(TopicquestionList.get(randomQuestionIndex).getChoice4());


        choice1Button.setOnAction(e -> {

            if (choice1Button.getText().equals(TopicquestionList.get(randomQuestionIndex).getAnswer()) && quizIndex.equals(9)) {
                score++;
                StoreScore();
                FeedbackCorrectLast();
                window.close();
                }
                else if (choice1Button.getText().equals(TopicquestionList.get(randomQuestionIndex).getAnswer())) {
                TopicquestionList.remove(randomQuestionIndex);
                score++;
                FeedbackCorrect();
                quizIndex++;
                window.close();
                start();
            }
            else if ((!choice1Button.getText().equals(TopicquestionList.get(randomQuestionIndex).getAnswer())) && quizIndex.equals(9)) {
                StoreScore();
                FeedbackWrongLast();
                window.close();
            }
            else {
                FeedbackWrong();
                TopicquestionList.remove(randomQuestionIndex);
                quizIndex++;
                window.close();
                start();
            }
        });

        choice2Button.setOnAction(e -> {

            if (choice2Button.getText().equals(TopicquestionList.get(randomQuestionIndex).getAnswer()) && quizIndex.equals(9)) {
                score++;
                StoreScore();
                FeedbackCorrectLast();
                window.close();
            }
            else if (choice2Button.getText().equals(TopicquestionList.get(randomQuestionIndex).getAnswer())) {
                TopicquestionList.remove(randomQuestionIndex);
                score++;
                FeedbackCorrect();
                quizIndex++;
                window.close();
                start();
            }
            else if ((!choice2Button.getText().equals(TopicquestionList.get(randomQuestionIndex).getAnswer())) && quizIndex.equals(9)) {
                StoreScore();
                FeedbackWrongLast();
                window.close();
            }
            else {
                FeedbackWrong();
                TopicquestionList.remove(randomQuestionIndex);
                quizIndex++;
                window.close();
                start();
            }
        });

        choice3Button.setOnAction(e -> {

            if (choice3Button.getText().equals(TopicquestionList.get(randomQuestionIndex).getAnswer()) && quizIndex.equals(9)) {
                score++;
                StoreScore();
                FeedbackCorrectLast();
                window.close();
            }
            else if (choice3Button.getText().equals(TopicquestionList.get(randomQuestionIndex).getAnswer())) {
                TopicquestionList.remove(randomQuestionIndex);
                score++;
                FeedbackCorrect();
                quizIndex++;
                window.close();
                start();
            }
            else if ((!choice3Button.getText().equals(TopicquestionList.get(randomQuestionIndex).getAnswer())) && quizIndex.equals(9)) {
                StoreScore();
                FeedbackWrongLast();
                window.close();
            }
            else {
                FeedbackWrong();
                TopicquestionList.remove(randomQuestionIndex);
                quizIndex++;
                window.close();
                start();
            }
        });

        choice4Button.setOnAction(e -> {

            if (choice4Button.getText().equals(TopicquestionList.get(randomQuestionIndex).getAnswer()) && quizIndex.equals(9)) {
                score++;
                StoreScore();
                FeedbackCorrectLast();
                window.close();
            }
            else if (choice4Button.getText().equals(TopicquestionList.get(randomQuestionIndex).getAnswer())) {
                TopicquestionList.remove(randomQuestionIndex);
                score++;
                FeedbackCorrect();
                quizIndex++;
                window.close();
                start();
            }
            else if ((!choice4Button.getText().equals(TopicquestionList.get(randomQuestionIndex).getAnswer())) && quizIndex.equals(9)) {
                StoreScore();
                FeedbackWrongLast();
                window.close();
            }
            else {
                FeedbackWrong();
                TopicquestionList.remove(randomQuestionIndex);
                quizIndex++;
                window.close();
                start();
            }
        });

        //Restart Button event
        Button restartButton = new Button("Restart");
        restartButton.setStyle("-fx-background-color: #A9A9A9");
        restartButton.setOnAction(e -> {
            // Refresh the class
            quizIndex = 0;
            score = 0;
            questionsList.clear();
            TopicquestionList.clear();
            window.close();
            start();
        });

        //Quit Button event
        Button quitButton = new Button("Quit");
        quitButton.setStyle("-fx-background-color: #A9A9A9");
        quitButton.setOnAction(e -> {
            window.close();
        });

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(restartButton, quitButton);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(20);

        vBox.setPadding(new Insets(70, 90, 40, 90));

        //Add buttons
        vBox.getChildren().addAll(questionNum ,question, choice1Button, choice2Button, choice3Button, choice4Button, hBox);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    public ObservableList<QuestionCreator> getQuestionCreator() {

        String fileName = "listQuestions.csv";
        File file = new File(fileName);
        try {
            Scanner inputStreams = new Scanner(file);
            while (inputStreams.hasNext()) {
                String line = inputStreams.nextLine();

                String[] splitted = line.split(",");
                questionsList.add(new QuestionCreator(splitted[0],splitted[1],splitted[2],splitted[3],splitted[4],splitted[5],splitted[6]));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        System.out.println(questionsList);
        return questionsList;
    }

    public String getTopic() {

        String fileName = "listPrefs.csv";
        File file = new File(fileName);
        try {
            Scanner inputStreams = new Scanner(file);
            inputStreams.nextLine();
            inputStreams.nextLine();
            topic = inputStreams.nextLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        System.out.println(topic);
        return topic;
    }

    public void FeedbackCorrect(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Feedback");
        alert.setHeaderText("Correct answer");
        alert.setContentText("Your Score: "+ score + "/ 10");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Feedback Shown");
            }
        });
    }

    public void FeedbackCorrectLast(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Feedback");
        alert.setHeaderText("Correct answer");
        alert.setContentText("Your Score: "+ score + " / 10");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Feedback Shown");
            }
        });
        Alert alertfinal = new Alert(Alert.AlertType.INFORMATION);
        alertfinal.setTitle("Overall Feedback");
        alert.setHeaderText("You have finished the Quiz");
        alert.setContentText("Overall Score: "+ score + " / 10");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Overall Feedback Shown");
            }
        });
    }

    public void FeedbackWrong(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Feedback");
        alert.setHeaderText("Wrong answer");
        alert.setContentText("Correct answer was: "+ TopicquestionList.get(randomQuestionIndex).getAnswer() +"\n\nYour Score: "+ score + " / 10");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Feedback Shown");
            }
        });
    }

    public void FeedbackWrongLast(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Feedback");
        alert.setHeaderText("Wrong answer");
        alert.setContentText("Correct answer was: "+ TopicquestionList.get(randomQuestionIndex).getAnswer() +"\n\nYour Score: "+ score + " / 10");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Feedback Shown");
            }
        });
        Alert alertfinal = new Alert(Alert.AlertType.INFORMATION);
        alertfinal.setTitle("Overall Feedback");
        alert.setHeaderText("You have finished the Quiz");
        alert.setContentText("Overall Score: "+ score + " / 10");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Overall Feedback Shown");
            }
        });
    }

    public void StoreScore(){

        try {
            FileWriter addScore = new FileWriter("listPrefs.csv", true);

            //  Append student scores in files
            addScore.append(score.toString());
            addScore.append(NEW_LINE_SEPARATOR);
            addScore.flush();
            addScore.close();
            System.out.println("Done");
        }   catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
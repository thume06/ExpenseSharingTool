import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainScreen implements Initializable{

    public MainScreen(){
        instance = this;
    }
    private static MainScreen instance;
    public static MainScreen getInstance(){return instance;}

    private Main screen;

    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    private final int SCREEN_WIDTH = 900;
    private final int SCREEN_HEIGHT = 600;

    private ArrayList<String> people = new ArrayList<>();

    @FXML AnchorPane parentPane;
    @FXML Label lblAllExpenses;
    @FXML Label lblAddPerson;
    @FXML ScrollPane scrollPeople;
    @FXML AnchorPane peoplePane;
    @FXML Button btnAddExpense;
    @FXML Button btnSettleDebt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        screen = Main.getInstance();

        scrollPeople.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPeople.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPeople.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-background-color:transparent;");

        lblAllExpenses.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {labelHovered(lblAllExpenses);}
        });
        lblAllExpenses.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {labelExited(lblAllExpenses);}
        });
        lblAddPerson.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {labelHovered(lblAddPerson);}
        });
        lblAddPerson.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {labelExited(lblAddPerson);}
        });
        lblAddPerson.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {addPersonPressed();}
        });

    }

    private void labelHovered(Label l){
        l.setTextFill(Color.web("#000000"));
    }

    private void labelExited(Label l){
        l.setTextFill(Color.web("#797575"));
    }

    private void addPersonPressed(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AddPerson.fxml"));;
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Add Person");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void addPerson(String n){
        final int x = 15;
        int lastY = people.size() * 20 - 15;
        final int personIndex = people.size();

        people.add(n);

        Label person = new Label();
        person.setText(people.get(people.size() - 1));
        person.setTextFill(Color.web("#797575"));
        person.setFont(Font.font("Arial", 14));

        peoplePane.getChildren().add(person);
        person.setLayoutX(x);
        person.setLayoutY(lastY + 20);

        person.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {labelHovered(person);}
        });
        person.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {labelExited(person);}
        });
        person.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {personPressed(personIndex);}
        });

        if(people.size() > 24){
            double h = peoplePane.getHeight();
            if (people.size() == 25){
                h -= 5;
            }
            peoplePane.setMaxHeight(h + 20 );
            peoplePane.setPrefHeight(h + 20 );
            scrollPeople.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        }
    }

    private void personPressed(int p){
        System.out.println(people.get(p) + " pressed.");
    }

    @FXML
    private void addExpensePressed(){
        if(people.size() < 2){
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You must have at least 2 people to add expenses.");
            alert.showAndWait();
            return;
        }

        try {
            Parent root = FXMLLoader.load(getClass().getResource("AddExpense.fxml"));;
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Add Expense");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<String> getPeople(){
        return people;
    }
}

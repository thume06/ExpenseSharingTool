import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddExpense implements Initializable{
    private MainScreen mainScreen;
    private Main screen;

    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    private ArrayList<String> people;
    private ArrayList<String> debtors = new ArrayList<>();

    private String selectedPayer;

    @FXML TextField txtCost;
    @FXML MenuButton  mnuDebtors;
    @FXML Slider barDistribution;
    @FXML ChoiceBox chcPayer;
    @FXML ChoiceBox chcCategory;
    @FXML Label lblDistribution;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        screen = Main.getInstance();
        mainScreen = MainScreen.getInstance();
        people = mainScreen.getPeople();
        mnuDebtors.getItems().clear();
        barDistribution.setValue(50);

        for(int i = 0; i < people.size(); i++){chcPayer.getItems().add(people.get(i));}
        chcPayer.setValue(people.get(0));
        selectedPayer = people.get(0);
        chcPayer.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                selectedPayer = String.valueOf(chcPayer.getItems().get((Integer) number2));
                loadDebtors();
                updateDistributionLabel();
            }
        });

        loadDebtors();

        chcCategory.getItems().add("Groceries");
        chcCategory.setValue("Groceries");

       barDistribution.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {updateDistributionLabel();}
        });
       updateDistributionLabel();


    }

    @FXML private void addExpensePressed(){
        double cost;
        try{cost = Double.parseDouble(txtCost.getText());}
        catch(NumberFormatException e){
            e.printStackTrace();
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Cost must be a decimal value (ex: 3.29).");
            alert.showAndWait();
            return;
        }

        String category = (String) chcCategory.getSelectionModel().getSelectedItem();
        double distribution = barDistribution.getValue();

        Expense expense = new Expense(category, (float) cost, selectedPayer, distribution, debtors);

        Stage stage = (Stage) txtCost.getScene().getWindow();
        stage.close();
    }

    //Loads in the selectable debtors. Called every time the payer is changed
    private void loadDebtors(){
        mnuDebtors.getItems().clear();
        debtors.clear();
        for(int i = 0; i < people.size(); i++){
            if(selectedPayer.equals(people.get(i))){
                continue;
            }
            CheckBox chkPerson = new CheckBox(people.get(i));
            chkPerson.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(chkPerson.isSelected()){
                        debtors.add(chkPerson.getText());
                    }
                    else{
                        for(int i = 0; i < debtors.size(); i++){
                            if(debtors.get(i).equals(chkPerson.getText())){
                                debtors.remove(i);
                                break;
                            }
                        }
                    }
                }
            });
            CustomMenuItem menuItem = new CustomMenuItem(chkPerson);
            menuItem.setHideOnClick(false);
            mnuDebtors.getItems().add(menuItem);
        }
    }

    //Updates the label below the distribution slider. Called when the slider is moved or payer is changed
    private void updateDistributionLabel(){
        lblDistribution.setText(selectedPayer + " will pay " + Math.round(barDistribution.getValue()) + "% of the cost.");
    }
}

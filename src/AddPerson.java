import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPerson implements Initializable{

    private MainScreen mainScreen;

    @FXML AnchorPane screenPane;
    @FXML Button btnAdd;
    @FXML Button btdCancel;
    @FXML TextField txtName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainScreen = MainScreen.getInstance();
    }

    @FXML
    public void cancelPressed(){
        Stage stage = (Stage) screenPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addPressed(){
        if(txtName.getText().equals("")){
            return;
        }
        Stage stage = (Stage) screenPane.getScene().getWindow();
        stage.close();
        mainScreen.addPerson(txtName.getText());
    }
}

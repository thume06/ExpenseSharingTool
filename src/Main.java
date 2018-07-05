import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public Main(){
        instance = this;
    }

    private static Main instance;

    private Stage stage;

    public static Main getInstance(){return instance;}

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Expense Sharing Tool");
        //stage.getIcons().add(new Image("image directory));
        stage.show();
    }

    public static void main(String[] args){launch(args);}

    public void setWidth(int w){stage.setWidth(w);}

    public void setHeight(int h){stage.setHeight(h);}
}

package academ.view;

import academ.MainApp;
import academ.util.DatabaseHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {
    @FXML
    private TextField loginField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField firstPassField;

    @FXML
    private PasswordField secondPassField;

    @FXML
    private Label status;

    private Stage dialogStage;
    private MainApp mainApp;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleSubmit() {
        String name = nameField.getText();
        String log = loginField.getText();
        String pass1 = firstPassField.getText();
        String pass2 = secondPassField.getText();
        if(pass1.equals(pass2)){
            if(!(DatabaseHelper.loginCheck(log))){
                DatabaseHelper.createUser(name, log, pass1);
                nameField.setText("");
                loginField.setText("");
                firstPassField.setText("");
                secondPassField.setText("");
                status.setText("Succesfully sumbitted");
            }
        }

    }
}
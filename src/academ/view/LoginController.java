package academ.view;

import academ.MainApp;
import academ.util.DatabaseHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private Button aboutButton;

    @FXML
    private Button signinButton;

    @FXML
    private Button registerButton;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

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
    private void handleAbout() {
        mainApp.showAbout();
    }

    @FXML
    private void handleSignin() {
        String login = loginField.getText();
        String password = passwordField.getText();
        if(DatabaseHelper.userCheck(login, password)){
            mainApp.showUser();
        }

    }



    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (loginField.getText() == null || loginField.getText().length() == 0) {
            errorMessage += "Login is not valid!\n";
        }
        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "Password is not valid!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();
            return false;
        }
    }

    @FXML
    private void handleRegister() {
        mainApp.showReg();
    }
}
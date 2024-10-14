package org.calc2.enviarmail;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.apache.commons.mail.EmailException;

public class RootController {
    @FXML
    private Label welcomeText;


    @FXML
    private TextField asuntoField;

    @FXML
    private TextField destinatarioField;

    @FXML
    private TextArea mensajeField;

    @FXML
    private TextField remitenteField;

    @FXML
    private TextField smtpField;

    @FXML
    private TextField smtpPortField;


    @FXML
    private TextField passField;

    @FXML
    private CheckBox sslCheckBox;

    @FXML
    void enviarButton(ActionEvent event) {
        enviarCorreo();


    }

    @FXML
    void clearButton(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    private void cerrarButton(ActionEvent event) {
        Platform.exit(); // Cierra la aplicación

    }

    private void enviarCorreo() {
        try {
            if (asuntoField.getText().isEmpty() || destinatarioField.getText().isEmpty() || remitenteField.getText().isEmpty() || smtpField.getText().isEmpty()
                    || smtpPortField.getText().isEmpty() || passField.getText().isEmpty()) {
                throw new Exception("Por favor, complete los datos correctamente");
            } else {
                EmailSender.setConnection(sslCheckBox.isSelected()); // Actualiza el estado del SSL
                EmailSender.emailSender(
                        smtpField.getText(),
                        smtpPortField.getText(),
                        remitenteField.getText(),
                        asuntoField.getText(),
                        mensajeField.getText(),
                        destinatarioField.getText(),
                        passField.getText()
                );
                mensajeExito();
            }



        } catch (EmailException e) {
            mensajeError(new Exception("Error al enviar el correo: " + e.getMessage()));
        } catch (Exception e) {
            mensajeError(e); // Maneja cualquier otra excepción
        }
    }


    private void mensajeExito () {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exito");
        alert.setHeaderText("Correo enviado");
        alert.showAndWait();
        return;
    }
    private void mensajeError (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Revise los datos");
        alert.setContentText(e.getMessage());
        alert.setResizable(true);
        alert.getDialogPane().setMinSize(400, 200);
        alert.showAndWait();
        return;
    }

    private void limpiarCampos() {
        asuntoField.clear();
        destinatarioField.clear();
        mensajeField.clear();
        remitenteField.clear();
        smtpField.clear();
        smtpPortField.clear();
        sslCheckBox.setSelected(false);
    }




}
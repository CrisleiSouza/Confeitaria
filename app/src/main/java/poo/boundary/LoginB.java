package poo.boundary;

import poo.boundary.adminVer.PrincipalB;
import poo.boundary.viewOnly.PrincipalView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginB implements ITela {

    private Stage stage;

    private TextField txtEmail = new TextField();
    private PasswordField txtSenha = new PasswordField();

    public LoginB (Stage stage) {
        this.stage = stage;
    }

    @Override
    public Pane render () {

        BorderPane bp = new BorderPane();
        GridPane grid = new GridPane();

        bp.setCenter(grid);

        // Labels
        grid.add(new Label("E-mail:"), 0, 0);
        grid.add(txtEmail, 1, 0);

        grid.add(new Label("Senha:"), 0, 1);
        grid.add(txtSenha, 1, 1);

        // Botão
        Button btnEntrar = new Button("Entrar");
        
        btnEntrar.setOnAction(e -> {
            String email = txtEmail.getText();
            String senha = txtSenha.getText();

            ITela tela;

            if ( validarAdmin(email, senha) ) {
                System.out.println("TESTE");
                tela = new PrincipalB();
                stage.setScene(new Scene(tela.render(), 1000, 600));

            } else if ( validarView(email, senha) ){
                tela = new PrincipalView();
                stage.setScene(new Scene(tela.render(), 1000, 600));

            } else {
                new Alert(AlertType.WARNING, 
                "E-mail ou senha inválidos").show();
            }
        });

        grid.add(btnEntrar, 1, 2);

        return bp;
    }

    private boolean validarAdmin(String email, String senha) {
        return email.equals("admin@admin.com") && senha.equals("123456");
    }

    private boolean validarView(String email, String senha) {
    return email.equals("view@view.com") && senha.equals("123456");
}
}
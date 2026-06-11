package poo.boundary.adminVer;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import poo.boundary.ITela;

public class PrincipalB implements ITela {

    private Pane boloB = new BoloB().render();
    private Pane confeiteiroB = new ConfeiteiroB().render();

    @Override
    public Pane render() {
        
        BorderPane panPrincipal = new BorderPane();
        
        GridPane pane = new GridPane();

        Label lblTitulo = new Label("CONFEITARIA");
        lblTitulo.setFont(new Font("Arial", 25));
        Label lblSubtitulo = new Label("Catálogo de bolos e confeiteiros funcionários da confeitaria!");
        lblSubtitulo.setFont(new Font("Arial", 20));

        pane.add(lblTitulo, 0, 0, 10, 1);
        pane.add(lblSubtitulo, 0, 1, 10, 1);
        GridPane.setHalignment(lblTitulo, HPos.CENTER);
        GridPane.setHalignment(lblSubtitulo, HPos.CENTER);
        pane.setAlignment(Pos.CENTER);

        MenuBar menuBar = new MenuBar();

        Menu menuGeral = new Menu("Menu");
        menuBar.getMenus().addAll( menuGeral );
        MenuItem menuBoloItem = new MenuItem("Bolo");
        MenuItem menuConfeiteiroItem = new MenuItem("Confeiteiro");
        
        menuBoloItem.setOnAction( e -> panPrincipal.setCenter( boloB ));
        menuConfeiteiroItem.setOnAction(e -> panPrincipal.setCenter( confeiteiroB ));
    
        menuGeral.getItems().addAll( menuBoloItem, menuConfeiteiroItem );
    
        panPrincipal.setTop( menuBar );
        panPrincipal.setCenter( pane );

        return panPrincipal;
    }
    
}
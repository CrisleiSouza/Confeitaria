package poo.boundary.adminVer;

import poo.entity.Bolo;
import poo.boundary.ITela;
import poo.control.BoloC;

import java.text.NumberFormat;
import java.util.Locale;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.beans.binding.Bindings;

import javafx.util.converter.NumberStringConverter;
import javafx.util.Callback;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BoloB implements ITela {
    
    private NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));

    private ObservableList<String> formatos = 
        FXCollections.observableArrayList("Redondo", "Retângulo", "Quadrado");

    private ComboBox<String> cmbFormato = new ComboBox<>();
    private TextField txtNome = new TextField();
    private TextField txtDescricao = new TextField();
    private TextField txtIngredientes = new TextField();
    private TextField txtPreco = new TextField();
    
    private BoloC control = new BoloC();

    private TableView<Bolo> table = new TableView<>();

    @Override
    public Pane render() { 
        System.out.println("BOLOS");
        BorderPane bp = new BorderPane();
        GridPane paneCampos = new GridPane();

        bp.setTop(paneCampos);
        bp.setCenter(table);
        
        paneCampos.add( new Label("Formato"), 0, 0);
        paneCampos.add( cmbFormato, 1, 0);
        paneCampos.add( new Label("Nome"), 0, 1);
        paneCampos.add( txtNome, 1, 1);
        paneCampos.add( new Label("Descrição"), 0, 2);
        paneCampos.add( txtDescricao, 1, 2);
        paneCampos.add( new Label("Ingredientes"), 0, 3);
        paneCampos.add( txtIngredientes, 1, 3);
        paneCampos.add( new Label("Preço"), 0, 4);
        paneCampos.add( txtPreco, 1, 4);

        cmbFormato.setItems(formatos);

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction( ( e ) -> {
            control.salvar();
            control.limparCampos();
        });

        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction( ( e )-> { 
            control.pesquisar();
        });

        Button btnMostrar = new Button("Mostrar todos");
        btnMostrar.setOnAction( (e) -> {
            control.carregar();
            control.limparCampos();
        });

        Button btnLimparCampos = new Button("Limpar");
        btnLimparCampos.setOnAction( e -> control.limparCampos() );

        paneCampos.add( btnSalvar, 0, 5);
        paneCampos.add( btnPesquisar, 1, 5);
        paneCampos.add( btnMostrar, 2, 5);
        paneCampos.add( btnLimparCampos, 2, 0);
        
        Bindings.bindBidirectional( txtNome.textProperty(), control.nome );
        Bindings.bindBidirectional( cmbFormato.valueProperty(), control.formato );
        Bindings.bindBidirectional( txtDescricao.textProperty(), control.descricao );
        Bindings.bindBidirectional( txtIngredientes.textProperty(), control.ingredientes );
        Bindings.bindBidirectional( txtPreco.textProperty(), control.preco, new NumberStringConverter(nf));

        TableColumn<Bolo, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory( 
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNome())
        );
        TableColumn<Bolo, String> colFormato = new TableColumn<>("Formato");
        colFormato.setCellValueFactory( 
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getFormato())
        );
        TableColumn<Bolo, String> colDescricao = new TableColumn<>("Descrição");
        colDescricao.setCellValueFactory( 
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getDescricao())
        );
        TableColumn<Bolo, String> colIngredientes = new TableColumn<>("Ingredientes");
        colIngredientes.setCellValueFactory( 
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getIngredientes())
        );
        TableColumn<Bolo, String> colPreco = new TableColumn<>("Preço");
        colPreco.setCellValueFactory( 
            itemData -> new ReadOnlyStringWrapper(
                String.valueOf(itemData.getValue().getPreco())
            )
        );

        TableColumn<Bolo, Void> colAcoes = new TableColumn<>("Ações");
        Callback<TableColumn<Bolo, Void>, TableCell<Bolo, Void>> callBack = new Callback<>() {
            public TableCell<Bolo, Void> call(TableColumn<Bolo, Void> param) { 
                return new TableCell<Bolo, Void>(){
                    private Button btnDelete = new Button("Deletar");
                    {
                        btnDelete.setOnAction( e -> control.apagar( getIndex() ));
                    }

                    public void updateItem(Void item, boolean empty) {
                        if (!empty) {
                            setGraphic( btnDelete );
                        } else { 
                            setGraphic( null );
                        }
                    }
                };
            }
        };
        colAcoes.setCellFactory( callBack );

        table.setItems( control.getLista() );

        table.getColumns().add( colNome );
        table.getColumns().add( colFormato );
        table.getColumns().add( colDescricao );
        table.getColumns().add( colIngredientes );
        table.getColumns().add( colPreco );
        table.getColumns().add( colAcoes );

        table.getSelectionModel().selectedItemProperty().addListener(
            (obj, antigo, nova) -> control.toBoundary(nova)
        );

        control.limparCampos();
        return bp;
    }

}

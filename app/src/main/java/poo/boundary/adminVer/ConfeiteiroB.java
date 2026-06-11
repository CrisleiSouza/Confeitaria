package poo.boundary.adminVer;

import poo.boundary.ITela;
import poo.control.ConfeiteiroC;
import poo.entity.Confeiteiro;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;


public class ConfeiteiroB implements ITela {

    private TextField txtNome = new TextField();
    private TextField txtEmail = new TextField();
    private TextField txtTelefone = new TextField();
    private TextField txtSobre = new TextField();
    private TextField txtEspecialidade = new TextField();
    
    private ConfeiteiroC control = new ConfeiteiroC();

    private TableView<Confeiteiro> table = new TableView<>();
    
    @Override
    public Pane render() {
        System.out.println("CONFEITEIROS");
        BorderPane bp = new BorderPane();
        GridPane paneCampos = new GridPane();

        bp.setTop(paneCampos);
        bp.setCenter(table);
        
        paneCampos.add( new Label("Nome"), 0, 0);
        paneCampos.add( txtNome, 1, 0);
        paneCampos.add( new Label("E-mail"), 0, 1);
        paneCampos.add( txtEmail, 1, 1);
        paneCampos.add( new Label("Telefone"), 0, 2);
        paneCampos.add( txtTelefone, 1, 2);
        paneCampos.add( new Label("Sobre o confeiteiro"), 0, 3);
        paneCampos.add( txtSobre, 1, 3);
        paneCampos.add( new Label("Especialidade"), 0, 4);
        paneCampos.add( txtEspecialidade, 1, 4);

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
        btnLimparCampos.setOnAction( e -> { 
            control.limparCampos();
        });

        paneCampos.add( btnSalvar, 0, 5);
        paneCampos.add( btnPesquisar, 1, 5);
        paneCampos.add( btnMostrar, 2, 5);
        paneCampos.add( btnLimparCampos, 2, 0);
        
        Bindings.bindBidirectional( txtNome.textProperty(), control.nome );
        Bindings.bindBidirectional( txtEmail.textProperty(), control.email );
        Bindings.bindBidirectional( txtTelefone.textProperty(), control.telefone );
        Bindings.bindBidirectional( txtSobre.textProperty(), control.sobre );
        Bindings.bindBidirectional( txtEspecialidade.textProperty(), control.especialidade );

        TableColumn<Confeiteiro, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory( 
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNome())
        );
        TableColumn<Confeiteiro, String> colEmail = new TableColumn<>("E-mail");
        colEmail.setCellValueFactory( 
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getEmail())
        );
        TableColumn<Confeiteiro, String> colTelefone = new TableColumn<>("Telefone");
        colTelefone.setCellValueFactory( 
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getTelefone())
        );
        TableColumn<Confeiteiro, String> colSobre = new TableColumn<>("Sobre o confeiteiro");
        colSobre.setCellValueFactory( 
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getSobre())
        );
        TableColumn<Confeiteiro, String> colEspecialidade = new TableColumn<>("Especialidade");
        colEspecialidade.setCellValueFactory( 
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getEspecialidade())
        );

        TableColumn<Confeiteiro, Void> colAcoes = new TableColumn<>("Ações");
        Callback<TableColumn<Confeiteiro, Void>, TableCell<Confeiteiro, Void>> callBack = new Callback<>() {
            public TableCell<Confeiteiro, Void> call(TableColumn<Confeiteiro, Void> param) { 
                return new TableCell<Confeiteiro, Void>(){
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
        table.getColumns().add( colEmail );
        table.getColumns().add( colTelefone );
        table.getColumns().add( colSobre );
        table.getColumns().add( colEspecialidade );
        table.getColumns().add( colAcoes );

        table.getSelectionModel().selectedItemProperty().addListener(
            (obj, antigo, nova) -> control.toBoundary(nova)
        );

        control.limparCampos();
        return bp;
    }
    
}
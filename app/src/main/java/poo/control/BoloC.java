package poo.control;

import poo.entity.Bolo;
import poo.DAO.BoloDAO;
import poo.DAO.BoloDAOImpl;
import poo.validation.BoloValidator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;

public class BoloC {

    private ObservableList<Bolo> lista = FXCollections.observableArrayList();

    public LongProperty id = new SimpleLongProperty( -1 );
    public StringProperty nome = new SimpleStringProperty("");
    public StringProperty formato = new SimpleStringProperty("");
    public StringProperty descricao = new SimpleStringProperty("");
    public StringProperty ingredientes = new SimpleStringProperty("");
    public DoubleProperty preco = new SimpleDoubleProperty();

    private BoloDAO dao = new BoloDAOImpl();

    public BoloC() { 
        carregar();
    }

    public void limparCampos() {
        id.set(-1);
        nome.set("");
        formato.set("");
        descricao.set("");
        ingredientes.set("");
        preco.set(0);
    }

    public void salvar() {

        Bolo bolo = toEntity();

        try {
            BoloValidator.validar(bolo);

            if (bolo.getId() > 0) {
                dao.atualizar( bolo.getId(), bolo );
            } else { 
                dao.cadastrar(bolo);
            }

            limparCampos();
            carregar();

        } catch (RuntimeException e) {
            new Alert(AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void carregar() { 
        lista.clear();
        lista.addAll(
            dao.consultarPorNome( "" )
        );
    }
 
    public void pesquisar() { 
        lista.clear();
        lista.addAll(
            dao.consultarPorNome( nome.get() )
        );
    }

    public void apagar( int index ){ 
        Bolo b = lista.get( index );
        dao.apagar( b.getId() );
        carregar();
    }

    public Bolo toEntity() { 
        Bolo bolo = new Bolo();
        bolo.setId( id.get() );
        bolo.setNome( nome.get() );
        bolo.setFormato( formato.get() );
        bolo.setDescricao( descricao.get() );
        bolo.setIngredientes( ingredientes.get() );
        bolo.setPreco( preco.get() );

        return bolo;
    }

    public void toBoundary(Bolo bolo) { 
        if (bolo != null) {
            id.set( bolo.getId() );
            nome.set(bolo.getNome());
            formato.set(bolo.getFormato());
            descricao.set(bolo.getDescricao());
            ingredientes.set(bolo.getIngredientes());
            preco.set(bolo.getPreco());
        }
    }

    public ObservableList<Bolo> getLista() { 
        return lista;
    }
}

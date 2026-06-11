package poo.control;

import poo.DAO.ConfeiteiroDAO;
import poo.DAO.ConfeiteiroDAOImpl;
import poo.entity.Confeiteiro;
import poo.validation.ConfeiteiroValidator;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ConfeiteiroC {
    
    private ObservableList<Confeiteiro> lista = FXCollections.observableArrayList();

    public LongProperty id = new SimpleLongProperty( -1 );
    public StringProperty nome = new SimpleStringProperty("");
    public StringProperty email = new SimpleStringProperty("");
    public StringProperty telefone = new SimpleStringProperty("");
    public StringProperty sobre = new SimpleStringProperty("");
    public StringProperty especialidade = new SimpleStringProperty("");

    private ConfeiteiroDAO dao = new ConfeiteiroDAOImpl();

    public ConfeiteiroC() { 
        carregar();
    }

    public void limparCampos() {
        id.set(-1);
        nome.set("");
        email.set("");
        telefone.set("");
        sobre.set("");
        especialidade.set("");
    }

    public void salvar() { 

        Confeiteiro conf = toEntity();

        try {
            ConfeiteiroValidator.validar(conf);

            if (conf.getId() > 0) {
                dao.atualizar( conf.getId(), conf );
            } else { 
                dao.cadastrar(conf);
            }

            limparCampos();
            carregar();

        } catch (Exception e) {
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
        Confeiteiro conf = lista.get( index );
        dao.apagar( conf.getId() );
        carregar();
    }

    public Confeiteiro toEntity() { 
        Confeiteiro conf = new Confeiteiro();
        conf.setId( id.get() );
        conf.setNome( nome.get() );
        conf.setEmail( email.get() );
        conf.setTelefone( telefone.get() );
        conf.setSobre( sobre.get() );
        conf.setEspecialidade( especialidade.get() );

        return conf;
    }

    public void toBoundary(Confeiteiro conf) { 
        if (conf != null) {
            id.set( conf.getId() );
            nome.set( conf.getNome() );
            email.set( conf.getEmail() );
            telefone.set( conf.getTelefone() );
            sobre.set( conf.getSobre() );
            especialidade.set( conf.getEspecialidade() );
        }
    }

    public ObservableList<Confeiteiro> getLista() { 
        return lista;
    }
}

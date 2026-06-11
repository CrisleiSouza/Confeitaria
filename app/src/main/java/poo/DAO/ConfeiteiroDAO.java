package poo.DAO;

import java.util.List;

import poo.entity.Confeiteiro;

public interface ConfeiteiroDAO {
    void cadastrar( Confeiteiro confeiteiro );
    List<Confeiteiro> consultarPorNome( String nome );
    void atualizar( long id, Confeiteiro confeiteiro );
    void apagar( long id );
}

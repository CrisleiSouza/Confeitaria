package poo.DAO;

import poo.entity.Bolo;

import java.util.List;

public interface BoloDAO {
    void cadastrar( Bolo bolo );
    List<Bolo> consultarPorNome( String nome );
    void atualizar( long id, Bolo bolo );
    void apagar( long id );
}

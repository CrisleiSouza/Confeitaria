package poo.DAO;

import poo.entity.Confeiteiro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConfeiteiroDAOImpl implements ConfeiteiroDAO {

    private static final String DB_JDBC_URI = "jdbc:mariadb://localhost:3306/confeitaria?allowPublicKeyRetrieval=true&useSSL=false&allowMultiQueries=true";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456"; 
    private Connection con;

    public ConfeiteiroDAOImpl() {
        System.out.println("DAO Confeiteiro criado com database!");

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Classe carregada");
            con = DriverManager.getConnection(DB_JDBC_URI, DB_USER, DB_PASS);
            System.out.println("Conexao realizada com sucesso");

        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar a classe");
            e.printStackTrace();

        } catch (SQLException e) {
            System.err.println("Erro ao conectar");
            e.printStackTrace();
        }
    }

    @Override
    public void cadastrar(Confeiteiro confeiteiro) {
        try {
            String sql = "INSERT INTO confeiteiro (nome, email, telefone, sobre, especialidade) VALUES "
                    + "(?, ?, ?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, confeiteiro.getNome());
            stm.setString(2, confeiteiro.getEmail());
            stm.setString(3, confeiteiro.getTelefone());
            stm.setString(4, confeiteiro.getSobre());
            stm.setString(5, confeiteiro.getEspecialidade());

            stm.executeUpdate();

            System.out.println("Insert executado com sucesso!"); 

        } catch (SQLException e) {
            System.out.println("Erro ao conectar");
            e.printStackTrace();
        }
    }

    @Override
    public List<Confeiteiro> consultarPorNome(String nome) {
        List<Confeiteiro> lista = new ArrayList<>();

        try { 
            String sql = "SELECT * FROM confeiteiro WHERE nome LIKE ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, "%" + nome + "%" );
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String confeiteiroNome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");                
                String sobre = rs.getString("sobre");
                String especialidade = rs.getString("especialidade");
                
                Confeiteiro c = new Confeiteiro();
                c.setId( id );
                c.setNome( confeiteiroNome );
                c.setEmail( email );
                c.setTelefone( telefone );
                c.setSobre( sobre );
                c.setEspecialidade( especialidade );
                
                lista.add( c );
            }

            System.out.println("Consulta executada com sucesso");   

        } catch (SQLException e) {
            System.err.println("Erro ao conectar");
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void atualizar(long id, Confeiteiro c) {
        try {
            String sql = 
            "UPDATE confeiteiro SET nome = ?, email = ?, telefone = ?, sobre = ?, especialidade = ? WHERE id = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, c.getNome());
            stm.setString(2, c.getEmail());
            stm.setString(3, c.getTelefone());
            stm.setString(4, c.getSobre());
            stm.setString(5, c.getEspecialidade());
            stm.setLong(6, id);

            stm.executeUpdate();

            System.out.println("Confeiteiro atualizado com sucesso!"); 

        } catch (SQLException e) {
            System.err.println("Erro ao conectar");
            e.printStackTrace();
        }
    }

    @Override
    public void apagar(long id) {
        try {
            String sql = "DELETE FROM confeiteiro WHERE id = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setLong(1, id);

            stm.executeUpdate();

            System.out.println("Confeiteiro apagado com sucesso!"); 

        } catch (SQLException e) {
            System.err.println("Erro ao conectar");
            e.printStackTrace();
        }
    }
}

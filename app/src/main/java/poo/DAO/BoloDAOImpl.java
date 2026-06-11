package poo.DAO;

import poo.entity.Bolo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoloDAOImpl implements BoloDAO{
    private static final String DB_JDBC_URI = "jdbc:mariadb://localhost:3306/confeitaria?allowPublicKeyRetrieval=true&useSSL=false&allowMultiQueries=true";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456"; 
    private Connection con;

    public BoloDAOImpl() { 
        System.out.println("DAO Bolo criado com database!");

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
    public void cadastrar(Bolo bolo) {
         try { 
            String sql = "INSERT INTO bolo (nome, descricao, ingredientes, formato, preco) VALUES " +
            "(?, ?, ?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, bolo.getNome());
            stm.setString(2, bolo.getDescricao());
            stm.setString(3, bolo.getIngredientes());
            stm.setString(4, bolo.getFormato());
            stm.setDouble(5, bolo.getPreco());
            stm.executeUpdate();

            System.out.println("Insert executado com sucesso!"); 

        } catch (SQLException e) {
            System.err.println("Erro ao conectar");
            e.printStackTrace();
        }
    }

    @Override
    public List<Bolo> consultarPorNome(String nome) {
        List<Bolo> lista = new ArrayList<>();

        try { 
            String sql = "SELECT * FROM bolo WHERE nome LIKE ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, "%" + nome + "%" );
            ResultSet rs = stm.executeQuery();

            while (rs.next()) { 
                Long id = rs.getLong("id");
                String boloNome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                String ingredientes = rs.getString("ingredientes");                
                String formato = rs.getString("formato");
                double preco = rs.getDouble("preco");
                
                Bolo b = new Bolo();
                b.setId( id );
                b.setNome( boloNome );
                b.setFormato( formato );
                b.setDescricao( descricao );
                b.setIngredientes( ingredientes );
                b.setPreco( preco );
                
                lista.add( b );
            }

            System.out.println("Consulta executada com sucesso");   

        } catch (SQLException e) {
            System.err.println("Erro ao conectar");
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void atualizar(long id, Bolo b) {
        try { 
            String sql = 
            "UPDATE bolo SET nome = ?, formato = ?, descricao = ?, ingredientes = ?, preco = ? WHERE id = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, b.getNome());
            stm.setString(2, b.getFormato());
            stm.setString(3, b.getDescricao());
            stm.setString(4, b.getIngredientes());
            stm.setDouble(5, b.getPreco());
            stm.setLong(6, id);
            stm.executeUpdate();

            System.out.println("Bolo atualizado com sucesso!"); 

        } catch (SQLException e) {
            System.err.println("Erro ao conectar");
            e.printStackTrace();
        }
    }

    @Override
    public void apagar(long id) {
        try { 
            String sql = "DELETE FROM bolo WHERE id = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setLong(1, id);
            stm.executeUpdate();

            System.out.println("Bolo apagado com sucesso!"); 

        } catch (SQLException e) {
            System.err.println("Erro ao conectar");
            e.printStackTrace();
        }
    }

}

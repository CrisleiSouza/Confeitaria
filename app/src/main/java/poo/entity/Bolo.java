package poo.entity;

public class Bolo {
    private long id;
    private String nome = "";
    private String descricao = "";
    private String ingredientes = "";
    private String formato = "";
    private double preco = 0000.00;

    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return this.descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIngredientes() {
        return this.ingredientes;
    }
    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getFormato() {
        return this.formato;
    }
    public void setFormato(String formato) {
        this.formato = formato;
    }

    public double getPreco() {
        return this.preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
}

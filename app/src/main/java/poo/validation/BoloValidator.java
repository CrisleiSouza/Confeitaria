package poo.validation;

import poo.entity.Bolo;

public class BoloValidator {

    public static void validar(Bolo b) {

        if (b.getNome() == null || b.getNome().trim().isEmpty()) {
            throw new RuntimeException("Nome não pode estar vazio");
        }
        if (b.getNome().length() > 100) {
            throw new RuntimeException("Nome deve ter no máximo 100 caracteres");
        }


        if (b.getDescricao() == null || b.getDescricao().trim().isEmpty()) {
            throw new RuntimeException("Descrição não pode estar vazia");
        }
        if (b.getDescricao().length() > 200) {
            throw new RuntimeException("Descrição deve ter no máximo 200 caracteres");
        }


        if (b.getIngredientes() == null || b.getIngredientes().trim().isEmpty()) {
            throw new RuntimeException("Ingredientes não podem estar vazios");
        }
        if (b.getIngredientes().length() > 200) {
            throw new RuntimeException("Ingredientes deve ter no máximo 200 caracteres");
        }


        if (b.getFormato() == null || b.getFormato().trim().isEmpty()) {
            throw new RuntimeException("Formato deve ser selecionado");
        }

        
        if (b.getPreco() <= 0) {
            throw new RuntimeException("Preço deve ser maior que zero");
        }
    }
}
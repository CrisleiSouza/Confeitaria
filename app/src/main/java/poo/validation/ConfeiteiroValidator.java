package poo.validation;

import poo.entity.Confeiteiro;

public class ConfeiteiroValidator {

    public static void validar(Confeiteiro c) {

        if (c.getNome() == null || c.getNome().trim().isEmpty()) {
            throw new RuntimeException("Nome não pode estar vazio");
        }
        if (c.getNome().length() > 100) {
            throw new RuntimeException("Nome deve ter no máximo 100 caracteres");
        }


        if (c.getEmail() == null || c.getEmail().trim().isEmpty()) {
            throw new RuntimeException("E-mail não pode estar vazio");
        }
        if (c.getEmail().length() > 60) {
            throw new RuntimeException("E-mail deve ter no máximo 60 caracteres");
        }
        if (!c.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new RuntimeException("E-mail inválido");
        }

        
        if (c.getTelefone() == null || c.getTelefone().trim().isEmpty()) {
            throw new RuntimeException("Telefone não pode estar vazio");
        }
        if (!c.getTelefone().matches("\\d+")) {
            throw new RuntimeException("Telefone deve conter apenas números");
        }
        if (c.getTelefone().length() != 11) {
            throw new RuntimeException("Telefone deve conter exatamente 11 dígitos");
        }

        
        if (c.getSobre() == null || c.getSobre().trim().isEmpty()) {
            throw new RuntimeException("Campo 'Sobre' não pode estar vazio");
        }
        if (c.getSobre().length() > 200) {
            throw new RuntimeException("Campo 'Sobre' deve ter no máximo 200 caracteres");
        }

        
        if (c.getEspecialidade() == null || c.getEspecialidade().trim().isEmpty()) {
            throw new RuntimeException("Especialidade não pode estar vazia");
        }
        if (c.getEspecialidade().length() > 80) {
            throw new RuntimeException("Especialidade deve ter no máximo 80 caracteres");
        }
    }
}
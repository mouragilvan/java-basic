package com.api.agendamento.anotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TelefoneValidator implements ConstraintValidator<Telefone, String> {
	
	private static final int MAX_LENGTH = 14;

    @Override
    public void initialize(Telefone annotation) {
    }

    @Override
    public boolean isValid(String telefone, ConstraintValidatorContext context) {
        if (telefone == null || telefone.isEmpty()) {
            return true; // Permite campos vazios, se necessário
        }
        
        if (telefone.length() > MAX_LENGTH) {
            return false; // Verifica o limite máximo de caracteres
        }
        
        // Remove todos os caracteres não numéricos
        String digitsOnly = telefone.replaceAll("\\D", "");
        
        // Verifica se todos os caracteres restantes são dígitos
        return digitsOnly.matches("[0-9]+");
    }
}

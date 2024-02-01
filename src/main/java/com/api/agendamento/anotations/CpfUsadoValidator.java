package com.api.agendamento.anotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.api.agendamento.services.CustomerService;

public class CpfUsadoValidator implements ConstraintValidator<CpfUsado, String>{

	
    private final CustomerService customerService;

    public CpfUsadoValidator(CustomerService customerService) {
        this.customerService = customerService;
    }
			
	@Override
    public void initialize(CpfUsado annotation) {
    }

	@Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {
		
		if(this.customerService.existsByCpf(cpf)) {
			return false;
		}
		
		return true;
	}
	
	
}

package com.api.agendamento.anotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CpfUsadoValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CpfUsado {
    String message() default "Cpf já está sendo utilizado por outro cadastro";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

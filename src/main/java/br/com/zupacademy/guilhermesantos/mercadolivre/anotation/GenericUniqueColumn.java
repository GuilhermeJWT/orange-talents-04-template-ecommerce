package br.com.zupacademy.guilhermesantos.mercadolivre.anotation;

import br.com.zupacademy.guilhermesantos.mercadolivre.exception.GenericUniqueColumnValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {GenericUniqueColumnValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GenericUniqueColumn {
	
	String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> domainClass();

}

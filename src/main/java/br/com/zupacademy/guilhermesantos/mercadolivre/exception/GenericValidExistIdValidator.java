package br.com.zupacademy.guilhermesantos.mercadolivre.exception;

import br.com.zupacademy.guilhermesantos.mercadolivre.anotation.GenericValidExistId;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class GenericValidExistIdValidator implements ConstraintValidator<GenericValidExistId, Long>{

    @PersistenceContext
    private EntityManager manager;

    private Class<?> clazz;
    private String field;

    @Override
    public void initialize(GenericValidExistId params) {
        clazz = params.domainClass();
        field = params.fieldName();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        List<?> resultList = manager
                .createQuery("select 1 from " +clazz.getName()+ " where " +field+ "=:value")
                .setParameter("value", value)
                .getResultList();

        Assert.state(resultList.size() <= 1, "Foi encontrado mais de um "+clazz.getName()+" com o atributo "+field+" = "+value);

        return !resultList.isEmpty();

    }

}

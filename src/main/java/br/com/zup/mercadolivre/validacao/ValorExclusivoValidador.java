package br.com.zup.mercadolivre.validacao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ValorExclusivoValidador implements ConstraintValidator<ValorExclusivo, Object> {


    private String domainAtribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager manager;


    @Override
    public void initialize(ValorExclusivo uv) {
        domainAtribute = uv.fieldName();
        klass = uv.domainClass();
    }

    @Override
    public boolean isValid(Object criterio, ConstraintValidatorContext context) {
        Query query = manager.createQuery("SELECT q FROM " +
                klass.getSimpleName() + " q WHERE q." + domainAtribute + " =  :pCriterio ");
        query.setParameter("pCriterio", criterio);
        boolean passou = query.getResultList().isEmpty();
        return passou;
    }
}

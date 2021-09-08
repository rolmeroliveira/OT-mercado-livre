package br.com.zup.mercadolivre.validacao;

import br.com.zup.mercadolivre.produto.ProdutoRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.List;

public class NomeRepetidoValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return ProdutoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        ProdutoRequest req = (ProdutoRequest) target;
        List<String> repetidos = req.retornaCaracteristicasRepetidas();
        if(!repetidos.isEmpty()){
            String mensagem =  repetidos.size() > 1 ?
                    "As seguintes características estão repetidas: " :
                    "A seguinte característica está repetida: ";
            errors.rejectValue("caracteristicaProdutoRequest",null,
                    mensagem + repetidos);
        }
    }
}
